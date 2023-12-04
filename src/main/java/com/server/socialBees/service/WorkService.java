package com.server.socialBees.service;

import com.server.socialBees.entity.Work;

import java.util.List;

public interface WorkService {
    Work createWork(Work work);
    Work updateWork(Work newWork);
    Work deleteWorkById(Long workId);
    Work getWorkById(Long workId);
    List<Work> getWorksForUser(Long userId);
    Long getNumberOfWorksForUser(Long userId);
    List<Work> getWorksByFollowedTagsForUser(Long userId);
    List<Work> getRecentWorks();
}
