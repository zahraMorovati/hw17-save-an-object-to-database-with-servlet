package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    private String username;
    private String password;
    private Date birthDate;
    private String email;


    public static final class UserBuilder {
        private User user;

        private UserBuilder() {
            user = new User();
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder setId(int id) {
            user.setId(id);
            return this;
        }

        public UserBuilder setName(String name) {
            user.setName(name);
            return this;
        }

        public UserBuilder setFamily(String family) {
            user.setFamily(family);
            return this;
        }

        public UserBuilder setUsername(String username) {
            user.setUsername(username);
            return this;
        }

        public UserBuilder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public UserBuilder setBirthDate(Date birthDate) {
            user.setBirthDate(birthDate);
            return this;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
