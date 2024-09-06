package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.impl.FilterRuleImpl;

import java.time.LocalDateTime;

/**
 * The class for filtering flights with a date earlier than the current one
 */
public class DepartureBeforeCurrentTimeFilter implements FilterRuleImpl {
    /**
     * Compares the departure date with the current one
     * @param flight Instance flight {@link Flight}
     * @return true or false
     */
    @Override
    public boolean filter(Flight flight) {
        return flight.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now());
    }

}
