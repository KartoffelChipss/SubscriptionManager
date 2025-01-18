package org.strassburger.abomanager.model.entity;

public class Profile {
    private final int id;
    private final String name;
    private String pin;
    private boolean requiresPin;

    public Profile(int id, String name, String pin, boolean requiresPin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.requiresPin = requiresPin;
    }

    public Profile(int id, String name) {
        this.id = id;
        this.name = name;
        this.pin = null;
        this.requiresPin = false;
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

    public boolean requiresPin() {
        return requiresPin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setRequiresPin(boolean requiresPin) {
        this.requiresPin = requiresPin;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pin='" + pin + '\'' +
                ", requiresPin=" + requiresPin +
                '}';
    }
}
