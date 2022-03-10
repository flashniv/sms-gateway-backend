package ua.com.serverhelp.smsgateway.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.serverhelp.smsgateway.entity.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
