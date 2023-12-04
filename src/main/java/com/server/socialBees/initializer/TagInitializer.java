package com.server.socialBees.initializer;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TagInitializer implements CommandLineRunner {
    private final TagRepository tagRepository;

    @Autowired
    public TagInitializer(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(String... args) {
        initializeTags();
    }

    private void initializeTags() {
        if(!tagRepository.findById(1L).isPresent()) {
            Tag tagPoems = new Tag();
            tagPoems.setName("Poems");
            tagRepository.save(tagPoems);

            Tag tagStories = new Tag();
            tagStories.setName("Stories");
            tagRepository.save(tagStories);

            Tag tagMods = new Tag();
            tagMods.setName("Mods");
            tagRepository.save(tagMods);

            Tag tagDrawings = new Tag();
            tagDrawings.setName("Drawings");
            tagRepository.save(tagDrawings);

            Tag tagPhotos = new Tag();
            tagPhotos.setName("Photos");
            tagRepository.save(tagPhotos);

            Tag tagMusic = new Tag();
            tagMusic.setName("Music");
            tagRepository.save(tagMusic);

            Tag tagNewbies = new Tag();
            tagNewbies.setName("Newbies");
            tagRepository.save(tagNewbies);

            Tag tagPopular = new Tag();
            tagPopular.setName("Popular");
            tagRepository.save(tagPopular);

            Tag tagSponsored = new Tag();
            tagSponsored.setName("Sponsored");
            tagRepository.save(tagSponsored);

            Tag tagPioneer = new Tag();
            tagPioneer.setName("Pioneer");
            tagRepository.save(tagPioneer);}
    }
}

