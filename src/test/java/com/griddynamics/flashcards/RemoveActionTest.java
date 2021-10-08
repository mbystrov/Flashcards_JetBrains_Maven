package com.griddynamics.flashcards;

import com.griddynamics.flashcards.action.RemoveAction;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
При выполнении всего сьюта падают все сьюты, кроме одного. По отдельности не падают.
 */
class RemoveActionTest {

    @Test
    void testRemoveActionExecuteExistingTerm() {
        Map<String, String> flashcards = new LinkedHashMap<>() {{
            put("Left", "Levo");
            put("Right", "Pravo");
        }};
        Map<String, Integer> mistakes = new HashMap<>() {{
            put("Left", 0);
            put("Right",3);
        }};

        RemoveAction removeAction = new RemoveAction(flashcards, mistakes);

        String term = "Left";
        InputStream in = new ByteArrayInputStream(term.getBytes());
        System.setIn(in);

        assertEquals(2, flashcards.size());
        assertEquals(flashcards.size(), mistakes.size());
        removeAction.execute();
        assertEquals(1, flashcards.size(), "Flashcards has wrong size");
        assertEquals(flashcards.size(), mistakes.size(), "Flashcards and mistakes has different sizes");
    }

    @Test
    void testRemoveActionExecuteTermDoesNotExist() {
        Map<String, String> flashcards = new LinkedHashMap<>() {{
            put("Left", "Levo");
            put("Right", "Pravo");
        }};
        Map<String, Integer> mistakes = new HashMap<>() {{
            put("Left", 0);
            put("Right",3);
        }};

        RemoveAction removeAction = new RemoveAction(flashcards, mistakes);

        String term = "Start";
        InputStream in = new ByteArrayInputStream(term.getBytes());
        System.setIn(in);

        assertEquals(2, flashcards.size());
        assertEquals(flashcards.size(), mistakes.size());
        removeAction.execute();
        assertEquals(2, flashcards.size());
        assertEquals(flashcards.size(), mistakes.size());
    }

    @Test
    void testRemoveActionExecuteTermEmptyValue() {
        Map<String, String> flashcards = new LinkedHashMap<>() {{
            put("Left", "Levo");
            put("Right", "Pravo");
        }};
        Map<String, Integer> mistakes = new HashMap<>() {{
            put("Left", 0);
            put("Right",3);
        }};

        RemoveAction removeAction = new RemoveAction(flashcards, mistakes);

        String term = "\n";
        InputStream in = new ByteArrayInputStream(term.getBytes());
        System.setIn(in);

        assertEquals(2, flashcards.size());
        assertEquals(flashcards.size(), mistakes.size());
        removeAction.execute();
        assertEquals(2, flashcards.size());
        assertEquals(flashcards.size(), mistakes.size());
    }

}