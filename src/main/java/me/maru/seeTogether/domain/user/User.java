package me.maru.seeTogether.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

/**
 * 유저 클래스<p>
 * 1. password 는 JsonIgnore 을 통하여 json 에서 제거<p>
 * 2. authority 는 요구사항의 일반유저 임으로, 1개의 역할 role_user 로 작성
 *
 * @author 이석민
 */
@Entity
@Getter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 유저 시퀀스값의 ID

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email; // 유저가 로그인 하는 실제 ID

    @JsonIgnore
    @Column(name = "password")
    private String password; // 유저 password

    @Column(name = "auth_role")
    @Enumerated(EnumType.STRING)
    private AuthRole authRole;

    public User() {
    }

    @Builder
    public User(Long userId, String name, String email, String password, AuthRole authority) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authRole = authority;
    }
}