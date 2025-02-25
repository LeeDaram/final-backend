package com.example.finalEclips.eclips.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalEclips.eclips.notice.dto.CreateNoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeUpdateDto;
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

	@Override
	public List<NoticeDto> getSearchNotices(String search) {
		return noticeMapper.findSearchNotice(search);
	}

	@Override
	public NoticeDto getNoticeById(int id) {
		noticeMapper.incrementViewCount(id);
		return noticeMapper.findNoticeById(id);
	}

	@Override
	public void updateNoticeById(int id, NoticeUpdateDto noticeUpdateDto) {
		noticeUpdateDto.setId(id);
		noticeMapper.updateNoticeById(noticeUpdateDto);
		
	}

	@Override
	public void deleteNoticeById(int id) {
		noticeMapper.deleteNoticeById(id);
	}

	@Override
	public void createNotice(CreateNoticeDto createNoticeDto) {
		noticeMapper.saveNotice(createNoticeDto);
		
	}	

}
