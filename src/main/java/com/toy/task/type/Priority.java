package com.toy.task.type;

public enum Priority {
    LOW(20),
    NORMAL(35),
    HIGH(55);

    private final int xp;

    Priority(int xp) {
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }
}
