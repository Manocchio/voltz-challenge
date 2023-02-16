package com.challenge.voltz.factory;

import com.challenge.voltz.application.web.payloads.requests.CreateToolRequestV1;

import java.util.List;

public class RequestFactory {

    public static CreateToolRequestV1 createToolRequestSample() {
        return new CreateToolRequestV1(
                "default_title",
                "http://fakeurl.com",
                "cats can fly",
                List.of("aa", "bb")
        );
    }

    public static CreateToolRequestV1 duplicatedTagsCreateToolRequestSample() {
        return new CreateToolRequestV1(
                "default_title",
                "www",
                "description",
                List.of("aa","aa")
        );
    }

    public static CreateToolRequestV1 overTagLimitCreateToolSample(){
        return new CreateToolRequestV1(
                "default_title",
                "www",
                "description",
                List.of("the","one","piece","is","real","tell","them","that","!")
        );
    }

    public static CreateToolRequestV1 overDescriptionLimitCreateToolSample(){
        return new CreateToolRequestV1(
                "default_title",
                "www",
                "a very cool description a very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool descriptiona very cool description",
                List.of("the","one","piece","is","real")
        );
    }


}
