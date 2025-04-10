import java.util.Arrays;
import repository.*;
import service.*;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepo = new UserRepository();
        TweetRepository tweetRepo = new TweetRepository();
        UserService userService = new UserService(userRepo);
        TweetService tweetService = new TweetService(userRepo, tweetRepo);

        try {
            userService.createUser("Gopal");
            userService.createUser("Madhav");
            userService.createUser("Lucky");
            userService.createUser("Laxman");

            userService.addFollowers("Gopal", Arrays.asList("Madhav", "Lucky", "Laxman"));
            userService.addFollowers("Lucky", Arrays.asList("Laxman"));
            // userService.addFollowers("Swati", Arrays.asList("Laxman"));

            tweetService.postTweet("Good morning", "Gopal");
            tweetService.postTweet("Good night", "Gopal");

            tweetService.getUserTweets("Gopal"); 

            tweetService.postTweet("Bonjour", "Lucky"); 

            tweetService.getUserFeed("Laxman"); 
            tweetService.getUserFeed("Madhav"); 

            tweetService.hideTweetForUser("Madhav", 1);
            tweetService.getUserFeed("Madhav"); 

            tweetService.deleteTweet(2);
            //tweetService.deleteTweet(10);
            tweetService.getUserFeed("Laxman"); 
            tweetService.getUserFeed("Madhav");
            tweetService.getUserFeed("Gopal"); 

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}