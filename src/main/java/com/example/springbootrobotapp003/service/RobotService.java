package com.example.springbootrobotapp003.service;

import com.example.springbootrobotapp003.domain.Robot;
import com.example.springbootrobotapp003.domain.RobotStep;

public interface RobotService {

    void stepForward (Robot robot);

    void turnRight (Robot robot);

    void turnLeft (Robot robot);

    void robotStepSave (Robot robot);

    Robot readRobot (Long id, int toX, int toY);

    Robot createRobot (Robot robot);

    Robot updateRobot (Long id, Robot robot);

    void deleteRobot (Long id);

}
