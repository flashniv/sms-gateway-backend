package ua.com.serverhelp.smsgateway.rest;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.serverhelp.smsgateway.db.AccountRepository;
import ua.com.serverhelp.smsgateway.db.MessageRepository;
import ua.com.serverhelp.smsgateway.db.TokenRepository;
import ua.com.serverhelp.smsgateway.entity.Account;
import ua.com.serverhelp.smsgateway.entity.Message;
import ua.com.serverhelp.smsgateway.entity.Token;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageRest {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping("")
    ResponseEntity<String> getAllMessages(
            @RequestParam String token
    ){
        Optional<Token> optionalToken=tokenRepository.findByHash(token);
        if(optionalToken.isPresent()) {
            Account account=optionalToken.get().getAccount();
            List<Message> messages=messageRepository.findAllByAccount(account);
            JSONArray jsonArray=new JSONArray(messages);
            return ResponseEntity.ok(jsonArray.toString());
        }
        return ResponseEntity.status(403).body("Access denied");
    }

    @PostMapping("")
    ResponseEntity<String> receiveMessage(
            @RequestBody String message
    ){
        log.info("Receive message:"+message);
        try{
            JSONObject input=new JSONObject(message);
            JSONObject data=input.getJSONObject("data");
            String event_type=data.getString("event_type");
            if (event_type.equals("message.received")){
                Message messageEntity=new Message();
                JSONObject payload=data.getJSONObject("payload");
                String received_at= payload.getString("received_at");
                messageEntity.setReceivedAt(Instant.parse(received_at));
                JSONObject from=payload.getJSONObject("from");
                messageEntity.setFromNumber(from.getString("phone_number"));
                messageEntity.setText(payload.getString("text"));
                JSONArray to=payload.getJSONArray("to");
                JSONObject firstRecipient=to.getJSONObject(0);
                messageEntity.setToNumber(firstRecipient.getString("phone_number"));

                Optional<Account> optionalAccount=accountRepository.findByNumber(firstRecipient.getString("phone_number"));
                if(optionalAccount.isPresent()) {
                    messageEntity.setAccount(optionalAccount.get());
                    messageRepository.save(messageEntity);
                    log.info("Save message id=" + messageEntity.getId());
                }
            }
        }catch (JSONException e){
            log.warn("Error parsing: "+message);
        }
        return ResponseEntity.ok("Success");
    }
}
