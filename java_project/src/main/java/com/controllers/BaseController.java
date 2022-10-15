package com.controllers;

import com.models.NodeRequest;
import com.services.TreeService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BaseController {

    private final TreeService treeService;

    public BaseController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping(value = "/tree")
    public List<Map<Integer, Map<String, Object>>> getTree() throws NotFoundException {
        return Arrays.asList(treeService.getTree());
    }

    @PostMapping(value = "/tree")
    public String addNodeToTree(
            @RequestBody NodeRequest nodeRequest
    ) throws NotFoundException {
        return treeService.addNodeToTree(nodeRequest);
    }
}
