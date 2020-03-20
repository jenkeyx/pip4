package Classes.Beans;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class Dot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "jpaSequence",sequenceName = "JPA_SEQUENCE1",allocationSize = 1,initialValue = 1)
    private Integer id;
    @Column(name = "x")
    double x;
    @Column(name = "y")
    double y;
    @Column(name = "r")
    double r;
    @Column(name = "is_hit")
    boolean isHit;
    @Column(name = "owner")
    String owner;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return isHit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dot dot = (Dot) o;
        return Double.compare(dot.x, x) == 0 &&
                Double.compare(dot.y, y) == 0 &&
                Double.compare(dot.r, r) == 0 &&
                isHit == dot.isHit &&
                owner.equals(dot.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, isHit, owner);
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
//todo замутить БД