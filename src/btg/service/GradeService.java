/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 22, 2016 - created GradeService File
 *                      - insertGrade(), updateGrade(), getAllStudentGradesByCourse() (Dave)
 *     October 23, 2016 - getAllGradesByAccountId() (Dave)
 *     November 25, 2016 - created getGradeByAccountAndCourse() (Dave)
 */
package btg.service;

import java.util.ArrayList;
import java.util.List;



import btg.common.GlobalConstants;
import btg.dao.AccountDao;
import btg.dao.CourseDao;
import btg.dao.GradeDao;
import btg.dto.GradeDto;
import btg.dto.StudentGradeDto;
import btg.model.AccountModel;
import btg.model.CourseModel;
import btg.model.GradeModel;

public class GradeService {

    GradeDao gradeDao = new GradeDao();
    CourseDao courseDao = new CourseDao();
    AccountDao accountDao = new AccountDao();
    
    
    
    /*
     *  Purpose: Inserts a grade to the database
     *  @param: GradeModel to insert to the database
     *  @return: void
     */
    public GradeDto insertGrade(GradeDto inputGrade){
        System.out.println("GradeService: insertGrade() START");
        GradeModel gradeModel = new GradeModel();
        gradeModel.setAccountId(inputGrade.getAccountId());
        gradeModel.setCourseId(inputGrade.getCourseId());
        gradeModel.setGrade(inputGrade.getGrade());
        gradeModel.setStatus(inputGrade.getStatus());
        try{
            if (null == gradeDao.getGradeById(gradeModel)) {
                try {
                    gradeDao.insertGrade(gradeModel);
                } catch (Exception e) {
                    inputGrade.addError(GlobalConstants.ERR_ENTRY_CANNOT_ADD);
                }
            } else {
                inputGrade.addError(GlobalConstants.ERR_ENTRY_ALREADY_EXISTS);
            }
            
        }catch (Exception e){
            inputGrade.addError(e.toString());
            e.printStackTrace();
        }
        System.out.println("GradeService: insertGrade() END");
        return inputGrade;
    }
    
        
      public List<StudentGradeDto> getAllStudentGradesByCourse(Long inputCourseId){
          List<StudentGradeDto> studentGradeDtoList;
          List<GradeModel> gradeModelList;
          CourseModel courseModel;
          AccountModel accountModel;
          StudentGradeDto studentGradeDto;
          
          studentGradeDtoList = new ArrayList<StudentGradeDto>();
          courseModel = new CourseModel();
          courseModel.setCourseId(inputCourseId);
          gradeModelList = gradeDao.getAllGradesByCourse(courseModel);
          for(GradeModel gradeModel : gradeModelList){
              studentGradeDto = new StudentGradeDto();
              accountModel = new AccountModel();
              accountModel.setId(gradeModel.getAccountId());
              accountModel = accountDao.getAccountById(accountModel);
              studentGradeDto.setAccountId(accountModel.getAccountId());
              studentGradeDto.setCourseId(courseModel.getCourseId());
              studentGradeDto.setCourseName(courseModel.getCourseName());
              studentGradeDto.setFirstName(accountModel.getFirstName());
              studentGradeDto.setLastName(accountModel.getLastName());
              studentGradeDto.setGrade(gradeModel.getGrade());
              studentGradeDto.setGradeId(gradeModel.getGradeId());
              studentGradeDtoList.add(studentGradeDto);
          }
               
          return studentGradeDtoList;
           
      }
      
      
      /*
       *  Purpose: Updates a grade to the database
       *  @param: GradeModel to update the grade to the database
       *  @return: void
       */
      public GradeDto updateGrade(GradeDto inputGrade){
          GradeModel gradeModel;
          GradeModel temp;
          
          gradeModel = new GradeModel();
          gradeModel.setAccountId(inputGrade.getAccountId());
          gradeModel.setCourseId(inputGrade.getCourseId());
          gradeModel.setGradeId(inputGrade.getGradeId());
          gradeModel.setGrade(inputGrade.getGrade());
          gradeModel.setStatus(true);
          gradeModel.setId(inputGrade.getGradeId());
          try{
              temp = new GradeModel();
              temp.setId(gradeModel.getGradeId());
              temp = gradeDao.getGradeById(temp);
              if (null != temp) {
                  try {
                      gradeModel.setKey(temp.getKey());
                      gradeDao.updateGrade(gradeModel);
                  } catch (Exception e) {
                      inputGrade.addError(GlobalConstants.ERR_ENTRY_CANNOT_ADD);
                  }
              } else {
                  inputGrade.addError(GlobalConstants.ERR_ENTRY_ALREADY_EXISTS);
              }
              
          }catch (Exception e){
              inputGrade.addError(e.toString());
          }
          return inputGrade;
      }
      
