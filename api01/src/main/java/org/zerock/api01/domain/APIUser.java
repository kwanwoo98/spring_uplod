package org.zerock.api01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class APIUser {
  @Id
  private String mid;
  private String mpw;
  private String name;
  private String email;
  private boolean emailCheck;
  private boolean snsCheck;
  public void changePw(String mpw){
    this.mpw = mpw;
  }
  public void changeName(String name){
    this.name = name;
  }
  public void changeEmail(String email){
    this.email = email;
  }
  public void changeEmailCheck(boolean emailCheck){
    this.emailCheck = emailCheck;
  }
  public void changeSnsCheck(boolean snsCheck){
    this.snsCheck = snsCheck;
  }


}
