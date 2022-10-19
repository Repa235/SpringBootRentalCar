package com.example.springbootrentalcar.config;
import com.example.springbootrentalcar.mapper.RentMapper;
import com.example.springbootrentalcar.mapper.UserMapper;
import com.example.springbootrentalcar.mapper.VehicleMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserMapper userMapper(){
    return new UserMapper(modelMapper());
    }

    @Bean
    public VehicleMapper vehicleMapper(){
        return new VehicleMapper(modelMapper());
    }

    @Bean
    public RentMapper rentMapper(){
        return new RentMapper(modelMapper(), userMapper(), vehicleMapper());
    }
}