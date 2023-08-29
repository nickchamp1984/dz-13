package cc.robotdreams.lessons.lesson21;

import cc.robotdreams.lessons.lesson21.BaseTest;
import org.testng.annotations.Test;

public class TestExample2 extends BaseTest
{
    @Test(testName = "Test example 2 test name", groups = "smoke")
    public void test() {
        System.out.println("Test example 2");
    }
}
