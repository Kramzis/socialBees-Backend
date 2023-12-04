package com.server.socialBees.service;

import com.server.socialBees.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<Tag> getAllTags();
    Set<Tag> assignTagsToSetFromList(List<String> tags);
}
