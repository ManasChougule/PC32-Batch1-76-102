package dao;

import entity.User;

public interface UserDao {
    boolean validateUser(User user);
}
