package org.neo4j.ogm.types.point;

class CartesianPoint3DImpl implements CartesianPoint3D {

    private final double x;
    private final double y;
    private final double z;

    CartesianPoint3DImpl(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }
}
