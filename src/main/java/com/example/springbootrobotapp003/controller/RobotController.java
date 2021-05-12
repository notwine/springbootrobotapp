package com.example.springbootrobotapp003.controller;

import com.example.springbootrobotapp003.domain.Robot;
import com.example.springbootrobotapp003.dto.RobotDTO;
import com.example.springbootrobotapp003.mapper.RobotMapper;
import com.example.springbootrobotapp003.service.impl.RobotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/robots")
public class RobotController {

    private RobotServiceImpl robotServiceImpl;

    @Autowired
    private void setRobotServiceImpl (RobotServiceImpl robotServiceImpl) {
        this.robotServiceImpl = robotServiceImpl;
    }

    @GetMapping("/{id}")
    public RobotDTO getRobot(@PathVariable Long id,
                             @RequestParam(value = "toX", required = true) int toX,
                             @RequestParam(value = "toY", required = true) int toY) {
        Robot robot = robotServiceImpl.readRobot(id, toX, toY);
        return RobotMapper.INSTANCE.toDTO(robot);
    }

    @PostMapping
    public RobotDTO postRobot(@RequestBody Robot robot) {
        Robot postedRobot = robotServiceImpl.createRobot(robot);
        return RobotMapper.INSTANCE.toDTO(postedRobot);
    }

    @PutMapping("/{id}")
    public RobotDTO putRobot(@PathVariable Long id, @RequestBody Robot robot) {
        Robot putedRobot = robotServiceImpl.updateRobot(id, robot);
        return RobotMapper.INSTANCE.toDTO(putedRobot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRobot(@PathVariable Long id) {
        robotServiceImpl.deleteRobot(id);
        return ResponseEntity.ok().body("Potracheno");
    }

}
