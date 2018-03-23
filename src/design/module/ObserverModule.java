package design.module;

import java.util.Observable;

class ConcreSubject extends Observable{

    public void setData() {
        this.setChanged();
    }
}
public class ObserverModule {
    public static void main(String[] args) {
        ConcreSubject observable = new ConcreSubject();
        observable.setData();
        observable.addObserver(new ConcreObserver());
        observable.notifyObservers();
    }
}

