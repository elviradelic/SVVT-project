package TestBase.Factory;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class TestUser {

    private  String email;
    private String password;
    private String firstName;
    private String lastName;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Builder pattern for flexible creation
    public static Builder builder() { return new Builder(); }

    @NoArgsConstructor
    public static class Builder {
        private String email = "azaria.anyelo@minuteafter.com";
        private String password = "bgbng25Sfr01";
        private String firstName = "Azari";
        private String lastName = "Any";

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public TestUser build() {
            return new TestUser(email, password, firstName, lastName);
        }
    }

}
