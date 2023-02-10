package com.udacity.webcrawler.profiler;

import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CustomCrawlTask extends RecursiveTask {

    private final String url;
    private final Duration timeout;
    private final int popularWordCount;
    private final Clock clock;
    private final ForkJoinPool pool;


    public CustomCrawlTask(String url, Duration timeout, int popularWordCount, Clock clock, ForkJoinPool pool) {
        this.url = url;
        this.timeout = timeout;
        this.popularWordCount = popularWordCount;
        this.clock = clock;
        this.pool = pool;
    }

    @Override
    protected Object compute() {
        return null;
    }
}
