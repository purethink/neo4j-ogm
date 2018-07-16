package org.neo4j.ogm.types.point;

public class PointBuilder {

    public static CartesianPoint2D fromCartesianCoordinates(double x, double y) {
        return new CartesianPoint2DImpl(x, y);
    }

    public static CartesianPoint3D fromCartesianCoordinates(double x, double y, double z) {
        return new CartesianPoint3DImpl(x, y, z);
    }

    public static Wgs84Point3D fromPolarCoordinates(double longitude, double latitude, double height) {
        return new Wgs84Point3DImpl(longitude, latitude, height);
    }

    public static Wgs84Point2D fromPolarCoordinates(double longitude, double latitude) {
        return new Wgs84Point2DImpl(longitude, latitude);
    }


}
