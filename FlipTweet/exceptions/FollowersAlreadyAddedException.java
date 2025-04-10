package exceptions;

public class FollowersAlreadyAddedException extends RuntimeException {
    public FollowersAlreadyAddedException(String msg) {
        super(msg);
    }
}