package org.neo4j.ogm.types.point;

public interface CartesianPoint3D extends Point {

    double getX();
    double getY();
    double getZ();

    default SpatialReferenceSystem getSpatialReferenceSystem() {
        return SpatialReferenceSystem.CARTESIAN_3D;
    }
}
