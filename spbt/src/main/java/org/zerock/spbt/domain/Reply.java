package org.zerock.spbt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
// 댓글 게시글 조회시 인덱시 이용해서 성능을 개선함
@Table(name = "Reply" , indexes = {@Index(name = "idx_reply_board_bno", columnList = "board_bno")})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity{

    // 댓글번호, 자동 증가(오토 인크리먼트)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    //  if (Reply : Many) To <- (Board : One) ---- 단방향
    // ( 양방향이 된다 )
    // 장점 - 서로간 참조 가능 / 단점 - 구조가 복잡해짐

    // (Reply : Many) -> To (Board : One) : 단방향 참조
    // 장점 : 설정이 쉽고 간단함
    // 단점 : 서로간 참조가 어려움 >> 조인 설정을 이용하면 해소가 가능하다
    // FetchType.LAZY : DB에 연결을 즉시x 늦게 하겠다
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;

    private String replyer;

    // 댓글 수정시 메서드 이용해서 댓글의 내용만 변경하기
    public void changeText(String text){
        this.replyText = text;
    }
}
