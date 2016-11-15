/*
 * Main Developer: Christian Dave B. Baclayon
 *  Development History:
 *     October 18, 2016 - Created the Service file (Dave)
 *                      - Created the insertAccount(), updateAccount(), deleteAccount(), getAccountById() (Dave)
 *     October 23, 2016 - Created computeGPA() (Dave)
 *     October 24, 2016 - Created getStudentsByStrand() (Dave)
 *     November 15, 2016 - Created getAccountByUsernamePassword() (Dave)
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

    AccountDao accountDao = new AccountDao();
    
    
    
    /*
     *  Purpose: Checks if account is not yet in the database by checking the EMAIL ADDRESS and the unique ID. 
     *           If everything is unique, stores to model and calls dao.            
     *  @param: AccountDto that will be stored to the database
     *  @return: AccountDto that will hold the error list if exception occurs.
     */
    public AccountDto insertAccount(AccountDto inputAccount){
        AccountModel accountModel;
        
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
                    System.out.println("Exception in insertAccount in AccountService: ");
                    System.out.println(e.toString());
                    System.out.println(GlobalConstants.ERR_ENTRY_CANNOT_ADD);
                    inputAccount.addError(GlobalConstants.ERR_ENTRY_CANNOT_ADD);
                }
            } else {
                inputAccount.addError(GlobalConstants.ERR_ENTRY_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            System.out.println("Exception in insertAccount in AccountService: ");
            System.out.println(e.toString());
            inputAccount.addError(GlobalConstants.ERR_DB_EXCEPTION);
        }        
        return inputAccount;
    }
    
    
    
    
    /*
     *  Purpose: Checks if account is already in the database by checking the EMAIL ADDRESS or the unique ID. 
     *           If it exists, stores to model and calls dao.            
     *  @param: AccountDto that will be updated to the database
     *  @return: AccountDto that will hold the error list if exception occurs.
     */
    public AccountDto updateAccount(AccountDto inputAccount){
        AccountModel accountModel;
        AccountModel temp;
        
        System.out.println("This is my AccountId: "+inputAccount.getId());
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
        
        return inputAccount;
    }
    
    
    
    
    /*
     *  Purpose: Checks if account is in the database by checking the EMAIL ADDRESS or the unique ID. 
     *           If it exists, soft delete by setting the status to the model and calls dao.            
     *  @param: AccountDto that will be deleted from the database
     *  @return: AccountDto that will hold the error list if exception occurs.
     */
    public AccountDto deleteAccount(AccountDto inputAccount){
        AccountModel accountModel;
        
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
        return inputAccount;
    }
    
    
    
    
    /*
     *  Purpose: Checks if account is in the database by checking the EMAIL ADDRESS or the unique ID. 
     *           If it exists, stores the retrieved account to the dto.            
     *  @param: AccountDto containing the ID
     *  @return: AccountDto that will hold the complete details of the account or the error list if exception occurs.
     */
    public AccountDto getAccountById(AccountDto inputAccount){
        AccountModel accountModel = new AccountModel();
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
            System.out.println(e.toString());
        }
        
        
        return inputAccount;
    }
    
    
    /*
     *  Purpose: Retrieves all of the student accounts by calling the getAllStudentAccounts() in AccountDao
     *          Transfers the data from the model to the dto.        
     *  @param: none
     *  @return: List<AccountDto> that will hold the complete details of the student accounts.
     */
    
    public List<AccountDto> getAllStudentAccounts(){
        List<AccountDto> accountDtoList;
        List<AccountModel> accountModelList;
        
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
            System.out.println(e.toString());
        }
        
        return accountDtoList;
        
    }
    
    
    
    /*
     *  Purpose: Retrieves all of the teacher accounts by calling the getAllTeacherAccounts() in AccountDao
     *          Transfers the data from the model to the dto.        
     *  @param: none
     *  @return: List<AccountDto> that will hold the complete details of the teacher accounts.
     */
    
    public List<AccountDto> getAllTeacherAccounts(){
        List<AccountDto> accountDtoList;
        List<AccountModel> accountModelList;
        
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
            System.out.println(e.toString());
        }
        
        return accountDtoList;
        
    }
    
   
    //computeGPA
    /*
     * PUT COMMENTS HERE
     * ARRANGE INDENTATION
     */
    
        public void computeGPA(JSONArray gradesArray){
            int ctr;
            AccountModel accountModel;
            GradeService gradeService;
            List<GradeModel> gradeModelList;
            CourseModel courseModel;
            CourseDao courseDao;
            int totalUnits;
            double cumulativeGrade;
            double gpa;
            
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
                    System.out.println(e.toString());
                }
                
            }
        }
        
        
        
        
        /*
         * PUT COMMENTS HERE
         */
        
        public List<AccountDto> getStudentsByStrand (AccountDto inputAccount){
            List<AccountDto> accountDtoList;
            List<AccountModel> accountModelList;
            AccountModel accountModelTemp;
            
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
                System.out.println(e.toString());
            }
            
            return accountDtoList;  
            }
        
        
        public AccountDto getAccountByUsernamePassword(AccountDto inputAccount){
            AccountModel accountModel;
            AccountModel tempAccount;
            AccountDto accountDto;
            
            tempAccount = new AccountModel();
            
            accountDto = null;
            tempAccount.setUsername(inputAccount.getUsername());
            tempAccount.setPassword(inputAccount.getPassword());
            
            try{
                accountModel = accountDao.getAccountByUsername(tempAccount);
                if(null!=accountModel && accountModel.getPassword().equals(tempAccount.getPassword())){
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
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            
            
            return accountDto;
            
        }
}
