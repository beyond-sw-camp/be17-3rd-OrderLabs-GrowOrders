package org.example.groworders.domain.inventories.service;

import lombok.*;
import org.example.groworders.common.exception.BaseException;
import org.example.groworders.domain.crops.model.dto.CropDto;
import org.example.groworders.domain.crops.model.entity.Crop;
import org.example.groworders.domain.crops.repository.CropQueryRepository;
import org.example.groworders.domain.crops.repository.CropRepository;
import org.example.groworders.domain.farms.model.dto.FarmDto;
import org.example.groworders.domain.farms.model.entity.Farm;
import org.example.groworders.domain.farms.repository.FarmRepository;
import org.example.groworders.domain.inventories.model.dto.InventoryDto;
import org.example.groworders.domain.users.service.S3PresignedUrlService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.groworders.common.model.BaseResponseStatus.INVALID_CROP_INFO;
import static org.example.groworders.common.model.BaseResponseStatus.INVALID_FARM_INFO;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final CropQueryRepository cropQueryRepository;
    private final CropRepository cropRepository;
    private final S3PresignedUrlService s3PresignedUrlService;

    //재고 등록
    public void save(InventoryDto.Register dto) {
        Crop crop = cropRepository.findById(dto.getCropId()).orElseThrow(()-> BaseException.from(INVALID_CROP_INFO));

        //작물이 존재하면 예측 재고 등록
        crop.registerInventory(dto);
        cropRepository.save(crop);
    }

    //재고 수정
    public void update(InventoryDto.Update dto) {
        Crop crop = cropRepository.findById(dto.getCropId()).orElseThrow(()-> BaseException.from(INVALID_CROP_INFO));

        //작물이 존재하면 수정
        crop.updateInventory(dto);
        cropRepository.save(crop);
    }

    //재고 상세 조회
    public CropDto.CropResponse details(Long cropId) {
        Crop crop = cropRepository.findById(cropId).orElseThrow(() -> BaseException.from(INVALID_CROP_INFO));
        return CropDto.CropResponse.from(crop);

    }


    //재고 목록 조회
    public FarmDto.FarmListResponse list(Long farmId) {
        Farm farm = cropRepository.findByIdWithCrop(farmId).orElseThrow(() -> BaseException.from(INVALID_FARM_INFO));

        String presignedUrl = farm.getFarmImage() != null ?
                s3PresignedUrlService.generatePresignedUrl(farm.getFarmImage(), Duration.ofMinutes(60)) :
                s3PresignedUrlService.generatePresignedUrl("not-found-image.jpg", Duration.ofMinutes(60));

        return FarmDto.FarmListResponse.from(farm, presignedUrl);
    }

    //재고 검색 조회
    public List<CropDto.CropResponse> search(Long farmId, CropDto.Search dto) {
        List<Crop> crop = cropQueryRepository.search(farmId, dto);
        return crop.stream().map(CropDto.CropResponse::from).toList();
    }
}
