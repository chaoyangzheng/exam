package com.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.exam.dao")
@ServletComponentScan       //好像是支持filter跨域配置
public class ExamApplication {

    public static void main (String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

}
