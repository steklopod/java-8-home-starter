package ru.stdpr.fc.java8.dinamicfactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.stdpr.fc.connection.DataProvider;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(JUnitPlatform.class)
@ContextConfiguration(classes = {DataProvider.class})
class Factory {
    private static Logger logger = LoggerFactory.getLogger(Factory.class);

    TranslatorEngine translatorEngine;

    @BeforeEach
    void setUp() {
        translatorEngine = new TranslatorEngine();
    }

    @Test
    void testTranlsateHello() {
        assertEquals("Bonjour", translatorEngine.tranlate("Hello"));
    }

    @Test
    void testTranlsateYes() {
        assertEquals("Oui", translatorEngine.tranlate("Yes"));
    }

    @TestFactory
    Collection<DynamicTest> translateDynamicTests() {
        List<String> inPhrases =
                new ArrayList<>(Arrays.asList("Hello", "Yes", "No", "Goodbye", "Good night", "Thank you"));
        List<String> outPhrases =
                new ArrayList<>(Arrays.asList("Bonjour", "Oui", "Non", "Au revoir", "Bonne nuit", "Merci"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i = 0; i < inPhrases.size(); i++) {
            String phr = inPhrases.get(i);
            String outPhr = outPhrases.get(i);
            Executable exec = () -> assertEquals(outPhr, translatorEngine.tranlate(phr));
            String testName = "Test tranlate " + phr;
            DynamicTest dTest = DynamicTest.dynamicTest(testName, exec);
            dynamicTests.add(dTest);
        }
        return dynamicTests;
    }

    @TestFactory
    Stream<DynamicTest> translateDynamicTestsFromStream() {
        List<String> inPhrases =
                new ArrayList<>(Arrays.asList("Hello", "Yes", "No", "Goodbye", "Good night", "Thank you"));
        List<String> outPhrases =
                new ArrayList<>(Arrays.asList("Bonjour", "Oui", "Non", "Au revoir", "Bonne nuit", "Merci"));
        return inPhrases.stream()
                .map(phrs -> DynamicTest.dynamicTest("Test translate " + phrs,
                        () -> {
                            int idx = inPhrases.indexOf(phrs);
                            assertEquals(outPhrases.get(idx), translatorEngine.tranlate(phrs));
                        }));
    }
}
