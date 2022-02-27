package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//表示标注当前的类 是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//启动这个单元测试类（必须传递一个参数 必须是SpringRunner的实例类型）
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    //idea有检测的功能，接口是不能够直接创建Bean的（动态代理技术来解决）
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setUid(14);
        address.setPhone("17811112222");
        address.setName("男朋友");
        addressService.addNewAddress(14,"管理员",address);
    }

    @Test
    public void setDefault(){
        addressService.setDefault(9,13,"管理员");
    }

    @Test
    public void delete(){
        addressService.delete(5,14,"管理员");
    }

}
