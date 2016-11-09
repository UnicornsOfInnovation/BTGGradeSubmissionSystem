package budget.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import btg.model.CourseModel;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CourseModelTest extends AppEngineTestCase {

    private CourseModel model = new CourseModel();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
