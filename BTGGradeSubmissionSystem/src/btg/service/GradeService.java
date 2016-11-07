/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 22, 2016 - created GradeService File
 *                      - insertGrade(), updateGrade(), getAllStudentGradesByCourse() (Dave)
 *     October 23, 2016 - getAllGradesByAccountId() (Dave)
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
        }
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
          courseModel = courseDao.getCourseById(courseModel);    
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
          gradeModel.setGrade(inputGrade.getGrade());
          gradeModel.setStatus(inputGrade.getStatus());
          try{
              temp = new GradeModel();
              temp.setId(inputGrade.getId());
              temp = gradeDao.getGradeById(temp);
              if (null != temp) {
                  try {
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
    
}
