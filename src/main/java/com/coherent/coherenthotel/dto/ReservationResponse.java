package com.coherent.coherenthotel.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationResponse {

  private Integer id;

  private String clientFullName;

  private Integer roomNumber;

  private List<LocalDate> reservationDates;

}
