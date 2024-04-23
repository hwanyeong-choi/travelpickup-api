package com.travelpickup.member.domain;

import com.travelpickup.member.enums.LoginProvider;
import com.travelpickup.member.enums.TravelPickupManagerRole;
import com.travelpickup.member.enums.TravelPickupUserRole;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "travelpickup_manager")
public class TravelPickupManager {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "provider", columnDefinition = "VARCHAR(100)", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LoginProvider provider;

    @Column(name = "provider_id", unique = true, nullable = false)
    private Long providerId;

    @Column(name = "role", columnDefinition = "VARCHAR(100)", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TravelPickupManagerRole travelPickupManagerRole;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "modify_at", nullable = false)
    private LocalDateTime modifyAt;

}
