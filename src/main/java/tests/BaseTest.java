package tests;

import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Configuration.*;

/**
 * Базовый класс для всех тестов
 */
public class BaseTest {

    @BeforeSuite
    public void setUp() {
        // Настройки Selenide
        browser = "chrome";
        timeout = 10000;
    }
}
