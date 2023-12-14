package com.coherent.coherenthotel.service;

import com.coherent.coherenthotel.dto.ReservationRequest;
import com.coherent.coherenthotel.dto.ReservationResponse;
import com.coherent.coherenthotel.model.Reservation;
import com.coherent.coherenthotel.repository.ReservationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;

  public ReservationResponse saveReservation(ReservationRequest reservationRequest) {
    log.info("Saving the reservation: {}", reservationRequest);
    Reservation reservation = Reservation.builder()
        .reservationDates(reservationRequest.getReservationDates())
        .clientFullName(reservationRequest.getClientFullName())
        .roomNumber(reservationRequest.getRoomNumber())
        .build();
    reservation = reservationRepository.save(reservation);
    return ReservationResponse.builder()
        .id(reservation.getId())
        .reservationDates(reservation.getReservationDates())
        .clientFullName(reservation.getClientFullName())
        .roomNumber(reservation.getRoomNumber())
        .build();
  }

  public List<ReservationResponse> retrieveReservations() {
    log.info("Retrieving all the reservations.");
    return reservationRepository.findAll().stream().map(reservation -> ReservationResponse.builder()
        .id(reservation.getId())
        .reservationDates(reservation.getReservationDates())
        .clientFullName(reservation.getClientFullName())
        .roomNumber(reservation.getRoomNumber())
        .build()).toList();
  }

  public ReservationResponse updateReservation(Integer id,
      ReservationRequest reservationRequest) {
    log.info("Saving the reservation: {} {}", id, reservationRequest);
    Reservation reservation = new Reservation();
    reservation.setId(id);
    reservation.setReservationDates(reservationRequest.getReservationDates());
    reservation.setRoomNumber(reservationRequest.getRoomNumber());
    reservation.setClientFullName(reservationRequest.getClientFullName());
    reservationRepository.deleteById(id);
    reservation = reservationRepository.save(reservation);
    return ReservationResponse.builder()
        .id(reservation.getId())
        .reservationDates(reservation.getReservationDates())
        .clientFullName(reservation.getClientFullName())
        .roomNumber(reservation.getRoomNumber())
        .build();
  }
}
