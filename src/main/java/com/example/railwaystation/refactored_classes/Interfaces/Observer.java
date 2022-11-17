package com.example.railwaystation.refactored_classes.Interfaces;

import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.refactored_classes.Helpers.UserProcessedEventArgs;

public interface Observer {

    public void process(UserProcessedEventArgs user);
}
