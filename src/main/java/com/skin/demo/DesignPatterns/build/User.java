package com.skin.demo.DesignPatterns.build;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/5/7 10:47
 */
public class User {
    private String username;
    private String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class UserBuilder {
        private String username;
        private String password;

        UserBuilder() {
        }

        public User.UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public User.UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this.username, this.password);
        }

        @Override
        public String toString() {
            return "User.UserBuilder(username=" + this.username + ", password=" + this.password + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println(User.builder().username("admin").password("123").build());
    }
}
