package tests;


import pages.MainPage;
import pages.SearchResultsPage;
import utils.CsvDataProvider;
import utils.PriceRangeCalculator;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Тесты для проверки поиска товаров на Яндекс Маркете
 */
public class ProductSearchTest extends BaseTest {

    /**
     * Тест 1: Фильтрация по верхней половине ценового диапазона и проверка сортировки "Подороже"
     * @param productName название товара для поиска
     */
    @Test(dataProvider = "productsDataProvider", dataProviderClass = CsvDataProvider.class)
    public void testUpperHalfPriceRange(String productName) {
        // 1. Открываем главную страницу и выполняем поиск товара
        SearchResultsPage resultsPage = new MainPage()
                .open()
                .searchForProduct(productName);

        // 2. Рассчитываем ценовой диапазон
        List<Double> priceRange = resultsPage.priceExtractor();
        double minPrice = priceRange.get(0);
        double maxPrice = priceRange.get(1);

        // 3. Рассчитываем верхнюю половину диапазона
        double[] upperHalfRange = PriceRangeCalculator.calculateUpperHalfRange(minPrice, maxPrice);
        double lowerBound = upperHalfRange[0];
        double upperBound = upperHalfRange[1];

        // 4. Применяем фильтр по верхней половине ценового диапазона
        resultsPage.setPriceFilter(lowerBound, upperBound);

        // 5. Сортируем по убыванию цены и получаем цену первого товара
        resultsPage.sortByPriceDescending();
        double firstProductPrice = resultsPage.getFirstProductPrice();

        // 6. Проверяем, что цена первого товара попадает в заданный диапазон
        assertTrue(firstProductPrice >= lowerBound && firstProductPrice <= upperBound,
                String.format("Цена первого товара " + productName + " должна быть в диапазоне от " + lowerBound + " до " + upperBound,
                        firstProductPrice, lowerBound, upperBound));
    }

    /**
     * Тест 2: Фильтрация по нижней половине ценового диапазона и проверка сортировки "Подешевле"
     * @param productName название товара для поиска
     */
    @Test(dataProvider = "productsDataProvider", dataProviderClass = CsvDataProvider.class)
    public void testLowerHalfPriceRange(String productName) {
        // 1. Открываем главную страницу и выполняем поиск товара
        SearchResultsPage resultsPage = new MainPage()
                .open()
                .searchForProduct(productName);

        // 2. Рассчитываем ценовой диапазон
        List<Double> priceRange = resultsPage.priceExtractor();
        double minPrice = priceRange.get(0);
        double maxPrice = priceRange.get(1);

        // 3. Рассчитываем нижнюю половину диапазона
        double[] lowerHalfRange = PriceRangeCalculator.calculateLowerHalfRange(minPrice, maxPrice);
        double lowerBound = lowerHalfRange[0];
        double upperBound = lowerHalfRange[1];

        // 4. Применяем фильтр по нижней половине ценового диапазона
        resultsPage.setPriceFilter(lowerBound, upperBound);

        // 5. Сортируем по возрастанию цены и получаем цену первого товара
        resultsPage.sortByPriceAscending();
        double firstProductPrice = resultsPage.getFirstProductPrice();

        // 6. Проверяем, что цена первого товара попадает в заданный диапазон
        assertTrue(firstProductPrice >= lowerBound && firstProductPrice <= upperBound,
                String.format("Цена первого товара " + productName + " должна быть в диапазоне от " + lowerBound + " до " + upperBound,
                        firstProductPrice, lowerBound, upperBound));
    }

    /**
     * Тест 3: Фильтрация по средней половине ценового диапазона (от 1/4 до 3/4) и проверка сортировки "Подороже"
     * @param productName название товара для поиска
     */
    @Test(dataProvider = "productsDataProvider", dataProviderClass = CsvDataProvider.class)
    public void testMiddleHalfPriceRange(String productName) {
        // 1. Открываем главную страницу и выполняем поиск товара
        SearchResultsPage resultsPage = new MainPage()
                .open()
                .searchForProduct(productName);

        // 2. Рассчитываем ценовой диапазон
        List<Double> priceRange = resultsPage.priceExtractor();
        double minPrice = priceRange.get(0);
        double maxPrice = priceRange.get(1);

        // 3. Рассчитываем среднюю половину диапазона (от 1/4 до 3/4)
        double[] middleHalfRange = PriceRangeCalculator.calculateMiddleHalfRange(minPrice, maxPrice);
        double lowerBound = middleHalfRange[0];
        double upperBound = middleHalfRange[1];

        // 4. Применяем фильтр по средней половине ценового диапазона
        resultsPage.setPriceFilter(lowerBound, upperBound);

        // 5. Сортируем по убыванию цены и получаем цену первого товара
        resultsPage.sortByPriceDescending();
        double firstProductPrice = resultsPage.getFirstProductPrice();

        // 6. Проверяем, что цена первого товара попадает в заданный диапазон
        assertTrue(firstProductPrice >= lowerBound && firstProductPrice <= upperBound,
                String.format("Цена первого товара " + productName + " должна быть в диапазоне от " + lowerBound + " до " + upperBound,
                        firstProductPrice, lowerBound, upperBound));
    }
}