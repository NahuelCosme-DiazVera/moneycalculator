package software.ulpgc.moneycalculator;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class TsvFileExchangeRateLoader implements ExchangeRateLoader{
    private File file;

    private TsvFileExchangeRateLoader(File file) {
        this.file = file;
    }

    public static  TsvFileExchangeRateLoader with(File file) {
        return new TsvFileExchangeRateLoader(file);
    }

    @Override
    public List<ExchangeRate> load() {
        try {
            return load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ExchangeRate> load(FileReader reader) throws IOException {
        return load(new BufferedReader(reader));
    }

    private List<ExchangeRate> load(BufferedReader reader) throws IOException {
        List<ExchangeRate> result = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            result.add(toExchangeRate(line.split("\t")));
            line = reader.readLine();
        }
        return result;
    }

    private ExchangeRate toExchangeRate(String[] line) {
        return new ExchangeRate(
                new Currency("USD", "US Dollar", "$"),
                new Currency(line[0], line[1], ""),
                LocalDate.of(2024, 01, 10),
                parseDouble(line[2])
        );
    }
}
