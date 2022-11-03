package Game;

enum State{
    MOVING,
    PROCESSING, //користувача обслуговує каса
    PENDING //стоїть в черзі
}

public class UserGame  {

    protected float speed;

    protected State state;

    public Priority getPriority() {
        return priority;
    }

    protected Priority priority;
}
