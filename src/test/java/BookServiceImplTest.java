import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dto.AppointExecution;
import service.BookService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })

public class BookServiceImplTest {
	
	@Autowired
	private BookService bookService;

	@Test
	public void testAppoint() {
		AppointExecution appointExecution= bookService.appoint(1001,100000);
		System.out.println(appointExecution.toString());
	}
}
