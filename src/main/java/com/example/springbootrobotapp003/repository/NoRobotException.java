package com.example.springbootrobotapp003.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRobotException extends EntityNotFoundException {
}
