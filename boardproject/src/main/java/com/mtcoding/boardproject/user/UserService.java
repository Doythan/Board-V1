package com.mtcoding.boardproject.user;

import com.mtcoding.boardproject.core.handler.ex.Exception401;
import com.mtcoding.boardproject.core.handler.ex.Exception404;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User 로그인(UserRequest.LoginDTO requestDTO) {
        // 유저 조회
        User user = userRepository.findByUsername(requestDTO.getUsername())
                .orElseThrow(() -> new Exception404("유저네임을 찾을 수 없습니다."));
        // 비밀번호 확인
        if (!user.getPassword().equals(requestDTO.getPassword())) {
            throw new Exception401("비밀번호가 일치하지 않습니다");
        }
        return user;
    }

    @Transactional
    public void 회원가입(UserRequest.SaveDTO requestDTO) {
        // 중복체크
        if (userRepository.findByUsername(requestDTO.getUsername()).isPresent())
            throw new Exception401("이미 존재하는 유저네임입니다.");
        // 회원가입
        User user = requestDTO.toEntity();
        userRepository.save(user);
    }
}
