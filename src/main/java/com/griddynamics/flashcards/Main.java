package com.griddynamics.flashcards;

import com.griddynamics.flashcards.game.FlashcardGame;

public class Main {
    public static void main(String[] args) {
        FlashcardGame flashCardGame = new FlashcardGame();
        flashCardGame.startGame(args);
    }
}