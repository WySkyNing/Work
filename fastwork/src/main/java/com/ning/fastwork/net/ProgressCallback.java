package com.ning.fastwork.net;

/**
 * Desction:
 * Author:pengjianbo
 * Date:16/4/19 上午11:31
 */
interface ProgressCallback {
    void updateProgress(int progress, long networkSpeed, boolean done);
}