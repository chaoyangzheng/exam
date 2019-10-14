package com.exam.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author chaoyang
 * @date 2019/10/14
 */
public class MyRealm extends AuthorizingRealm {

    //获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo (PrincipalCollection principalCollection) {
        return null;
    }

    //跟认证相关，获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken authenticationToken) throws AuthenticationException {
       /* //获取用户的身份信息
        String name = (String) authenticationToken.getPrincipal();
        User user = userService.findUserByName(name);
        SimpleAuthenticationInfo info = null;
        if (user ==null){
            info = new SimpleAuthenticationInfo("","","");
        }else {
            //身份认证信息对象
            //第一个参数，用户身份信息，第二个参数，用户凭证信息，第三个参数，realm的名称
            info = new SimpleAuthenticationInfo(name,user.getPassword(),this.getName());

        }
        return info;*/
       return null;
    }
}
