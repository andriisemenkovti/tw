package com.tw.tw.posting;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public final class PostDTO {
    private final Long id;
    private final String content;
    private final String userName;
    private final LocalDateTime createdAt;
}
