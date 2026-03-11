package controller;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.Member;
import model.MemberDao;

@WebServlet(urlPatterns= {"/member/*"},
		initParams= {@WebInitParam(name="view",value="/view/")})
public class MemberController extends MskimRequestMapping{
	private MemberDao dao = new MemberDao();
/*	
	회원가입폼 : http://localhost:8080/model2study/member/joinForm : 메서드 없으면
	                              /webapp/view/member/joinForm.jsp view로 호출됨
*/		
	@RequestMapping("join") //http://localhost:8080/model2study/member/join
	public String join(HttpServletRequest request, HttpServletResponse response) {
		//request : CharEncodingFilter 에서 인코딩 완료된 객체
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setGender(Integer.parseInt(request.getParameter("gender")));
		mem.setTel(request.getParameter("tel"));
		mem.setEmail(request.getParameter("email"));
		mem.setPicture(request.getParameter("picture"));
		if(dao.insert(mem)) {
			request.setAttribute("msg", mem.getName()+"님 회원 가입 되었습니다.");
			request.setAttribute("url", "loginForm");
		} else {
			request.setAttribute("msg", mem.getName()+"님 회원가입시 오류 발생했습니다.");
 	        request.setAttribute("url", "joinForm");
		}
		return "alert"; //view의 이름. /webapp/view/alert.jsp
	}
	@RequestMapping("login") //http://localhost:8080/model2study/member/login
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		Member mem = dao.selectOne(id);
		String msg = null;
		String url = "loginForm";
		if(mem == null) {
			msg = "아이디를 확인하세요";
		} else if(!pass.equals(mem.getPass())) {
			msg = "비밀번호가 틀립니다";
		} else {
			msg = mem.getName() +"님이 로그인 하셨습니다.";
			url = "main";
			request.getSession().setAttribute("login", id);
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}
/*
  1. 로그인 여부 검증 => main.jsp 페이지는 로그인상태에서만 조회가 되어야 함.
     현재 로그인 상태 : main.jsp 화면 출력
     로그아웃 상태 : 로그인이 필요한 화면입니다. 메세지 출력 후 loginForm 페이지로 이동
 */
	@RequestMapping("main") //http://localhost:8080/model2study/member/main
	public String main(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		if(login == null ||login.trim().equals("")) {
			request.setAttribute("msg", "로그인 하세요");
			request.setAttribute("url", "loginForm");
			return "alert";
		}
		return "member/main";				
	}	
	@RequestMapping("logout") //http://localhost:8080/model2study/member/logout
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "redirect:loginForm"; //loginForm을 redirect 하도록 MskimRequestMapping 서블릿에서 설정
	}
}
