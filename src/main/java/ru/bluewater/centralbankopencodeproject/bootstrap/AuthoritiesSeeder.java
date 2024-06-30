package ru.bluewater.centralbankopencodeproject.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.bluewater.centralbankopencodeproject.api.dto.request.RegistrationRequestDTO;
import ru.bluewater.centralbankopencodeproject.api.dto.response.RegistrationResponseDTO;
import ru.bluewater.centralbankopencodeproject.api.exception.UsernameAlreadyExistsException;
import ru.bluewater.centralbankopencodeproject.api.type.AuthorityType;
import ru.bluewater.centralbankopencodeproject.entity.AuthorityEntity;
import ru.bluewater.centralbankopencodeproject.entity.UserEntity;
import ru.bluewater.centralbankopencodeproject.respository.AuthorityRepository;
import ru.bluewater.centralbankopencodeproject.respository.UserRepository;
import ru.bluewater.centralbankopencodeproject.service.AuthenticationService;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Component
public class AuthoritiesSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    @Autowired
    public AuthoritiesSeeder(AuthorityRepository authorityRepository, UserRepository userRepository, AuthenticationService authenticationService) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadAuthorities();

        try {
            this.loadSuperAdminUser();
        } catch (UsernameAlreadyExistsException e) {
            System.out.println("Unluck bratan");;
        }
    }

    private void loadAuthorities() {
        Arrays.stream(AuthorityType.values()).forEach(authorityName -> {
            Optional<AuthorityEntity> authority = authorityRepository.findByAuthority(authorityName.toString());

            if (authority.isEmpty()) {
                authorityRepository.save(AuthorityEntity.builder().authority(authorityName.toString()).build());
            }
        });
    }

    private void loadSuperAdminUser() throws UsernameAlreadyExistsException {
        AuthorityEntity adminRoleEntity;
        if (authorityRepository.findByAuthority("ADMIN").isEmpty()) {
            adminRoleEntity = authorityRepository.save(AuthorityEntity.builder().authority("ADMIN").build());
        } else {
            adminRoleEntity = authorityRepository.findByAuthority("ADMIN").get();
        }

        RegistrationResponseDTO registrationResponseDTO = authenticationService.registerUser(
                new RegistrationRequestDTO("admin", "admin"));

        UserEntity userEntity = userRepository.findByUsername(registrationResponseDTO.getUsername()).get();

        Set<AuthorityEntity> authorityEntitySet = userEntity.getAuthorities();
        authorityEntitySet.add(adminRoleEntity);

        userEntity.setAuthorities(authorityEntitySet);
        userRepository.save(userEntity);
    }
}
