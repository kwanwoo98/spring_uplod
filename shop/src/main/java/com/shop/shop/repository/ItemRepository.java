package com.shop.shop.repository;

import com.shop.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
QuerydslPredicateExecutor 인터페이스 정의 메소드
1) long count(Predicate) :  조건에 맞는 데이터의 총 개수 반환
2) boolean exists(Predicate) : 조건에 맞는 데이터 존재 여부 반환
3) Iterable findAll(Predicate) : 조건에 맞는 모든 데이터 반환
4) Page<T> fineAll(Predicate, Pageable) : 조건에 맞는 페이지 데이터 반환
5) Iterable findAll(Predicate, Sort) : 조건에 맞는 정렬된 데이터 반환
6) T findOne(Predicate) : 조건에 맞는 데이터 1개 반환
 */
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByItemNm(String itemNm);
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
    List<Item> findByPriceLessThan(Integer price);
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);
}
