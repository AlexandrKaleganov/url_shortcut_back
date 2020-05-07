package ru.akaleganov.job4j_url_shortcut.config.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {
    @Test
    public void testString() {
        char data[] = {'n', 'o', 't', 'e', 's'};
        String str = new String(data);
        System.out.println( str );
    }

}
