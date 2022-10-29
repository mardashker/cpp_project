public interface UserGeneratedEventHandler {
    void subscribeUserGeneratedListener(UserGeneratedEventListener listener);
    void unsubscribeUserGeneratedListener(UserGeneratedEventListener listener);
    void startUserGeneration();
    void stopUserGeneration();
}
