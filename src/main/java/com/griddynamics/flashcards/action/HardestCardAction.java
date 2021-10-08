package com.griddynamics.flashcards.action;

import com.griddynamics.flashcards.game.FlashcardGame;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * The class is intended to execute the 'hardest card' function
 */
public class HardestCardAction implements Action {
    private final Map<String, Integer> mistakes;

    /**
     * The constructor for the class.<br>
     *
     * @param mistakes a map consisting of number of mistakes for each card
     */
    public HardestCardAction(Map<String, Integer> mistakes) {
        this.mistakes = mistakes;
    }

    /**
     * The method executing the 'hardest card' operation.<br>
     * The method get max number of mistakes from the mistakes map.<br>
     * If the max number of mistakes is 0, the method outputs a message saying about lack of cards with errors.<br>
     * Then, the method gets a list of terms of cards having the max number of mistakes.<br>
     * The method outputs a message naming the hardest card(s) and the max number of mistakes.
     */
    @Override
    public void execute() {
        int maxValue = getMaxNumberOfMistakes();
        if (maxValue <= 0) {
            FlashcardGame.outputMsg("There are no cards with errors.");
            return;
        }
        List<String> result = getListMaxMistakesCardTerms(maxValue);
        if (result.size() > 1) {
            String cards = String.join("\", \"", result);
            FlashcardGame.outputMsg("The hardest cards are \"" + cards +
                    "\". Your have " + maxValue + " errors answering them");
        } else if (result.size() == 1) {
            FlashcardGame.outputMsg("The hardest card is \"" + result.get(0) +
                    "\". You have " + maxValue + " errors answering it");
        }
    }

    /**
     * The method returns a max number of mistakes
     *
     * @return the max value of the mistakes map
     */
    private int getMaxNumberOfMistakes() {
        return mistakes.values()
                .stream()
                .mapToInt(x -> x)
                .max()
                .orElse(-1);
    }

    /**
     * The method returns a list of terms having the max number of mistakes from the mistakes map.
     *
     * @param maxValue max number of mistakes
     * @return list of terms with the max number of mistakes
     */
    private List<String> getListMaxMistakesCardTerms(int maxValue) {
        List<Map.Entry<String, Integer>> entriesWithMaxMistakes = mistakes.entrySet()
                .stream()
                .filter(y -> y.getValue() == maxValue)
                .collect(toList());
        return entriesWithMaxMistakes
                .stream()
                .map(Map.Entry::getKey)
                .collect(toList());
    }
}
