package budget.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import btg.model.StrandModel;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StrandModelTest extends AppEngineTestCase {

    private StrandModel model = new StrandModel();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
