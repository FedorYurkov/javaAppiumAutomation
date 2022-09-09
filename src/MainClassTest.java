import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("The value != 14", MainClass.getLocalNumber() == 14);
    }

}
