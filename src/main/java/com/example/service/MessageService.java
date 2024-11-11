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
        String textOfMessage = message.getMessageText();
        int size = textOfMessage.length(); 
        if (size>0 && size<=255){
        return messageRepository.save(message);
    }else{ 
            return null; 
        } 
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
    public Integer deleteMessageById(int message_id){
        if(getMessageById(message_id)==null){
            return null;
        }
        
        messageRepository.deleteById(message_id);
        return 1;
    }   
    
    //7. Update a message text identified by a message ID.
    public Message updateMessageById(int message_id, Message message){
        String textOfMessage = message.getMessageText();
        int textSize = textOfMessage.length();
        if(textSize<0 || textSize>255){
            return null;
        }
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if(optionalMessage.isEmpty()){
            return null;
        }else{
            optionalMessage.get().setMessageText(message.getMessageText());
            return messageRepository.save(optionalMessage.get());

        }
    
    }

    //8. Retrieve all messages written by a particular user.
    public List<Message> getAllMessagesByOneUser(int posted_by){
        return messageRepository.findAllByPostedBy(posted_by);
    }      
}
