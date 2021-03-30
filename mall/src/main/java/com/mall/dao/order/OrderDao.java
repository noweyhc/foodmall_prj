package com.mall.dao.order;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.Order.OrderProdListVo;
import com.mall.vo.Order.OrderShippingVo;

@Repository
public class OrderDao {
	
	private SqlSession sqlSession;
	
	public OrderDao() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}//InquiryDao

	public List<OrderProdListVo> findCartList(String mem_id) {
		
		List<OrderProdListVo> cVoList = sqlSession.selectList("order.findCartList",mem_id);
		
		return cVoList;
	}// findCartList

	// 커밋
	public void commit(){
		sqlSession.commit();
	}

	public int getTotAmount(String mem_id) {
		
		int totAmount = sqlSession.selectOne("cart.sumPrice",mem_id);
		
		return totAmount;
	}

	public OrderShippingVo selectMemShip(String mem_id) {
		
		OrderShippingVo ospVo = sqlSession.selectOne("order.selectMemShip",mem_id);
		
		return ospVo;
	}

	public int selectQty(String mem_id) {
		
		int totQty = sqlSession.selectOne("order.selectQty",mem_id);
		
		return totQty;
	}
	
}//OrderDao
