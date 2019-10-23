package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Result;
import entity.User;
import service.UserInfoService;
import util.MD5;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private UserInfoService userservice;

	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public Result<User> register(@RequestBody User user) {
		if(user!=null) {
			User before=userservice.findUserBynumber(user.getStu_no());
			if(before!=null) {
				return new Result<User>(false, "用户已存在");
			}else {
				user.setPassWord(MD5.MD5Encode(user.getPassWord()));
				int state=userservice.register(user);
				if(state>0) {
					return new Result<User>(true, "注册成功");
				}else {
					return new Result<User>(true, "注册失败请稍后重试");
				}
			}
		
		}else {
			return new Result<User>(false, "注册信息为空");
		}
		 
		
	}
}
