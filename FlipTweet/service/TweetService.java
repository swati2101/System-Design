package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import entity.*;
import repository.*;

public class TweetService {
    private final UserRepository userRepo;
    private final TweetRepository tweetRepo;

    public TweetService(UserRepository userRepo, TweetRepository tweetRepo) {
        this.userRepo = userRepo;
        this.tweetRepo = tweetRepo;
    }

    public void postTweet(String text, String userName) {
        User user = userRepo.getUser(userName);
        Tweet tweet = new Tweet(text, userName);
        user.postTweet(tweet);
        tweetRepo.saveTweet(tweet);
        System.out.println("Tweet posted by " + userName + " is " + tweet.getText() + " with id " + tweet.getId());
    }

    public void getUserTweets(String userName) {
        User user = userRepo.getUser(userName);
        System.out.println("Tweets by " + userName + " are");
        for (Tweet tweet : user.getTweets()) {
            if (!tweet.isDeleted()) {
                System.out.println(tweet.getText());
            }
        }
    }

    public void getUserFeed(String userName) {
        User currentUser = userRepo.getUser(userName);
        List<String> result = new ArrayList<>();
        for (User user : userRepo.getAllUsers()) {
            if (user.getFollowers().contains(userName)) {
                for (Tweet tweet : user.getTweets()) {
                    if (!tweet.isDeleted() && !isHidden(currentUser, user.getName(), tweet.getId())) {
                        result.add(tweet.getText());
                    }
                }
            }
        }
        if (result.isEmpty())
            System.out.println("<No output>");
        else {
            System.out.println("UserFeed of " + userName + " is");
            result.forEach(System.out::println);
        }
    }

    public void hideTweetForUser(String userName, int tweetId) {
        Tweet tweet = tweetRepo.getTweet(tweetId);
        User user = userRepo.getUser(userName);
        System.out.println("Feed for " + userName + " BEFORE hiding tweetId " + tweetId + ":");
        getUserFeed(userName);
        user.getHiddenTweetsFromUsers()
                .computeIfAbsent(tweet.getPostedBy(), k -> new HashSet<>())
                .add(tweetId);
        System.out.println("Feed for " + userName + " AFTER hiding tweetId " + tweetId + ":");
        getUserFeed(userName);
    }

    public void deleteTweet(int tweetId) {
        tweetRepo.deleteTweet(tweetId);
    }

    private boolean isHidden(User user, String postedBy, int tweetId) {
        return user.getHiddenTweetsFromUsers()
                .getOrDefault(postedBy, Collections.emptySet())
                .contains(tweetId);
    }
}
