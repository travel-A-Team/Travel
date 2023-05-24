package com.travelproject.travelproject.service.Implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.plannerBoard.PatchPlannerBoardRequestDto;
import com.travelproject.travelproject.dto.request.plannerBoard.PostPlannerBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerListResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerTouristSpotResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerWriteListResponseDto;
import com.travelproject.travelproject.entity.PlannerDailyTravelDateEntity;
import com.travelproject.travelproject.entity.TouristSpotEntity;
import com.travelproject.travelproject.entity.listEntity.PlannerBoardResultSet;
import com.travelproject.travelproject.entity.listEntity.PlannerWriteRegionResultSet;
import com.travelproject.travelproject.entity.listEntity.PlannerWriteSpotResultSet;
import com.travelproject.travelproject.entity.planner.PlannerEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.PlannerBoardRepository;
import com.travelproject.travelproject.repository.PlannerDailyTravelDateRepository;
import com.travelproject.travelproject.repository.RegionRepository;
import com.travelproject.travelproject.repository.TouristSpotRepository;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.PlannerService;

@Service
public class PlannerServiceImplement implements PlannerService {

    private UserRepository userRepository;
    private PlannerBoardRepository plannerBoardRepository;
    private PlannerDailyTravelDateRepository plannerDailyTravelDateRepository;
    private TouristSpotRepository touristSpotRepository;
    private RegionRepository regionRepository;
    private StringBuilder stringBuilder = new StringBuilder();
    private String plannerTourRouteCollection;

    @Autowired
    public PlannerServiceImplement(UserRepository userRepository, PlannerBoardRepository plannerBoardRepository,
            PlannerDailyTravelDateRepository plannerDailyTravelDateRepository,
            TouristSpotRepository touristSpotRepository, RegionRepository regionRepository) {
        this.userRepository = userRepository;
        this.plannerBoardRepository = plannerBoardRepository;
        this.plannerDailyTravelDateRepository = plannerDailyTravelDateRepository;
        this.touristSpotRepository = touristSpotRepository;
        this.regionRepository = regionRepository;
    }

