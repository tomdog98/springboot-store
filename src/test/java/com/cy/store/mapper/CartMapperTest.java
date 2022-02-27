package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTest {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {

        Cart cart = new Cart();
        cart.setUid(13);
        cart.setPid(10000002);
        cart.setNum(2);
        cart.setPrice(1000l);
        cartMapper.insert(cart);

    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1,4,"张三",new Date());

    }

    @Test
    public void findByUidAndPid() {
        Cart byUidAndPid = cartMapper.findByUidAndPid(13, 10000002);
        System.out.println(byUidAndPid);
    }

    @Test
    public void findVOByUid(){
        List<CartVO> voByUid = cartMapper.findVOByUid(13);
        System.out.println(voByUid);
    }
    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(1));
    }
    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 6, 7, 8, 9, 10};
        List<CartVO> list = cartMapper.findVOByCids(cids);
        System.out.println(list);
    }
}