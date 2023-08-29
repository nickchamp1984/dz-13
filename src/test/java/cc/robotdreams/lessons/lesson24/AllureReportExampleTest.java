package cc.robotdreams.lessons.lesson24;

import cc.robotdreams.automation.testng.approach.SomeFeatureTest;
import cc.robotdreams.lessons.lesson22.Calculator;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Story("Story description") // Story
public class AllureReportExampleTest extends SomeFeatureTest
{
    @Description("Test calculator functions")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Allure example test", groups="lesson24")
    public void test() {
        Allure.step("Create calculator object");
        Calculator calc = Calculator.integer(0);
        stepTestPlus(calc, 10);
        stepTestMinus(calc, 5);
        Allure.step("End");
        Allure.descriptionHtml("<h1>Some description</h1>");
    }

    @Step(value = "Test method 'plus'")
    private void stepTestPlus(Calculator calc, Integer val) {
        Integer expected = calc.calculate() + val;
        Allure.step("Call method plus(10);");
        calc.plus(val);
        Allure.step("Assert calculate is " + expected);
        Assert.assertEquals(calc.calculate(), expected);
    }

    @Step(value = "Test method 'minus'")
    private void stepTestMinus(Calculator calc, Integer val) {
        Integer expected = calc.calculate() - val;
        Allure.step("Call method minus(10);");
        calc.minus(val);
        Allure.step("Assert calculate is " + expected);
        Assert.assertEquals(calc.calculate(), expected);
    }
}
