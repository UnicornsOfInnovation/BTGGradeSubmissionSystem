package budget.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import btg.model.BestStudentModel;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BestStudentModelTest extends AppEngineTestCase {

    private BestStudentModel model = new BestStudentModel();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
