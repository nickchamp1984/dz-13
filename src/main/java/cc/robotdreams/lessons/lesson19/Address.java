package cc.robotdreams.lessons.lesson19;

public class Address
{
    private String country;
    private String city;
    private String region;
    private String street;
    private String building;
    private String apartment;

    public Address(String country, String city, String region, String street, String building, String apartment) {
        this.country = country;
        this.city = city;
        this.region = region;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    static public Builder builder() {
        return new Builder();
    }

    static public class Builder
    {
        private String country;
        private String city;
        private String region;
        private String street;
        private String building;
        private String apartment;

        public Builder country(String value) {
            this.country = value;
            return this;
        }

        public Builder city(String value) {
            this.city = value;
            return this;
        }

        public Builder region(String value) {
            this.region = value;
            return this;
        }

        public Builder street(String value) {
            this.street = value;
            return this;
        }

        public Builder building(String value) {
            this.building = value;
            return this;
        }

        public Builder apartment(String value) {
            this.apartment = value;
            return this;
        }

        public Address build() {
            StringBuilder errorMessages = new StringBuilder();
            if (country == null)
                errorMessages.append("Country must not be null\n");
            if (city == null)
                errorMessages.append("City must not be null\n");
            if (street == null)
                errorMessages.append("Street must not be null\n");
            String errMsgString = errorMessages.toString();
            if (!errMsgString.isEmpty())
                throw new RuntimeException(errMsgString);

            return new Address(country, city, region, street, building, apartment);
        }

    }

}
