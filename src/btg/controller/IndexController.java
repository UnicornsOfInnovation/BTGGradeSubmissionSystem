
package btg.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import btg.common.GlobalConstants;

public class IndexController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        System.out.println("IndexController.run start end");
        return forward(GlobalConstants.SYS_FORWARD_LOGINSCREEN);
    }

}
