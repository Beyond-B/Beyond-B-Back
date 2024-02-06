package com.beyondB.beyondB.entity;

import com.beyondB.beyondB.entity.mapping.DiaryFeeling;
import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String event;

    private String thought;

    private String emotionSpecific;

    private String behavior;

    private String result;

    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    private DiaryFeeling diaryFeeling;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setDiaryFeeling(DiaryFeeling diaryFeeling) {
        this.diaryFeeling = diaryFeeling;
    }
}
