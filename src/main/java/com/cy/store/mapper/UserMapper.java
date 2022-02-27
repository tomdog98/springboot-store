package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/** 用户模块的持久层接口 */
public interface UserMapper {

    Integer insert(User user);

    //用户名
    //如果找到对应的用户则返回这个用户数据，没有这返回null
    User findByUsername(String username);

    /*根据用户的uid来修改用户密码
    uid:用户的id
    password:用户输入的新密码
    modifiedUser：表示修改的执行者
    modifiedTime：修改数据的时间
    返回值为首受影响的行数
    * */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /*根据用户的id查询用户的数据
    * 用户的id
    * 如果找到则返回对象，反之返回null
    * */
    User findByUid(Integer uid);

    /**更新用户的数据信息
     * user 用户的数据
     * 返回值为受影响的行数
     * */
    Integer updateInfoByUid(User user);

    /**
     * 根据uid更新用户的头像
     * @param uid 用户的id
     * @param avatar 新头像的路径
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);


}
