package com.thinking.robot.domain.tuling.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TuLingRequestData {
    private Integer reqType;
    private Perception perception;
    private UserInfo userInfo;
    
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Perception{
        private InputText inputText;
        private InputImage inputImage;
        private SelfInfo selfInfo;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class InputText{
        private String text;
    
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class InputImage{
        private String url;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SelfInfo{
        private Location location;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Location{
        private String city;
        private String province;
        private String street;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UserInfo{
        private String apiKey;
        private String userId;
        private String groupId;
        private String userIdName;
    }
    
    public static TuLingRequestData getInstance( final UserInfo userInfo){
        final TuLingRequestData tuLingRequestData = new TuLingRequestData();
        tuLingRequestData.setUserInfo(userInfo);
        return tuLingRequestData;
    }
}

