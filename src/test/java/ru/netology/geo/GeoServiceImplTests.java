package ru.netology.geo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

public class GeoServiceImplTests {

    // Написать тесты для проверки определения лакации

    public static Stream<Arguments> sourceCheckLocationByIp() {
        return Stream.of(Arguments.of("127.0.0.1", null),
                Arguments.of("172.0.32.11", "Moscow"),
                Arguments.of("172.","Moscow"),
                Arguments.of("96.44.183.149","New York"),
                Arguments.of("96.44.183.149","New York")
        );
    }

    @MethodSource("sourceCheckLocationByIp")
    @ParameterizedTest
    public void checkLocationByIp(String ip, String city) {
        // arrange:
        GeoServiceImpl geoService = new GeoServiceImpl();

        // act:

        // assert:
        Assertions.assertEquals(city,geoService.byIp(ip).getCity());
    }
}
