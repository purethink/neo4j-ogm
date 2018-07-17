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

import org.neo4j.ogm.exception.core.MappingException;
import org.neo4j.ogm.types.point.CartesianPoint2D;
import org.neo4j.ogm.types.point.CartesianPoint3D;
import org.neo4j.ogm.types.point.Point;
import org.neo4j.ogm.types.point.PointBuilder;
import org.neo4j.ogm.types.point.Wgs84Point2D;
import org.neo4j.ogm.types.point.Wgs84Point3D;

public final class PointConverter implements CompositeAttributeConverter<Point> {

    private final String fieldNamePrefix;

    private final String[] REPLACEABLE_PROPERTIES = {
        CartesianPoint2D.X_PROPERTY, CartesianPoint2D.Y_PROPERTY,
        CartesianPoint3D.X_PROPERTY, CartesianPoint3D.Y_PROPERTY, CartesianPoint3D.Z_PROPERTY,
        Wgs84Point2D.LONGITUDE_PROPERTY, Wgs84Point2D.LATITUDE_PROPERTY,
        Wgs84Point3D.LONGITUDE_PROPERTY, Wgs84Point3D.LATITUDE_PROPERTY, Wgs84Point3D.HEIGHT_PROPERTY
    };

    public PointConverter(String fieldName) {
        this.fieldNamePrefix = fieldName + ".";
    }

    @Override
    public Map<String, ?> toGraphProperties(Point point) {
        if (point == null) {
            return Collections.emptyMap();
        }
        Map<String, ?> properties = point.asProperties();

        return addPrefix((Map<String, ? super Object>) properties);
    }

    @Override
    public Point toEntityAttribute(Map<String, ?> values) {
        try {
            return PointBuilder.fromProperties(stripPrefix((Map<String, ? super Object>) values));
        } catch (IllegalArgumentException iae) {
            throw new MappingException("Cannot map properties to point.", iae);
        }
    }

    private Map<String, ?> addPrefix(Map<String, ? super Object> pointProperties) {
        Map<String, ? super Object> prefixedProperties = new HashMap<>();

        for (String replaceableProperty : REPLACEABLE_PROPERTIES) {
            if (pointProperties.containsKey(replaceableProperty)) {
                prefixedProperties.put(fieldNamePrefix + replaceableProperty, pointProperties.get(replaceableProperty));
            }
        }

        return prefixedProperties;
    }

    private Map<String, ?> stripPrefix(Map<String, ? super Object> prefixedProperties) {
        Map<String, ? super Object> properties = new HashMap<>();
        System.out.println("Hallo: " + prefixedProperties);
        for (String replaceableProperty : REPLACEABLE_PROPERTIES) {
            if (prefixedProperties.containsKey(fieldNamePrefix + replaceableProperty)) {
                properties.put(replaceableProperty, prefixedProperties.get(fieldNamePrefix + replaceableProperty));
            }
        }

        return properties;
    }
}
