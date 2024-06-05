package org.zerock.bookmarket.service;

import org.zerock.bookmarket.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks(BookDTO bookDTO);
    void addBook(BookDTO bookDTO);
    BookDTO getBook(String id);
    void deleteBook(String id);
    void modifyBook(BookDTO bookDTO);
}
