package cc.robotdreams.lessons.lesson21;

import cc.robotdreams.lessons.lesson19.solid.B_OpenClosed;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MenWomanTest
{
    static final private String INIT_MEN_FIRST_NAME = "";

    @Test
    public void testMenAndWoman() {

        B_OpenClosed.Men men = new B_OpenClosed.Men();
        Assert.assertEquals(men.getGender(), "Male", "Gender is not valid");
        B_OpenClosed.Woman woman = new B_OpenClosed.Woman();
        Assert.assertEquals(woman.getGender(), "Female", "Gender is not valid");
    }
}
