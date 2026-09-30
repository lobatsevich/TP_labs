import org.RationalFraction;
import org.SpacePoint;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpacePointTest {
    @Test
    public void testFractionReduction() {
        RationalFraction fraction = new RationalFraction(4, 8);
        assertEquals(0.5, fraction.toDouble(), 1e-9);
        assertEquals("1/2", fraction.toString());
    }

    @Test
    public void testFractionNegativeDenominator() {
        RationalFraction fraction = new RationalFraction(1, -2);
        assertEquals(-0.5, fraction.toDouble(), 1e-9);
        assertEquals("-1/2", fraction.toString());
    }

    @Test
    public void testFractionZeroDenominator() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RationalFraction(5, 0);
        });
    }

    @Test
    public void testDistanceToOrigin() {
        SpacePoint point = new SpacePoint(
                new RationalFraction(3, 1),
                new RationalFraction(4, 1),
                new RationalFraction(0, 1)
        );
        assertEquals(5.0, point.distanceToOrigin(), 1e-9);
    }

    @Test
    public void testDistanceToOriginWithNegatives() {
        SpacePoint point = new SpacePoint(
                new RationalFraction(-3, 1),
                new RationalFraction(0, 1),
                new RationalFraction(-4, 1)
        );
        assertEquals(5.0, point.distanceToOrigin(), 1e-9);
    }

    @Test
    public void testDistanceBetweenTwoPoints() {
        SpacePoint p1 = new SpacePoint(
                new RationalFraction(1, 1),
                new RationalFraction(1, 1),
                new RationalFraction(1, 1)
        );
        SpacePoint p2 = new SpacePoint(
                new RationalFraction(2, 1),
                new RationalFraction(3, 1),
                new RationalFraction(3, 1)
        );
        assertEquals(3.0, p1.distanceTo(p2), 1e-9);
    }

    @Test
    public void testAreCollinearTrueWithRealFractions() {
        SpacePoint p1 = new SpacePoint(
                new RationalFraction(1, 3),
                new RationalFraction(1, 3),
                new RationalFraction(1, 3)
        );
        SpacePoint p2 = new SpacePoint(
                new RationalFraction(2, 3),
                new RationalFraction(2, 3),
                new RationalFraction(2, 3)
        );
        SpacePoint p3 = new SpacePoint(
                new RationalFraction(1, 1),
                new RationalFraction(1, 1),
                new RationalFraction(1, 1)
        );

        assertTrue(SpacePoint.areCollinear(p1, p2, p3));
    }

    @Test
    public void testAreCollinearFalse() {
        SpacePoint p1 = new SpacePoint(
                new RationalFraction(0, 1),
                new RationalFraction(0, 1),
                new RationalFraction(0, 1)
        );
        SpacePoint p2 = new SpacePoint(
                new RationalFraction(1, 1),
                new RationalFraction(0, 1),
                new RationalFraction(0, 1)
        );
        SpacePoint p3 = new SpacePoint(
                new RationalFraction(0, 1),
                new RationalFraction(1, 1),
                new RationalFraction(0, 1)
        );

        assertFalse(SpacePoint.areCollinear(p1, p2, p3));
    }

    @Test
    public void testAreCollinearCoincidingPoints() {
        SpacePoint p1 = new SpacePoint(
                new RationalFraction(1, 2),
                new RationalFraction(1, 2),
                new RationalFraction(1, 2)
        );
        SpacePoint p2 = new SpacePoint(
                new RationalFraction(1, 2),
                new RationalFraction(1, 2),
                new RationalFraction(1, 2)
        );
        SpacePoint p3 = new SpacePoint(
                new RationalFraction(5, 1),
                new RationalFraction(3, 1),
                new RationalFraction(2, 1)
        );

        assertTrue(SpacePoint.areCollinear(p1, p2, p3));
    }
}
