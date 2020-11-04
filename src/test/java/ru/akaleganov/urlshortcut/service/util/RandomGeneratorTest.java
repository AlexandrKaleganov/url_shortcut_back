package ru.akaleganov.urlshortcut.service.util;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The type Random generator test.
 */
@DisplayName("тестирование: RandomGenerator")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class RandomGeneratorTest {
    /**
     * The Random generator.
     */
    @Autowired
    RandomGenerator randomGenerator;

    /**
     * Generate login.
     */
    @Test
    void generateLogin() {
    }

    /**
     * Generate password.
     */
    @Test
    void generatePassword() {
    }

    /**
     * Generate new origin.
     */
    @Test
    @DisplayName("тестирование генерации урл")
    void generateNewOrigin() {
        HashSet<String> hashSet = new HashSet<>(101, 1);
        for (int i = 0; i < 100; i++) {
           hashSet.add(this.randomGenerator.generateShortCut());
        }
        assertThat(hashSet.size(),
                Is.is(100));
    }
}
