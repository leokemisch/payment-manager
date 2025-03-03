package com.github.payment_manager.dto.user;

import com.github.payment_manager.domain.User;

public record GetUserResponseDTO(String id, String name, String login) {
    public GetUserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getUsername());
    }
}
