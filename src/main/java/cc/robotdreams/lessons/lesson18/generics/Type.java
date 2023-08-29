package cc.robotdreams.lessons.lesson18.generics;

abstract public class Type<T>
{
    static protected void checkNotNull(Object obj) {
        if (obj == null)
            throw new RuntimeException("Value can not be null");
    }

    protected T value;

    public Type(T value) {
        checkNotNull(value);
        this.value = value;
    }

    public T get() {
        return this.value;
    }

    @Override
    final public String toString() {
        return this.value.toString();
    }

    abstract public void add(T value);
}
