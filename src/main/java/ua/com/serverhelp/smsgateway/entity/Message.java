package ua.com.serverhelp.smsgateway.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Instant receivedAt;
    private String fromNumber;
    private String toNumber;
    private String text;
    @ManyToOne(optional=false)
    @JoinColumn(name="account_id")
    private Account account;
}
