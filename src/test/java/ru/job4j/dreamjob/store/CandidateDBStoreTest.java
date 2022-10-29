package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CandidateDBStoreTest {

    @Test
    public void whenAddCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(1, "Dennis", "Candidate for Java Middle",
                LocalDate.now(), new City(1, "RND"));
        store.add(candidate);
        Candidate candidateDB = store.findById(candidate.getId());
        assertThat(candidateDB.getName()).isEqualTo(candidate.getName());
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(2, "Peter", "Candidate for Java Middle",
                LocalDate.now(), new City(1, "RND"));
        store.add(candidate);
        Candidate updated1 = new Candidate(candidate.getId(), "John", "Candidate for Java Middle",
                LocalDate.now(), new City(2, "SPB"));

        store.update(updated1);
        Candidate candidateDB = store.findById(candidate.getId());
        assertThat(candidateDB.getName()).isEqualTo(updated1.getName());
    }

    @Test
    public void whenFindById() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(3, "Dennis", "Candidate for Java Middle",
                LocalDate.now(), new City(1, "RND"));
        store.add(candidate);
        Candidate candidateDB = store.findById(candidate.getId());
        assertThat(candidateDB).isEqualTo(candidate);
    }

    @Test
    public void whenFindAll() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate1 = new Candidate(4, "Dennis", "Candidate for Java Middle",
                LocalDate.now(), new City(1, "RND"));
        Candidate candidate2 = new Candidate(5, "Oleg", "Candidate for Java Lead",
                LocalDate.now(), new City(2, "SPB"));
        Candidate candidate3 = new Candidate(6, "Elena", "Candidate for Java QA",
                LocalDate.now(), new City(3, "MSK"));
        store.add(candidate1);
        store.add(candidate2);
        store.add(candidate3);
        List<Candidate> candidates = store.findAll();
        assertThat(candidates).contains(candidate1, candidate2, candidate3);
    }
}