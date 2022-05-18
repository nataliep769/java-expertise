package com.hingehealth.demo.controllers;

import com.hingehealth.demo.services.TreeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public List<Map<Integer, Map<String, Object>>> getTree() {
        return Arrays.asList(treeService.createTree());
    }
}
