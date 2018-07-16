package org.neo4j.ogm.types.point;

public interface Wgs84Point3D extends Point {

    double getLongitude();
    double getLatitude();
    double getHeight();

    default SpatialReferenceSystem getSpatialReferenceSystem() {
        return SpatialReferenceSystem.WGS_84_3D;
    }
}
