package exceptions;

public class TweetNotFoundException extends RuntimeException {
    public TweetNotFoundException(String msg) {
        super(msg);
    }
}
