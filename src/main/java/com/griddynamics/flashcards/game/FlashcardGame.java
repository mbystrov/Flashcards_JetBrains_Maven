package com.griddynamics.flashcards.game;

import com.griddynamics.flashcards.action.*;

import java.util.*;

public class FlashcardGame {
    private final Map<String, String> flashcards = new LinkedHashMap<>();
    private final Map<String, Integer> mistakes = new HashMap<>();

    private static final StringBuilder log = new StringBuilder();
    private static final Scanner scanner = new Scanner(System.in);

    public void startGame(String[] arr) {
        Map<String, Action> actions = new HashMap<>();
        actions.put("add", new AddAction(flashcards, mistakes));
        actions.put("remove", new RemoveAction(flashcards, mistakes));
        actions.put("import", new ImportAction(flashcards, mistakes));
        actions.put("export", new ExportAction(flashcards, mistakes));
        actions.put("ask", new AskAction(flashcards, mistakes));
        actions.put("log", new LogAction());
        actions.put("hardest card", new HardestCardAction(mistakes));
        actions.put("reset stats", new ResetStatsAction(mistakes));

        if (Arrays.asList(arr).contains("-import") && Arrays.asList(arr).indexOf("-import") + 1 < arr.length) {
            String fileName = arr[Arrays.asList(arr).indexOf("-import") + 1];
            ImportAction importAction = new ImportAction(flashcards, mistakes);
            importAction.execute(fileName);
        }
        while (true) {
            printMenu();
            String command = getUserInput().toLowerCase();
            if (!actions.containsKey(command) && !command.equals("exit")) {
                outputMsg("Your command doesn't exist, please, try again");
                continue;
            }
            if (command.equals("exit")
                    && Arrays.asList(arr).contains("-export")
                    && Arrays.asList(arr).indexOf("-export") + 1 < arr.length) {
                String fileName = arr[Arrays.asList(arr).indexOf("-export") + 1];
                ExportAction exportAction = new ExportAction(flashcards, mistakes);
                outputMsg("Bye bye!");
                exportAction.execute(fileName);
                return;
            }
            if (command.equals("exit")) {
                outputMsg("Bye bye!");
                return;
            }
            Action action = actions.get(command);
            action.execute();
        }
    }

    public void printMenu() {
        outputMsg("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
    }

    public static void outputMsg(String msg) {
        System.out.println(msg);
        log.append(msg).append("\n");
    }

    public static String getUserInput() {
        String input = scanner.nextLine();
        log.append(input).append("\n");
        return input;
    }

    public static String getLog() {
        return log.toString();
    }
}