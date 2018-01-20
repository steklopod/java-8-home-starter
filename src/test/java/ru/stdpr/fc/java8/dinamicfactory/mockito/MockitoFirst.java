package ru.stdpr.fc.java8.dinamicfactory.mockito;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.stdpr.fc.java8.comporator.PersonForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@RunWith(JUnitPlatform.class)
class MockitoFirst {
    private static Logger logger = LoggerFactory.getLogger(MockitoFirst.class);

    @BeforeEach
    @Disabled
    @SuppressWarnings("Не работает")
    void init(@Mock PersonForTest person) {
        when(person.getFirstName()).thenReturn("Dilbert");
    }

    @Test
    @Disabled
    void simpleTestWithInjectedMock(@Mock PersonForTest person) {
        assertEquals("Dilbert", person.getFirstName());
    }


}
