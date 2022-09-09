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

    @Test
    public void testGetClassString() {
        MainClass obj = new MainClass();
        String actual_string = obj.getClassString();
        Assert.assertTrue("Substring doesn't find",
                actual_string.contains("hello") || actual_string.contains("Hello"));
    }

}
