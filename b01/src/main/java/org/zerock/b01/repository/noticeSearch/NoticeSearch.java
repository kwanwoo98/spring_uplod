package org.zerock.b01.repository.noticeSearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b01.domain.Notice;

public interface NoticeSearch {
    Page<Notice> searchOne(String keyword,Pageable pageable);

}
