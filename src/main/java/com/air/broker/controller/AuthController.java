package com.air.broker.controller;

import com.air.broker.config.JwtTokenUtil;
import com.air.broker.model.JwtResponse;
import com.air.broker.model.Role;
import com.air.broker.model.User;
import com.air.broker.payload.LoginDto;
import com.air.broker.payload.SignUpDto;
import com.air.broker.repositary.RoleRepository;
import com.air.broker.repositary.UserRepository;
import com.air.broker.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginDto loginDto) throws Exception {
        try {
            authenticate(loginDto.getUsernameOrEmail(), loginDto.getPassword());
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));

//        SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(loginDto.getUsernameOrEmail());

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
//        return new ResponseEntity<>("User signin successfully", HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        // User name already taken
        if(userRepository.existsByUsername(signUpDto.getUser_name())){
            return new ResponseEntity<>("UserName Already Taken!!", HttpStatus.BAD_REQUEST);
        }
        // Email already taken
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return  new ResponseEntity<>("Email Already Taken!!", HttpStatus.BAD_REQUEST);
        }
        //Create user
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUser_name());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        return new ResponseEntity<>("Register successfully", HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
