package com.tw.tw.wall;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WallEmptyException extends ResponseStatusException {
    public WallEmptyException(String userName) {
        super(HttpStatus.NOT_FOUND, String.format("Wall is empty for user: %s", userName));
    }
}
