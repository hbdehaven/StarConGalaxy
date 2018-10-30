package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    //map user to list of rating; ove
    private Map<User, List<Rating>> userListMap;

    public Database(){
        this.userListMap = new HashMap<>();
    }

    public void addUserListRating(User user, Rating rating){
        if (userListMap.containsKey(user)){
            List<Rating> lor = userListMap.get(user);
            lor.add(rating);
        }
        else {
            List<Rating> lor = new ArrayList<>();
            lor.add(rating);
            userListMap.put(user, lor);
        }
    }

    public List<Rating> retrieveRatings(User user){
           return userListMap.get(user);
    }
}
