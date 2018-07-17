package org.neo4j.ogm.types.point;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PointsTest {

    @Test
    public void createCartesianPoint2DFromProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        properties.put("y", 2d);

        Point point = Points.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(CartesianPoint2DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.CARTESIAN);
    }

    @Test
    public void createCartesianPoint3DFromProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        properties.put("y", 2d);
        properties.put("z", 3d);

        Point point = Points.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(CartesianPoint3DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.CARTESIAN_3D);
    }

    @Test
    public void createWgs84Point2DFromProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("longitude", 1d);
        properties.put("latitude", 2d);

        Point point = Points.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(Wgs84Point2DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.WGS_84);
    }

    @Test
    public void createWgs84Point2DFromCartesianPropertiesWithCrs() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        properties.put("y", 2d);
        properties.put("crs", SpatialReferenceSystem.WGS_84.getCrs());

        Point point = Points.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(Wgs84Point2DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.WGS_84);
    }

    @Test
    public void createWgs84Point2DFromCartesianPropertiesWithSrid() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        properties.put("y", 2d);
        properties.put("srid", SpatialReferenceSystem.WGS_84.getSrid());

        Point point = Points.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(Wgs84Point2DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.WGS_84);
    }

    @Test
    public void createWgs84Point3DFromProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("longitude", 1d);
        properties.put("latitude", 2d);
        properties.put("height", 3d);

        Point point = Points.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(Wgs84Point3DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.WGS_84_3D);
    }

    @Test
    public void createWgs84Point3DFromCartesianPropertiesWithCrs() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        properties.put("y", 2d);
        properties.put("z", 3d);
        properties.put("crs", SpatialReferenceSystem.WGS_84_3D.getCrs());

        Point point = Points.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(Wgs84Point3DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.WGS_84_3D);
    }

    @Test
    public void createWgs84Point3DFromCartesianPropertiesWithSrid() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        properties.put("y", 2d);
        properties.put("z", 3d);
        properties.put("srid", SpatialReferenceSystem.WGS_84_3D.getSrid());

        Point point = Points.fromProperties(properties);

        assertThat(point).isNotNull();
        assertThat(point).isInstanceOf(Wgs84Point3DImpl.class);
        assertThat(point.getSpatialReferenceSystem()).isEqualTo(SpatialReferenceSystem.WGS_84_3D);
    }

    @Test
    public void returnNullIfPropertiesNotFound() {
        Map<String, Object> properties = new HashMap<>();

        Point point = Points.fromProperties(properties);

        assertThat(point).isNull();
    }

    @Test
    public void returnNullIfPropertiesPartial() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("x", 1d);
        Point point = Points.fromProperties(properties);

        assertThat(point).isNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfValuesAreAmbiguous() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("longitude", 1d);
        properties.put("latitude", 2d);
        properties.put("x", 3d);
        properties.put("y", 4d);

        Point point = Points.fromProperties(properties);

        assertThat(point).isNull();
    }
}