      /*
       *  Purpose: This method is called by computeGPA() from AccountService. It returns all grades by accountId
       *           to recompute the Grade Point Average of the account.
       *  @param: AccountModel containing the AccountId
       *  @return: List<GradeModel> that holds the grades of all the students.
       */
      
      //CHANGE THIS WTIH GRADE DTO NOT GRADE MODEL TO BE RETURNED
      public List<GradeModel> getAllGradesByAccountId (AccountModel inputAccount){
          List<GradeModel> gradeModelList;
          
          gradeModelList = null;
          try{
              gradeModelList = gradeDao.getAllGradesByAccountId(inputAccount);
          } catch (Exception e){
              System.out.println("This error is found in getAllGradesByAccountId found in GradeService:");
              System.out.println(e.toString());
          }
          return gradeModelList;
      }
    
      
      
      public List<GradeDto> getAllGrades(){
          List<GradeDto> gradeListDto;
          List<GradeModel> gradeModelList;
          
          gradeListDto = new ArrayList<GradeDto>();
          gradeModelList = gradeDao.getAllGrades();
          for(GradeModel gradeModel: gradeModelList){
              GradeDto gradeDto = new GradeDto();
              gradeDto.setAccountId(gradeModel.getAccountId());
              gradeDto.setCourseId(gradeModel.getCourseId());
              gradeDto.setGrade(gradeModel.getGrade());
              gradeDto.setGradeId(gradeModel.getGradeId());
              gradeListDto.add(gradeDto);
          }
          
          return gradeListDto;
      }
      
      
      
      public GradeDto getGradeByAccountAndCourse(GradeDto inputGrade){
          GradeDto gradeDto;
          GradeModel gradeModel;
          GradeModel tempGradeModel;
          
          gradeDto = null;
          tempGradeModel = new GradeModel();
          tempGradeModel.setAccountId(inputGrade.getAccountId());
          tempGradeModel.setCourseId(inputGrade.getCourseId());
          
          try{
              gradeModel = gradeDao.getGradeByAccountAndCourse(tempGradeModel);
              gradeDto = new GradeDto();
              gradeDto.setAccountId(gradeModel.getAccountId());
              gradeDto.setCourseId(gradeModel.getCourseId());
              gradeDto.setGrade(gradeModel.getGrade());
              gradeDto.setGradeId(gradeModel.getGradeId());
          } catch (Exception e){
              System.out.println("Exception in getAccountByAccountAndCourse() in GradeService:");
              e.printStackTrace();
          }
          
          return gradeDto;
      }
      
      
      public GradeDto getGradeById (GradeDto inputGrade){
          GradeModel gradeModel;
          GradeDto gradeDto;
          
          gradeDto = null;
          gradeModel = new GradeModel();
          gradeModel.setId(inputGrade.getGradeId());
          try{
              gradeModel = gradeDao.getGradeById(gradeModel);
              gradeDto = new GradeDto();
              gradeDto.setAccountId(gradeModel.getAccountId());
              gradeDto.setCourseId(gradeModel.getCourseId());
              gradeDto.setGrade(gradeModel.getGrade());
              gradeDto.setGradeId(gradeModel.getGradeId());
          } catch (Exception e){
              e.printStackTrace();
          }
          
          return gradeDto;
      }
      
      
      
      public List<StudentGradeDto> getAllStudentGrades(){
          List<StudentGradeDto> studentGradeDtoList;
          List<GradeModel> gradeModelList;
          CourseModel courseModel;
          AccountModel accountModel;
          StudentGradeDto studentGradeDto;
          
          studentGradeDtoList = new ArrayList<StudentGradeDto>();
          gradeModelList = gradeDao.getAllGrades();
          for(GradeModel gradeModel : gradeModelList){
              studentGradeDto = new StudentGradeDto();
              accountModel = new AccountModel();
              accountModel.setId(gradeModel.getAccountId());
              accountModel = accountDao.getAccountById(accountModel);
              courseModel = new CourseModel();
              courseModel.setId(gradeModel.getCourseId());
              courseModel = courseDao.getCourseById(courseModel);
              studentGradeDto.setAccountId(accountModel.getAccountId());
              studentGradeDto.setCourseId(courseModel.getCourseId());
              studentGradeDto.setCourseName(courseModel.getCourseName());
              studentGradeDto.setFirstName(accountModel.getFirstName());
              studentGradeDto.setLastName(accountModel.getLastName());
              studentGradeDto.setGrade(gradeModel.getGrade());
              studentGradeDto.setGradeId(gradeModel.getGradeId());
              studentGradeDtoList.add(studentGradeDto);
          }
               
          return studentGradeDtoList;
           
      }
      
      
      
}
