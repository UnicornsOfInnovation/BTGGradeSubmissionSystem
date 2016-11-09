/*
 * 
 * Created by: Christian Dave B. Baclayon
 * Developed by:
 *  Development History:
 *     October 18, 2016 - created the CourseController file (Dave)
 *                      - created the insertCourse(), updateCourse(), deleteCourse(), 
 *                        getCourseById() (Dave)
 *     October 23, 2016 - created the getAllCoursesController() (Dave)
 *     October 26, 2016 - added "start" "end" in all methods (Dave)
 *                      - modified Error handling messages (Dave)
 *     November 9, 2016 - reviewed the Coding Standards for this file (Dave)
 */

package btg.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONException;
import org.slim3.repackaged.org.json.JSONObject;
import org.slim3.util.RequestMap;

import btg.common.GlobalConstants;
import btg.dto.CourseDto;
import btg.service.CourseService;


public class CourseController extends Controller{

    
    CourseService courseService = new CourseService();      //used to access methods in CourseService or database
    
    
    
    
    /*
     * (non-Javadoc)
     * @see org.slim3.controller.Controller#run()
     * 
     * Purpose: This function is called upon the $http.post() command called by the front-end. 
     *          Function calls happen depending on the "action" attribute received by this Controller
     *          from the front-end. Different functions are then called depending on the action attribute.
     * @param: none
     * @return: Navigation
     */
    @Override
    protected Navigation run() throws Exception {
        System.out.println("CourseController.run():"+ "start");
        
        String action;
        JSONObject jsonObject;              //holds the data passed from the front-end
        CourseDto courseDto;                //holds the course retrieved from the back-end
        List<CourseDto> courseDtoList;      //holds the list of courses retrieved from the database
        
        jsonObject = null;
        courseDto = new CourseDto();
        courseDtoList = null;
        
        try{
            jsonObject = new JSONObject(new RequestMap(this.request));
            action = jsonObject.getString("action");
            if(action.equalsIgnoreCase("InsertCourse")){
                courseDto = insertCourseController(jsonObject);
            } else if(action.equalsIgnoreCase("UpdateCourse")){
                courseDto = updateCourseController(jsonObject);
            } else if(action.equalsIgnoreCase("DeleteCourse")){
                courseDto = deleteCourseController(jsonObject);
            } else if(action.equalsIgnoreCase("GetCourseById")){
                courseDto = getCourseByIdController(jsonObject);
                jsonObject.put("courseDto", courseDto);
            } else if(action.equalsIgnoreCase("GetAllCourses")){
                courseDtoList = getAllCoursesController();
                for(CourseDto course : courseDtoList){
                    System.out.println("Course-->>"+course.getYearLevel());
                }
                jsonObject.put("courseDtoList", courseDtoList);
            }
            System.out.print(courseDto); 
        }catch (Exception e){
            System.out.println("Exception Found in run() in CourseController:");
            e.printStackTrace();
            // Adds an error message if there exists.
            courseDto.addError(GlobalConstants.ERR_SERVER_CONTROLLER_PREFIX + e.getMessage());
            // initialize the json object that will be passed as response.
            if (null == jsonObject) {
                jsonObject = new JSONObject();
            }
        }
        
        // add the error message to the json object.
        jsonObject.put("errorList", courseDto.getErrorList());
        
        // set the type of response.
        response.setContentType(GlobalConstants.SYS_CONTENT_TYPE_JSON);
        // send the response back to the JS file.
        response.getWriter().write(jsonObject.toString());
        
        System.out.println("CourseController.run():"+ "end");
        return null;
    }

    
    
    

    /*
     * Purpose: Receives the jsonObject when it is the function to be called. It is used to
     *          store a new account by calling the insertAccount in Service.
     * @param: JSONObject - the jsonObject passed by the client
     * @return AccountDto - holds the error list if there are any errors
     */
    public CourseDto insertCourseController(JSONObject jsonObject){
        System.out.println("CourseController.insertCourseController():"+ "start");
        
        CourseDto courseDto;        //holds the course that will be inserted to the database
        String courseType;
        
        courseDto = new CourseDto();    
        
        try {
            courseType = jsonObject.getString("courseType");
            courseDto.setCourseCode(jsonObject.getString("courseCode"));             
            courseDto.setCourseName(jsonObject.getString("courseName"));
            courseDto.setCourseUnits(Integer.parseInt(jsonObject.getString("courseUnits")));
            courseDto.setStatus(true);
            courseDto.setCourseType(courseType); 
            if("major".equalsIgnoreCase(courseType)){
                courseDto.setStrand(jsonObject.getString("strand"));
                courseDto.setYearLevel(Integer.parseInt(jsonObject.getString("yearLevel")));
            }
            System.out.println(courseDto.getCourseCode());
            System.out.println(courseDto.getCourseName());
            System.out.println(courseDto.getCourseUnits());
            System.out.println(courseDto.getCourseType());
            System.out.println(courseDto.getStrand());
            System.out.println(courseDto.getYearLevel());

            courseDto = courseService.insertCourse(courseDto);               
        } catch (JSONException e) {
            System.out.println("Exception Found in insertCourseController() in CourseController:");
            e.printStackTrace();
            courseDto.addError(e.toString());
        }
        
        System.out.println("CourseController.insertCourseController():"+ "end");
        return courseDto;
    }
    
    
    
    
    
