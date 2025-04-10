package entity;

import util.IdGenerator;

public class Tweet {
    private final int tweetId;
    private final String text;
    private final String postedBy;
    private boolean isDeleted;

    public Tweet(String text, String postedBy) {
        this.tweetId = IdGenerator.getInstance().generateTweetId();
        this.text = text;
        this.postedBy = postedBy;
        this.isDeleted = false;
    }

    public int getId() {
        return tweetId;
    }

    public String getText() {
        return text;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void markDeleted() {
        this.isDeleted = true;
    }
}