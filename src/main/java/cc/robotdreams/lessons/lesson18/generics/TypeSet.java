package cc.robotdreams.lessons.lesson18.generics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TypeSet
{
    final private Set<Type<?>> set = new HashSet<>();

    public void add(Type<?> type) {
        Type.checkNotNull(type);
        this.set.add(type);
    }

    public List<Type<?>> getList() {
        return new ArrayList<>(this.set);
    }
}
