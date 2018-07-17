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

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Wgs84Point3DImpl that = (Wgs84Point3DImpl) o;
        return Double.compare(that.longitude, longitude) == 0 &&
            Double.compare(that.latitude, latitude) == 0 &&
            Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, height);
    }
}
