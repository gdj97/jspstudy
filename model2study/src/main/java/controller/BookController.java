package controller;

import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.Book;
import model.BookDao;

@WebServlet(urlPatterns= {"/book/*"},
initParams= {@WebInitParam(name="view",value="/view/")})
public class BookController extends MskimRequestMapping{
	BookDao dao = new BookDao();
	//방명록 등록
	@RequestMapping("write")
	public String write (HttpServletRequest request, HttpServletResponse response) {
		Book book = new Book();
	    
	    book.setWriter(request.getParameter("writer"));
	    book.setTitle(request.getParameter("title"));
	    book.setContent(request.getParameter("content"));
	    BookDao dao = new BookDao();
	    if(dao.insert(book)) {
	    	return "redirect:list";
	    } else {
	    	request.setAttribute("msg", "방명록 등록시 오류가 발생했습니다.");
	    	request.setAttribute("url", "writeform");
	    	return "alert";
	    }	    
	}
	//방명록 조회
	@RequestMapping("info")
	public String info (HttpServletRequest request, HttpServletResponse response) {
		
		BookDao dao = new BookDao();
		int no = Integer.parseInt(request.getParameter("no"));
		Book book = dao.selectOne(no);
		if(book == null) {
			request.setAttribute("script", "alert('해당 글이 없습니다.'); history.back();");
			return "dumy";
		}
		request.setAttribute("book", book);
		return "book/info";
	}
	//방명록 삭제
	@RequestMapping("delete")
	public String delete (HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		new BookDao().delete(no);
		return "redirect:list";
	}
	//방명록 수정
	@RequestMapping("updateform")
	public String updateform (HttpServletRequest request, HttpServletResponse response) {
		
		BookDao dao = new BookDao();
		int no = Integer.parseInt(request.getParameter("no"));
		Book book = dao.selectOne(no);
		if(book == null) {
			request.setAttribute("script", "alert('해당 글이 없습니다.'); history.back();");
			return "dumy";
		}
		request.setAttribute("b", book);
		return "book/updateform";
	}
	
	@RequestMapping("update")
	public String update (HttpServletRequest request, HttpServletResponse response) {
		Book b = new Book();
		b.setNo(Integer.parseInt(request.getParameter("no")));
		b.setWriter(request.getParameter("writer"));
		b.setTitle(request.getParameter("title"));
		b.setContent(request.getParameter("content"));
		BookDao dao = new BookDao();
		dao.update(b);
		return "redirect:info?no="+b.getNo();
	}
	
	//방명록 목록
	@RequestMapping("list")
	public String list (HttpServletRequest request, HttpServletResponse response) {
		List<Book> list = new BookDao().list();
		request.setAttribute("list",list);
		return "book/list";

	}
	
}
