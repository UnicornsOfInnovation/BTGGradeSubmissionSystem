/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 22, 2016 - created GradeController File (Dave)
 *                      - run(), submitGradeController(), updateGradeController(), getAllStudentGradesByCourseController() (Dave)
 *     October 23, 2016 - added comments to the purpose of each function (Dave)
 *                      - added 'accountService.computeGPA(gradesArray);' in submitGradeController (Dave)
 *                      - added 'accountService.computeGPA(gradesArray);' in updateGradeController (Dave)
 *     November 18, 2016- added getAllGrades() (Dave)
 */
package btg.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import org.slim3.repackaged.org.json.JSONObject;
import org.slim3.util.RequestMap;


import btg.common.GlobalConstants;
import btg.dao.GradeDao;
import btg.dto.BestStudentDto;
import btg.dto.CourseDto;
import btg.dto.GradeDto;
import btg.dto.StudentGradeDto;
import btg.service.AccountService;
import btg.service.BestStudentService;
import btg.service.CourseService;
import btg.service.GradeService;

public class GradeController extends Controller {
    
        GradeService gradeService = new GradeService();
    
        /* 
         * Purpose: This function is called upon the $http.post() command called by the front-end. 
         *          Function calls happen depending on the "action" attribute received by this Controller
         *          from the front-end. Different functions are then called depending on the action attribute.
         * @param: none
         * @return: Navigation
         */
        @Override
        protected Navigation run() throws Exception {
            String action;
            JSONObject jsonObject;
            GradeDto gradeDto;
            List<StudentGradeDto> studentGradeDto;
            List<GradeDto> gradeDtoList;
            
            gradeDtoList = null;
            studentGradeDto = null;       
            jsonObject = null;
            gradeDto = new GradeDto();

            try{
                jsonObject = new JSONObject(new RequestMap(this.request));
                System.out.println("REQUEST-->"+this.request);
                action = jsonObject.getString("action");
                if(action.equals("SubmitGrade")){
                    gradeDto = submitGradeController(jsonObject);                    
                } else if(action.equals("ListStudentGradesByCourse")){
                    studentGradeDto = getAllStudentGradesByCourseController(jsonObject);
                    jsonObject.put("studentGradesList", studentGradeDto);
                } else if(action.equals("UpdateGrade")){
                    gradeDto = updateGradeController(jsonObject);
                } else if(action.equals("GetAllGrades")){
                    gradeDtoList = getAllGradesController();
                    jsonObject.put("allGrades", gradeDtoList);
                }
            } catch (Exception e) {
                // Adds an error message if there exists.
                gradeDto.addError(GlobalConstants.ERR_SERVER_CONTROLLER_PREFIX + e.getMessage());
                // initialize the json object that will be passed as response.
                if (null == jsonObject) {
                    jsonObject = new JSONObject();
                }            
            }
            // add the error message to the json object.
            jsonObject.put("errorList", gradeDto.getErrorList());
            
            // set the type of response.
            response.setContentType(GlobalConstants.SYS_CONTENT_TYPE_JSON);
            // send the response back to the JS file.
            response.getWriter().write(jsonObject.toString());
            
            return null;
        }
        
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          store retrieve the array of objects of grades and insert each element in the array to the database.
         * @param: JSONObject - the jsonObject passed by the client that contains the array from client side
         * @return GradeDto - holds the error list if there are any errors
         */            
        public GradeDto submitGradeController(JSONObject jsonObject){
            GradeDto gradeDto;
            GradeDao gradeDao;
            GradeDto tempGradeDto;
            BestStudentDto bestStudentDto;
            BestStudentController bestStudentController;
            BestStudentService bestStudentService;
            CourseDto courseDto;
            CourseService courseService;
            JSONArray gradesArray;
            AccountService accountService;
            int ctr;
            List<JSONObject> studentDtoList;
            List<GradeDto> gradeDtoList;
            
            bestStudentService = new BestStudentService();
            gradeDao = new GradeDao();
            gradeDtoList = new ArrayList<GradeDto>();
            studentDtoList = new ArrayList<JSONObject>();
            gradesArray = null;
            accountService = new AccountService();
            gradeDto = null;
            bestStudentDto = null;
            bestStudentController = new BestStudentController();
            
            //The JSONArray is in reference to RegisterBentoController of 05_Quiz_Output

            try{
                gradesArray = jsonObject.getJSONArray("gradesArray");  
                System.out.println("jsonarray-->>"+gradesArray.toString());
                System.out.println("jsonarray2-->>"+gradesArray.getString(0));
                for (int i = 0; i < gradesArray.length(); i++){
                    JSONObject jo = new JSONObject(gradesArray.getString(i));
                    GradeDto grade = new GradeDto();
                    grade.setAccountId(jo.getLong("accountId"));
                    grade.setCourseId(jo.getLong("courseId"));
                    grade.setGrade(jo.getDouble("grade"));
                    grade.setGradeId(jo.getLong("gradeId"));
                    gradeDtoList.add(grade);
                }
                
                for(GradeDto gradeEntry : gradeDtoList){
                    tempGradeDto = new GradeDto();
                    tempGradeDto.setAccountId(gradeEntry.getAccountId());
                    tempGradeDto.setCourseId(gradeEntry.getCourseId());
                    tempGradeDto.setGradeId(gradeEntry.getGradeId());

                    try{
                        gradeDto = gradeService.getGradeById(tempGradeDto);
                        gradeDto.setGrade(gradeEntry.getGrade());
                        gradeService.updateGrade(gradeDto);
                    } catch (Exception e){
                        System.out.println("Exception found in Submit Grade Controller!");
                        e.printStackTrace();
                        //accountService.computeGPA(gradesArray.getJSONObject(ctr));
                    }

                }
                
                bestStudentDto = new BestStudentDto();
                bestStudentDto.setBestStudentId(Long.parseLong(jsonObject.getString("bestStudentId")));
                bestStudentDto.setAccountId(Long.parseLong(jsonObject.getString("accountId")));
                bestStudentDto.setCourseId(Long.parseLong(jsonObject.getString("courseId")));                
                courseDto = new CourseDto();
                courseDto.setCourseId(Long.parseLong(jsonObject.getString("courseId")));
                courseService = new CourseService();
                courseDto = courseService.getCourseById(courseDto);
                bestStudentDto.setCourseName(courseDto.getCourseName());
                bestStudentDto.setFirstName(jsonObject.getString("firstName"));
                bestStudentDto.setLastName(jsonObject.getString("lastName"));
                bestStudentDto.setGrade(Double.parseDouble(jsonObject.getString("grade")));
                bestStudentDto.setGradeId(Long.parseLong(jsonObject.getString("gradeId")));
                
                bestStudentService.updateBestStudent(bestStudentDto);
                

                //it is assumed that the account id of the best student is submitted as well
                //bestStudentDto = bestStudentController.insertBestStudentController(jsonObject);
            } catch(Exception e){
                gradeDto = new GradeDto();
                gradeDto.addError(e.toString());
                e.printStackTrace();
            }
            
            return gradeDto;
        }
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          return a JSONArray with all student grades in it depending on the course of the teacher.
         * @param: JSONObject - the jsonObject passed by the client that contains the courseId
         * @return List<StudentGradeDto> - the list the holds all the student grades queried
         */    
        public List<StudentGradeDto> getAllStudentGradesByCourseController(JSONObject jsonObject){
            List<StudentGradeDto> studentGradeList;
            Long courseId;
            
            studentGradeList = null;
            try{
                courseId = Long.parseLong(jsonObject.getString("courseId"));
                studentGradeList = gradeService.getAllStudentGradesByCourse(courseId);    
            } catch (Exception e){
                System.out.println("This is an exception in getAllStudentGradesByCourseController in GradeController:");
                e.printStackTrace();
            }
            return studentGradeList;    
        }
        
        
        /*
         * Purpose: Receives the jsonObject when it is the function to be called. It is used to
         *          store retrieve the array of objects of grades and update each element of the array to the database.
         * @param: JSONObject - the jsonObject passed by the client that contains the array from client side
         * @return GradeDto - holds the error list if there are any errors
         */    
        public GradeDto updateGradeController (JSONObject jsonObject){
            GradeDto gradeDto;
            JSONArray gradesArray;
            int ctr;
            AccountService accountService;
            
            accountService = new AccountService();
            gradeDto = null;
            //The JSONArray is in reference to RegisterBentoController of 05_Quiz_Output
            try{
                gradesArray = jsonObject.getJSONArray("gradesArray");                     
                for(ctr=0; ctr < gradesArray.length(); ctr++){
                    gradeDto = new GradeDto();
                    gradeDto.setAccountId(Long.parseLong(gradesArray.getJSONObject(ctr).getString("accountId")));
                    gradeDto.setCourseId(Long.parseLong(gradesArray.getJSONObject(ctr).getString("courseId")));
                    gradeDto.setGrade(Double.parseDouble(gradesArray.getJSONObject(ctr).getString("grade")));
                    gradeDto.setStatus(true);
                    //submits the grade one by one and not as a list
                    try{
                        gradeService.updateGrade(gradeDto);
                      //computes GPA of students after every submit
                        accountService.computeGPA(gradesArray.getJSONObject(ctr));
                    } catch (Exception e){
                        gradeDto.addError(e.toString());
                    }
                }                    
                
            } catch(Exception e){
                gradeDto = new GradeDto();
                gradeDto.addError(e.toString());
            }
            return null;
        }
      
        
        
        public List<GradeDto> getAllGradesController (){
            System.out.println("getAllGrade + Start");
            List<GradeDto> gradeDtoList;
            
            gradeDtoList = gradeService.getAllGrades();
            System.out.println("GetAllGrade + End");
            return gradeDtoList;
           
        }
        
    
}
