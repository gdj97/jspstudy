package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;

@WebServlet(urlPatterns= {"/ajax/*"},
		initParams= {@WebInitParam(name="view",value="/view/")})
public class AjaxController extends MskimRequestMapping {
	@RequestMapping("select")
	public String select(HttpServletRequest request, HttpServletResponse response) {
		BufferedReader fr = null;
		String path =request.getServletContext().getRealPath("/")+ "file/sido.txt";
		try {
			fr = new BufferedReader(new FileReader(path));
		}catch (IOException e) {
			e.printStackTrace();
		}
		//LinkedHashSet : 중복불가. 순서유지 
		Set<String> set = new LinkedHashSet<>(); //{서울특별시,부산광역시,....}
		String data = null;
		String si = request.getParameter("si");
		String gu = request.getParameter("gu");
		if(si == null && gu==null) { //시도 목록 조회. 초기값
			try {
				//data : 부산광역시	중구	중구
			  while((data=fr.readLine()) != null) {
				  String[] arr = data.split("\\s+"); // \\s+ : 정규식. \s(공백) +:한개이상
				  //arr : [부산광역시,중구,중구]
				  if(arr.length >=3) set.add(arr[0].trim());
			  }
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else if(gu == null) {
			si = si.trim();
			try {
				while((data = fr.readLine()) != null) {
					String[] arr = data.split("\\s+");
					if(arr.length >= 3 && arr[0].equals(si) && !arr[1].contains(arr[0])) {
						set.add(arr[1].trim());
					}
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("list", new ArrayList<String>(set));
		request.setAttribute("len", set.size());
		return "ajax/select";
	}
}
