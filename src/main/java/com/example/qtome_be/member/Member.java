package com.example.qtome_be.member;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    private String nickname;

    private String thumbnail;

    public void modify(String nickname, String thumbnail) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (thumbnail != null) {
            this.thumbnail = thumbnail;
        }
    }
}
