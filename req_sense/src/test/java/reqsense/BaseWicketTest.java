package reqsense;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

public abstract class BaseWicketTest {

    protected WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester();
    }
}
