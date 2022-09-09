import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("The value != 14", MainClass.getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber() {
        MainClass obj = new MainClass();
        Assert.assertTrue("Variable class_number != 20", obj.getClassNumber() == 20);
    }

}
