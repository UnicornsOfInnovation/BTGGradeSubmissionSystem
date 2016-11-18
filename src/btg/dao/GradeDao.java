/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 22, 2016 - created GradeDao File
 *                      - insertGrade(), getGradeById(), getAllGradesByCourse(), updateGrade() (Dave)
 *     October 23, 2016 - created getAllGradesByAccountId() (Dave)
 */

package btg.dao;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import btg.meta.GradeModelMeta;
import btg.model.AccountModel;
import btg.model.CourseModel;
import btg.model.GradeModel;

public class GradeDao {
    
    /*
     * PUT A COMMENT HERE
     */
    
    public void insertGrade(GradeModel inputGrade){
        System.out.println("GradeDao: insertGrade() START");

        Transaction tx;
        Key parentKey;
        Key key;
        
        tx = Datastore.beginTransaction();
        // creating key and ID for the new entity
        parentKey = KeyFactory.createKey("Grade", ""+inputGrade.getCourseId()+""+inputGrade.getAccountId()); //akong gi add ang duha ahahahahaha
        key = Datastore.allocateId(parentKey, "GradeModel");
        
        // setting the 'key' and 'id' of the model
        inputGrade.setKey(key);
        inputGrade.setId(key.getId());
        inputGrade.setGradeId(key.getId());
        
        // adding the item to the datastore
        Datastore.put(inputGrade);
        
        tx.commit();
        System.out.println("GradeDao: insertGrade() END");

    }
    
    
    /*
     *  Purpose: Gets a grade by Id from the database
     *  @param: GradeModel to check in the database
     *  @return: GradeModel returning the complete details of the grade
     */ 
    public GradeModel getGradeById(GradeModel inputGrade){
        GradeModelMeta gradeModelMeta;
        GradeModel gradeModel;
        
        gradeModelMeta= GradeModelMeta.get();        
        gradeModel = Datastore.query(gradeModelMeta)
                        .filter(gradeModelMeta.id.equal(inputGrade.getId()))
                        .asSingle();        
        return gradeModel;
    }
    
    /*
     *  Purpose: Gets a grade by CourseId from the database
     *  @param: CourseModel containing the courseId to check in the database
     *  @return: List<GradeModel> returning the complete details of the grades of the course
     */     
    public List<GradeModel> getAllGradesByCourse(CourseModel inputCourse){
        GradeModelMeta gradeModelMeta;
        List<GradeModel> gradeModelList;
        
        gradeModelMeta= GradeModelMeta.get();        
        gradeModelList= Datastore.query(gradeModelMeta)
                        .filter(gradeModelMeta.courseId.equal(inputCourse.getCourseId()))
                        .asList();                      
                        
        return gradeModelList;
    }

    public void updateGrade(GradeModel inputGrade){
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(inputGrade);
        tx.commit();  
    }
    
    
    
    /*
     *  Purpose: Gets a grade by AccountId from the database
     *  @param: GradeModel to check in the database
     *  @return: List<GradeModel> returning the complete details of the grade of the account
     */        
    public List<GradeModel> getAllGradesByAccountId (AccountModel inputAccount){
        GradeModelMeta gradeModelMeta;
        List<GradeModel> gradeModelList;
        
        gradeModelMeta= GradeModelMeta.get();        
        gradeModelList= Datastore.query(gradeModelMeta)
                        .filter(gradeModelMeta.accountId.equal(inputAccount.getAccountId()))
                        .asList();                      
                        
        return gradeModelList;
    }

    
    public List<GradeModel> getAllGrades(){
        GradeModelMeta gradeModelMeta;
        List<GradeModel> gradeModelList;
        
        gradeModelMeta= GradeModelMeta.get();        
        gradeModelList= Datastore.query(gradeModelMeta)
                        .asList();                      
                        
        return gradeModelList;        
    }
}
