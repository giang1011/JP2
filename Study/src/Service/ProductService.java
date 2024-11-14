package Service;

import Entity.ProductData;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProductService {

    public List<ProductData> readFile(String filePath) {
        List<ProductData> dataList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                int idSanPham = Integer.parseInt(data[0]);
                int click = Integer.parseInt(data[1]);
                int addToCart = Integer.parseInt(data[2]);
                int checkout = Integer.parseInt(data[3]);
                LocalDate dateTime = LocalDate.parse(data[4], formatter);

                dataList.add(new ProductData(idSanPham, click, addToCart, checkout, dateTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public Map<Integer, double[]> calculatePercentages(List<ProductData> dataList, LocalDate startDate, LocalDate endDate) {
        Map<Integer, Integer> totalClicks = new HashMap<>();
        Map<Integer, Integer> totalAddToCarts = new HashMap<>();
        Map<Integer, Integer> totalCheckouts = new HashMap<>();

        for (ProductData data : dataList) {
            if (!data.dateTime.isBefore(startDate) && !data.dateTime.isAfter(endDate)) {
                totalClicks.put(data.idSanPham, totalClicks.getOrDefault(data.idSanPham, 0) + data.click);
                totalAddToCarts.put(data.idSanPham, totalAddToCarts.getOrDefault(data.idSanPham, 0) + data.addToCart);
                totalCheckouts.put(data.idSanPham, totalCheckouts.getOrDefault(data.idSanPham, 0) + data.checkout);
            }
        }

        int sumClicks = totalClicks.values().stream().mapToInt(Integer::intValue).sum();
        int sumAddToCarts = totalAddToCarts.values().stream().mapToInt(Integer::intValue).sum();
        int sumCheckouts = totalCheckouts.values().stream().mapToInt(Integer::intValue).sum();

        Map<Integer, double[]> percentages = new HashMap<>();
        for (int id : totalClicks.keySet()) {
            double clickPercentage = (double) totalClicks.get(id) / sumClicks * 100;
            double addToCartPercentage = (double) totalAddToCarts.get(id) / sumAddToCarts * 100;
            double checkoutPercentage = (double) totalCheckouts.get(id) / sumCheckouts * 100;
            percentages.put(id, new double[]{clickPercentage, addToCartPercentage, checkoutPercentage});
        }

        return percentages;
    }

    public void writeFile(String filePath, Map<Integer, double[]> percentages) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("IdSanpham;%Click;%AddToCart;%Checkout\n");
            for (Map.Entry<Integer, double[]> entry : percentages.entrySet()) {
                int idSanPham = entry.getKey();
                double[] values = entry.getValue();
                writer.write(idSanPham + ";" + String.format("%.2f", values[0]) + ";" +
                        String.format("%.2f", values[1]) + ";" + String.format("%.2f", values[2]) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

