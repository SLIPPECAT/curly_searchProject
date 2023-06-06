package com.curly.search.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "이메일 형식을 맞추서 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+$", message = "이메일 형식을 맞추서 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}\\[\\]\\|:;\"'<>,.?\\/]).{4,15}$",
            message = "4~15글자, 글자 1개, 숫자 1개, 특수문자 1개 꼭 입력해야합니다.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;


    private boolean Admin = false;
    private String adminToken= "";

}
