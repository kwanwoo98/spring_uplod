package org.zerock.spbt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO2 {
    private String member_id;
    private String member_pw;
    private String name;
    private String phone;
    private String email1;
    private String email2;
    private String gender;
    private boolean agree;
    private LocalDate create_date;

}
