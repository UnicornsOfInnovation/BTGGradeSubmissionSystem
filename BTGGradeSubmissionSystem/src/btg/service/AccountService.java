/*
 * Main Developer: Christian Dave B. Baclayon
 *  Development History:
 *     October 18, 2016 - Created the Service file (Dave)
 *                      - Created the insertAccount(), updateAccount(), deleteAccount(), getAccountById() (Dave)
 *     October 23, 2016 - Created computeGPA() (Dave)
 *     October 24, 2016 - Created getStudentsByStrand() (Dave)
 *     November 9, 2016 - reviewed the Coding Standards for this file (Dave)
 */

package btg.service;

import java.util.ArrayList;
import java.util.List;

import org.slim3.repackaged.org.json.JSONArray;

import btg.dao.AccountDao;
import btg.dao.CourseDao;
import btg.dto.AccountDto;
import btg.model.AccountModel;
import btg.model.CourseModel;
import btg.model.GradeModel;
import btg.common.GlobalConstants;

public class AccountService {

    AccountDao accountDao = new AccountDao();   //used to access the methods in the Data Access Object or Database
    
    
    
    /*
     *  Purpose: Checks if account is not yet in the database by checking the EMAIL ADDRESS and the unique ID. 
     *           If everything is unique, stores to model and calls dao.            
     *  @param: AccountDto that will be stored to the database
     *  @return: AccountDto that will hold the error list if exception occurs.
     */
    public AccountDto insertAccount(AccountDto inputAccount){
        System.out.println("AccountService.insertAccount():"+ "start");

        AccountModel accountModel;          //used to store the data from DTO and this is passed to DAO
        
        accountModel = new AccountModel();
        
        accountModel.setContactNumber(inputAccount.getContactNumber());
        accountModel.setCourseCode(inputAccount.getCourseCode());
        accountModel.setEmailAddress(inputAccount.getEmailAddress());
        accountModel.setFirstName(inputAccount.getFirstName());
        accountModel.setLastName(inputAccount.getLastName());
        accountModel.setParentContact(inputAccount.getParentContact());
        accountModel.setParentName(inputAccount.getParentName());
        accountModel.setPassword(inputAccount.getPassword());
        accountModel.setSchool(inputAccount.getSchool());
        accountModel.setStatus(inputAccount.getStatus());
        accountModel.setStrand(inputAccount.getStrand());
        accountModel.setUsername(inputAccount.getUsername());
        accountModel.setUserType(inputAccount.getUserType());
        accountModel.setYearLevel(inputAccount.getYearLevel());
        
        try {
            /*
             * TODO: Add a condition if the email address is the same
             * Plan: Get all accounts from database. Traverse to the list of accounts and check 
             * if the email address is the same.
             */
            if (null == accountDao.getAccountByEmailAddress(accountModel)) {
                try {
                    accountDao.insertAccount(accountModel);
                } catch (Exception e) {
                    System.out.println("Exception in insertAccount() in AccountService: ");
                    System.out.println(e.toString());
                    System.out.println(GlobalConstants.ERR_ENTRY_CANNOT_ADD);
                    inputAccount.addError(GlobalConstants.ERR_ENTRY_CANNOT_ADD);
                }
            } else {
                inputAccount.addError(GlobalConstants.ERR_ENTRY_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            System.out.println("Exception in insertAccount() in AccountService: ");
            e.printStackTrace();
            inputAccount.addError(GlobalConstants.ERR_DB_EXCEPTION);
        }        

        System.out.println("AccountService.insertAccount():"+ "end");
        return inputAccount;
    }
    
    
    
    
    
    /*
     *  Purpose: Checks if account is already in the database by checking the EMAIL ADDRESS or the unique ID. 
     *           If it exists, stores to model and calls dao.            
     *  @param: AccountDto that will be updated to the database
     *  @return: AccountDto that will hold the error list if exception occurs.
     */
    public AccountDto updateAccount(AccountDto inputAccount){
        System.out.println("AccountService.updateAccount():"+ "start");

        AccountModel accountModel;          //used to store the data from DTO and this is passed to DAO
        AccountModel temp;                  //used to store data from database if account entry already exists
        
        accountModel = new AccountModel();
        
        accountModel.setAccountId(inputAccount.getAccountId());
        accountModel.setId(inputAccount.getId());
        accountModel.setContactNumber(inputAccount.getContactNumber());
        accountModel.setCourseCode(inputAccount.getCourseCode());
        accountModel.setEmailAddress(inputAccount.getEmailAddress());
        accountModel.setFirstName(inputAccount.getFirstName());
        accountModel.setLastName(inputAccount.getLastName());
        accountModel.setParentContact(inputAccount.getParentContact());
        accountModel.setParentName(inputAccount.getParentName());
        accountModel.setPassword(inputAccount.getPassword());
        accountModel.setSchool(inputAccount.getSchool());
        accountModel.setStatus(inputAccount.getStatus());
        accountModel.setStrand(inputAccount.getStrand());
        accountModel.setUsername(inputAccount.getUsername());
        accountModel.setUserType(inputAccount.getUserType());
        accountModel.setYearLevel(inputAccount.getYearLevel());
        
        
        try {
            temp = new AccountModel();
            temp = accountDao.getAccountById(accountModel);
            
            if (null != temp || true == accountModel.getStatus()) {
                try {
                    accountModel.setKey(temp.getKey());
                    accountDao.updateAccount(accountModel);
                } catch (Exception e) {
                    System.out.println("Exception in updateAccount() in AccountService: ");
                    System.out.println(GlobalConstants.ERR_ENTRY_CANNOT_UPDATE);
                    inputAccount.addError(GlobalConstants.ERR_ENTRY_CANNOT_UPDATE);
                    System.out.println(e.toString());
                }
            } else {
                inputAccount.addError(GlobalConstants.ERR_ENTRY_NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Exception in updateAccount() in AccountService: ");
            e.printStackTrace();
            inputAccount.addError(GlobalConstants.ERR_DB_EXCEPTION);
        }
        
        System.out.println("AccountService.updateAccount():"+ "end");
        return inputAccount;
    }
    
    
    
    
    
    /*
     *  Purpose: Checks if account is in the database by checking the EMAIL ADDRESS or the unique ID. 
     *           If it exists, soft delete by setting the status to the model and calls dao.            
     *  @param: AccountDto that will be deleted from the database
     *  @return: AccountDto that will hold the error list if exception occurs.
     */
    public AccountDto deleteAccount(AccountDto inputAccount){
        System.out.println("AccountService.deleteAccount():"+ "start");
        AccountModel accountModel;      //used to store the data from DTO and this is passed to DAO
        
        accountModel = new AccountModel();
        
        accountModel.setId(inputAccount.getId());
        try{
            accountModel = accountDao.getAccountById(accountModel);
            if(null != accountModel){
                try{
                    accountModel.setStatus(false);
                    accountDao.deleteAccount(accountModel);
                } catch(Exception e){
                    inputAccount.addError(e.toString());
                    inputAccount.addError(GlobalConstants.ERR_ENTRY_CANNOT_DELETE);
                    System.out.println("Exception in deleteAccount() in AccountService: ");
                    System.out.println(e.toString());
                    System.out.println(GlobalConstants.ERR_ENTRY_CANNOT_UPDATE);
                    
                }
            } else {
                inputAccount.addError(GlobalConstants.ERR_ENTRY_NOT_FOUND);
                
            }
        } catch (Exception e){
            inputAccount.addError(GlobalConstants.ERR_DB_EXCEPTION);
            System.out.println("Exception in deleteAccount() in AccountService: ");
            e.printStackTrace();
        }
        
        System.out.println("AccountService.deleteAccount():"+ "end");
        return inputAccount;
    }
    
    
    
    
    
    /*
     *  Purpose: Checks if account is in the database by checking the EMAIL ADDRESS or the unique ID. 
     *           If it exists, stores the retrieved account to the dto.            
     *  @param: AccountDto containing the ID
     *  @return: AccountDto that will hold the complete details of the account or the error list if exception occurs.
     */
    public AccountDto getAccountById(AccountDto inputAccount){
        System.out.println("AccountService.getAccountById():"+ "start");

        AccountModel accountModel;        //used to store the data from DTO and this is passed to DAO

        accountModel = new AccountModel();
        
        accountModel.setId(inputAccount.getId());
        
        try {
            accountModel = accountDao.getAccountById(accountModel);
            if (null != accountModel) {
                inputAccount.setAccountId(accountModel.getAccountId());
                inputAccount.setId(accountModel.getId());
                inputAccount.setContactNumber(accountModel.getContactNumber());
                inputAccount.setCourseCode(accountModel.getCourseCode());
                inputAccount.setEmailAddress(accountModel.getEmailAddress());
                inputAccount.setFirstName(accountModel.getFirstName());
                inputAccount.setLastName(accountModel.getLastName());
                inputAccount.setParentContact(accountModel.getParentContact());
                inputAccount.setParentName(accountModel.getParentName());
                inputAccount.setPassword(accountModel.getPassword());
                inputAccount.setSchool(accountModel.getSchool());
                inputAccount.setStatus(accountModel.getStatus());
                inputAccount.setStrand(accountModel.getStrand());
                inputAccount.setUsername(accountModel.getUsername());
                inputAccount.setUserType(accountModel.getUserType());
                inputAccount.setYearLevel(accountModel.getYearLevel());                
            } else {
                inputAccount.addError(GlobalConstants.ERR_ENTRY_NOT_FOUND);
            }
        } catch (Exception e) {
            inputAccount.addError(GlobalConstants.ERR_DB_EXCEPTION);
            System.out.println("Exception in getAccountById() in AccountService: ");
            e.printStackTrace();
        }
        
        System.out.println("AccountService.getAccountById():"+ "end");
        return inputAccount;
    }
    
    
    
    
    
    /*
     *  Purpose: Retrieves all of the student accounts by calling the getAllStudentAccounts() in AccountDao
     *          Transfers the data from the model to the dto.        
     *  @param: none
     *  @return: List<AccountDto> that will hold the complete details of the student accounts.
     */    
    public List<AccountDto> getAllStudentAccounts(){
        System.out.println("AccountService.getAllStudentAccounts():"+ "start");

        List<AccountDto> accountDtoList;        //used to store all student accounts as a DTO
        List<AccountModel> accountModelList;    //used to store all student accounts as a Model from the database.
        
        accountDtoList = null;
        
        try{
            accountModelList = accountDao.getAllStudentAccounts();
            accountDtoList = new ArrayList<AccountDto>();
            
            for(AccountModel accountModel : accountModelList){
                AccountDto accountDto;            
                accountDto = new AccountDto();
                accountDto.setAccountId(accountModel.getAccountId());
                accountDto.setId(accountModel.getId());
                accountDto.setContactNumber(accountModel.getContactNumber());
                accountDto.setCourseCode(accountModel.getCourseCode());
                accountDto.setEmailAddress(accountModel.getEmailAddress());
                accountDto.setFirstName(accountModel.getFirstName());
                accountDto.setLastName(accountModel.getLastName());
                accountDto.setParentContact(accountModel.getParentContact());
                accountDto.setParentName(accountModel.getParentName());
                accountDto.setPassword(accountModel.getPassword());
                accountDto.setSchool(accountModel.getSchool());
                accountDto.setStatus(accountModel.getStatus());
                accountDto.setStrand(accountModel.getStrand());
                accountDto.setUsername(accountModel.getUsername());
                accountDto.setUserType(accountModel.getUserType());
                accountDto.setYearLevel(accountModel.getYearLevel());
                accountDtoList.add(accountDto);                
            }
        } catch (Exception e){
            System.out.println(GlobalConstants.ERR_DB_EXCEPTION); 
            System.out.println("Exception in getAllStudentAccounts() in AccountService: ");
            e.printStackTrace();
        }
        
        System.out.println("AccountService.getAllStudentAccounts():"+ "end");
        return accountDtoList;
        
    }
    
    
    
    
    
    /*
     *  Purpose: Retrieves all of the teacher accounts by calling the getAllTeacherAccounts() in AccountDao
     *          Transfers the data from the model to the dto.        
     *  @param: none
     *  @return: List<AccountDto> that will hold the complete details of the teacher accounts.
     */    
    public List<AccountDto> getAllTeacherAccounts(){
        System.out.println("AccountService.getAllTeacherAccounts():"+ "start");

        List<AccountDto> accountDtoList;        //used to store all teacher accounts as a DTO
        List<AccountModel> accountModelList;    //used to store all teacher accounts as a Model from the database.
        
        accountDtoList = null;
        
        try{
            accountModelList = accountDao.getAllTeacherAccounts();
            accountDtoList = new ArrayList<AccountDto>();
            
            for(AccountModel accountModel : accountModelList){
                AccountDto accountDto;            
                accountDto = new AccountDto();
                accountDto.setAccountId(accountModel.getAccountId());
                accountDto.setId(accountModel.getId());
                accountDto.setContactNumber(accountModel.getContactNumber());
                accountDto.setCourseCode(accountModel.getCourseCode());
                accountDto.setEmailAddress(accountModel.getEmailAddress());
                accountDto.setFirstName(accountModel.getFirstName());
                accountDto.setLastName(accountModel.getLastName());
                accountDto.setParentContact(accountModel.getParentContact());
                accountDto.setParentName(accountModel.getParentName());
                accountDto.setPassword(accountModel.getPassword());
                accountDto.setSchool(accountModel.getSchool());
                accountDto.setStatus(accountModel.getStatus());
                accountDto.setStrand(accountModel.getStrand());
                accountDto.setUsername(accountModel.getUsername());
                accountDto.setUserType(accountModel.getUserType());
                accountDto.setYearLevel(accountModel.getYearLevel());
                accountDtoList.add(accountDto);                
            }
        } catch (Exception e){
            System.out.println(GlobalConstants.ERR_DB_EXCEPTION);   
            System.out.println("Exception in getAllTeacherAccounts() in AccountService: ");
            e.printStackTrace();
        }
        
        System.out.println("AccountService.getAllTeacherAccounts():"+ "end");
        return accountDtoList;
        
    }
    
   
    
    
    
    /*
     *  Purpose: Receives the gradeArray and computes for the new GPA of the student everytime a teacher submits a grade.       
     *  @param: JSONArray - contains the grades stored in an array that will be used to compute the gpa of the student
     *  @return: none
     */
    public void computeGPA(JSONArray gradesArray){
        System.out.println("AccountService.computeGPA():"+ "start");

        AccountModel accountModel;          //used to store the student account of which the gpa will be computed
        GradeService gradeService;          //used to access methods in computing the GPA
        List<GradeModel> gradeModelList;    //used to hold all the grades of that student
        CourseModel courseModel;            //used to store the Course from the database of that grade
        CourseDao courseDao;                //used to access the database through the methods of courseDao
        int totalUnits;
        double cumulativeGrade;
        double gpa;
        int ctr;
        
        courseDao = new CourseDao();
        gradeModelList = null;
        gradeService = new GradeService();
        for(ctr=0; ctr < gradesArray.length(); ctr++){
            accountModel = new AccountModel();
            try {
                accountModel.setId(Long.parseLong(gradesArray.getJSONObject(ctr).getString("accountId")));
                // Getting the account from the database
                accountModel = accountDao.getAccountById(accountModel);
                // Computing for the GPA of the account
                if(null != accountModel){
                    // Getting all the grades of the account from the database
                    gradeModelList = gradeService.getAllGradesByAccountId(accountModel);
                    totalUnits = 0;
                    cumulativeGrade = 0;
                    for(GradeModel gradeModel : gradeModelList){
                        // Getting the course units
                        courseModel = new CourseModel();
                        courseModel.setId(gradeModel.getCourseId());
                        courseModel = courseDao.getCourseById(courseModel);
                        // Adding the units to total units
                        totalUnits += courseModel.getCourseUnits();
                        // Computing the weight of the grade
                        cumulativeGrade += (courseModel.getCourseUnits() * gradeModel.getGrade());                 
                    }
                    gpa = cumulativeGrade/totalUnits;
                    // Storing the GPA to the account and updating the account.
                    accountModel.setGpa(gpa);
                    accountDao.updateAccount(accountModel);
                }
            } catch (Exception e){
                System.out.println("This exception is found in computeGPA in AccountService:");
                e.printStackTrace();
            }
            
        }
        
        System.out.println("AccountService.computeGPA():"+ "end");

    }
    
    
    
    
    
    /*
     *  Purpose: Receives an AccountDto that holds the Strand to be retrieved and returns a list of students with that strand       
     *  @param: AccountDto - holds the strand 
     *  @return: List<AccountDto> - holds all of the accounts that have the same strand as the input strand
     */           
    public List<AccountDto> getStudentsByStrand (AccountDto inputAccount){
        System.out.println("AccountService.getStudentsByStrand():"+ "start");

        List<AccountDto> accountDtoList;            //used to store all student accounts of that strand in the DTO
        List<AccountModel> accountModelList;        //used to store all student accounts of that strand in the Model
        AccountModel accountModelTemp;              //used to store the strand from the inputAccount
        
        accountModelTemp = new AccountModel();
        accountDtoList = null;
        accountModelTemp.setStrand(inputAccount.getStrand());
        try{
            accountModelList = accountDao.getStudentsByStrand(accountModelTemp);
            accountDtoList = new ArrayList<AccountDto>();
            
            for(AccountModel accountModel : accountModelList){
                AccountDto accountDto;            
                accountDto = new AccountDto();
                accountDto.setAccountId(accountModel.getAccountId());
                accountDto.setId(accountModel.getId());
                accountDto.setContactNumber(accountModel.getContactNumber());
                accountDto.setCourseCode(accountModel.getCourseCode());
                accountDto.setEmailAddress(accountModel.getEmailAddress());
                accountDto.setFirstName(accountModel.getFirstName());
                accountDto.setLastName(accountModel.getLastName());
                accountDto.setParentContact(accountModel.getParentContact());
                accountDto.setParentName(accountModel.getParentName());
                accountDto.setPassword(accountModel.getPassword());
                accountDto.setSchool(accountModel.getSchool());
                accountDto.setStatus(accountModel.getStatus());
                accountDto.setStrand(accountModel.getStrand());
                accountDto.setUsername(accountModel.getUsername());
                accountDto.setUserType(accountModel.getUserType());
                accountDto.setYearLevel(accountModel.getYearLevel());
                accountDtoList.add(accountDto);                
            }
        } catch (Exception e){
            System.out.println(GlobalConstants.ERR_DB_EXCEPTION);    
            System.out.println("Exception in getStudentsByStrand() in AccountService: ");
            e.printStackTrace();
        }
        
        System.out.println("AccountService.getStudentsByStrand():"+ "end");
        return accountDtoList;  
    }
        
}
