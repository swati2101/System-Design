package service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.User;
import exceptions.UserNotFoundException;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(String name) {
        System.out.println("User created " + name);
        userRepo.addUser(name);
    }

    public void addFollowers(String userName, List<String> followers) {
        User user = userRepo.getUser(userName);
        Set<String> validFollowers = new HashSet<>();
        for (String followerName : followers) {
            if (!userRepo.exists(followerName)) {
                throw new UserNotFoundException("Follower not found: " + followerName);
            }
            validFollowers.add(followerName);
        }
        user.addFollowers(validFollowers);
        System.out.println("Follower list of " + userName + " " + "is " + validFollowers);
    }
}
