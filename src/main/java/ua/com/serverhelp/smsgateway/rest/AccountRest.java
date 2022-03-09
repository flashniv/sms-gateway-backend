package ua.com.serverhelp.smsgateway.rest;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.serverhelp.smsgateway.entity.Account;
import ua.com.serverhelp.smsgateway.entity.Token;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountRest {

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody Account account
    ) {
        Token token = new Token();
        return ResponseEntity.ok(new JSONObject(token).toString());
    }
}
