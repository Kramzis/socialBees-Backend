package com.server.socialBees;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.repository.WorkRepository;
import com.server.socialBees.service.implementation.WorkServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private WorkRepository workRepository;
    @InjectMocks
    private WorkServiceImpl workService;

    private User user;
    private Work work;

    @BeforeEach
    public void setup(){
        user = User.builder()
                .id(1L)
                .name("Jan")
                .surname("Kowalski")
                .username("jjkowal")
                .email("jkowalski@gmail.com")
                .birthday(LocalDate.parse("2001-10-10"))
                .isDeleted(false)
                .build();

        work = Work.builder()
                .id(1L)
                .title("test title")
                .content("test content")
                .isDeleted(false)
                .user(user)
                .build();

        Set<Work> works = new HashSet<>();
        works.add(work);

        user.setWorks(works);
    }

    @Test
    @DisplayName("JUnit test for getNumberOfWorksForUser method")
    public void testGetNumberOfWorksWhenAreWorks() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Long result = workService.getNumberOfWorksForUser(user.getId());

        assertEquals(1L, result);
    }

    @DisplayName("JUnit test for getWorkById method")
    @Test
    public void givenWorkId_whenGetWorkById_thenReturnWorkObject(){
        given(workRepository.findWorkById(work.getId())).willReturn(work);

        Work savedWork = workService.getWorkById(work.getId());

        assertThat(savedWork).isNotNull();
        assertThat(savedWork.isDeleted()).isFalse();
    }
}
