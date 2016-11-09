/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 18, 2016 - Created the DAO file (Dave)
 *                      - Created the insertCourse(), updateCourse(), deleteCourse(), getCourseByName() (Dave)
 *     October 19, 2016 - Created the getCourseById() (Dave)
 *     October 23, 2016 - Created the getAllCourses() (Dave)
 *     November 9, 2016 - reviewed the Coding Standards for this file (Dave)
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
        System.out.println("CourseDao.insertCourse():"+ "start");

        Transaction tx;     //used to create transaction for insert
        
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
        
        System.out.println("CourseDao.insertCourse():"+ "end");

    }
    
    
    
    
    
    /*
     * Purpose: Updates a course to the database
     * @param: CourseModel to be updated to the database
     * @return: void
     */
    public void updateCourse(CourseModel inputCourse){
        System.out.println("CourseDao.updateCourse():"+ "start");

        Transaction tx;     //used to create transaction for insert
        
        tx = Datastore.beginTransaction();
        
        Datastore.put(inputCourse);
        tx.commit();       
        
        System.out.println("CourseDao.udpateCourse():"+ "end");

    }
    
    
    
    
    
    /*
     * Purpose: Deletes a course to the database
     * @param: CourseModel to be soft deleted from the database
     * @return: void
     */
    public void deleteCourse(CourseModel inputCourse){
        System.out.println("CourseDao.deleteCourse():"+ "start");

        Transaction tx;     //used to create transaction for insert
        
        tx = Datastore.beginTransaction();
        
        Datastore.put(inputCourse);
        tx.commit();
        
        System.out.println("CourseDao.deleteCourse():"+ "end");
    }
    
    
    
    
    
    /*
     * Purpose: Gets a course from the database by the Name. This is used for inserting a new course in the database
     * @param: CourseModel that stores the ID
     * @return: CourseModel (stores the complete course details from datastore)
     */
    public CourseModel getCourseByName(CourseModel inputCourse){
        System.out.println("CourseDao.getCourseByName():"+ "start");

        CourseModelMeta courseModelMeta;        //MetaClass for accessing the database
        CourseModel courseModel;                //object to hold course retrieved from the database
        
        courseModelMeta = CourseModelMeta.get();
        courseModel = Datastore.query(courseModelMeta)
                .filter(courseModelMeta.courseName.equal(inputCourse.getCourseName()))
                .asSingle();
        
        System.out.println("CourseDao.getCourseByName():"+ "end");
        return courseModel;
    }

    
    
    
    
    /*
     * Purpose: Gets a course from the database by the Name. This is used for inserting a new course in the database
     * @param: CourseModel that stores the ID
     * @return: CourseModel (stores the complete course details from datastore)
     */
    public CourseModel getCourseById(CourseModel inputCourse){
        System.out.println("CourseDao.getCourseById():"+ "start");

        CourseModelMeta courseModelMeta;        //MetaClass for accessing the database
        CourseModel courseModel;                //object to hold course retrieved from the database
        
        courseModelMeta = CourseModelMeta.get();
        courseModel = Datastore.query(courseModelMeta)
                .filter(courseModelMeta.id.equal(inputCourse.getId()))
                .asSingle();

        System.out.println("CourseDao.getCourseById():"+ "end");
        return courseModel;
    }
    
    
    
    
    
    /*
     * Purpose: Gets all the courses in the database. 
     * @param: none
     * @return: List<CourseModel> (stores the complete course details of all courses)
     */    
    public List<CourseModel> getAllCourses(){
        System.out.println("CourseDao.getAllCourses():"+ "start");

        CourseModelMeta courseModelMeta;        //MetaClass for accessing the database
        List<CourseModel> courseModelList;      //object to hold the list of courses retrieved from the database
                
        courseModelMeta = CourseModelMeta.get();
        courseModelList = Datastore.query(courseModelMeta).filter((courseModelMeta.status.equal(true)))
                .asList();

        System.out.println("CourseDao.getAllCourses():"+ "end");
        return courseModelList;
    }
}
