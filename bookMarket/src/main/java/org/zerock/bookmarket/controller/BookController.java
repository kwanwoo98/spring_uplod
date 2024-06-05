package org.zerock.bookmarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.bookmarket.dto.BookDTO;
import org.zerock.bookmarket.service.BookService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/book")
@Log4j2
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public void getBooks(@Valid BookDTO bookDTO, BindingResult bindingResult, Model model) {
        log.info("Get books.........");
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getFieldError().getDefaultMessage());
        }
        log.info("Books: " + bookService.getBooks(bookDTO));
        model.addAttribute("books", bookService.getBooks(bookDTO));
    }

    @GetMapping("/book")
    public void getBook(String id, Model model) {
        log.info("Get book.........");
        model.addAttribute("book", bookService.getBook(id));
    }

    @GetMapping("/addBook")
    public void getAdd() {
        log.info("Get book add.........");
    }

    @PostMapping("/addBook")
    public String postAdd(MultipartFile file, @Valid BookDTO bookDTO, BindingResult bindingResult) throws IOException {
        log.info("Post book add.........");
        log.info(file.getOriginalFilename());
        log.info(file.getSize());
        log.info(file.getContentType());

        file.transferTo(new File("c://files//" + file.getOriginalFilename()));
        bookDTO.setImgFileName(file.getOriginalFilename());
        if (bindingResult.hasErrors()) {
            log.info("has errors,,,,,,,,,,,,,");
            return "redirect:/book/add";
        }
        log.info(bookDTO);
        bookService.addBook(bookDTO);
        return "redirect:/book/books";
    }

    @GetMapping("/editBook")
    public void getEdit(BookDTO bookDTO,Model model) {
        log.info("Get book edit.........");
        model.addAttribute("books", bookService.getBooks(bookDTO));
    }

    @GetMapping("/updateBook")
    public void getModify(String id, Model model) {
        log.info("Get book update.........");
        model.addAttribute("book", bookService.getBook(id));
    }

    @PostMapping("/updateBook")
    public String postEdit(BookDTO bookDTO, MultipartFile file, BindingResult bindingResult) throws IOException {
        log.info("Post book update.........");
        if (bindingResult.hasErrors()) {
            log.info("errors.............");
            return "redirect:/book/editBook";
        }
        log.info(bookDTO);
        if (!file.isEmpty()) {
            file.transferTo(new File("c://files//" + file.getOriginalFilename()));
            bookDTO.setImgFileName(file.getOriginalFilename());
        } else {
            BookDTO existingBook = bookService.getBook(bookDTO.getId());
            bookDTO.setImgFileName(existingBook.getImgFileName());
        }
        bookService.modifyBook(bookDTO);
        return "redirect:/book/editBook";
    }

    @GetMapping("/remove")
    public String getremove(String id, Model model) {
        log.info("Get book remove.........");
        bookService.deleteBook(id);
        return "redirect:/book/editBook";
    }
}
