package com.example.finalEclips.eclips.notice.service;

import java.util.List;

import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;

public interface NoticeService {
	List<NoticeDto> getNotices();
	NoticeAttachmentDto getNoticeAttachment(int id);

}
