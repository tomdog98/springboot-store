package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//表示标注当前的类 是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//启动这个单元测试类（必须传递一个参数 必须是SpringRunner的实例类型）
@RunWith(SpringRunner.class)
public class UserServiceTest {
    //idea有检测的功能，接口是不能够直接创建Bean的（动态代理技术来解决）
    @Autowired
    private IUserService userService;
    /**
     *
     * 1、必须在@Test注解修饰
     * 2、返回值类型必须是void
     * 3、方法的参数列表不指定任何类型
     * 4、方法的访问修饰符必须是public
     */
    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("yuanxin03");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (Exception e) {
            //获取类的对象，在获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User user = userService.login("admin", "123456");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(13,"test003","123","321");
    }
    @Test
    public void getByUid() {
        System.out.println(userService.getByUid(13));
    }

    @Test
    public void changeInfo() {
        User user = new User();
        user.setPhone("12389899898");
        user.setEmail("yuan@qq.com");
        user.setGender(0);
        userService.changeInfo(13,"管理员",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(13,"/upload/test.png","小明");
    }
}
