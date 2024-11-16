package General;

import java.util.List;

public interface IFileService<T> {
    public  List<T> readFileStatistics(String fileInPath);
    public List<T> writeFileStatistics(String fileOutPath);
}
