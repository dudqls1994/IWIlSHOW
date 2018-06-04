package media.co.kr.controller;

import java.security.Principal;
import java.sql.SQLException;


import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private SqlSession sqlsession;
	
	@RequestMapping("/Home.do")
	public String Home1() throws ClassNotFoundException, SQLException{
	
		return "home.main";
		
		
	}
	
}
