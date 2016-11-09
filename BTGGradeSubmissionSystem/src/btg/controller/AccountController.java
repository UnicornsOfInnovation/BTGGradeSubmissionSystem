/*
 * Main Developer: Christian Dave B. Baclayon
 *  Development History:
 *     October 18, 2016 - created the StudentController file (Dave)
 *                      - created the initial run(), insertAccountController(), updateAccountController(),
 *                        deleteAccountController(), and getAccountByIdController() (Dave)
 *     October 19, 2016 - changePasswordController() (Dave)
 *     October 24, 2016 - getStudentsByStrandController() (Dave)
 *     October 25, 2016 - updated the error messages of all catch statements.
 *                      - getAllTeacherAccounts() (Dave)
 *     November 9, 2016 - review the Coding Standards of this file (Russell)                   
 *     
 */

package btg.controller;


import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONException;
import org.slim3.repackaged.org.json.JSONObject;
import org.slim3.util.RequestMap;
import btg.common.GlobalConstants;
import btg.dto.AccountDto;
import btg.service.AccountService;


public class AccountController extends Controller{
        
        
        AccountService accountService = new AccountService();   //used to access methods in AccountService or database
        
        
        
        
        
        /* 
         * Purpose: This function is called upon the $http.post() command called by the front-end. 
         *          Function calls happen depending on the "action" attribute received by this Controller
         *          from the front-end. Different functions are then called depending on the action attribute.
         * @param: none
         * @return: Navigation
         */
        @Override
        protected Navigation run() throws Exception {
            System.out.println("AccountController.run():" + "start");
            
            String action;
            JSONObject jsonObject;              //holds the data passed from the front-end
            AccountDto accountDto;              //holds the account passed from the back-end
            List<AccountDto> accountDtoList;    //holds the list of accounts passed from the back-end
            
            jsonObject = null;
            accountDto = new AccountDto();
            accountDtoList = null;
            
            try{
                jsonObject = new JSONObject(new RequestMap(this.request));
                action = jsonObject.getString("action");
                if(action.equals("InsertAccount")){
                    accountDto = insertAccountController(jsonObject);                    
                } else if(action.equals("UpdateAccount")){
                    accountDto = updateAccountController(jsonObject);
                } else if(action.equals("DeleteAccount")){
                    accountDto = deleteAccountController(jsonObject);
                } else if(action.equals("GetAccountById")){
                    accountDto = getAccountByIdController(jsonObject);
                    jsonObject.put("account", accountDto);
                } else if(action.equals("GetAllStudentAccounts")){
                    accountDtoList = getAllStudentAccountsController();
                    jsonObject.put("studentAccounts", accountDtoList);
                } else if(action.equals("ChangePassword")){
                    accountDto = changePasswordController(jsonObject);
                } else if(action.equals("GetStudentsByStrand")){
                    accountDtoList = getStudentsByStrandController(jsonObject);
                    jsonObject.put("studentAccounts", accountDtoList);
                } else if(action.equals("GetAllTeacherAccounts")){
                    accountDtoList = getAllTeacherAccountsController();
                    jsonObject.put("teacherAccounts", accountDtoList);
                }
                System.out.print(accountDto); 
            }catch (Exception e){
                System.out.println("Exception Found in run() in AccountController:");
                e.printStackTrace();
                // Adds an error message if there exists.
                accountDto.addError(GlobalConstants.ERR_SERVER_CONTROLLER_PREFIX + e.getMessage());
                // initialize the json object that will be passed as response.
                if (null == jsonObject) {
                    jsonObject = new JSONObject();
                }
            }
            
            // add the error message to the json object.
            jsonObject.put("errorList", accountDto.getErrorList());
            
            // set the type of response.
            response.setContentType(GlobalConstants.SYS_CONTENT_TYPE_JSON);
            // send the response back to the JS file.
            response.getWriter().write(jsonObject.toString());
            
            System.out.println("AccountController.run():" + "end");
            return null;
        }
        
        
        
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          store a new account by calling the insertAccount in Service.
         * @param: JSONObject - the jsonObject passed by the client
         * @return AccountDto - holds the error list if there are any errors
         */
        public AccountDto insertAccountController(JSONObject jsonObject){
            System.out.println("AccountController.insertAccountController():" + "start");
            
            AccountDto accountDto;      //holds the account passed from the back-end
            String userType;
            
            accountDto = new AccountDto(); 
            
            try {
                userType = jsonObject.getString("userType");
                accountDto.setFirstName(jsonObject.getString("firstName"));
                accountDto.setLastName(jsonObject.getString("lastName"));
                accountDto.setUsername(jsonObject.getString("username"));
                accountDto.setPassword(jsonObject.getString("password"));
                accountDto.setEmailAddress(jsonObject.getString("emailAddress"));
                accountDto.setContactNumber(jsonObject.getString("contactNumber"));
                accountDto.setUserType(jsonObject.getString("userType"));
                accountDto.setStatus(true);
                if("student".equals(userType)){
                    accountDto.setParentContact(jsonObject.getString("parentContact"));
                    accountDto.setParentName(jsonObject.getString("parentName"));
                    accountDto.setSchool(jsonObject.getString("school"));
                    accountDto.setStrand(jsonObject.getString("strand"));
                    accountDto.setYearLevel(Integer.parseInt(jsonObject.getString("yearLevel")));
                } else if ("teacher".equals(userType)){
                    accountDto.setCourseCode(jsonObject.getString("courseCode"));
                }                 
                accountDto = accountService.insertAccount(accountDto);               
            } catch (Exception e) {
                System.out.println("Exception Found in insertAccountController() in AccountController:");
                e.printStackTrace();
                accountDto.addError(e.toString());
            }
            
            System.out.println("AccountController.insertAccountController():" + "end");
            return accountDto;
        }
        
        
        
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          store update an account by calling the updateAccount in Service.
         * @param: JSONObject - the jsonObject passed by the client
         * @return: AccountDto - holds the error list if there are any errors
         */
        public AccountDto updateAccountController(JSONObject jsonObject){
            System.out.println("AccountController.updateAccountController():" + "start");

            AccountDto accountDto;      //holds the account passed from the back-end
            String userType;
            
            accountDto = new AccountDto();   
            
            try {
                userType = jsonObject.getString("userType");
                accountDto.setAccountId(Long.parseLong(jsonObject.getString("accountId")));
                accountDto.setId(Long.parseLong(jsonObject.getString("id")));
                accountDto.setFirstName(jsonObject.getString("firstName"));
                accountDto.setLastName(jsonObject.getString("lastName"));
                accountDto.setUsername(jsonObject.getString("username"));
                accountDto.setPassword(jsonObject.getString("password"));
                accountDto.setEmailAddress(jsonObject.getString("emailAddress"));
                accountDto.setContactNumber(jsonObject.getString("contactNumber"));
                accountDto.setUserType(jsonObject.getString("userType"));
                accountDto.setStatus(Boolean.parseBoolean(jsonObject.getString("status")));
                if("student".equals(userType)){
                    accountDto.setParentContact(jsonObject.getString("parentContact"));
                    accountDto.setParentName(jsonObject.getString("parentName"));
                    accountDto.setSchool(jsonObject.getString("school"));
                    accountDto.setStrand(jsonObject.getString("strand"));
                    accountDto.setYearLevel(Integer.parseInt(jsonObject.getString("yearLevel")));
                } else if ("teacher".equals(userType)){
                    accountDto.setCourseCode(jsonObject.getString("courseCode"));
                }               
                
                accountDto = accountService.updateAccount(accountDto); 
                
            } catch (JSONException e) {
                System.out.println("Exception Found in updateAccountController() in AccountController:");
                e.printStackTrace();
                accountDto.addError(e.toString());
            }
            
            System.out.println("AccountController.updateAccountController():" + "end");
            return accountDto;
        }
        
        
        
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          set status of an account to false and calls the deleteAccount in Service.
         * @param: JSONObject - the jsonObject passed by the client
         * @return: AccountDto - holds the error list if there are any errors
         */
        public AccountDto deleteAccountController(JSONObject jsonObject){
            System.out.println("AccountController.deleteAccountController():" + "start");

            AccountDto accountDto;      //holds the account passed from the back-end
            
            accountDto = new AccountDto();
            
            try {
                accountDto.setId(Long.parseLong(jsonObject.getString("id")));
                accountDto = accountService.deleteAccount(accountDto);
            } catch (Exception e) {
                System.out.println("Exception Found in deleteAccountController() in AccountController:");
                e.printStackTrace();
                accountDto.addError(e.toString());
            }
            
            System.out.println("AccountController.deleteAccountController():" + "end");
            return accountDto;
        }
        
        
        
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          retrieve an account by calling the getAccountById in Service.
         * @param: JSONObject - the jsonObject passed by the client
         * @return AccountDto - holds the details of the account if successful
         *                      or holds the error list if there are any errors
         */
        public AccountDto getAccountByIdController(JSONObject jsonObject){
            System.out.println("AccountController.getAccountByIdController():" + "start");

            AccountDto accountDto;      //holds the account passed from the back-end
            
            accountDto = new AccountDto();
            
            try {
                accountDto.setId(Long.parseLong(jsonObject.getString("id")));
                accountDto = accountService.getAccountById(accountDto);
            } catch (Exception e){
                System.out.println("Exception Found in getAccountByIdController() in AccountController:");
                e.printStackTrace();
                accountDto.addError(e.toString());
            }
        
            System.out.println("AccountController.getAccountByIdController():" + "end");
            return accountDto;
        }
        
        
        
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          retrieve all student accounts by calling the getAllStudentAccounts in Service.
         * @param: JSONObject - the jsonObject passed by the client
         * @return AccountDto - holds the details of the account if successful
         *                      or holds the error list if there are any errors
         */
        public List<AccountDto> getAllStudentAccountsController(){
            System.out.println("AccountController.getAllStudentAccountsController():" + "start");

            List<AccountDto> accountDtoList;    //holds the list of accounts passed from the back-end
            
            accountDtoList = accountService.getAllStudentAccounts();   
            
            System.out.println("AccountController.getAllStudentAccountsController():" + "end");
            return accountDtoList;
        }
        
        
        
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          retrieve all student accounts by calling the getAllTeacherAccounts in Service.
         * @param: JSONObject - the jsonObject passed by the client
         * @return AccountDto - holds the details of the account if successful
         *                      or holds the error list if there are any errors
         */
        public List<AccountDto> getAllTeacherAccountsController(){
            System.out.println("AccountController.getAllTeacherAccountsController():" + "start");

            List<AccountDto> accountDtoList;    //holds the list of accounts passed from the back-end
            
            accountDtoList = accountService.getAllTeacherAccounts();  
            
            System.out.println("AccountController.getAllTeacherAccountsController():" + "end");
            return accountDtoList;
        }
        
        

        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          change the password of an account
         * @param: JSONObject - the jsonObject passed by the client
         * @return: AccountDto - holds the error list if there are any errors
         */
        public AccountDto changePasswordController(JSONObject jsonObject){
            System.out.println("AccountController.changePasswordController():" + "start");

            String newPassword;
            AccountDto accountDto;  //holds the account passed from the back-end
            
            accountDto = new AccountDto();
           
            try {
                newPassword = jsonObject.getString("newPassword");
                accountDto.setId(Long.parseLong(jsonObject.getString("id")));
                accountDto = accountService.getAccountById(accountDto);
                if(newPassword.equals(accountDto.getPassword())){
                    accountDto.addError("Error: Password is still the same.");
                } else {
                    accountDto.setPassword(newPassword);
                    accountDto = accountService.updateAccount(accountDto);
                }
            } catch (Exception e) {
                System.out.println("Exception Found in changePasswordController() in AccountController:");
                e.printStackTrace();
                accountDto.addError(e.toString());
            }
            
            System.out.println("AccountController.changePasswordController():" + "end");
            return accountDto;            
        }
        
        
        
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          retrieve all student accounts in a strand by calling the getStudentsByStrand in Service.
         * @param: JSONObject - the jsonObject passed by the client
         * @return: AccountDto - holds the details of the accounts if successful
         *                      or holds the error list if there are any errors
         */
        public List<AccountDto> getStudentsByStrandController(JSONObject jsonObject){
            System.out.println("AccountController.getStudentsByStrandController():" + "start");

            List<AccountDto> accountDtoList;    //holds the list of accounts passed from the back-end
            AccountDto accountDto;              //holds the account passed from the back-end
            
            accountDto = new AccountDto();
            accountDtoList = null;
            
            try{
                accountDto.setStrand(jsonObject.getString("strand"));
                accountDtoList = accountService.getStudentsByStrand(accountDto);
            } catch (Exception e){
                System.out.println("Exception Found in getStudentsByStrandController() in AccountController:");
                e.printStackTrace();
            }
            
            System.out.println("AccountController.getStudentsByStrandController():" + "end");
            return accountDtoList;
        }
}
