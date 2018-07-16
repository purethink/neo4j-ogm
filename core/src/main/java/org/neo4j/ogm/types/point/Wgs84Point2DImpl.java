package org.neo4j.ogm.types.point;

class Wgs84Point2DImpl implements Wgs84Point2D {

    private final double longitude;
    private final double latitude;

    Wgs84Point2DImpl(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }
}
