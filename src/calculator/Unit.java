package calculator;

public class Unit {
    private double mass, length, time;

    public Unit() {
        mass = 0;
        length = 0;
        time = 0;
    }

    public Unit(double mass, double length, double time) {
        this.mass = mass;
        this.length = length;
        this.time = time;
    }

    public Unit(Unit other) {
        this.mass = other.getMass();
        this.length = other.getLength();
        this.time = other.getTime();
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setAll(double m, double l, double t) {
        mass = m;
        length = l;
        time = t;
    }

    public boolean equals(Unit other) {
        return mass == other.getMass() && length == other.getLength() && time == other.getTime();
    }

    public void mpy(Unit other) {
        mass += other.getMass();
        length += other.getLength();
        time += other.getTime();
    }

    public void div(Unit other) {
        mass -= other.getMass();
        length -= other.getLength();
        time -= other.getTime();
    }

    private void root(int n) {
        mass /= n;
        length /= n;
        time /= n;
    }

    public void sqrt() {
        root(2);
    }

    @Override
    public String toString() {
        return mass + " " + length + " " + time + "";
    }

    public String toDebugString() {
        return "M: " + mass + ", L: " + length + ", T: " + time;
    }
}
