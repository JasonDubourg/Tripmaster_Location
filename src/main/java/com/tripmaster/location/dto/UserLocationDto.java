package com.tripmaster.location.dto;

import java.util.UUID;

public class UserLocationDto {
    private UUID userId;
    private Coordinates coordinates;

    public UserLocationDto() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "UserRecentLocation [userUUID=" + userId + ", coordinates=" + coordinates + "]";
    }
}
