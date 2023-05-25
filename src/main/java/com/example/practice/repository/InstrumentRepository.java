package com.example.practice.repository;

import com.example.practice.model.Instrument;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository

public class InstrumentRepository implements IInstrumentRepository{
    private String file = "C:\\Users\\Иришка\\Desktop\\practice\\src\\main\\resources\\Files\\Instrument.csv";
    private String fileHead = "id;title;unit_of_measurement;division_price;sensitivity;maximum_value;number_of_box";
    @Override
    public List<Instrument> getAllInstrument() {
        List<Instrument> instruments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {//конструкция, чтобы ничего не поломалось
            String line;

            // пропускаем заголовок файла
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");//строка записывается в массив

                Long id = Long.parseLong(fields[0]);//строку переводим в тип Long, присваеивается часть массива
                String title = fields[1];
                String unit_of_measurement = fields[2];
                int division_price = Integer.parseInt(fields[3]);
                double sensitivity = Double.parseDouble(fields[4]);
                int maximum_value = Integer.parseInt(fields[5]);
                int number_of_box = Integer.parseInt(fields[6]);

                Instrument instrument = new Instrument(id, title, unit_of_measurement, division_price, sensitivity, maximum_value, number_of_box);
                instruments.add(instrument);//добавление в список инструментов
            }
        } catch (IOException e) {
            System.err.format("Ошибка чтения файла: %s%n", e);
        }

        return instruments;
    }

    @Override
    public Instrument getInstrumentById(Long id) {
        List<Instrument> instruments = getAllInstrument();
        for (Instrument instrument : instruments) {
            if (instrument.getId() == id) {
                return instrument;//получение инструмента по айдишнику
            }
        }
        return null;
    }

    @Override
    public void createInstrument(Instrument instrument) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
             CSVPrinter csvPrinter = new CSVPrinter(
                     writer, CSVFormat.DEFAULT
                     .withDelimiter(';'))) {

            int lastID = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].matches("\\d+")) {
                    int id = Integer.parseInt(parts[0]); //создается id считываемый с первого столбца файла
                    if (id > lastID) {
                        lastID = id;
                    }
                }
            }

            int newID = lastID + 1;
            if (lastID == 0) {
                newID = 1;
            }

            if (file.length() == 0) {
                csvPrinter.printRecord("id", "title", "unit_of_measurement", "division_price", "sensitivity", "maximum_value", "number_of_box");
            }

            csvPrinter.printRecord(
                    newID,
                    instrument.getTitle(),
                    instrument.getUnit_of_measurement(),
                    instrument.getDivision_price(),
                    instrument.getSensitivity(),
                    instrument.getMaximum_value(),
                    instrument.getNumber_of_box()//получаем для того, чтоб записать
            );

            csvPrinter.flush();//без понятия (загуглить)
        } catch (IOException e) {
            System.err.println("Ошибка сохранения файла: " + e.getMessage());
        }
    }


    @Override
    public void deleteInstrument(Long id) {
        List<Instrument> instruments = getAllInstrument();
        Instrument instrumentToRemove = null;//инструмент для удаления пока ничему не равен
        for (Instrument instrument : instruments) {
            if (instrument.getId() == id) {
                instrumentToRemove = instrument;
                break;
            }//берем по айдишнику
        }
        if (instrumentToRemove != null) {
            instruments.remove(instrumentToRemove);//удаление из листа
            try {
                // Открываем файл на запись
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                // Записываем заголовок
                writer.write("id; title; unit_of_measurement; division_price; sensitivity; maximum_value; number_of_box");
                writer.newLine();

                // Записываем данные всех приборов, кроме удаленного
                for (Instrument instrument : instruments) {
                    writer.write(instrument.getId() + ";" +
                            instrument.getTitle() + ";" +
                            instrument.getUnit_of_measurement() + ";" +
                            instrument.getDivision_price() + ";" +
                            instrument.getSensitivity() + ";" +
                            instrument.getMaximum_value() + ";" +
                            instrument.getNumber_of_box());
                    writer.newLine();
                }

                // Закрываем файл
                writer.close();


            } catch (IOException e) {
                System.err.println("Ошибка сохранения файла: " + e.getMessage());
            }
        }
    }

    @SneakyThrows
    @Override
    public void updateInstrument(Instrument instrument){
        List<String> lines = Files.readAllLines(Path.of(file), StandardCharsets.UTF_8);//создаем массив линий и читаем каждую линию
        lines.remove(0);//удаляется нулевая линия
        List<String> updatedLines = new ArrayList<>();
        updatedLines.add(fileHead);
        for (String line : lines) {
            String[] values = line.split(";");
            if (Long.parseLong(values[0]) == instrument.getId()) {//id строки сравниваем с id, который нам пришел
                String updatedLine = String.format(Locale.US, "%d;%s;%s;%d;%.2f;%d;%d", instrument.getId(), instrument.getTitle(), instrument.getUnit_of_measurement(), instrument.getDivision_price(), instrument.getSensitivity(), instrument.getMaximum_value(), instrument.getNumber_of_box());
                updatedLines.add(updatedLine);
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(Path.of(file), updatedLines, StandardCharsets.UTF_8);
        System.out.println("Информация о приборе успешно изменена в файле.");
    } //catch (IOException e) {
        //System.err.println("Ошибка при изменении информации о студенте в файле: " + e.getMessage());
    //}
}
