package repository;

import exceptions.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import entity.*;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public void addUser(String name) {
        if (users.containsKey(name)) {
            throw new UserAlreadyExistsException("User already exists: " + name);
        }
        users.put(name, new User(name));
    }

    public User getUser(String name) {
        if (!users.containsKey(name)) {
            throw new UserNotFoundException("User not found: " + name);
        }
        return users.get(name);
    }

    public boolean exists(String name) {
        return users.containsKey(name);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }
}
