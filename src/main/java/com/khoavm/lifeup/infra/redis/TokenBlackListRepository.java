package com.khoavm.lifeup.infra.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TokenBlackListRepository extends CrudRepository<TokenBlackList, String> {

    boolean existsByToken(String token);
}
