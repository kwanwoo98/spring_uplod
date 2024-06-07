package org.zerock.api01.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.dto.*;

@Transactional
public interface UserService {
    String register(JoinDTO joinDTO);
    JoinDTO read(String mid);

    void update(JoinDTO joinDTO);
    void remove(String mid);
}
