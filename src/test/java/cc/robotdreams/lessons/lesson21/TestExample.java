package cc.robotdreams.lessons.lesson21;

import cc.robotdreams.lessons.lesson21.BaseTest;
import org.testng.annotations.*;

public class TestExample extends BaseTest
{
    @BeforeClass
    public void beforeClass() {
        System.out.println("--- --- Before class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("--- --- After class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("--- --- --- Before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("--- --- --- After method");
    }

    @Test
    public void test() {
        System.out.println("--- --- --- --- Test message");
    }

    @Test
    public void test2() {
        System.out.println("--- --- --- --- Test message 2");
    }
}
