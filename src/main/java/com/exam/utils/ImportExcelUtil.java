package com.exam.utils;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR;

/**
 * poi 实现 excel 导入 工具类
 */
public class ImportExcelUtil {

    //正则表达式 用于匹配属性的第一个字母
    private static final String REGEX = "[a-zA-Z]";

    /**
     * Excel数据转 list
     *
     * @param originUrl   Excel表的所在路径
     * @param //datePattern 日期格式 如"yyyy-MM-dd hh:mm:ss"
     * @param //filedsIndex 模板定义的属性名的行位置
     * @param //startRow    从第几行开始
     * @param //endRow      到第几行结束 (0表示所有行;正数表示到第几行结束;负数表示到倒数第几行结束)
     * @param clazz       要返回的对象集合的类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> importExcel(String originUrl,Class<T> clazz) throws IOException {
        int startRow = 1;
        int endRow = 0;
        int filedsIndex = 0;
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        // 判断文件是否存在
        File file = new File(originUrl);
        if (!file.exists()) {
            throw new IOException("文件名为" + file.getName() + "Excel文件不存在！");
        }
        InputStream fis = new FileInputStream(file);

        return doImportExcel(fis,clazz);
    }

    /**
     * Excel数据转 list
     *
     * @param inputStream Excel文件输入流
     * @param //datePattern 日期格式 如"yyyy-MM-dd hh:mm:ss"
     * @param //filedsIndex 模板定义的属性名的行位置
     * @param //startRow    从第几行开始
     * @param //endRow      到第几行结束 (0表示所有行;正数表示到第几行结束;负数表示到倒数第几行结束)
     * @param clazz       要返回的对象集合的类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> importExcel(InputStream inputStream,  Class<T> clazz) throws IOException {
        return doImportExcel(inputStream,clazz);
    }

    /**
     * 真正实现
     *
     * @param inputStream
     * @param //datePattern
     * @param //filedsIndex
     * @param //startRow
     * @param //endRow
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    private static <T> List<T> doImportExcel(InputStream inputStream,Class<T> clazz) throws IOException {
        int startRow = 1;
        int endRow = 0;
        int filedsIndex = 0;
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        Workbook wb;
        Sheet sheet;
        Row filedsRow = null;
        List<Row> rowList = new ArrayList<>();
        try {
            // 去读Excel
            // HSSFWorkbook wb = new HSSFWorkbook(fis);
            // 使用workbook 支持2003/2007
            wb = WorkbookFactory.create(inputStream);
            sheet = wb.getSheetAt(0);
            // 获取最后行号
            int lastRowNum = sheet.getLastRowNum();

            int rowLength = lastRowNum;
            if (endRow > 0) {
                rowLength = endRow;
            } else if (endRow < 0) {
                rowLength = lastRowNum + endRow;
            }

            // 获取属性列字段
            filedsRow = sheet.getRow(filedsIndex);
            // 循环读取
            Row row;
            for (int i = startRow; i <= rowLength; i++) {
                row = sheet.getRow(i);
                rowList.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                throw e;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return returnObjectList(datePattern, filedsRow, rowList, clazz);
    }

    /**
     * 功能:返回指定的对象集合
     */
    private static <T> List<T> returnObjectList(String datePattern, Row filedsRow, List<Row> rowList, Class<T> clazz) {
        List<T> objectList = new ArrayList<>();
        try {
            T obj;
            String attribute;
            String value;

            for (Row row : rowList) {
                obj = clazz.newInstance();
                for (int j = 0; j < filedsRow.getLastCellNum(); j++) {
                    attribute = getCellValue(filedsRow.getCell(j));
                    if (!attribute.equals("")) {
                        value = getCellValue(row.getCell(j));
                        setAttrributeValue(obj, attribute, value, datePattern);
                    }
                }
                objectList.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectList;
    }

    /**
     * 功能:获取单元格的值
     */
    private static String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                case CELL_TYPE_NUMERIC:
                    result = cell.getNumericCellValue();
                    break;
                case CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                case CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case CELL_TYPE_BLANK:
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }

    /**
     * 功能:给指定对象的指定属性赋值
     */
    private static void setAttrributeValue(Object obj, String attribute, String value, String datePattern) {
        //得到该属性的set方法名
        String method_name = convertToMethodName(attribute, obj.getClass(), true);
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            /**
             * 因为这里只是调用bean中属性的set方法，属性名称不能重复
             * 所以set方法也不会重复，所以就直接用方法名称去锁定一个方法
             * （注：在java中，锁定一个方法的条件是方法名及参数）
             */
            if (method.getName().equals(method_name)) {
                Class<?>[] parameterC = method.getParameterTypes();
                try {
                    /**如果是(整型,浮点型,布尔型,字节型,时间类型),
                     * 按照各自的规则把value值转换成各自的类型
                     * 否则一律按类型强制转换(比如:String类型)
                     */
                    if (parameterC[0] == int.class || parameterC[0] == Integer.class) {
                        if (value != null && value.length() > 0) {
                            value = value.substring(0, value.lastIndexOf("."));
                            method.invoke(obj, Integer.valueOf(value));
                        }

                        break;
                    } else if (parameterC[0] == long.class || parameterC[0] == Long.class) {
                        if (value != null && value.length() > 0) {
                            value = value.substring(0, value.lastIndexOf("."));
                            method.invoke(obj, Long.valueOf(value));
                        }
                        break;
                    } else if (parameterC[0] == float.class || parameterC[0] == Float.class) {
                        if (value != null && value.length() > 0) {
                            method.invoke(obj, Float.valueOf(value));
                        }
                        break;
                    } else if (parameterC[0] == double.class || parameterC[0] == Double.class) {
                        if (value != null && value.length() > 0) {
                            method.invoke(obj, Double.valueOf(value));
                        }
                        break;
                    } else if (parameterC[0] == byte.class || parameterC[0] == Byte.class) {
                        if (value != null && value.length() > 0) {
                            method.invoke(obj, Byte.valueOf(value));
                        }
                        break;
                    } else if (parameterC[0] == boolean.class || parameterC[0] == Boolean.class) {
                        if (value != null && value.length() > 0) {
                            method.invoke(obj, Boolean.valueOf(value));
                        }
                        break;
                    } else if (parameterC[0] == Date.class) {
                        if (value != null && value.length() > 0) {
                            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                            Date date = null;
                            try {
                                date = sdf.parse(value);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            method.invoke(obj, date);
                        }

                        break;
                    } else if (parameterC[0] == BigDecimal.class) {
                        if (value != null && value.length() > 0) {
                            method.invoke(obj, new BigDecimal(value));
                        }
                        break;
                    } else {
                        if (value != null && value.length() > 0) {
                            method.invoke(obj, parameterC[0].cast(value));
                        }
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 功能:根据属性生成对应的set/get方法
     */
    private static String convertToMethodName(String attribute, Class<?> objClass, boolean isSet) {
        /** 通过正则表达式来匹配第一个字符 **/
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(attribute);
        StringBuilder sb = new StringBuilder();
        /** 如果是set方法名称 **/
        if (isSet) {
            sb.append("set");
        } else {
            /** get方法名称 **/
            try {
                Field attributeField = objClass.getDeclaredField(attribute);
                /** 如果类型为boolean **/
                if (attributeField.getType() == boolean.class || attributeField.getType() == Boolean.class) {
                    sb.append("is");
                } else {
                    sb.append("get");
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        /** 针对以下划线开头的属性 **/
        if (attribute.charAt(0) != '_' && m.find()) {
            sb.append(m.replaceFirst(m.group().toUpperCase()));
        } else {
            sb.append(attribute);
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        String originUrl = "D:\\XY\\test.xls";

    /*    List<SelectQuestions> linesList;
        try {
            linesList = ImportExcelUtil.importExcel(originUrl,SelectQuestions.class);
            for (SelectQuestions lists : linesList) {

            }
        } catch (IOException e) {*/
        /*// TODO Auto-generated catch block
            e.printStackTrace();
        }
*/
    }

}
