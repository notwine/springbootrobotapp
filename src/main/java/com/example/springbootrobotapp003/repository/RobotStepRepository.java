package com.example.springbootrobotapp003.repository;

import com.example.springbootrobotapp003.domain.RobotStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotStepRepository extends JpaRepository<RobotStep, Long> {

}
