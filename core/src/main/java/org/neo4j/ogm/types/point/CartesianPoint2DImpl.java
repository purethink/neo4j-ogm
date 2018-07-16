package org.neo4j.ogm.types.point;

class CartesianPoint2DImpl implements CartesianPoint2D {

    private final double x;
    private final double y;

    CartesianPoint2DImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
