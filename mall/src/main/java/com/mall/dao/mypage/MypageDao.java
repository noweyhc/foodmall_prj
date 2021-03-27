package com.mall.dao.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.mypage.MyInqDetailVo;
import com.mall.vo.mypage.MyInqListVo;
import com.mall.vo.mypage.MypageVo;
import com.mall.vo.mypage.ShippingVo;

@Repository
public class MypageDao {

	private SqlSession sqlSession;
	
	public MypageDao() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	public MypageVo getMemberinfo(String admin) {
		
		MypageVo mVo = sqlSession.selectOne("mypage.getUserinfo",admin);

		return mVo;
	}

	public int updateEmail(String email, String admin) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("mem_email", email);
		map.put("mem_id", admin);
		
		int re = sqlSession.update("mypage.updateEmail",map);
		
		if(re == 1) {
			commit();
			System.out.println(re);
		}
		return re;
	}

	public int updatePhone(String phone, String admin) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("mem_phone", phone);
		map.put("mem_id", admin);
		
		int re = sqlSession.update("mypage.updatePhone",map);

		if(re == 1) {
			commit();
		}

		return re;
	}

	public MypageVo getMemberid(String mem_id) {
		
		MypageVo mVo = sqlSession.selectOne("mypage.getMemberId",mem_id);
		
		return mVo;
	}

	public String getPwd(String currPassword, String mem_id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("mem_id", mem_id);
		map.put("mem_pwd", currPassword);
		
		String pwd = sqlSession.selectOne("mypage.getPwd",map);
		
		return pwd;
	}

	public int updatePwd(String newPassword) {
		
		String admin = "leewooo";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("mem_id", admin);
		map.put("mem_pwd", newPassword);
		
		System.out.println("bb"+map.get("mem_pwd")+"bb");
		
		int re = sqlSession.update("mypage.updatePwd",map);
		
		commit();
		
		System.out.println(re);
		
		return re;
	}
	
	public void commit() {
    	sqlSession.commit();
    }

	public int deleteId(String mem_id) {
		
		System.out.println(mem_id);
		int re = sqlSession.delete("mypage.deleteId",mem_id);
		
		commit();
		
		return re;
	}

	public List<MyInqListVo> findMyInq(String mem_id) {
		
		List<MyInqListVo> list = sqlSession.selectList("mypage.findMyInq",mem_id);
		
		return list;
	}

	public MyInqDetailVo findDetailInq(int cs_no) {
		
		MyInqDetailVo detailVo = sqlSession.selectOne("mypage.detailInq",cs_no);
		
		return detailVo;
	}

	public ShippingVo getShipping(String mem_id) {
		
		ShippingVo spVo = sqlSession.selectOne("mypage.getShipping",mem_id);
		
		return spVo;
	}
}
