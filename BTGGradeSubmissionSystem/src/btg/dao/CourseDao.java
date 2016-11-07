/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 18, 2016 - Created the DAO file (Dave)
 *                      - Created the insertCourse(), updateCourse(), deleteCourse(), getCourseByName() (Dave)
 *     October 19, 2016 - Created the getCourseById() (Dave)
 *     October 23, 2016 - Created the getAllCourses() (Dave)
 */


package btg.dao;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import btg.meta.CourseModelMeta;
import btg.model.CourseModel;

public class CourseDao {
    /*
     *  Purpose: Inserts a course to the database
     *  @param: CourseModel to insert to the database
     *  @return: void
     */
    public void insertCourse(CourseModel inputCourse){
        Transaction tx;
        
        tx = Datastore.beginTransaction();
        // creating key and ID for the new entity
        Key parentKey = KeyFactory.createKey("Course", inputCourse.getCourseName());
        Key key = Datastore.allocateId(parentKey, "CourseModel");
        
        // setting the 'key' and 'id' of the model
        inputCourse.setKey(key);
        inputCourse.setId(key.getId());
        inputCourse.setCourseId(key.getId());
        
        // adding the item to the datastore
        Datastore.put(inputCourse);
        
        tx.commit();
    }
    
    
    
    /*
     * Purpose: Updates a course to the database
     * @param: CourseModel to be updated to the database
     * @return: void
     */
    public void updateCourse(CourseModel inputCourse){
        Transaction tx;
        
        tx = Datastore.beginTransaction();
        Datastore.put(inputCourse);
        tx.commit();           
    }
    
    
    
    /*
     * Purpose: Deletes a course to the database
     * @param: CourseModel to be soft deleted from the database
     * @return: void
     */
    public void deleteCourse(CourseModel inputCourse){
        Transaction tx;
        
        tx= Datastore.beginTransaction();
        Datastore.put(inputCourse);
        tx.commit();
        
    }
    
    
    
    
    /*
     * Purpose: Gets a course from the database by the Name. This is used for inserting a new course in the database
     * @param: CourseModel that stores the ID
     * @return: CourseModel (stores the complete course details from datastore)
     */
    public CourseModel getCourseByName(CourseModel inputCourse){
        CourseModelMeta courseModelMeta;
        CourseModel courseModel;
        
        courseModelMeta = CourseModelMeta.get();
        courseModel = Datastore.query(courseModelMeta)
                .filter(courseModelMeta.courseName.equal(inputCourse.getCourseName()))
                .asSingle();

        return courseModel;
    }

    
    /*
     * Purpose: Gets a course from the database by the Name. This is used for inserting a new course in the database
     * @param: CourseModel that stores the ID
     * @return: CourseModel (stores the complete course details from datastore)
     */
    
    public CourseModel getCourseById(CourseModel inputCourse){
        CourseModelMeta courseModelMeta;
        CourseModel courseModel;
        
        courseModelMeta = CourseModelMeta.get();
        courseModel = Datastore.query(courseModelMeta)
                .filter(courseModelMeta.id.equal(inputCourse.getId()))
                .asSingle();

        return courseModel;
    }
    
    
    /*
     * Purpose: Gets all the courses in the database. 
     * @param: none
     * @return: List<CourseModel> (stores the complete course details of all courses)
     */
    
    public List<CourseModel> getAllCourses(){
        CourseModelMeta courseModelMeta;
        List<CourseModel> courseModelList;
        
        courseModelMeta = CourseModelMeta.get();
        courseModelList = Datastore.query(courseModelMeta).filter((courseModelMeta.status.equal(true)))
                .asList();

        return courseModelList;
    }
}
