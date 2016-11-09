/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     October 18, 2016 - Modified the GlobalConstands file of Rococo Global Technologies. (Dave)
 */
package btg.common;
public class GlobalConstants {

    /**
     * Content Type - JSON.
     */
    public static final String SYS_CONTENT_TYPE_JSON = "application/json";

    
    /*************************************************************
     * REDIRECT LINKS
     *************************************************************/

    /**
     * Link for the budget list page.
     */
    //public static final String SYS_REDIRECT_BUDGET_LIST = "/budget";
    //sample above
    
    
    
    /*************************************************************
     * FORWARD LINKS
     *************************************************************/

    /**
     * Link for the budget.jsp file.
     */
    public static final String SYS_FORWARD_HOMESCREEN= "/html/admin.html";
    //sample above
    
    
    

    /*************************************************************
     * COMMON ERRORS
     *************************************************************/

    /**
     * Prefix for Server Controller Error.
     */
    public static final String ERR_SERVER_CONTROLLER_PREFIX = "Server Controller Error: ";

    /**
     * Error message for general exceptions.
     */
    public static final String ERR_GENERAL_EXCEPTION = "General Exception Occurred. Please Contact Administrator.";

    /**
     * Error message for database/datastore exceptions.
     */
    public static final String ERR_DB_EXCEPTION = "DB Exception Occurred. Please Contact Administrator.";

    /**
     * Error message for unable to add entry.
     */
    public static final String ERR_ENTRY_CANNOT_ADD = "Unable to add entry. Please try again.";

    /**
     * Error message for unable to update entry.
     */
    public static final String ERR_ENTRY_CANNOT_UPDATE = "Unable to update entry. Please try again.";

    /**
     * Error message for unable to delete entry.
     */
    public static final String ERR_ENTRY_CANNOT_DELETE = "Unable to delete entry. Please try again.";

    /**
     * Error message for duplicate entry.
     */
    public static final String ERR_ENTRY_ALREADY_EXISTS = "Entry already exists. Please use another date-type-description combination.";

    /**
     * Error message for missing entry.
     */
    public static final String ERR_ENTRY_NOT_FOUND = "Entry not found in datastore, please try refreshing the list.";

}
