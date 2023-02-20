package com.example.flight.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RoadGoatAPIDataDto {

    private List<IncludedDto> included;
}
