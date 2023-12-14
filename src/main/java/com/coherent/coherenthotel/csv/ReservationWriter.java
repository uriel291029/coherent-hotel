package com.coherent.coherenthotel.csv;

import com.coherent.coherenthotel.model.Reservation;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReservationWriter {

  private static final String FILE_PATH = "/data/reservations.csv";

  public void writeToFile(Set<Reservation> reservations) {

    try (Writer writer = new FileWriter(new ClassPathResource(FILE_PATH).getFile());
        CSVWriter csvWriter = new CSVWriter(writer)) {
      List<String[]> records = new ArrayList<>();
      for (Reservation reservation : reservations) {
        String id = reservation.getId().toString();
        String clientFullName = reservation.getClientFullName();
        String roomNumber = reservation.getRoomNumber().toString();
        String reservationDates = reservation.getReservationDates().stream()
            .map(LocalDate::toString).collect(Collectors.joining(","));
        String[] data = {id, clientFullName, roomNumber, reservationDates};
        records.add(data);
      }
      csvWriter.writeAll(records);
      log.info("Data size {} has been written to {}", records.size(), FILE_PATH);
    } catch (IOException e) {
      log.error("An exception has occurred : {}", e.getMessage());
    }
  }
}
