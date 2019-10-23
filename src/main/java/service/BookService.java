package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dto.AppointExecution;
import entity.Book;


public interface BookService {
	Book getById(long bookid);
	
	List<Book> getList();
	
	AppointExecution appoint(long bookId,long studentId);
}
