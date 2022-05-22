package com.hingehealth.demo.controllers;

import com.hingehealth.demo.services.TreeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BaseController {

    private TreeService treeService;

    public BaseController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }
//
//    @RequestMapping(value = "/tree", method = RequestMethod.GET)
//    public Map<Integer, Map<String, Object>> getTree() throws Exception {
//        return treeService.createTreeMap();
//    }

    @RequestMapping(value = "/tree")
    public String addNodeToTree(
            @RequestParam Integer parent
    ) throws Exception {
        return treeService.addChildToTree(parent, "meow");
    }
}
