package com.learn.employees.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "icon",
        "code"
})

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiConditionBean {
    @JsonProperty("text")
    public String text;
    @JsonProperty("icon")
    public String icon;
    @JsonProperty("code")
    public Integer code;
}
