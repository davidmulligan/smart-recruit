package com.eureka.smartrecruit.domain.enumeration;

import com.eureka.smartrecruit.domain.Job;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum JobStatus {

    PENDING {

        @Override
        public void open(Job job) {
            job.setStatus(OPENED);
        }

        @Override
        public void reject(Job job) {
            job.setStatus(REJECTED);
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("You can not negotiate a pending job.");
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("You can not start a pending job.");
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("You can not finish a pending job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a pending job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }

        @Override
        public void expire(Job job) {
            job.setStatus(EXPIRED);
        }

        @Override
        public void cancel(Job job) {
            job.setStatus(CANCELLED);
        }
    },
    OPENED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("Job is already opened.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("You can not reject an open job.");
        }

        @Override
        public void negotiate(Job job) {
            job.setStatus(NEGOTIATION);
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("You can not start an open job.");
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("You can not finish an open job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute an open job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }

        @Override
        public void expire(Job job) {
            job.setStatus(EXPIRED);
        }

        @Override
        public void cancel(Job job) {
            job.setStatus(CANCELLED);
        }
    },
    REJECTED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open a rejected job.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("Job is already rejected.");
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("You can not negotiate a rejected job.");
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("You can not start a rejected job.");
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("You can not finish a rejected job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a rejected job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }

        @Override
        public void expire(Job job) {
            throw new IllegalStateException("You can not expire a rejected job.");
        }

        @Override
        public void cancel(Job job) {
            job.setStatus(CANCELLED);
        }
    },

















    NEGOTIATION {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open a negotiation job.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("You can not reject negotiation job.");
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("Job is already negotiation.");
        }

        @Override
        public void start(Job job) {
            job.setStatus(STARTED);
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("You can not finish a negotiation job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a negotiation job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }

        @Override
        public void expire(Job job) {
            throw new IllegalStateException("You can not expire a negotiation job.");
        }

        @Override
        public void cancel(Job job) {
            job.setStatus(CANCELLED);
        }
    },
    STARTED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open a started job.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("You can not reject a started job.");
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("You can not open a started job.");
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("Job is already started.");
        }

        @Override
        public void finish(Job job) {
            job.setStatus(FINISHED);
        }

        @Override
        public void dispute(Job job) {
            job.setStatus(DISPUTED);
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }

        @Override
        public void expire(Job job) {
            throw new IllegalStateException("You can not expire a started job.");
        }

        @Override
        public void cancel(Job job) {
            job.setStatus(CANCELLED);
        }
    },
    FINISHED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open a finished job.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("You can not reject a finished job.");
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("You can not negotiate a finished job.");
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("You can not start a finished job.");
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("Job is already finished.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a finished job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }

        @Override
        public void expire(Job job) {
            throw new IllegalStateException("You can not expire a finished job.");
        }

        @Override
        public void cancel(Job job) {
            job.setStatus(CANCELLED);
        }
    },
    DISPUTED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open a disputed job.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("You can not reject a disputed job.");
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("You can not negotiate a disputed job.");
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("You can not start a disputed job.");
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("You can not finish a disputed job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("Job is already disputed.");
        }

        @Override
        public void archive(Job job) {
            throw new IllegalStateException("You can not archive a disputed job.");
        }

        @Override
        public void expire(Job job) {
            throw new IllegalStateException("You can not expire a disputed job.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("You can not cancel a disputed job.");
        }
    },
    ARCHIVED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not close an archived job.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("You can not reject an archived job.");
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("You can not negotiate a archived job.");
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("You can not start a archived job.");
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("You can not finish a archived job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute an archived job.");
        }

        @Override
        public void archive(Job job) {
            throw new IllegalStateException("Job is already archived.");
        }

        @Override
        public void expire(Job job) {
            throw new IllegalStateException("You can not expire an archived job.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("You can not cancel an archived job.");
        }
    },
    EXPIRED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open an expired job.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("You can not reject an expired job.");
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("You can not negotiate an expired job.");
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("You can not start an expired job.");
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("You can not finish an expired job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute an expired job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }

        @Override
        public void expire(Job job) {
            throw new IllegalStateException("Job is already expired.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("You can not cancel an expired job.");
        }
    },
    CANCELLED {

        @Override
        public void open(Job job) {
            throw new IllegalStateException("You can not open a cancelled job.");
        }

        @Override
        public void reject(Job job) {
            throw new IllegalStateException("You can not reject a cancelled job.");
        }

        @Override
        public void negotiate(Job job) {
            throw new IllegalStateException("You can not negotiate a cancelled job.");
        }

        @Override
        public void start(Job job) {
            throw new IllegalStateException("You can not start a cancelled job.");
        }

        @Override
        public void finish(Job job) {
            throw new IllegalStateException("You can not finish a cancelled job.");
        }

        @Override
        public void dispute(Job job) {
            throw new IllegalStateException("You can not dispute a cancelled job.");
        }

        @Override
        public void archive(Job job) {
            job.setStatus(ARCHIVED);
        }

        @Override
        public void expire(Job job) {
            throw new IllegalStateException("You can not expire a cancelled job.");
        }

        @Override
        public void cancel(Job job) {
            throw new IllegalStateException("Job is already cancelled.");
        }
    };

    public abstract void open(Job job);
    public abstract void reject(Job job);


    public abstract void negotiate(Job job);
    public abstract void start(Job job);
    public abstract void finish(Job job);
    public abstract void dispute(Job job);
    public abstract void archive(Job job);
    public abstract void expire(Job job);
    public abstract void cancel(Job job);
}
