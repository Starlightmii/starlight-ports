package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.SystemClock;
import android.view.InputEventConsistencyVerifier;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.google.protobuf.Internal$$ExternalSyntheticBUOutline0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class NavigationGestureDetector {
    public boolean mAlwaysInBiggerTapRegion;
    public boolean mAlwaysInTapRegion;
    public final float mAmbiguousGestureMultiplier;
    public MotionEvent mCurrentDownEvent;
    public long mCurrentDownEventTimeMillis;
    public MotionEvent mCurrentMotionEvent;
    public OnDoubleTapListener mDoubleTapListener;
    public final int mDoubleTapSlopSquare;
    public final int mDoubleTapTouchSlopSquare;
    public float mDownFocusX;
    public float mDownFocusY;
    public final GestureHandler mHandler;
    public boolean mInLongPress;
    public final InputEventConsistencyVerifier mInputEventConsistencyVerifier;
    public boolean mIsDoubleTapping;
    public boolean mIsLongPressEnabled;
    public float mLastFocusX;
    public float mLastFocusY;
    public final SimpleOnGestureListener mListener;
    public int mLongPressThreshold;
    public final int mMaximumFlingVelocity;
    public final int mMinimumFlingVelocity;
    public MotionEvent mPreviousUpEvent;
    public int mShowPressThresholds;
    public boolean mStillDown;
    public final int mTouchSlopSquare;
    public VelocityTracker mVelocityTracker;
    public final int mVelocityTrackerStrategy;
    public static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    public static final int DOUBLE_TAP_MIN_TIME = ViewConfiguration.getDoubleTapMinTime();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class GestureHandler extends Handler {
        public GestureHandler() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            NavigationGestureDetector navigationGestureDetector = NavigationGestureDetector.this;
            SimpleOnGestureListener simpleOnGestureListener = navigationGestureDetector.mListener;
            int i = message.what;
            if (i == 1) {
                simpleOnGestureListener.onShowPress();
                return;
            }
            if (i == 2) {
                navigationGestureDetector.mHandler.removeMessages(3);
                navigationGestureDetector.mInLongPress = true;
                simpleOnGestureListener.onLongPress();
            } else if (i != 3) {
                if (i == 4) {
                    simpleOnGestureListener.onPreLongPress();
                } else {
                    Internal$$ExternalSyntheticBUOutline0.m(message, "Unknown message ");
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OnContextClickListener {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OnDoubleTapListener {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class SimpleOnGestureListener implements OnDoubleTapListener, OnContextClickListener {
        public abstract void onDown();

        public abstract void onLongPress();

        public void onPreLongPress() {
        }

        public abstract void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2);

        public abstract void onShowPress();
    }

    public NavigationGestureDetector(Context context, SimpleOnGestureListener simpleOnGestureListener) {
        int scaledDoubleTapTouchSlop;
        int touchSlop;
        int doubleTapSlop;
        this.mInputEventConsistencyVerifier = InputEventConsistencyVerifier.isInstrumentationEnabled() ? new InputEventConsistencyVerifier(this, 0) : null;
        this.mLongPressThreshold = 800;
        this.mShowPressThresholds = 200;
        this.mHandler = new GestureHandler();
        this.mListener = simpleOnGestureListener;
        setOnDoubleTapListener(simpleOnGestureListener);
        setContextClickListener(simpleOnGestureListener);
        this.mVelocityTrackerStrategy = -1;
        this.mIsLongPressEnabled = true;
        if (context == null) {
            touchSlop = ViewConfiguration.getTouchSlop();
            doubleTapSlop = ViewConfiguration.getDoubleTapSlop();
            this.mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            this.mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
            this.mAmbiguousGestureMultiplier = ViewConfiguration.getAmbiguousGestureMultiplier();
            scaledDoubleTapTouchSlop = touchSlop;
        } else {
            StrictMode.assertConfigurationContext(context, "NavigationGestureDetector#init");
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
            scaledDoubleTapTouchSlop = viewConfiguration.getScaledDoubleTapTouchSlop();
            int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
            this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            this.mAmbiguousGestureMultiplier = viewConfiguration.getScaledAmbiguousGestureMultiplier();
            touchSlop = scaledTouchSlop;
            doubleTapSlop = scaledDoubleTapSlop;
        }
        this.mTouchSlopSquare = touchSlop * touchSlop;
        this.mDoubleTapTouchSlopSquare = scaledDoubleTapTouchSlop * scaledDoubleTapTouchSlop;
        this.mDoubleTapSlopSquare = doubleTapSlop * doubleTapSlop;
    }

    public final void onTouchEvent(MotionEvent motionEvent) {
        SimpleOnGestureListener simpleOnGestureListener;
        MotionEvent motionEvent2;
        int i;
        InputEventConsistencyVerifier inputEventConsistencyVerifier = this.mInputEventConsistencyVerifier;
        if (inputEventConsistencyVerifier != null) {
            inputEventConsistencyVerifier.onTouchEvent(motionEvent, 0);
        }
        int action = motionEvent.getAction();
        MotionEvent motionEvent3 = this.mCurrentMotionEvent;
        if (motionEvent3 != null) {
            motionEvent3.recycle();
        }
        this.mCurrentMotionEvent = MotionEvent.obtain(motionEvent);
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain(this.mVelocityTrackerStrategy);
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i2 = action & 255;
        boolean z = i2 == 6;
        int actionIndex = z ? motionEvent.getActionIndex() : -1;
        boolean z2 = (motionEvent.getFlags() & 8) != 0;
        int pointerCount = motionEvent.getPointerCount();
        float x = 0.0f;
        float y = 0.0f;
        for (int i3 = 0; i3 < pointerCount; i3++) {
            if (actionIndex != i3) {
                x += motionEvent.getX(i3);
                y += motionEvent.getY(i3);
            }
        }
        float f = z ? pointerCount - 1 : pointerCount;
        float f2 = x / f;
        float f3 = y / f;
        SimpleOnGestureListener simpleOnGestureListener2 = this.mListener;
        GestureHandler gestureHandler = this.mHandler;
        if (i2 != 0) {
            int i4 = this.mMaximumFlingVelocity;
            if (i2 == 1) {
                this.mStillDown = false;
                MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                if (this.mIsDoubleTapping) {
                    this.mDoubleTapListener.getClass();
                } else if (this.mInLongPress) {
                    gestureHandler.removeMessages(3);
                    this.mInLongPress = false;
                } else if (this.mAlwaysInTapRegion) {
                    simpleOnGestureListener2.getClass();
                } else {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    int pointerId = motionEvent.getPointerId(0);
                    velocityTracker.computeCurrentVelocity(1000, i4);
                    float yVelocity = velocityTracker.getYVelocity(pointerId);
                    float xVelocity = velocityTracker.getXVelocity(pointerId);
                    float fAbs = Math.abs(yVelocity);
                    int i5 = this.mMinimumFlingVelocity;
                    if (fAbs > i5 || Math.abs(xVelocity) > i5) {
                        simpleOnGestureListener2.getClass();
                    }
                }
                MotionEvent motionEvent4 = this.mPreviousUpEvent;
                if (motionEvent4 != null) {
                    motionEvent4.recycle();
                }
                this.mPreviousUpEvent = motionEventObtain;
                VelocityTracker velocityTracker2 = this.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.mVelocityTracker = null;
                }
                this.mIsDoubleTapping = false;
                gestureHandler.removeMessages(1);
                gestureHandler.removeMessages(4);
                gestureHandler.removeMessages(2);
            } else if (i2 != 2) {
                if (i2 == 3) {
                    gestureHandler.removeMessages(1);
                    gestureHandler.removeMessages(4);
                    gestureHandler.removeMessages(2);
                    gestureHandler.removeMessages(3);
                    VelocityTracker velocityTracker3 = this.mVelocityTracker;
                    if (velocityTracker3 != null) {
                        velocityTracker3.recycle();
                        this.mVelocityTracker = null;
                    }
                    this.mIsDoubleTapping = false;
                    this.mStillDown = false;
                    this.mAlwaysInTapRegion = false;
                    this.mAlwaysInBiggerTapRegion = false;
                    this.mInLongPress = false;
                } else if (i2 == 5) {
                    this.mLastFocusX = f2;
                    this.mDownFocusX = f2;
                    this.mLastFocusY = f3;
                    this.mDownFocusY = f3;
                    gestureHandler.removeMessages(1);
                    gestureHandler.removeMessages(4);
                    gestureHandler.removeMessages(2);
                    gestureHandler.removeMessages(3);
                    this.mIsDoubleTapping = false;
                    this.mAlwaysInTapRegion = false;
                    this.mAlwaysInBiggerTapRegion = false;
                    this.mInLongPress = false;
                } else if (i2 == 6) {
                    this.mLastFocusX = f2;
                    this.mDownFocusX = f2;
                    this.mLastFocusY = f3;
                    this.mDownFocusY = f3;
                    this.mVelocityTracker.computeCurrentVelocity(1000, i4);
                    int actionIndex2 = motionEvent.getActionIndex();
                    int pointerId2 = motionEvent.getPointerId(actionIndex2);
                    float xVelocity2 = this.mVelocityTracker.getXVelocity(pointerId2);
                    float yVelocity2 = this.mVelocityTracker.getYVelocity(pointerId2);
                    for (int i6 = 0; i6 < pointerCount; i6++) {
                        if (i6 != actionIndex2) {
                            int pointerId3 = motionEvent.getPointerId(i6);
                            if ((this.mVelocityTracker.getYVelocity(pointerId3) * yVelocity2) + (this.mVelocityTracker.getXVelocity(pointerId3) * xVelocity2) < 0.0f) {
                                this.mVelocityTracker.clear();
                                break;
                            }
                        }
                    }
                }
            } else if (!this.mInLongPress) {
                int classification = motionEvent.getClassification();
                boolean zHasMessages = gestureHandler.hasMessages(2);
                float f4 = this.mLastFocusX - f2;
                float f5 = this.mLastFocusY - f3;
                if (this.mIsDoubleTapping) {
                    this.mDoubleTapListener.getClass();
                    i = 2;
                } else {
                    if (this.mAlwaysInTapRegion) {
                        int i7 = (int) (f2 - this.mDownFocusX);
                        int i8 = (int) (f3 - this.mDownFocusY);
                        int i9 = (i8 * i8) + (i7 * i7);
                        int i10 = z2 ? 0 : this.mTouchSlopSquare;
                        boolean z3 = classification == 1;
                        if (zHasMessages && z3) {
                            float f6 = this.mAmbiguousGestureMultiplier;
                            if (i9 > i10) {
                                gestureHandler.removeMessages(2);
                                gestureHandler.sendMessageAtTime(gestureHandler.obtainMessage(2, 0), this.mCurrentDownEventTimeMillis + ((long) (this.mLongPressThreshold * f6)));
                            }
                            i10 = (int) (f6 * f6 * i10);
                        } else {
                            z2 = z2;
                            f2 = f2;
                        }
                        if (i9 > i10) {
                            simpleOnGestureListener2.onScroll(this.mCurrentDownEvent, motionEvent);
                            this.mLastFocusX = f2;
                            this.mLastFocusY = f3;
                            this.mAlwaysInTapRegion = false;
                            gestureHandler.removeMessages(3);
                            gestureHandler.removeMessages(1);
                            gestureHandler.removeMessages(4);
                            gestureHandler.removeMessages(2);
                        }
                        if (i9 > (z2 ? 0 : this.mDoubleTapTouchSlopSquare)) {
                            this.mAlwaysInBiggerTapRegion = false;
                        }
                    } else if (Math.abs(f4) >= 1.0f || Math.abs(f5) >= 1.0f) {
                        simpleOnGestureListener2.onScroll(this.mCurrentDownEvent, motionEvent);
                        this.mLastFocusX = f2;
                        this.mLastFocusY = f3;
                    }
                    i = 2;
                }
                if (classification == i && zHasMessages) {
                    gestureHandler.removeMessages(i);
                    gestureHandler.sendMessage(gestureHandler.obtainMessage(i, 0));
                }
            }
        } else {
            if (this.mDoubleTapListener != null) {
                boolean zHasMessages2 = gestureHandler.hasMessages(3);
                if (zHasMessages2) {
                    gestureHandler.removeMessages(3);
                }
                MotionEvent motionEvent5 = this.mCurrentDownEvent;
                int i11 = DOUBLE_TAP_TIMEOUT;
                if (motionEvent5 == null || (motionEvent2 = this.mPreviousUpEvent) == null || !zHasMessages2 || !this.mAlwaysInBiggerTapRegion) {
                    simpleOnGestureListener = simpleOnGestureListener2;
                } else {
                    long eventTime = motionEvent.getEventTime() - motionEvent2.getEventTime();
                    simpleOnGestureListener = simpleOnGestureListener2;
                    if (eventTime <= i11 && eventTime >= DOUBLE_TAP_MIN_TIME) {
                        int x2 = ((int) motionEvent5.getX()) - ((int) motionEvent.getX());
                        int y2 = ((int) motionEvent5.getY()) - ((int) motionEvent.getY());
                        if ((y2 * y2) + (x2 * x2) < ((motionEvent5.getFlags() & 8) != 0 ? 0 : this.mDoubleTapSlopSquare)) {
                            this.mIsDoubleTapping = true;
                            this.mDoubleTapListener.getClass();
                            this.mDoubleTapListener.getClass();
                        }
                    }
                }
                gestureHandler.sendEmptyMessageDelayed(3, i11);
            } else {
                simpleOnGestureListener = simpleOnGestureListener2;
            }
            this.mLastFocusX = f2;
            this.mDownFocusX = f2;
            this.mLastFocusY = f3;
            this.mDownFocusY = f3;
            MotionEvent motionEvent6 = this.mCurrentDownEvent;
            if (motionEvent6 != null) {
                motionEvent6.recycle();
            }
            this.mCurrentDownEventTimeMillis = SystemClock.uptimeMillis();
            this.mCurrentDownEvent = MotionEvent.obtain(motionEvent);
            this.mAlwaysInTapRegion = true;
            this.mAlwaysInBiggerTapRegion = true;
            this.mStillDown = true;
            this.mInLongPress = false;
            if (this.mIsLongPressEnabled) {
                gestureHandler.removeMessages(2);
                gestureHandler.sendMessageDelayed(gestureHandler.obtainMessage(4, 0), 300L);
                gestureHandler.sendMessageDelayed(gestureHandler.obtainMessage(2, 0), this.mLongPressThreshold);
            }
            gestureHandler.sendEmptyMessageDelayed(1, this.mShowPressThresholds);
            simpleOnGestureListener.onDown();
        }
        InputEventConsistencyVerifier inputEventConsistencyVerifier2 = this.mInputEventConsistencyVerifier;
        if (inputEventConsistencyVerifier2 != null) {
            inputEventConsistencyVerifier2.onUnhandledEvent(motionEvent, 0);
        }
    }

    public void setContextClickListener(OnContextClickListener onContextClickListener) {
    }

    public void setIsLongPressEnabled(boolean z) {
        this.mIsLongPressEnabled = z;
    }

    public void setLongPressThreshold(int i) {
        this.mLongPressThreshold = i;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.mDoubleTapListener = onDoubleTapListener;
    }

    public void setShowPressThresholds(int i) {
        this.mShowPressThresholds = i;
    }
}
