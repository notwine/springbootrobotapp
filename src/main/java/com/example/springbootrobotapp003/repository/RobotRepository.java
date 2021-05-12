package com.example.springbootrobotapp003.repository;

import com.example.springbootrobotapp003.domain.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {

}
