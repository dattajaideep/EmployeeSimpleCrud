package com.learn.employees.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "region",
        "country",
        "lat",
        "lon",
        "tz_id",
        "localtime_epoch",
        "localtime"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiLocation {
    @JsonProperty("name")
    public String name;
    @JsonProperty("region")
    public String region;
    @JsonProperty("country")
    public String country;
    @JsonProperty("lat")
    public Double lat;
    @JsonProperty("lon")
    public Double lon;
    @JsonProperty("tz_id")
    public String tzId;
    @JsonProperty("localtime_epoch")
    public Integer localtimeEpoch;
    @JsonProperty("localtime")
    public String localtime;
}
