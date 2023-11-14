package edu.whu.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    /**
     * sendMessage(@RequestBody Message message),
     * getMessages(int senderId, int receiverId).
     */
}

