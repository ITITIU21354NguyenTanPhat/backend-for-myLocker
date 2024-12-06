package com.myApp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email; // Chắc chắn rằng email có getter và setter

    // Nếu cần các trường khác thì thêm vào đây
}
