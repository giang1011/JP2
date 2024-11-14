import Entity.CRStatistics;
import Entity.StatisticsView;
import Service.FileService;


import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        String fileInPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "\\data\\statistics.view.txt";
        FileService fileService = new FileService();
        List<StatisticsView> statisticsViews = fileService.readFileStatistics(fileInPathStatistics);

        //Data Analytics
        Map<CRStatistics, CRStatistics> dataCRS = statisticsViews.stream()
                .collect(Collectors.groupingBy(
                        cr->new CRStatistics(cr.getId(),cr.getMonthOfDate(), cr.getYearOfDate()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                ListCR->{
                                    CRStatistics crStatistics = new CRStatistics();
                                    StatisticsView firstStatistics = ListCR.getFirst();
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


    }
}