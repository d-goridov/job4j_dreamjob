package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(3);

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Petr", "Candidate for Java Lead", LocalDate.now()));
        candidates.put(2, new Candidate(2, "Dmitriy", "Candidate for Java Junior", LocalDate.now()));
        candidates.put(3, new Candidate(3, "Alexandra", "Candidate for Java Trainee", LocalDate.now()));
    }

    public static CandidateStore getInst() {
        return INST;
    }
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
    public void add(Candidate candidate) {
        int id = counter.incrementAndGet();
        candidate.setId(id);
        candidates.putIfAbsent(id, candidate);
    }
    public Candidate findById(int id) {
        return candidates.get(id);
    }
    public void update(Candidate candidate) {
        int id = candidate.getId();
        candidates.replace(id, candidate);
    }
}
