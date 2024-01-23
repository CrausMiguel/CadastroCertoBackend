package com.elotech.cadastroCerto.utils;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversorDateTimeLongTest {

    @InjectMocks
    private ConversorDateTimeLong conversor;

    @Test
    void longToLocalDateTime() {
        String inputDateString = "2024-01-23T04:14:04.508Z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime localDateTime = LocalDateTime.parse(inputDateString, formatter);

        assertEquals(ConversorDateTimeLong.longToLocalDateTime(1705994044508L), localDateTime);
    }

    @Test
    void localDateTimeToLong() {
        String inputDateString = "2024-01-23T04:14:04.508Z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime localDateTime = LocalDateTime.parse(inputDateString, formatter);

        assertEquals(ConversorDateTimeLong.localDateTimeToLong(localDateTime), 1705994044508L);
    }
}