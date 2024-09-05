package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FilterService {
    List<Flight> filter(List<Flight> flights);
}
