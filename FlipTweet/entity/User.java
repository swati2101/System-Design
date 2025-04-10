package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import exceptions.*;

public class User {
    private final String name;
    private Set<String> followers = new HashSet<>();
    private final List<Tweet> tweets = new ArrayList<>();
    private final Map<String, Set<Integer>> hiddenTweetsFromUsers = new HashMap<>();
    private boolean followersAdded = false;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<String> getFollowers() {
        return followers;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public Map<String, Set<Integer>> getHiddenTweetsFromUsers() {
        return hiddenTweetsFromUsers;
    }

    public void addFollowers(Set<String> followers) {
        if (followersAdded) {
            throw new FollowersAlreadyAddedException("Followers already added for user: " + name);
        }
        this.followers.addAll(followers);
        followersAdded = true;
    }

    public void postTweet(Tweet tweet) {
        tweets.add(tweet);
    }
}