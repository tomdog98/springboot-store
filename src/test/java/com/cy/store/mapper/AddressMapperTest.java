package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//表示标注当前的类 是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//启动这个单元测试类（必须传递一个参数 必须是SpringRunner的实例类型）
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    //idea有检测的功能，接口是不能够直接创建Bean的（动态代理技术来解决）
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(14);
        address.setPhone("13211112222");
        address.setName("女朋友");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(14);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(13);
        System.out.println(list);
    }


    @Test
    public void findByAid() {
        Address address = addressMapper.findByAid(9);
        System.out.println(address);
    }

    @Test
    public void updateNonDefault() {
        addressMapper.updateNonDefault(13);

    }

    @Test
    public void updateDefaultByAid() {
        addressMapper.updateDefaultByAid(9,"管理员",new Date());
    }

    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(6);


    }

    @Test
    public void findLastModified() {

        Address lastModified = addressMapper.findLastModified(13);
        System.out.println(lastModified);
    }
}
