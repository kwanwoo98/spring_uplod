package org.zerock.api01.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class APIUserDTO extends User {
  private String mid;
  private String mpw;
  private String name;
  private String email;
  private boolean emailCheck;
  private boolean snsCheck;



  // APIUserDTO 생성자 : UserDetails에 아이디, 패스워드, 권한을 설정하기 위한 생성자
  public APIUserDTO(String username, String password,String name,String email, boolean emailCheck, boolean snsCheck, Collection<GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.mid = username;
    this.mpw = password;
    this.name = name;
    this.email = email;
    this.emailCheck = emailCheck;
    this.snsCheck = snsCheck;

  }

}
