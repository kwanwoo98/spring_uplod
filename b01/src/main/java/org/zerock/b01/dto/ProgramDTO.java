package org.zerock.b01.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDTO {

    private Integer no;
    private String title;
    private String text;
    private String subtext;
    private String schedule;
    private String img;
}
