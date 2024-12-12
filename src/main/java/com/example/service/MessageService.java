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


@Slf4j
@Service
public class MessageService {

    
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message){
        
        String text = message.getMessageText();
        Optional<Account> account = accountRepository.findById(message.getPostedBy());

        if(text.length() > 0 && text.length() < 255 && account.isPresent()){
            return messageRepository.save(message);
        }
        throw new BadRequestException("Messages must be shorter than 255 characters and cannot be empty. Please try again.");
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(int id){
        return messageRepository.findById(id);
    }

    public int deleteMessageById(int id){
        int res = 0;
        if(getMessageById(id).isPresent()){
            messageRepository.deleteById(id);
            res++;
        }
        return res;
    }

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

    public List<Message> getAllMessagesByUser(int accountId){
        return messageRepository.findAllByPostedBy(accountId);
    }
}
