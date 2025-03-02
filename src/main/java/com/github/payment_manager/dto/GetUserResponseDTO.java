package com.github.payment_manager.dto;

import com.github.payment_manager.domain.User;

public record GetUserResponseDTO(String id, String name) {
    public GetUserResponseDTO(User user) {
        this(user.getId(), user.getName());
    }
}
