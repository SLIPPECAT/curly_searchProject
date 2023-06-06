package com.curly.search.user;

import com.curly.search.exception.CustomException;
import com.curly.search.exception.ErrorCode;
import com.curly.search.exception.SuccessCode;
import com.curly.search.user.dto.LoginRequestDto;
import com.curly.search.user.dto.SignupRequestDto;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<StatusResponseDto> signup(@Validated @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.NOT_FOUND_SIGNUP_USER);
        }
        return userService.signup(requestDto);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginRequestDto requestDto, HttpServletResponse responseDto) {
        userService.login(requestDto, responseDto);

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(SuccessCode.LOGIN_SUCCESS.getStatus());
        statusResponseDto.setMsg(SuccessCode.LOGIN_SUCCESS.getMsg());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }
}
