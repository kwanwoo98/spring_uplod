package org.zerock.bookmarket.dto;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String id;
    private String name;
    private String description;
    private String category;
    private String author;
    private String publisher;
    private String releaseDate;
    private int pages;
    private int unitPrice;
    private int unitInStock;
    private String imgFileName;
    private LocalDateTime createDate;
}
