package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AppointExecution;
import dto.Result;
import entity.Book;
import excepetion.NoNumberException;
import excepetion.RepeatAppointException;
import service.BookService;
import state.AppointStateEnum;

@Controller
@RequestMapping("/book")
public class BookController {
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BookService bookService;
	@RequestMapping(value="/list" ,method=RequestMethod.GET)
	private String list(Model model) {
		List<Book> list=bookService.getList();
		model.addAttribute("list",list);
		return "list";
	}
	
	@RequestMapping(value="/{bookid}/detail",method=RequestMethod.GET)
	private String detail(@PathVariable ("bookid") Long  bookid,Model model) {
		if(bookid == null) {
			return "redirect:/book/list";
		}
		Book   book=bookService.getById(bookid);
		if(book ==null) {
			return "forward:/book/list";
		}
		model.addAttribute("book",book);
		return "detail";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/{bookid}/appoint",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	private Result<AppointExecution> appoint(@PathVariable ("bookid") Long bookid,@RequestParam("studentId") Long studentId){
		if(studentId==null || studentId.equals("")) {
			return new Result<AppointExecution>(false,"学号不能为空");
		}
		AppointExecution appointExecution=null;
		try {
			appointExecution=bookService.appoint(bookid, studentId);
		}catch(NoNumberException e ) {
			appointExecution=new AppointExecution(bookid, AppointStateEnum.NO_NUMBER);
		}catch(RepeatAppointException ex) {
			appointExecution = new AppointExecution(bookid,AppointStateEnum.RE_PEAT);
		}catch(Exception e) {
			appointExecution=new AppointExecution(bookid,AppointStateEnum.INNER_ERROR);
		}
		return new Result<AppointExecution> (true, appointExecution);
	}
	
}
