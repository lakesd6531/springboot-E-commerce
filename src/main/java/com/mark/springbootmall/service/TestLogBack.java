package com.mark.springbootmall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogBack {
    private static final Logger logger = LoggerFactory.getLogger(TestLogBack.class);

    public static void main(String[] args) {
        logger.warn("Example log from {}", TestLogBack.class.getSimpleName());
    }
}
