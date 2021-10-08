package com.griddynamics.flashcards.action;

import java.io.FileWriter;
import java.io.IOException;

import static com.griddynamics.flashcards.game.FlashcardGame.*;

/**
 * The class is intended to execute the 'log' function
 */
public class LogAction implements Action {

    /**
     * The method executes the 'log' operation.<br>
     * A user enters name of a file to which the application saves a log.<br>
     * The file is located under the 'src/main/resources/log/' directory.
     */
    @Override
    public void execute() {
        outputMsg("File name:");
        String fileName = getUserInput();
        String path = String.format("src/main/resources/log/%s", fileName);
        try (FileWriter fileWriter = new FileWriter(path)) {
            outputMsg("The log has been saved.");
            fileWriter.write(getLog());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
