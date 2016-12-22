/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 22, 2016 - created BestStudentController File (Dave)
 */
package btg.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;


import com.google.appengine.labs.repackaged.org.json.JSONObject;

import btg.common.GlobalConstants;
import btg.dto.BestStudentDto;
import btg.service.BestStudentService;

public class BestStudentsController extends Controller {

        BestStudentService bestStudentService = new BestStudentService();
        /*
         * (non-Javadoc)
         * @see org.slim3.controller.Controller#run()
         * PUT COMMENTS HERE
         */
        @Override
        protected Navigation run() throws Exception {
            String action;
            JSONObject jsonObject;
            List<BestStudentDto> bestStudentDtoList;
            BestStudentDto bestStudentDto;
            
            bestStudentDto = new BestStudentDto();
            jsonObject = null;
            bestStudentDtoList = null;
            try{
                jsonObject = new JSONObject(new RequestMap(this.request));
                action = jsonObject.getString("action");
                if(action.equalsIgnoreCase("GetAllBestStudents")){
                    bestStudentDtoList = getAllBestStudentsController();
                    System.out.println("test best1");
                    for(BestStudentDto x : bestStudentDtoList){
                        System.out.println("test best"+x.getLastName());
                    }
                    jsonObject.put("bestStudentList", bestStudentDtoList);
                } else if (action.equalsIgnoreCase("getBestStudentByCourseId")){
                    bestStudentDto = getBestStudentByCourseIdController(jsonObject);
                }  else if (action.equalsIgnoreCase("insertBestStudent")){
                    bestStudentDto = insertBestStudentController(jsonObject);
                } 
            }catch(Exception e){
                // Adds an error message if there exists.
                bestStudentDto.addError(GlobalConstants.ERR_SERVER_CONTROLLER_PREFIX + e.getMessage());
                // initialize the json object that will be passed as response.
                if (null == jsonObject) {
                    jsonObject = new JSONObject();
                }       
               
            }
            // add the error message to the json object.
            jsonObject.put("errorList", bestStudentDto.getErrorList());
            
            // set the type of response.
            response.setContentType(GlobalConstants.SYS_CONTENT_TYPE_JSON);
            // send the response back to the JS file.
            response.getWriter().write(jsonObject.toString());
            return null;
        }
        
        
        
        public List<BestStudentDto> getAllBestStudentsController (){
            List<BestStudentDto> bestStudentDtoList;
            
            bestStudentDtoList = null;
            try{
                bestStudentDtoList = bestStudentService.getAllBestStudents();
                for(BestStudentDto x : bestStudentDtoList){
                    System.out.println("test best"+x.getLastName());
                }
                
            } catch (Exception e){
                System.out.println("This error is in getAllBestStudents:");
                System.out.println(e.toString());               
            }
            return bestStudentDtoList;    
        }
        
        
        public BestStudentDto getBestStudentByCourseIdController (JSONObject jsonObject){
            BestStudentDto bestStudentDto;
            Long courseId;
            
            bestStudentDto = new BestStudentDto();
            try{
                courseId = Long.parseLong(jsonObject.getString("courseId"));
                bestStudentDto.setCourseId(courseId);
                bestStudentDto = bestStudentService.getBestStudentByCourseId(bestStudentDto);
            } catch (Exception e){
                bestStudentDto.addError(e.toString());
            }
            return null;
        }
        
        
        
        
        /*
         * IMPLEMENT THIS FUNCTION LATER. SABUTI SA NINYO NI ADI KUNG UNSA ANG JSON OBJECT NGA IPASS GIKAN SA TEACHER
         * PARA IMO MAPLANNED OUT ANG FOLLOWING:
         *  1. Nawng sa database for best student
         *  2. Name conventions and consistencies sa BestStudentDto
         */
        
        public BestStudentDto insertBestStudentController(JSONObject jsonObject){
              BestStudentDto bestStudentDto;
//            AccountDto accountDto;
//            
//            accountDto = new AccountDto();
              bestStudentDto = new BestStudentDto();
//            
//            try {
//                bestStudentDto.setAccountId(Long.parseLong(jsonObject.getString("accountId")));
//                bestStudentDto.setCourseId(Long.parseLong(jsonObject.getString("courseId")));
//                bestStudentDto.setCourseName(jsonObject.getString("courseName"));         
//                bestStudentDto.setGradeId(Long.parseLong(jsonObject.getString("gradeId")));
//                bestStudentDto.setFirstName(jsonObject.getString("courseName"));
//                bestStudentDto.setLastName(jsonObject.getString("courseName"));
//               bestStudentDto.setGrade(jsonObject.getString("courseName"));
//                bestStudentDto.setId(jsonObject.getString("courseName"));
//                bestStudentDto.setStatus(jsonObject.getString("courseName"));
//                bestStudentDtoList.add(bestStudentDto);
//            } catch (Exception e) {
//                bestStudentDto.addError(e.toString());
//            }
//           
            return bestStudentDto;
        }

}
