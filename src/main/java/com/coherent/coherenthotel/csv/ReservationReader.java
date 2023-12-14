package com.coherent.coherenthotel.csv;

import com.coherent.coherenthotel.model.Reservation;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReservationReader {

  private static final String FILE_PATH = "/data/reservations.csv";

  public Set<Reservation> readFromFile() {
    Set<Reservation> reservations = new HashSet<>();
    try (Reader reader = new FileReader(new ClassPathResource(FILE_PATH).getFile());
        CSVReader csvReader = new CSVReader(reader)) {

      String[] line;
      while ((line = csvReader.readNext()) != null) {
        log.info("Reading the line : {}", (Object) line);
        Integer id = Integer.parseInt(line[0]);
        String clientFullName = line[1];
        Integer roomNumber = Integer.parseInt(line[2]);
        List<LocalDate> reservationDates = Arrays.stream(line[3].split(","))
            .map(LocalDate::parse).toList();
        Reservation reservation = Reservation.builder()
            .id(id)
            .clientFullName(clientFullName)
            .roomNumber(roomNumber)
            .reservationDates(reservationDates)
            .build();
        reservations.add(reservation);
      }
    } catch (IOException | CsvValidationException e) {
      log.error("An exception has occurred : {}", e.getMessage());
    }
    return reservations;
  }
}
