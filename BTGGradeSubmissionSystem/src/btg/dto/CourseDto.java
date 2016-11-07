/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *      October 18, 2016 - created the Dto file with getters and setters (Dave)
 *      Octover 26, 2016 - added yearLevel (Dave)
 */
package btg.dto;

import com.google.appengine.api.datastore.Key;

public class CourseDto extends BaseDto{

    private boolean status;
    private Long courseId;
    private Key key;
    private Long id;
    private String courseCode;
    private int courseUnits;    
    private String courseType;
    private String courseName;
    
    //if major
    private int yearLevel;
    private String strand;
    
    
    public int getYearLevel() {
        return yearLevel;
    }
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }
    public Key getKey() {
        return key;
    }
    public void setKey(Key key) {
        this.key = key;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseType() {
        return courseType;
    }
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    public String getStrand() {
        return strand;
    }
    public void setStrand(String strand) {
        this.strand = strand;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getCourseUnits() {
        return courseUnits;
    }
    public void setCourseUnits(int courseUnits) {
        this.courseUnits = courseUnits;
    }
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
