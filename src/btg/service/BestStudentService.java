/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 22, 2016 - created BestStudentService File (Dave)
 */
package btg.service;

import java.util.ArrayList;
import java.util.List;

import btg.common.GlobalConstants;
import btg.dao.BestStudentDao;
import btg.dto.BestStudentDto;
import btg.model.BestStudentModel;

public class BestStudentService {
    
    BestStudentDao bestStudentDao = new BestStudentDao();
    /*
     * 
     */
    public List<BestStudentDto> getAllBestStudents(){
        List<BestStudentDto> bestStudentDtoList;
        List<BestStudentModel> bestStudentModelList;
        BestStudentDto bestStudentDto;
        
        bestStudentDtoList = new ArrayList<BestStudentDto>();
        try{
            bestStudentModelList = bestStudentDao.getAllBestStudents();
            for(BestStudentModel bestStudentModel : bestStudentModelList){
                bestStudentDto = new BestStudentDto();
                bestStudentDto.setAccountId(bestStudentModel.getAccountId());
                bestStudentDto.setBestStudentId(bestStudentModel.getBestStudentId());
                bestStudentDto.setCourseId(bestStudentModel.getCourseId());
                bestStudentDto.setGradeId(bestStudentModel.getGradeId());
                bestStudentDto.setCourseName(bestStudentModel.getCourseName());
                bestStudentDto.setFirstName(bestStudentModel.getFirstName());
                bestStudentDto.setLastName(bestStudentModel.getLastName());
                bestStudentDto.setGrade(bestStudentModel.getGrade());
                bestStudentDto.setId(bestStudentModel.getId());
                bestStudentDto.setStatus(bestStudentModel.getStatus());
                bestStudentDtoList.add(bestStudentDto);
            }
            
        } catch (Exception e){
            System.out.println("This error is in getAllBestStudents Service: ");
            System.out.println(e.toString());
        }
        
        
        return bestStudentDtoList;
    }
    
    public BestStudentDto getBestStudentByCourseId(BestStudentDto inputBestStudent){
        BestStudentModel bestStudentModel;
        
        bestStudentModel = new BestStudentModel();
        bestStudentModel.setCourseId(inputBestStudent.getCourseId());
        try{
            bestStudentModel = bestStudentDao.getBestStudentByCourseId(bestStudentModel);
            if(null != bestStudentModel){
                inputBestStudent.setAccountId(bestStudentModel.getAccountId());
                inputBestStudent.setBestStudentId(bestStudentModel.getBestStudentId());
                inputBestStudent.setCourseId(bestStudentModel.getCourseId());
                inputBestStudent.setGradeId(bestStudentModel.getGradeId());
                inputBestStudent.setCourseName(bestStudentModel.getCourseName());
                inputBestStudent.setFirstName(bestStudentModel.getFirstName());
                inputBestStudent.setLastName(bestStudentModel.getLastName());
                inputBestStudent.setGrade(bestStudentModel.getGrade());
                inputBestStudent.setId(bestStudentModel.getId());
                inputBestStudent.setStatus(bestStudentModel.getStatus());
            } else {
                inputBestStudent.addError(GlobalConstants.ERR_ENTRY_NOT_FOUND);
            }
        } catch (Exception e){
            inputBestStudent.addError(e.toString());
        }
        
        return inputBestStudent;
    }
}
