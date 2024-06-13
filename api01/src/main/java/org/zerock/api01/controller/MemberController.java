package org.zerock.api01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.dto.APIUserDTO;
import org.zerock.api01.dto.JoinDTO;
import org.zerock.api01.dto.TodoDTO;
import org.zerock.api01.service.UserService;
import org.zerock.api01.service.UserServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/api/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final UserService userService;

    @CrossOrigin
    @PostMapping("/join")
    public Map<String,String> register(@RequestBody JoinDTO joinDTO){
        log.info(joinDTO);
        String mid = userService.register(joinDTO);
        return Map.of("mid",mid);
    }

    @PostMapping("/modify")
    public Map<String, String> modify(@RequestBody JoinDTO joinDTO) {
        log.info("Received update request: " + joinDTO);
        userService.update(joinDTO);
        return Map.of("mid", joinDTO.getMid());
    }


    @DeleteMapping("/{mid}")
    public Map<String, String> delete(@PathVariable("mid") String mid) {
        userService.remove(mid);
        return Map.of("result", "success");
    }


    @GetMapping("/{mid}")
    public JoinDTO get(@PathVariable("mid") String mid){
        log.info(mid);
        return userService.read(mid);
    }

}
