package com.eureka.smartrecruit.domain.enumeration;

import com.eureka.smartrecruit.domain.Job;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum JobStatus {

    PENDING {

        @Override
        public void publish(Job job) {
        }
    },
    OPEN {

        @Override
        public void publish(Job job) {
        }
    },
    CLOSED {

        @Override
        public void publish(Job job) {
        }
    },
    CANCELLED {

        @Override
        public void publish(Job job) {
        }
    },
    EXPIRED {

        @Override
        public void publish(Job job) {
        }
    },
    DISPUTED {

        @Override
        public void publish(Job job) {
        }
    },
    ARCHIVED {

        @Override
        public void publish(Job job) {
        }
    };

    public abstract void publish(Job job);
}
