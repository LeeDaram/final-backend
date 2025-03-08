package com.example.finalEclips.eclips.store.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.common.dto.FileDto;
import com.example.finalEclips.eclips.common.dto.PaginationDto;
import com.example.finalEclips.eclips.helper.FileHelper;
import com.example.finalEclips.eclips.store.dto.FilterRequestDto;
import com.example.finalEclips.eclips.store.dto.IndustryDto;
import com.example.finalEclips.eclips.store.dto.ReviewAttachmentDto;
import com.example.finalEclips.eclips.store.dto.SidoDto;
import com.example.finalEclips.eclips.store.dto.SigunguDto;
import com.example.finalEclips.eclips.store.dto.StoreAddressDto;
import com.example.finalEclips.eclips.store.dto.StoreReservationDto;
import com.example.finalEclips.eclips.store.dto.StoreReviewDto;
import com.example.finalEclips.eclips.store.dto.StoreReviewRequestDto;
import com.example.finalEclips.eclips.store.dto.UserDto;
import com.example.finalEclips.eclips.store.dto.StoreDto;
import com.example.finalEclips.eclips.store.dto.StoreFilterDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDetailDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDto;
import com.example.finalEclips.eclips.store.dto.ReviewDto;
import com.example.finalEclips.eclips.store.dto.ReviewRequestDto;
import com.example.finalEclips.eclips.store.repository.StoreMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
	private final StoreMapper storeMapper;
	private final FileHelper fileHelper;

	@Override
	public PageImpl<StoreDto> getStores(StoreRequestDto storeRequestDto, Pageable pageable) {
		PaginationDto<?> paginationDto = PaginationDto.builder().data(storeRequestDto).pageable(pageable).build();
		List<StoreDto> content = storeMapper.findPaginatedStores(paginationDto);
		int totalCount = storeMapper.findPaginatedStoresCount(paginationDto);

		return new PageImpl<>(content, pageable, totalCount);
	}

	@Override
	public List<SidoDto> getSido() {
		return storeMapper.findSidoName();
	}

	@Override
	public List<SigunguDto> getSigungu() {
		return storeMapper.findSigunguName();
	}

	@Override
	public List<IndustryDto> getIndustry() {
		return storeMapper.findIndustryName();
	}

	@Override
	public PageImpl<StoreFilterDto> getStoreFilter(FilterRequestDto filterRequestDto, Pageable pageable) {
		PaginationDto<?> paginationDto = PaginationDto.builder().data(filterRequestDto).pageable(pageable).build();
		List<StoreFilterDto> content = storeMapper.filterStore(paginationDto);
		int totalCount = storeMapper.filterStoreCount(paginationDto);

		return new PageImpl<>(content, pageable, totalCount);
	}

	@Override
	public List<StoreAddressDto> getAllAddress() {
		return storeMapper.findAllAddress();
	}

	@Override
	public List<StoreReservationDto> getAllStoreDetail() {
		return null;
	}

	@Override
	public void createActivate(StoreRequestDetailDto storeRequestDetailDto) {
		UserDto user = this.getUser(storeRequestDetailDto.getUserId());
		if (user == null) {
			throw new IllegalStateException("존재하지 않는 유저입니다.");
		}

		boolean exists = storeMapper.existsByUserIdAndDate(storeRequestDetailDto.getUserId(),
				storeRequestDetailDto.getReservationDate());

		if (exists) {
			throw new IllegalStateException(
					"해당 날짜(" + storeRequestDetailDto.getReservationDate() + ")에 이미 예약이 완료되었습니다.");
		}

		storeMapper.saveActivate(storeRequestDetailDto);

	}

	@Override
	public UserDto getUser(String userId) {
		return storeMapper.findUserById(userId);
	}

	@Override
	@Transactional
	public void saveReview(ReviewDto reviewDto, List<MultipartFile> files) {
		// 1. 리뷰 저장한다.
		// 2. 리뷰 레코드의 아이디를 반환받는다.
		storeMapper.saveReview(reviewDto);

		// 3. 디렉터리에 파일을 업로드 시킨다 = List<FilDto)
		List<FileDto> uploadFiles = fileHelper.uploadFiles(files);

		// 4. 2번에서 받은 아이디, 3번에서 받은 파일 객체를 Mapper 로 전송해 리뷰 이미지 저장 MethodName(int
		// reviewId, List<FilDto> files)
		storeMapper.saveReviewAttechments(reviewDto.getReviewId(), uploadFiles);
	}

	@Override
	public List<ReviewAttachmentDto> getReviewAttachmentsByReviewId(int reviewId) {
		return storeMapper.findAttachmentByReviewId(reviewId);
	}

	//리소스 할거
	@Override
	public ReviewAttachmentDto getReviewAttachment(int id) {
		return storeMapper.findReviewAttachmentById(id);
	}

	@Override
	public ResponseEntity<Resource> showReviewAttachmentResource(int id) throws IOException {
		ReviewAttachmentDto reviewAttachment = this.getReviewAttachment(id);
		Resource resource = fileHelper.getFileResource(reviewAttachment.getStoredFilename());
		
		// 파일 존재하는지 확인
		if(!resource.exists()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		String contentType = Files.probeContentType(resource.getFile().toPath());
		if(contentType == null) {
			contentType = "application/octet-stream"; // 기본 바이너리 타입
		}
		
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", reviewAttachment.getOriginFilename()))
                .body(resource);
	}

	@Override
	public PageImpl<StoreReviewDto> getStoreReviews(StoreReviewRequestDto requestDto, Pageable pageable) {
		
		PaginationDto<?> paginationDto = PaginationDto.builder().data(requestDto).pageable(pageable).build();
		List<StoreReviewDto> content = storeMapper.findStoreReviews(paginationDto);
		
		int totalCount = storeMapper.findStoreReviewTotalCount(paginationDto);

		return new PageImpl<>(content, pageable, totalCount);
	}

	@Override
	public void toggleLike(int reviewId, ReviewRequestDto requestDto) {
		storeMapper.incrementLikeCount(requestDto);
	}


}
