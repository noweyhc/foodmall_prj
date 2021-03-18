package com.mall.dao.user;

import java.util.HashMap;

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
    
	public String getLogin(String id, String password) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("mem_id", id);
		map.put("mem_pwd", password);

		String validPwd = sqlSession.selectOne("userMapper.findpwd", map);
		
		return validPwd;
	}
    
    public void commit() {
    	sqlSession.commit();
    }

}
