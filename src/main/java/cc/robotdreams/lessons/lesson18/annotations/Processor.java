package cc.robotdreams.lessons.lesson18.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

final public class Processor
{
    private Processor() {}

    static public boolean isClassLevel(Object obj) {
        if (obj == null)
            return false;
        return obj.getClass().isAnnotationPresent(ClassLevel.class);
    }

    static public String getClassLevelMessage(Object obj) {
        if (isClassLevel(obj)) {
            return obj.getClass().getAnnotation(ClassLevel.class).message();
        } else
            throw new RuntimeException("Object " + obj + " is not annotated with @ClassLevel");
    }

    static public List<String> getMethodLevelValues(Object obj) {
        List<String> result = new ArrayList<>();
        if (obj == null)
            throw new RuntimeException("Object can not be null");

        for (Method method : obj.getClass().getMethods()) {
            if (method.isAnnotationPresent(MethodLevel.class)) {
                result.add(method.getName() + ": " + method.getAnnotation(MethodLevel.class).value());
            }
        }

        return result;
    }
}
