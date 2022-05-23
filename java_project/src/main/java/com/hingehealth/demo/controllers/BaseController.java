package com.hingehealth.demo.controllers;

import com.hingehealth.demo.models.NodeRequest;
import com.hingehealth.demo.services.TreeService;
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
    public List<Map<Integer, Map<String, Object>>> getTree() throws Exception {
        return Arrays.asList(treeService.getTree());
    }

    @PostMapping(value = "/tree")
    public String addNodeToTree(
            @RequestBody NodeRequest nodeRequest
    ) throws Exception {
        return treeService.addNodeToTree(nodeRequest);
    }
}
