package miu.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetdataController {
    @GetMapping("/metrics")
    public String getMetrics() {
        // TODO: Implement Netdata API call and data processing
        return "Hello, world!";
    }
}
