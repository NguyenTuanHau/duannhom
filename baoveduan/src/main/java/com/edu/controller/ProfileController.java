package com.edu.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.entity.Account;
import com.edu.service.AccountService;
import com.edu.service.impl.AccountServiceImpl;

@Controller
@RequestMapping("profile")
public class ProfileController {
	@Autowired
	AccountService accountService;
	@Autowired
	AccountServiceImpl accountServiceImpl;

	@GetMapping("/user")
	public String profile(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("account", accountService.findById(username));
		return "security/profile";
	}

	@PostMapping("/user")
	public String processProfile(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
//		String birthday = request.getParameter("birthday");
//		String gender = request.getParameter("gender");
		String username = request.getRemoteUser();
		Account account = accountService.findById(username);
		accountServiceImpl.profileupdate(account, name, address, phone);
		model.addAttribute("message", "Bạn đã thay đổi thành công");
		return "forward:/home";
	}
	
	@GetMapping("/changepassword")
	public String change() {
		return "security/Change_ps2";
	}

	@PostMapping("/changepassword")
	public String processPassword(HttpServletRequest request, Model model) {
		String password = request.getParameter("password");
		String username = request.getRemoteUser();
		Account account = accountService.findById(username);
		accountServiceImpl.changepassword(account, password);
		model.addAttribute("message", "Bạn đã thay đổi thành công mật khẩu của bạn.");
		return "security/Change_ps2";
	}
}
