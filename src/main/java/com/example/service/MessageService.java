package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MessageService {
    MessageRepository messageRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //3. Add a new message.
    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    //4. Retrieve all messages. 
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    //5. Retrieve a message by its ID.
    public Message getMessageById(int message_id){
        Optional <Message> optionalMessage = messageRepository.findById(message_id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        return null;
    }

    //6. Delete a message identified by a message ID.
    public void deleteMessageById(int message_id){
        messageRepository.deleteById(message_id);
    }   
    
    
    //7. Update a message text identified by a message ID.
    public Message updateMessageById(int message_id, Message message){
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if(optionalMessage.isEmpty()){
            return null;
        }
        return messageRepository.save(message);
    }

    //8. Retrieve all messages written by a particular user.
    public List<Message> getAllMessagesByOneUser(int posted_by){
        return messageRepository.findAllById(posted_by);
    }      
}
