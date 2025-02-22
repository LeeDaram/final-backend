package com.example.finalEclips.eclips.notice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	private final NoticeService noticeService;

	@GetMapping("/main")
	public ResponseEntity<List<NoticeDto>> getNotice() {
		return ResponseEntity.ok(noticeService.getNotices());
	}
	@GetMapping("/attachments/{id}")
	public ResponseEntity<NoticeAttachmentDto> getNoticeAttachment(@PathVariable("id") int id){
		return ResponseEntity.ok(noticeService.getNoticeAttachment(id));
	}
}
