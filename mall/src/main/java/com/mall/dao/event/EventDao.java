package com.mall.dao.event;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.event.EventVo;

@Repository
public class EventDao {

private SqlSession sqlSession;
	
	public EventDao() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}

	//이벤트 글을 등록하고 성공 여부를 반환합니다
	public int registerEvent(EventVo ev) {
		int re = sqlSession.update("event.register", ev);
		if(re == 1) {
			sqlSession.commit();
		}
		return re;
	}
	
	//제일 최근 등록된 이벤트 글의 번호를 반환합니다
	public int getNextNo() {
		int re = 0;
		try {
			re = sqlSession.selectOne("event.getNextNo");
		} catch(Exception e) {
			//이벤트 데이터 개수가 0일 때 예외 발생
		}
		return re + 1;
	}
}
