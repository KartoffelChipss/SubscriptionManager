package org.strassburger.subscriptionmanager.model.entity;

public class Profile {
    private final int id;
    private final String name;
    private String pin;

    public Profile(int id, String name, String pin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
    }

    public Profile(int id, String name) {
        this.id = id;
        this.name = name;
        this.pin = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean requiresPin() {
        return pin != null;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}
