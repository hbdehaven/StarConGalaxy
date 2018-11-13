package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private static List<Observer> observers;

    public Subject(){
        observers = new ArrayList<>();
    }

    public static void addObserver(Observer observer){
        observers.add(observer);
    }

    public static void notifyObservers(){
        for (Observer observer: observers){
            observer.update();
        }
    }
}
