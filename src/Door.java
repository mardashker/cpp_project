public class Door implements UserGeneratedEventListener {

    private UserGeneratedEventHandler handler;

    public Door() {
        handler = new UserGenerator();
        handler.subscribeUserGeneratedListener(this);
    }

    @Override
    public void onUserGenerated(User user) {
        // add user to the field
    }

    public void OpenDoor(){
        //start generation in new thread
        handler.startUserGeneration();
    }

    public void CloseDoor(){
        //stop generation
        handler.stopUserGeneration();
    }

}
