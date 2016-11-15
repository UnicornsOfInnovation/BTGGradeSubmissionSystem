/*
 *  Main Developer: Christian Dave B. Baclayon
 *  Development History:
 *     November 12, 2016 - created the SessionManager file (Dave)
 */

package btg.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SessionManager {
    
   
    public static void createSession(HttpServletRequest request, String username, String accntType){
        HttpSession session = request.getSession();
        if(session.isNew()){
            System.out.println("New Session");
            session.setAttribute("username", username);
            session.setAttribute("accntType", accntType);
        }
        else{
            //do nothing, already created
        }
    }
    
    
    public static Boolean isSessionExist(HttpServletRequest request){
       Boolean ret = true;
       HttpSession session = request.getSession(false);
       if(session == null){
           ret = false;
       }
       return ret;
    }
    
    
    public static void destroySession(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(null != session){
            System.out.println("Session Destroyed");
            session.invalidate();
        }
    }
    
}
