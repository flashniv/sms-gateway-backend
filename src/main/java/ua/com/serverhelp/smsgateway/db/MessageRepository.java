package ua.com.serverhelp.smsgateway.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.serverhelp.smsgateway.entity.Account;
import ua.com.serverhelp.smsgateway.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByAccount(Account account);
}
