package media.co.kr.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import media.co.kr.dto.UserDto;
import media.co.kr.dao.LoginDao;


/**
 * Handles requests for the application home page.
 */
@Controller
public class SignUpController {
	@Autowired
	private SqlSession sqlsession;
	
	@RequestMapping("/Login.do")
	public String Login() throws ClassNotFoundException, SQLException{
		return "home.Login";
	}
	
	@RequestMapping("/SignUp.do")
	public String SignUp() throws ClassNotFoundException, SQLException{
		return "home.SignUp";
	}
	@RequestMapping(value = "Join.do", method = RequestMethod.POST)
	public String Join(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException{
		
		LoginDao loginDao = sqlsession.getMapper(LoginDao.class);
		
		String email = request.getParameter("email"); 
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		String problem =request.getParameter("demo-human");
		if(problem==null) {
			problem = "0";
		}
		System.out.println("emial : "+ email + "pro  "+ problem);
		UserDto userdto = new UserDto(password,name,email,message,Integer.parseInt(problem));		
		
		loginDao.insert_user(userdto);
		
		return "home.main";
	}
	@RequestMapping(value = "Login.do", method = RequestMethod.POST)
	public String Login(HttpServletRequest request, HttpServletResponse response,Model model) throws SQLException, ClassNotFoundException, IOException{
		
		LoginDao loginDao = sqlsession.getMapper(LoginDao.class);
		
		String email = request.getParameter("email"); 
		String password = request.getParameter("password");
		
		List<UserDto> login =  loginDao.Login(email);
		if(login.size()==0) {
			System.out.println("이메일 없는경우");
			model.addAttribute("value","fail");
			return "home.Login";
		}else {
			if(login.get(0).getPassword().equals(password)) {
				System.out.println("로그인완료");
				model.addAttribute("value",email);
				return "loginsuccess.main";
			}else {
				System.out.println("비밀번호 실패");
				model.addAttribute("value","fail");
				return "home.Login";
			}
		}
		
		
		
	}
	
}
