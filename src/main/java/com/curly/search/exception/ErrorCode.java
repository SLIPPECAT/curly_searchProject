package com.curly.search.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // 400 BAD REQUEST
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다."),
    DIFFERENT_AUTHOR(HttpStatus.BAD_REQUEST, "작성자만 삭제/수정할 수 있습니다."),
    DUPLICATED_USERNAME(HttpStatus.BAD_REQUEST, "중복된 username 입니다."),
    USER_NOTFOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    NOT_FOUND_SIGNUP_USER(HttpStatus.BAD_REQUEST, "비밀번호 형식에 맞지 않습니다."),

    // 404 NOT_FOUND 잘못된 리소스 접근
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    PASSWORD_ERROR(HttpStatus.NOT_FOUND, "사용자의 비밀번호가 다릅니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),

    // 500 INTERNAL SERVER ERROR;
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다."),
    TOKEN_SECURITY_ERROR(HttpStatus.BAD_REQUEST, "안전하지 않은 토큰입니다.");

    private final HttpStatus httpStatus;
    private final String detail;

}
