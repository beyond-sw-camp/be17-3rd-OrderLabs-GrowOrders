package org.example.groworders.domain.weather.model.dto;

import lombok.*;
import org.example.groworders.domain.weather.model.entity.Weather;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class WeatherDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeatherData {
        private String tm;   // 관측시각
        private String stn;  // 지점번호
        private String ws;   // 풍속
        private String ta;   // 기온
        private String hm;   // 습도
        private String rn;   // 강수량
        private String si;   // 일사량

        public Weather toEntity(double predictedYield) {
            return Weather.builder()
                    .tm(this.tm)
                    .stn(this.stn)
                    .ws(this.ws)
                    .ta(this.ta)
                    .hm(this.hm)
                    .rn(this.rn)
                    .si(this.si)
                    .sowingDate(LocalDate.now())
                    .predictYield(String.valueOf(predictedYield))
                    .build();
        }

        public static WeatherData fromEntity(Weather weather) {
            return WeatherData.builder()
                    .tm(weather.getTm())
                    .stn(weather.getStn())
                    .ws(weather.getWs())
                    .ta(weather.getTa())
                    .hm(weather.getHm())
                    .rn(weather.getRn())
                    .si(weather.getSi())
                    .build();
        }
    }
}