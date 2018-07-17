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
package org.neo4j.ogm.typeconversion;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.neo4j.ogm.exception.core.MappingException;
import org.neo4j.ogm.types.point.CartesianPoint2D;
import org.neo4j.ogm.types.point.CartesianPoint3D;
import org.neo4j.ogm.types.point.Point;
import org.neo4j.ogm.types.point.Points;
import org.neo4j.ogm.types.point.Wgs84Point2D;
import org.neo4j.ogm.types.point.Wgs84Point3D;

public final class PointConverter implements CompositeAttributeConverter<Point> {

    private final String fieldNamePrefix;

    PointConverter(String fieldName) {
        this.fieldNamePrefix = fieldName + ".";
    }

    @Override
    public Map<String, ?> toGraphProperties(Point point) {
        if (point == null) {
            return Collections.emptyMap();
        }
        return prefixedProperties(point);
    }

    @Override
    public Point toEntityAttribute(Map<String, ?> values) {
        try {
            return Points.fromProperties(stripPrefix(values));
        } catch (IllegalArgumentException iae) {
            throw new MappingException("Cannot map properties to point.", iae);
        }
    }

    private Map<String, ?> prefixedProperties(Point point) {
        switch (point.getSpatialReferenceSystem()) {
            case CARTESIAN:
                return cartesianPoint2DProperties((CartesianPoint2D) point);
            case CARTESIAN_3D:
                return cartesianPoint3DProperties((CartesianPoint3D) point);
            case WGS_84:
                return wgsPoint2DProperties((Wgs84Point2D) point);
            case WGS_84_3D:
                return wgsPoint3DProperties((Wgs84Point3D) point);
            default:
                return Collections.emptyMap();
        }
    }

    private Map<String, ?> cartesianPoint2DProperties(CartesianPoint2D point) {
        HashMap<String, Object> pointProperties = new HashMap<>();
        pointProperties.put(fieldNamePrefix + Point.X_PROPERTY, point.getX());
        pointProperties.put(fieldNamePrefix + Point.Y_PROPERTY, point.getY());
        pointProperties.put(fieldNamePrefix + Point.SRID_PROPERTY, point.getSpatialReferenceSystem().getSrid());
        pointProperties.put(fieldNamePrefix + Point.CRS_PROPERTY, point.getSpatialReferenceSystem().getCrs());
        return pointProperties;
    }

    private Map<String, ?> cartesianPoint3DProperties(CartesianPoint3D point) {
        HashMap<String, Object> pointProperties = new HashMap<>();
        pointProperties.put(fieldNamePrefix + Point.X_PROPERTY, point.getX());
        pointProperties.put(fieldNamePrefix + Point.Y_PROPERTY, point.getY());
        pointProperties.put(fieldNamePrefix + Point.Z_PROPERTY, point.getZ());
        pointProperties.put(fieldNamePrefix + Point.SRID_PROPERTY, point.getSpatialReferenceSystem().getSrid());
        pointProperties.put(fieldNamePrefix + Point.CRS_PROPERTY, point.getSpatialReferenceSystem().getCrs());
        return pointProperties;
    }

    private Map<String, ?> wgsPoint2DProperties(Wgs84Point2D point) {
        HashMap<String, Object> pointProperties = new HashMap<>();
        pointProperties.put(fieldNamePrefix + Point.LONGITUDE_PROPERTY, point.getLongitude());
        pointProperties.put(fieldNamePrefix + Point.LATITUDE_PROPERTY, point.getLatitude());
        pointProperties.put(fieldNamePrefix + Point.SRID_PROPERTY, point.getSpatialReferenceSystem().getSrid());
        pointProperties.put(fieldNamePrefix + Point.CRS_PROPERTY, point.getSpatialReferenceSystem().getCrs());
        return pointProperties;
    }

    private Map<String, ?> wgsPoint3DProperties(Wgs84Point3D point) {
        HashMap<String, Object> pointProperties = new HashMap<>();
        pointProperties.put(fieldNamePrefix + Point.LONGITUDE_PROPERTY, point.getLongitude());
        pointProperties.put(fieldNamePrefix + Point.LATITUDE_PROPERTY, point.getLatitude());
        pointProperties.put(fieldNamePrefix + Point.HEIGHT_PROPERTY, point.getHeight());
        pointProperties.put(fieldNamePrefix + Point.SRID_PROPERTY, point.getSpatialReferenceSystem().getSrid());
        pointProperties.put(fieldNamePrefix + Point.CRS_PROPERTY, point.getSpatialReferenceSystem().getCrs());
        return pointProperties;
    }

    private Map<String, ?> stripPrefix(Map<String, ?> prefixedProperties) {
        return prefixedProperties.entrySet().stream()
            .collect(Collectors.toMap(e -> e.getKey().replaceAll(fieldNamePrefix, ""), Map.Entry::getValue));
    }
}
