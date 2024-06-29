package com.learn.employees.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "location",
        "current"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiBean {
    @JsonProperty("location")
    public WeatherApiLocation location;
    @JsonProperty("current")
    public WeatherApiLive current;
    public int employeeId;
}
