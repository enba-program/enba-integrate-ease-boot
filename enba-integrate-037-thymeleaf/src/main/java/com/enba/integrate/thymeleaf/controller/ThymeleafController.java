package com.enba.integrate.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class ThymeleafController {

    @GetMapping("/example")
    public String example(Model model) {
        // 添加一个简单的字符串
        model.addAttribute("message", "Hello, Thymeleaf!");

        // 添加一个整数
        model.addAttribute("number", 1234);

        // 添加一个布尔值
        model.addAttribute("flag", true);

        // 添加一个日期
        model.addAttribute("now", new Date());

        // 添加一个列表
        List<String> list = Arrays.asList("Apple", "Banana", "Cherry");
        model.addAttribute("fruits", list);

        // 添加一个映射
        Map<String, String> map = new HashMap<>();
        map.put("one", "First");
        map.put("two", "Second");
        model.addAttribute("colors", map);

        // 添加一个对象
        Person person = new Person();
        person.setName("Alice");
        person.setAge(30);
        person.setFavoriteFruit(list.get(0));
        model.addAttribute("person", person);

        return "example";
    }

    static class Person {
        private String name;
        private int age;
        private String favoriteFruit;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFavoriteFruit() {
            return favoriteFruit;
        }

        public void setFavoriteFruit(String favoriteFruit) {
            this.favoriteFruit = favoriteFruit;
        }
    }
}