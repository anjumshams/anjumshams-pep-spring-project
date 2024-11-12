package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.MessageService;
import com.example.service.AccountService;
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

public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    //1. Process new User registrations.
    //POST localhost:8080/register. 
    @PostMapping("/register")
    public ResponseEntity<Account> addUserAccount(@RequestBody Account account){
       try{
        Account newAccount = accountService.addUserAccount(account);
        if(newAccount == null){
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(200).body(newAccount);
    }catch (IllegalArgumentException e){
        return ResponseEntity.status(400).build();
    }
    }

    //2. Process User logins.
    //POST localhost:8080/login.
    @PostMapping("/login")
    public ResponseEntity <Account> UserLogin(@RequestBody Account account){
        Account accountLogin = accountService.UserLogin(account);
        if(accountLogin == null){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(accountLogin);
    }

    //3. Add a new message.
    //POST localhost:8080/messages.
    @PostMapping("/messages")
    public ResponseEntity <Message> addMessage(@RequestBody Message message){
        try{
            Message newMessage = messageService.addMessage(message);
            return ResponseEntity.ok(newMessage); 
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).build();
        }   
    }

    //4. Retrieve all messages. 
    //GET request on the endpoint GET localhost:8080/messages.
    @GetMapping("/messages")
    public ResponseEntity <List<Message>> getAllMessages(){
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    //5. Retrieve a message by its ID.  
    //GET request on the endpoint GET localhost:8080/messages/{messageId}.
    @GetMapping("/messages/{messageId}")
    public ResponseEntity <Message> getMessageById(@PathVariable int messageId){
        return ResponseEntity.ok(messageService.getMessageById(messageId));
    }

    //6. Delete a message by its ID
    //DELETE localhost:8080/messages/{messageId}.
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity <Integer> deleteMessageById(@PathVariable int messageId){
        if(messageService.deleteMessageById(messageId)==null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(1);
    }
    
    //7. Update a message text identified by a message ID.
    //PATCH request on the endpoint PATCH localhost:8080/messages/{messageId}.
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity <Integer> updateMessageById(@PathVariable int messageId, @RequestBody Message message){
       Message updatedMessage = messageService.updateMessageById(messageId, message);
       if(updatedMessage == null){
        return ResponseEntity.status(400).build();
       }
       return ResponseEntity.ok(1);
    }

     ///8. Retrieve all messages written by a particular user.
     //GET localhost:8080/accounts/{accountId}/messages.
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity <List<Message>> getAllMessagesByOneUser(@PathVariable int accountId){
        return ResponseEntity.ok(messageService.getAllMessagesByOneUser(accountId));   
    }
}
   
  
  
   
    


