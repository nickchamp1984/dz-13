package cc.robotdreams.lessons.lesson22;

import cc.robotdreams.automation.base.BaseTestNG;
import cc.robotdreams.automation.utils.CSVFileReader;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CalculatorTest extends BaseTestNG
{
    private enum ExpectedStatus
    {
        EQUALS,
        NOT_EQUALS,
        EXCEPTION
    }

    static public class CalcDataRecord extends CsvToBean
    {
        @CsvBindByName(column = "expected_status")
        private String expectedStatus;

        @CsvBindByName(column = "expression")
        private String expression;

        @CsvBindByName(column = "expected_value")
        private Integer expectedValue;
    }

    @DataProvider
    public Object[][] parseData() {
        List<CalcDataRecord> list = CSVFileReader
                .csvToDataProvider(CalcDataRecord.class, "./src/test/resources/lesson22/calcParseData.csv");
        return list.stream()
                .map(item -> new Object[] {
                        ExpectedStatus.valueOf(item.expectedStatus),
                        item.expression.trim(),
                        item.expectedValue
                })
                .toArray(Object[][]::new);
        //Object[][] objs = new Object[list.size()][];
        //for (int i = 0; i < list.size(); i ++) {
        //    objs[i] = new Object[] { ExpectedStatus.valueOf(list.get(i).expectedStatus), list.get(i).expression, list.get(i).expectedValue};
        //}
        //return objs;

        //return new Object[][] {
        //        {ExpectedStatus.EQUALS,         "2 + 2 * 2",        6},
        //        {ExpectedStatus.EQUALS,         "2 + 5 + 7",        14},
        //        {ExpectedStatus.EQUALS,         "2 + 10 / 5 * 2",   6},
        //        {ExpectedStatus.NOT_EQUALS,     "2 + 2 * 2",        8},
        //        {ExpectedStatus.NOT_EQUALS,     "2 * 2 + 2",        8},
        //        {ExpectedStatus.EXCEPTION,      " ",                null},
        //        {ExpectedStatus.EXCEPTION,      "2 * 2 +",          null},
        //        {ExpectedStatus.EXCEPTION,      "2 4 * 2",          null},
        //        {ExpectedStatus.EXCEPTION,      "2 + * 2",          null},
        //        {ExpectedStatus.EXCEPTION,      "2 = 2",            null},
        //};
    }

    @Test(testName = "Test parsing of expression", dataProvider = "parseData", groups = "lesson22")
    public void testParse(ExpectedStatus status, String expression, Integer expectedValue) {
        logger.debug(String.format("Status: %s, Expression: %s, Expected value: %d", status, expression, expectedValue));
        if (status == ExpectedStatus.EQUALS) {
            Integer result = Calculator.parse(expression).calculate();
            Assert.assertEquals(result, expectedValue, "Wrong calculation");
        } else if (status == ExpectedStatus.NOT_EQUALS) {
            Integer result = Calculator.parse(expression).calculate();
            Assert.assertNotEquals(result, expectedValue, "Wrong calculation");
        } else if (status == ExpectedStatus.EXCEPTION) {
            Assert.assertThrows(new Assert.ThrowingRunnable()
            {
                @Override
                public void run() throws Throwable {
                    try {
                        Calculator.parse(expression).calculate();
                    } catch (Throwable e) {
                        logger.error(e.getMessage());
                        throw e;
                    }

                }
            });
        }
        logger.info("Passed");
    }

    @Test(testName = "Test calculator sum", groups = "lesson22")
    public void testPlus() {
        SoftAssert softAssert = new SoftAssert();

        Calculator calc = Calculator.integer(0);
        Integer result = calc.plus(10).plus(20).plus(5).calculate();
        softAssert.assertEquals(result, (Integer) 35, "Wrong calculation");

        Calculator calc2 = Calculator.integer(0);
        Integer result2 = calc2.plus(5).plus(8).plus(10).calculate();
        softAssert.assertEquals(result2, (Integer) 23, "Wrong calculation");

        softAssert.assertAll(); // Throw Errors if any
    }

    @Test(testName = "Test calculator multiply",groups = "lesson22")
    public void testMultiply() {
        Integer result = Calculator.integer(10).plus(5).multiply(5).calculate();
        Assert.assertEquals(result, 35, "Wrong calculation");
    }
}
