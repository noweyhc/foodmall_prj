package com.mall.dao.review;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.reivew.ReviewVO;

@Repository
public class ReviewDao {
	
	private SqlSession session;
	
	public ReviewDao() {
		this.session = SqlSessionFactoryBean.getSqlSession();
	}//ReviewDao
	
	public int save(ReviewVO reviewVO) {
		int result =  session.insert("reviewMapper.save", reviewVO);
		if(result == 1) {
			session.commit();
		}//end if
		
		return result;
	}//end save
	
	

}
