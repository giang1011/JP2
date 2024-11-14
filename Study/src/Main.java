import Entity.ProductData;
import Service.ProductService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        String sysPath = System.getProperty("user.dir");
        String fileInPath = sysPath.replace("/", "\\") + "\\data\\Product.in.txt";
        String fileOutPath = sysPath.replace("/", "\\") + "\\data\\Product.out.txt";

        List<ProductData> dataList = productService.readFile(fileInPath);

        LocalDate startDate = LocalDate.of(2024, 2, 1);
        LocalDate endDate = LocalDate.of(2024, 3, 1);

        Map<Integer, double[]> percentages = productService.calculatePercentages(dataList, startDate, endDate);

        productService.writeFile(fileOutPath, percentages);
    }
}
