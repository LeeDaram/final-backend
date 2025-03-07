package com.example.finalEclips.eclips.notice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.notice.dto.CreateNoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeRequestDto;
import com.example.finalEclips.eclips.notice.dto.NoticeUpdateDto;
import com.example.finalEclips.eclips.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	private final NoticeService noticeService;

	//공지사항 전체조회
	@GetMapping("/main")
	public ResponseEntity<List<NoticeDto>> getNotice() {
		return ResponseEntity.ok(noticeService.getNotices());
	}
	// 공지사항 페이지네이션
	@GetMapping("/main/pagination")
	public ResponseEntity<PageImpl<NoticeDto>> getNoticesPage(
			NoticeRequestDto noticeRequestDto,
			@PageableDefault(size = 8, page = 0) Pageable pageable
			){
		return ResponseEntity.ok(noticeService.getNoticesPage(noticeRequestDto, pageable));
				}
	//각 게시물 첨부파일 조회
	@GetMapping("/attachments/{id}")
	public ResponseEntity<NoticeAttachmentDto> getNoticeAttachment(@PathVariable("id") int id){
		return ResponseEntity.ok(noticeService.getNoticeAttachment(id));
	}
	// 첨부파일 업로드
	@PostMapping("/attachments")
    public ResponseEntity<Void> uploadFiles(
            @RequestPart("files") List<MultipartFile> files,
            @RequestParam("noticeId") int noticeId) {
			  noticeService.createNoticeAttachments(noticeId, files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
	//제목 or 내용 검색바
	@GetMapping("/main/search/{search}")
	public ResponseEntity<List<NoticeDto>> getSearchNotices(@PathVariable("search") String search){
		return ResponseEntity.ok(noticeService.getSearchNotices(search));
	}
	
	//공지사항 클릭시 타겟팅
	@GetMapping("/main/{id}")
	public ResponseEntity<NoticeDto> getNoticeById(@PathVariable("id") int id){
		return ResponseEntity.ok(noticeService.getNoticeById(id));
	}
	
	//공지사항 수정
	@PatchMapping("/update/{id}")
	public ResponseEntity<NoticeUpdateDto> updateNoticeById(@PathVariable("id") int id, @RequestBody NoticeUpdateDto noticeUpdateDto){
		noticeService.updateNoticeById(id, noticeUpdateDto);
		return ResponseEntity.ok().build();
	}
	//공지사항 삭제
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteNoticeById(@PathVariable("id") int id){
		noticeService.deleteNoticeById(id);
		return ResponseEntity.ok().build();
	}
	//공지사항 작성
	@PostMapping("/create")
	public ResponseEntity<Map<String, Integer>> createNotice(@RequestBody CreateNoticeDto createNoticeDto) {
	    int noticeId = noticeService.createNotice(createNoticeDto);
	    Map<String, Integer> response = new HashMap<>();
	    response.put("id", noticeId); //프론트로 id값 반환해서 공지사항 먼져 작성후 올라간 id값에 첨부파일 업로드
	    return ResponseEntity.ok(response);
	}
	// 첨부파일 조회
	@GetMapping("/attachment/img/{id}/download")
	public ResponseEntity<Resource> downloadNoticeAttachment(@PathVariable("id") int id) throws IOException {
		return noticeService.downloadNoticeAttachmentResource(id);
    }
	// 공지사항별 첨부파일 정보 조회
	@GetMapping("/attachment/{noticeId}/download")
	public ResponseEntity<List<NoticeAttachmentDto>> getNoticeAttachments(@PathVariable("noticeId") int noticeId) {
	    List<NoticeAttachmentDto> attachments = noticeService.getNoticeAttachmentsByNoticeId(noticeId);
	    if (attachments == null || attachments.isEmpty()) {
//	        return ResponseEntity.notFound().build();
	    	
	    }
	    return ResponseEntity.ok(attachments);
	}
}
