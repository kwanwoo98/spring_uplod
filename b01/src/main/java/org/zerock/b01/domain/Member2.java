package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member2 {
    @Id
    private String member_id;
    @Column(length = 20 , nullable = false)
    private String member_pw;
    @Column(length = 20 , nullable = false)
    private String name;
    @Column(length = 20 , nullable = false)
    private String phone;
    @Column(length = 20 , nullable = false)
    private String email1;
    @Column(length = 20 , nullable = false)
    private String email2;

    @Column(length = 6 , nullable = false)
    private String gender;
    private boolean agree;

    @Builder.Default
    private LocalDateTime create_date = LocalDateTime.now();
}
