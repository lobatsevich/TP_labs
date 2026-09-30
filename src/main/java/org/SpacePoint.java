package org;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpacePoint {


    private static final Logger logger = LoggerFactory.getLogger(SpacePoint.class);

    private final RationalFraction x;
    private final RationalFraction y;
    private final RationalFraction z;

    public SpacePoint(RationalFraction x, RationalFraction y, RationalFraction z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceTo(SpacePoint other) {
        double dx = this.x.toDouble() - other.x.toDouble();
        double dy = this.y.toDouble() - other.y.toDouble();
        double dz = this.z.toDouble() - other.z.toDouble();

        double d = Math.sqrt(dx * dx + dy * dy + dz * dz);

        logger.info("Расстояние между {} и {}: {}", this, other, d);
        return d;
    }

    public double distanceToOrigin() {
        double dx = this.x.toDouble();
        double dy = this.y.toDouble();
        double dz = this.z.toDouble();

        double d = Math.sqrt(dx * dx + dy * dy + dz * dz);

        logger.info("Расстояние от {} до начала координат: {}", this, d);
        return d;
    }

    public static boolean areCollinear(SpacePoint p1, SpacePoint p2, SpacePoint p3) {
        double abX = p2.x.toDouble() - p1.x.toDouble();
        double abY = p2.y.toDouble() - p1.y.toDouble();
        double abZ = p2.z.toDouble() - p1.z.toDouble();

        double acX = p3.x.toDouble() - p1.x.toDouble();
        double acY = p3.y.toDouble() - p1.y.toDouble();
        double acZ = p3.z.toDouble() - p1.z.toDouble();

        double trianI = abY * acZ - abZ * acY;
        double trianJ = abZ * acX - abX * acZ;
        double trianK = abX * acY - abY * acX;

        double e = 1e-9;

        boolean collinear =
                Math.abs(trianI) < e &&
                Math.abs(trianJ) < e &&
                Math.abs(trianK) < e;

        logger.info("Коллинеарность точек {}, {}, {}: {}", p1, p2, p3, collinear);
        return collinear;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}