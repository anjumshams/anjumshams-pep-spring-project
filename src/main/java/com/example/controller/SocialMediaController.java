package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.MessageService;
import com.example.service.AccountService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
@RequestMapping("message")
public class SocialMediaController {
    private MessageService messageService;
    public SocialMediaController(MessageService messageService){
        this.messageService = messageService;
    }


    //1. Process new User registrations.
    //POST localhost:8080/register. 
    @PostMapping(params = "account")
    public @ResponseBody Account addUserAccount(@RequestParam Account account){
        return accountService.addUserAccount(account);
    }

    //2. Process User logins.
    //POST localhost:8080/login.
    @PostMapping(params = "account")
    public @ResponseBody Account UserLogin(@RequestParam Account account){
        return accountService.UserLogin(account);
    }

    //3. Add a new message.
    //POST localhost:8080/messages.
    @PostMapping(params = "message")
    public @ResponseBody Message addMessage(@RequestParam Message message){
        return messageService.addMessage(message);
    }

    //4. Retrieve all messages. 
    //GET request on the endpoint GET localhost:8080/messages.
    @GetMapping
    public @ResponseBody List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    //5. Retrieve a message by its ID.  
    //GET request on the endpoint GET localhost:8080/messages/{messageId}.
    @GetMapping(params = "message_id")
    public @ResponseBody Message getMessageById(@RequestParam int message_id){
        messageService.getMessageById(message_id);
        return messageService.getMessageById(message_id);
    }

    //6. Delete a message by its ID
    //DELETE localhost:8080/messages/{messageId}.
    @DeleteMapping(params = "message_id")
    public @ResponseBody Message deleteMessageById(@RequestParam int message_id){
        Message message = messageService.getMessageById(message_id);
        messageService.deleteMessageById(message_id);
        return message;
    }


    //7. Update a message text identified by a message ID.
    //PATCH request on the endpoint PATCH localhost:8080/messages/{messageId}.
    @PatchMapping(params ="messag_id", "message")
    public ResponseBody Message updateMessageById(@RequestParam int messageId, Message message){
        return messageService.updateMessageById(messageId, message);
    
    }


     //8. Retrieve all messages written by a particular user.
     //GET localhost:8080/accounts/{accountId}/messages.
    @GetMapping(params = "posted_by")
    public @ResponseBody List<Message> getAllMessagesByOneUser(@RequestParam int posted_by){
        return messageService.getAllMessagesByOneUser(posted_by);
        
    }
    

}
   
  
  
   
    


