package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gdu.mskim.MSLogin;
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
	@RequestMapping("id") //http://localhost:8080/model2study/member/id
	public String id(HttpServletRequest request, HttpServletResponse response) {
		   String email = request.getParameter("email");
		   String tel = request.getParameter("tel");
		   String id = new MemberDao().idSearch(email,tel);
		   if (id == null) {
			   request.setAttribute("msg","아이디를 찾을 수 없습니다" );
			   request.setAttribute("url","idForm" );
			   return "alert";
		   }
		   String showId = id.substring(0,id.length()-2);
		   request.setAttribute("id", showId);
		   return "member/id";
	}
	@RequestMapping("pw") //http://localhost:8080/model2study/member/pw
	public String pw(HttpServletRequest request, HttpServletResponse response) {
		   String id = request.getParameter("id");
		   String email = request.getParameter("email");
		   String tel = request.getParameter("tel");
		   String pass = dao.pwSearch(id,email,tel);
		   if(pass != null) {
			   request.setAttribute("pass", pass.substring(2,pass.length()));
		       return "member/pw";
		   } else {
			   request.setAttribute("msg", "정보에 맞는 비밀번호를 찾을 수 없습니다.");
			   request.setAttribute("url", "pwForm");
			   return "alert";
		   }
	}
   @RequestMapping("idchk")
   public String idchk (HttpServletRequest request,  HttpServletResponse response) {
		   String id = request.getParameter("id");
		   Member mem = dao.selectOne(id);
		   String msg = null;
		   String className = null;		   
		   if (mem == null) {
			   msg = "사용가능한 아이디 입니다.";
			   className = "able";
		   } else {
			   msg = "사용 중인 아이디 입니다.";
			   className = "disable";
		   }
		   request.setAttribute("msg", msg);
		   request.setAttribute("className", className);
		   return "member/idchk";
   }
