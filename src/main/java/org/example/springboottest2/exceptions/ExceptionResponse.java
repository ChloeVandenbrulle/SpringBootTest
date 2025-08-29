package org.example.springboottest2.exceptions;

import java.time.Instant;

public record ExceptionResponse(Instant data, String message, String details) {
}
