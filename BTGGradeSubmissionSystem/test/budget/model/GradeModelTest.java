package budget.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import btg.model.GradeModel;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GradeModelTest extends AppEngineTestCase {

    private GradeModel model = new GradeModel();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
