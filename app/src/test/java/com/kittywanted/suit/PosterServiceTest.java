package com.kittywanted.suit;


import com.kittywanted.domain.model.WantedPoster;
import com.kittywanted.domain.ports.PosterService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PosterServiceTest {

    @Test
    void testPosterServiceReturnsEmptyReport(){
        PosterService posterService = new PosterService();
        assertNotNull(posterService.getEmptyPoster());
        assertNotNull(posterService.getEmptyPoster());
        assertNotNull(posterService.getEmptyPoster());
    }

    @Test
    void testSavingPosterAsPdf(){
        PosterService posterService = new PosterService();

        WantedPoster poster = posterService.getEmptyPoster();k

        posterService.saveAsPdf(poster);
    }

}