/*
   1. 로그인 상태 검증 
      로그아웃상태 : 로그인하세요 메세지, loginForm 페이지로 이동
      로그인 상태 :
         - 다른 id를 조회할 수 없음. 단 관리자는 다른 id 조회가능함
           내정보만 조회 가능합니다. 메세지 출력. main 페이지 이동
   2. id 파라미터 조회
   3. id에 해당하는 레코드를 조회하여 Member 객체에 저장(mem)
   4. info.jsp로 Member 객체(이름이 mem) 속성으로 전달   
 */
   @RequestMapping("info")
   public String info (HttpServletRequest request,  HttpServletResponse response) {

	   String url = loginIdCheck(request,response);
	   if(url != null) return url;
	   
	   String id = request.getParameter("id");
	   Member mem = dao.selectOne(id);
	   request.setAttribute("mem", mem);
	   return "member/info";
   }
   @RequestMapping("updateForm")
   public String updateForm (HttpServletRequest request,  HttpServletResponse response) {

	   String url = loginIdCheck(request,response);
	   if(url != null) return url;
	   
	   String id = request.getParameter("id");
	   Member mem = dao.selectOne(id);
	   request.setAttribute("mem", mem);
	   return "member/updateForm";
   }
   /*
   1. 모든 파라미터를 Member 객체에 저장하기
   2. 입력된 비밀번호와 db에 저장된 비밀번호 비교.
       관리자로 로그인 한 경우 관리자비밀번호로 비교
       본인 정보 수정시 본인의 비밀번호로 비교
       불일치 : '비밀번호 오류' 메세지 출력후 updateForm 페이지 이동 
   3. 비밀번호 일치 
   Member 객체의 내용으로 db를 수정하기 : boolean MemberDao.update(Member)
       - 성공 : 회원정보 수정 완료 메세지 출력후 info로 페이지 이동
       - 실패 : 회원정보 수정 실패 메세지 출력 후 updateForm 페이지 이동     
   */
   @RequestMapping("update")
   public String update (HttpServletRequest request,  HttpServletResponse response) {
	   Member mem = new Member();
	   mem.setId(request.getParameter("id"));
	   mem.setPass(request.getParameter("pass"));
	   mem.setName(request.getParameter("name"));
	   mem.setGender(Integer.parseInt(request.getParameter("gender")));
	   mem.setTel(request.getParameter("tel"));
	   mem.setEmail(request.getParameter("email"));
	   mem.setPicture(request.getParameter("picture"));
	   //로그인 정보 데이터 조회. 비밀번호 검증. 
	   String login = (String)request.getSession().getAttribute("login");
	   Member dbMem = dao.selectOne(login);
	   String msg = "비밀번호 오류";
	   String url = "updateForm?id=" + mem.getId();
	   if(mem.getPass().equals(dbMem.getPass()))  { //비밀번호 일치
		  if(dao.update(mem)) {                     //수정성공
			  msg = "수정 성공";
			  url = "info?id="+mem.getId();
		  } else {                                  //수정실패
			  msg = "수정 실패";
		  }
	   }
	   request.setAttribute("msg", msg);
	   request.setAttribute("url", url);
	   return "alert";
   }
   /*
   1. id 파라미터 저장
   2. 로그인 정보 검증
       - 로그아웃상태 : 로그인하세요. loginForm로 페이지 이동
       - 본인탈퇴여부 검증 : 관리자를 제외하고 본인만 탈퇴 가능
         본인이 아닌 경우, 본인만 탈퇴 가능, main로 페이지 이동
    */
   @RequestMapping("deleteForm")
   public String deleteForm (HttpServletRequest request,  HttpServletResponse response) {
	   String id = request.getParameter("id");
	   String url = loginIdCheck(request,response);
	   if(url != null) return url;
	   if(id.equals("admin")) {
		   request.setAttribute("msg", "관리자는 탈퇴 할 수 없습니다.");
		   request.setAttribute("url", "info?id=" + id);
		   return "alert";
	   }
	   return "member/deleteForm";	   
   }
   /*
  1. 2개의 파라미터 정보 변수 저장
  2. 로그인정보 검증
     - 로그아웃상태 : 로그인하세요. loginForm로 페이지 이동
     - 본인만 탈퇴가능(관리자는 제외) : 본인만 탈퇴 가능합니다. main 페이지 이동
     - 관리자가 탈퇴는 불가 : 관리자는 탈퇴불가합니다. list로 페이지 이동
  3. 비밀번호 검증
     - 로그인 정보로 비밀번호 검증. 
     - 비밀번호 불일치 : 비밀번호 오류 메세지 출력. deleteForm로 페이지 이동
  4. db에서 id에 해당하는 회원정보 삭제하기
     boolean MemberDao.delete(id)
     탈퇴 성공 
       - 일반사용자 : 로그아웃실행. 탈퇴메세지 출력, loginForm로 페이지 이동
       - 관리자    : 탈퇴메세지 출력. list로 페이지 이동
     탈퇴 실패      
       - 일반사용자 : 탈퇴실패메세지 출력, main로 페이지 이동
       - 관리자    : 탈퇴실패메세지 출력. list로 페이지 이동
    */
   @RequestMapping("delete")
   public String delete (HttpServletRequest request,  HttpServletResponse response) {
	   String id = request.getParameter("id");
	   String pass = request.getParameter("pass");
	   String url = loginIdCheck(request, response);
	   if(url != null) return url;
	   if(id.equals("admin")) {
		   request.setAttribute("msg", "관리자는 탈퇴 할 수 없습니다.");
		   request.setAttribute("url", "list");
		   return "alert";
	   }
	   String login = (String)request.getSession().getAttribute("login");
	   Member mem = dao.selectOne(login);
	   if(!pass.equals(mem.getPass())) {
		   request.setAttribute("msg", "비밀번호가 틀립니다.");
		   request.setAttribute("url", "deleteForm?id="+id);
		   return "alert";				   
	   }
	   if(login.equals("admin")) request.setAttribute("url", "list");
	   if(dao.delete(id)) {
		   request.setAttribute("msg", id+"회원님이 탈퇴 되었습니다.");
		   if(!login.equals("admin")) {
			   request.getSession().invalidate(); //로그아웃
			   request.setAttribute("url", "loginForm");
		   }
	   } else {
		   request.setAttribute("msg", id+"회원 탈퇴 실패");
		   if(!login.equals("admin")) {
			   request.setAttribute("url", "main");
		   }
	   }
	   return "alert";
   }
   /*
   1. admin으로 로그인 된경우만 조회 가능
   2. db에서 모든 회원목록을 조회하여 List<Member> 객체로 리턴  
    */
   @RequestMapping("list")
   public String list (HttpServletRequest request,  HttpServletResponse response) {
	   String url = loginAdminCheck(request,response);
	   if(url != null) return url;
	   List<Member> list = dao.list();
	   request.setAttribute("list", list);
	   return "member/list";
   }
   /*
    * 본인만 비밀번호 변경 가능 : login 정보로 비밀번호 변경하기
    */
   @RequestMapping("passwordForm")
   public String passwordForm (HttpServletRequest request,  HttpServletResponse response) {
	   String login = (String)request.getSession().getAttribute("login");
	   if(login == null || login.trim().equals("") ) {
		   request.setAttribute("msg", "로그인 하세요");
		   request.setAttribute("url", "loginForm");
		   return "openeralert";
	   }
	   return "member/passwordForm";
   }
   /*
    * 1. 로그인한 사용자의 비밀번호만 변경 가능=> 로그인상태 검증
    *    => 로그아웃인 경우 현재창 닫기. Opener의 화면을 loginForm으로 변경하기
    * 2. 파라미터 값 저장 : pass, chgpass
    * 3. 로그인 정보로 회원정보를 db에서 조회.
    * 4. 비밀번호 검증 : pass 파라미터와 db에 저장된 비밀번호 비교
    *    - 불일치 : 오류 메시지 출력 후 현재 페이지를 passwordForm으로 이동
    * 5. 비밀번호 일치한 경우 db의 비밀번호를 변경된 비밀번호로 변경
    *      MemberDao. updatePassword(id, 변경비밀번호)
    *    - 변경성공 : 성공메세지 출력 후. opener 페이지를 info로 이동. 현재페이지 닫기
    *    - 변경실패 : 오류메세지 출력 후. 현재페이지 닫기
    */
   @RequestMapping("password")
   public String password (HttpServletRequest request,  HttpServletResponse response) {
	   String login = (String)request.getSession().getAttribute("login");
	   if(login == null || login.trim().equals("") ) {
		   request.setAttribute("msg", "로그인 하세요");
		   request.setAttribute("url", "loginForm");
		   return "openeralert";
	   }
	   String pass = request.getParameter("pass");
	   String chgpass = request.getParameter("chgpass");
	   Member mem = dao.selectOne(login);
	   if(!pass.equals(mem.getPass())) {
		   request.setAttribute("msg", "비밀번호가 틀립니다.");
		   request.setAttribute("url", "passwordForm");
		   return "alert";
	   }
	   if(dao.updatePassword(login,chgpass)) {
		   request.setAttribute("msg", "비밀번호가 변경되었습니다.");
		   request.setAttribute("url", "info?id=" + login);
		   return "openeralert";
	   } else {
		   StringBuilder sb = new StringBuilder();
		   sb.append("alert('비밀번호 변경시 오류가 발생했습니다.');\n");
		   sb.append("self.close()\n");
		   request.setAttribute("script", sb.toString());
		   return "dumy";
	   }
   
   }
   /*
   1. request 객체로 이미지 업로드 불가 => cos.jar  
   2. 이미지 업로드 폴더 : 현재폴더/picture 설정 
   3. opener 화면에 이미지 볼수 있도록 결과 전달 => javascript
   4. 현재 화면 close 하기                  => javascript
    */
   @RequestMapping("picture")
   public String picture (HttpServletRequest request,  HttpServletResponse response) {
		//path : 업로드 파일의 위치.
	   //application : request.getServletContext() 
	   String path = request.getServletContext().getRealPath("/") + "picture/";
	   String fname = null;
	   File f = new File(path);
	   if(!f.exists()) f.mkdirs(); //업로드 폴더 생성
	   //업로드 완료. 같은 이름의 이미지가 업로드 시 이름 변경 안됨. 업로드파일의이름 = 원본파일의 이름이 같다. 
	   MultipartRequest multi = null;
	   try {
		   multi = new MultipartRequest(
				   request,   //이미지 파일 정보 저장
				   path,      //업로드 파일의 위치
				   10*1024*1024, //10M. 업로드 파일의 최대 크기
				   "utf-8"    //인코딩 설정
				   );
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
	   fname = multi.getFilesystemName("picture");  //파일의 이름
	   request.setAttribute("fname", fname);
	   return "member/picture";
   }
//==================================================================================
   private String loginAdminCheck(HttpServletRequest request,  HttpServletResponse response) {
	   String id = request.getParameter("id");
	   String login = (String)request.getSession().getAttribute("login");
	   if(login == null) {
		   request.setAttribute("msg", "로그인 하세요");
		   request.setAttribute("url", "loginForm");
		   return "alert";
	   } else if (!login.equals("admin")) {
		   request.setAttribute("msg", "관리자만 거래 가능합니다.");
		   request.setAttribute("url", "main");
		   return "alert";
	   }
	   return null;
   }

   private String loginIdCheck(HttpServletRequest request,  HttpServletResponse response) {
	   String id = request.getParameter("id");
	   String login = (String)request.getSession().getAttribute("login");
	   if(login == null) {
		   request.setAttribute("msg", "로그인 하세요");
		   request.setAttribute("url", "loginForm");
		   return "alert";
	   } else if (!login.equals("admin") && !id.equals(login)) {
		   request.setAttribute("msg", "본인만 거래 가능합니다.");
		   request.setAttribute("url", "main");
		   return "alert";
	   }
	   return null;
   }
}
