package ru.bluewater.centralbankrestsrc.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.bluewater.centralbankrestapi.api.dto.request.RegistrationRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RegistrationResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.UsernameAlreadyExistsException;
import ru.bluewater.centralbankrestapi.api.type.AuthorityType;
import ru.bluewater.centralbankrestsrc.entity.AuthorityEntity;
import ru.bluewater.centralbankrestsrc.entity.UserEntity;
import ru.bluewater.centralbankrestsrc.respository.AuthorityRepository;
import ru.bluewater.centralbankrestsrc.respository.UserRepository;
import ru.bluewater.centralbankrestsrc.service.AuthenticationService;

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
            System.out.println("Unluck bratan");
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
                RegistrationRequestDTO.builder().username("admin").password("admin").build());

        UserEntity userEntity = userRepository.findByUsername(registrationResponseDTO.getUsername()).get();

        Set<AuthorityEntity> authorityEntitySet = userEntity.getAuthorities();
        authorityEntitySet.add(adminRoleEntity);

        userEntity.setAuthorities(authorityEntitySet);
        userRepository.save(userEntity);
    }
}
