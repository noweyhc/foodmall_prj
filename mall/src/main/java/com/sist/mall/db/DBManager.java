package com.sist.mall.db;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.mall.product.vo.ProductVo;


public class DBManager {

	private static SqlSessionFactory factory;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/sist/mall/db/dbConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int registerProduct(ProductVo pv) {
		SqlSession session = factory.openSession();
		int re = session.update("product.register", pv);
		session.commit();
		session.close();
		
		return re;
	}
}
