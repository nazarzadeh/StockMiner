package com.stockminer.stockminer.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import loader.CsvBarsLoader;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvFileLoaderServiceImpl implements LoaderService {
    private String filename;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     *
     * @param filename Sinfle file or package i n future.
     */
    public CsvFileLoaderServiceImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public BarSeries getBarSeries() {
        InputStream stream = CsvBarsLoader.class.getClassLoader().getResourceAsStream(filename);
        BarSeries series = new BaseBarSeries(filename.split("[.]")[0]);
        CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        try (csvReader) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                ZonedDateTime date = LocalDate.parse(line[0], DATE_FORMAT).atStartOfDay(ZoneId.systemDefault());
                double open = Double.parseDouble(line[1]);
                double high = Double.parseDouble(line[2]);
                double low = Double.parseDouble(line[3]);
                double close = Double.parseDouble(line[4]);
                double volume = Double.parseDouble(line[5]);
                series.addBar(date, open, high, low, close, volume);
            }
        } catch (IOException ioe) {
            Logger.getLogger(CsvBarsLoader.class.getName()).log(Level.SEVERE, "Unable to load bars from CSV", ioe);
        } catch (NumberFormatException nfe) {
            Logger.getLogger(CsvBarsLoader.class.getName()).log(Level.SEVERE, "Error while parsing value", nfe);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return series;
    }
}
