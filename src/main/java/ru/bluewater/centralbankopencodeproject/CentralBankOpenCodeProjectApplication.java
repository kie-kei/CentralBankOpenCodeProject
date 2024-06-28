package ru.bluewater.centralbankopencodeproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.respository.RootEntityRepository;

@SpringBootApplication
public class CentralBankOpenCodeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralBankOpenCodeProjectApplication.class, args);

    }

}
