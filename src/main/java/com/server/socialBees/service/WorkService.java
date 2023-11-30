package com.server.socialBees.service;

import com.server.socialBees.entity.Work;

public interface WorkService {
    Work createWork(Work work);
    Work getWorkById(Long workId);
    Work updateWork(Work newWork);
    Work deleteWorkById(Long workId);
}
