package com.beyondB.beyondB.service.impl;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;
import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.DiaryException;
import com.beyondB.beyondB.dto.request.DiaryRequestDTO;
import com.beyondB.beyondB.entity.Diary;
import com.beyondB.beyondB.entity.Feeling;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.enums.Emotion;
import com.beyondB.beyondB.entity.mapping.DiaryFeeling;
import com.beyondB.beyondB.repository.DiaryFeelingRepository;
import com.beyondB.beyondB.repository.DiaryRepository;
import com.beyondB.beyondB.repository.FeelingRepository;
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
    private final FeelingRepository feelingRepository;
    private final DiaryFeelingRepository diaryFeelingRepository;

    @Override
    @Transactional
    public Diary updateDiary(DiaryRequestDTO.UpdateDiaryDTO request){
        Diary diary = diaryRepository.findById(request.getDiaryId())
                .orElseThrow(() -> new RuntimeException("Diary not found with id: " + request.getDiaryId()));

        updateDiaryFields(diary, request);

        return diaryRepository.save(diary);
    }

    @Transactional
    public Diary createDiary(DiaryRequestDTO.CreateDiaryDTO request, User user) {
        Diary diary = Diary.builder().user(user)
                .behavior(request.getBehavior())
                .result(request.getResult())
                .thought(request.getThought())
                .event(request.getEvent())
                .date(request.getDate())
                .emotionSpecific(request.getEmotionSpecific())

                .build();

        Emotion emotion = request.getEmotion();
        Feeling feeling = feelingRepository.findByEmotion(emotion);

        DiaryFeeling diaryFeeling = DiaryFeeling.builder().diary(diary).feeling(feeling).build();

        diaryFeelingRepository.save(diaryFeeling);

        diary.setDiaryFeeling(diaryFeeling);

        diaryRepository.save(diary);
        return diary;
    }

    private void updateDiaryFields(Diary diary, DiaryRequestDTO.UpdateDiaryDTO request) {
        diary.setEvent(request.getEvent());
        diary.setThought(request.getThought());
        diary.setEmotionSpecific(request.getEmotionSpecific());
        diary.setBehavior(request.getBehavior());
        diary.setResult(request.getResult());
    }
}