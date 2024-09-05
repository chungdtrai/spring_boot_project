package com.chung.jpapaging_demo.service;

import java.util.Map;
import java.util.Objects;

public interface ThymeleafService {
    String createContent(String template, Map<String, Object> variables);
}
