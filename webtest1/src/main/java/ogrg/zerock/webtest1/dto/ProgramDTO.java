package ogrg.zerock.webtest1.dto;

import java.time.LocalDate;

public class ProgramDTO {
    private int no;
    private String title;
    private String text;
    private String subrText;
    private String schedule;
    private String img;
    private LocalDate create_date;

    // Getter methods
    public int getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getSubrText() {
        return subrText;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getImg() {
        return img;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    // Setter methods
    public void setNo(int no) {
        this.no = no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubrText(String subText) {
        this.subrText = subText;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }
}
