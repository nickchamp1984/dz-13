package cc.robotdreams.lessons.lesson18;

import cc.robotdreams.lessons.lesson18.annotations.ClassLevel;
import cc.robotdreams.lessons.lesson18.annotations.Example;
import cc.robotdreams.lessons.lesson18.annotations.Processor;
import cc.robotdreams.lessons.lesson18.generics.IntType;
import cc.robotdreams.lessons.lesson18.generics.StrType;
import cc.robotdreams.lessons.lesson18.generics.Type;
import cc.robotdreams.lessons.lesson18.generics.TypeSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@ClassLevel
public class Lesson18
{
    static public void main(String[] args) {
        // Annotations
        Example example = new Example();
        if (Processor.isClassLevel(example)) {
            System.out.println("Class " + example.getClass().getCanonicalName() + " annotated with @ClassLevel");
            System.out.println("Message is: " + Processor.getClassLevelMessage(example));
            System.out.println("Methods values:");
            for (String message : Processor.getMethodLevelValues(example)) {
                System.out.println(message);
            }
        }

        example.methodExample();

        // Generics
        IntType intType = new IntType(10);
        intType.add(15);
        System.out.println(intType);

        StrType strType = new StrType("String value");
        strType.add("Concatenated string");
        System.out.println(strType);

        TypeSet set = new TypeSet();
        set.add(intType);
        set.add(strType);

        for (Type<?> value : set.getList()) {
            System.out.println(value);
        }

        // Lambda
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("// Lambda");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////");
        callConsumer(System.out::println); // Collection.stream().forEach
        callSupplier(() -> "Hello from main method");
        callPredicate(s -> s.equalsIgnoreCase("check this string")); // Collection.stream().filter
        callFunction(s -> s + " + concatenated value"); // Collection.stream().map

        // Streams

        List<String> colors = new ArrayList<>();
        colors.add("WHITE");
        colors.add("BLACK");
        colors.add("Green");
        colors.add("Red");
        colors.add("yellow");
        colors.add("blue");

        System.out.println("//////////////////////////////////////////////////////////////////");
        System.out.println("// Do something - Consumer interface");
        System.out.println("//////////////////////////////////////////////////////////////////");
        colors.stream().forEach(System.out::println);

        System.out.println("//////////////////////////////////////////////////////////////////");
        System.out.println("// Generate or get something - Supplier interface");
        System.out.println("//////////////////////////////////////////////////////////////////");
        Supplier<String> randomString = () -> UUID.randomUUID().toString();
        colors.stream().forEach(color -> {
            System.out.println(color + " : " + randomString.get());
        });

        System.out.println("//////////////////////////////////////////////////////////////////");
        System.out.println("// Filter something - Predicate interface");
        System.out.println("//////////////////////////////////////////////////////////////////");
        List<String> uppercase = colors.stream().filter(color -> color.toUpperCase().equals(color)).collect(Collectors.toList());
        List<String> lowercase = colors.stream().filter(color -> color.toLowerCase().equals(color)).collect(Collectors.toList());
        List<String> other = colors.stream().filter(
                color -> {
                    return !uppercase.contains(color) && !lowercase.contains(color);
                }
        ).collect(Collectors.toList());
        System.out.println("Uppercase: " + uppercase);
        System.out.println("Lowercase: " + lowercase);
        System.out.println("Other:     " + other);

        System.out.println("//////////////////////////////////////////////////////////////////");
        System.out.println("// Do something and get result - Function interface");
        System.out.println("//////////////////////////////////////////////////////////////////");
        List<String> allToUpper = colors.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("All to upper: " + allToUpper);
        //test(String::toUpperCase);

    }

    static public void callConsumer(Consumer<String> lambda) {
        lambda.accept("Hello from callConsumer");
    }

    static public void callSupplier(Supplier<String> lambda) {
        String value = lambda.get();
        System.out.println("callSupplier: " + value);
    }

    static public void callPredicate(Predicate<String> lambda) {
        String val = "check this string";
        if (lambda.test(val)) {
            System.out.println("callPredicate: Value is \"" + val + "\"");
        } else {
            System.out.println("callPredicate: Value is not \"" + val + "\"");
        }
    }

    static public void callFunction(Function<String, String> lambda) {
        System.out.println("callFunction: " + lambda.apply("test value"));
    }
}
