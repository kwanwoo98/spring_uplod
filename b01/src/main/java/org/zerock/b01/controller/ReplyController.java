package org.zerock.b01.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.service.ReplyService;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor // 의존성 주입 위함
public class ReplyController {
    private final ReplyService replyService;

    @Tag(name="Replies POST", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    // ResponseEntity : 데이터를 HTTP 상태코드와 같이 앞단에 전달하는역할
    // JSON 타입의 문자열로 전달시 사용
    // 서버에 도착하면 JSON > ReplyDTO 타입으로 자동변환( jackson 라이브러리가 컨버터역할을 해서 - 스프링부트 자동내장)
//    public ResponseEntity<Map<String, Long>> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException { // 오류 및 유효성 체크 등 오류 발생하면 오류 표시하게끔

    // 실제 데이터 넣는 서비스 추가하기
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {

        log.info(replyDTO);

        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();

//        resultMap.put("rno",1234L);
//        return ResponseEntity.ok(resultMap);

        Long rno = replyService.register(replyDTO);
        resultMap.put("rno", rno);
        return resultMap;
    }

    // 특정 게시물의 댓글 목록 조회
    @Tag(name = "Replies of Board" ,description = "Get방식으로 특정 게시물의 댓글 목록 조회하기")
    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);

        return responseDTO;
    }

    @Tag(name = "Read reply" ,description = "Get방식으로 특정 게시물의 특정 댓글 조회하기")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }

    @Tag(name = "delete reply", description = "delete 방식으로 특정 댓글 삭제")
    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);

        return resultMap;
    }

    @Tag(name="Modify reply", description = "put방식으로 댓글 수정하기")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setRno(rno); // 번호 일치
        replyService.modify(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }



}
