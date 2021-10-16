package tpp.example.chatserver.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tpp.example.chatserver.service.ChatServerService;
import tpp.example.chatserver.web.model.MessageDto;

@Slf4j
@RestController
public class ChatServerController {

  private final ChatServerService chatServerService;

  @Autowired
  public ChatServerController(ChatServerService chatServerService) {
    this.chatServerService = chatServerService;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/messages/{type}")
  public void addMessage(@PathVariable("type") String type, @RequestBody MessageDto message) {
    chatServerService.handleMessage(message, type);
  }
}
