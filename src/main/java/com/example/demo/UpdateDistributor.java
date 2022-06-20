package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UpdateDistributor {
    private final List<UpdateListener> updateListeners;
    private static UpdateDistributor updateDistributor= null;

    private UpdateDistributor() {
        this.updateListeners = new CopyOnWriteArrayList<>();
    }

    public void addUpdateListener(UpdateListener listener) {
        synchronized (this.updateListeners) {
            this.updateListeners.add(listener);
        }
    }

    public void removeUpdateListener(UpdateListener listener) {
        synchronized (this.updateListeners) {
            this.updateListeners.remove(listener);
        }
    }

    protected void distributeUpdate(OutputUpdate newUpdate) {
        synchronized (this.updateListeners) {
            for (UpdateListener listener : this.updateListeners) {
                listener.pushUpdate(newUpdate);
            }
        }
    }

    public static UpdateDistributor getInstance() {
        if (updateDistributor == null) {
            updateDistributor = new UpdateDistributor();
        }
        return updateDistributor;
    }
}
