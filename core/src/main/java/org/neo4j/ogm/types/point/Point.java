package org.neo4j.ogm.types.point;

public interface Point {

    default String getCoordinateReferenceSystem() {
        return getSpatialReferenceSystem().getCrs();
    }

    default Integer getSpatialReferenceSystemIdentifier() {
        return getSpatialReferenceSystem().getSrid();
    }

    SpatialReferenceSystem getSpatialReferenceSystem();

}
