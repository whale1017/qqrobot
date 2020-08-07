package com.thinking.robot.domain.facade.leetcode.service;

import com.thinking.robot.domain.facade.leetcode.data.Question;

public interface LeetCodeService {
    
    Question getQuestionByTitleSlug(final String titleSlug);
    
    Question getTodayQuestion();
}
