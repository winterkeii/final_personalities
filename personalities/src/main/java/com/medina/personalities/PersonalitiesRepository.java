package com.medina.personalities;

import com.medina.personalities.Personalities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalitiesRepository extends JpaRepository<Personalities, Long> {
    // ... your code ...
}