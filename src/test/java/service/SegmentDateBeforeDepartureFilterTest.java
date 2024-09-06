package service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.SegmentDateBeforeDepartureFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SegmentDateBeforeDepartureFilterTest {
    private final SegmentDateBeforeDepartureFilter filter = new SegmentDateBeforeDepartureFilter();

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
                new Segment(LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(4))
        ));

        boolean result1 = filter.filter(flight1);
        boolean result2 = filter.filter(flight3);

        assertTrue(result1);
        assertFalse(result2);
    }
}
