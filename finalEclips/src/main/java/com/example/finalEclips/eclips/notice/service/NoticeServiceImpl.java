package com.example.finalEclips.eclips.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.notice.repository.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeMapper noticeMapper;

	@Override
	public List<NoticeDto> getNotices() {
		return noticeMapper.findAllNotices();
	}

	@Override
	public NoticeAttachmentDto getNoticeAttachment(int id) {
		return noticeMapper.findNoticeAttachmentById(id);
	}
	

}
