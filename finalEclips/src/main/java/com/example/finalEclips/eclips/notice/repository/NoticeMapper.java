package com.example.finalEclips.eclips.notice.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.notice.dto.CreateNoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeUpdateDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> findAllNotices(); // 전체조회
	NoticeAttachmentDto findNoticeAttachmentById(int id); // 각공지사항마다 첨부파일 조회 left outer join
	
	List<NoticeDto> findSearchNotice(String search); // 제목또는 내용검색
	NoticeDto findNoticeById(int id); // 클릭한 공지사항 페이지 
	void updateNoticeById(NoticeUpdateDto noticeUpdateDto); // 공지사항 수정
	void deleteNoticeById(int id); // 공지사항 삭제
	void saveNotice(CreateNoticeDto createNoticeDto); // 공지사항 쓰기
	void incrementViewCount(int id); // 조회수
}
