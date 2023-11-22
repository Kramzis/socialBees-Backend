package com.server.socialBees.service;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Set<Tag> assignTagsToSetFromList(List<String> tags){
        return tagRepository.assignTagsToSetFromList(tags);
    }

    @Override
    @Transactional
    public Tag deleteTagBy(Integer tagId){
        Tag tag = tagRepository.findTagById(tagId);
        tag.setDeleted(true);

        return tagRepository.save(tag);
    }
}
