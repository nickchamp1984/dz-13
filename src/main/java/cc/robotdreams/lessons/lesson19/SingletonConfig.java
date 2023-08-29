package cc.robotdreams.lessons.lesson19;

import java.util.HashMap;
import java.util.Map;

public class SingletonConfig
{
    static private SingletonConfig _instance;

    private SingletonConfig() { /* Empty */ }

    static public SingletonConfig get() {
        if (_instance == null)
            _instance = new SingletonConfig();
        return _instance;
    }

    Map<String, String> parameters = new HashMap<>();



    public String someStringValue = "Init value";

}
