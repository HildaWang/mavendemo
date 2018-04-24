package com.mavendemo.controller;

import com.mavendemo.model.User;
import com.mavendemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //查询
    @ResponseBody
    @RequestMapping(value="/findUser/{userId}",method = RequestMethod.GET)
    public String findUser(@PathVariable("userId") String userId){
        int id = Integer.parseInt(userId);
        System.out.println("1.user的id是："+id);
        User user = userService.findUser(id);
        System.out.println("2.user的id是："+user.getId());
        System.out.println("user的名字是："+user.getName());
        return user.toString();
    }


    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHello(){
        System.out.println("Hello！");
        Map<String,String> helloMap = new HashMap<>();
        helloMap.put("helloString","Hello World!");
        return helloMap.toString();
    }


    //增加，解决返回中文乱码
    @ResponseBody
    @RequestMapping(value="/saveUser",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String saveUser(Integer id,String name,HttpServletResponse response){
        User userAdd = new User(2,"name2");
        int idAdd = userAdd.getId();
        String nameAdd = userAdd.getName();
        Integer num = userService.saveUser(idAdd,nameAdd);
        StringBuilder returnMsg = new StringBuilder();
        if(num!=null&&num>0){
            System.out.println("保存成功了！");
            returnMsg.append("resp:"+"添加用户成功！");
        }else {
            System.out.println("保存失败了！");
            returnMsg.append("resp:"+"添加用户失败！");
        }
//        response.setHeader("Content-Type", "text/html;charset=utf-8");
        return returnMsg.toString();
    }


    //删除
    @ResponseBody
    @RequestMapping(value = "/delUser",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String delUser(String Params){
        StringBuilder returnMsg = new StringBuilder();
        StringBuilder jsonParams = new StringBuilder("{\"method\":\"DEL\"");
        String id = "2";
        jsonParams.append(",\"delId\":\"");
        jsonParams.append(id);
        jsonParams.append("\"}");

        System.out.println("jsonParam:"+jsonParams);
        JSONObject jobj = JSONObject.parseObject(jsonParams.toString());
        int delId = Integer.parseInt(jobj.getString("delId"));
        String method = jobj.getString("method");
        if("DEL".equals(method)){
            Integer num = userService.deleteUser(delId);
            System.out.println("UserController userService.deleteUser(delId)=="+num);
            if(num!=null&&num>0){
                System.out.println("删除成功了！");
                returnMsg.append("resp:"+"删除用户成功！");
            }else {
                System.out.println("删除失败了！");
                returnMsg.append("resp:"+"删除用户失败！");
            }

        }else{
            returnMsg.append("resp:"+"不是删除！无效操作");
        }
        return returnMsg.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String updateUser(String Params){
        StringBuilder returnMsg = new StringBuilder();
        StringBuilder jsonParams = new StringBuilder("{\"method\":\"UPDATE\"");
        String id = "2";
        jsonParams.append(",\"updateId\":\"");
        jsonParams.append(id);
        jsonParams.append("\",\"updateName\":\"");
        String name = "name22";
        jsonParams.append(name);
        jsonParams.append("\"}");

        System.out.println("jsonParam:"+jsonParams);
        JSONObject jobj = JSONObject.parseObject(jsonParams.toString());
        Integer updateId = Integer.parseInt(jobj.getString("updateId"));
        String updateName = jobj.getString("updateName");
        User updateUser = new User(updateId,updateName);
        String method = jobj.getString("method");
        if("UPDATE".equals(method)){
            Integer num = userService.updateUser(updateUser);
            System.out.println("UserController userService.updateUser(updateUser)=="+num);
            if(num!=null&&num>0){
                System.out.println("修改成功了！");
                returnMsg.append("resp:"+"修改用户成功！");
            }else {
                System.out.println("修改失败了！");
                returnMsg.append("resp:"+"修改用户失败！");
            }

        }else{
            returnMsg.append("resp:"+"不是修改更新！无效操作");
        }
        return returnMsg.toString();
    }



}