    /*
     * Purpose: Receives the jsonObject when it is the function to be called. It is used to
     *          store update an account by calling the updateAccount in Service.
     * @param: JSONObject - the jsonObject passed by the client
     * @return AccountDto - holds the error list if there are any errors
     */
    public CourseDto updateCourseController(JSONObject jsonObject){
        System.out.println("CourseController.updateCourseController():"+ "start");

        CourseDto courseDto;        //holds the course that will be updated from the database
        String courseType;
        
        courseDto = new CourseDto();
        
        try{
            courseType = jsonObject.getString("courseType");
            courseDto.setCourseId(Long.parseLong(jsonObject.getString("id")));
            courseDto.setId(Long.parseLong(jsonObject.getString("id")));
            courseDto.setCourseCode(jsonObject.getString("courseCode"));             
            courseDto.setCourseName(jsonObject.getString("courseName"));
            courseDto.setCourseUnits(Integer.parseInt(jsonObject.getString("courseUnits")));
            courseDto.setStatus(true);
            courseDto.setCourseType(courseType); 
            if("major".equalsIgnoreCase(courseType)){
                courseDto.setStrand(jsonObject.getString("strand"));
                courseDto.setYearLevel(Integer.parseInt(jsonObject.getString("yearLevel")));

            }
           courseDto = courseService.updateCourse(courseDto); 
            
        } catch (Exception e) {
            System.out.println("Exception found in updateCourseController() in CourseController:");
            e.printStackTrace();
            courseDto.addError(e.toString());
        }
        
        System.out.println("CourseController.updateCourseController():"+ "end");
        return courseDto;
    }
    
    
    
    
    
    /*
     * Purpose: Receives the jsonObject when it is the function to be called. It is used to
     *          set status of an account to false and calls the deleteAccount in Service.
     * @param: JSONObject - the jsonObject passed by the client
     * @return AccountDto - holds the error list if there are any errors
     */
    public CourseDto deleteCourseController(JSONObject jsonObject){
        System.out.println("CourseController.deleteCourseController():"+ "start");
        
        CourseDto courseDto;        //holds the course that will be deleted from the database
        
        courseDto = new CourseDto();
        
        try {
            courseDto.setId(Long.parseLong(jsonObject.getString("id")));
            courseDto = courseService.deleteCourse(courseDto);
        } catch (Exception e) {
            System.out.println("Exception found in deleteCourseController() in CourseController:");
            e.printStackTrace();
            courseDto.addError(e.toString());
        }
        
        System.out.println("CourseController.deleteCourseController():"+ "end");
        return courseDto;
    }
    
    
    
    
    
    /*
     * Purpose: Receives the jsonObject when it is the function to be called. It is used to
     *          retrieve a course by calling the getCourseById in Service.
     * @param: JSONObject - the jsonObject passed by the client
     * @return CourseDto - holds the details of the course if successful
     *                      or holds the error list if there are any errors
     */
    public CourseDto getCourseByIdController(JSONObject jsonObject){
        System.out.println("CourseController.getCourseByIdController():"+ "start");

        CourseDto courseDto;        //holds the course that will be retrieved from the database
        
        courseDto = new CourseDto();
        
        try {
            courseDto.setId(Long.parseLong(jsonObject.getString("id")));
            courseDto = courseService.getCourseById(courseDto);
        } catch (Exception e){
            System.out.println("Exception found in getCourseByIdController() in CourseController:");
            e.printStackTrace();
            courseDto.addError(e.toString());
        }
        
        System.out.println("CourseController.getCourseByIdController():"+ "end");
        return courseDto;
    }
    
    
    
    
    
    /*
     * Purpose: It is used to return the list of all courses in the database by calling the 
     *          getAllCourses method in the Service.
     * @param: none
     * @return List<CourseDto> - holds the details of all the courses if successful, prints the error
     *                          in cmd if not.
     */
    public List<CourseDto> getAllCoursesController(){
        System.out.println("CourseController.getAllCoursesController():"+ "start");
        
        List<CourseDto> courseDtoList;      //holds the list of courses that will is retrieved from the database
        
        courseDtoList = null;
        try{
            courseDtoList = courseService.getAllCourses();
        } catch (Exception e){
            System.out.println("Exception found in getAllCoursesController() in CourseController:");
            System.out.println(e.toString());            
        }
        
        System.out.println("CourseController.getAllCoursesController():"+ "end");
        return courseDtoList;
    }
}
