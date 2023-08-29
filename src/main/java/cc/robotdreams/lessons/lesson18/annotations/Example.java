package cc.robotdreams.lessons.lesson18.annotations;

@Deprecated
@ClassLevel(message="Hello from Example class")
public class Example
{


    @MethodLevel(value = "Hello from method")
    public void methodExample() {

    }
}
