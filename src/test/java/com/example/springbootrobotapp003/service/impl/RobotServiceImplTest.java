package com.example.springbootrobotapp003.service.impl;

import com.example.springbootrobotapp003.domain.Robot;
import com.example.springbootrobotapp003.domain.RobotDirection;
import com.example.springbootrobotapp003.repository.RobotRepository;
import com.example.springbootrobotapp003.repository.RobotStepRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
class RobotServiceImplTest {

    @Autowired
    private RobotServiceImpl robotServiceImpl;

    @MockBean
    private RobotRepository robotRepository;

    @MockBean
    private RobotStepRepository robotStepRepository;

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void readRobot() {

        int toX = 0;
        int toY = 0;
        Long robotId = 1L;

        Robot robot = new Robot();
        robot.setId(1L);
        robot.setX(2);
        robot.setY(3);
        robot.setRobotDirection(RobotDirection.UP);
        robot.setRobotName("RobotName_readRobotTest");
//        robotRepository.save(robot);

        doReturn(Optional.of(robot)).when(robotRepository).findById(robotId);
        Optional<Robot> robotOptional = Optional.ofNullable(robotServiceImpl.readRobot(robotId, toX, toY));
        Assertions.assertSame(robotOptional.get(), robot, "The robot cant be returned");

    }

    @Test
    void createRobot() {

        Robot robot = new Robot();
        robot.setX(2);
        robot.setY(3);
        robot.setRobotDirection(RobotDirection.UP);
        robot.setRobotName("RobotName_createRobotTest");

        Optional<Robot> createdRobot = Optional.ofNullable(robotServiceImpl.createRobot(robot));
        Assertions.assertEquals(robot.getX(), createdRobot.get().getX());
        Assertions.assertEquals(robot.getY(), createdRobot.get().getY());
        Assertions.assertEquals(robot.getRobotDirection(), createdRobot.get().getRobotDirection());
        Assertions.assertEquals(robot.getRobotName(), createdRobot.get().getRobotName());

    }

    @Test
    void updateRobot() {

        Long robotId = 1L;

        Robot robotForSave = new Robot();
        robotForSave.setId(robotId);
        robotForSave.setX(2);
        robotForSave.setY(3);
        robotForSave.setRobotDirection(RobotDirection.UP);
        robotForSave.setRobotName("RobotName_updateRobotTest");
//        robotRepository.save(robotForSave);
        doReturn(Optional.of(robotForSave)).when(robotRepository).findById(robotId);

        Robot robotForUpdate = new Robot();
        robotForUpdate.setId(robotId);
        robotForUpdate.setX(4);
        robotForUpdate.setY(5);
        robotForUpdate.setRobotDirection(RobotDirection.DOWN);
        robotForUpdate.setRobotName("updateRobotTest");

        Optional<Robot> updatedRobot = Optional.ofNullable(robotServiceImpl.updateRobot(robotId, robotForUpdate));
        Assertions.assertEquals(robotForSave, updatedRobot.get());

    }

    @Test
    void deleteRobot() {

        Robot robot = new Robot();
        robot.setId(1L);
        robot.setX(2);
        robot.setY(3);
        robot.setRobotDirection(RobotDirection.UP);
        robot.setRobotName("RobotName_deleteRobotTest");

        doReturn(Optional.of(robot)).when(robotRepository).findById(1L);
        robotServiceImpl.deleteRobot(1L);

    }

    @Test
    void stepForward() {

        Robot robot = new Robot();
        robot.setId(1L);
        robot.setX(2);
        robot.setY(3);
        robot.setRobotDirection(RobotDirection.RIGHT);
        robot.setRobotName("stepForwardRobotTest");
        doReturn(Optional.of(robot)).when(robotRepository).findById(1L);

        robotServiceImpl.stepForward(robot);

        Assertions.assertEquals(3, robot.getX());

    }

    @Test
    void turnRight() {

        Robot robot = new Robot();
        robot.setId(1L);
        robot.setX(2);
        robot.setY(3);
        robot.setRobotDirection(RobotDirection.RIGHT);
        robot.setRobotName("turnRightRobotTest");
        doReturn(Optional.of(robot)).when(robotRepository).findById(1L);

        robotServiceImpl.turnRight(robot);

        Assertions.assertEquals(RobotDirection.DOWN, robot.getRobotDirection());

    }

    @Test
    void turnLeft() {

        Robot robot = new Robot();
        robot.setId(1L);
        robot.setX(2);
        robot.setY(3);
        robot.setRobotDirection(RobotDirection.RIGHT);
        robot.setRobotName("turnLeftRobotTest");
        doReturn(Optional.of(robot)).when(robotRepository).findById(1L);

        robotServiceImpl.turnLeft(robot);

        Assertions.assertEquals(RobotDirection.UP, robot.getRobotDirection());

    }

    @Test
    void robotStepSave() {

        Robot robot = new Robot();
        robot.setId(1L);
        robot.setX(2);
        robot.setY(3);
        robot.setRobotDirection(RobotDirection.UP);
        robot.setRobotName("RobotName_robotStepSaveTest");

        doReturn(Optional.of(robot)).when(robotRepository).findById(1L);
        robotServiceImpl.robotStepSave(robot);

    }
}