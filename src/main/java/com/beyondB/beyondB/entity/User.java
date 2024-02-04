package com.beyondB.beyondB.entity;

import com.beyondB.beyondB.entity.common.BaseEntity;
import com.beyondB.beyondB.entity.enums.Role;
import com.beyondB.beyondB.entity.mapping.UserBook;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String socialType;

    private String birth;

    private String picture;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Diary> diaryList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserBook> userBookList = new ArrayList<>();

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    @NotNull
    private Role role;

    public String getRoleKey() {
        return this.role.getKey();
    }

    public User update(String name, String picture) {
        this.username = name;
        this.picture = picture;

        return this;
    }
}
