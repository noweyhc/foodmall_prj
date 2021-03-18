package com.mall.dao.product;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.product.ProductVo;

@Repository
public class ProductDao {
	
	private SqlSession sqlSession;
	
	public ProductDao() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	public int registerProduct(ProductVo pv) {
		System.out.println(pv);
		int re = sqlSession.update("products.register", pv);
		if (re == 1) {
			sqlSession.commit();
		}
		return re;
	}
	
	public ProductVo selectProducts(int no) {
		ProductVo p = sqlSession.selectOne("products.selectProducts", no);
		return p;
	}
	
	public List<ProductVo> findAll(){
		List<ProductVo> list = null;
		list = sqlSession.selectList("products.findAll");
		return list;
	}
	
	public void commit() {
    	sqlSession.commit();
    }


}
