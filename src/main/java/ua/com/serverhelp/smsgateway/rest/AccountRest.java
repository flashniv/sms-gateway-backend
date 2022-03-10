package ua.com.serverhelp.smsgateway.rest;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.serverhelp.smsgateway.db.AccountRepository;
import ua.com.serverhelp.smsgateway.db.TokenRepository;
import ua.com.serverhelp.smsgateway.entity.Account;
import ua.com.serverhelp.smsgateway.entity.Token;

import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/account")
public class AccountRest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody Account account
    ) {
        Optional<Account> optionalAccount=accountRepository.findByNumber(account.getNumber());
        if (optionalAccount.isPresent()) {
            Account accountFromDB=optionalAccount.get();
            if(accountFromDB.getPassword().equals(account.getPassword())){
                Token token = Token.createToken(accountFromDB);
                tokenRepository.save(token);
                return ResponseEntity.ok(new JSONObject(token).toString());
            }
            return ResponseEntity.status(403).body("Access denied");
        }
        return ResponseEntity.status(404).body("Not found");
    }
}
