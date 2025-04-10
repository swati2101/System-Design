package repository;

import java.util.HashMap;
import java.util.Map;
import exceptions.*;
import entity.*;

public class TweetRepository {
    private static TweetRepository instance;
    private final Map<Integer, Tweet> tweetMap = new HashMap<>();

    public static synchronized TweetRepository getInstance() {
        if (instance == null) {
            instance = new TweetRepository();
        }
        return instance;
    }

    public void saveTweet(Tweet tweet) {
        tweetMap.put(tweet.getId(), tweet);
    }

    public Tweet getTweet(int id) {
        if (!tweetMap.containsKey(id)) {
            throw new TweetNotFoundException("Tweet not found with id: " + id);
        }
        return tweetMap.get(id);
    }

    public void deleteTweet(int id) {
        Tweet tweet = getTweet(id);
        tweet.markDeleted();
    }
}
