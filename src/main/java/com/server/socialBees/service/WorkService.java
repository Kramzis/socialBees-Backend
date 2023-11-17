package com.server.socialBees.service;

import com.server.socialBees.entity.Work;
public interface WorkService {
    Work createWork(Work work);
    Work getWorkBy(Integer workId);
    Work updateWork(Work newWork);
    Work deleteWorkBy(Integer workId);
}
