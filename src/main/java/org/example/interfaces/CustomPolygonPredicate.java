package org.example.interfaces;

import org.example.model.Point;
import java.util.List;

@FunctionalInterface
public interface CustomPolygonPredicate {
    boolean test(Point point, List<Point> polygon);
}
