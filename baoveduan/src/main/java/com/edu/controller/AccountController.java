package com.edu.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.dao.AccountDAO;
import com.edu.dao.AuthorityDAO;
import com.edu.dao.RoleDAO;
import com.edu.entity.Account;
import com.edu.service.AccountService;




@Controller
@RequestMapping("account")
public class AccountController {
	   @Autowired
		HttpSession session;
	    @Autowired
	    AccountService accountService;
	    @Autowired
	    AccountDAO accountDAO;
	    @Autowired
	    AuthorityDAO authorityDAO;
	    @Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
	    @Autowired
	    RoleDAO roleDAO;

	    @GetMapping("dk")
	    public String add(Model model) {
	        model.addAttribute("account", new Account());
	        return "security/Sign_up";
	    }
//
//	    
//	    @GetMapping("edit/{username}")
//	    public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {
//	        Optional <Account> opt = accountService.findByUsername(username);
//	        Account dto = new Account();
//	        if(opt.isPresent()){
//	            Account entity = opt.get();
//	            BeanUtils.copyProperties(entity, dto);
//	            dto.setIsEdit(true); // thiet lap o che do chinh sua thong tin
//	            model.addAttribute("account", dto);
//	            return new ModelAndView("account/updateProfile", model);
//	        }
//	        model.addAttribute("message", "Register is not existed");
//	        return new ModelAndView("forward:/account", model) ;
//	    }
//
	    @PostMapping("saveOrUpdate")
	    public String saveOrUpdate(ModelMap model, 
	    @Valid @ModelAttribute("account") Account dto, BindingResult result) {
	    	 if(result.hasErrors()){
	             return "security/Sign_up";
	         }
	        Account entity = new Account();
	        BeanUtils.copyProperties(dto, entity);
	        accountService.save(entity);
	        model.addAttribute("message", "Register Success!");
	        return "redirect:/security/login/form";
	    }
//
//	    @RequestMapping("/up")
//		public String list(Model model) {
//				model.addAttribute("account");
//			return "account/updateProfile";
//	
//	
//}
	    
}
