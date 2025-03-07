package com.example.finalEclips.eclips.notice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.notice.dto.CreateNoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeRequestDto;
import com.example.finalEclips.eclips.notice.dto.NoticeUpdateDto;

public interface NoticeService {
	List<NoticeDto> getNotices();
	NoticeAttachmentDto getNoticeAttachment(int id);
	
	//파일 업로드
    void deleteNoticeAttachments(int noticeId);
	void createNoticeAttachments(int noticeId, List<MultipartFile> files);
	
	List<NoticeDto> getSearchNotices(String search);
	NoticeDto getNoticeById(int id);
	void updateNoticeById(int id, NoticeUpdateDto noticeUpdateDto);
	void deleteNoticeById(int id);
	int createNotice(CreateNoticeDto createNoticeDto); //파일 업로드
	ResponseEntity<Resource> downloadNoticeAttachmentResource(int id) throws IOException; //파일 하나조회
	List<NoticeAttachmentDto> getNoticeAttachmentsByNoticeId(int noticeId); //공지사항 아이디로 다 조회
	
	PageImpl<NoticeDto> getNoticesPage(NoticeRequestDto noticeRequestDto, Pageable pageable);
}
