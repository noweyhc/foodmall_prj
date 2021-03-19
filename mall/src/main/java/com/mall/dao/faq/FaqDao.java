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
	
/*
	public void insertLog(Sist_log log) {
		sqlSession.insert("sist_log.insert", log);
	}
	
	public void updateStep(int b_ref, int b_step) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("b_ref", b_ref);
		map.put("b_step", b_step);
		sqlSession.update("board.updateStep", map);
		sqlSession.commit();
	}
	
	public int getNextNo() {
		int no =sqlSession.selectOne("board.nextNo");
		return no;
	}
	*/
	
	public int totBoard() {
		
		int total = sqlSession.selectOne("board.totBoard");

		return total;
	}
	/*
	
	public int updateBoard(FAQVo b) {
		int re= sqlSession.update("board.update", b);
		sqlSession.commit();
		return re;
	}
	
	public int deleteBoard(int no, String pwd) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("no", no);
		map.put("pwd",pwd);
		int re = sqlSession.delete("board.delete", map);
		sqlSession.commit();
		return re;
	}
	
	
	public int insertBoard(FAQVo b) {
		System.out.println("---------manager-----------");
		System.out.println("b_ref:"+b.getB_ref());
		System.out.println("b_step:"+b.getB_step());
		System.out.println("b_level:"+b.getB_level());
		System.out.println("------------------------");
		int re = sqlSession.insert("board.insert",b);
		sqlSession.commit();
		return re;
	}
	*/
	
	public List<FaqVo> listBoard(int start, int end){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		List<FaqVo> list= sqlSession.selectList("board.findAll",map);
		return list;
	}


	public FaqVo getBoard(int no) {
		FaqVo b = sqlSession.selectOne("board.getBoard",no);
		return b;
	}
}










