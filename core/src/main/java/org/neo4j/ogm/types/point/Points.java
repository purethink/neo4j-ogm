/*
 * Copyright (c) 2002-2018 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 *  conditions of the subcomponent's license, as noted in the LICENSE file.
 */
package org.neo4j.ogm.types.point;

import java.util.Map;

public class Points {

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

    public static Point fromProperties(Map<String, ?> values) {

        boolean isWgs84Point3D = isWgs84Point3D(values);
        boolean isWgs84Point2D = isWgs84Point2D(values);
        boolean isCartesianPoint2D = isCartesianPoint2D(values);
        boolean isCartesianPoint3D = isCartesianPoint3D(values);

        if (isAmbiguous(isWgs84Point2D, isCartesianPoint2D)) {
            throw new IllegalArgumentException(
                "Ambiguous properties: The point type can either be Wgs84 or cartesian.");
        }

        if (isWgs84Point3D) {
            return wgs84Point3DFromProperties(values);
        }
        if (isWgs84Point2D) {
            return wgs84Point2DFromProperties(values);
        }
        if (isCartesianPoint3D) {
            return cartesianPoint3DFromProperties(values);
        }
        if (isCartesianPoint2D) {
            return cartesianPoint2DFromProperties(values);
        }

        return null;
    }

    private static boolean isWgs84Point3D(Map<String, ?> values) {
        Object longitude = values.get(Point.LONGITUDE_PROPERTY);
        Object latitude = values.get(Point.LATITUDE_PROPERTY);
        Object height = values.get(Point.HEIGHT_PROPERTY);
        Object xCoordinate = values.get(Point.X_PROPERTY);
        Object yCoordinate = values.get(Point.Y_PROPERTY);
        Object zCoordinate = values.get(Point.Z_PROPERTY);
        Object srid = values.get(Point.SRID_PROPERTY);
        Object crs = values.get(Point.CRS_PROPERTY);

        boolean hasPolarCoordinates =
            longitude instanceof Number && latitude instanceof Number && height instanceof Number;

        boolean explicitlyWgsTyped = SpatialReferenceSystem.WGS_84_3D.matches(crs, srid);

        boolean hasCartesianCoordinates =
            xCoordinate instanceof Number && yCoordinate instanceof Number && zCoordinate instanceof Number;

        return hasPolarCoordinates ||
            (explicitlyWgsTyped && hasCartesianCoordinates);
    }

    private static boolean isWgs84Point2D(Map<String, ?> values) {
        Object longitude = values.get(Point.LONGITUDE_PROPERTY);
        Object latitude = values.get(Point.LATITUDE_PROPERTY);
        Object xCoordinate = values.get(Point.X_PROPERTY);
        Object yCoordinate = values.get(Point.Y_PROPERTY);
        Object srid = values.get(Point.SRID_PROPERTY);
        Object crs = values.get(Point.CRS_PROPERTY);

        boolean hasPolarCoordinates = longitude instanceof Number && latitude instanceof Number;

        boolean explicitlyWgsTyped = SpatialReferenceSystem.WGS_84.matches(crs, srid);

        boolean hasCartesianCoordinates = xCoordinate instanceof Number && yCoordinate instanceof Number;

        return hasPolarCoordinates ||
            (explicitlyWgsTyped && hasCartesianCoordinates);
    }

    private static boolean isCartesianPoint3D(Map<String, ?> values) {
        Object xCoordinate = values.get(Point.X_PROPERTY);
        Object yCoordinate = values.get(Point.Y_PROPERTY);
        Object zCoordinate = values.get(Point.Z_PROPERTY);
        Object srid = values.get(Point.SRID_PROPERTY);
        Object crs = values.get(Point.CRS_PROPERTY);

        boolean explicitlyTypedOrNull = (crs == null && srid == null)
            || SpatialReferenceSystem.CARTESIAN_3D.matches(crs, srid);

        return explicitlyTypedOrNull && (xCoordinate instanceof Number && yCoordinate instanceof Number
            && zCoordinate instanceof Number);
    }

    private static boolean isCartesianPoint2D(Map<String, ?> values) {
        Object xCoordinate = values.get(Point.X_PROPERTY);
        Object yCoordinate = values.get(Point.Y_PROPERTY);
        Object srid = values.get(Point.SRID_PROPERTY);
        Object crs = values.get(Point.CRS_PROPERTY);

        boolean explicitlyTypedOrNull = (crs == null && srid == null)
            || SpatialReferenceSystem.CARTESIAN.matches(crs, srid);

        return explicitlyTypedOrNull && (xCoordinate instanceof Number && yCoordinate instanceof Number);
    }

    private static boolean isAmbiguous(boolean isWgsPoint, boolean isCartesianPoint) {
        return isWgsPoint && isCartesianPoint;
    }

    private static Wgs84Point3D wgs84Point3DFromProperties(Map<String, ?> properties) {
        Object longitude = properties.get(Point.LONGITUDE_PROPERTY);
        Object latitude = properties.get(Point.LATITUDE_PROPERTY);
        Object height = properties.get(Point.HEIGHT_PROPERTY);

        if (longitude != null && latitude != null && height != null) {
            return fromPolarCoordinates(
                Double.parseDouble(longitude.toString()),
                Double.parseDouble(latitude.toString()),
                Double.parseDouble(height.toString())
            );
        }

        Object xCoordinate = properties.get(Point.X_PROPERTY);
        Object yCoordinate = properties.get(Point.Y_PROPERTY);
        Object zCoordinate = properties.get(Point.Z_PROPERTY);

        return fromPolarCoordinates(
            Double.parseDouble(xCoordinate.toString()),
            Double.parseDouble(yCoordinate.toString()),
            Double.parseDouble(zCoordinate.toString())
        );
    }

    private static Wgs84Point2D wgs84Point2DFromProperties(Map<String, ?> properties) {
        Object longitude = properties.get(Point.LONGITUDE_PROPERTY);
        Object latitude = properties.get(Point.LATITUDE_PROPERTY);

        if (longitude != null && latitude != null) {
            return fromPolarCoordinates(
                Double.parseDouble(longitude.toString()),
                Double.parseDouble(latitude.toString())
            );
        }

        Object xCoordinate = properties.get(Point.X_PROPERTY);
        Object yCoordinate = properties.get(Point.Y_PROPERTY);

        return fromPolarCoordinates(
            Double.parseDouble(xCoordinate.toString()),
            Double.parseDouble(yCoordinate.toString())
        );
    }

    private static CartesianPoint3D cartesianPoint3DFromProperties(Map<String, ?> properties) {
        Object xCoordinate = properties.get(Point.X_PROPERTY);
        Object yCoordinate = properties.get(Point.Y_PROPERTY);
        Object zCoordinate = properties.get(Point.Z_PROPERTY);

        return fromCartesianCoordinates(
            Double.parseDouble(xCoordinate.toString()),
            Double.parseDouble(yCoordinate.toString()),
            Double.parseDouble(zCoordinate.toString())
        );
    }

    private static CartesianPoint2D cartesianPoint2DFromProperties(Map<String, ?> properties) {
        Object xCoordinate = properties.get(Point.X_PROPERTY);
        Object yCoordinate = properties.get(Point.Y_PROPERTY);

        return fromCartesianCoordinates(
            Double.parseDouble(xCoordinate.toString()),
            Double.parseDouble(yCoordinate.toString())
        );
    }
}
