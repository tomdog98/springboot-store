package com.cy.store.service.impl;

import com.cy.store.entity.District;
import com.cy.store.service.IDistrictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceImplTest {

    @Autowired
    private IDistrictService service;
    @Test
    public void getByParent() {
        //86表示中国,所有省的父代号都是86
        List<District> list = service.getByParent("86");
        for (District d:list){
            System.out.println(d);
        }

    }
}