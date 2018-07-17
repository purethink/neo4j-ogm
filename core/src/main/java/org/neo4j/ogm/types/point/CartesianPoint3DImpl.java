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

class CartesianPoint3DImpl implements CartesianPoint3D {

    private final Map<String, Double> properties = new HashMap<>(3);

    CartesianPoint3DImpl(double x, double y, double z) {
        properties.put(X_PROPERTY, x);
        properties.put(Y_PROPERTY, y);
        properties.put(Z_PROPERTY, z);
    }

    @Override
    public double getX() {
        return properties.get(X_PROPERTY);
    }

    @Override
    public double getY() {
        return properties.get(Y_PROPERTY);
    }

    @Override
    public double getZ() {
        return properties.get(Z_PROPERTY);
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
        CartesianPoint3DImpl that = (CartesianPoint3DImpl) o;
        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }
}
