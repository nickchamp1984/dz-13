package cc.robotdreams.lessons.lesson19.solid;

public class B_OpenClosed
{
    static abstract public class Human
    {
        abstract String getName();
        abstract String getGender();
    }

    static public class Men extends Human
    {
        @Override
        public String getName() {
            return "Male name";
        }

        @Override
        public String getGender() {
            return "Male";
        }
    }

    static public class Woman extends Human
    {
        @Override
        public String getName() {
            return "Female name";
        }

        @Override
        public String getGender() {
            return "Female";
        }
    }

    static public class peopleManager
    {
        public String getGender(Human human) {
            if (human.getClass() == Men.class) {
                return "Male";
            }
            if (human.getClass() == Woman.class) {
                return "Female";
            }
            return "Undefined";
        }
    }
}
