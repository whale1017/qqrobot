package com.thinking.robot.domain.facade.leetcode.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinking.robot.domain.facade.leetcode.data.Question;
import com.thinking.robot.domain.facade.leetcode.service.LeetCodeService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class LeetCodeServiceImpl implements LeetCodeService {
    
    private static final String LEET_CODE_PREFIX = "https://leetcode-cn.com/graphql/";
    
    private static final String REQUEST_CONTENT_TEMPLATE = "{\"operationName\":\"questionData\",\"variables\":{\"titleSlug\":\"xun-bao\"},\"query\":\"query questionData($titleSlug: String!) {\\n  question(titleSlug: $titleSlug) {\\n    questionId\\n    questionFrontendId\\n    boundTopicId\\n    title\\n    titleSlug\\n    content\\n    translatedTitle\\n    translatedContent\\n    difficulty\\n}\\n}\\n\"}";
    
    private static final String TODAY_RECORD_QUESTION = "{\"operationName\":\"questionOfToday\",\"variables\":{},\"query\":\"query questionOfToday {\\n  todayRecord {\\n    question {\\n      questionFrontendId\\n      questionTitleSlug\\n      __typename\\n    }\\n    lastSubmission {\\n      id\\n      __typename\\n    }\\n    date\\n    userStatus\\n    __typename\\n  }\\n}\\n\"}";
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public Question getQuestionByTitleSlug(final String titleSlug) {
        final QueryRequest request;
        try {
            request = MAPPER.readValue(REQUEST_CONTENT_TEMPLATE, QueryRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        
        request.getVariables().setTitleSlug(titleSlug);
        
        final QueryResult result = restTemplate.postForObject(
                LEET_CODE_PREFIX,
                request,
                QueryResult.class);
        if(result != null && result.getData() != null && result.getData().getQuestion() != null){
            return result.getData().getQuestion();
        } else {
            return null;
        }
    }
    
    @Override
    public Question getTodayQuestion() {
        final QueryRequest request;
        try {
            request = MAPPER.readValue(TODAY_RECORD_QUESTION, QueryRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        
        final QueryResult result = restTemplate.postForObject(
                LEET_CODE_PREFIX,
                request,
                QueryResult.class);
        if(result != null && result.getData() != null && !CollectionUtils.isEmpty(result.getData().getTodayRecord()) && result.getData().getTodayRecord().get(0).getQuestion() != null){
            final String titleSlug = result.getData().getTodayRecord().get(0).getQuestion().getQuestionTitleSlug();
            return getQuestionByTitleSlug(titleSlug);
        } else {
            return null;
        }
    }
    
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class QueryRequest{
        private String operationName;
        private Variable variables;
        private String query;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Variable{
        private String titleSlug;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class QueryResult{
        private QueryResultData data;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class QueryResultData{
        private Question question;
        private List<TodayRecord> todayRecord;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class TodayRecord{
        private QuestionIndex question;
        private String date;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class QuestionIndex{
        private String questionFrontendId;
        private String questionTitleSlug;
        @JsonProperty("__typename")
        private String typename;
    }
    
}
