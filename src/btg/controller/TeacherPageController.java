/*
 * Main Developer: Christian Dave B. Baclayon
 * Development History:
 *     November 15, 2016 - creation of this file (Dave)
 */
package btg.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class TeacherPageController extends Controller {
    @Override
    protected Navigation run(){
        
        return forward("/html/teacher.html");
    }
}