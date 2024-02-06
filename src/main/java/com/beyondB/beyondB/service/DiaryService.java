package com.beyondB.beyondB.service;

import com.beyondB.beyondB.dto.request.DiaryRequestDTO;
import com.beyondB.beyondB.entity.Diary;

public interface DiaryService {
    Diary updateDiary(DiaryRequestDTO.UpdateDiaryDTO request);
    void deleteDiary(Long diaryId);
}

