package com.griddynamics.flashcards.action;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static com.griddynamics.flashcards.game.FlashcardGame.getUserInput;
import static com.griddynamics.flashcards.game.FlashcardGame.outputMsg;

/**
 * The class is intended to execute the 'export' function
 */
public class ExportAction implements Action {
    private final Map<String, String> flashcards;
    private final Map<String, Integer> mistakes;

    /**
     * The constructor for the class.<br>
     *
     * @param flashcards a flashcards map
     * @param mistakes   a map consisting of number of mistakes for each card
     */
    public ExportAction(Map<String, String> flashcards, Map<String, Integer> mistakes) {
        this.flashcards = flashcards;
        this.mistakes = mistakes;
    }

    /**
     * The method executing the 'export' operation.<br>
     * A user inputs name of a file to which flashcards should be exported from the application.<br>
     * The file is located under the src/main/resources/export/ directory.
     */
    @Override
    public void execute() {
        outputMsg("File name:");
        String fileName = getUserInput();
        execute(fileName);
    }

    /**
     * The method for exporting flashcards and mistakes maps to a file.<br>
     * A separator for term/definition/mistakes is the '=' character.<br>
     *
     * @param fileName name of a file
     */
    public void execute(String fileName) {
        String path = String.format("src/main/resources/export/%s", fileName);
        try (FileWriter fileWriter = new FileWriter(path)) {
            for (Map.Entry<String, String> card : flashcards.entrySet()) {
                fileWriter.write(card.getKey() + "=" + card.getValue() + "=" + mistakes.get(card.getKey()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputMsg(flashcards.size() + " cards have been saved.");
    }
}