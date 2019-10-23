package service;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import dao.UserMapper;
import entity.User;

@Service
public class UserInfoService {
	
	private UserMapper userMapper;
	public User getUserInfoByUserName(String username) {
		 
	 
	    return userMapper.selectByUserName(username);
 }
	public int register(User userinfo) {
		
		return userMapper.insert(userinfo);
	}
	
	public User findUserBynumber(String number) {
		
		return userMapper.findByStuNO(number);
	}
}
