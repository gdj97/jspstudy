package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Book;

public interface BookMapper {

	@Insert("insert into book (writer,title,content,regdate) values(#{writer},#{title},#{content},now())")
	int insert(Book book);

	@Select("select * from book where no=#{value}")
	Book selectOne(int no);

	@Update("update book set writer=#{writer},title=#{title},content=#{content} where no=#{no}")
	int update(Book book);
	
	@Delete("delete from book where no=#{value}")
	int delete(int no);

	@Select("select * from book")
	List<Book> selectList();
}
