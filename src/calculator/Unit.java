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

    public void sqrt() {
        mass /= 2;
        length /= 2;
        time /= 2;
    }
}
