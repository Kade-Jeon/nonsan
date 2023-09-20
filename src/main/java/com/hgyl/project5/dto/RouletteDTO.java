package com.hgyl.project5.dto;

import com.hgyl.project5.entity.Roulette;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class RouletteDTO {

    private String uid;

    @Min(value = 0, message = "point 는 음수 불가.")
    private Long point;

    private String nickName;

    private Integer count;

        @Builder
        public RouletteDTO(String uid, Long point, String nickName, Integer count) {
            this.uid = uid;
            this.point = point;
            this.nickName = nickName;
            this.count = count;
        }

        public static RouletteDTO pointFactory(Roulette roulette){
           return RouletteDTO.builder()
                   .uid(roulette.getUid())
                   .point(roulette.getPoint())
                   .nickName(roulette.getNickName())
                   .count(roulette.getCount())
                   .build();
       }




}
