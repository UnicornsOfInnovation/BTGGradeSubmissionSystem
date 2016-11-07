package budget.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import btg.model.AccountModel;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AccountModelTest extends AppEngineTestCase {

    private AccountModel model = new AccountModel();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
