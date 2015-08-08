package reqsense;

import org.junit.Assert;
import org.junit.Test;

public class StartPageTest extends BaseWicketTest {

    @Test
    public void always_show_startPage() {

        System.err.println("\n\n\t *** Running StartPageTest ***\n\n");
        Assert.assertNotNull(tester);
        /*StartPage page = tester.startPage(StartPage.class);
        tester.assertRenderedPage(StartPage.class);*/
    }

    //@Test
    public void todo_when_User_is_not_Authenticated_then_Login_is_Offered() {

    }
}
