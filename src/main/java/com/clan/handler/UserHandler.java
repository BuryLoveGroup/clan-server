package com.clan.handler;

import com.clan.bean.User;
import com.clan.dao.UserMapper;
import com.clan.database.MapperFactory;
import com.clan.exception.AppException;
import com.clan.exception.ParamException;
import com.clan.utils.HttpServerExchangeUtil;
import com.clan.utils.VerificationUtils;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by robot on 2017/11/14.
 */
public class UserHandler implements HttpHandler{

    private UserMapper userMapper;

    private SimpleDateFormat sdf;

    public UserHandler(){
        userMapper = MapperFactory.createMapper(UserMapper.class);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }


    public void handleRequest(HttpServerExchange exchange) throws Exception {
        //设置统一的responseHeader，采用RestFul风格，应该设置为json
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html; charset=utf-8");
        //全局异常处理
        try {
            //获取参数
            Map<String, Object> parameters = HttpServerExchangeUtil.getParameter(exchange);

            if("get".equals(exchange.getRequestMethod())){

                Map<String,String> map = new HashMap<String, String>();
                map.put("name", VerificationUtils.objectToString(parameters.get("name")));
                map.put("password", VerificationUtils.objectToString(parameters.get("password")));

                User user = userMapper.selectByNameAndPassword(map);
                if(user == null){
                    exchange.getResponseSender().send("用户名或密码错误");
                }
                exchange.getResponseSender().send("登录成功！");
            }else if ("post".equals(exchange.getRequestMethod())){
                User user = new User();
                String uuid = UUID.randomUUID().toString();

                user.setClanId((Integer)parameters.get("clanId"));
                user.setName(VerificationUtils.objectToString(parameters.get("name")));
                user.setPhone(VerificationUtils.objectToString(parameters.get("phone")));
                user.setPassword(VerificationUtils.objectToString(parameters.get("password")));
                user.setSex((Short)parameters.get("sex"));
                user.setArea(VerificationUtils.objectToString(parameters.get("area")));
                user.setAutograph(VerificationUtils.objectToString(parameters.get("autograph")));
                user.setGrade((Short) parameters.get("grade"));
                if(parameters.get("autograph") != null){
                    user.setBirthday(sdf.parse(parameters.get("autograph").toString()));
                }
                user.setRegistTime(new Date());

                userMapper.insertSelective(user);

                exchange.getResponseSender().send("注册成功");

            }

        } catch (Exception e) {
            if (e instanceof AppException) {
                exchange.getResponseSender().send(e.getMessage());
            } else {
                exchange.setStatusCode(500).getResponseSender().send("SERVER ERROR!");
            }
        }
    }
}