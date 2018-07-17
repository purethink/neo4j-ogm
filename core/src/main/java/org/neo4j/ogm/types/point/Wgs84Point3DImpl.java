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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Wgs84Point3DImpl implements Wgs84Point3D {

    private final Map<String, Double> properties = new HashMap<>(3);

    Wgs84Point3DImpl(double longitude, double latitude, double height) {
        properties.put(LONGITUDE_PROPERTY, longitude);
        properties.put(LATITUDE_PROPERTY, latitude);
        properties.put(HEIGHT_PROPERTY, height);
    }

    @Override
    public double getLongitude() {
        return properties.get(LONGITUDE_PROPERTY);
    }

    @Override
    public double getLatitude() {
        return properties.get(LATITUDE_PROPERTY);
    }

    @Override
    public double getHeight() {
        return properties.get(HEIGHT_PROPERTY);
    }

    @Override
    public Map<String, ?> asProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Wgs84Point3DImpl that = (Wgs84Point3DImpl) o;
        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }
}
