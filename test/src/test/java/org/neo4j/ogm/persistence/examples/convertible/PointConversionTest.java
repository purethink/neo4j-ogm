package org.neo4j.ogm.persistence.examples.convertible;

import static org.assertj.core.api.Assertions.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.ogm.domain.convertible.point.Place;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.MultiDriverTestClass;
import org.neo4j.ogm.types.point.CartesianPoint2D;
import org.neo4j.ogm.types.point.CartesianPoint3D;
import org.neo4j.ogm.types.point.Points;
import org.neo4j.ogm.types.point.Wgs84Point2D;
import org.neo4j.ogm.types.point.Wgs84Point3D;

public class PointConversionTest extends MultiDriverTestClass {

    private Session session;

    @BeforeClass
    public static void oneTimeSetUp() {
        sessionFactory = new SessionFactory(driver, "org.neo4j.ogm.domain.convertible.point");
    }

    @Before
    public void init() {
        session = sessionFactory.openSession();
    }

    @After
    public void cleanUp() {
        session.purgeDatabase();
    }

    @Test
    public void convertSimplePointAsCartesianPoint2D() {
        Place place = new Place("MyPlace");
        CartesianPoint2D simplePosition = Points.fromCartesianCoordinates(1d, 2d);
        place.setSimplePosition(simplePosition);

        session.save(place);
        session.clear();

        Collection<Place> places = session.loadAll(Place.class);
        place = places.iterator().next();

        assertThat(place.getName()).isEqualTo("MyPlace");
        assertThat(place.getSimplePosition()).isInstanceOf(CartesianPoint2D.class);

        CartesianPoint2D cartesianPoint2D = (CartesianPoint2D) place.getSimplePosition();
        assertThat(cartesianPoint2D).isEqualTo(simplePosition);
    }

    @Test
    public void convertCartesianPoint2D() {
        Place place = new Place("MyPlace");
        CartesianPoint2D cartesianPoint2D = Points.fromCartesianCoordinates(1d, 2d);
        place.setCartesianPoint2D(cartesianPoint2D);

        session.save(place);
        session.clear();

        Collection<Place> places = session.loadAll(Place.class);
        place = places.iterator().next();
        assertThat(place.getName()).isEqualTo("MyPlace");
        assertThat(place.getCartesianPoint2D()).isEqualTo(cartesianPoint2D);
    }

    @Test
    public void convertCartesianPoint3D() {
        Place place = new Place("MyPlace");
        CartesianPoint3D cartesianPoint3D = Points.fromCartesianCoordinates(1d, 2d, 3d);
        place.setCartesianPoint3D(cartesianPoint3D);

        session.save(place);
        session.clear();

        Collection<Place> places = session.loadAll(Place.class);
        place = places.iterator().next();
        assertThat(place.getName()).isEqualTo("MyPlace");
        assertThat(place.getCartesianPoint3D()).isEqualTo(cartesianPoint3D);
    }

    @Test
    public void convertWgsPoint2D() {
        Place place = new Place("MyPlace");
        Wgs84Point2D wgs84Point2D = Points.fromPolarCoordinates(1d, 2d);
        place.setWgs84Point2D(wgs84Point2D);

        session.save(place);
        session.clear();

        Collection<Place> places = session.loadAll(Place.class);
        place = places.iterator().next();
        assertThat(place.getName()).isEqualTo("MyPlace");
        assertThat(place.getWgs84Point2D()).isEqualTo(wgs84Point2D);
    }

    @Test
    public void convertWgsPoint3D() {
        Place place = new Place("MyPlace");
        Wgs84Point3D wgs84Point3D = Points.fromPolarCoordinates(1d, 2d, 3d);
        place.setWgs84Point3D(wgs84Point3D);

        session.save(place);
        session.clear();

        Collection<Place> places = session.loadAll(Place.class);
        place = places.iterator().next();
        assertThat(place.getName()).isEqualTo("MyPlace");
        assertThat(place.getWgs84Point3D()).isEqualTo(wgs84Point3D);
    }

    @Test
    public void convertMultiplePointTypesInOneClass() {
        Place place = new Place("MyPlace");
        CartesianPoint2D simplePosition = Points.fromCartesianCoordinates(1d, 2d);
        CartesianPoint2D cartesianPoint2D = Points.fromCartesianCoordinates(1d, 2d);
        CartesianPoint3D cartesianPoint3D = Points.fromCartesianCoordinates(1d, 2d, 3d);
        Wgs84Point2D wgs84Point2D = Points.fromPolarCoordinates(1d, 2d);
        Wgs84Point3D wgs84Point3D = Points.fromPolarCoordinates(1d, 2d, 3d);

        place.setSimplePosition(simplePosition);
        place.setCartesianPoint2D(cartesianPoint2D);
        place.setCartesianPoint3D(cartesianPoint3D);
        place.setWgs84Point2D(wgs84Point2D);
        place.setWgs84Point3D(wgs84Point3D);

        session.save(place);
        session.clear();

        Collection<Place> places = session.loadAll(Place.class);
        place = places.iterator().next();
        assertThat(place.getName()).isEqualTo("MyPlace");
        assertThat(place.getSimplePosition()).isEqualTo(simplePosition);
        assertThat(place.getCartesianPoint2D()).isEqualTo(cartesianPoint2D);
        assertThat(place.getCartesianPoint3D()).isEqualTo(cartesianPoint3D);
        assertThat(place.getWgs84Point2D()).isEqualTo(wgs84Point2D);
        assertThat(place.getWgs84Point3D()).isEqualTo(wgs84Point3D);
    }
}
