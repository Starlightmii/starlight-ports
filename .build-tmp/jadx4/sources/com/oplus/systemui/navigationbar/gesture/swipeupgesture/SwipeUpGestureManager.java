package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.oplus.systemui.navigationbar.gesture.manager.GestureBGThreadManager;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.gesture.sidegesture.EventInterceptInjectHelper;
import com.oplus.systemui.navigationbar.observer.NavStateObserver;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureMistouchPreventionObserver;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.common.utils.Util;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SwipeUpGestureManager {
    public static volatile SwipeUpGestureManager sInstance;
    public final SwipeUpGestureManager$$ExternalSyntheticLambda0 mCancelTimeOutRunnable;
    public final Context mContext;
    public int mRotation;
    public final SideGestureConfigurationEx mSideGestureConfigurationEx;
    public final SwipeUpGesturePostProcess mSwipeUpGesturePostprocess;
    public final SwipeUpGesturePreProcess mSwipeUpGesturePreprocess;
    public final Handler mSwipeUpGestureUIHandler;
    public final SystemGesturesPointerTraceDetector mSystemGesturesPointerTraceDetector;
    public int mGestureDownState = 1001;
    public boolean mIsSendDownEvent = false;
    public boolean mHasMoveEvent = false;
    public MotionEvent mCancelEvent = null;
    public boolean mIsValidTouchEvent = false;
    public int mBottomGestureAreaHeight = 0;
    public int mScreenHeight = 0;
    public int mScreenWidth = 0;

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.swipeupgesture.SwipeUpGestureManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 {
        public /* synthetic */ SwipeUpGestureManager this$0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SwipeUpGestureManager(Context context) {
        this.mRotation = 0;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        SwipeUpGestureManager$$ExternalSyntheticLambda0 swipeUpGestureManager$$ExternalSyntheticLambda0 = new SwipeUpGestureManager$$ExternalSyntheticLambda0();
        swipeUpGestureManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mCancelTimeOutRunnable = swipeUpGestureManager$$ExternalSyntheticLambda0;
        this.mContext = context;
        this.mRotation = context.getResources().getConfiguration().windowConfiguration.getDisplayRotation();
        Handler handler = new Handler(OplusThreadFactoryImpl.INSTANCE.buildNewThread("SwipeUPGestureUI", -8).getLooper());
        this.mSwipeUpGestureUIHandler = handler;
        Handler gestureBGHandler = GestureBGThreadManager.INSTANCE.getGestureBGHandler();
        SwipeUpGesturePreProcess swipeUpGesturePreProcess = new SwipeUpGesturePreProcess();
        swipeUpGesturePreProcess.mLastEventTime = 0L;
        swipeUpGesturePreProcess.mScreenHeight = 0;
        swipeUpGesturePreProcess.mScreenWidth = 0;
        swipeUpGesturePreProcess.mSwipeActionState = 4000;
        swipeUpGesturePreProcess.mContext = context;
        swipeUpGesturePreProcess.mSwipeUpGestureManager = this;
        this.mSwipeUpGesturePreprocess = swipeUpGesturePreProcess;
        SwipeUpGesturePostProcess swipeUpGesturePostProcess = new SwipeUpGesturePostProcess();
        swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch = false;
        SwipeUpGesturePostProcess$$ExternalSyntheticLambda0 swipeUpGesturePostProcess$$ExternalSyntheticLambda0 = new SwipeUpGesturePostProcess$$ExternalSyntheticLambda0();
        swipeUpGesturePostProcess$$ExternalSyntheticLambda0.f$0 = swipeUpGesturePostProcess;
        VarHandle.storeStoreFence();
        swipeUpGesturePostProcess.mUpGestureMistouchStateListener = swipeUpGesturePostProcess$$ExternalSyntheticLambda0;
        SwipeUpGesturePostProcess$$ExternalSyntheticLambda1 swipeUpGesturePostProcess$$ExternalSyntheticLambda1 = new SwipeUpGesturePostProcess$$ExternalSyntheticLambda1(0 == true ? 1 : 0);
        swipeUpGesturePostProcess$$ExternalSyntheticLambda1.f$0 = swipeUpGesturePostProcess;
        VarHandle.storeStoreFence();
        swipeUpGesturePostProcess.mMisTouchRunnable = swipeUpGesturePostProcess$$ExternalSyntheticLambda1;
        SwipeUpGesturePostProcess$$ExternalSyntheticLambda1 swipeUpGesturePostProcess$$ExternalSyntheticLambda2 = new SwipeUpGesturePostProcess$$ExternalSyntheticLambda1(1);
        swipeUpGesturePostProcess$$ExternalSyntheticLambda2.f$0 = swipeUpGesturePostProcess;
        VarHandle.storeStoreFence();
        swipeUpGesturePostProcess.mBackEventRunnable = swipeUpGesturePostProcess$$ExternalSyntheticLambda2;
        SwipeUpGesturePostProcess$$ExternalSyntheticLambda1 swipeUpGesturePostProcess$$ExternalSyntheticLambda3 = new SwipeUpGesturePostProcess$$ExternalSyntheticLambda1(2);
        swipeUpGesturePostProcess$$ExternalSyntheticLambda3.f$0 = swipeUpGesturePostProcess;
        VarHandle.storeStoreFence();
        swipeUpGesturePostProcess.mHomeEventRunnable = swipeUpGesturePostProcess$$ExternalSyntheticLambda3;
        swipeUpGesturePostProcess.mBackgroundHandler = gestureBGHandler;
        swipeUpGesturePostProcess.mContext = context;
        swipeUpGesturePostProcess.mSwipeUpGestureManager = this;
        ArrayList arrayList = new ArrayList();
        swipeUpGesturePostProcess.mMisTouchAppWhiteList = arrayList;
        arrayList.add("com.tencent.tmgp.sgame");
        swipeUpGesturePostProcess.mMisTouchAppWhiteList.add("com.tencent.tmgp.WePop");
        swipeUpGesturePostProcess.mMisTouchAppWhiteList.add("com.tencent.wod");
        SwipeUpGestureMistouchPreventionObserver swipeUpGestureMistouchPreventionObserver = SwipeUpGestureMistouchPreventionObserver.INSTANCE;
        swipeUpGesturePostProcess.mMistouchEnable = swipeUpGestureMistouchPreventionObserver.getSwipeUpGestureMistouchPrevention() == 1;
        swipeUpGestureMistouchPreventionObserver.addListener(swipeUpGesturePostProcess$$ExternalSyntheticLambda0);
        NavStateObserver.INSTANCE.addListener(swipeUpGesturePostProcess$$ExternalSyntheticLambda0);
        this.mSwipeUpGesturePostprocess = swipeUpGesturePostProcess;
        SystemGesturesPointerTraceDetector systemGesturesPointerTraceDetector = new SystemGesturesPointerTraceDetector();
        SystemGesturesPointerTraceDetector$$ExternalSyntheticLambda0 systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0 = new SystemGesturesPointerTraceDetector$$ExternalSyntheticLambda0();
        systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0.f$0 = systemGesturesPointerTraceDetector;
        VarHandle.storeStoreFence();
        systemGesturesPointerTraceDetector.mSwipeUpFailRunnable = systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0;
        systemGesturesPointerTraceDetector.mSwipeUpGestureUIHandler = handler;
        systemGesturesPointerTraceDetector.mCallbacks = anonymousClass1;
        systemGesturesPointerTraceDetector.mTopSwipeStartThreshold = context.getResources().getInteger(2131427711);
        VarHandle.storeStoreFence();
        this.mSystemGesturesPointerTraceDetector = systemGesturesPointerTraceDetector;
        this.mSideGestureConfigurationEx = NavBarUtils.getEdgeBackController(context).mSideGestureConfiguration;
        updateSwipeUpGestureDisplaySize(this.mRotation);
    }

    public static SwipeUpGestureManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SwipeUpGestureManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SwipeUpGestureManager(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public static void injectAllEvent() {
        ((EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get()).injectAllEvent();
    }

    /* JADX WARN: Code duplicated, block: B:102:0x024b  */
    /* JADX WARN: Code duplicated, block: B:105:0x0252  */
    /* JADX WARN: Code duplicated, block: B:106:0x0258 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:107:0x025a  */
    /* JADX WARN: Code duplicated, block: B:108:0x0260  */
    /* JADX WARN: Code duplicated, block: B:110:0x0264  */
    /* JADX WARN: Code duplicated, block: B:55:0x012f  */
    /* JADX WARN: Code duplicated, block: B:57:0x0133  */
    /* JADX WARN: Code duplicated, block: B:58:0x0138  */
    /* JADX WARN: Code duplicated, block: B:90:0x020a  */
    public final void addPointerEvent(MotionEvent motionEvent) {
        char c;
        Toast toast;
        if (motionEvent == null) {
            return;
        }
        ((EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get()).addPointerEvent(motionEvent);
        SystemGesturesPointerTraceDetector systemGesturesPointerTraceDetector = this.mSystemGesturesPointerTraceDetector;
        AnonymousClass1 anonymousClass1 = systemGesturesPointerTraceDetector.mCallbacks;
        SystemGesturesPointerTraceDetector$$ExternalSyntheticLambda0 systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0 = systemGesturesPointerTraceDetector.mSwipeUpFailRunnable;
        Handler handler = systemGesturesPointerTraceDetector.mSwipeUpGestureUIHandler;
        if (motionEvent.getPointerCount() > 1 && systemGesturesPointerTraceDetector.mSwipeUpFireable) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", "addPointerEvent onSwipePointFail getPointerCount = " + motionEvent.getPointerCount());
            systemGesturesPointerTraceDetector.mSwipeUpFireable = false;
            SwipeUpGestureManager swipeUpGestureManager = anonymousClass1.this$0;
            LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "mSystemGesturesCallbacks   onSwipePointerFail ");
            injectAllEvent();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            systemGesturesPointerTraceDetector.mSwipeUpFireable = true;
            systemGesturesPointerTraceDetector.captureDown(motionEvent);
            SwipeUpGestureManager swipeUpGestureManager2 = anonymousClass1.this$0;
            LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "mSystemGesturesCallbacks   onDown ");
            systemGesturesPointerTraceDetector.removeFailCallback();
            handler.postDelayed(systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0, 200L);
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (!systemGesturesPointerTraceDetector.mSwipeUpFireable) {
                    systemGesturesPointerTraceDetector.removeFailCallback();
                    return;
                }
                systemGesturesPointerTraceDetector.removeFailCallback();
                handler.postDelayed(systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0, 50L);
                long eventTime = motionEvent.getEventTime();
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                float f = systemGesturesPointerTraceDetector.mDownX;
                float f2 = systemGesturesPointerTraceDetector.mDownY;
                long j = eventTime - systemGesturesPointerTraceDetector.mDownTime;
                StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("moved (", f, "->", x, OplusPreventModeData.SEPARATOR);
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f2, "->", y, ") in ");
                sbM.append(j);
                LogUtil.d(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", sbM.toString());
                float f3 = systemGesturesPointerTraceDetector.mTopSwipeStartThreshold;
                if (f2 > f3 || y <= f3 + f2 || j >= 400 || Math.abs(f2 - y) <= Math.abs(f - x)) {
                    float f4 = systemGesturesPointerTraceDetector.mScreenHeight;
                    float f5 = systemGesturesPointerTraceDetector.mBottomSwipeStartThreshold;
                    if (f2 < f4 - f5) {
                        if (j >= 400) {
                            systemGesturesPointerTraceDetector.mReason = 0;
                            c = 5;
                        } else {
                            c = 0;
                        }
                    } else if (y >= f2 - f5 || j >= 400) {
                        float f6 = f2 - y;
                        if (Math.abs(f6) < 4.0f && Math.abs(f - x) < 4.0f && j > 50) {
                            systemGesturesPointerTraceDetector.mReason = 1;
                        } else if (Math.abs(f6) * 1.2f < Math.abs(f - x) && j > 80 && Math.abs(f6) > 0.0f) {
                            systemGesturesPointerTraceDetector.mReason = 2;
                        } else if (j >= 400) {
                            systemGesturesPointerTraceDetector.mReason = 0;
                            c = 5;
                        } else {
                            c = 0;
                        }
                        c = 6;
                    } else {
                        c = 2;
                    }
                } else {
                    c = 1;
                }
                if (c != 0) {
                    systemGesturesPointerTraceDetector.removeFailCallback();
                } else {
                    c = 0;
                }
                systemGesturesPointerTraceDetector.mSwipeUpFireable = c == 0;
                if (c == 1) {
                    LogUtil.i(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", "Firing onSwipeFromTop");
                    SwipeUpGestureManager swipeUpGestureManager3 = anonymousClass1.this$0;
                    LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "mSystemGesturesCallbacks   onSwipeFromTop ");
                    injectAllEvent();
                    return;
                }
                if (c != 2 && c != 4 && c != 3) {
                    if (c == 5) {
                        LogUtil.i(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", "Swipe Action Time out");
                        SwipeUpGestureManager swipeUpGestureManager4 = anonymousClass1.this$0;
                        LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "mSystemGesturesCallbacks   onSwipeTimeOut ");
                        injectAllEvent();
                        return;
                    }
                    if (c == 6) {
                        LogUtil.i(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", "Swipe Action fail, reason = " + systemGesturesPointerTraceDetector.mReason);
                        anonymousClass1.getClass();
                        LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "mSystemGesturesCallbacks   onSwipeFail ");
                        injectAllEvent();
                        return;
                    }
                    return;
                }
                LogUtil.i(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", "Firing SUCCESS");
                SwipeUpGestureManager swipeUpGestureManager5 = anonymousClass1.this$0;
                LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "mSystemGesturesCallbacks   onSwipeSuccess ");
                SwipeUpGesturePostProcess swipeUpGesturePostProcess = swipeUpGestureManager5.mSwipeUpGesturePostprocess;
                int swipeActionState = swipeUpGestureManager5.getSwipeUpGesturePreprocess().getSwipeActionState();
                int i = swipeUpGestureManager5.mRotation;
                Handler handler2 = swipeUpGesturePostProcess.mBackgroundHandler;
                SwipeUpGesturePostProcess$$ExternalSyntheticLambda1 swipeUpGesturePostProcess$$ExternalSyntheticLambda1 = swipeUpGesturePostProcess.mMisTouchRunnable;
                String currentTopAppPackageName = Util.getCurrentTopAppPackageName();
                StringBuilder sb = new StringBuilder("onInputEvent mistouchEnable:");
                PromptIconState$.ExternalSyntheticOutline0.m(i, ",rotation:", ",hasSwipeOnce:", sb, swipeUpGesturePostProcess.mMistouchEnable);
                sb.append(swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch);
                sb.append(",topAppPkg:");
                sb.append(currentTopAppPackageName);
                LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGesturePostProcess", sb.toString());
                if (!swipeUpGesturePostProcess.mMistouchEnable) {
                    swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch = false;
                    handler2.removeCallbacks(swipeUpGesturePostProcess$$ExternalSyntheticLambda1);
                    toast = swipeUpGesturePostProcess.mNotifyMisTouchToast;
                    if (toast != null) {
                        toast.cancel();
                    }
                    if (swipeActionState == 4002) {
                        handler2.post(swipeUpGesturePostProcess.mHomeEventRunnable);
                    } else if (swipeActionState == 4003) {
                        handler2.post(swipeUpGesturePostProcess.mBackEventRunnable);
                    } else if (swipeActionState == 4001) {
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        swipeUpGesturePostProcess.mDownTime = jUptimeMillis;
                        swipeUpGesturePostProcess.sendEvent(187, 0, jUptimeMillis);
                        swipeUpGesturePostProcess.sendEvent(187, 1, swipeUpGesturePostProcess.mDownTime);
                    }
                } else if (swipeUpGesturePostProcess.mSwipeUpGestureManager.getSwipeUpGesturePreprocess().getSwipeActionState() == 4003) {
                    ArrayList arrayList = swipeUpGesturePostProcess.mMisTouchAppWhiteList;
                    int size = arrayList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 < size) {
                            Object obj = arrayList.get(i2);
                            i2++;
                            if (currentTopAppPackageName.contains((String) obj)) {
                            }
                        } else if (i == 0 && i != 2) {
                            int intForUser = Settings.Secure.getIntForUser(swipeUpGesturePostProcess.mContext.getContentResolver(), "gesture_mistouch_in_game_mode", -1, -2);
                            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(intForUser, "check mistouch prevention, appType(game --1; app--0):", LogUtil.TAG_NAVBAR, "SwipeUpGesturePostProcess");
                            if (intForUser == 1 && !swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch) {
                                swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch = true;
                                handler2.postDelayed(swipeUpGesturePostProcess$$ExternalSyntheticLambda1, 2000L);
                                Toast toastMakeText = Toast.makeText(swipeUpGesturePostProcess.mContext, 2131955157, 0);
                                swipeUpGesturePostProcess.mNotifyMisTouchToast = toastMakeText;
                                toastMakeText.show();
                            }
                        }
                        swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch = false;
                        handler2.removeCallbacks(swipeUpGesturePostProcess$$ExternalSyntheticLambda1);
                        toast = swipeUpGesturePostProcess.mNotifyMisTouchToast;
                        if (toast != null) {
                            toast.cancel();
                        }
                        if (swipeActionState == 4002) {
                            handler2.post(swipeUpGesturePostProcess.mHomeEventRunnable);
                        } else if (swipeActionState == 4003) {
                            handler2.post(swipeUpGesturePostProcess.mBackEventRunnable);
                        } else if (swipeActionState == 4001) {
                            long jUptimeMillis2 = SystemClock.uptimeMillis();
                            swipeUpGesturePostProcess.mDownTime = jUptimeMillis2;
                            swipeUpGesturePostProcess.sendEvent(187, 0, jUptimeMillis2);
                            swipeUpGesturePostProcess.sendEvent(187, 1, swipeUpGesturePostProcess.mDownTime);
                        }
                    }
                } else if (i == 0) {
                    swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch = false;
                    handler2.removeCallbacks(swipeUpGesturePostProcess$$ExternalSyntheticLambda1);
                    toast = swipeUpGesturePostProcess.mNotifyMisTouchToast;
                    if (toast != null) {
                        toast.cancel();
                    }
                    if (swipeActionState == 4002) {
                        handler2.post(swipeUpGesturePostProcess.mHomeEventRunnable);
                    } else if (swipeActionState == 4003) {
                        handler2.post(swipeUpGesturePostProcess.mBackEventRunnable);
                    } else if (swipeActionState == 4001) {
                        long jUptimeMillis3 = SystemClock.uptimeMillis();
                        swipeUpGesturePostProcess.mDownTime = jUptimeMillis3;
                        swipeUpGesturePostProcess.sendEvent(187, 0, jUptimeMillis3);
                        swipeUpGesturePostProcess.sendEvent(187, 1, swipeUpGesturePostProcess.mDownTime);
                    }
                } else {
                    swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch = false;
                    handler2.removeCallbacks(swipeUpGesturePostProcess$$ExternalSyntheticLambda1);
                    toast = swipeUpGesturePostProcess.mNotifyMisTouchToast;
                    if (toast != null) {
                        toast.cancel();
                    }
                    if (swipeActionState == 4002) {
                        handler2.post(swipeUpGesturePostProcess.mHomeEventRunnable);
                    } else if (swipeActionState == 4003) {
                        handler2.post(swipeUpGesturePostProcess.mBackEventRunnable);
                    } else if (swipeActionState == 4001) {
                        long jUptimeMillis4 = SystemClock.uptimeMillis();
                        swipeUpGesturePostProcess.mDownTime = jUptimeMillis4;
                        swipeUpGesturePostProcess.sendEvent(187, 0, jUptimeMillis4);
                        swipeUpGesturePostProcess.sendEvent(187, 1, swipeUpGesturePostProcess.mDownTime);
                    }
                }
                ((EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get()).clearLastEvent();
                return;
            }
            if (actionMasked != 3) {
                if (actionMasked == 5) {
                    systemGesturesPointerTraceDetector.captureDown(motionEvent);
                    return;
                }
                LogUtil.d(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", "Ignoring " + motionEvent);
                return;
            }
        }
        systemGesturesPointerTraceDetector.mSwipeUpFireable = false;
        anonymousClass1.getClass();
        LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "mSystemGesturesCallbacks   onUpOrCancel ");
        injectAllEvent();
        systemGesturesPointerTraceDetector.removeFailCallback();
    }

    public int getBottomGestureAreaHeight() {
        return this.mBottomGestureAreaHeight;
    }

    public SwipeUpGesturePreProcess getSwipeUpGesturePreprocess() {
        return this.mSwipeUpGesturePreprocess;
    }

    public final void updateSwipeUpGestureDisplaySize(int i) {
        this.mRotation = i;
        SideGestureConfigurationEx sideGestureConfigurationEx = this.mSideGestureConfigurationEx;
        this.mScreenWidth = sideGestureConfigurationEx.getScreenWidth(i);
        this.mScreenHeight = sideGestureConfigurationEx.getScreenHeight(this.mRotation);
        int bottomGestureAreaHeight = sideGestureConfigurationEx.getBottomGestureAreaHeight();
        this.mBottomGestureAreaHeight = bottomGestureAreaHeight;
        float f = this.mScreenHeight;
        SystemGesturesPointerTraceDetector systemGesturesPointerTraceDetector = this.mSystemGesturesPointerTraceDetector;
        systemGesturesPointerTraceDetector.mScreenHeight = f;
        systemGesturesPointerTraceDetector.setSwipeUpThreshold(bottomGestureAreaHeight);
        int i2 = this.mScreenWidth;
        int i3 = this.mScreenHeight;
        SwipeUpGesturePreProcess swipeUpGesturePreProcess = this.mSwipeUpGesturePreprocess;
        swipeUpGesturePreProcess.mScreenHeight = i3;
        swipeUpGesturePreProcess.mScreenWidth = i2;
        this.mSwipeUpGesturePostprocess.getClass();
        if (NavBarUtils.isGestureUpMode()) {
            GestureUpFirstGuideHelper gestureUpFirstGuideHelper = GestureUpFirstGuideHelper.getInstance();
            int i4 = this.mRotation;
            Context context = this.mContext;
            gestureUpFirstGuideHelper.getClass();
            LogUtil.d(LogUtil.TAG_NAVBAR, "FirstGuideHelper", "notifyRotationChanged:");
            if (NavBarSettingsValueProxy.getUpGestureEnterLandScapeTipsNumber(context) != 0) {
                ThreadManager threadManager = ThreadManager.getInstance();
                GestureUpFirstGuideHelper$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new GestureUpFirstGuideHelper$.ExternalSyntheticLambda0();
                externalSyntheticLambda0.f$0 = gestureUpFirstGuideHelper;
                externalSyntheticLambda0.f$1 = i4;
                externalSyntheticLambda0.f$2 = context;
                VarHandle.storeStoreFence();
                threadManager.postInMainThread(externalSyntheticLambda0);
            }
        }
        StringBuilder sb = new StringBuilder("mScreenHeight:");
        sb.append(this.mScreenHeight);
        sb.append(" mScreenWidth:");
        sb.append(this.mScreenWidth);
        sb.append(" mBottomGestureAreaHeight:");
        sb.append(this.mBottomGestureAreaHeight);
        sb.append(" mRotation:");
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mRotation, LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", sb);
    }
}
