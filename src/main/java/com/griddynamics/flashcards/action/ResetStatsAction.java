package com.griddynamics.flashcards.action;

import java.util.Map;

import static com.griddynamics.flashcards.game.FlashcardGame.outputMsg;

/**
 * The class is intended to execute the 'reset stats' function
 */
public class ResetStatsAction implements Action {
    private final Map<String, Integer> mistakes;

    /**
     * The constructor for the class.<br>
     *
     * @param mistakes a map consisting of number of mistakes for each card
     */
    public ResetStatsAction(Map<String, Integer> mistakes) {
        this.mistakes = mistakes;
    }

    /**
     * The method executes 'reset stats' action.<br>
     * The method resets all values in the mistakes map.<br>
     */
    @Override
    public void execute() {
        mistakes.replaceAll((k, v) -> v = 0);
        outputMsg("Card statistics have been reset.");
    }
}
