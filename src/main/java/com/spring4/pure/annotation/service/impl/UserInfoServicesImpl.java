package com.spring4.pure.annotation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring4.pure.annotation.dao.mapper.UserInfoMapper;
import com.spring4.pure.annotation.entity.UserInfoEntity;
import com.spring4.pure.annotation.service.UserInfoServices;
@Service
public class UserInfoServicesImpl implements UserInfoServices {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public List<UserInfoEntity> getUserInfoAll() {
		return this.userInfoMapper.getUserInfoAll();
	}

}
