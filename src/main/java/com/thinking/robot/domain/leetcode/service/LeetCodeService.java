package com.thinking.robot.domain.leetcode.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinking.robot.domain.leetcode.data.Question;
import com.thinking.robot.domain.leetcode.service.impl.LeetCodeServiceImpl;

public interface LeetCodeService {
    
    Question getQuestionByTitleSlug(final String titleSlug);
    
    Question getTodayQuestion();
}
