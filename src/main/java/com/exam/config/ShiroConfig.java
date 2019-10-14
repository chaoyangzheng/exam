package com.exam.config;

import com.exam.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chaoyang
 * @date 2019/10/14
 */
public class ShiroConfig {
    // shiro资源过滤配置
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 未登陆情况下，访问需要登陆后才能访问资源时，跳转到指定资源（比如登陆页面）
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 当没有权限访问某些资源时，跳转到的资源
        shiroFilterFactoryBean.setUnauthorizedUrl("/notPermision");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // authc:必须认证通过才可以访问;
        // anon: 匿名访问
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");

        filterChainDefinitionMap.put("/admin/**", "authc");
        filterChainDefinitionMap.put("/user/**", "authc");

        //必须放在所有权限设置的最后，匹配的是不满足前面匹配条件的资源
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    // 安全管理对象
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        return defaultSecurityManager;
    }

    // 自定义realm对象
    @Bean
    public MyRealm customRealm() {
        MyRealm customRealm = new MyRealm();
        return customRealm;
    }
}
