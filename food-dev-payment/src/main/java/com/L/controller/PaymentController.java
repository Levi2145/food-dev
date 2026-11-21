package com.L.controller;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final Map<String, Map<String,Object>> orders = new ConcurrentHashMap<String, Map<String,Object>>();
    @PostMapping("/create")
    public Map<String,Object> create(@RequestParam String orderId, @RequestParam(defaultValue="0.01") BigDecimal amount) {
        Map<String,Object> result = new LinkedHashMap<String,Object>(); result.put("orderId", orderId); result.put("amount", amount); result.put("status", "WAIT_PAY"); result.put("qrCode", "mockpay://food/" + orderId + "?amount=" + amount); orders.put(orderId, result); return result;
    }
    @PostMapping("/mock-success/{orderId}")
    public Map<String,Object> success(@PathVariable String orderId) { Map<String,Object> r = orders.get(orderId); if (r == null) { r = new LinkedHashMap<String,Object>(); r.put("orderId", orderId); } r.put("status", "SUCCESS"); r.put("paidAt", new Date()); orders.put(orderId, r); return r; }
    @GetMapping("/status/{orderId}")
    public Map<String,Object> status(@PathVariable String orderId) { return orders.getOrDefault(orderId, Collections.<String,Object>singletonMap("status", "NOT_FOUND")); }
}
