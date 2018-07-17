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

public enum SpatialReferenceSystem {

    CARTESIAN("cartesian", 7203),
    CARTESIAN_3D("cartesian-3D", 9157),
    WGS_84_3D("WGS-84-3D", 4979),
    WGS_84("WGS-84", 4326);

    private final String crs;
    private final Integer srid;

    SpatialReferenceSystem(String crs, Integer srid) {
        this.crs = crs;
        this.srid = srid;
    }

    String getCrs() {
        return crs;
    }

    Integer getSrid() {
        return srid;
    }
}
