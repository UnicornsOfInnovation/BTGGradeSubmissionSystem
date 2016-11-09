/*
 * Developer: Christian Dave B. Baclayon
 * Development History:
 *      October 25, 2016 - created StrandModel (Dave)
 */

package btg.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class StrandModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    

    private String strandName;
    private String strandCode;
    private Long strandId;
    private Long id;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StrandModel other = (StrandModel) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
