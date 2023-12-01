package com.server.socialBees.service;

import com.server.socialBees.entity.Work;

import java.util.Date;
import java.util.List;

public interface WorkService {
    Work createWork(Work work);
    Work getWorkById(Long workId);
    List<Work> getRecentWorks();
    Work updateWork(Work newWork);
    Work deleteWorkById(Long workId);
}
