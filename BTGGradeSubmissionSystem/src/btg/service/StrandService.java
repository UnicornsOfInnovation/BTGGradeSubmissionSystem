/*
 * Developer: Christian Dave B. Baclayon
 * Development History:
 *      October 25, 2016 - created StrandService (Dave)
 *                       - insertStrand(), getAllStrandS() (Dave)
 */

package btg.service;

import java.util.ArrayList;
import java.util.List;

import btg.dao.StrandDao;
import btg.dto.StrandDto;
import btg.model.StrandModel;

public class StrandService {

    StrandDao strandDao = new StrandDao();
    
    
    /*
     * Purpose: Used to insert a new strand to the database
     * @param: StrandDto that holds the details of the strand
     * @return: void - nothing to return
     */
    public void insertStrand(StrandDto inputStrand){
        StrandModel strandModel;
        
        strandModel = new StrandModel();
        strandModel.setStrandCode(inputStrand.getStrandCode());
        strandModel.setStrandName(inputStrand.getStrandName());
        try{
            strandDao.insertStrand(strandModel);
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    
    public List<StrandDto> getAllStrands(){
        List<StrandDto> strandDtoList;
        List<StrandModel> strandModelList;
        StrandDto strandDto;
        
        
        strandDtoList = new ArrayList<StrandDto>();
        try{
            strandModelList = strandDao.getAllStrands();
            for(StrandModel strandModel : strandModelList){
                strandDto = new StrandDto();
                strandDto.setStrandCode(strandModel.getStrandCode());
                strandDto.setId(strandModel.getStrandId());
                strandDto.setStrandName(strandModel.getStrandName());
                strandDto.setStrandId(strandModel.getStrandId());
                strandDtoList.add(strandDto);
            }
        } catch (Exception e){
            System.out.println("Exception in getAllStrands in StrandService:");
            System.out.println(e.toString());
        }
        return strandDtoList;
    }
}
