package utils;

/**
 * Утилита для расчета ценовых диапазонов
 */
public class PriceRangeCalculator {

    /**
     * Рассчитывает верхнюю половину ценового диапазона
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return массив из двух элементов - нижняя и верхняя граница диапазона
     */
    public static double[] calculateUpperHalfRange(double minPrice, double maxPrice) {
        double lowerBound = (minPrice + maxPrice) / 2;
        return new double[]{lowerBound, maxPrice};
    }

    /**
     * Рассчитывает нижнюю половину ценового диапазона
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return массив из двух элементов - нижняя и верхняя граница диапазона
     */
    public static double[] calculateLowerHalfRange(double minPrice, double maxPrice) {
        double upperBound = (minPrice + maxPrice) / 2;
        return new double[]{minPrice, upperBound};
    }

    /**
     * Рассчитывает среднюю половину ценового диапазона (от 1/4 до 3/4)
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return массив из двух элементов - нижняя и верхняя граница диапазона
     */
    public static double[] calculateMiddleHalfRange(double minPrice, double maxPrice) {
        double quarter = (maxPrice - minPrice) / 4;
        return new double[]{minPrice + quarter, maxPrice - quarter};
    }
}