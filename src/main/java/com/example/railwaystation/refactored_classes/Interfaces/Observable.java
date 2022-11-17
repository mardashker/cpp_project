package com.example.railwaystation.refactored_classes.Interfaces;

import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.refactored_classes.Helpers.UserProcessedEventArgs;

public interface Observable {

    public void subscribe(Observer observer);
    public void notifySubscribers(UserProcessedEventArgs user);
    public boolean unsubscribe(Observer observer);
}
