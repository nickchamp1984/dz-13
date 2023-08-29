package cc.robotdreams.automation.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

abstract public class CSVFileReader
{
    private CSVFileReader() {}

    static public <T extends CsvToBean> List<T> csvToDataProvider(Class<T> beanClass, String path) {
        List<T> result;

        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
                    .withType(beanClass)
                    .build();
            result = cb.parse();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
