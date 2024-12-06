package com.myApp.backend.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.myApp.backend.views.Views;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // Đổi tên bảng từ "user2" thành "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @JsonView(Views.Base.class)
    private String email; // Email sẽ là unique identifier của người dùng trong hệ thống (thay vì username)

    }
