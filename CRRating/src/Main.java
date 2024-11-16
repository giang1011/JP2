import Entity.CRStatistics;
import Entity.StatisticsView;
import Service.FileService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String fileInPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "\\data\\statistics.view.txt";
        FileService fileService = new FileService();
        List<StatisticsView> statisticsViews = fileService.readFileStatistics(fileInPathStatistics);


        LocalDate startDate = LocalDate.of(2024, 2, 1);
        LocalDate endDate = startDate.plusDays(29);


        List<StatisticsView> filteredData = statisticsViews.stream()
                .filter(s -> !s.getCreateAtDate().isBefore(startDate) && !s.getCreateAtDate().isAfter(endDate))
                .collect(Collectors.toList());


        Map<CRStatistics, CRStatistics> dataCRS = filteredData.stream()
                .collect(Collectors.groupingBy(
                        cr -> new CRStatistics(cr.getId(), cr.getMonthOfDate(), cr.getYearOfDate()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                ListCR -> {
                                    CRStatistics crStatistics = new CRStatistics();
                                    StatisticsView firstStatistics = ListCR.get(0);
                                    int totalView = ListCR.stream().mapToInt(StatisticsView::getView).sum();
                                    crStatistics.setId(firstStatistics.getId());
                                    crStatistics.setMonth(firstStatistics.getMonthOfDate());
                                    crStatistics.setYear(firstStatistics.getYearOfDate());
                                    crStatistics.setTotalView(totalView);
                                    crStatistics.setTotalAddToCart(ListCR.stream().mapToInt(StatisticsView::getAddToCart).sum());
                                    crStatistics.setTotalCheckOut(ListCR.stream().mapToInt(StatisticsView::getCheckOut).sum());
                                    return crStatistics;
                                }
                        )
                ));
        dataCRS.forEach((k,v)-> System.out.println(v));



        dataCRS.forEach((id, crStatistics) -> {
            int totalClick = crStatistics.getTotalView();
            int totalAddToCart = crStatistics.getTotalAddToCart();
            int totalCheckOut = crStatistics.getTotalCheckOut();

            int totalBehavior = totalClick + totalAddToCart + totalCheckOut;
            if (totalBehavior > 0) {
                double percentClick = (double) totalClick / totalBehavior * 100;
                double percentAddToCart = (double) totalAddToCart / totalBehavior * 100;
                double percentCheckOut = (double) totalCheckOut / totalBehavior * 100;

                System.out.println(String.format("%d; %.2f; %.2f; %.2f; %s",
                        id.getId(), percentClick, percentAddToCart, percentCheckOut,
                        startDate.getYear() + "-" + String.format("%02d", startDate.getMonthValue())));
            } else {
                System.out.println(String.format("%d; No Data; No Data; No Data; %s",
                        id.getId(), startDate.getYear() + "-" + String.format("%02d", startDate.getMonthValue())));
            }
        });
    }
}
