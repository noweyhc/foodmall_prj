package com.mall.interceptor;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//로그인처리를 담당하는 인터셉터
@Component
public class LoginSessionListener extends HandlerInterceptorAdapter implements HttpSessionBindingListener{

	private static Hashtable<HttpSession, String> loginUser = new Hashtable<HttpSession,String>();

	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // session 객체를 가져옴
        HttpSession session = request.getSession();
        // login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴
        Object obj = session.getAttribute("login");
        
		/*
		 * if ( obj ==null ){ // 로그인이 안되어 있는 상태임으로 로그인 폼으로 다시 돌려보냄(redirect)
		 * response.sendRedirect("/login/userLogin"); return false; // 더이상 컨트롤러 요청으로 가지
		 * 않도록false로 반환함 }
		 */
        // preHandle의return은 컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임
        // 따라서true로하면 컨트롤러 uri로 가게 됨.
        return true;
    }
 
    // 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }

    //==================================================================================

    /**
	 * 세션 연결할 때 호출(HashTable에 접속자를 저장한다.)
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		loginUser.put(event.getSession(), event.getName());
		System.out.println(event.getName() + " 로그인 완료");
	}

	/**
	 * 세션이 끊겼을 때 호출
	 * 로그아웃 버튼을 눌르지 않았을 때 컴퓨터종료,세션만료등에 이유로 로그아웃 된다.
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		loginUser.remove(event.getSession());
	}
    
    /**
     * 로그아웃 버튼 눌렀을 때, 해쉬테이블 순차적으로 배치시킴, while 키 값이 존재하는동안,session에 키 값 받아옴
     * 입력받은 아이디를 해시테이블에서 삭제
     * @param userId
     */
    public void removeSession(String userId) {
        Enumeration<HttpSession> e = loginUser.keys();
        HttpSession session = null;
        while(e.hasMoreElements()){
            session = (HttpSession)e.nextElement();
            if(loginUser.get(session).equals(userId)){
                session.invalidate();
            }//end if
       }//end while
    }// removeSession
    
    /**
     * 입력받은 session Object로 아이디를 리턴한다.
     * @param session => 접속한 사용자의 session Object
     * @return String => 접속자의 아이디
     */
    public String getUserID(HttpSession session) {
    	return (String)loginUser.get(session);
    }//getUserID

    public void setSession(HttpSession session, String id) {
    	//로그인에 this 즉 자기 자신을 집어 넣는다.
    	session.setAttribute(id, this);
    }
}