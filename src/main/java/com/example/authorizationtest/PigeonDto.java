package com.example.authorizationtest;

public record PigeonDto(String id,
                        String description,
                        Integer gender) {
    public PigeonDao toDao() {
        return new PigeonDao(
                this.id,
                this.description,
                this.gender,
                null,
                null);
    }
}
