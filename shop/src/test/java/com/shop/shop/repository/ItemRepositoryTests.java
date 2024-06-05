package com.shop.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.shop.constant.ItemSellStatus;
import com.shop.shop.entity.Item;
import com.shop.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ItemRepositoryTests {

    // 영속성 컨텍스트를 사용하기위해 EntityManager 빈 주입
    @PersistenceContext
    EntityManager em;

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemsSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    public void createItemList(){
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트상품"+i);
            item.setPrice(10000+i*100);
            item.setItemDetail("테스트상품 상세 설명"+i);
            item.setItemsSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100+i);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트상품1");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 OR테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList =
                itemRepository.findByItemNmOrItemDetail("테스트상품1","테스트상품 상세 설명5");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan테스트")
    public void findByPriceLessThanTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10500);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회테스트")
    public void findByPriceLessThanOrderByPriceDescTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10500);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트상품 상세 설명");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByItemDetailNativeQueryTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetailNative("테스트상품 상세 설명");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트 1")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em); // 쿼리 동적생성
        QItem qItem = QItem.item; // querydsl을 통해 쿼리를 생성하기위해  Qitem 객체이용
        JPAQuery<Item> query = queryFactory.selectFrom(qItem) // 자바코드지만 sql처럼 작성 가능
                .where(qItem.itemsSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"테스트상품 상세 설명"+"%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch(); // 쿼리 결과를 리스트로 반환. fetch() 실행시점에 쿼리문 실행

        /*
        JPAQuery 데이터 반환 메소드
        1) List<T> fetch() : 조회 결과 리스트 반환
        2) T fetchOne : 조회 대상이 1건인 경우 제네릭으로 지정한 타입 반환
        3) T fetchFirst() : 조회 대상 중 1건만 반환
        4) Long fetchCount() : 조회 대상 개수 반환
        5) QueryResult<T> fetchResults() : 조회한 리스트와 전체 개수를 포함한 QueryResult 반환
         */

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    public void createItemList2(){
        for(int i = 1; i <= 5; i++){
            Item item = new Item();
            item.setItemNm("테스트상품"+i);
            item.setPrice(10000+i*100);
            item.setItemDetail("테스트상품 상세 설명"+i);
            item.setItemsSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(150);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
        for(int i = 6; i <= 10; i++){
            Item item = new Item();
            item.setItemNm("테스트상품"+i);
            item.setPrice(10000+i*100);
            item.setItemDetail("테스트상품 상세 설명"+i);
            item.setItemsSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 Querydsl 조회 테스트2")
    public void queryDslTest2(){
        this.createItemList2();
        // 쿼리에 들어갈 조건을 만들어주는 빌더. Predicate 를 구현하고 있으며 메소드 체인형식으로 사용할 수 있다.
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10300;
        String itemSellStat = "SELL";

        // 필요한 상품 조회시 필요한 and조건 추가. 상품의 판매상태가 SELL일때만 booleanBuilder에 판매상태 조건을 동적으로 추가
        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
        booleanBuilder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemsSellStatus.eq(ItemSellStatus.SELL));
        }

        // 데이터를 페이징해서 조회하도록 PageRequest.of()를 이용해 Pageable 객체 생성. 첫번째 인자는 조회할 페이지의 번호, 두번재 인자는 한 페이지당 조회할 데이터의 개수
        Pageable pageable = PageRequest.of(0,5);
        // findAll()을 이용해 조건에 맞는 데이터를 Page 객체로 받아옴
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder,pageable);
        System.out.println("total elements : "+ itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem : resultItemList){
            System.out.println(resultItem.toString());
        }

    }

}
