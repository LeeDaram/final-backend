package com.example.finalEclips.eclips.notice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.common.dto.FileDto;
import com.example.finalEclips.eclips.helper.FileHelper;
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
	private final FileHelper fileHelper;

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

	//파일 삭제
	@Override
	public void deleteNoticeAttachments(int noticeId) {
		noticeMapper.deleteNoticeAttachmentsByPostId(noticeId);
		
	}
	//파일 업로드
	@Override
	public void createNoticeAttachments(int noticeId, List<MultipartFile> files) {
		List<FileDto> fileDtos = fileHelper.uploadFiles(files);
		this.deleteNoticeAttachments(noticeId);
		noticeMapper.saveNoticeAttachments(noticeId, fileDtos);
		
	}

	@Override
	public int createNotice(CreateNoticeDto createNoticeDto) {
		noticeMapper.saveNotice(createNoticeDto);
        return createNoticeDto.getNoticeId(); // mapper에서 id반환
	}

	@Override
	public ResponseEntity<Resource> downloadNoticeAttachmentResource(int id) throws IOException {
		NoticeAttachmentDto noticeAttachment = this.getNoticeAttachment(id);
		Resource resource = fileHelper.getFileResource(noticeAttachment.getStoredFilename());

		 // 파일이 존재하는지 확인
        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 파일 유형을 결정 (MIME 타입 설정)
        String contentType = Files.probeContentType(resource.getFile().toPath());
        if (contentType == null) {
            contentType = "application/octet-stream"; // 기본 바이너리 타입
        }
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", noticeAttachment.getOriginFilename()))
                .body(resource);
	}

	@Override
	public List<NoticeAttachmentDto> getNoticeAttachmentsByNoticeId(int noticeId) {
		return noticeMapper.findNoticeAttachmentsByNoticeId(noticeId);
	}	

}
