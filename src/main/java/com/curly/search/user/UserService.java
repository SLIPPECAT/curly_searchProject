package com.curly.search.user;

import com.curly.search.jwt.JwtUtil;
import com.curly.search.user.dto.LoginRequestDto;
import com.curly.search.user.dto.SignupRequestDto;
import com.curly.search.user.entity.User;
import com.curly.search.user.entity.UserRoleEnum;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<StatusResponseDto> signup(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        String nickname = requestDto.getNickname();
        // 사용자 확인
        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if(found.isPresent()){ throw new IllegalArgumentException("아이디가 이미 존재합니다.");}

        Optional<User> nick = userRepository.findByUsername(requestDto.getNickname());
        if(nick.isPresent()){ throw new IllegalArgumentException("닉네임이 이미 존재합니다.");}
        UserRoleEnum role = UserRoleEnum.USER;
        // 관리자 여부 확인
        if(requestDto.isAdmin()){
            if(!requestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 토큰값이 다릅니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        // 유저 생성
        User user = new User(username, encodedPassword, nickname, role);
        userRepository.save(user);

        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "회원가입 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }

    @Transactional
    public ResponseEntity<StatusResponseDto> login(LoginRequestDto requestDto, HttpServletResponse response){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new IllegalArgumentException("사용자가 존재하지 않습니다."));
        // 저장된 암호와 입력왼 암호 비교
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        }
        // Jwt 토큰 발급
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "로그인 완료");
        String token = jwtUtil.createToken(user.getUsername(), user.getNickname(), user.getRole());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getNickname(), user.getRole()));

        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }
}
