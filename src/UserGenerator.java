import java.util.LinkedList;
import java.util.List;

public class UserGenerator implements UserGeneratedEventHandler {
    private List<UserGeneratedEventListener> listeners;

    public UserGenerator() {
        this.listeners = listeners = new LinkedList<>();
    }
    @Override
    public void subscribeUserGeneratedListener(UserGeneratedEventListener listener) {
        listeners.add(listener);
    }
    @Override
    public void unsubscribeUserGeneratedListener(UserGeneratedEventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void startUserGeneration(){
        //While
        //{
        //  some code
        User user = new User();
        handleEvent(user);
        //}
    }

    @Override
    public void stopUserGeneration() {

    }

    public void handleEvent(User user){
        for (UserGeneratedEventListener listener: listeners) {
            listener.onUserGenerated(user);
        }
    }
}
