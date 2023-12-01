package com.server.socialBees.service.implementation;

import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.WorkRepository;
import com.server.socialBees.service.WorkService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;
    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    @Transactional
    public Work createWork(Work work){
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

    public List<Work> getRecentWorks() {
        LocalDate last30Days = LocalDate.now().minusDays(30);

        return workRepository.findRecentWorks(last30Days);
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
}
