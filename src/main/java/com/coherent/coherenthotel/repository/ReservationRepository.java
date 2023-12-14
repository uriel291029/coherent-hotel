package com.coherent.coherenthotel.repository;

import com.coherent.coherenthotel.csv.ReservationReader;
import com.coherent.coherenthotel.csv.ReservationWriter;
import com.coherent.coherenthotel.model.Reservation;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReservationRepository {

  private Set<Reservation> reservations;

  private final ReservationWriter reservationWriter;

  private final ReservationReader reservationReader;

  private final Random random = new Random();

  @PostConstruct
  public void init() {
    log.info("Reading file to set reservations in memory.");
    this.reservations = reservationReader.readFromFile();
    log.info("Size of the reservations in memory : {}.", reservations.size());
  }

  public Reservation save(Reservation reservation) {
    int id = random.nextInt(Integer.MAX_VALUE);
    reservation.setId(id);
    Reservation reservationToSave = Reservation.builder()
        .id(id)
        .roomNumber(reservation.getRoomNumber())
        .clientFullName(reservation.getClientFullName())
        .reservationDates(reservation.getReservationDates())
        .build();
    reservations.add(reservationToSave);
    return reservation;
  }

  public Set<Reservation> findAll() {
    return reservations;
  }

  public Optional<Reservation> findById(Integer id) {
    return reservations.stream().filter(reservation -> reservation.getId().equals(id))
        .findAny();
  }

  public void deleteById(Integer id) {
    Optional<Reservation> reservation = findById(id);
    Reservation reservationToRemove = reservation.get();
    reservations.remove(reservationToRemove);
  }

  @PreDestroy
  public void store() {
    log.info("Storing the reservations in file.");
    reservationWriter.writeToFile(reservations);
  }
}
