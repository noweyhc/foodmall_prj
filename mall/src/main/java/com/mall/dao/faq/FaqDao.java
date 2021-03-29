package com.mall.dao.faq;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.faq.FaqVo;

@Repository
public class FaqDao {

	private SqlSession sqlSession;

	public FaqDao() {
		this.sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	// 글 목록
	public List<FaqVo> listFAQ(int start, int end) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		List<FaqVo> list = sqlSession.selectList("FAQ.findAll", map);
		return list;
	}

	// 글 상세
	public FaqVo detailFAQ(int no) {
		FaqVo f = sqlSession.selectOne("FAQ.detail", no);
		
		return f;
	}

	// 페이징을 위한 총 게시글 수 조회
	public int totBoard() {
		int total = sqlSession.selectOne("FAQ.totBoard");
		return total;
	}

	// 글 작성
	public int insertFAQ(FaqVo f) {
		int re = sqlSession.insert("FAQ.insert", f);
		sqlSession.commit();
		return re;
	}

	// 글 수정
	public int updateFAQ(FaqVo f) {
		int re = sqlSession.update("FAQ.update", f);
		sqlSession.commit();
		return re;
	}

	// 글 삭제
	public int deleteFAQ(int no) {
		int re = sqlSession.delete("FAQ.delete", no);
		sqlSession.commit();
		return re;
	}
	
	//조회수 증가
	public void updateHit(int no) {
		sqlSession.update("FAQ.updateHit",no);
		sqlSession.commit();
	}
	

	/*
	 * public void insertLog(Sist_log log) { sqlSession.insert("sist_log.insert",
	 * log); }
	 * 
	 * public int updateBoard(FAQVo b) { int re= sqlSession.update("board.update",
	 * b); sqlSession.commit(); return re; }
	 * 
	 * 
	 */

}
