package org.zerock.b01.repository.boardSearch;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.QBoard;
import org.zerock.b01.domain.QReply;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.BoardImageDTO;
import org.zerock.b01.dto.BoardListAllDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        //queryDSL 을 이용한 객체 설정
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        //JPQL을 사용한 WHERE 메서드로 조건식 추가
        query.where(board.title.contains("1"));
        //pageable 설정
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }
    @Override
    public Page<Board> searchALL(String[] types, String keyword, Pageable pageable) {
        //querydsl로 생성된 qboard 설정
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        // 검색 조건인 type와 키워드가 존재하는지 확인하는 if문
        if((types != null && types.length > 0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch(type) {
                    case "t":
                        // OR title LIKE "%keyword%" : SQL 작성
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        // OR content LIKE "%keyword%" : SQL 작성
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        // OR writer LIKE "%keyword%" : SQL 작성
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }// 실행할 쿼리문에 types, keyword 조건절 추가
            query.where(booleanBuilder);
        }
        // AND bno>0 : WHERE 쿼리 추가
        query.where(board.bno.gt(0L));
        // ORDER BY bno DESC limit 0,10 : 정렬 및 리미트 SQL 추가
        this.getQuerydsl().applyPagination(pageable, query);
        // SQL 실행
        List<Board> list = query.fetch();
        // count 관련 SQL 실행
        long count = query.fetchCount();
        // <> 아무거나 다넣어줄수있음.
        return new PageImpl<>(list, pageable,count);
    }

    @Override
    // 댓글의 개수와 함께 검색하기
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        // @Query안에 문자열, sql문법을 사용시 컴파일 체크가 안되기때문에 QueryDSL을 동적으로 사용하면 자바 문법 형식으로 데이터베이스타입으로 변환이 쉽다
        // QueryDSL의 QDomain 사용하기
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        // JPQL문법 사용할게
        JPQLQuery<Board> query = from(board);
        // 조인한다
        query.leftJoin(reply).on(reply.board.eq(board));

        query.groupBy(board);

        // 544p 코드 추가하기 ( 중복되어서 위에서 긁어옴 )
        if((types != null && types.length > 0) && keyword != null){
            // true,false 를 갖는다
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch(type) {
                    case "t":
                        // OR title LIKE "%keyword%" : SQL 작성
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        // OR content LIKE "%keyword%" : SQL 작성
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        // OR writer LIKE "%keyword%" : SQL 작성
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }// 실행할 쿼리문에 types, keyword 조건절 추가
            query.where(booleanBuilder);
        }
        // AND bno>0 : WHERE 쿼리 추가
        query.where(board.bno.gt(0L));

        // 추가부분
        // Projections를 이용해서 Entity 클래스를 자동으로 DTO타입으로 형변환
        JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(Projections.bean(BoardListReplyCountDTO.class, board.bno, board.title, board.writer,board.regDate, reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);
        List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();
        long count = dtoQuery.fetchCount();
        return new PageImpl<>(dtoList, pageable,count);
    }

//    @Override
//    public Page<BoardListReplyCountDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
//        QBoard board = QBoard.board;
//        QReply reply = QReply.reply;
//
//        JPQLQuery<Board> boardJPQLQuery = from(board);
//        boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));
//
//        getQuerydsl().applyPagination(pageable, boardJPQLQuery);
//        List<Board> boardList = boardJPQLQuery.fetch();
//
//        boardList.forEach(board1 -> {
//            System.out.println(board1.getBno());
//            System.out.println(board1.getImageSet());
//            System.out.println("----------------------------------");
//        });
//        return null;
//    }


    @Override
    public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> boardJPQLQuery = from(board);
        boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));

        if( (types != null && types.length > 0 ) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type : types){
                switch (type) {
                    case "t" :
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c" :
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w" :
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }// end for
            boardJPQLQuery.where(booleanBuilder);
        }

        boardJPQLQuery.groupBy(board);

        getQuerydsl().applyPagination(pageable,boardJPQLQuery);

        JPQLQuery<Tuple> tupleJPQLQuery = boardJPQLQuery.select(board,reply.countDistinct());

        List<Tuple> tupleList = tupleJPQLQuery.fetch();

        List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple -> {
            Board board1 = (Board) tuple.get(board);
            long replyCount = tuple.get(1,Long.class);

            BoardListAllDTO dto = BoardListAllDTO.builder()
                    .bno(board1.getBno())
                    .title(board1.getTitle())
                    .writer(board1.getWriter())
                    .regDate(board1.getRegDate())
                    .replyCount(replyCount)
                    .build();

            List<BoardImageDTO> imageDTOS = board1.getImageSet().stream().sorted()
                    .map(boardImage -> BoardImageDTO.builder()
                            .uuid(boardImage.getUuid())
                            .fileName(boardImage.getFileName())
                            .ord(boardImage.getOrd())
                            .build()
                    ).collect(Collectors.toList());

            dto.setBoardImages(imageDTOS);
            return dto;
        }).collect(Collectors.toList());

        long totalCount = boardJPQLQuery.fetchCount();

        return new PageImpl<>(dtoList,pageable,totalCount);
    }
}
