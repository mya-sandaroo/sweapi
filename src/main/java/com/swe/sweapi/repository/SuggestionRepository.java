package com.swe.sweapi.repository;

import com.swe.sweapi.entity.Suggestion;
import org.springframework.data.repository.CrudRepository;

public interface SuggestionRepository extends CrudRepository<Suggestion, Long> {
}
