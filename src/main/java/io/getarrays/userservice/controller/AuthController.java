package io.getarrays.userservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.payload.LoginPayload;
import io.getarrays.userservice.payload.LoginResponse;
import io.getarrays.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity auth(@RequestBody LoginPayload loginPayload) {
        User user = userService.getUser(loginPayload.getUsername());

        if (null != user && passwordEncoder.matches(loginPayload.getPassword(), user.getPassword())) {
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            String access_token = String.valueOf(JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer("Aarya Group of Company")
                    .withClaim("username", user.getUsername())
                    .sign(algorithm));
            return ResponseEntity.ok(new LoginResponse(access_token));
        }

        return ResponseEntity.badRequest().build();
    }

}
