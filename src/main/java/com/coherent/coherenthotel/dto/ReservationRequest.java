package com.coherent.coherenthotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class ReservationRequest {

  @NotBlank
  private String clientFullName;

  @NotBlank
  private Integer roomNumber;

  @NotNull
  @NotEmpty
  private List<LocalDate> reservationDates;
}
