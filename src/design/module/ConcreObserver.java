package design.module;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体观察者
 */
public class ConcreObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("11111111111");
    }
}
