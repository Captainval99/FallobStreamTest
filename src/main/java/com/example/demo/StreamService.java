package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StreamService implements Runnable, UpdateListener{
    private final ResponseBodyEmitter emitter;

    public StreamService(ResponseBodyEmitter emitter) {
        this.emitter = emitter;
    }


    @Override
    public void run() {

        UpdateDistributor distributor = UpdateDistributor.getInstance();
        distributor.addUpdateListener(this);

        try {
            this.emitter.send("Stream connected!\n", MediaType.TEXT_PLAIN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void pushUpdate(OutputUpdate update) {
        /**
        synchronized (this) {
            this.notify();
        }
        **/

        try {
            this.emitter.send(update.getMessage() + "\n", MediaType.TEXT_PLAIN);
        } catch (IOException e) {
            //end the emitter
            this.emitter.complete();

            //remove the observer
            UpdateDistributor distributor = UpdateDistributor.getInstance();
            distributor.removeUpdateListener(this);

            System.out.println("Connection was closed by user");
        }

        /**
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        **/

    }

    @Override
    public List<UpdateType> getSubscribedUpdateTypes() {
        List<UpdateType> updateTypes = new ArrayList<>();
        updateTypes.add(UpdateType.EVENT);
        return updateTypes;
    }
}
