package com.beyondB.beyondB.service.impl;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;
import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.DiaryException;
import com.beyondB.beyondB.dto.request.DiaryRequestDTO;
import com.beyondB.beyondB.entity.Diary;
import com.beyondB.beyondB.repository.DiaryRepository;
import com.beyondB.beyondB.service.DiaryService;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    @Override
    @Transactional
    public Diary updateDiary(DiaryRequestDTO.UpdateDiaryDTO request){
        Diary diary = diaryRepository.findById(request.getDiaryId())
                .orElseThrow(() -> new RuntimeException("Diary not found with id: " + request.getDiaryId()));

        updateDiaryFields(diary, request);

        return diaryRepository.save(diary);
    }

    @Override
    @Transactional
    public  void deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new DiaryException(ErrorStatus.DIARY_NOT_FOUND));

        diaryRepository.delete(diary);
    }

    private void updateDiaryFields(Diary diary, DiaryRequestDTO.UpdateDiaryDTO request) {
        diary.setEvent(request.getEvent());
        diary.setThought(request.getThought());
        diary.setEmotionSpecific(request.getEmotionSpecific());
        diary.setBehavior(request.getBehavior());
        diary.setResult(request.getResult());
    }
}