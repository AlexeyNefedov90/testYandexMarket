package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object для главной страницы Яндекс Маркета
 */
public class MainPage {

    private final SelenideElement searchInput = $("input#header-search");
    private final SelenideElement searchButton = $("button[type='submit']");

    /**
     * Открывает главную страницу Яндекс Маркета
     * @return экземпляр MainPage
     */
    public MainPage open() {
        Selenide.open("https://market.yandex.ru/");
        return this;
    }

    /**
     * Выполняет поиск товара
     * @param productName название товара для поиска
     * @return экземпляр SearchResultsPage
     */
    public SearchResultsPage searchForProduct(String productName) {
        searchInput.setValue(productName);
        searchButton.click();
        return page(SearchResultsPage.class);
    }
}
