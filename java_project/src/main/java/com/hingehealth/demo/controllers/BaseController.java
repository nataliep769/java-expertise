package com.hingehealth.demo.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hingehealth.demo.models.Node;
import com.hingehealth.demo.services.TreeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BaseController {

    private TreeService treeService;

    public BaseController (TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/tree")
    public Node getTree() {
        return treeService.buildTree();
    }
}