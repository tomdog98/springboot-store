package com.cy.store.service.impl;

import com.cy.store.service.ICartService;
import com.cy.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceImplTest {


    //已存在的意见购物车中的数据的更新
    @Autowired
    private ICartService service;
    @Test
    public void addToCart() {
        service.addToCart(2,10000007,1,"Tom");
        System.out.println("OK");

    }

    @Test
    public void getVOByCids() {
        Integer[] cids = {1, 2, 6, 7, 8, 9, 10};
        Integer uid = 31;
        List<CartVO> list = service.getVOByCids(uid, cids);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }

}