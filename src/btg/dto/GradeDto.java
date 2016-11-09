/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 21, 2016 - created GradeDto File
 */
package btg.dto;

import com.google.appengine.api.datastore.Key;

public class GradeDto extends BaseDto{
    
    private Long gradeId;
    private Key key;
    private Long id;
    private double grade;
    private Long accountId;
    private Long courseId;
    private boolean status;

    public Long getGradeId() {
        return gradeId;
    }
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
    public Key getKey() {
        return key;
    }
    public void setKey(Key key) {
        this.key = key;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
