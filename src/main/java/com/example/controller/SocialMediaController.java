package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> addAccountHandler(@RequestBody Account account){
        return ResponseEntity.ok().body(accountService.addAccount(account));
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginHandler(@RequestBody Account account){
        return ResponseEntity.ok().body(accountService.login(account));
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessageHandler(@RequestBody Message message){
        return ResponseEntity.ok().body(messageService.createMessage(message));
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessagesHandler(){
        return ResponseEntity.ok().body(messageService.getAllMessages());
    }
    
    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageByIdHandler(@PathVariable int id){
        Optional<Message> optionalMessage = messageService.getMessageById(id);
        if(optionalMessage.isPresent()){
            return ResponseEntity.ok(optionalMessage.get());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> deleteMessageByIdHandler(@PathVariable int id){
        int rowsAffected = messageService.deleteMessageById(id);
        if(rowsAffected == 1){
            return ResponseEntity.ok().body(rowsAffected);
        }
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/messages/{id}")
    public ResponseEntity<Integer> updateMessageByIdHandler(@PathVariable int id, @RequestBody Message message){
        return ResponseEntity.ok().body(messageService.updateMessageById(id, message));
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUserHandler(@PathVariable int accountId){
        return ResponseEntity.ok(messageService.getAllMessagesByUser(accountId));
    }
}
