package service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.TimeOnEarthExceedsTwoHoursFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeOnEarthExceedsTwoHoursFilterTest {
    private final TimeOnEarthExceedsTwoHoursFilter filter = new TimeOnEarthExceedsTwoHoursFilter();

    @Test
    void filter() {

        Flight flight1 = new Flight(Arrays.asList(
                new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2)),
                new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4))
        ));

        Flight flight2 = new Flight(Arrays.asList(
                new Segment(LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(2)),
                new Segment(LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(4))
        ));

        Flight flight3 = new Flight(Arrays.asList(
                new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2)),
                new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4)),
                new Segment(LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6))
        ));

        boolean result1 = filter.filter(flight1);
        boolean result2 = filter.filter(flight2);
        boolean result3 = filter.filter(flight3);

        assertFalse(result1);
        assertTrue(result2);
        assertFalse(result3);
    }
}
