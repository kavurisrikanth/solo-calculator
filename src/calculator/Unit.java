package calculator;

public class Unit {
    private int mass, length, time;

    public Unit() {
        mass = 0;
        length = 0;
        time = 0;
    }

    public Unit(int mass, int length, int time) {
        this.mass = mass;
        this.length = length;
        this.time = time;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
