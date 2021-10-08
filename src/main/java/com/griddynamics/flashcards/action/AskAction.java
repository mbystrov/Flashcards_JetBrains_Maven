package com.griddynamics.flashcards.action;

import com.griddynamics.flashcards.game.FlashcardGame;

import java.util.Map;

/**
 * The class is intended to execute the 'ask' function
 */
public class AskAction implements Action {
    private final Map<String, String> flashcards;
    private final Map<String, Integer> mistakes;

    /**
     * The constructor for the class.<br>
     *
     * @param flashcards a flashcards map
     * @param mistakes   a map consisting of number of mistakes for each card
     */
    public AskAction(Map<String, String> flashcards, Map<String, Integer> mistakes) {
        this.flashcards = flashcards;
        this.mistakes = mistakes;
    }

    /**
     * The method executing the 'ask' operation.<br>
     * A user inputs number of cards to be asked.<br>
     * Then, the application outputs term of a card obliging the user to write a definition.<br>
     * If the user was wrong mistakes number for a card increases by 1.<br>
     * If user was correct, a success message appears, otherwise there are two cases possible:
     * <li>User gives answer which is correct for another flashcard, then, a message saying a correct answer and a term fitted this definition appears
     * <li>User gives answer which is incorrect for all flashcards, then, a message saying only a correct answer appears
     *
     */
    @Override
    public void execute() {
        int askTimes = getNumberOfQuestions();
        for (String term : flashcards.keySet()) {
            FlashcardGame.outputMsg("Print the definition of \"" + term + "\":");
            String answer = FlashcardGame.getUserInput();
            if (flashcards.get(term).equals(answer)) {
                FlashcardGame.outputMsg("Correct!");
            } else if (flashcards.containsValue(answer)) {
                FlashcardGame.outputMsg("Wrong. The right answer is \"" + flashcards.get(term) + "\", but your definition is correct for \"" + getTermByDefinition(answer) + "\".");
                mistakes.put(term, mistakes.get(term) + 1);
            } else {
                FlashcardGame.outputMsg("Wrong. The right answer is \"" + flashcards.get(term) + "\".");
                mistakes.put(term, mistakes.get(term) + 1);
            }
            askTimes--;
            if (askTimes <= 0) {
                return;
            }
        }
    }

    /**
     * A method for receiving term by its definition.<br>
     *
     * @param definition a definition of a card
     * @return a term corresponding to the definition
     */
    private String getTermByDefinition(String definition) {
        for (Map.Entry<String, String> flashcard : flashcards.entrySet()) {
            if (flashcard.getValue().equals(definition)) {
                return flashcard.getKey();
            }
        }
        return null;
    }

    /**
     * A method verifying a string contains a positive integer number.<br>
     *
     * @param str a string containing a value
     * @return boolean true if string contains a positive integer, false - otherwise
     */
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private int getNumberOfQuestions() {
        while (true) {
            FlashcardGame.outputMsg("How many times to ask?");
            String rawAskTimes = FlashcardGame.getUserInput();
            if (isNumeric(rawAskTimes)) {
                return processAskTimesNumber(rawAskTimes);
            } else {
                FlashcardGame.outputMsg("Input a positive number, please");
            }
        }
    }

    /**
     * A method which processes rawAskTimes value and prevents it to exceed a max integer value.<br>
     *
     * @param rawAskTimes a number in string format
     * @return int value
     */
    private int processAskTimesNumber(String rawAskTimes) {
        try {
            return Integer.parseInt(rawAskTimes);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}