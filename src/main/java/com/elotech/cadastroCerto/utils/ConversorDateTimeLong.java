package com.elotech.cadastroCerto.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConversorDateTimeLong {

    public static LocalDateTime longToLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId
                .systemDefault());
    }
    public static Long localDateTimeToLong(LocalDateTime dataHora) {
        return dataHora.atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();
    }
}
