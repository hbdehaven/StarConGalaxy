package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingDatabase {
    //map user to list of rating; ove
    private static Map<User, List<Rating>> userListMap;

    public RatingDatabase(){
        userListMap = new HashMap<User, List<Rating>>();
        
        User dummyuser = new User("dummyUser");
        List<Rating> dummyLOR = null;
        userListMap.put(dummyuser, dummyLOR);
    }

    public static void addUserListRating(User user, Rating rating){
        List<Rating> lor;
        if (!userListMap.containsKey(user)){
            lor = new ArrayList<>();
            lor.add(rating);
            userListMap.put(user, lor);
        }
        else {
            lor = retrieveRatings(user);
            lor.add(rating);
        }

    }

    public static List<Rating> retrieveRatings(User user){
           return userListMap.get(user);
    }

}
