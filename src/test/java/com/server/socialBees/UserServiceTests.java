package com.server.socialBees;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Tag;
import com.server.socialBees.repository.TagRepository;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.service.implementation.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Tag tag;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .id(1L)
                .name("Jan")
                .surname("Kowal")
                .username("jjkowal")
                .email("jkowal@gmail.com")
                .birthday(LocalDate.parse("2001-10-10"))
                .isDeleted(false)
                .build();

        tag = Tag.builder()
                .id(1L)
                .name("Test")
                .build();

        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
    }

    @Test
    @DisplayName("JUnit test for followTag method")
    @Transactional
    public void testFollowTag() {
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        given(tagRepository.findById(1L)).willReturn(Optional.of(tag));

        userService.followTag(1L, 1L);

        Set<Tag> followedTags = user.getTags();
        verify(userRepository).save(user);

        assertThat(followedTags.contains(tag)).isTrue();
    }
}
