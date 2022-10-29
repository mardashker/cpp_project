package Moduls;

//стан каси
enum State{
    OPEN,
    CLOSE,
    WORKING
}

public class CashRegister {

    private OurQueue ourQueue;

    private State state;

    private boolean isOpen;

    public void processUser(){
        //TODO: процес обробки першого юзера з черги
    }

    public void updateQueue(){
        //TODO: повідомляє про те що обробили ще одного юзера або взяли ще одного юзера
    }

    public void closeCashRegister(){
        //TODO: закрити касу
    }

    public void openCashRegister(){
        //TODO: відкрити касу
    }

}
