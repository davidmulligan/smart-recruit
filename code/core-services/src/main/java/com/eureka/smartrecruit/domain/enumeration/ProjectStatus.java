package com.eureka.smartrecruit.domain.enumeration;

import com.eureka.smartrecruit.domain.Project;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProjectStatus {

    PENDING {

        @Override
        public void publish(Project project) {
        }
    },
    PUBLISHED {

        @Override
        public void publish(Project project) {
        }
    };

    public abstract void publish(Project project);
}
