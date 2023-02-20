package com.example.flight.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HotelListDto implements Serializable {
    List<HotelDto> hotelDtoList;
}
