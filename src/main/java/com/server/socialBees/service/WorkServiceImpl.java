package com.server.socialBees.service;

import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.WorkRepository;
import com.server.socialBees.service.WorkService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkServiceImpl implements WorkService{
    private final WorkRepository workRepository;
    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    @Transactional
    public Work createWork(Work work){return workRepository.save(work);}

    @Override
    @Transactional
    public Work getWorkBy(Integer workId){
        return workRepository.findWorkById(workId);
    }

    @Override
    @Transactional
    public Work updateWork(Work newWork){
        Work work = workRepository.findWorkById(newWork.getId());
        if(work != null){
            work.setTitle(newWork.getTitle());
            work.setContent(newWork.getContent());
            work.setDate(LocalDate.now());
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
