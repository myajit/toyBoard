package studyBoard.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	//Singleton 패턴
	//해당 클래스의 인스턴스가 하나만 생성되어야 할 때 사용하는 디자인패턴
	private static JDBCTemplate instance;
	
	//생성자를 private으로 처리해 외부에서 JDBCTemplate을 생성하는 것을 차단.
	private JDBCTemplate() {
		try {
			//1. oracle jdbc Driver를 JVM에 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//외부에서 JDBCTemplate의 인스턴스를 생성하지 않고도 사용할 수 있는
	//JDBCTemplate의 인스턴스를 반환받는 용도의 메서드
	public static JDBCTemplate getInstance() {
		//instance 변수가 초기화 된적이 없다면 
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		
		return instance;
	}
	
	//2. 데이터베이스와 연결 수립
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "BOARDTEST";
		String password = "1234";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			//트랜잭션관리를 개발자가 하기 위해 Auto Commit 설정 끄기
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//3. commit / rollback
	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//4. 시스템 자원(Connection, Statement, ResultSet) 반환
	public void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(Statement stmt, Connection conn) {
		close(stmt);
		close(conn);
	}
	
	public void close(ResultSet rset, Statement stmt) {
		close(rset);
		close(stmt);
	}
	
	public void close(ResultSet rset, Statement stmt, Connection conn) {
		close(rset);
		close(stmt);
		close(conn);
	}
	
	
	
	
	
	
	
	
	
	
	
	


}
