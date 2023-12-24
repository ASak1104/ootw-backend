package com.backendoori.ootw;

import java.util.NoSuchElementException;
import com.backendoori.ootw.domain.Post;
import com.backendoori.ootw.domain.User;
import com.backendoori.ootw.dto.PostSaveRequest;
import com.backendoori.ootw.dto.PostSaveResponse;
import com.backendoori.ootw.repository.PostRepository;
import com.backendoori.ootw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostSaveResponse save(PostSaveRequest request) {
        // TODO: 사용자 인증/인가 로직 추가
        User user = userRepository.findById(request.userId())
            .orElseThrow(() ->
                new NoSuchElementException("해당하는 유저가 없습니다.")
            );

        Post savedPost = postRepository.save(Post.from(user, request));
        return PostSaveResponse.from(savedPost);
    }

}
