package com.sth.userservice.service;

import com.sth.userservice.model.dto.UserDTO;
import com.sth.userservice.model.entity.User;
import com.sth.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    // 유저 전체 조회
    public List<UserDTO> listUser() {
        List<User> list = new ArrayList<>();
        List<UserDTO> resultList = new ArrayList<>();

        list = userRepository.findAll();
        for(User user : list) {
            resultList.add(user.toDto());
        }

        return resultList;
    }

    // 유저 회원가입
    public UserDTO createUser(UserDTO userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        User user = userDto.toEntity();
        user.setEncryptedPwd(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);

        return user.toDto();
    }
    
    // 유저 디테일
    public UserDTO getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(email);

        UserDTO userDto = user.toDto();
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findById(username);
        User user;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPwd(),
                    true, true, true, true,
                    new ArrayList<>());
        }else {

            throw new UsernameNotFoundException(username + ": not found");
        }


    }
}