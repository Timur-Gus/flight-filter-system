package service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.service.FilterServiceImpl;
import com.gridnine.testing.service.SegmentDateBeforeDepartureFilter;
import com.gridnine.testing.service.TimeOnEarthExceedsTwoHoursFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FilterServiceImplTest {
    private final FilterServiceImpl filterService = new FilterServiceImpl(
            new DepartureBeforeCurrentTimeFilter(),
            new SegmentDateBeforeDepartureFilter(),
            new TimeOnEarthExceedsTwoHoursFilter()
    );

    @Test
    void filter() {

        List<Flight> flights = Arrays.asList(
                new Flight(Arrays.asList(
                        new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                        new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4))
                )),
                new Flight(Arrays.asList(
                        new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now().minusHours(2)),
                        new Segment(LocalDateTime.now().minusHours(3), LocalDateTime.now().minusHours(4))
                )),
                new Flight(Arrays.asList(
                        new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                        new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5)),
                        new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(7))
                )),
                new Flight(Arrays.asList(
                        new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now().minusHours(2)),
                        new Segment(LocalDateTime.now().minusHours(3), LocalDateTime.now().minusHours(4)),
                        new Segment(LocalDateTime.now().minusHours(5), LocalDateTime.now().minusHours(6))
                ))
        );


        List<Flight> filteredFlights = filterService.filter(flights);

        assertEquals(2, filteredFlights.size());
        assertEquals(flights.get(0), filteredFlights.get(0));
        assertEquals(flights.get(2), filteredFlights.get(1));
    }

}
