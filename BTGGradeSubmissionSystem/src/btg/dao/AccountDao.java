/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 18, 2016 - Created the DAO file (Dave)
 *                      - Created the insertAccount(), updateAccount(), deleteAccount(), getAccountById() (Dave)
 *                      - getAllStudentAccounts(), getAllTeacherAccounts() (Dave)
 *     October 19, 2016 - getAccountByEmailAddress() (Dave)
 *     October 24, 2016 - getStudentsByStrand() (Dave)
 */


package btg.dao;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import btg.meta.AccountModelMeta;
import btg.model.AccountModel;

public class AccountDao {
    
    
    /*
     *  Purpose: Inserts an account to the database
     *  @param: AccountModel to insert to the database
     *  @return: void
     */
    public void insertAccount(AccountModel inputAccount){
        System.out.println("AccountDao.insertAccount():" + "start");

        Transaction tx;      //used to create transaction for insert
        
        Key parentKey;
        Key key;
        
        tx = Datastore.beginTransaction();
        
        // creating key and ID for the new entity
        parentKey = KeyFactory.createKey("Account", inputAccount.getEmailAddress());
        key = Datastore.allocateId(parentKey, "AccountModel");
        
        // setting the 'key' and 'id' of the model
        inputAccount.setKey(key);
        inputAccount.setId(key.getId());
        inputAccount.setAccountId(key.getId());
        
        // adding the item to the datastore
        Datastore.put(inputAccount);
        tx.commit();
        
        System.out.println("AccountDao.insertAccount():" + "end");
    }
    
    
    
    
    
    /*
     * Purpose: Updates an account to the database
     * @param: AccountModel to be updated to the database
     * @return: void
     */
    public void updateAccount(AccountModel inputAccount){
        System.out.println("AccountDao.updateAccount():" + "start");

        Transaction tx;     //used to create transaction for insert
        
        tx = Datastore.beginTransaction();
        Datastore.put(inputAccount);
        tx.commit();
        
        System.out.println("AccountDao.updateAccount():" + "end");
    }
    
    
    
    
    
    /*
     * Purpose: Deletes an account to the database
     * @param: AccountModel to be soft deleted to the database
     * @return: void
     */
    public void deleteAccount(AccountModel inputAccount){
        System.out.println("AccountDao.deleteAccount():" + "start");

        Transaction tx;     //used to create transaction for insert
        
        tx = Datastore.beginTransaction();
        
        Datastore.put(inputAccount);
        tx.commit();
        
        System.out.println("AccountDao.deleteAccount():" + "end");
    }
    
    
    
    
    
    /*
     * Purpose: Gets an account from the database by the ID.
     * @param: AccountModel that stores the ID
     * @return: AccountModel (stores the complete account details from datastore)
     */
    public AccountModel getAccountById(AccountModel inputAccount){
        System.out.println("AccountDao.getAccountById():" + "start");

        AccountModelMeta accountModelMeta;  //MetaClass for accessing the database
        AccountModel accountModel;          //object to hold account retrieved from the database
        
        accountModel = new AccountModel();
        
        accountModelMeta= AccountModelMeta.get();        
        accountModel = Datastore.query(accountModelMeta)
                        .filter(accountModelMeta.id.equal(inputAccount.getId()))
                        .asSingle();
        
        System.out.println("AccountDao.getAccountById():" + "end");
        return accountModel;
    }
    
    
    
    
    
    /*
     * Purpose: Gets all the student accounts from the datastore.
     * @param: none
     * @return: List<AccountModel> (stores all of the student accounts from the database.
     */
    public List<AccountModel> getAllStudentAccounts(){
        System.out.println("AccountDao.getAllStudentAccounts():" + "start");

        List<AccountModel> accountModelList;    //object to hold list of accounts retrieved from the database
        AccountModelMeta accountModelMeta;      //MetaClass for accessing the database
        
        accountModelMeta = AccountModelMeta.get();        
        accountModelList = Datastore.query(accountModelMeta)
                        .filter(accountModelMeta.userType.equal("student"),
                               (accountModelMeta.status.equal(true)))
                        .asList();
        
        System.out.println("AccountDao.getAllStudentAccounts():" + "end");
        return accountModelList;
        
    }
    
    
    
    
    
    /*
     * Purpose: Gets all the teacher accounts from the datastore.
     * @param: none
     * @return: List<AccountModel> (stores all of the student accounts from the database.
     */
    public List<AccountModel> getAllTeacherAccounts(){
        System.out.println("AccountDao.getAllTeacherAccounts():" + "start");

        List<AccountModel> accountModelList;    //object to hold list of accounts retrieved from the database
        AccountModelMeta accountModelMeta;      //MetaClass for accessing the database
        
        accountModelMeta = AccountModelMeta.get();
        accountModelList = Datastore.query(accountModelMeta)
                        .filter(accountModelMeta.userType.equal("teacher"),
                               (accountModelMeta.status.equal(true)))
                        .asList();
        
        System.out.println("AccountDao.getAllTeacherAccounts():" + "end");
        return accountModelList;
        
    }
    
    
    
    
    
    /*
     * Purpose: Gets the account with the given Email Address. This is used for inserting new account
     *          to know that there are no duplicates.
     * @param: AccountModel
     * @return: AccountModel (stores the details of the student with that emailAddress)
     */
    public AccountModel getAccountByEmailAddress(AccountModel inputAccount){
        System.out.println("AccountDao.getAccountByEmailAddress():" + "start");

        AccountModel accountModel;          //object to hold account retrieved from the database
        AccountModelMeta accountModelMeta;  //MetaClass for accessing the database
        
        accountModelMeta = AccountModelMeta.get();
        accountModel = Datastore.query(accountModelMeta)
                .filter(accountModelMeta.emailAddress.equal(inputAccount.getEmailAddress()))
                .asSingle();
        
        System.out.println("AccountDao.getAccountByEmailAddress():" + "end");
        return accountModel;    
    }
    
    
    
    
    
    /*
     * Purpose: Gets all the student accounts by strand from the datastore.
     * @param: AccountModel containing the strand
     * @return: List<AccountModel> (stores all of the student accounts of the given strand from the database.
     */
    public List<AccountModel> getStudentsByStrand(AccountModel inputAccount){
        System.out.println("AccountDao.getStudentsByStrand():" + "start");

        List<AccountModel> accountModelList;    //object to hold list of accounts retrieved from the database
        AccountModelMeta accountModelMeta;      //MetaClass for accessing the database
        
        accountModelMeta = AccountModelMeta.get();        
        accountModelList = Datastore.query(accountModelMeta)
                        .filter(accountModelMeta.userType.equal("student"),
                               (accountModelMeta.strand.equal(inputAccount.getStrand())),
                               (accountModelMeta.status.equal(true)))
                        .asList();
        
        System.out.println("AccountDao.getStudentsByStrand():" + "end");
        return accountModelList;
        
    }
    
       
}
