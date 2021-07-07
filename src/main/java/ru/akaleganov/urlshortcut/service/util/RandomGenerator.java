package ru.akaleganov.urlshortcut.service.util;

import java.util.Random;

import org.springframework.stereotype.Service;

/**
 * класс для рандомной генерации логина и пароля
 */
@Service
public class RandomGenerator {

    /**
     * Generate login string.
     *
     * @return the string
     */
    public String generateLogin() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                     .limit(targetStringLength)
                     .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                     .toString();
    }

    /**
     * Generate password string.
     *
     * @return the string
     */
    public String generatePassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                     .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                     .limit(targetStringLength)
                     .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                     .toString();
    }

    /**
     * Generate short cut string.
     *
     * @return the string
     */
    public String generateShortCut() {
        return new Random().ints(48, 122).filter(i ->
                                                         (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                           .limit(4)
                           .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                           .toString();
    }

}
