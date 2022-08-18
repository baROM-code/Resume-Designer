package  ru.devteam.resume.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.exceptions.AppError;
import ru.devteam.resume.services.UserService;
import ru.devteam.resume.utils.JwtTokenUtil;
import ru.geekbrains.march.market.dtos.JwtRequest;
import ru.geekbrains.march.market.dtos.JwtResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError("CHECK_TOKEN_ERROR", "Некорректный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        Long userId = userService.findByEmail(authRequest.getUsername()).get().getId();
        String token = jwtTokenUtil.generateToken(userDetails, userId);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password,
                               @RequestParam String firstname, @RequestParam String lastname, Model model){

        String token = userService.sighUp(username, password, firstname, lastname);
        model.addAttribute("token", token);

        return "register-confirm";
    }

    @GetMapping("/register/confirm")
    public String registerConfirm(@RequestParam String token){
        if(userService.confirmRegistration(token)){
            return "register-complete";
        }
        return "redirect:/";
    }

}
