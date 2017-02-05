package com.eureka.smartrecruit.domain.enumeration;

import com.eureka.smartrecruit.domain.Job;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum JobStatus {

    PENDING {

        @Override
        public void open(Job job) {
            job.setStatus(OPEN);
        }

        @Override
        public void close(Job job) {
            job.setStatus(CLOSED);
        }

        @Override
        public void cancel(Job job) {
            job.setStatus(CANCELLED);
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a pending job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }
    },
    OPEN {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("Job is already opened.");
        }

        @Override
        public void close(Job job) {
            job.setStatus(CLOSED);
        }

        @Override
        public void cancel(Job job) {
            job.setStatus(CANCELLED);
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a open job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }
    },
    CLOSED {

        @Override
        public void open(Job job) {
            job.setStatus(OPEN);
        }

        @Override
        public void close(Job job) {
            throw new IllegalStateException("Job is already closed.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("You can not cancel a closed job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a closed job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }
    },
    CANCELLED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open a cancelled job.");
        }

        @Override
        public void close(Job job) {
            throw new IllegalStateException("You can not close a cancelled job.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("Job is already cancelled.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a cancelled job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }
    },
    EXPIRED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open an expired job.");
        }

        @Override
        public void close(Job job) {
            throw new IllegalStateException("You can not close an expired job.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("You can not cancel an expired job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute an expired job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }
    },
    DISPUTED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open a disputed job.");
        }

        @Override
        public void close(Job job) {
            throw new IllegalStateException("You can not close a disputed job.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("You can not cancel a disputed job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("Job is already disputed.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }
    },
    ARCHIVED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not close an archived job.");
        }

        @Override
        public void close(Job job) {
            throw new IllegalStateException("You can not close an archived job.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("You can not cancel an archived job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute an archived job.");
        }

        @Override
        public void archive(Job job) {
            throw new IllegalStateException("Job is already archived.");
        }
    };

    public abstract void open(Job job);
    public abstract void close(Job job);
    public abstract void cancel(Job job);
    public abstract void dispute(Job job);
    public abstract void archive(Job job);
}
