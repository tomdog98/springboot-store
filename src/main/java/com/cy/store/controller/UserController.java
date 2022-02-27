package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
            userService.reg(user);
            return new JsonResult<>(ok);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username,String password,HttpSession session ){
        User data = userService.login(username, password);
        //想session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        //获取session中的绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(ok,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(ok);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(ok,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        //user对象中有四部分的数据：username，phone，email，gender
        //uid数据需要再次封装到user对象中
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(ok);
    }
    /*设置上传文件的最大值*/
    public static final int AVATAR_MAX_SIZE =10 * 1024 * 1024;
    /*限制上传文件的类型*/
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    /**
     * MultipartFile接口是由springmvc提供的接口，为我们包装了获取文件类型的数据（任何类型的文件都可以）
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file){
        //判断文件是否为null
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //判断文件的类型是否是我们规定的和后缀类型
        String contentType = file.getContentType();
        //如果集合包含某个元素则返回true
        if (!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        //上传的文件../upload/文件.png
        String parent = session.getServletContext().getRealPath("upload");
        //File对象指向之歌路径，file是否存在
        File dir = new File(parent);
        if (!dir.exists()){//检测目录是否存在
            dir.mkdir();//创建当前的目录
        }
        //获取到这个文件名称，UUID工具类生成一个新的字符串
        String originalFilename = file.getOriginalFilename();
        System.err.println("originalFilename == "+originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest = new File(dir,filename);//是一个空文件
        //参数file中数据写入到这个空文件中
        try {
            file.transferTo(dest);//把file数据写入到dest文件
        } catch (IOException e) {
            throw new FileUploadException("文件读写异常");
        }catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }

        Integer uld = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路径
        String avatar = "/upload/"+filename;
        userService.changeAvatar(uld,avatar,username);
        //返回用户头像的路径给前端页面，将来用于头像展示使用
        return new JsonResult<>(ok,avatar);
    }
}
