package com.example.finalEclips.eclips.notice.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeUpdateDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> findAllNotices();
	NoticeAttachmentDto findNoticeAttachmentById(int id);
	List<NoticeDto> findSearchNotice(String search);
	NoticeDto findNoticeById(int id);
	void updateNoticeById(NoticeUpdateDto noticeUpdateDto);
	void deleteNoticeById(int id);
}
