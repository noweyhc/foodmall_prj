package com.sist.mall.product.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sist.mall.db.SqlSessionFactoryBean;
import com.sist.mall.product.vo.ProductVo;

@Repository
public class ProductDao {
	
	private SqlSession sqlSession;
	
	public ProductDao() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	public int registerProduct(ProductVo pv) {
		int re = sqlSession.update("product.register", pv);
		if (re == 1) {
			sqlSession.commit();
		}
		return re;
	}
	
	public void commit() {
    	sqlSession.commit();
    }


}
