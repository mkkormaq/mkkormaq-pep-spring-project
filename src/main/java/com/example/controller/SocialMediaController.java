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

    // AllArgsConstructor for our controller. Service dependencies injected via Spring.
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    // Handler for User Story 1
    // Our API should be able to process new User registrations.
    @PostMapping("/register")
    public ResponseEntity<Account> addAccountHandler(@RequestBody Account account){
        return ResponseEntity.ok().body(accountService.addAccount(account));
    }

    // Handler for User Story 2
    // Our API should be able to process User logins.
    @PostMapping("/login")
    public ResponseEntity<Account> loginHandler(@RequestBody Account account){
        return ResponseEntity.ok().body(accountService.login(account));
    }

    // Handler for User Story 3
    // Our API should be able to process the creation of new messages.
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessageHandler(@RequestBody Message message){
        return ResponseEntity.ok().body(messageService.createMessage(message));
    }

    // Handler for User Story 4
    // Our API should be able to retrieve all messages.
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessagesHandler(){
        return ResponseEntity.ok().body(messageService.getAllMessages());
    }
    
    // Handler for User Story 5
    // Our API should be able to retrieve a message by its ID.
    // Includes conditional logic to send successful response with empty body if the message isn't found.
    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageByIdHandler(@PathVariable int id){
        Optional<Message> optionalMessage = messageService.getMessageById(id);
        if(optionalMessage.isPresent()){
            return ResponseEntity.ok(optionalMessage.get());
        }
        return ResponseEntity.ok().build();
    }

    // Handler for User Story 6
    // Our API should be able to delete a message identified by a message ID.
    // Includes conditional logic to send successful response with empty body if the message isn't found.
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> deleteMessageByIdHandler(@PathVariable int id){
        int rowsAffected = messageService.deleteMessageById(id);
        if(rowsAffected == 1){
            return ResponseEntity.ok().body(rowsAffected);
        }
        return ResponseEntity.ok().build();
    }
    
    // Handler for User Story 7
    // Our API should be able to update a message text identified by a message ID.
    @PatchMapping("/messages/{id}")
    public ResponseEntity<Integer> updateMessageByIdHandler(@PathVariable int id, @RequestBody Message message){
        return ResponseEntity.ok().body(messageService.updateMessageById(id, message));
    }

    // Handler for User Story 8
    // Our API should be able to retrieve all messages written by a particular user.
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUserHandler(@PathVariable int accountId){
        return ResponseEntity.ok(messageService.getAllMessagesByUser(accountId));
    }
}
