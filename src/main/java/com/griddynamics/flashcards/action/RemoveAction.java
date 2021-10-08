package com.griddynamics.flashcards.action;

import com.griddynamics.flashcards.game.FlashcardGame;

import java.util.Map;

/**
 * The class is intended to execute the 'remove' function
 */
public class RemoveAction implements Action {
    private final Map<String, String> flashcards;
    private final Map<String, Integer> mistakes;

    /**
     * The constructor for the class.<br>
     *
     * @param flashcards a flashcards map
     * @param mistakes   a map consisting of number of mistakes for each card
     */
    public RemoveAction(Map<String, String> flashcards, Map<String, Integer> mistakes) {
        this.flashcards = flashcards;
        this.mistakes = mistakes;
    }

    /**
     * The method executes 'remove' action
     * A user enters term of a card to be removed.
     * The method removes entry by term(key) from the flashcards map and the mistakes map.
     * The method outputs the following messages:
     * <li> if removal successful - "The card has been removed."
     * <li> if card with the term was not found - "Can't remove "{term}": there is no such card."
     */
    @Override
    public void execute() {
        FlashcardGame.outputMsg("Which card?");
        String term = FlashcardGame.getUserInput();
        if (flashcards.containsKey(term)) {
            flashcards.remove(term);
            mistakes.remove(term);
            FlashcardGame.outputMsg("The card has been removed.");
        } else {
            FlashcardGame.outputMsg("Can't remove \"" + term + "\": there is no such card.");
        }
    }
}

