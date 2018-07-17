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

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.neo4j.ogm.domain.convertible.point.Place;
import org.neo4j.ogm.metadata.ClassInfo;
import org.neo4j.ogm.metadata.MetaData;
import org.neo4j.ogm.types.point.CartesianPoint2D;
import org.neo4j.ogm.types.point.CartesianPoint3D;
import org.neo4j.ogm.types.point.Point;
import org.neo4j.ogm.types.point.Points;
import org.neo4j.ogm.types.point.SpatialReferenceSystem;
import org.neo4j.ogm.types.point.Wgs84Point2D;
import org.neo4j.ogm.types.point.Wgs84Point3D;

/**
 * @author Gerrit Meier
 */
public class PointConverterTest {

    private final MetaData metaData = new MetaData("org.neo4j.ogm.domain.convertible.point");
    private final ClassInfo classInfo = metaData.classInfo("Place");

    @Test
    public void assertPlaceFieldsHaveDefaultConverters() {
        assertThat(classInfo.propertyField("simplePosition").hasCompositeConverter()).isTrue();
        assertThat(classInfo.propertyField("cartesianPoint2D").hasCompositeConverter()).isTrue();
        assertThat(classInfo.propertyField("cartesianPoint3D").hasCompositeConverter()).isTrue();
        assertThat(classInfo.propertyField("wgs84Point2D").hasCompositeConverter()).isTrue();
        assertThat(classInfo.propertyField("wgs84Point3D").hasCompositeConverter()).isTrue();
    }

    @Test
    public void assertSimplePointTypeGetsConverted() {
        String propertyName = "simplePosition";
        CompositeAttributeConverter<Point> converter = classInfo.propertyField(propertyName).getCompositeConverter();

        Place place = new Place();
        CartesianPoint2D simplePosition = Points.fromCartesianCoordinates(1d, 2d);
        place.setSimplePosition(simplePosition);

        Map<String, ? super Object> expectedProperties = new HashMap<>();
        expectedProperties.put(propertyName + ".x", 1d);
        expectedProperties.put(propertyName + ".y", 2d);
        expectedProperties.put(propertyName + ".srid", SpatialReferenceSystem.CARTESIAN.getSrid());
        expectedProperties.put(propertyName + ".crs", SpatialReferenceSystem.CARTESIAN.getCrs());

        assertThat(converter.toGraphProperties(place.getSimplePosition())).isEqualTo(expectedProperties);

        place.setSimplePosition(converter.toEntityAttribute(expectedProperties));
        assertThat(place.getSimplePosition()).isEqualTo(simplePosition);
    }

    @Test
    public void assertCartesianPoint2DTypeGetsConverted() {
        String propertyName = "cartesianPoint2D";
        CompositeAttributeConverter<Point> converter = classInfo.propertyField(propertyName).getCompositeConverter();

        Place place = new Place();
        CartesianPoint2D cartesianPoint2D = Points.fromCartesianCoordinates(1d, 2d);
        place.setCartesianPoint2D(cartesianPoint2D);

        Map<String, ? super Object> expectedProperties = new HashMap<>();
        expectedProperties.put(propertyName + ".x", 1d);
        expectedProperties.put(propertyName + ".y", 2d);
        expectedProperties.put(propertyName + ".srid", SpatialReferenceSystem.CARTESIAN.getSrid());
        expectedProperties.put(propertyName + ".crs", SpatialReferenceSystem.CARTESIAN.getCrs());

        assertThat(converter.toGraphProperties(place.getCartesianPoint2D())).isEqualTo(expectedProperties);

        place.setCartesianPoint2D((CartesianPoint2D) converter.toEntityAttribute(expectedProperties));
        assertThat(place.getCartesianPoint2D()).isEqualTo(cartesianPoint2D);
    }

    @Test
    public void assertCartesianPoint3DTypeGetsConverted() {
        String propertyName = "cartesianPoint3D";
        CompositeAttributeConverter<Point> converter = classInfo.propertyField(propertyName).getCompositeConverter();

        Place place = new Place();
        CartesianPoint3D cartesianPoint3D = Points.fromCartesianCoordinates(1d, 2d, 3d);
        place.setCartesianPoint3D(cartesianPoint3D);

        Map<String, ? super Object> expectedProperties = new HashMap<>();
        expectedProperties.put(propertyName + ".x", 1d);
        expectedProperties.put(propertyName + ".y", 2d);
        expectedProperties.put(propertyName + ".z", 3d);
        expectedProperties.put(propertyName + ".srid", SpatialReferenceSystem.CARTESIAN_3D.getSrid());
        expectedProperties.put(propertyName + ".crs", SpatialReferenceSystem.CARTESIAN_3D.getCrs());

        assertThat(converter.toGraphProperties(place.getCartesianPoint3D())).isEqualTo(expectedProperties);

        place.setCartesianPoint3D((CartesianPoint3D) converter.toEntityAttribute(expectedProperties));
        assertThat(place.getCartesianPoint3D()).isEqualTo(cartesianPoint3D);
    }

    @Test
    public void assertWgs84Point2DTypeGetsConverted() {
        String propertyName = "wgs84Point2D";
        CompositeAttributeConverter<Point> converter = classInfo.propertyField(propertyName).getCompositeConverter();

        Place place = new Place();
        Wgs84Point2D wgs84Point2D = Points.fromPolarCoordinates(1d, 2d);
        place.setWgs84Point2D(wgs84Point2D);

        Map<String, ? super Object> expectedProperties = new HashMap<>();
        expectedProperties.put(propertyName + ".longitude", 1d);
        expectedProperties.put(propertyName + ".latitude", 2d);
        expectedProperties.put(propertyName + ".srid", SpatialReferenceSystem.WGS_84.getSrid());
        expectedProperties.put(propertyName + ".crs", SpatialReferenceSystem.WGS_84.getCrs());

        assertThat(converter.toGraphProperties(place.getWgs84Point2D())).isEqualTo(expectedProperties);

        place.setWgs84Point2D((Wgs84Point2D) converter.toEntityAttribute(expectedProperties));
        assertThat(place.getWgs84Point2D()).isEqualTo(wgs84Point2D);
    }

    @Test
    public void assertWgs84Point3DTypeGetsConverted() {
        String propertyName = "wgs84Point3D";
        CompositeAttributeConverter<Point> converter = classInfo.propertyField(propertyName).getCompositeConverter();

        Place place = new Place();
        Wgs84Point3D wgs84Point3D = Points.fromPolarCoordinates(1d, 2d, 3d);
        place.setWgs84Point3D(wgs84Point3D);

        Map<String, ? super Object> expectedProperties = new HashMap<>();
        expectedProperties.put(propertyName + ".longitude", 1d);
        expectedProperties.put(propertyName + ".latitude", 2d);
        expectedProperties.put(propertyName + ".height", 3d);
        expectedProperties.put(propertyName + ".srid", SpatialReferenceSystem.WGS_84_3D.getSrid());
        expectedProperties.put(propertyName + ".crs", SpatialReferenceSystem.WGS_84_3D.getCrs());

        assertThat(converter.toGraphProperties(place.getWgs84Point3D())).isEqualTo(expectedProperties);

        place.setWgs84Point3D((Wgs84Point3D) converter.toEntityAttribute(expectedProperties));
        assertThat(place.getWgs84Point3D()).isEqualTo(wgs84Point3D);
    }

}
