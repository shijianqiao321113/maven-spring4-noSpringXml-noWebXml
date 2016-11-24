package com.spring4.pure.annotation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.spring4.pure.annotation.activemq.MessageQueueSender;
import com.spring4.pure.annotation.entity.UserInfoEntity;
import com.spring4.pure.annotation.redis.RedisOper;
import com.spring4.pure.annotation.service.UserInfoServices;

@Controller
public class TestController {
	
	@Value("${activemq.queue.name}")
	private String queueName;
	
	@Autowired
	private UserInfoServices userInfoServices;
	
	@Autowired
	private RedisOper redisOper;
	
	@Autowired
	private MessageQueueSender messageQueueSender;
	
	@RequestMapping("/user/login")
	public String showView(HttpServletRequest request,HttpServletResponse response){
		List<UserInfoEntity> arrylist = this.userInfoServices.getUserInfoAll();
		request.setAttribute("userInfoList",arrylist);
		
		for (UserInfoEntity userInfoEntity : arrylist) {
			redisOper.setUserInfo_Hs("userInfo_key",userInfoEntity.getUserLogin(),JSON.toJSONString(userInfoEntity));
		}
		
		for (UserInfoEntity userInfoEntity : arrylist) {
			messageQueueSender.sendTextMessage(queueName, JSON.toJSONString(userInfoEntity));
		}
		
		return "show";
	}
}
