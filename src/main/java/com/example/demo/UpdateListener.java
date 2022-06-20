package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public interface UpdateListener {


    void pushUpdate(OutputUpdate update);
    List<UpdateType> getSubscribedUpdateTypes();

}
