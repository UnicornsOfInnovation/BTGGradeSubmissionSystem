/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 22, 2016 - created BestStudentDao File (Dave)
 */
package btg.dao;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import btg.meta.BestStudentModelMeta;
import btg.model.AccountModel;
import btg.model.BestStudentModel;


public class BestStudentDao {
    
    
    public void insertBestStudent(BestStudentModel inputBestStudent){
        Transaction tx = Datastore.beginTransaction();
        Key parentKey;
        Key key;
        // creating key and ID for the new entity
        parentKey = KeyFactory.createKey("BestStudent", inputBestStudent.getCourseName()+ inputBestStudent.getCourseId());
        key = Datastore.allocateId(parentKey, "BestStudentModel");
        
        // setting the 'key' and 'id' of the model
        inputBestStudent.setKey(key);
        inputBestStudent.setId(key.getId());
        inputBestStudent.setBestStudentId(key.getId());
        
        // adding the item to the datastore
        Datastore.put(inputBestStudent);
        
        tx.commit();
        
    }
    
    
    public void updateBestStudent(BestStudentModel inputBestStudent){
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(inputBestStudent);
        tx.commit();        
    }
    
    
    
    
    public List<BestStudentModel> getAllBestStudents(){
        List<BestStudentModel> bestStudentModelList;
        BestStudentModelMeta bestStudentModelMeta = BestStudentModelMeta.get();
        
        bestStudentModelList = Datastore.query(bestStudentModelMeta)
                        .asList();
        
        return bestStudentModelList;        
    }

    public BestStudentModel getBestStudentByCourseId (BestStudentModel inputBestStudent){
        BestStudentModelMeta bestStudentModelMeta;
        BestStudentModel bestStudentModel;
        
        bestStudentModelMeta = BestStudentModelMeta.get();
        bestStudentModel = Datastore.query(bestStudentModelMeta)
                    .filter(bestStudentModelMeta.courseId.equal(inputBestStudent.getCourseId()))
                    .asSingle();  
        
        return bestStudentModel;
    }
}
