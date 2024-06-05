package com.shop.shop.controller;

import com.shop.shop.dto.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
// url에 "/thymeleaf"경로로 오는 요청을 ThymeleafExController 가 처리
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExController {
    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model){
        model.addAttribute("data","타임리프 예제");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemDetail("상품 상세 설명");
        itemDTO.setItemNm("테스트 상품 1");
        itemDTO.setPrice(10000);
        itemDTO.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto",itemDTO);
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model model){
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (int i = 1; i <= 10; i++){
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemDetail("상품 상세 설명"+i);
            itemDTO.setItemNm("테스트 상품"+i);
            itemDTO.setPrice(10000+i*100);
            itemDTO.setRegTime(LocalDateTime.now());

            itemDTOList.add(itemDTO);
        }
        model.addAttribute("itemDtoList", itemDTOList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model model) {
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (int i = 1; i <= 10; i++){
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemDetail("상품 상세 설명"+i);
            itemDTO.setItemNm("테스트 상품"+i);
            itemDTO.setPrice(1000*i);
            itemDTO.setRegTime(LocalDateTime.now());

            itemDTOList.add(itemDTO);
        }
        model.addAttribute("itemDtoList",itemDTOList);
        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping(value = "/ex05")
    public String thymeleafExample05(){
        return "thymeleafEx/thymeleafEx05";
    }
    @GetMapping(value = "/ex06")
    public String thymeleafExample06(String param1, String param2, Model model){
        model.addAttribute("param1",param1);
        model.addAttribute("param2",param2);
        return "thymeleafEx/thymeleafEx06";
    }
}
