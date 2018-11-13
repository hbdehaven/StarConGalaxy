package model;

import java.util.*;

public class User extends Observer{
    private String name;
    private List<StellarObject> haveSeen;
    private List<StellarObject> wantToSee;

    public User(String name){
        this.name = name;
        haveSeen = new ArrayList<>();
        wantToSee = new ArrayList<>();
    }

    public void addStellarObjectWantToSee(StellarObject so){
        if (!wantToSee.contains(so)){
            wantToSee.add(so);
        }
    }

    // remove SO from wanttoSee, add to have seen;
    // subsequently called addUserSeen
    public void addStellarObjectHaveSeen(StellarObject so){
        if (wantToSee.contains(so) && !(haveSeen.contains(so))){
            wantToSee.remove(so);
            haveSeen.add(so);
        }
    }

    // EFFECTS: print have seen list
    public void printHaveSeenList() {
        System.out.println("Have Seen: ");
        for (StellarObject so: haveSeen){
            System.out.println(so.getName());
        }
        System.out.println("/////////");
    }

    // EFFECTS: print want to see list
    public void printWantToSeeList() {
        System.out.println("Would Like to See: ");
        for (StellarObject so: wantToSee){
            System.out.println(so.getName());
        }
        System.out.println("/////////");
    }

    // getters
    public String getName(){
        return name;
    }

    public List<StellarObject> getHaveSeen() {
        return haveSeen;
    }

    public List<StellarObject> getWantToSee() {
        return wantToSee;
    }

    // setters
    public void setHaveSeen(List<StellarObject> haveSeen) {
        this.haveSeen = haveSeen;
    }

    public void setWantToSee(List<StellarObject> wantToSee) {
        this.wantToSee = wantToSee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public void update() {
        System.out.println(getName() + " you!, have viewed NASA Picture of the Day!");
    }
}
