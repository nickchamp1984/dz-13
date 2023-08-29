package cc.robotdreams.lessons.lesson23;

import cc.robotdreams.automation.Config;
import cc.robotdreams.automation.base.BaseTestNG;
import org.testng.annotations.Test;

import java.awt.*;

public class ConfigTestExample extends BaseTestNG
{
    @Test(groups = "lesson23")
    public void test() {
        System.out.println(Config.HTTP_BASE_URL.value);
        System.out.println(Config.HTTP_BASE_PORT.value);
        //System.out.println(System.getProperty("groups"));
        //System.out.println(System.getenv("TEST_ENV_VAR"));
    }
}
