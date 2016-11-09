/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 18, 2016 - Created the Service file (Dave)
 *                      - Created the insertCourse(), updateCourse(), deleteCourse(), getCourseById() (Dave)
 *     October 23, 2016 - Created the getAllCourses() (Dave)
 *     November 9, 2016 - reviewed the Coding Standards for this file (Dave)
 * 
 */

package btg.service;

import java.util.ArrayList;
import java.util.List;

import btg.common.GlobalConstants;
import btg.dao.CourseDao;
import btg.dto.CourseDto;
import btg.model.CourseModel;

public class CourseService {
    
    
    CourseDao courseDao = new CourseDao();  //used to access methods for Data Access Objects or database
    
    
    
    
    
    /*
     *  Purpose: Checks if Course is not yet in the database by checking the courseID. 
     *           If everything is unique, stores to model and calls dao.            
     *  @param: CourseDto that will be stored to the database
     *  @return: CourseDto that will hold the error list if exception occurs.
     */
    public CourseDto insertCourse(CourseDto inputCourse){
        System.out.println("CourseService.insertCourse():"+ "start");
        
        CourseModel courseModel;        //used to store data from dto and serves as model for the database
        
        courseModel = new CourseModel();
        
        courseModel.setCourseCode(inputCourse.getCourseCode());
        courseModel.setCourseType(inputCourse.getCourseType());
        courseModel.setCourseName(inputCourse.getCourseName());
        courseModel.setStatus(inputCourse.getStatus());
        courseModel.setStrand(inputCourse.getStrand());
        courseModel.setCourseUnits(inputCourse.getCourseUnits());
        courseModel.setYearLevel(inputCourse.getYearLevel());

        try {
            if (null == courseDao.getCourseByName(courseModel)) {
                try {
                    courseDao.insertCourse(courseModel);
                } catch (Exception e) {
                    System.out.println("Exception Found in insertCourse() in CourseService:");
                    e.printStackTrace();
                    inputCourse.addError(GlobalConstants.ERR_ENTRY_CANNOT_ADD);
                }
            } else {
                System.out.println("Exception Found in insertCourse() in CourseService:");
                inputCourse.addError(GlobalConstants.ERR_ENTRY_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            System.out.println("Exception Found in insertCourse() in CourseService:");
            e.printStackTrace();
            inputCourse.addError(GlobalConstants.ERR_DB_EXCEPTION);
        }        
        
        System.out.println("CourseService.insertCourse():"+ "end");
        return inputCourse;
    }
    
    
    
    
    
    /*
     *  Purpose: Checks if Course is already in the database by checking the courseID. 
     *           If it exists, stores to model and calls dao.            
     *  @param: CourseDto that will be updated to the database
     *  @return: CourseDto that will hold the error list if exception occurs.
     */
    public CourseDto updateCourse(CourseDto inputCourse){
        System.out.println("CourseService.updateCourse():"+ "start");
        
        CourseModel courseModel;        //used to store data from dto and serves as model for the database
        CourseModel temp;               //used to store the data retrieved from database in checking if course exists.
        
        courseModel = new CourseModel();
        
        courseModel.setCourseCode(inputCourse.getCourseCode());
        courseModel.setCourseId(inputCourse.getCourseId());
        courseModel.setId(inputCourse.getId());
        courseModel.setCourseType(inputCourse.getCourseType());
        courseModel.setCourseName(inputCourse.getCourseName());
        courseModel.setStatus(inputCourse.getStatus());
        courseModel.setStrand(inputCourse.getStrand());
        courseModel.setCourseUnits(inputCourse.getCourseUnits());
        courseModel.setYearLevel(inputCourse.getYearLevel());
        

        try {
            temp = new CourseModel();
            temp = courseDao.getCourseById(courseModel);
            if (null != temp || true == courseModel.getStatus()) {
                try {
                    courseModel.setKey(temp.getKey());
                    courseDao.updateCourse(courseModel);
                } catch (Exception e) {
                    System.out.println("Exception Found in updateCourse() in CourseService:");
                    e.printStackTrace();
                    inputCourse.addError(GlobalConstants.ERR_ENTRY_CANNOT_UPDATE);
                }
            } else {
                inputCourse.addError(GlobalConstants.ERR_ENTRY_NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Exception Found in updateCourse() in CourseService:");
            e.printStackTrace();
            inputCourse.addError(GlobalConstants.ERR_DB_EXCEPTION);
        }
        
        System.out.println("CourseService.updateCourse():"+ "end");       
        return inputCourse;
    }
    
    
    
    
    
    /*
     *  Purpose: Checks if Course is in the database by checking the courseID. 
     *           If it exists, soft delete by setting the status to the model and calls dao.            
     *  @param: CourseDto that will be deleted from the database
     *  @return: CourseDto that will hold the error list if exception occurs.
     */
    public CourseDto deleteCourse(CourseDto inputCourse){
        System.out.println("CourseService.deleteCourse():"+ "start");

        CourseModel courseModel;        //used to store data from dto and serves as model for the database
        
        courseModel = new CourseModel();
        
        courseModel.setId(inputCourse.getId());
        courseModel = courseDao.getCourseById(courseModel);
        try{
            if(null != courseModel){
                try{
                    courseModel.setStatus(false);
                    courseDao.deleteCourse(courseModel);
                } catch(Exception e){
                    System.out.println("Exception Found in deleteCourse() in CourseService:");
                    e.printStackTrace();
                    inputCourse.addError(GlobalConstants.ERR_ENTRY_CANNOT_DELETE);   
                }
            } else {
                inputCourse.addError(GlobalConstants.ERR_ENTRY_NOT_FOUND);
            }
        } catch (Exception e){
            System.out.println("Exception Found in deleteCourse() in CourseService:");
            e.printStackTrace();
            inputCourse.addError(GlobalConstants.ERR_DB_EXCEPTION);
        }
        
        System.out.println("CourseService.deleteCourse():"+ "end");
        return inputCourse;
    }
    
    
    
    
    
    /*
     *  Purpose: Checks if Course is in the database by checking the courseID. 
     *           If it exists, stores the retrieved course to the dto.            
     *  @param: CourseDto containing the ID
     *  @return: CourseDto that will hold the complete details of the Course or the error list if exception occurs.
     */
    public CourseDto getCourseById(CourseDto inputCourse){
        System.out.println("CourseService.getCourseById():"+ "start");

        CourseModel courseModel;        //used to store data from dto and serves as model for the database
        
        courseModel = new CourseModel();
        
        courseModel.setId(inputCourse.getId());
        
        try{
            courseModel = courseDao.getCourseById(courseModel);
            if(null != courseModel){
                inputCourse.setCourseCode(courseModel.getCourseCode());
                inputCourse.setCourseId(courseModel.getCourseId());
                inputCourse.setCourseType(courseModel.getCourseType());               
                inputCourse.setStatus(courseModel.getStatus());
                inputCourse.setCourseName(courseModel.getCourseName());
                inputCourse.setStrand(courseModel.getStrand());
                inputCourse.setCourseUnits(courseModel.getCourseUnits());            
            } else {
                inputCourse.addError(GlobalConstants.ERR_ENTRY_NOT_FOUND);
            }
        } catch (Exception e){
            System.out.println("Exception Found in getCourseById() in CourseService:");
            e.printStackTrace();
            inputCourse.addError(GlobalConstants.ERR_DB_EXCEPTION);
            
        }       
        
        System.out.println("CourseService.getCourseById():"+ "end");
        return inputCourse;
    }
    
    
    
    
    
    /*
     * Purpose: It is used to return the list of all courses in the database by calling the 
     *          getAllCourses method in the Dao.
     * @param: none
     * @return List<CourseDto> - holds the details of all the courses if successful, prints the error
     *                          in cmd if not.
     */
    public List<CourseDto> getAllCourses(){
        System.out.println("CourseService.getAllCourses():"+ "start");

        List<CourseDto> courseDtoList;          //stores the list of courses as a CourseDTO
        List<CourseModel> courseModelList;      //stores the list of courses as a CourseModel
        CourseDto courseDto;                    //used as a temporary value to transfer values from CourseModel to CourseDTO
        
        courseDtoList = new ArrayList<CourseDto>();
        
        try{
            courseModelList = courseDao.getAllCourses();
            for(CourseModel courseModel : courseModelList){
                courseDto = new CourseDto();
                courseDto.setCourseCode(courseModel.getCourseCode());
                courseDto.setCourseId(courseModel.getCourseId());
                courseDto.setId(courseModel.getId());
                courseDto.setCourseType(courseModel.getCourseType());
                courseDto.setCourseName(courseModel.getCourseName());
                courseDto.setStatus(courseModel.getStatus());
                courseDto.setStrand(courseModel.getStrand());
                courseDto.setCourseUnits(courseModel.getCourseUnits());
                courseDto.setYearLevel(courseModel.getYearLevel()); 
                courseDtoList.add(courseDto);
            }
            
        } catch (Exception e){
            System.out.println("Exception Found in getAllCourses() in CourseService:");
            e.printStackTrace();
        }    
        
        System.out.println("CourseService.getAllCourses():"+ "end");
        return courseDtoList;
    }
    
}
