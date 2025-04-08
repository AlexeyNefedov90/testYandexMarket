package utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилита для чтения тестовых данных из CSV файла
 */
public class CsvDataProvider {

    private static final String CSV_FILE_PATH = "src/main/resources/test_data.csv";

    /**
     * DataProvider для TestNG, который читает данные из CSV файла
     * @return двумерный массив объектов, содержащий тестовые данные
     * @throws IOException если возникла ошибка при чтении файла
     */
    @DataProvider(name = "productsDataProvider")
    public static Object[][] provideProductsData() throws IOException {
        List<String> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // Пропускаем заголовок
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                products.add(line.trim());
            }
        }

        // Преобразуем список в двумерный массив для TestNG
        Object[][] data = new Object[products.size()][1];
        for (int i = 0; i < products.size(); i++) {
            data[i][0] = products.get(i);
        }

        return data;
    }
}
