package com.server.socialBees.service;

import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.WorkRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class WorkServiceImpl implements WorkService{
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
    public Work getWorkBy(Integer workId){
        Work work = workRepository.findWorkById(workId);
        if(work.isDeleted()){
            return null;
        } else {
            return work;
        }
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
    public Work deleteWorkBy(Integer workId){
        Work work = workRepository.findWorkById(workId);
        work.setDeleted(true);

        return workRepository.save(work);
    }
}
