package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.impl.FilterRuleImpl;

import java.time.Duration;
import java.util.List;

/**
 * The Class for screening flights with a total ground time exceeding two hours.
 */
public class TimeOnEarthExceedsTwoHoursFilter implements FilterRuleImpl {

    /**
     * Filtering by flights where the total time spent on the ground exceeds two hours
     * @param flight Instance flight {@link Flight}
     * @return true or false
     */
    @Override
    public boolean filter(Flight flight) {
        List<Segment> segments = flight.getSegments();
        Duration totalTimeOnEarth = Duration.ZERO;
        for (int i = 1; i < segments.size(); i++) {
            totalTimeOnEarth = totalTimeOnEarth.
                    plus(Duration.between(segments.get(i - 1).getArrivalDate(),
                            segments.get(i).getDepartureDate()));
        }
        return totalTimeOnEarth.toHours() <= 2;
    }
}
