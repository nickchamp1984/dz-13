package cc.robotdreams.lessons.lesson19;

public class Lesson19
{
    static public void main(String[] args) {
        /////////////////////// SOLID ///////////////////////
        // Single Responsibility Principle
        // Open/Closed Principle
        // Liskov Substitution Principle
        // Interface Segregation Principle
        // Dependency Inversion Principle

        // DRY - Do not repeat yourself
        // YAGNI - You aren't gonna need it
        // KISS - Keep It Simple Stupid

        SingletonConfig config = SingletonConfig.get();
        System.out.println(config.someStringValue);
        config.someStringValue = "Changed value from config";


        // ... //
        SingletonConfig config2 = SingletonConfig.get();
        System.out.println(config2.someStringValue);
        System.out.println(SingletonConfig.get().someStringValue);


        Address addr = new Address("Ukraine", "Kyiv", "Kyiv", "Khreschatyk", "1", "1");
        Address addr2 = new Address("Ukraine", "Kyiv", null, "Khreschatyk", null, null);

        Address addr3 = Address.builder()
                .country("Ukraine")
                .city("Kyiv")
                .street("Khreschatyk")
                .build();
    }
}