    // @ 플래너 작성
    @Override
    public ResponseEntity<ResponseDto> postPlannerBoard(UserToken userToken, PostPlannerBoardRequestDto dto) {

        int plannerSpotListSize = dto.getPlannerTravelSpotList().size();

        try {

            if (userToken == null) {
                return ResponseMessage.NOT_EXIST_USER_TOKEN;
            }

            if (!(userToken.getRole().equals("admin"))){
                return ResponseMessage.NO_PERMISSIONS;
            }

            String plannerUserEmail = userToken.getEmail();
            boolean existedByEmail = userRepository.existsByEmail(plannerUserEmail);
            if (!existedByEmail) {
                return ResponseMessage.NOT_EXIST_USER_EMAIL;
            }

            

            for (int count = 0; count < plannerSpotListSize; count++) {
                if (count == 0) {
                    stringBuilder.append(dto.getPlannerTravelSpotList().get(count).getWriteTouristSpotName());
                } else {
                    stringBuilder.append(" → " + dto.getPlannerTravelSpotList().get(count).getWriteTouristSpotName());
                }
            }
            plannerTourRouteCollection = stringBuilder.toString();

            List<String> plannerTravelDateList = new ArrayList<>();

            for (int count = 0; count < plannerSpotListSize; count++) {
                String date = dto.getPlannerTravelSpotList().get(count).getPlannerTravelDate();
                plannerTravelDateList.add(date);
            }

            String maxDate = plannerTravelDateList.get(0);
            String minDate = plannerTravelDateList.get(0);

            for (int count = 1; count < plannerTravelDateList.size(); count++) {
                if (maxDate.compareTo(plannerTravelDateList.get(count)) < 0) {
                    maxDate = plannerTravelDateList.get(count);
                }
                if (minDate.compareTo(plannerTravelDateList.get(count)) >= 0) {
                    minDate = plannerTravelDateList.get(count);
                }
            }

            String totalPlannerTravelSchedule = minDate + " ~ " + maxDate;

            PlannerEntity plannerEntity = new PlannerEntity(plannerUserEmail, dto, plannerTourRouteCollection,
                    totalPlannerTravelSchedule);
            plannerBoardRepository.save(plannerEntity);
            stringBuilder.setLength(0);

            int plannerNumber = plannerEntity.getPlannerNumber();

            for (int count = 0; count < plannerSpotListSize; count++) {
                int touristSpotNumber = dto.getPlannerTravelSpotList().get(count)
                        .getTouristSpotWriteTouristSpotNumber();
                int sequence = dto.getPlannerTravelSpotList().get(count).getSequence();
                TouristSpotEntity touristSpotEntity = touristSpotRepository.findByTouristSpotNumber(touristSpotNumber);

                if (touristSpotEntity == null)
                    return ResponseMessage.NOT_EXIST_WRITE_TOURIST_SPOT_NUMBER;

                String plannerTravelDate = plannerTravelDateList.get(count);

                PlannerDailyTravelDateEntity plannerDailyTravelDateEntity = new PlannerDailyTravelDateEntity(
                        plannerNumber,
                        plannerTravelDate, sequence, touristSpotEntity, dto);

                plannerDailyTravelDateRepository.save(plannerDailyTravelDateEntity);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    // @ 플래너 작성 시 지역 이름 리스트, 여행지 리스트 불러오기
    @Override
    public ResponseEntity<? super GetPlannerWriteListResponseDto> getWritePlannerBoardList() {
        GetPlannerWriteListResponseDto body = null;

        try {
            List<PlannerWriteRegionResultSet> plannerWriteRegionResultSet = regionRepository
                    .getPlannerWriteRegionList();
            List<PlannerWriteSpotResultSet> plannerWriteSpotResultSet = touristSpotRepository.getPlannerWriteSpotList();
            body = new GetPlannerWriteListResponseDto(plannerWriteRegionResultSet, plannerWriteSpotResultSet);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // @ 플래너 작성 시 지역 이름으로 여행지 검색
    @Override
    public ResponseEntity<? super GetPlannerTouristSpotResponseDto> getSearchRegionNameSpotList(
            String writeRegion) {
        GetPlannerTouristSpotResponseDto body = null;
        try {
            List<TouristSpotEntity> searchPlannerSpotResult = touristSpotRepository
                    .findByWriteRegionContains(writeRegion);
            body = new GetPlannerTouristSpotResponseDto(searchPlannerSpotResult);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // @ 플래너 작성 시 여행지 이름으로 여행지 검색
    @Override
    public ResponseEntity<? super GetPlannerTouristSpotResponseDto> getSearchTouristSpotNameList(
            String writeTouristSpotName) {
        GetPlannerTouristSpotResponseDto body = null;
        try {
            List<TouristSpotEntity> searchPlannerSpotResult = touristSpotRepository
                    .findByWriteTouristSpotNameContains(writeTouristSpotName);
            body = new GetPlannerTouristSpotResponseDto(searchPlannerSpotResult);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // @ 플래너 작성 시 여행지 지역, 이름으로 여행지 검색
    @Override
    public ResponseEntity<? super GetPlannerTouristSpotResponseDto> getSearchTouristSpotList(String writeRegion,
            String writeTouristSpotName) {
        GetPlannerTouristSpotResponseDto body = null;
        try {
            List<TouristSpotEntity> searchPlannerSpotResult = touristSpotRepository
                    .findByWriteRegionAndWriteTouristSpotNameContains(writeRegion, writeTouristSpotName);
            body = new GetPlannerTouristSpotResponseDto(searchPlannerSpotResult);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // @ 플래너 목록
    @Override
    public ResponseEntity<? super GetPlannerListResponseDto> getPlannerBoardList() {
        GetPlannerListResponseDto body = null;

        try {
            List<PlannerBoardResultSet> resultSet = plannerBoardRepository.getPlannerBoardList();
            body = new GetPlannerListResponseDto(resultSet);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // @ 플래너 상세 보기
    @Override
    public ResponseEntity<? super GetPlannerResponseDto> getPlannerBoard(Integer plannerNumber) {
        GetPlannerResponseDto body = null;

        try {
            if (plannerNumber == null) {
                return ResponseMessage.VAILDATION_FAILED;
            }

            PlannerEntity plannerEntity = plannerBoardRepository.findByPlannerNumber(plannerNumber);
            if (plannerEntity == null) {
                return ResponseMessage.NOT_EXIST_PLANNER_NUMBER;
            }

            List<PlannerDailyTravelDateEntity> plannerDailyTravelDateEntities = plannerDailyTravelDateRepository
                    .findByPlannerPlannerNumber(plannerNumber);

            body = new GetPlannerResponseDto(plannerEntity, plannerDailyTravelDateEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // @ 플래너 수정
    @Override
    public ResponseEntity<ResponseDto> patchPlannerBoard(UserToken userToken, PatchPlannerBoardRequestDto dto) {

        int plannerNumber = dto.getPlannerNumber();
        String plannerTitle = dto.getPlannerTitle();
        int plannerMoney = dto.getPlannerMoney();
        int plannerSpotListSize = dto.getPlannerTravelSpotList().size();

        int touristSpotWriteTouristSpotNumber;
        String plannerTravelDate;
        String writeImageUrl;
        String writeTouristSpotName;
        String writePlannerAddress;
        int sequence;

        try {
            if (userToken == null) {
                return ResponseMessage.NOT_EXIST_USER_TOKEN;
            }

            if (!(userToken.getRole().equals("admin"))){
                return ResponseMessage.NO_PERMISSIONS;
            }

            String plannerUserEmail = userToken.getEmail();
            PlannerEntity plannerEntity = plannerBoardRepository.findByPlannerNumber(plannerNumber);
            if (plannerEntity == null) {
                return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;
            }

            boolean existedUserEmail = userRepository.existsByEmail(plannerUserEmail);
            if (!existedUserEmail) {
                return ResponseMessage.NOT_EXIST_USER_EMAIL;
            }

            boolean equalWriter = plannerEntity.getPlannerUserEmail().equals(plannerUserEmail);
            if (!equalWriter) {
                return ResponseMessage.NO_PERMISSIONS;
            }

            List<String> plannerTravelDateList = new ArrayList<>();

            for (int count = 0; count < plannerSpotListSize; count++) {
                String date = dto.getPlannerTravelSpotList().get(count).getPlannerTravelDate();
                plannerTravelDateList.add(date);
            }

            String maxDate = plannerTravelDateList.get(0);
            String minDate = plannerTravelDateList.get(0);

            for (int count = 1; count < plannerTravelDateList.size(); count++) {
                if (maxDate.compareTo(plannerTravelDateList.get(count)) < 0) {
                    maxDate = plannerTravelDateList.get(count);
                }
                if (minDate.compareTo(plannerTravelDateList.get(count)) >= 0) {
                    minDate = plannerTravelDateList.get(count);
                }
            }

            String totalPlannerTravelSchedule = minDate + " ~ " + maxDate;

            for (int count = 0; count < plannerSpotListSize; count++) {
                if (count == 0) {
                    stringBuilder.append(dto.getPlannerTravelSpotList().get(count).getWriteTouristSpotName());
                } else {
                    stringBuilder.append(" → " + dto.getPlannerTravelSpotList().get(count).getWriteTouristSpotName());
                }
            }
            plannerTourRouteCollection = stringBuilder.toString();

            plannerEntity.setPlannerTitle(plannerTitle);
            plannerEntity.setTotalPlannerTravelSchedule(totalPlannerTravelSchedule);
            plannerEntity.setPlannerMoney(plannerMoney);
            plannerEntity.setPlannerTourRoute(plannerTourRouteCollection);

            plannerBoardRepository.save(plannerEntity);
            stringBuilder.setLength(0);

            List<PlannerDailyTravelDateEntity> plannerDailyEntities = plannerDailyTravelDateRepository
                    .findByPlannerPlannerNumber(plannerEntity.getPlannerNumber());

            for (int count = 0; count < plannerSpotListSize; count++) {
                touristSpotWriteTouristSpotNumber = dto.getPlannerTravelSpotList().get(count)
                        .getTouristSpotWriteTouristSpotNumber();
                plannerTravelDate = dto.getPlannerTravelSpotList().get(count).getPlannerTravelDate();
                writeImageUrl = dto.getPlannerTravelSpotList().get(count).getWriteImageUrl();
                writeTouristSpotName = dto.getPlannerTravelSpotList().get(count).getWriteTouristSpotName();
                writePlannerAddress = dto.getPlannerTravelSpotList().get(count).getWritePlannerAddress();
                sequence = dto.getPlannerTravelSpotList().get(count).getSequence();

                if (count < plannerDailyEntities.size()) {
                    PlannerDailyTravelDateEntity plannerDailyTravelDateEntity = plannerDailyEntities.get(count);
                    plannerDailyTravelDateEntity
                            .setTouristSpotWriteTouristSpotNumber(touristSpotWriteTouristSpotNumber);
                    plannerDailyTravelDateEntity.setPlannerTravelDate(plannerTravelDate);
                    plannerDailyTravelDateEntity.setWriteImageUrl(writeImageUrl);
                    plannerDailyTravelDateEntity.setWriteTouristSpotName(writeTouristSpotName);
                    plannerDailyTravelDateEntity.setWritePlannerAddress(writePlannerAddress);
                    plannerDailyTravelDateEntity.setSequence(sequence);
                    plannerDailyTravelDateRepository.save(plannerDailyTravelDateEntity);
                } else {
                    PlannerDailyTravelDateEntity addPlannerDailyTravelDateEntity = new PlannerDailyTravelDateEntity(dto,
                            touristSpotWriteTouristSpotNumber, plannerTravelDate, writeImageUrl, writeTouristSpotName,
                            writePlannerAddress, sequence);
                    plannerDailyTravelDateRepository.save(addPlannerDailyTravelDateEntity);
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    // @ 플래너 삭제
    @Override
    public ResponseEntity<ResponseDto> deletePlannerBoard(UserToken userToken, Integer plannerNumber) {

        try {
            if (userToken == null) {
                return ResponseMessage.NOT_EXIST_USER_TOKEN;
            }

            if (!(userToken.getRole().equals("admin"))){
                return ResponseMessage.NO_PERMISSIONS;
            }

            String plannerUserEmail = userToken.getEmail();
            if (plannerNumber == null)
                return ResponseMessage.VAILDATION_FAILED;

            PlannerEntity plannerEntity = plannerBoardRepository.findByPlannerNumber(plannerNumber);
            if (plannerEntity == null)
                return ResponseMessage.NOT_EXIST_PLANNER_NUMBER;

            List<PlannerDailyTravelDateEntity> plannerDailyTravelDateEntity = plannerDailyTravelDateRepository
                    .findByPlannerPlannerNumber(plannerNumber);
            if (plannerDailyTravelDateEntity == null)
                return ResponseMessage.NOT_EXIST_PLANNER_NUMBER;

            boolean existedUserEmail = userRepository.existsByEmail(plannerUserEmail);
            if (!existedUserEmail)
                return ResponseMessage.NOT_EXIST_USER_EMAIL;

            boolean equalWriter = plannerEntity.getPlannerUserEmail().equals(plannerUserEmail);
            if (!equalWriter)
                return ResponseMessage.NO_PERMISSIONS;

            plannerDailyTravelDateRepository.deleteByPlannerPlannerNumber(plannerNumber);
            plannerBoardRepository.delete(plannerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }
        return ResponseMessage.SUCCESS;
    }

}
