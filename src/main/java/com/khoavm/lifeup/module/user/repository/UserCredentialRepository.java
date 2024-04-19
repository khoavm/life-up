package com.khoavm.lifeup.module.user.repository;

import com.khoavm.lifeup.config.security.OAuth2Provider;
import com.khoavm.lifeup.module.user.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, UUID> {
    Optional<UserCredential> findByProviderAndUser_Id(@NonNull OAuth2Provider provider, @NonNull UUID id);

}