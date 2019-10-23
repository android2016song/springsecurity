import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.ShellRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.AppointmentDao;
import dao.BookDao;
import entity.Book;

/**
 * ����spring��junit���ϣ�junit����ʱ����springIOC���� spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ����junit spring�����ļ�
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class BaseTest {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private AppointmentDao appointDao;
	
	@Test
	public void querid() {
		long bookId =1000;
		Book book=bookDao.queryById(bookId);
		System.out.println("--------------"+book.toString());
	}
	
	
	// MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";

	@Test
	public void testMysql() {
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://127.0.0.1:3306/testmysql?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	    // 数据库的用户名与密码，需要根据自己的设置
	    final String USER = "root";
	     final String PASS = "mysql";

	     Connection conn = null;
	        Statement stmt = null;
	   try {
		   // 注册 JDBC 驱动
	         Class.forName(JDBC_DRIVER);
	     
	         // 打开链接
	         System.out.println("连接数据库...");
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);
	     
	         // 执行查询
	         System.out.println(" 实例化Statement对象...");
	         stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT book_id, name, number FROM book";
	         ResultSet rs = stmt.executeQuery(sql);
	      // 展开结果集数据库
	            while(rs.next()){
	                // 通过字段检索
	                int id  = rs.getInt("book_id");
	                String name = rs.getString("name");
	                String url = rs.getString("number");
	    
	                // 输出数据
	                System.out.print("ID: " + id);
	                System.out.print(", 站点名称: " + name);
	                System.out.print(", 站点 URL: " + url);
	                System.out.print("\n");
	            }
	            // 完成后关闭
	            rs.close();
	            stmt.close();
	            conn.close();

	   }catch(Exception e) {
		  e.printStackTrace();
	   }finally {
		// 关闭资源
           try{
               if(stmt!=null) stmt.close();
           }catch(Exception se2){
        	   se2.printStackTrace();
           }// 什么都不做
           try{
               if(conn!=null) conn.close();
           }catch(Exception se){
               se.printStackTrace();
           }

	   }
	     
	 
	}
	
	@Test
	public void testSql() {
		Book b=bookDao.queryById(1001);
		System.out.println(b.getName());
	}
	@Test
	public void testSql1() {
		appointDao.insertAppointment(1001,23);
	}
	@Test
	public void queryAppoint() {
		appointDao.queryByKeyWithBook(1001,23);
	}
	
	@Test
	public void generator() {
		
		//自动生成mapper文件
	  String[] 	args = new String[] { "-configfile", "src\\main\\resources\\generatorConfig.xml", "-overwrite" };
		
        ShellRunner.main(args);
	}
}
 
 
