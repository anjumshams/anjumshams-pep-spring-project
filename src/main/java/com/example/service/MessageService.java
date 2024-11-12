package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    //3. Add a new message.
    public Message addMessage(Message message){
        String textOfMessage = message.getMessageText();
        int msgSize = textOfMessage.length(); 
        int accountId = message.getPostedBy();
        if(accountRepository.findById(accountId).isEmpty()|| msgSize<1|| msgSize>255){
            throw new IllegalArgumentException("Invalid entry ");
        }else{
            return messageRepository.save(message);
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
        if(textSize<1 || textSize>255){
            return null;
        }
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if(optionalMessage.isEmpty()){
            return null;
        }else{
           Message updateMessage = optionalMessage.get();
           String newMsgText = message.getMessageText();
            updateMessage.setMessageText(newMsgText);
            return messageRepository.save(updateMessage);
        }
    }

    //8. Retrieve all messages written by a particular user.
    public List<Message> getAllMessagesByOneUser(int accountId){
        return messageRepository.findAllByPostedBy(accountId);
    }      
}
