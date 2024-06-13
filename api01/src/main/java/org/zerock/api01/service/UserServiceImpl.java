package org.zerock.api01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.api01.domain.APIUser;

import org.zerock.api01.dto.*;
import org.zerock.api01.repository.APIUserRepository;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final APIUserRepository apiUserRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(JoinDTO joinDTO) {
        joinDTO.setMpw(passwordEncoder.encode(joinDTO.getMpw()));
        APIUser user = modelMapper.map(joinDTO, APIUser.class);
        String mid = apiUserRepository.save(user).getMid();
        return mid;
    }

    @Override
    public JoinDTO read(String mid) {
        Optional<APIUser> result = apiUserRepository.findById(mid);
        APIUser user = result.orElseThrow();
        return modelMapper.map(user,JoinDTO.class);
    }


    @Override
    public void update(JoinDTO joinDTO) {
        log.info("Updating user: " + joinDTO);
        Optional<APIUser> result = apiUserRepository.findById(joinDTO.getMid());
        if (result.isPresent()) {
            APIUser user = result.get();
            user.changePw(passwordEncoder.encode(joinDTO.getMpw()));
            user.changeName(joinDTO.getName());
            user.changeEmail(joinDTO.getEmail());
            user.changeEmailCheck(joinDTO.isEmailCheck());
            user.changeSnsCheck(joinDTO.isSnsCheck());
            apiUserRepository.save(user);
        } else {
            log.warn("User not found with ID: " + joinDTO.getMid());
        }
    }


    @Override
    public void remove(String mid) {
        apiUserRepository.deleteById(mid);
    }

}
