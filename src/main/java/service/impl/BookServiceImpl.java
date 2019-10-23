package service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AppointmentDao;
import dao.BookDao;
import dto.AppointExecution;
import entity.Appointment;
import entity.Book;
import excepetion.AppointException;
import excepetion.NoNumberException;
import excepetion.RepeatAppointException;
import service.BookService;
import state.AppointStateEnum;

@Service
public class BookServiceImpl implements BookService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BookDao bookDao;
	@Autowired
	private AppointmentDao appointDao;
	
	public Book getById(long bookid) {
		 
		return bookDao.queryById(bookid);
	}

	public List<Book> getList() {
		 
		return bookDao.queryAll(0, 1000);
	}
 
	/**
	 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	@Transactional
	public AppointExecution appoint(long bookId, long studentId) {
		try {
			int number=bookDao.reduceNumber(bookId);
			if(number<0) {
				throw new NoNumberException("库存不足");
			}else {
				int inser=appointDao.insertAppointment(bookId, studentId);
				if(inser<=0) {
					throw new RepeatAppointException("重复预约");
				}else {
					Appointment appointment =appointDao.queryByKeyWithBook(bookId, studentId);
					return new AppointExecution(bookId, AppointStateEnum.SUCESS,appointment);
				}
			}
		}catch(NoNumberException e) {
			throw e;
			
		}catch(RepeatAppointException e) {
			throw e;
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			throw new AppointException("appoint inner error:" +e.getMessage());
		}
		 
	}

}
