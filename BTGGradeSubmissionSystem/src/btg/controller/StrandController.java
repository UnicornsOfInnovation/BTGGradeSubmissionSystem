/*
 * Developer: Christian Dave B. Baclayon
 * Development History:
 *      October 25, 2016 - created StrandController (Dave)
 *                       - insertStrand(), getAllStrands() (Dave)
 */


package btg.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import btg.common.GlobalConstants;
import btg.dto.StrandDto;
import btg.service.StrandService;


import org.slim3.repackaged.org.json.JSONObject;

public class StrandController extends Controller {

    
    StrandService strandService = new StrandService();
    @Override
    protected Navigation run() throws Exception {
        
        JSONObject jsonObject;
        StrandDto strandDto;
        String action;
        List<StrandDto> strandDtoList;
        
        strandDto = new StrandDto();
        strandDtoList = null;
        jsonObject = null;
        try{
            jsonObject = new JSONObject(new RequestMap(this.request));
            action = jsonObject.getString("action");
            if(action.equalsIgnoreCase("InsertStrand")){
                insertStrandController(jsonObject);
            } else if (action.equalsIgnoreCase("GetAllStrands")){
                strandDtoList = getAllStrandsController();
                for(StrandDto strandDto2 : strandDtoList){
                    System.out.println(strandDto2.getStrandCode());
                }
                jsonObject.put("strandsList", strandDtoList);
                for(StrandDto Strand : strandDtoList){
                    System.out.println(Strand.getStrandName());
                }
            }
        } catch (Exception e){
            System.out.println("This exception is found in StrandController:");
            System.out.println(e.toString());
         // Adds an error message if there exists.
            strandDto.addError(GlobalConstants.ERR_SERVER_CONTROLLER_PREFIX + e.getMessage());
            // initialize the json object that will be passed as response.
            if (null == jsonObject) {
                jsonObject = new JSONObject();
            }
        }
        
        // add the error message to the json object.
        jsonObject.put("errorList", strandDto.getErrorList());
        
        // set the type of response.
        response.setContentType(GlobalConstants.SYS_CONTENT_TYPE_JSON);
        // send the response back to the JS file.
        response.getWriter().write(jsonObject.toString());
        
        return null;
    }
    
    
    public void insertStrandController(JSONObject jsonObject){
        StrandDto strandDto;
        
        strandDto = new StrandDto();
        try{
            strandDto.setStrandCode(jsonObject.getString("strandCode"));
            strandDto.setStrandName(jsonObject.getString("strandName"));
            System.out.println(strandDto.getStrandCode());
            System.out.println(strandDto.getStrandName());
            strandService.insertStrand(strandDto);
        } catch (Exception e){
            System.out.println("This exception is found in StrandController:");
            System.out.println(e.toString());
        }
        
    }
    
    public List<StrandDto> getAllStrandsController(){
        List<StrandDto> strandDtoList;
        strandDtoList = new ArrayList<StrandDto>();
        try{
            strandDtoList = strandService.getAllStrands();

        } catch (Exception e){
            System.out.println("Exception in getAllStrandsController:");
            System.out.println(e.toString());
        }
        
        
        return strandDtoList;
    }

}
