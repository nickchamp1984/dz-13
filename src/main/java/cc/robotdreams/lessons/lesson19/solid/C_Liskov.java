package cc.robotdreams.lessons.lesson19.solid;

public class C_Liskov
{
    static abstract public class User
    {
        abstract public String getUsername();
        abstract public String getPassword();

        protected void save() {
            /* Do nothing */
        }
    }

    static public class ApplicationUser extends User
    {
        final private String username;
        final private String password;

        public ApplicationUser(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public String getUsername() {
            return this.username;
        }

        @Override
        public String getPassword() {
            return this.password;
        }
    }

    static public class DBUser extends User
    {
        private int id;
        private String username;
        private String password;

        public DBUser(int id) {
            super();
            this.id = id;
            // Add shutdown hook for saving data
        }

        private void fillFields() {
            if (this.username == null) {
                // Connect to DB
                // Fill fields by id
                // Disconnect
            }
        }

        @Override
        public void save (){
            // Connect to DB
            // Save data by id
            // Disconnect
        }

        @Override
        public String getUsername() {
            this.fillFields();
            return this.username;
        }

        @Override
        public String getPassword() {
            this.fillFields();
            return this.password;
        }
    }
}
