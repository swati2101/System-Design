package util;

import java.util.concurrent.atomic.AtomicInteger;
public class IdGenerator {
    private static IdGenerator instance;
    private final AtomicInteger tweetIdCounter;

    private IdGenerator() {
        tweetIdCounter = new AtomicInteger(1);
    }

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public int generateTweetId() {
        return tweetIdCounter.getAndIncrement();
    }
}