package com.mall.dao.set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.set.SetVo;

@Repository
public class SetDao {
	
	private SqlSession sqlSession;
	
	public SetDao() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	//세트 상품을 등록하고 성공 여부를 반환합니다
	public int registerSet(SetVo sv) {
		int re = sqlSession.update("sets.register", sv);
		if(re == 1) {
			sqlSession.commit();
		}
		return re;
	}
	
	//제일 최근 등록된 세트 상품의 번호를 반환합니다
	public int getNextNo() {
		int re = 0;
		try {
			re = sqlSession.selectOne("sets.getNextNo");
		} catch(Exception e) {
			//세트 상품 데이터 개수가 0일 때 예외 발생
		}
		return re + 1;
	}
	
}
