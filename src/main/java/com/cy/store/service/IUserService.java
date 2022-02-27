package com.cy.store.service;

import com.cy.store.entity.User;

import java.util.Date;

/** 用户模块业务层接口*/
public interface IUserService {
    /**
     * 用户注册方法
     * User用户的数据对象
     * */
    void reg(User user);

    /*
     * 用户登录功能
     * 当前匹配的用户数据，如果没有则返回null值
     * */
    User login(String username,String password);

    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**根据用户的id查询用户的数据
     * uid 用户id
     * 返回值用户的数据
     */
    User getByUid(Integer uid);
    /**更新用户的数据操作*/
    void changeInfo(Integer uid,String username,User user);

    /**
     *修改用户的头像
     * @param uid 用户的id
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     */
    void changeAvatar(Integer uid,
                      String avatar,
                      String username);
}
