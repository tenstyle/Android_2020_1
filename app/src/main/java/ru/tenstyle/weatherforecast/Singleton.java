package ru.tenstyle.weatherforecast;

public final class Singleton {

    private static Singleton instance = null;
    private static final Object syncObject = new Object();
    private int counter;
    private int counter2;

    private Singleton() {
        counter = 0;
        counter2 = 0;
    }

    public void incrementCounter() {
        counter++;
        counter2 = counter2 + 11;
    }

    public int getCounter() {
        return counter;
    }

    public int getCounter2() {
        return counter2;
    }

    public static Singleton getInstance() {
        synchronized (syncObject) {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
}
