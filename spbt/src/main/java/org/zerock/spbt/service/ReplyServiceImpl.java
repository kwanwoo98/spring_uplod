package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.repository.ReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> replyOptional = replyRepository.findById(rno);
        // optinal 타입을 entity class 타입으로 변환
        Reply reply = replyOptional.orElseThrow();
        // entity 클래스타입 > DTO 전달용으로 사용하는 타입으로 변환
        return modelMapper.map(reply, ReplyDTO.class);
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getRno());
        Reply reply = replyOptional.orElseThrow();
        reply.changeText(replyDTO.getReplyText()); // 댓글의 내용만 수정가능
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        // PageRequestDTO : 화면에서 백엔드로 보내는 페이징 정보가 들어있음. 현재페이지나 크기 등
        // 페이징 하기위한 준비물 정보
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0? 0 : pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(), Sort.by("rno").ascending());

        // 페이징 처리가 된 댓글의 목록들 조회
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

        // 스트림(병렬처리) >> 여러개의 댓글 내용의 타입을 entity에서 DTO타입으로 변환해서 다시 List로 담기
        List<ReplyDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList());

        // 서버에서 페이징 처리 후 화면으로 전달해주기 > PageResponseDTO 로 응답
        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
