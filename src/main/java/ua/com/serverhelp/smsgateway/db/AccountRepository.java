package ua.com.serverhelp.smsgateway.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.serverhelp.smsgateway.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByNumber(String num);
}
