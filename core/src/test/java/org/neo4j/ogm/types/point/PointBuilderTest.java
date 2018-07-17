package org.neo4j.ogm.types.point;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PointBuilderTest {

    @Test
    public void createCartesianPoint2DFromProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        properties.put("y", 2d);

        Point point = PointBuilder.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(CartesianPoint2DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.CARTESIAN);
        assertThat(point.asProperties()).isEqualTo(properties);
    }

    @Test
    public void createCartesianPoint3DFromProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        properties.put("y", 2d);
        properties.put("z", 3d);

        Point point = PointBuilder.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(CartesianPoint3DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.CARTESIAN_3D);
        assertThat(point.asProperties()).isEqualTo(properties);
    }

    @Test
    public void createWgs84Point2DFromProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("longitude", 1d);
        properties.put("latitude", 2d);

        Point point = PointBuilder.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(Wgs84Point2DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.WGS_84);
        assertThat(point.asProperties()).isEqualTo(properties);
    }

    @Test
    public void createWgs84Point3DFromProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("longitude", 1d);
        properties.put("latitude", 2d);
        properties.put("height", 3d);

        Point point = PointBuilder.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(Wgs84Point3DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.WGS_84_3D);
        assertThat(point.asProperties()).isEqualTo(properties);
    }

    @Test
    public void returnNullIfPropertiesNotFound() {
        Map<String, Object> properties = new HashMap<>();

        Point point = PointBuilder.fromProperties(properties);

        assertThat(point).isNull();
    }

    @Test
    public void returnNullIfPropertiesPartial() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        Point point = PointBuilder.fromProperties(properties);

        assertThat(point).isNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfValuesAreAmbiguous() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("longitude", 1d);
        properties.put("latitude", 2d);
        properties.put("x", 3d);
        properties.put("y", 4d);

        Point point = PointBuilder.fromProperties(properties);

        assertThat(point).isNull();
    }
}
