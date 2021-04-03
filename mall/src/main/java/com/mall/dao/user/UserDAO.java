package com.mall.dao.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mall.db.SqlSessionFactoryBean;
import com.mall.vo.user.UserVO;


@Repository
public class UserDAO {
	
	private SqlSession sqlSession;
	
	public UserDAO() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	public int idCheck(String mem_id) {
		return sqlSession.selectOne("userMapper.idCheck", mem_id);
	}
	
	public int emailCheck(String mem_email) {
		return sqlSession.selectOne("userMapper.emailCheck", mem_email);
	}
	
	//회원가입
    public int memberSave(UserVO userVO){
        int result = sqlSession.insert("userMapper.memberSave", userVO);
        if(result == 1) {
        	commit();
        }//end if
        return result;
    }
    
	public int validPwd(String id, String password) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("mem_id", id);
		map.put("mem_pwd", password);
		
		System.out.println(map);

		int validPwd = sqlSession.selectOne("userMapper.findpwd", map);
		
		return validPwd;
	}

	public int validId(String id) {
		
		int validId = sqlSession.selectOne("userMapper.findId",id);
		
		return validId;
	}

	public int phoneAuth(HashMap<String, Object> map) {
		
		Map<String, Object> phoneAuthMap = new HashMap<>();
		
		phoneAuthMap.put("mem_name", map.get("mem_name")); 
		phoneAuthMap.put("mem_phone", map.get("mem_phone"));
		
		int cnt = sqlSession.selectOne("mypage.phoneAuth",phoneAuthMap);
		
		
		return cnt;
	}

	public String phoneAuthGetId(HashMap<String, Object> map) {
		
		Map<String, Object> phoneAuthMap = new HashMap<>();
		
		phoneAuthMap.put("mem_name", map.get("mem_name")); 
		phoneAuthMap.put("mem_phone", map.get("mem_phone"));
		

		String mem_id =sqlSession.selectOne("mypage.phoneAuthGetId",phoneAuthMap);
				
		return mem_id;
	}
	
	public int emailAuth(HashMap<String, Object> map) {
		
		Map<String, Object> emailAuthMap = new HashMap<>();
		
		emailAuthMap.put("mem_name", map.get("mem_name")); 
		emailAuthMap.put("mem_email", map.get("mem_email"));
		
		int cnt = sqlSession.selectOne("mypage.emailAuth",emailAuthMap);
		
		
		return cnt;
	}


	public String EmailAuthGetId(HashMap<String, Object> map) {
		
		Map<String, Object> emailAuthMap = new HashMap<>();
		
		emailAuthMap.put("mem_name", map.get("mem_name")); 
		emailAuthMap.put("mem_email", map.get("mem_email"));
		
		String mem_id = sqlSession.selectOne("mypage.emailAuthGetId",emailAuthMap);
		
		
		return mem_id;
	}
	
	public void commit() {
		sqlSession.commit();
	}


}
