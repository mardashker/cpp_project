package Entities;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;


public final class Logger implements Flow.Subscriber<String> {
    private static volatile Logger instance;
    private Flow.Subscription subscription;
    public List<String> consumedElements = new LinkedList<>();


    public static Logger getInstance() {
        Logger result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Logger.class) {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }
    }

    /**
     * This method is triggered when the Subscriber subscribes to a Publisher
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    /**
     * This method is triggered the Subscriber receives an event
     * signaling an item being sent from the publisher. The Item is simply printed here.
     */
    @Override
    public void onNext(String item) {
        System.out.println("Received Letter: " + item);
        consumedElements.add(item);
        subscription.request(1);
    }

    /**
     * This method is triggered when the Subscriber receives an error event.
     * In our case we just print the error message.
     */
    @Override
    public void onError(Throwable error) {
        System.out.println("Error Occurred: " + error.getMessage());
    }

    /**
     * This method is triggered when the Subscriber Receives a complete. This means
     * it has already received and processed all items from the publisher to which it is subscribed.
     */
    @Override
    public void onComplete() {
        System.out.println("Every Element has been received");
    }
}