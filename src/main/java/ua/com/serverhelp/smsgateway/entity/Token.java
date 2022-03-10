package ua.com.serverhelp.smsgateway.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

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

    public static Token createToken(Account account) {
        Token res=new Token();

        res.setAccount(account);
        res.setExpireAt(Instant.now().plus(2, ChronoUnit.HOURS));
        res.setHash(Base64.getEncoder().encodeToString((account.getNumber()+Instant.now().getEpochSecond()).getBytes()));

        return res;
    }
}
