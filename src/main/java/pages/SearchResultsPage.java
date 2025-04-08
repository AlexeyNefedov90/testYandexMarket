package pages;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object для страницы результатов поиска Яндекс Маркета
 */
public class SearchResultsPage {

    // Локаторы для текста маски плейсхолдера
    private final SelenideElement priceFromPlaceholder = $(By.xpath("//label[@class='_3Nl22 _2c7XK']"));
    private final SelenideElement priceToPlaceholder = $(By.xpath("//label[@class='_3Nl22 _2DcTF']"));
    // Локаторы для ввода текста
    private final SelenideElement priceFromInput = $("#range-filter-field-glprice_25563_min");
    private final SelenideElement priceToInput = $("#range-filter-field-glprice_25563_max");
    // локаторы для кнопок сортировки
    private final SelenideElement sortByPriceDescButton = $x("//span[contains(text(),'Подешевле')]");
    private final SelenideElement sortButton = $(By.xpath("//button[@class='_3ooNj _1l_Gv']"));
    private final SelenideElement sortByPriceAscButton = $x("//span[contains(text(),'Подороже')]");
    // локатор для цены первого продукта в найденом списке
    private final SelenideElement firstProductPrice = $(By.xpath(("//span[@data-auto='snippet-price-current']")));

    /**
     * Устанавливает фильтр по цене
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return экземпляр SearchResultsPage
     */
    public SearchResultsPage setPriceFilter(double minPrice, double maxPrice) {
        priceFromInput.setValue(String.valueOf(minPrice));
        priceToInput.setValue(String.valueOf(maxPrice));
        return this;
    }

    /**
     * Сортирует товары по убыванию цены
     * @return экземпляр SearchResultsPage
     */
    public SearchResultsPage sortByPriceDescending() {
        sortButton.click();
        sortByPriceDescButton.click();
        return this;
    }

    /**
     * Сортирует товары по возрастанию цены
     * @return экземпляр SearchResultsPage
     */
    public SearchResultsPage sortByPriceAscending() {
        sortButton.click();
        sortByPriceAscButton.click();
        return this;
    }


    /**
     * Получает цену первого товара в списке
     * @return цена первого товара
     */
    public double getFirstProductPrice(){
        firstProductPrice.should(appear);
        String productPrice = firstProductPrice.getAttribute("textContent");
        double productPriceDouble = parsePriceFromPlaceholder(productPrice);
        return productPriceDouble;
    }

    /**
     * Получает значение из маски плейсхолдера
     *
     * @return minPrice maxPrice
     */
    public List<Double> priceExtractor() {
        // Извлечь innerText из элемента с указанным селектором
        String innerTextMin = priceFromPlaceholder.getAttribute("textContent");
        String innerTextMax = priceToPlaceholder.getAttribute("textContent");

        // Печать извлеченного текста
        System.out.println("InnerTextMin элемента: " + innerTextMin);
        System.out.println("InnerTextMax элемента: " + innerTextMax);

        // Парсим числовые значения из плейсхолдеров
        double minPrice = parsePriceFromPlaceholder(innerTextMin);
        double maxPrice = parsePriceFromPlaceholder(innerTextMax);

        System.out.println("Минимальная цена: " + minPrice + " ₽");
        System.out.println("Максимальная цена: " + maxPrice + " ₽");
        return Arrays.asList(minPrice, maxPrice);
    }

    // Извлекаем только цифры и пробелы, затем удаляем пробелы
    private static int parsePriceFromPlaceholder(String placeholder) {
        String numericPart = placeholder.replaceAll("[^0-9\\s]", "").trim();
        return Integer.parseInt(numericPart.replaceAll("\\s+", ""));
    }

    public static class PriceRange {
        private final int minPrice;
        private final int maxPrice;

        public PriceRange(int minPrice, int maxPrice) {
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
        }

        public int getMinPrice() {
            return minPrice;
        }

        public int getMaxPrice() {
            return maxPrice;
        }
    }
}
