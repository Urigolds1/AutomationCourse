package main.infrastructure;

public interface Users {
    class User {
        public String email;
        public String password;

        public User(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    User TESTIL = new User("testil@gmail.com", "Aa111111");
}
