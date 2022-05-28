package com.fazliddin.fullyme.common;

import com.fazliddin.fullyme.entity.Role;
import com.fazliddin.fullyme.entity.User;
import com.fazliddin.fullyme.enums.RoleTypeEnum;
import com.fazliddin.fullyme.repository.RoleRepository;
import com.fazliddin.fullyme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")) {

            Role roleUser = new Role();
            roleUser.setName("User");
            roleUser.setRoleType(RoleTypeEnum.USER);
            roleUser.setDescription("oddiy user foydalanuvchi");
            roleRepository.save(roleUser);

            Role role = new Role();
            role.setName("Admin");
            role.setRoleType(RoleTypeEnum.SYSTEM_ADMIN);
            role.setDescription("eng katta adminlarni admini bo'ladi, adminlarni shepi!!!");
            Role savedRoleAdmin = roleRepository.save(role);


            User user = new User();
            user.setFirstName("Fazliddin");
            user.setLastName("Xamdamov");
            user.setUsername("+998991171148");
            user.setPassword(passwordEncoder.encode("Fazliddin"));
            user.setRole(savedRoleAdmin);
            userRepo.save(user);
        }
    }
}
