package cc.robotdreams.lessons.lesson18.generics;

public class IntType extends Type<Integer>
{
    public IntType(Integer value) {
        super(value);
    }

    @Override
    public void add(Integer value) {
        checkNotNull(value);
        this.value += value;
    }
}
