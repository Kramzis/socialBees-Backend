package com.server.socialBees.service.implementation;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.repository.TagRepository;
import com.server.socialBees.service.TagService;
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

}
