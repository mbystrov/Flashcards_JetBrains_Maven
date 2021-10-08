package com.griddynamics.flashcards.action;

import com.griddynamics.flashcards.game.FlashcardGame;

import java.util.Map;

/**
 * The class is intended to execute the 'add' function
 */
public class AddAction implements Action {
    private final Map<String, String> flashcards;
    private final Map<String, Integer> mistakes;

    /**
     * The constructor for the class.<br>
     *
     * @param flashcards a flashcards map
     * @param mistakes   a map consisting of number of mistakes for each card
     */
    public AddAction(Map<String, String> flashcards, Map<String, Integer> mistakes) {
        this.flashcards = flashcards;
        this.mistakes = mistakes;
    }

    /**
     * The method executing the 'add' operation.<br>
     * A user inputs term, definition of a card.<br>
     * Then, if they pass validation, the new card is put in flashcard and mistakes maps and a success message appears.<br>
     */
    @Override
    public void execute() {
        FlashcardGame.outputMsg("The Card:");
        String term = getCardTerm();
        if (term == null) return;
        FlashcardGame.outputMsg("The definition of the card:");
        String definition = getCardDefinition();
        if (definition == null) return;
        flashcards.put(term, definition);
        mistakes.put(term, 0);
        FlashcardGame.outputMsg("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }

    /**
     * The method to get card term.<br>
     * The method receives user's input, then checks if an entered term exists in the flashcards map.<br>
     *
     * @return if the flashcards map contains a term, method returns null. Otherwise - the term itself.
     */
    private String getCardTerm() {
        String term = FlashcardGame.getUserInput();
        if (flashcards.containsKey(term)) {
            FlashcardGame.outputMsg("The card \"" + term + "\" already exists.");
            return null;
        }
        return term;
    }

    /**
     * The method to get card definition.<br>
     * The method receives user's input, then checks if an entered term exists in the flashcards map.<br>
     *
     * @return if the flashcards map contains a definition, method returns null. Otherwise - the definition itself.
     */
    private String getCardDefinition() {
        String definition = FlashcardGame.getUserInput();
        if (flashcards.containsValue(definition)) {
            FlashcardGame.outputMsg("The definition \"" + definition + "\" already exists.");
            return null;
        }
        return definition;
    }
}

