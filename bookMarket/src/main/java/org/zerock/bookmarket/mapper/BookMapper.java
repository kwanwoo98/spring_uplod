package org.zerock.bookmarket.mapper;

import org.zerock.bookmarket.domain.BookVO;

import java.util.List;

public interface BookMapper {
    List<BookVO> getAllBooks();
    void addBook(BookVO bookVO);
    BookVO selectOneBook(String id);
    void updateBook(BookVO bookVO);
    void deleteBook(String id);
}
