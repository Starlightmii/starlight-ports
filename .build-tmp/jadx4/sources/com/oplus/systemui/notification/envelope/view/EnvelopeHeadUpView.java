package com.oplus.systemui.notification.envelope.view;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.oplus.systemui.util.NotificationUtil;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class EnvelopeHeadUpView extends LinearLayout {
    public CallBack mCallBack;
    public float mDistanceThreshold;
    public final SparseArray mDownEventList;
    public long mDownTime;
    public boolean mIsSwipeGesture;
    public int mSwipeDirection;
    public int mSwipeStatus;
    public int mTouchDownX;
    public int mTouchDownY;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CallBack {
    }

    public EnvelopeHeadUpView(Context context) {
        super(context);
        this.mDownEventList = new SparseArray();
        this.mSwipeStatus = -1;
        this.mSwipeDirection = -1;
        this.mIsSwipeGesture = false;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        setVisibility(8);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        CallBack callBack;
        int i;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mIsSwipeGesture = false;
            this.mTouchDownX = (int) motionEvent.getRawX();
            this.mTouchDownY = (int) motionEvent.getRawY();
            this.mDownTime = SystemClock.uptimeMillis();
            this.mDownEventList.clear();
            this.mSwipeStatus = 1;
            int pointerId = motionEvent.getPointerId(0);
            float x = motionEvent.getX(0);
            float y = motionEvent.getY(0);
            long eventTime = motionEvent.getEventTime();
            EventInfo eventInfo = new EventInfo();
            eventInfo.mX = x;
            eventInfo.mY = y;
            eventInfo.mTime = eventTime;
            this.mDownEventList.put(pointerId, eventInfo);
            return true;
        }
        if (actionMasked != 1) {
            int i2 = 2;
            if (actionMasked == 2) {
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                boolean z = ((float) Math.abs(rawX - this.mTouchDownX)) > this.mDistanceThreshold;
                boolean z2 = ((float) Math.abs(rawY - this.mTouchDownY)) > this.mDistanceThreshold;
                if (z || z2) {
                    this.mIsSwipeGesture = true;
                }
                int i3 = this.mSwipeStatus;
                if (i3 != 2 && i3 != 0) {
                    int pointerCount = motionEvent.getPointerCount();
                    int i4 = 0;
                    int i5 = 1;
                    while (true) {
                        if (i4 >= pointerCount) {
                            i2 = i5;
                            break;
                        }
                        int pointerId2 = motionEvent.getPointerId(i4);
                        int iFindPointerIndex = motionEvent.findPointerIndex(pointerId2);
                        if (iFindPointerIndex == -1) {
                            break;
                        }
                        motionEvent.getX(iFindPointerIndex);
                        float y2 = motionEvent.getY(iFindPointerIndex);
                        long eventTime2 = motionEvent.getEventTime();
                        float f = ((EventInfo) this.mDownEventList.get(pointerId2)).mX;
                        float f2 = ((EventInfo) this.mDownEventList.get(pointerId2)).mY;
                        long j = eventTime2 - ((EventInfo) this.mDownEventList.get(pointerId2)).mTime;
                        float f3 = getContext().getResources().getConfiguration().orientation == 1 ? 30.0f : 15.0f;
                        this.mDistanceThreshold = f3;
                        if (f2 - y2 <= f3 || j >= 400) {
                            i5 = j > 400 ? 2 : 1;
                        } else {
                            this.mSwipeDirection = 0;
                            i5 = 0;
                        }
                        i4++;
                    }
                    this.mSwipeStatus = i2;
                    if (i2 == 0 && (callBack = this.mCallBack) != null && this.mSwipeDirection == 0) {
                        ((EnvelopeViewManager.AnonymousClass5) callBack).this$0.removeHeadUpViewImmediately();
                    }
                }
            } else if (actionMasked != 3) {
                if (actionMasked == 5 && (i = this.mSwipeStatus) != 2 && i != 0) {
                    int actionIndex = motionEvent.getActionIndex();
                    int pointerId3 = motionEvent.getPointerId(actionIndex);
                    float x2 = motionEvent.getX(actionIndex);
                    float y3 = motionEvent.getY(actionIndex);
                    long eventTime3 = motionEvent.getEventTime();
                    EventInfo eventInfo2 = new EventInfo();
                    eventInfo2.mX = x2;
                    eventInfo2.mY = y3;
                    eventInfo2.mTime = eventTime3;
                    this.mDownEventList.put(pointerId3, eventInfo2);
                    return true;
                }
            }
            return true;
        }
        if (SystemClock.uptimeMillis() - this.mDownTime < 500 && !this.mIsSwipeGesture && this.mSwipeStatus != 0) {
            EnvelopeViewManager envelopeViewManager = ((EnvelopeViewManager.AnonymousClass5) this.mCallBack).this$0;
            try {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "[onClick] mPendingIntent = " + envelopeViewManager.mPendingIntent);
                envelopeViewManager.removeHeadUpViewImmediately();
                Context context = envelopeViewManager.mCurrentContext;
                NotificationUtil.sendPendingIntent(context == null ? 0 : context.getDisplayId(), envelopeViewManager.mPendingIntent, false);
            } catch (PendingIntent.CanceledException unused) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--error when open envelope");
            }
        }
        this.mDownEventList.clear();
        this.mSwipeStatus = -1;
        return true;
    }

    public void setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }

    public EnvelopeHeadUpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDownEventList = new SparseArray();
        this.mSwipeStatus = -1;
        this.mSwipeDirection = -1;
        this.mIsSwipeGesture = false;
    }
}
