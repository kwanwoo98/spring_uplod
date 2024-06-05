package org.zerock.spbt.repository.boardSearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardListAllDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);
    Page<Board> searchALL(String[] types, String keyword, Pageable pageable);

    // 댓글 표시해서 목록 출력하기
    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);

    // Page<BoardListReplyCountDTO> searchWithAll(String[] types, String keyword, Pageable pageable);
    Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);

}
