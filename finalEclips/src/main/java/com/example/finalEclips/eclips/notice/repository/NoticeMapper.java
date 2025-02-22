package com.example.finalEclips.eclips.notice.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> findAllNotices();
	NoticeAttachmentDto findNoticeAttachmentById(int id);
}
