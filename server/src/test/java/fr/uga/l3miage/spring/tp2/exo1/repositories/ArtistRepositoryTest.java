package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.AstUtils;

import java.util.Set;

import static fr.uga.l3miage.exo1.enums.GenreMusical.RAP;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testRequestcountByGenreMusicalEquals(){
        //given
        ArtistEntity artistEntity = ArtistEntity
                .builder()
                .name("Nicolas")
                .biography("Prof JPA")
                .genreMusical(RAP)
                .build();

        ArtistEntity artistEntity2 = ArtistEntity
                .builder()
                .name("Alexendre")
                .biography("Prof VA/IHM")
                .genreMusical(RAP)
                .build();

        artistRepository.save(artistEntity);
        artistRepository.save(artistEntity2);

        // when
        int artistEntitiesResponses = artistRepository.countByGenreMusicalEquals(RAP);

        //then
        assertThat(artistEntitiesResponses).isEqualTo(2);
    }
}