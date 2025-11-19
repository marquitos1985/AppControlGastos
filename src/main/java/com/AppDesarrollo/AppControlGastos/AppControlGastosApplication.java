package com.AppDesarrollo.AppControlGastos;

import com.AppDesarrollo.AppControlGastos.dtos.UserRequest;
import com.AppDesarrollo.AppControlGastos.dtos.UserResponse;
import com.AppDesarrollo.AppControlGastos.repositories.UserRepository;
import com.AppDesarrollo.AppControlGastos.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class AppControlGastosApplication {

/*
    @Autowired
    UserServiceImpl userService;


 */

	public static void main(String[] args) {
		SpringApplication.run(AppControlGastosApplication.class, args);
	}
/*
    @Override
    public void run(String... args) throws Exception {


        log.info("WELCOME---------------------------------");

        List<UserResponse> userResponseList = new ArrayList<>();
        UserResponse userResponse = null;


        UserRequest userRequest = new UserRequest("User", "uno", "user1985@example.com", "123456");
        userResponse = this.userService.create(userRequest);
        log.info(userResponse.toString());

        userRequest.setEmail("marquitos1985@hotmail.com");
        userResponse = this.userService.update(userRequest,6L);
        log.info(userResponse.toString());


        this.userService.deleteById(1L);



        userResponseList = this.userService.findAll();
        log.info(userResponseList.toString());


        log.info("FIN---------------------------------");



    }

 */
}
