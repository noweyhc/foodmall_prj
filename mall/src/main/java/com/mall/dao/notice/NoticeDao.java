package com.mall.dao.notice;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.notice.NoticeVo;

@Repository
public class NoticeDao {

	private SqlSession sqlSession;

	public NoticeDao() {
		this.sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	// 글 목록
	public List<NoticeVo> listNotice(int start, int end) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		List<NoticeVo> list = sqlSession.selectList("notice.findAll", map);
		return list;
	}

	// 글 상세
	public NoticeVo detailNotice(int no) {
		NoticeVo n = sqlSession.selectOne("notice.detail", no);
		return n;
	}

	public int totBoard() {
		int total = sqlSession.selectOne("notice.totBoard");
		return total;
	}

	// 글 작성
	public int insertNotice(NoticeVo n) {
		int re = sqlSession.insert("notice.insert", n);
		sqlSession.commit();
		return re;
	}

	// 글 수정
	public int updateNotice(NoticeVo n) {
		int re = sqlSession.update("notice.update", n);
		sqlSession.commit();
		return re;
	}

	// 글 삭제
	public int deleteNotice(int no) {
		int re = sqlSession.delete("notice.delete", no);
		sqlSession.commit();
		return re;
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
