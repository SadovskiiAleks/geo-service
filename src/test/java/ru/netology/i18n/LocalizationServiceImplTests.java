package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceImplTests {

    //Написать тесты для проверки возращаемого текста
    public static Stream<Arguments> sourceCheckLocate() {
        return Stream.of(Arguments.of("Добро пожаловать", Country.RUSSIA),
                Arguments.of("Welcome", Country.USA),
                Arguments.of("Welcome", Country.BRAZIL),
                Arguments.of("Welcome", Country.GERMANY)
        );
    }

    @MethodSource("sourceCheckLocate")
    @ParameterizedTest
    public void checkLocateByCountry(String said, Country country) {
        // arrange:
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        // act:


        // assert:
        Assertions.assertEquals(said,localizationService.locale(country));
    }
}
