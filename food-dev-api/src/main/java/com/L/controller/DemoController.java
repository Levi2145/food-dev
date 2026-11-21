package com.L.controller;

import com.L.mapper.CategoryMapper;
import com.L.mapper.ItemsMapper;
import com.L.mapper.UsersMapper;
import com.L.pojo.Category;
import com.L.pojo.Items;
import com.L.pojo.Users;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/** 面向前端的单体商城接口：页面的每个模块都从这里取数。 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class DemoController {
    @Autowired private ItemsMapper itemsMapper;
    @Autowired private CategoryMapper categoryMapper;
    @Autowired private UsersMapper usersMapper;
    @Autowired private Environment environment;

    private final Map<String, List<Map<String, Object>>> carts = new ConcurrentHashMap<String, List<Map<String, Object>>>();
    private final Map<String, List<Map<String, Object>>> addresses = new ConcurrentHashMap<String, List<Map<String, Object>>>();
    private final Map<String, Map<String, Object>> orders = new ConcurrentHashMap<String, Map<String, Object>>();
    private final Map<String, List<Map<String, Object>>> comments = new ConcurrentHashMap<String, List<Map<String, Object>>>();

    @GetMapping("/health")
    public Map<String, Object> health() {
        String url = environment.getProperty("spring.datasource.url", "");
        return map("status", "UP", "database", url.startsWith("jdbc:mysql:") ? "MySQL" : "H2-memory");
    }

    @GetMapping("/home")
    public Map<String, Object> home(@RequestParam(defaultValue = "1") long current, @RequestParam(defaultValue = "8") long size) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("carousel", carousel()); result.put("categories", categories()); result.put("items", items(current, size, null));
        return result;
    }

    @GetMapping("/carousel")
    public List<Map<String, Object>> carousel() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map("id", 1, "title", "春日鲜果季", "subtitle", "产地直采，满 99 减 20", "color", "#dcefe0", "emoji", "🍎"));
        list.add(map("id", 2, "title", "每日蔬菜新鲜到", "subtitle", "今日下单，明日送达", "color", "#f8ead9", "emoji", "🥬"));
        list.add(map("id", 3, "title", "一分钱模拟支付", "subtitle", "下单即可体验完整流程", "color", "#e9e4f5", "emoji", "🛒"));
        return list;
    }

    @GetMapping("/categories")
    public List<Category> categories() { return categoryMapper.selectList(null); }

    @GetMapping("/items")
    public IPage<Items> items(@RequestParam(defaultValue = "1") long current,
                              @RequestParam(defaultValue = "10") long size,
                              @RequestParam(required = false) String keyword) {
        QueryWrapper<Items> query = new QueryWrapper<Items>().orderByDesc("created_time");
        if (keyword != null && !keyword.trim().isEmpty()) query.like("item_name", keyword.trim());
        return itemsMapper.selectPage(new Page<Items>(current, Math.min(size, 50)), query);
    }

    @GetMapping("/items/{id}")
    public Map<String, Object> item(@PathVariable String id) {
        Items item = itemsMapper.selectById(id);
        return map("item", item, "images", Collections.singletonList(item == null ? "" : item.getMainImg()), "specs", Collections.singletonList(map("id", id + "-default", "name", "标准装", "price", item == null ? BigDecimal.ZERO : item.getPrice(), "stock", 100)), "comments", comments.getOrDefault(id, Collections.<Map<String, Object>>emptyList()));
    }

    @PostMapping("/auth/register")
    public Users register(@RequestBody Users u) { if (u.getId() == null) u.setId(UUID.randomUUID().toString()); usersMapper.insert(u); return u; }

    @PostMapping("/auth/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        Users user = usersMapper.selectOne(new QueryWrapper<Users>().eq("username", body.get("username")));
        if (user == null || (body.get("password") != null && !body.get("password").equals(user.getPassword()))) return map("success", false, "message", "用户名或密码错误");
        return map("success", true, "token", user.getId(), "user", user);
    }

    @GetMapping("/cart")
    public List<Map<String, Object>> cart(@RequestParam(defaultValue = "guest") String userId) { return carts.getOrDefault(userId, new ArrayList<Map<String, Object>>()); }

    @PostMapping("/cart")
    public List<Map<String, Object>> addCart(@RequestParam(defaultValue = "guest") String userId, @RequestBody Map<String, Object> body) {
        String itemId = String.valueOf(body.get("itemId")); int quantity = body.get("quantity") == null ? 1 : Integer.parseInt(String.valueOf(body.get("quantity")));
        Items item = itemsMapper.selectById(itemId); if (item == null) return cart(userId);
        List<Map<String, Object>> list = carts.computeIfAbsent(userId, k -> new ArrayList<Map<String, Object>>());
        for (Map<String, Object> row : list) if (itemId.equals(row.get("itemId"))) { row.put("quantity", ((Integer) row.get("quantity")) + quantity); return list; }
        list.add(map("itemId", itemId, "itemName", item.getItemName(), "price", item.getPrice(), "image", item.getMainImg(), "quantity", quantity)); return list;
    }

    @DeleteMapping("/cart/{itemId}")
    public List<Map<String, Object>> removeCart(@RequestParam(defaultValue = "guest") String userId, @PathVariable String itemId) { List<Map<String, Object>> list = carts.getOrDefault(userId, new ArrayList<Map<String, Object>>()); list.removeIf(row -> itemId.equals(row.get("itemId"))); return list; }

    @GetMapping("/addresses")
    public List<Map<String, Object>> addressList(@RequestParam(defaultValue = "guest") String userId) { return addresses.getOrDefault(userId, new ArrayList<Map<String, Object>>()); }

    @PostMapping("/addresses")
    public List<Map<String, Object>> addAddress(@RequestParam(defaultValue = "guest") String userId, @RequestBody Map<String, Object> body) { Map<String, Object> address = new LinkedHashMap<String, Object>(body); address.put("id", UUID.randomUUID().toString()); address.put("createdTime", LocalDateTime.now().toString()); addresses.computeIfAbsent(userId, k -> new ArrayList<Map<String, Object>>()).add(address); return addressList(userId); }

    @PostMapping("/orders")
    public Map<String, Object> createOrder(@RequestParam(defaultValue = "guest") String userId, @RequestBody Map<String, Object> body) { String orderId = "FD" + System.currentTimeMillis(); Map<String, Object> order = new LinkedHashMap<String, Object>(body); order.put("orderId", orderId); order.put("userId", userId); order.put("status", 10); order.put("createdTime", LocalDateTime.now().toString()); orders.put(orderId, order); carts.remove(userId); return order; }

    @GetMapping("/orders")
    public List<Map<String, Object>> orderList(@RequestParam(defaultValue = "guest") String userId) { List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); for (Map<String, Object> order : orders.values()) if (userId.equals(order.get("userId"))) result.add(order); return result; }

    @GetMapping("/comments/{itemId}")
    public List<Map<String, Object>> commentList(@PathVariable String itemId) { return comments.getOrDefault(itemId, new ArrayList<Map<String, Object>>()); }

    @PostMapping("/comments/{itemId}")
    public List<Map<String, Object>> addComment(@PathVariable String itemId, @RequestBody Map<String, Object> body) { Map<String, Object> comment = new LinkedHashMap<String, Object>(body); comment.put("createdTime", LocalDateTime.now().toString()); comments.computeIfAbsent(itemId, k -> new ArrayList<Map<String, Object>>()).add(comment); return commentList(itemId); }

    private static Map<String, Object> map(Object... values) { Map<String, Object> result = new LinkedHashMap<String, Object>(); for (int i = 0; i + 1 < values.length; i += 2) result.put(String.valueOf(values[i]), values[i + 1]); return result; }
}
