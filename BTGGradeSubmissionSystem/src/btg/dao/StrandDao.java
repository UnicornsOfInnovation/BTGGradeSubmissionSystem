/*
 * Developer: Christian Dave B. Baclayon
 * Development History:
 *      October 25, 2016 - created StrandDao (Dave)
 *                       - insertStrand(), getAllStrands() (Dave)
 */

package btg.dao;

import java.util.List;

import org.slim3.datastore.Datastore;

import btg.meta.StrandModelMeta;
import btg.model.StrandModel;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class StrandDao {

    
    
    /*
     * Purpose: Used to insert a new strand to the database
     * @param: StrandModel that holds the details of the strand
     * @return: void - nothing to return
     */
    
    public void insertStrand(StrandModel inputStrand){
        Transaction tx;
        Key parentKey;
        Key key;
        
        tx = Datastore.beginTransaction();
       
        // creating key and ID for the new entity
        parentKey = KeyFactory.createKey("Strand", inputStrand.getStrandName());
        key = Datastore.allocateId(parentKey, "StrandModel");
        
        // setting the 'key' and 'id' of the model
        inputStrand.setKey(key);
        inputStrand.setId(key.getId());
        inputStrand.setStrandId(key.getId());
        
        Datastore.put(inputStrand);
        tx.commit();
        System.out.println("Success Insertion!");
    }
    
    
    public List<StrandModel> getAllStrands(){
        List<StrandModel> strandModelList;
        StrandModelMeta strandModelMeta;
        
        strandModelMeta = StrandModelMeta.get();
        strandModelList = Datastore.query(strandModelMeta)
                .asList();

        return strandModelList;
    }
}
