package ua.com.serverhelp.smsgateway.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.serverhelp.smsgateway.entity.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByHash(String hash);
}
