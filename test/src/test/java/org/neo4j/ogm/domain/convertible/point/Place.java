package org.neo4j.ogm.domain.convertible.point;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.types.point.CartesianPoint2D;
import org.neo4j.ogm.types.point.CartesianPoint3D;
import org.neo4j.ogm.types.point.Point;
import org.neo4j.ogm.types.point.Wgs84Point2D;
import org.neo4j.ogm.types.point.Wgs84Point3D;

@NodeEntity
public class Place {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Point simplePosition;

    private CartesianPoint2D cartesianPoint2D;

    private CartesianPoint3D cartesianPoint3D;

    private Wgs84Point2D wgs84Point2D;

    private Wgs84Point3D wgs84Point3D;

    public Place() {
    }

    public Place(String name) {
        this.name = name;
    }

    public Point getSimplePosition() {
        return simplePosition;
    }

    public void setSimplePosition(Point simplePosition) {
        this.simplePosition = simplePosition;
    }

    public CartesianPoint2D getCartesianPoint2D() {
        return cartesianPoint2D;
    }

    public void setCartesianPoint2D(CartesianPoint2D cartesianPoint2D) {
        this.cartesianPoint2D = cartesianPoint2D;
    }

    public CartesianPoint3D getCartesianPoint3D() {
        return cartesianPoint3D;
    }

    public void setCartesianPoint3D(CartesianPoint3D cartesianPoint3D) {
        this.cartesianPoint3D = cartesianPoint3D;
    }

    public Wgs84Point2D getWgs84Point2D() {
        return wgs84Point2D;
    }

    public void setWgs84Point2D(Wgs84Point2D wgs84Point2D) {
        this.wgs84Point2D = wgs84Point2D;
    }

    public Wgs84Point3D getWgs84Point3D() {
        return wgs84Point3D;
    }

    public void setWgs84Point3D(Wgs84Point3D wgs84Point3D) {
        this.wgs84Point3D = wgs84Point3D;
    }
}
