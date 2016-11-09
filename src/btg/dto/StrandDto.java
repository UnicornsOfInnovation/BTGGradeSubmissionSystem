/*
 * Developer: Christian Dave B. Baclayon
 * Development History:
 *      October 25, 2016 - created StrandDto (Dave)
 */
package btg.dto;

import com.google.appengine.api.datastore.Key;

public class StrandDto extends BaseDto {
    private String strandName;
    private String strandCode;
    private Long strandId;
    private Long id;
    private Key key; 
    public String getStrandName() {
        return strandName;
    }
    public void setStrandName(String strandName) {
        this.strandName = strandName;
    }
    public String getStrandCode() {
        return strandCode;
    }
    public void setStrandCode(String strandCode) {
        this.strandCode = strandCode;
    }
    public Long getStrandId() {
        return strandId;
    }
    public void setStrandId(Long strandId) {
        this.strandId = strandId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Key getKey() {
        return key;
    }
    public void setKey(Key key) {
        this.key = key;
    }
       
}
