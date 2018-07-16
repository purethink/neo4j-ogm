package org.neo4j.ogm.types.point;

public enum SpatialReferenceSystem {

    CARTESIAN("cartesian", 7203),
    CARTESIAN_3D("cartesian-3D", 9157),
    WGS_84_3D("WGS-84-3D", 4979),
    WGS_84("WGS-84", 4326);

    private final String crs;
    private final Integer srid;

    SpatialReferenceSystem(String crs, Integer srid) {
        this.crs = crs;
        this.srid = srid;
    }

    String getCrs() {
        return crs;
    }

    Integer getSrid() {
        return srid;
    }
}
