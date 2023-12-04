package com.server.socialBees.service.implementation;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.repository.WorkRepository;
import com.server.socialBees.service.WorkService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;
    private final UserRepository userRepository;
    public WorkServiceImpl(WorkRepository workRepository, UserRepository userRepository) {
        this.workRepository = workRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Work createWork(Work work){
        return workRepository.save(work);
    }

    @Override
    @Transactional
    public Work updateWork(Work newWork) {
        Work work = workRepository.findWorkById(newWork.getId());
        if(work != null){
            work.setTitle(newWork.getTitle());
            work.setContent(newWork.getContent());
            work.setDate(LocalDate.now());
            work.setFilesDB(new ArrayList<>());

            return workRepository.save(work);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Work deleteWorkById(Long workId){
        Work work = workRepository.findWorkById(workId);
        work.setDeleted(true);

        return workRepository.save(work);
    }

    @Override
    @Transactional
    public Work getWorkById(Long workId){
        Work work = workRepository.findWorkById(workId);
        if(work.isDeleted()){
            return null;
        } else {
            return work;
        }
    }

    @Override
    public List<Work> getWorksForUser(Long userId) {
        User user = userRepository.findById(userId).get();

        List<Work> sortedWorks = user.getWorks()
                .stream()
                .filter(work -> !work.isDeleted())
                .sorted(Comparator.comparing(Work::getId))
                .collect(Collectors.toList());

        return sortedWorks;
    }

    @Override
    public Long getNumberOfWorksForUser(Long userId){
        User user = userRepository.findById(userId).get();
        return (long) user.getWorks().size();
    }

    @Override
    public List<Work> getWorksByFollowedTagsForUser(Long userId) {
        User user = userRepository.findById(userId).get();
        Set<Tag> tags = user.getTags();
        return workRepository.findByTagsIn(tags);
    }

    @Override
    public List<Work> getRecentWorks() {
        LocalDate last30Days = LocalDate.now().minusDays(30);

        return workRepository.findRecentWorks(last30Days);
    }
}
