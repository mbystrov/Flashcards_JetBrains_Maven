package com.griddynamics.flashcards.action;

import com.griddynamics.flashcards.game.FlashcardGame;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static com.griddynamics.flashcards.game.FlashcardGame.outputMsg;

/**
 * The class is intended to execute the 'import' function
 */
public class ImportAction implements Action {
    private final Map<String, String> flashcards;
    private final Map<String, Integer> mistakes;

    /**
     * The constructor for the class.<br>
     *
     * @param flashcards a flashcards map
     * @param mistakes   a map consisting of number of mistakes for each card
     */
    public ImportAction(Map<String, String> flashcards, Map<String, Integer> mistakes) {
        this.flashcards = flashcards;
        this.mistakes = mistakes;
    }

    /**
     * The method executes the 'import' action.<br>
     * A user inputs the name of a file from which flashcards are imported.
     */
    @Override
    public void execute() {
        outputMsg("File name:");
        String fileName = FlashcardGame.getUserInput();
        execute(fileName);
    }

    /**
     * The method imports cards from the fileName.<br>
     * Cards parts should be separated by '=' in the import file.<br>
     * <ul>
     * <li>for instance: term=definition=numberOfMistakes
     * </ul>
     * If import file has improper value of mistakes number, the value is set to 0.<br>
     * The method outputs number of imported cards.
     * The prefix for the file path is 'src/main/resources/import/'.
     * @param fileName name of a file to import
     */
    public void execute(String fileName) {
        int cardsCounter = 0;
        String path = String.format("src/main/resources/import/%s", fileName);
        try (Scanner fileScanner = new Scanner(new File(path))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.contains("=")) {
                    continue;
                }
                String[] card = line.split("=");
                int mistakesFromFile = 0;
                if (card.length == 3) {
                    try {
                        mistakesFromFile = Integer.parseInt(card[2]);
                    } catch (NumberFormatException e) {
                        outputMsg("Invalid number of mistakes in the '" + card[0] + "' card. Number of mistakes was set to 0");
                    }
                }
                flashcards.put(card[0], card[1]);
                mistakes.put(card[0], mistakesFromFile);
                cardsCounter++;
            }
        } catch (IOException e) {
            outputMsg("File not found.");
        }
        outputMsg(cardsCounter + " cards have been loaded.");
    }
}