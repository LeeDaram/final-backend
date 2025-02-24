package com.example.finalEclips.eclips.notice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finalEclips.eclips.notice.dto.NoticeAttachmentDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeUpdateDto;
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
	
	@GetMapping("/main/search/{search}")
	public ResponseEntity<List<NoticeDto>> getSearchNotices(@PathVariable("search") String search){
		return ResponseEntity.ok(noticeService.getSearchNotices(search));
	}
	@GetMapping("/main/{id}")
	public ResponseEntity<NoticeDto> getNoticeById(@PathVariable("id") int id){
		return ResponseEntity.ok(noticeService.getNoticeById(id));
	}
	@PatchMapping("/update/{id}")
	public ResponseEntity<NoticeUpdateDto> updateNoticeById(@PathVariable("id") int id, @RequestBody NoticeUpdateDto noticeUpdateDto){
		noticeService.updateNoticeById(id, noticeUpdateDto);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteNoticeById(@PathVariable("id") int id){
		noticeService.deleteNoticeById(id);
		return ResponseEntity.ok().build();
	}
}
