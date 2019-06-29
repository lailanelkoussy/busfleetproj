package com.busfleetproj.busfleetproj.dto;

import lombok.Data;

@Data
public class RouteDTO {

    private int routeId;

    private String area;

    private String startPoint;

    private String endPoint;
}
