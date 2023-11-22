package com.swe.sweapi.repository;

import com.swe.sweapi.entity.SWEUser;
import org.springframework.data.repository.CrudRepository;

public interface SWEUserRepository extends CrudRepository<SWEUser, Long> {
    SWEUser findByLoginIdAndLoginPassword(String loginId, String loginPassword);
    SWEUser findByLoginId(String loginId);
}
