package org.neo4j.ogm.types.point;

public interface CartesianPoint2D extends Point {

    double getX();
    double getY();

    default SpatialReferenceSystem getSpatialReferenceSystem() {
        return SpatialReferenceSystem.CARTESIAN;
    }
}
