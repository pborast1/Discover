package com.example.pareshboraste.doordash.discover.di;

public class DoorDashSingleton {
    private static DoorDashSingleton instance;
    private DoorDashComponent component;

    private DoorDashSingleton() {
        this.component = DaggerDoorDashComponent.create();
    }

    public static synchronized DoorDashSingleton getInstance() {
        if (instance == null) {
            instance = new DoorDashSingleton();
        }
        return instance;
    }

    public DoorDashComponent getComponent() {
        return component;
    }
}
