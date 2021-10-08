package com.griddynamics.flashcards;

import com.griddynamics.flashcards.action.ResetStatsAction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResetStatsActionTest {

    @Test
    void testExecuteResetStatsAction() {
        Map<String, Integer> mistakes = new HashMap<>() {{
            put("Pravo", 4);
            put("Levo", 3);
        }};
        ResetStatsAction resetStatsAction = new ResetStatsAction(mistakes);
        resetStatsAction.execute();
        mistakes.forEach((k, v) -> assertEquals(v, 0));
    }

    @Test
    void testExecuteResetStatsActionEmptyMap() {
        Map<String, Integer> mistakes = new HashMap<>();
        ResetStatsAction resetStatsAction = new ResetStatsAction(mistakes);
        resetStatsAction.execute();
        mistakes.forEach((k, v) -> assertEquals(v, 0));
    }
}