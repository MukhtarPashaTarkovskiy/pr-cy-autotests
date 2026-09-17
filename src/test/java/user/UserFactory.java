package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("sausedemo.user"),
                PropertyReader.getProperty("sausedemo.password"));
    }

    public static User withLockedPermission() {
        return new User(PropertyReader.getProperty("sausedemo.locked.user"),
                PropertyReader.getProperty("sausedemo.password"));
    }
}
