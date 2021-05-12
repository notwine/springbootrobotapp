package com.example.springbootrobotapp003.service.impl;

import com.example.springbootrobotapp003.domain.Robot;
import com.example.springbootrobotapp003.domain.RobotDirection;
import com.example.springbootrobotapp003.domain.RobotStep;
import com.example.springbootrobotapp003.repository.NoRobotException;
import com.example.springbootrobotapp003.repository.RobotRepository;
import com.example.springbootrobotapp003.repository.RobotStepRepository;
import com.example.springbootrobotapp003.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class RobotServiceImpl implements RobotService {

    private RobotRepository robotRepository;
    private RobotStepRepository robotStepRepository;

    @Autowired
    private void setRobotRepository (RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }

    @Autowired
    private void setRobotStepRepository (RobotStepRepository robotStepRepository) {
        this.robotStepRepository = robotStepRepository;
    }

    @Override
    public Robot readRobot(Long id, int toX, int toY) {
        Robot robot = robotRepository.findById(id).orElseThrow(() -> new NoRobotException());
        honorSquare(robot);
        moveRobot(robot, toX, toY);
        return robot;
    }

    @Override
    public Robot createRobot(Robot robot) {
        Robot createdRobot = new Robot();
        createdRobot.setX(robot.getX());
        createdRobot.setY(robot.getY());
        createdRobot.setRobotDirection(robot.getRobotDirection());
        createdRobot.setRobotName(robot.getRobotName());
        robotRepository.save(createdRobot);
        return createdRobot;
    }

    @Override
    public Robot updateRobot(Long id, Robot robot) {
        Robot updatedRobot = robotRepository.findById(id).orElseThrow(() -> new NoRobotException());
        updatedRobot.setX(robot.getX());
        updatedRobot.setY(robot.getY());
        updatedRobot.setRobotDirection(robot.getRobotDirection());
        updatedRobot.setRobotName(robot.getRobotName());
        robotRepository.save(updatedRobot);
        return updatedRobot;
    }

    @Override
    public void deleteRobot(Long id) {
        robotRepository.findById(id).ifPresent(Robot -> robotRepository.delete(Robot));
    }

    private void honorSquare (Robot robot) {
        int squareSize = 3;
        robotRepository.save(robot);
        robotStepSave(robot);
        System.out.println("-> " + robot.getRobotName() + " makes a honor square");
        for (int i = 0; i < 4; i++) {
            System.out.println(robot.getRobotName () + " (" + robot.getX() + ";" + robot.getY()
                    + "). Direction is " + robot.getRobotDirection());
            for (int k = 0; k < squareSize; k++) {
                stepForward(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " (" + robot.getX() + ";" + robot.getY() + ")");
            }
            turnRight(robot);
            robotRepository.save(robot);
            robotStepSave(robot);
        }
        System.out.println("-> " + robot.getRobotName() + " finished the honor square");
    }

    private void moveRobot(Robot robot, int toX, int toY) {
        System.out.println("-> " + robot.getRobotName () + " (" + robot.getX() + ";" + robot.getY()
                + "). Direction is " + robot.getRobotDirection() + ". Goes to (" + toX + ";" + toY + ")");
        if (robot.getX() < toX) {
            while (robot.getRobotDirection() != RobotDirection.RIGHT) {
                turnRight(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " direction is " + robot.getRobotDirection());
            }
            while (robot.getX() < toX) {
                stepForward(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " (" + robot.getX() + ";" + robot.getY() + ")");
            }
        }
        if (robot.getX() > toX) {
            while (robot.getRobotDirection() != RobotDirection.LEFT) {
                turnLeft(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " direction is " + robot.getRobotDirection());
            }
            while (robot.getX() > toX) {
                stepForward(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " (" + robot.getX() + ";" + robot.getY() + ")");
            }
        }
        if (robot.getY() < toY) {
            while (robot.getRobotDirection() != RobotDirection.UP) {
                turnRight(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " direction is " + robot.getRobotDirection());
            }
            while (robot.getY() < toY) {
                stepForward(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " (" + robot.getX() + ";" + robot.getY() + ")");
            }
        }
        if (robot.getY() > toY) {
            while (robot.getRobotDirection() != RobotDirection.DOWN) {
                turnLeft(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " direction is " + robot.getRobotDirection());
            }
            while (robot.getY() > toY) {
                stepForward(robot);
                robotRepository.save(robot);
                robotStepSave(robot);
                System.out.println(robot.getRobotName () + " (" + robot.getX() + ";" + robot.getY() + ")");
            }
        }
        System.out.println("-> Mission complete");
    }

    @Override
    public void stepForward(Robot robot) {
        switch (robot.getRobotDirection()) {
            case RIGHT:
                robot.setX(robot.getX() + 1);
                break;
            case LEFT:
                robot.setX(robot.getX() - 1);
                break;
            case UP:
                robot.setY(robot.getY() + 1);
                break;
            default:
                robot.setY(robot.getY() - 1);
                break;
        }
    }

    @Override
    public void turnRight(Robot robot) {
        switch (robot.getRobotDirection()) {
            case UP:
                robot.setRobotDirection(RobotDirection.RIGHT);
                break;
            case RIGHT:
                robot.setRobotDirection(RobotDirection.DOWN);
                break;
            case DOWN:
                robot.setRobotDirection(RobotDirection.LEFT);
                break;
            default:
                robot.setRobotDirection(RobotDirection.UP);
                break;
        }
    }

    @Override
    public void turnLeft(Robot robot) {
        switch (robot.getRobotDirection()) {
            case UP:
                robot.setRobotDirection(RobotDirection.LEFT);
                break;
            case LEFT:
                robot.setRobotDirection(RobotDirection.DOWN);
                break;
            case DOWN:
                robot.setRobotDirection(RobotDirection.RIGHT);
                break;
            default:
                robot.setRobotDirection(RobotDirection.UP);
                break;
        }
    }

    @Override
    public void robotStepSave(Robot robot) {
        RobotStep robotStep = new RobotStep();
        robotStep.setX(robot.getX());
        robotStep.setY(robot.getY());
        robotStep.setRobotDirection(robot.getRobotDirection());
        robotStep.setRobotName(robot.getRobotName());
        robotStepRepository.save(robotStep);
    }

}
