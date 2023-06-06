package com.curly.search.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    // 200 클라이언트에 반환 성공
    SIGNUP_SUCCESS(200, "회원가입에 성공하였습니다."),
    LOGIN_SUCCESS(200, "로그인에 성공하였습니다.");

    private final int status;
    private final String msg;
}


//    UPDATE_POST_SUCCESS(200, "게시글을 수정에 성공했습니다."),
//    DELETE_POST_SUCCESS(200, "게시글을 삭제에 성공했습니다."),
//
//    ADD_COMMENT_SUCCESS(200, "댓글을 생성에 성공했습니다."),
//    UPDATE_COMMENT_SUCCESS(200, "댓글을 수정에 성공했습니다.")
//    DELETE_COMMENT_SUCCESS(200, "댓글을 삭제에 성공했습니다.");
