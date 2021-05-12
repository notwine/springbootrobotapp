package com.example.springbootrobotapp003.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected int x;
    protected int y;
    @Enumerated(EnumType.STRING)
    protected RobotDirection robotDirection;
    protected String robotName;

    public Robot() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Robot)) return false;
        Robot robot = (Robot) o;
        return x == robot.x &&
                y == robot.y &&
                id.equals(robot.id) &&
                robotDirection == robot.robotDirection &&
                robotName.equals(robot.robotName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, robotDirection, robotName);
    }

}
