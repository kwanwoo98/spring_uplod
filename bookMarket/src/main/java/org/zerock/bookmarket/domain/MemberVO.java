package org.zerock.bookmarket.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {
    private String memberID;
    private String memberPW;
    private String memberName;
    private String phone;
    private String address;
    private String email;
    private LocalDate createDate;
}
