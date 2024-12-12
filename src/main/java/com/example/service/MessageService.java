package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.exceptions.BadRequestException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import lombok.extern.slf4j.Slf4j;

// Service annotation tells Spring this is a service layer in our MVC architecture
// Logger initialized by Lombok. Used log.debug(arg) during debuggin, but have cleared those for now.
@Slf4j
@Service
public class MessageService {

    
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    // All args constructor, dependencies injected by Spring. Using both AccountRepository and MessageRepository in createMessage method.
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // Business logic for User Story 3
    // Our API should be able to process the creation of new messages.
    // Throws exception if messageText is too long or empty, and if it isn't posted by a registered account
    // Exception definitions and handlers are found in the com.example.exception package.
    public Message createMessage(Message message){
        
        String text = message.getMessageText();
        Optional<Account> account = accountRepository.findById(message.getPostedBy());

        if(text.length() > 0 && text.length() < 255 && account.isPresent()){
            return messageRepository.save(message);
        }
        throw new BadRequestException("Messages must be shorter than 255 characters and cannot be empty. Please try again.");
    }

    // Business Logic for User Story 4
    // Our API should be able to retrieve all messages.
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // Business Logic for User Story 5
    // Our API should be able to retrieve a message by its ID.
    public Optional<Message> getMessageById(int id){
        return messageRepository.findById(id);
    }

    // Business Logic for User Story 6
    // Our API should be able to delete a message identified by a message ID.
    public int deleteMessageById(int id){
        int res = 0;
        if(getMessageById(id).isPresent()){
            messageRepository.deleteById(id);
            res++;
        }
        return res;
    }

    // Business Logic for User Story 7
    // Our API should be able to update a message text identified by a message ID.
    // Throws exception if message id can't be found or if messageText doesn't meet requirements
    public int updateMessageById(int id, Message message){
        Optional<Message> optionalMessage = getMessageById(id);
        String text = message.getMessageText();
        if(optionalMessage.isPresent() && text.length() > 0 && text.length() < 255){
            message.setMessageId(id);
            messageRepository.save(message);
            return 1;
        }
        throw new BadRequestException("Message wasn't updated correctly. Check that the message ID is correct and that the message is between 1 and 255 characters.");
    }

    // Business Logic for User Story 8
    // Our API should be able to retrieve all messages written by a particular user.
    // Utilizes custom query method defined in the MessageRepository class.
    public List<Message> getAllMessagesByUser(int accountId){
        return messageRepository.findAllByPostedBy(accountId);
    }
}
