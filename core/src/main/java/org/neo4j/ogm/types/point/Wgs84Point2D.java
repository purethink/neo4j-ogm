package org.neo4j.ogm.types.point;

public interface Wgs84Point2D extends Point {

    double getLongitude();
    double getLatitude();

    default SpatialReferenceSystem getSpatialReferenceSystem() {
        return SpatialReferenceSystem.WGS_84;
    }
}
