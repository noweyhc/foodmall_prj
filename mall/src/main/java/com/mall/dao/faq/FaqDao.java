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

	public List<FaqVo> listBoard(int start, int end) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		List<FaqVo> list = sqlSession.selectList("FAQ.findAll", map);
		return list;
	}

	public FaqVo getBoard(int no) {
		FaqVo f = sqlSession.selectOne("FAQ.detail", no);
		return f;
	}

	public int totBoard() {

		int total = sqlSession.selectOne("FAQ.totBoard");

		return total;
	}

	//글 작성
	public int insertBoard(FaqVo f) {
		int re = sqlSession.insert("FAQ.insert",f);
		sqlSession.commit();
		return re;
	}
	

	/*
	 * public void insertLog(Sist_log log) { sqlSession.insert("sist_log.insert",
	 * log); }
	 */
/*
 * 
 *
 * public int updateBoard(FAQVo b) { int re= sqlSession.update("board.update",
 * b); sqlSession.commit(); return re; }
 * 
 * public int deleteBoard(int no, String pwd) { HashMap<String,Object> map = new
 * HashMap<String,Object>(); map.put("no", no); map.put("pwd",pwd); int re =
 * sqlSession.delete("board.delete", map); sqlSession.commit(); return re; }
*/
	
}
