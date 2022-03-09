package ua.com.serverhelp.smsgateway.rest;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageRest {

    @GetMapping("")
    ResponseEntity<String> getAllMessages(
            @RequestParam String token
    ){
        return ResponseEntity.ok(new JSONObject().toString());
    }

    @PostMapping("")
    ResponseEntity<String> receiveMessage(
            @RequestBody String message
    ){
        log.info("Receive message:"+message);
        return ResponseEntity.ok("Success");
    }
}
