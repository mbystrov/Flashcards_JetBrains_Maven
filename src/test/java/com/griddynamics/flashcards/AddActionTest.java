package com.griddynamics.flashcards;

import com.griddynamics.flashcards.action.AddAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class AddActionTest {

    @Test
    void testAddAction() {
        Map<String, String> flashcards = new LinkedHashMap<>() {{
            put("Left", "Levo");
            put("Right", "Pravo");
        }};
        Map<String, Integer> mistakes = new HashMap<>() {{
            put("Left", 0);
            put("Right",3);
        }};

        AddAction addAction = new AddAction(flashcards, mistakes);
        String term = "Left";
        InputStream in = new ByteArrayInputStream(term.getBytes());
        System.setIn(in);
    }
}