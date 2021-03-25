package com.mall.dao.sale;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.product.ProductVo;
import com.mall.vo.sale.SaleVo;

@Repository
public class SaleDao {

	private SqlSession sqlSession;
	
	public SaleDao() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	//timesale 테이블에 데이터를 입력하고 성공 여부를 반환합니다
	public int registerSale(SaleVo sv) {
		int re = sqlSession.update("sale.register", sv);
		if (re == 1) {
			sqlSession.commit();
		}
		return re;
	}
	
	public void commit() {
    	sqlSession.commit();
    }

}
