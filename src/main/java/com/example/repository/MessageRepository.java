package com.example.repository;

import com.example.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Though adding repository isn't strictly necessary, it's still helpful to spring boot and could prevent issues on larger projects
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    // Utilizing query method to custom query messages table by foreign key postedBy (-> accountId).
    // This is required for the MessageService method getAllMessagesByUser.

    public List<Message> findAllByPostedBy(int id);
}
