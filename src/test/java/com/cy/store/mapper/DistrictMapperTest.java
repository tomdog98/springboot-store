package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//表示标注当前的类 是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//启动这个单元测试类（必须传递一个参数 必须是SpringRunner的实例类型）
@RunWith(SpringRunner.class)
public class DistrictMapperTest {
    //idea有检测的功能，接口是不能够直接创建Bean的（动态代理技术来解决）
    @Autowired
    private DistrictMapper mapper;

    @Test
    public void findByParent(){
        List<District> list = mapper.findByParent("210100");
        for (District d:list){
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode(){
        String name = mapper.findNameByCode("610000");
        System.out.println(name);
    }



}
