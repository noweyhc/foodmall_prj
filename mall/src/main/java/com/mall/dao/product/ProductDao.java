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
	
	//상품번호 no 제품을 상품정보 테이블에서 삭제하고 성공 여부를 반환합니다
	public int deleteProduct(int no) {
		int re = sqlSession.delete("products.delete", no);
		if(re == 1) {
			sqlSession.commit();
		}
		return re;
	}
	
	//상품번호 no로 한 상품의 전체 데이터를 가져옵니다
	public ProductVo selectOne(int no) {
		ProductVo pv = sqlSession.selectOne("products.select", no);
		return pv;
	}
	
	//상품번호 no 제품의 정보를 변경하고 성공 여부를 반환합니다
	public int updateProduct(ProductVo pv) {
		int re = sqlSession.update("products.update", pv);
		if(re == 1) {
			sqlSession.commit();
		}
		return re;
	}
	
	public void commit() {
    	sqlSession.commit();
    }


}
