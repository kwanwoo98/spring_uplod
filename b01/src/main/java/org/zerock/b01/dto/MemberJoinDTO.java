package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDTO {
    private String mid;
    private String mpw;
    private String name;
    private String email;
    private String addr;
    private boolean del;
    private boolean social;
}
