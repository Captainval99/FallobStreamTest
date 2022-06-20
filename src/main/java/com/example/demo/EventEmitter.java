package com.example.demo;

public class EventEmitter implements Runnable{

    @Override
    public void run() {
        UpdateDistributor distributor = UpdateDistributor.getInstance();

        while (true) {
            OutputUpdate update = new OutputUpdate("this is an event!");
            distributor.distributeUpdate(update);
            System.out.println("distributed update");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
