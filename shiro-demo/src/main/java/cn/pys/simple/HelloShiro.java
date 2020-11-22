package cn.pys.simple;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;

/**
 * @Description
 * @Date 2020/11/17 11:36
 * @Created by pengys
 */
public class HelloShiro {

    public static void main(String[] args) {
        check();
        //authorization();
    }

    /**
     * 授权
     */
    private static void authorization() {
        // 配置在ini中
        //Subject subject = login("classpath:ini/shiro.ini", "pys", "123");
        //Assert.assertTrue("没有创建权限",subject.isPermitted("user:create"));
        //Assert.assertTrue("没有更新和删除的权限",subject.isPermittedAll("user:update","user:delete"));
        // 通过if判断角色
        //if(subject.hasRole("user:create")){ }

        // 配置在数据库中
        Subject subject = login("classpath:ini/shiro-jdbc-authorizer.ini", "pys", "123");
        Assert.assertTrue("没有权限",subject.isPermitted("user1:view"));
    }

    /**
     * 验证
     */
    private static void check() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        // shiro自带realm认证
        //login("classpath:ini/shiro.ini","pys","123");
        // 自定义realm认证
        login("classpath:ini/shiro-realm.ini","pys","123");
        // jdbc认证
        //login("classpath:ini/shiro-jdbc-realm.ini","pys","123");

    }

    private static Subject login(String iniPath, String userName, String password) {
        // jdbc认证
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory(iniPath);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        try {
            //4、登录，即身份验证
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }
        Assert.assertTrue("认证失败", subject.isAuthenticated());
        System.out.println("认证成功");
        //6、退出
        subject.logout();
        return subject;
    }
}
