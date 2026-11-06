package org.example.service;

import org.example.interfaces.CustomPolygonPredicate;
import org.example.model.Point;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public final class PolygonService {
    private static final double EPSILON = 1e-10;

    public static final CustomPolygonPredicate CUSTOM_WAY = PolygonService::checkInside;

    public static final BiPredicate<Point, List<Point>> STANDARD_WAY = PolygonService::checkInside;

    private static boolean checkInside(Point point, List<Point> polygon) {
        Objects.requireNonNull(point, "Точка не может быть null");
        Objects.requireNonNull(polygon, "Многоугольник не может быть null");
        if (polygon.size() < 3 || polygon.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Многоугольник должен содержать минимум три точки");
        }

        boolean inside = false;
        for (int i = 0, previous = polygon.size() - 1; i < polygon.size(); previous = i++) {
            Point currentPoint = polygon.get(i);
            Point previousPoint = polygon.get(previous);

            if (isOnSegment(point, previousPoint, currentPoint)) {
                return true;
            }

            boolean crossesScanLine = (currentPoint.getY() > point.getY())
                    != (previousPoint.getY() > point.getY());
            if (crossesScanLine) {
                double intersectionX = (previousPoint.getX() - currentPoint.getX())
                        * (point.getY() - currentPoint.getY())
                        / (previousPoint.getY() - currentPoint.getY())
                        + currentPoint.getX();
                if (point.getX() < intersectionX) {
                    inside = !inside;
                }
            }
        }
        return inside;
    }

    private static boolean isOnSegment(Point point, Point start, Point end) {
        double crossProduct = (point.getY() - start.getY()) * (end.getX() - start.getX())
                - (point.getX() - start.getX()) * (end.getY() - start.getY());
        if (Math.abs(crossProduct) > EPSILON) {
            return false;
        }
        return point.getX() >= Math.min(start.getX(), end.getX()) - EPSILON
                && point.getX() <= Math.max(start.getX(), end.getX()) + EPSILON
                && point.getY() >= Math.min(start.getY(), end.getY()) - EPSILON
                && point.getY() <= Math.max(start.getY(), end.getY()) + EPSILON;
    }
}
