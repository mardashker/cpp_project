package Game;

enum State{
    MOVING,
    PROCESSING, //користувача обслуговує каса
    PENDING //стоїть в черзі

}

public class UserGame  {

    private float speed;

    private State state;
    public int priority;
}
