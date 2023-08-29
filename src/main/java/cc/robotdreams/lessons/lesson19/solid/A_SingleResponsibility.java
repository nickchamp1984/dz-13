package cc.robotdreams.lessons.lesson19.solid;

public class A_SingleResponsibility
{
    static public class User
    {
        public String firstName = "";
        public String lastName  = "";
        public String gender    = "";

        private String role     = "user"; // User permissions

        private Address address = new Address();

        // Move to Logs class
        public boolean hasAccessLogs() {
            return this.role.equals("admin");
        }
    }

    static public class Address
    {
        private String country  = "";
        private String city     = "";
        private String street   = "";
        private String building = "";


    }
}
