package com.eureka.smartrecruit.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Rating {

    POOR(1),
    BELOW_AVERAGE(2),
    AVERAGE(3),
    GOOD(4),
    EXCELLENT(5);

    private final Integer score;

    public static Rating fromScore(Integer score) {
        if (score != null) {
            switch (score) {
                case 1: return POOR;
                case 2: return BELOW_AVERAGE;
                case 3: return AVERAGE;
                case 4: return GOOD;
                case 5: return EXCELLENT;
                default: return EXCELLENT;
            }
        }
        return null;
    }
}
