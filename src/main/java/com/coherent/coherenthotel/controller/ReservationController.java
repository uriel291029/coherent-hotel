package com.coherent.coherenthotel.controller;

import com.coherent.coherenthotel.dto.ReservationRequest;
import com.coherent.coherenthotel.dto.ReservationResponse;
import com.coherent.coherenthotel.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  public ReservationResponse saveReservation(@RequestBody ReservationRequest reservationRequest){
     log.info("The following reservation will be processed : {}", reservationRequest);
     return reservationService.saveReservation(reservationRequest);
  }

  @GetMapping
  public List<ReservationResponse> retrieveReservations(){
    log.info("Retrieving all the reservations.");
    return reservationService.retrieveReservations();
  }

  @PutMapping("{id}")
  public ReservationResponse updateReservation(@PathVariable Integer id, @RequestBody ReservationRequest reservationRequest){
    log.info("The following reservation {} will be updated : {}", id, reservationRequest);
    return reservationService.updateReservation(id, reservationRequest);
  }
}
