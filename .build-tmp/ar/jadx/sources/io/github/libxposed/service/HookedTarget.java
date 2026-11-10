package io.github.libxposed.service;

import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class HookedTarget {
    private final long mLoadedVersionCode;
    private final int mPid;
    private final String mProcessName;
    private final State mState;
    final long mTargetId;
    private final int mUid;

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public enum State {
        UP_TO_DATE,
        STALE,
        RELOADING,
        FAILED
    }

    public HookedTarget(long j5, int i5, int i6, String str, State state, long j6) {
        this.mTargetId = j5;
        this.mUid = i5;
        this.mPid = i6;
        Objects.requireNonNull(str);
        this.mProcessName = str;
        Objects.requireNonNull(state);
        this.mState = state;
        this.mLoadedVersionCode = j6;
    }

    public long getLoadedVersionCode() {
        return this.mLoadedVersionCode;
    }

    public int getPid() {
        return this.mPid;
    }

    public String getProcessName() {
        return this.mProcessName;
    }

    public State getState() {
        return this.mState;
    }

    public int getUid() {
        return this.mUid;
    }
}
