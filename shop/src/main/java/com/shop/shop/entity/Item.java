package com.shop.shop.entity;

import com.shop.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Entity // 반드시 기본키를 가져야하며, @Id를 붙여줌
@Table(name="item") // 어떤 테이블과 매핑될지 결정 (item 테이블과 매핑됨)
@Getter
@Setter
@ToString
public class Item {
    @Id
    @Column(name="item_id") // 매핑될 컬럼의 이름을 설정해준다
    /*
    auto : jpa 구현체가 자동으로 생성전략 결정
    identity : 기본키 생성을 데이터베이스에 위임(MySQL의 경우 auto_increament 사용)
    sequence : 데이터베이스 시퀀스 오브젝트를 사용한 기본키 생성. @SequenceGenerator 사용해서 시퀀스 등록 필요
    table : 키 생성용 테이블 사용. @TableGenerator 필요
     */
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성전략 : auto
    private Long id; // 상품 코드

    // nullable 설정을 이용해서 항상 값이 있어야하는 필드는 not null 설정하기. String 필드는 default 값으로 255가 설정되어 있으며, 각 필드마다 필요한 길이를 length 속성에 세팅하면 됨
    @Column(nullable = false, length = 50)
    private String itemNm; // 상품명

    @Column(name="price", nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고

    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemsSellStatus; // 상품 판매 상태

    private LocalDateTime regTime; // 등록시간
    private LocalDateTime updateTime; // 수정시간

}
