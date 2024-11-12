import Entity.OrderDetail;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Entity.OrderDetail;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        String sysPath = System.getProperty("user.dir");
        String fileInPath = sysPath.replace("/", "\\") + "\\data\\OrderDetail.in.txt";
        String fileOutPath = sysPath.replace("/", "\\") + "\\data\\OrderCustomer.out.txt";
        List<OrderDetail> orderDetails = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        try (BufferedReader reader = new BufferedReader(new FileReader(fileInPath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                int orderId = Integer.parseInt(data[1]);
                int productId = Integer.parseInt(data[2]);
                int quantity = Integer.parseInt(data[3]);
                double price = Double.parseDouble(data[4]);
                LocalDateTime dateTime = LocalDateTime.parse(data[5], formatter);

                OrderDetail orderDetail = new OrderDetail(id, orderId, productId, quantity, price, dateTime);
                orderDetails.add(orderDetail);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        generateInvoice(1, orderDetails, fileOutPath);
    }

    public static void generateInvoice(int customerId, List<OrderDetail> orderDetails, String fileOutPath) {
        double totalAmount = 0;

        StringBuilder invoice = new StringBuilder("Order Details for Customer ID: " + customerId + "\n");
        invoice.append("OrderID | ProductID | Quantity | Price | Total\n");

        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getOrderId() == customerId) {
                double itemTotal = orderDetail.getQuantity() * orderDetail.getPrice();
                totalAmount += itemTotal;
                invoice.append(orderDetail.getOrderId()).append(" | ")
                        .append(orderDetail.getProductId()).append(" | ")
                        .append(orderDetail.getQuantity()).append(" | ")
                        .append(orderDetail.getPrice()).append(" | ")
                        .append(itemTotal).append("\n");
            }
        }

        invoice.append("Total Amount: ").append(totalAmount).append("\n");
        writeToFile(fileOutPath, invoice.toString());
    }

    private static void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }




// tạo nhiều hóa đơn
//                String sysPath = System.getProperty("user.dir");
//                String fileInPath = sysPath.replace("/", "\\") + "\\data\\OrderDetail.in.txt";
//                List<OrderDetail> orderDetails = new ArrayList<>();
//
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//
//                try (BufferedReader reader = new BufferedReader(new FileReader(fileInPath))) {
//                    String line;
//                    reader.readLine(); // Bỏ qua dòng tiêu đề
//                    while ((line = reader.readLine()) != null) {
//                        String[] data = line.split(";");
//                        int id = Integer.parseInt(data[0]);
//                        int orderId = Integer.parseInt(data[1]);
//                        int productId = Integer.parseInt(data[2]);
//                        int quantity = Integer.parseInt(data[3]);
//                        double price = Double.parseDouble(data[4]);
//                        LocalDateTime dateTime = LocalDateTime.parse(data[5], formatter);
//
//                        OrderDetail orderDetail = new OrderDetail(id, orderId, productId, quantity, price, dateTime);
//                        orderDetails.add(orderDetail);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                Set<Integer> customerIds = orderDetails.stream()
//                        .map(OrderDetail::getOrderId)
//                        .collect(Collectors.toSet());
//
//
//                for (int customerId : customerIds) {
//                    String fileOutPath = sysPath.replace("/", "\\") + "\\data\\OrderCustomer_" + customerId + ".out.txt";
//                    generateInvoice(customerId, orderDetails, fileOutPath);
//                }
//            }
//
//            public static void generateInvoice(int customerId, List<OrderDetail> orderDetails, String fileOutPath) {
//                double totalAmount = 0;
//
//                StringBuilder invoice = new StringBuilder("Order Details for Customer ID: " + customerId + "\n");
//                invoice.append("OrderID | ProductID | Quantity | Price | Total\n");
//
//                for (OrderDetail orderDetail : orderDetails) {
//                    if (orderDetail.getOrderId() == customerId) {
//                        double itemTotal = orderDetail.getQuantity() * orderDetail.getPrice();
//                        totalAmount += itemTotal;
//                        invoice.append(orderDetail.getOrderId()).append(" | ")
//                                .append(orderDetail.getProductId()).append(" | ")
//                                .append(orderDetail.getQuantity()).append(" | ")
//                                .append(orderDetail.getPrice()).append(" | ")
//                                .append(itemTotal).append("\n");
//                    }
//                }
//
//                invoice.append("Total Amount: ").append(totalAmount).append("\n");
//                writeToFile(fileOutPath, invoice.toString());
//            }
//
//            private static void writeToFile(String fileName, String content) {
//                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//                    writer.write(content);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }


