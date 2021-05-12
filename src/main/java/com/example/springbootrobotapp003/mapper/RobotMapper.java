package com.example.springbootrobotapp003.mapper;

import com.example.springbootrobotapp003.domain.Robot;
import com.example.springbootrobotapp003.dto.RobotDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RobotMapper {

    RobotMapper INSTANCE = Mappers.getMapper(RobotMapper.class);
    RobotDTO toDTO(Robot robot);

}
