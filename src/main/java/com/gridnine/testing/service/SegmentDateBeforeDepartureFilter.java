package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.impl.FilterRuleImpl;

import java.time.LocalDateTime;

/**
 * The class filters segments by date before departure
 */
public class SegmentDateBeforeDepartureFilter implements FilterRuleImpl {

    /**
     * Filters segments by date before departure
     * @param flight Instance flight {@link Flight}
     * @return true or false
     */
    @Override
    public boolean filter(Flight flight) {
        LocalDateTime departureDate = flight.getSegments().get(0).getDepartureDate();
        return flight.getSegments().stream()
                .allMatch(s -> s.getArrivalDate().isAfter(departureDate));
    }
}
