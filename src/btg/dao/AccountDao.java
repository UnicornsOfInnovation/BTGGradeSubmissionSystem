/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 18, 2016 - Created the DAO file (Dave)
 *                      - Created the insertAccount(), updateAccount(), deleteAccount(), getAccountById() (Dave)
 *                      - getAllStudentAccounts(), getAllTeacherAccounts() (Dave)
 *     October 19, 2016 - getAccountByEmailAddress() (Dave)
 *     October 24, 2016 - getStudentsByStrand() (Dave)
 *     November 15, 2016 - getAccountByUsername() (Dave)
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
        Transaction tx = Datastore.beginTransaction();
        Key parentKey;
        Key key;
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
        
    }
    
    
    
    /*
     * Purpose: Updates an account to the database
     * @param: AccountModel to be updated to the database
     * @return: void
     */
    public void updateAccount(AccountModel inputAccount){
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(inputAccount);
        tx.commit();        
    }
    
    
    
    /*
     * Purpose: Deletes an account to the database
     * @param: AccountModel to be soft deleted to the database
     * @return: void
     */
    public void deleteAccount(AccountModel inputAccount){
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(inputAccount);
        tx.commit();
        
    }
    
    
    
    
    /*
     * Purpose: Gets an account from the database by the ID.
     * @param: AccountModel that stores the ID
     * @return: AccountModel (stores the complete account details from datastore)
     */
    public AccountModel getAccountById(AccountModel inputAccount){
        AccountModelMeta accountModelMeta;
        AccountModel accountModel;
        
        accountModel = new AccountModel();
        
        accountModelMeta= AccountModelMeta.get();        
        accountModel = Datastore.query(accountModelMeta)
                        .filter(accountModelMeta.id.equal(inputAccount.getId()))
                        .asSingle();        
        return accountModel;
    }
    
    
    /*
     * Purpose: Gets all the student accounts from the datastore.
     * @param: none
     * @return: List<AccountModel> (stores all of the student accounts from the database.
     */
    public List<AccountModel> getAllStudentAccounts(){
        List<AccountModel> accountModelList;
        AccountModelMeta accountModelMeta = AccountModelMeta.get();
        
        accountModelList = Datastore.query(accountModelMeta)
                        .filter(accountModelMeta.userType.equal("student"),
                               (accountModelMeta.status.equal(true)))
                        .asList();
        
        return accountModelList;
        
    }
    
    /*
     * Purpose: Gets all the teacher accounts from the datastore.
     * @param: none
     * @return: List<AccountModel> (stores all of the student accounts from the database.
     */
    public List<AccountModel> getAllTeacherAccounts(){
        List<AccountModel> accountModelList;
        
        AccountModelMeta accountModelMeta = AccountModelMeta.get();
        accountModelList = Datastore.query(accountModelMeta)
                        .filter(accountModelMeta.userType.equal("teacher"),
                               (accountModelMeta.status.equal(true)))
                        .asList();
        
        return accountModelList;
        
    }
    
    /*
     * Purpose: Gets the account with the given Email Address. This is used for inserting new account
     *          to know that there are no duplicates.
     * @param: AccountModel
     * @return: AccountModel (stores the details of the student with that emailAddress)
     */
    public AccountModel getAccountByEmailAddress(AccountModel inputAccount){
        AccountModel accountModel;
        
        AccountModelMeta accountModelMeta = AccountModelMeta.get();
        accountModel = Datastore.query(accountModelMeta)
                .filter(accountModelMeta.emailAddress.equal(inputAccount.getEmailAddress()))
                .asSingle();
        return accountModel;    
    }
    
    
    /*
     * Purpose: Gets all the student accounts by strand from the datastore.
     * @param: AccountModel containing the strand
     * @return: List<AccountModel> (stores all of the student accounts of the given strand from the database.
     */
    public List<AccountModel> getStudentsByStrand(AccountModel inputAccount){
        List<AccountModel> accountModelList;
        AccountModelMeta accountModelMeta = AccountModelMeta.get();
        
        accountModelList = Datastore.query(accountModelMeta)
                        .filter(accountModelMeta.userType.equal("student"),
                               (accountModelMeta.strand.equal(inputAccount.getStrand())),
                               (accountModelMeta.status.equal(true)))
                        .asList();
        
        return accountModelList;
        
    }
    
    
    public AccountModel getAccountByUsername(AccountModel inputAccount){
        AccountModel accountModel;
        AccountModelMeta accountModelMeta = AccountModelMeta.get();
        
        accountModel = Datastore.query(accountModelMeta)
                .filter(accountModelMeta.username.equal(inputAccount.getUsername()))
                .asSingle();
        
        return accountModel;
    }
    
    
   
}
