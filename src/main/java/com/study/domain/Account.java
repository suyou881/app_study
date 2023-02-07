package com.study.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private
    String password;

private boolean emailVerified;

private String emailCheckToken;

private LocalDateTime joinedAt;

private String bio;
private String url;
private String occupation;

private String liveAround;

private String location;

@Lob @Basic(fetch = FetchType.LAZY)
private String profileImage;

private boolean studyCreatedByEmail;

private boolean studyCreateByWebSet;

private boolean studyEnrollmentResultByEmail;

private boolean studyEnrollmentResultByWeb;
private boolean studyUpdatedByEmail;
private boolean studyUpdatedByWeb;
}
