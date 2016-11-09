/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 22, 2016 - created BestStudentDao File (Dave)
 */
package btg.dao;

import java.util.List;

import org.slim3.datastore.Datastore;

import btg.meta.BestStudentModelMeta;
import btg.model.BestStudentModel;


public class BestStudentDao {
    
    
    
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
                    .filter(bestStudentModelMeta.id.equal(inputBestStudent.getId()))
                    .asSingle();  
        
        return bestStudentModel;
    }
}
