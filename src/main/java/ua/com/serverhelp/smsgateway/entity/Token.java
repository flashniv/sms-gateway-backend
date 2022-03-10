package ua.com.serverhelp.smsgateway.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional=false)
    @JoinColumn (name="account_id")
    private Account account;
    private Instant expireAt;
    private String hash;
}
