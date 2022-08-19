package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.devteam.resume.dtos.CreateNewUserDto;
import ru.devteam.resume.entities.RegistrationToken;
import ru.devteam.resume.entities.Role;
import ru.devteam.resume.entities.User;
import ru.devteam.resume.enums.GenderType;
import ru.devteam.resume.repositories.RegistrationTokenRepository;
import ru.devteam.resume.repositories.RoleRepository;
import ru.devteam.resume.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final RegistrationTokenRepository registrationTokenRepository;
    private final EmailService emailService;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createNewUser(CreateNewUserDto createNewUserDto) {
        User user = new User();
        user.setPhoto(createNewUserDto.getPhoto());
        user.setFirstName(createNewUserDto.getFirstName());
        user.setLastName(createNewUserDto.getLastName());
        user.setGender(createNewUserDto.getGender());
        user.setEmail(createNewUserDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(createNewUserDto.getPassword()));
        userRepository.save(user);

        return;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь не найден", username)));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public String sighUp(String email, String password, String firstName, String lastName, String gender) {
        boolean userExist = userRepository.findByEmail(email).isPresent();
        if (userExist) {
            throw new IllegalStateException("Пользователь с таким email уже есть в системе");
        }
        var user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(GenderType.valueOf(gender));
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEnabled(false);
        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);

        String tokenUid = UUID.randomUUID().toString();
        registrationTokenRepository.save(new RegistrationToken(tokenUid, LocalDateTime.now().plusMinutes(15), user));

        emailService.sendVarificationLink(email, tokenUid);

        return tokenUid;
    }

    @Transactional
    public boolean confirmRegistration(String token) {
        var user = registrationTokenRepository.findUserByToken(LocalDateTime.now(), token);
        if (user.isEmpty()) {
            return false;
        }
        user.ifPresent(u -> u.setEnabled(true));
        return true;
    }
}
