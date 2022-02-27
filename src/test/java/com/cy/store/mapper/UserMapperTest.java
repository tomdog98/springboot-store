package com.cy.store.mapper;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.service.ex.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//表示标注当前的类 是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//启动这个单元测试类（必须传递一个参数 必须是SpringRunner的实例类型）
@RunWith(SpringRunner.class)
public class UserMapperTest {
    //idea有检测的功能，接口是不能够直接创建Bean的（动态代理技术来解决）
    @Autowired
    private UserMapper userMapper;
    /**
     *
     * 1、必须在@Test注解修饰
     * 2、返回值类型必须是void
     * 3、方法的参数列表不指定任何类型
     * 4、方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(11,"321","管理员",new Date());
    }
    /*根据用户的id查询用户的数据
     * 用户的id
     * 如果找到则返回对象，反之返回null
     * */

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(11));
    }
    @Test
    public void updateINfoByUid(){
        User user = new User();
        user.setUid(13);
        user.setPhone("18847418471");
        user.setEmail("test002@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }




}
