package org.zerock.b01.repsitory;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b01.repository.NoticeRepository;

@SpringBootTest
@Log4j2
public class NoticeRepositoryTests {

    @Autowired
    private NoticeRepository noticeRepository;
    @Test
    public void testSearchOne(){
        String keyword = "2" ;
        Pageable pageable = PageRequest.of(1,10, Sort.by("no").descending());
        noticeRepository.searchOne(keyword,pageable);
    }
}
