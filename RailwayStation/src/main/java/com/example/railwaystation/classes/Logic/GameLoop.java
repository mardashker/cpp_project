package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.GameLevel;

//TODO: клас для управління всієї логіки програми
public class GameLoop {
//    private GameLevel level;
//    public static final int MAX_USER_NUMBER = 25;
//    public GameLoop(Game game){
//        this.level = game.get_currentLevel();
//    }
//
//    public void run(){
//        while (true){
//            drawFrame();
//        }
//    }
//
//    private void drawFrame(){
//
//    }
//
//    private void moveAllCustomers(){
//        level.get_poligons()
//                .forEach(p -> moveQueue(p));
//        moveMovingUsers();
//    }
//
//    private void moveQueue(QueuePoligon queuePoligon){
//        //get necessary data
//        var queue = queuePoligon.get_queue();
//        var firstCellCoordinates = queuePoligon.get_queueCells().stream()
//                .findFirst()
//                .get().getPosition();
//        var firstUserPosition = queue.getHeadUsers().getPosition();
//
//        //if the first possition is occupied then it's all we can do
//        if(firstUserPosition.equals(firstCellCoordinates)){
//            return;
//        }
//
//        //else we move each user
//    }
//
//    private void moveEach(QueuePoligon queuePoligon){
//        for(var u : queuePoligon.get_queue().getUsersQueue()){
//            var currentPos = u.getPosition();
//            var cells = queuePoligon.get_queueCells();
//            for(var cell : cells){
//
//
//            }
//
//        }
//    }
//
//    private void moveMovingUsers(){
//
//    }
//
//    private List<User> getNewUsers(){
//        var newUsers = game.currentLevel.get_generators().stream()
//                .map(g -> g.generateUser())
//                .toList();
//
//        newUsers.removeAll(null);
//        return newUsers;
//    }
//
//    private boolean stationIsOverloaded(){
//        return PeopleMath.countUsersInStation(this.game.currentLevel)  > MAX_USER_NUMBER;
//    }
}
//
//class GameLoop{
//
//    private Game game;
//    public static final int MAX_USER_NUMBER = 25;
//
//    public GameLoop(Game game){}
//    public void run(){}
//
//    public
//
//
//}
