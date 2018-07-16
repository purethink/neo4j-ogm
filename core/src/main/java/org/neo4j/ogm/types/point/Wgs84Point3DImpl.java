package org.neo4j.ogm.types.point;

class Wgs84Point3DImpl implements Wgs84Point3D {

    private final double longitude;
    private final double latitude;
    private final double height;

    Wgs84Point3DImpl(double longitude, double latitude, double height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getHeight() {
        return height;
    }
}
