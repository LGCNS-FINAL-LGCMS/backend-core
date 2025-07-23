package com.lgcms.core.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FaqError implements ErrorCodeInterface {
    FAQ_NOT_FOUND("FAQE-03","FAQ를 불러올 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String status;
    private final String message;
    private final HttpStatus httpStatus;

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.builder()
            .status(status)
            .message(message)
            .httpStatus(httpStatus)
            .build();
    }
}
