package com.beyondB.beyondB.service;

import com.beyondB.beyondB.dto.request.DiaryRequestDTO;
import com.beyondB.beyondB.dto.response.DiaryResponseDTO;
import com.beyondB.beyondB.entity.Diary;
import com.beyondB.beyondB.entity.User;

import java.util.List;

public interface DiaryService {
    Diary updateDiary(DiaryRequestDTO.UpdateDiaryDTO request);

    Diary createDiary(DiaryRequestDTO.CreateDiaryDTO request, User user);

    void deleteDiary(Long diaryId);

    Diary getDetailDiary(Long diaryId);

    List<Diary> getMonthlyDiary(User user, int year, int month);

}

