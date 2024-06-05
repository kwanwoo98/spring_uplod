package ogrg.zerock.webtest1.dto;

import java.time.LocalDate;

public class NoticeDTO {
    private int no;
    private String title;
    private String content;
    private int count;
    private LocalDate create_date;

    public int getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getCount() {
        return count;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }
}
