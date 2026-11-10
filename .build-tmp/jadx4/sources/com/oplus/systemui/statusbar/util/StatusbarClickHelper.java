package com.oplus.systemui.statusbar.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.UserHandle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.broadcast.BroadcastSender;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusbarClickHelper {
    private static final String ACTION_CLICK_ON_STATUS_BAR = "com.oplus.clicktop";
    public static final Companion Companion = new Companion();
    private static final String TAG = "StatusbarClickHelper";
    private final BroadcastSender broadcastSender;
    public long lastClickTimeMs;
    private PointF mLastDownPoint = new PointF();
    public volatile int mTouchSlop = 24;
    public volatile int mLongPressTimeOut = 400;
    private final Intent mClickIntent = new Intent(ACTION_CLICK_ON_STATUS_BAR);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatusbarClickHelper(BroadcastSender broadcastSender) {
        this.broadcastSender = broadcastSender;
    }

    public final void checkClickParams(Context context) {
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mLongPressTimeOut = ViewConfiguration.getLongPressTimeout();
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(this.mTouchSlop, this.mLongPressTimeOut, "mTouchSlop: ", ", mLongPressTimeOut: "), null, 8);
    }

    public final void scrollToTop(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 1) {
            if (actionMasked == 0) {
                this.mLastDownPoint.set(motionEvent.getX(), motionEvent.getY());
                return;
            }
            boolean z = Math.abs(motionEvent.getX() - this.mLastDownPoint.x) > ((float) this.mTouchSlop) || Math.abs(motionEvent.getY() - this.mLastDownPoint.y) > ((float) this.mTouchSlop);
            boolean z2 = motionEvent.getEventTime() - motionEvent.getDownTime() <= ((long) this.mLongPressTimeOut);
            if (z || !z2) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "StatusbarClickHelper-->click on status bar");
            }
            long eventTime = motionEvent.getEventTime();
            if (eventTime - this.lastClickTimeMs >= 800) {
                this.lastClickTimeMs = eventTime;
                this.broadcastSender.sendBroadcastAsUser(this.mClickIntent, UserHandle.ALL);
            }
        }
    }
}
