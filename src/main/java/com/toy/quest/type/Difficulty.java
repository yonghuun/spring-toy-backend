package com.toy.quest.type;

public enum Difficulty {
    EASY(20),
    NORMAL(35),
    HARD(55);

    private final int xp;

    Difficulty(int xp) {
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }
}
