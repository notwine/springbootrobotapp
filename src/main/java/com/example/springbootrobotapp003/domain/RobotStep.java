package com.example.springbootrobotapp003.domain;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class RobotStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected int x;
    protected int y;
    @Enumerated(EnumType.STRING)
    protected RobotDirection robotDirection;
    protected String robotName;

//    @Transient
//    private boolean isNew;

    public RobotStep() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public RobotDirection getRobotDirection() {
        return robotDirection;
    }

    public void setRobotDirection(RobotDirection robotDirection) {
        this.robotDirection = robotDirection;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

//    @Override
//    public boolean isNew() {
//        return isNew;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RobotStep)) return false;
        RobotStep robotStep = (RobotStep) o;
        return x == robotStep.x &&
                y == robotStep.y &&
                robotDirection == robotStep.robotDirection &&
                robotName.equals(robotStep.robotName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, robotDirection, robotName);
    }

}
