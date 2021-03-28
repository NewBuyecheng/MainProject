package cn.zcbigdata.mybits_demo.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckLogin {

    public static int checkLoginFlag(HttpServletRequest request ){

        HttpSession session = request.getSession();
        if(session.getAttribute("flag") != null){
            return (Integer)session.getAttribute("flag");
        }
        return 9;
    }
    public static boolean checkLogin(HttpServletRequest request ){
        HttpSession session = request.getSession();
        if(session.getAttribute("flag") != null){
            return true;
        }
        System.out.println("未登录");
        return false;
    }
}
