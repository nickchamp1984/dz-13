package cc.robotdreams.lessons.lesson19.solid;

public class E_DependencyInversion
{
    public enum Gender
    {
        MALE,
        FEMALE
    }

    static abstract public class Human
    {
        protected String firstName;
        protected String lastName;

        private Human partner;

        public Human(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFullName() {
            return this.firstName + " " + this.lastName;
        }

        final public Human getPartner() {
            return this.partner;
        }

        final public boolean hasPartner() {
            return this.partner != null;
        }

        public boolean registerPartner(Human partner) {
            if (this.hasPartner()) {
                System.out.println("Human \"" + this.getFullName() + "\" already has partner");
                return false;
            }
            if (partner.hasPartner()) {
                System.out.println("Human \"" + partner.getFullName() + "\" already has partner");
                return false;
            }
            if (this.getClass() == partner.getClass()) {
                System.out.println("Marriage can be registered only for different genders");
                return false;
            }
            this.partner = partner;
            partner.partner = this;
            this.onRegisterPartner(partner);
            partner.onRegisterPartner(this);
            return true;
        }

        public void unregisterPartner() {
            if (this.hasPartner()) {
                this.onUnregisterPartner(partner);
                partner.onUnregisterPartner(this);
                partner.partner = null;
                partner = null;
            }
        }

        protected void onRegisterPartner(Human partner) {
            /* Do nothing */
        }

        protected void onUnregisterPartner(Human partner) {
            /* Do nothing */
        }

        abstract public Gender gender();
    }

    static public class Men extends Human
    {
        public Men(String firstName, String lastName) {
            super(firstName, lastName);
        }

        @Override
        public Gender gender() {
            return Gender.MALE;
        }
    }

    static public class Woman extends Human
    {
        final private String initialLastName;
        private boolean isChangingName = false;

        public Woman(String firstName, String lastName) {
            super(firstName, lastName);
            this.initialLastName = lastName;
        }

        public boolean isChangingName() {
            return this.isChangingName;
        }

        public void setChangingName(boolean value) {
            this.isChangingName = value;
        }

        @Override
        protected void onRegisterPartner(Human partner) {
            if (isChangingName)
                this.lastName = partner.lastName;
        }

        @Override
        protected void onUnregisterPartner(Human partner) {
            if (isChangingName)
                this.lastName = this.initialLastName;
        }

        @Override
        public Gender gender() {
            return Gender.FEMALE;
        }
    }
}
