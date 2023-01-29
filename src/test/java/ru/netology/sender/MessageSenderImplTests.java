package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;

public class MessageSenderImplTests {

    @Test
    public void test_getMassage_fromRussianIp(){
        //Я заглущил метот при вызове 2х значений, могу ли я как то заглушить все значения ?
        // arrange:
        GeoServiceImpl geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp("172."))
                .thenReturn(new Location("", Country.RUSSIA, null, 0));
        Mockito.when(geoServiceMock.byIp("172.0.32.11"))
                .thenReturn(new Location("", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceMock.locale(RUSSIA))
                .thenReturn("Добро пожаловать");

        // act:
        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceMock,localizationServiceMock);
        Map<String, String> headers = new HashMap<String, String>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        // assert:
        Assertions.assertEquals("Добро пожаловать",messageSender.send(headers));
    }

    @Test
    public void test_getMassage_fromUSAIp(){
        // arrange:
        GeoServiceImpl geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp("96."))
                .thenReturn(new Location("", Country.USA, null, 0));
        Mockito.when(geoServiceMock.byIp("96.44.183.149"))
                .thenReturn(new Location("", Country.USA, null, 0));


        LocalizationServiceImpl localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceMock.locale(Country.USA))
                .thenReturn("Welcome");

        // act:
        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceMock,localizationServiceMock);
        Map<String, String> headers = new HashMap<String, String>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");

        // assert:
        Assertions.assertEquals("Welcome",messageSender.send(headers));
    }


}
