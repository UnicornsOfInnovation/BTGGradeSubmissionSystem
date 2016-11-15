/*
 *  Main Developer: Christian Dave B. Baclayon
 *  Development History:
 *     November 12, 2016 - created the LoginController file (Dave)
 */


package btg.controller;


import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;
import btg.session.SessionManager;

public class LoginController extends Controller{
    @Override
    protected Navigation run() throws Exception {
        System.out.println(this.getClass().getSimpleName()+" START");
        
       try{
           String logoutString = request.getParameter("logout");
           if(null != logoutString && logoutString.equals("logout")){
               SessionManager.destroySession(request);
               System.out.println("Logging out");
               System.out.println("1");
               return forward("/html/Login.html");
           }else if(SessionManager.isSessionExist(request)==true
                   && (request.getSession().getAttribute("accntType").equals("admin")
                           || request.getSession().getAttribute("accntType").equals("company"))){
               JSONObject json  = new JSONObject();
               //TODO: Add code to check if admin or company
               json.put("pageToRedirect", request.getSession().getAttribute("accntType"));
               this.response.setContentType("application/json");
               this.response.getWriter().write(json.toString());
               System.out.println("2");
               return null;
           }
       }
       catch(Exception e){
           System.out.println("no log out");
       }
        
//        CompanyService companyService = new CompanyService();
//        CompanyDto companyDto = new CompanyDto(); 
        boolean isValid = false;
        
        JSONObject jsonObject = null;
        
        try{
            jsonObject = new JSONObject(this.request.getReader().readLine());
            
            if(jsonObject.isNull("username")){
                //if there is no user  name then return
                System.out.println("Return because no username set.");
                System.out.println("3");
                return null;
            }
            
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            
            System.out.println(username + " " + password);
            
//            companyDto.setEmailAddress(username);
//            companyDto.setPassword(password);
            
            System.out.println(username);
            System.out.println(password);
            
//            isValid = companyService.validateUsernamePassword(companyDto);
            
            System.out.println(this.getClass().getSimpleName()+" END");
            if("admin".equals(username) && "admin".equals(password)){
                
                // Create a session object if it is already not created.
                
                SessionManager.createSession(request, username, "admin");
                
                JSONObject json  = new JSONObject();
                json.put("pageToRedirect", "admin");
                this.response.setContentType("application/json");
                this.response.getWriter().write(json.toString());
                
            }else if (true == isValid){
                SessionManager.createSession(request, username, "company");
                
                JSONObject json  = new JSONObject();
                json.put("pageToRedirect", "company");
                this.response.setContentType("application/json");
                this.response.getWriter().write(json.toString());
            }else{
                System.out.println("Go back");
                JSONObject json  = new JSONObject();
                json.put("pageToRedirect", "");
                json.put("errorMsg", "Wrong username and password.");
                this.response.setContentType("application/json");
                this.response.getWriter().write(json.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
        
        System.out.println("2");
        return null;
        
    }
}
