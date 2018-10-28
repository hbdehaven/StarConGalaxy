package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User implements Loadable, Saveable{
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
            so.addUserWantToSee(this);
        }
    }

    // remove SO from wanttoSee, add to have seen;
    // subsequently called addUserSeen
    public void addStellarObjectHaveSeen(StellarObject so){
        if (wantToSee.contains(so) &&!haveSeen.contains(so)){
            wantToSee.remove(so);
            haveSeen.add(so);
            so.addUserSeen(this);
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

    //setters
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void load(Scanner scanner) throws IOException {
        String value = scanner.nextLine();
        String[] values = value.split(",");
        this.name = values[0];
        //this.haveSeen = ;
        //this.wantToSee = ;
    }

    @Override
    public void save(String fileName) throws IOException {
        Files.write(Paths.get(fileName), Arrays.asList(name + "," + haveSeen + "," + wantToSee ),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
