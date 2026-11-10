package com.oplus.systemui.qs.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LocalLongClickHandler {
    public static final Companion Companion = new Companion();
    private final Runnable countDownRunnable;
    public float mLastMotionX;
    public float mLastMotionY;
    private final AtomicBoolean mIsEnabled = new AtomicBoolean(false);
    private final AtomicBoolean mIsRelease = new AtomicBoolean(true);
    private final AtomicReference<View.OnLongClickListener> mLongClickListener = new AtomicReference<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public LocalLongClickHandler() {
        LocalLongClickHandler$countDownRunnable$1 localLongClickHandler$countDownRunnable$1 = new LocalLongClickHandler$countDownRunnable$1();
        localLongClickHandler$countDownRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.countDownRunnable = localLongClickHandler$countDownRunnable$1;
    }

    private final void setMotionEvent(MotionEvent motionEvent) {
        this.mLastMotionX = motionEvent.getX();
        this.mLastMotionY = motionEvent.getY();
    }

    public final View.OnLongClickListener getLongClickListener() {
        return this.mLongClickListener.get();
    }

    public final void onTouch(View view, MotionEvent motionEvent) {
        if (this.mIsEnabled.get()) {
            int action = motionEvent.getAction();
            if (action == 0) {
                setMotionEvent(motionEvent);
                this.mIsRelease.set(false);
                view.removeCallbacks(this.countDownRunnable);
                view.postDelayed(this.countDownRunnable, ViewConfiguration.getLongPressTimeout());
                return;
            }
            if (action != 1) {
                if (action == 2) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (Math.abs(this.mLastMotionX - x) > 20.0f || Math.abs(this.mLastMotionY - y) > 20.0f) {
                        this.mIsRelease.set(true);
                        return;
                    }
                    return;
                }
                if (action != 3) {
                    return;
                }
            }
            this.mIsRelease.set(true);
        }
    }

    public final boolean setEnable(boolean z) {
        return this.mIsEnabled.compareAndSet(!z, z);
    }

    public final void setLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mLongClickListener.set(onLongClickListener);
    }
}
