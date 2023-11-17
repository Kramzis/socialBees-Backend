package com.server.socialBees.initializer;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.repository.TagRepository;
import com.server.socialBees.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TagInitializer implements CommandLineRunner {
    private final WorkRepository workRepository;
    private final TagRepository tagRepository;

    @Autowired
    public TagInitializer(WorkRepository workRepository, TagRepository tagRepository) {
        this.workRepository = workRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeTags();
    }

    private void initializeTags() {
        Tag tagPoems = new Tag();
        tagPoems.setName("Poems");
        tagPoems.setDeleted(false);
        tagRepository.save(tagPoems);

        Tag tagStories = new Tag();
        tagStories.setName("Stories");
        tagStories.setDeleted(false);
        tagRepository.save(tagStories);

        Tag tagMods = new Tag();
        tagMods.setName("Mods");
        tagMods.setDeleted(false);
        tagRepository.save(tagMods);

        Tag tagDrawings = new Tag();
        tagDrawings.setName("Drawings");
        tagDrawings.setDeleted(false);
        tagRepository.save(tagDrawings);

        Tag tagPhotos = new Tag();
        tagPhotos.setName("Photos");
        tagPhotos.setDeleted(false);
        tagRepository.save(tagPhotos);

        Tag tagMusic = new Tag();
        tagMusic.setName("Music");
        tagMusic.setDeleted(false);
        tagRepository.save(tagMusic);

        Tag tagNewbies = new Tag();
        tagNewbies.setName("Newbies");
        tagNewbies.setDeleted(false);
        tagRepository.save(tagNewbies);

        Tag tagPopular = new Tag();
        tagPopular.setName("Popular");
        tagPopular.setDeleted(false);
        tagRepository.save(tagPopular);

        Tag tagSponsored = new Tag();
        tagSponsored.setName("Sponsored");
        tagSponsored.setDeleted(false);
        tagRepository.save(tagSponsored);

        Tag tagPioneer = new Tag();
        tagPioneer.setName("Pioneer");
        tagPioneer.setDeleted(false);
        tagRepository.save(tagPioneer);
    }
}

