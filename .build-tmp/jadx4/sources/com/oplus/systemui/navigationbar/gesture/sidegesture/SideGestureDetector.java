package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.app.INotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.hardware.input.InputManagerGlobal;
import android.os.Handler;
import android.os.Looper;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.ISystemGestureExclusionListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OplusWindowManager;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.fragment.app.Fragment$5$;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.application.impl.OplusSystemUIApplicationUtils;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.android.systemui.navigationbar.SideGestureDetectorEx;
import com.android.systemui.navigationbar.SideGestureViewManagerEx;
import com.android.systemui.navigationbar.gestural.DisplayBackGestureHandler;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.android.systemui.navigationbar.gestural.Utilities;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.NavigationEdgeBackPlugin;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.android.systemui.util.JankUtils;
import com.android.wm.shell.back.BackAnimation;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplus.systemui.common.OplusSwitchCameraPreviewStartable;
import com.oplus.systemui.navigationbar.gesture.AppSettingsHandler;
import com.oplus.systemui.navigationbar.gesture.AppSettingsHandler$$ExternalSyntheticLambda0;
import com.oplus.systemui.navigationbar.gesture.OplusInputInjector;
import com.oplus.systemui.navigationbar.gesture.manager.GestureBGThreadManager;
import com.oplus.systemui.navigationbar.gesture.subdisplay.ConfigurationOnSubDisplay;
import com.oplus.systemui.navigationbar.gesture.subdisplay.SecondaryRotationWatcher;
import com.oplus.systemui.navigationbar.observer.IpeSlideGestureRejectionObserver;
import com.oplus.systemui.navigationbar.observer.NavGestureProcessObserver;
import com.oplus.systemui.navigationbar.observer.OplusGameModeObserver;
import com.oplus.systemui.navigationbar.utils.DisplaySizeUtils;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.CommonUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SideGestureDetector extends SideGestureDetectorEx {
    public static final int MAX_LONG_PRESS_TIMEOUT = SystemProperties.getInt("gestures.back_timeout", ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION);
    public BackAnimation mBackAnimation;
    public final AnonymousClass3 mBackCallback;
    public final float mBackSwipeProgressThreshold;
    public final AnonymousClass6 mContentObserver;
    public final Context mContext;
    public int mDeviceState;
    public boolean mDisabledForQuickstep;
    public Display mDisplay;
    public Map mDisplayBackGestureHandlers;
    public final int mDisplayId;
    public final String mDisplayIdTag;
    public final FalsingManager mFalsingManager;
    public final List mGestureBlockingActivities;
    public final ISystemGestureExclusionListener mGestureExclusionListener;
    public final Handler mGestureUIHandler;
    public boolean mIsBackGestureAllowed;
    public boolean mIsInPipMode;
    public boolean mIsOnLeftEdge;
    public final boolean mIsOnSubDisplayId;
    public final boolean mIsSmallFold;
    public final int mLongPressTimeout;
    public final Executor mMainExecutor;
    public final Handler mMainHandler;
    public final SideGestureDetector$$ExternalSyntheticLambda2 mMisTouchToastDismiss;
    public final AnonymousClass2 mNavGestureProcessListener;
    public final float mNonLinearFactor;
    public final INotificationManager mNotificationManager;
    public final AnonymousClass6 mQuickNotesObserver;
    public int mRotation;
    public int mScreenHeight;
    public int mScreenWidth;
    public final SideGestureConfigurationEx mSideGestureConfiguration;
    public final SideGestureHomeHandleAnimation mSideGestureHomeHandleAnimation;
    public SideGestureViewManagerEx mSideGestureViewManager;
    public final AnonymousClass1 mStateChangedListener;
    public int mStatusBarHeight;
    public OplusSwitchCameraPreviewStartable mSwitchCameraPreviewStartable;
    public long mSysUiFlags;
    public final AnonymousClass5 mTaskStackListener;
    public float mTouchSlop;
    public final Rect mPipExcludedBounds = new Rect();
    public final Rect mNavBarOverlayExcludedBounds = new Rect();
    public final Region mExcludeRegion = new Region();
    public final Region mSideBarSceneRegion = new Region();
    public final Region mSmartBarRegion = new Region();
    public final RectF mQuickNotesRegionF = new RectF();
    public final PointF mDownPoint = new PointF();
    public final Point mDisplaySize = new Point();
    public final Long mMisTouchToastTime = 2000L;
    public boolean mIsWithinInsets = false;
    public boolean mThresholdCrossed = false;
    public boolean mAllowGesture = false;
    public boolean mNavBarHidden = false;
    public boolean mAllowGestureIgnoringBarVisibility = false;
    public boolean mWmsAllowGestureIgnoringBarVisibility = false;
    public boolean mIsExitMisTouchPreventionFlag = false;
    public boolean mIsFirstGestureInGameMode = false;
    public boolean mIsExitMisTouchPrevention = false;
    public boolean mIsStylusEvent = false;
    public float mGestureDonwPointInfloat_Y = 0.0f;
    public final Handler mGestureBGHandler = GestureBGThreadManager.INSTANCE.getGestureBGHandler();
    public int mLastMotionEventAction = -1;
    public boolean mIsMisTouchNeedInjectUpEvent = false;
    public Toast mMisTouchToast = null;
    public volatile long mLastBoostRequestTime = 0;

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements DeviceStateController.StateChangedListener {
        public /* synthetic */ SideGestureDetector this$0;

        @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
        public final void onStateChanged(int i) {
            this.this$0.mDeviceState = i;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements IObserverListener {
        public /* synthetic */ SideGestureDetector this$0;

        @Override // com.oplusos.systemui.common.observer.IObserverListener
        public final void onChange(boolean z) {
            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", "receive mNavGestureProcessListener + value = " + NavGestureProcessObserver.Companion.getInstance().getNavGestureProgressState());
            Handler handler = this.this$0.mGestureUIHandler;
            SideGestureDetector$$ExternalSyntheticLambda2 sideGestureDetector$$ExternalSyntheticLambda2 = new SideGestureDetector$$ExternalSyntheticLambda2(4);
            sideGestureDetector$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            handler.post(sideGestureDetector$$ExternalSyntheticLambda2);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements NavigationEdgeBackPlugin.BackCallback {
        public /* synthetic */ SideGestureDetector this$0;

        public final void cancelBack() {
            BackAnimation backAnimation = this.this$0.mBackAnimation;
            if (backAnimation != null) {
                backAnimation.setTriggerBack(false);
            }
        }

        public void setTriggerBack(boolean z) {
            BackAnimation backAnimation = this.this$0.mBackAnimation;
            if (backAnimation != null) {
                backAnimation.setTriggerBack(z);
            }
        }

        public final void triggerBack() {
            SideGestureDetector sideGestureDetector = this.this$0;
            String str = sideGestureDetector.mDisplayIdTag;
            sideGestureDetector.mFalsingManager.isFalseTouch(16);
            if (sideGestureDetector.mIsOnSubDisplayId) {
                LocalBroadcastManager.getInstance(sideGestureDetector.mContext).sendBroadcast(new Intent("app.intent.action.ACTION_SECONDHOME_BACK"));
                LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureDetector", " sendBroadcast ACTION_SECONDHOME_BACK ");
            }
            if (sideGestureDetector.mBackAnimation != null) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, ", BackAnimation Triggered back: down= displayId = ");
                sbM.append(sideGestureDetector.mContext.getDisplayId());
                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM.toString());
                sideGestureDetector.mBackAnimation.setTriggerBack(true);
                return;
            }
            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str + "  Triggered back: down=" + SideGestureDetector.m30$$Nest$msendEvent(sideGestureDetector, 0) + ", up=" + SideGestureDetector.m30$$Nest$msendEvent(sideGestureDetector, 1) + " displayId = " + sideGestureDetector.mContext.getDisplayId());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class AnonymousClass4 extends ISystemGestureExclusionListener.Stub {
        public AnonymousClass4() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSystemGestureExclusionChanged$0(Region region, Region region2) {
            SideGestureDetector.this.mExcludeRegion.set(region);
            LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", "onSystemGestureExclusionChanged:  excludeRegion=" + SideGestureDetector.this.mExcludeRegion + " unrestrictedOrNull=" + region2);
        }

        public void onSystemGestureExclusionChanged(int i, Region region, Region region2) {
            SideGestureDetector sideGestureDetector = SideGestureDetector.this;
            if (i == sideGestureDetector.mDisplayId) {
                SideGestureDetector$4$$ExternalSyntheticLambda0 sideGestureDetector$4$$ExternalSyntheticLambda0 = new SideGestureDetector$4$$ExternalSyntheticLambda0();
                sideGestureDetector$4$$ExternalSyntheticLambda0.f$0 = this;
                sideGestureDetector$4$$ExternalSyntheticLambda0.f$1 = region;
                sideGestureDetector$4$$ExternalSyntheticLambda0.f$2 = region2;
                VarHandle.storeStoreFence();
                Handler handler = sideGestureDetector.mGestureUIHandler;
                if (handler != null) {
                    handler.post(sideGestureDetector$4$$ExternalSyntheticLambda0);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements TaskStackChangeListener {
        public /* synthetic */ SideGestureDetector this$0;

        public final void onActivityPinned() {
            this.this$0.mIsInPipMode = true;
        }

        public final void onActivityUnpinned() {
            this.this$0.mIsInPipMode = false;
        }

        public final void onTaskCreated(int i, ComponentName componentName) {
            if (componentName != null) {
                componentName.getPackageName();
            }
        }

        public final void onTaskStackChanged() {
            AppSettingsHandler appSettingsHandler = AppSettingsHandler.getInstance(this.this$0.mContext);
            Executor executor = appSettingsHandler.mBgExecutor;
            AppSettingsHandler$$ExternalSyntheticLambda0 appSettingsHandler$$ExternalSyntheticLambda0 = new AppSettingsHandler$$ExternalSyntheticLambda0(1);
            appSettingsHandler$$ExternalSyntheticLambda0.f$0 = appSettingsHandler;
            VarHandle.storeStoreFence();
            executor.execute(appSettingsHandler$$ExternalSyntheticLambda0);
        }
    }

    public static /* synthetic */ void $r8$lambda$amvcDnjWy2zYLg4ZSWHWWPVzOWg(SideGestureDetector sideGestureDetector) {
        sideGestureDetector.mIsExitMisTouchPrevention = false;
        Toast toast = sideGestureDetector.mMisTouchToast;
        if (toast != null) {
            toast.cancel();
            sideGestureDetector.mMisTouchToast = null;
        }
        sideGestureDetector.setToastRateLimitingEnabled(true);
    }

    /* JADX INFO: renamed from: -$$Nest$msendEvent, reason: not valid java name */
    public static boolean m30$$Nest$msendEvent(SideGestureDetector sideGestureDetector, int i) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(jUptimeMillis, jUptimeMillis, i, 4, 0, 0, -1, 0, 72, 257);
        if (sideGestureDetector.mContext.getDisplay().getDisplayId() >= 10000) {
            keyEvent.setDisplayId(sideGestureDetector.mDisplayId);
        } else {
            keyEvent.setDisplayId(sideGestureDetector.mContext.getDisplay().getDisplayId());
        }
        return InputManagerGlobal.getInstance().injectInputEvent(keyEvent, 0);
    }

    /* JADX WARN: Type inference failed for: r6v13, types: [com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector$6] */
    /* JADX WARN: Type inference failed for: r6v14, types: [com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector$6] */
    public SideGestureDetector(Context context, Display display, List list, Executor executor, FalsingManager falsingManager, Handler handler, SideGestureConfigurationEx sideGestureConfigurationEx) {
        final int i = 0;
        this.mDisplayIdTag = "display_id_";
        this.mIsOnSubDisplayId = false;
        SideGestureDetector$$ExternalSyntheticLambda2 sideGestureDetector$$ExternalSyntheticLambda2 = new SideGestureDetector$$ExternalSyntheticLambda2(2);
        sideGestureDetector$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.mMisTouchToastDismiss = sideGestureDetector$$ExternalSyntheticLambda2;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStateChangedListener = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mNavGestureProcessListener = anonymousClass2;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mBackCallback = anonymousClass3;
        this.mGestureExclusionListener = new AnonymousClass4();
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mTaskStackListener = anonymousClass5;
        this.mContext = context;
        this.mDisplay = display;
        int displayId = context.getDisplayId();
        this.mDisplayId = displayId;
        this.mGestureUIHandler = handler;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mGestureBlockingActivities = list;
        this.mMainExecutor = executor;
        this.mFalsingManager = falsingManager;
        this.mDisplayIdTag = ListPopupWindow$.ExternalSyntheticOutline0.m(displayId, "display_id_");
        this.mLongPressTimeout = Math.min(MAX_LONG_PRESS_TIMEOUT, ViewConfiguration.getLongPressTimeout());
        this.mSideGestureConfiguration = sideGestureConfigurationEx;
        FeatureOption.INSTANCE.getClass();
        final int i2 = 1;
        this.mIsSmallFold = FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled();
        this.mDeviceState = DeviceStateController.getInstance().getState();
        SideGestureHomeHandleAnimation sideGestureHomeHandleAnimation = new SideGestureHomeHandleAnimation();
        sideGestureHomeHandleAnimation.mDisplaySize = new Point();
        sideGestureHomeHandleAnimation.mSideGestureConfiguration = sideGestureConfigurationEx;
        VarHandle.storeStoreFence();
        this.mSideGestureHomeHandleAnimation = sideGestureHomeHandleAnimation;
        ConfigurationOnSubDisplay.Companion.getInstance(context).getClass();
        boolean zIsSubDisplayId = ConfigurationOnSubDisplay.isSubDisplayId(displayId);
        this.mIsOnSubDisplayId = zIsSubDisplayId;
        if (zIsSubDisplayId) {
            this.mRotation = SecondaryRotationWatcher.Companion.getSecondaryRotation();
        } else {
            this.mRotation = this.mDisplay.getRotation();
        }
        context.getResources().getDimension(2131170470);
        this.mBackSwipeProgressThreshold = context.getResources().getDimension(2131170472);
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        resources.getValue(2131165586, typedValue, true);
        this.mNonLinearFactor = typedValue.getFloat();
        this.mNotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        this.mContentObserver = new ContentObserver(this, context.getMainThreadHandler()) { // from class: com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector.6
            public final /* synthetic */ SideGestureDetector this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        this.this$0.updateSideBarSceneRegion();
                        break;
                    default:
                        this.this$0.updateQuickNotesRegion();
                        break;
                }
            }
        };
        this.mQuickNotesObserver = new ContentObserver(this, context.getMainThreadHandler()) { // from class: com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector.6
            public final /* synthetic */ SideGestureDetector this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        this.this$0.updateSideBarSceneRegion();
                        break;
                    default:
                        this.this$0.updateQuickNotesRegion();
                        break;
                }
            }
        };
    }

    private boolean getNavBarHiddenStatus() {
        return this.mNavBarHidden;
    }

    private int getSide() {
        return !this.mIsOnLeftEdge ? 1 : 0;
    }

    private OplusSwitchCameraPreviewStartable getSwitchCameraPreviewStartable() {
        if (this.mSwitchCameraPreviewStartable == null) {
            this.mSwitchCameraPreviewStartable = (OplusSwitchCameraPreviewStartable) DependencyEx.sDependency.getDependency(OplusSwitchCameraPreviewStartable.class);
        }
        return this.mSwitchCameraPreviewStartable;
    }

    private void setToastRateLimitingEnabled(boolean z) {
        Runnable externalSyntheticLambda0 = new SideGestureDetector$.ExternalSyntheticLambda0();
        ((SideGestureDetector$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$0 = this;
        ((SideGestureDetector$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$1 = z;
        VarHandle.storeStoreFence();
        this.mGestureBGHandler.post(externalSyntheticLambda0);
    }

    public final void cancelGesture(MotionEvent motionEvent, Boolean bool, Boolean bool2) {
        this.mAllowGesture = false;
        if (!bool.booleanValue()) {
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            try {
                motionEventObtain.setAction(3);
                this.mBackCallback.cancelBack();
                if (bool2.booleanValue()) {
                    EventInterceptInjectHelper eventInterceptInjectHelper = (EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get();
                    if (!eventInterceptInjectHelper.thresholdCrossed) {
                        eventInterceptInjectHelper.injectAllEvent();
                    }
                }
                ((EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get()).resetGestureDownState();
                dispatchToBackAnimation(motionEventObtain);
                motionEventObtain.recycle();
            } catch (Throwable th) {
                motionEventObtain.recycle();
                throw th;
            }
        }
        OplusSysUiSchedMgr.getInstance().releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.EDGE_SWIPE_BACK_UI);
        OplusSysUiSchedMgr.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.EDGE_SWIPE_BACK_EXIT);
        resetGestureState();
    }

    public final void dispatchToBackAnimation(MotionEvent motionEvent) {
        BackAnimation backAnimation = this.mBackAnimation;
        if (backAnimation != null) {
            backAnimation.onBackMotion(motionEvent.getX(), motionEvent.getY(), motionEvent.getActionMasked(), !this.mIsOnLeftEdge ? 1 : 0, motionEvent.getDisplayId());
        }
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println("SideGestureDetector:");
        printWriter.println("  mGestureBlockingActivityRunning=false");
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m(new StringBuilder("  mAllowGesture="), this.mAllowGesture, printWriter, "  mExcludeRegion=");
        sbM.append(CommonUtils.safeRegionToString(this.mExcludeRegion));
        printWriter.println(sbM.toString());
        printWriter.println("  mSideBarSceneRegion=" + CommonUtils.safeRegionToString(this.mSideBarSceneRegion));
        printWriter.println("  mSmartBarRegion=" + CommonUtils.safeRegionToString(this.mSmartBarRegion));
        printWriter.println("  mPipExcludedBounds=" + this.mPipExcludedBounds);
        ((EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get()).dump(printWriter);
    }

    public ISystemGestureExclusionListener getGestureExclusionListener() {
        return this.mGestureExclusionListener;
    }

    public TaskStackChangeListener getTaskStackListener() {
        return this.mTaskStackListener;
    }

    public final void onConfigChanged(int i) {
        resetGestureState();
        this.mRotation = i;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDisplayIdTag);
        sb.append("  onConfigChanged mRotation = ");
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mRotation, LogUtil.TAG_NAVBAR, "NoBackGesture", sb);
        this.mSideGestureConfiguration.updateSideGestureAreaWidth();
    }

    /* JADX WARN: Code duplicated, block: B:103:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:106:0x032c  */
    /* JADX WARN: Code duplicated, block: B:108:0x0334  */
    /* JADX WARN: Code duplicated, block: B:109:0x0336  */
    /* JADX WARN: Code duplicated, block: B:116:0x0346  */
    /* JADX WARN: Code duplicated, block: B:118:0x0350  */
    /* JADX WARN: Code duplicated, block: B:119:0x0352  */
    /* JADX WARN: Code duplicated, block: B:122:0x035f  */
    /* JADX WARN: Code duplicated, block: B:123:0x0361  */
    /* JADX WARN: Code duplicated, block: B:125:0x036d  */
    /* JADX WARN: Code duplicated, block: B:139:0x03c0  */
    /* JADX WARN: Code duplicated, block: B:142:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:148:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:154:0x0410  */
    /* JADX WARN: Code duplicated, block: B:156:0x0414  */
    /* JADX WARN: Code duplicated, block: B:158:0x041a  */
    /* JADX WARN: Code duplicated, block: B:173:0x0451  */
    /* JADX WARN: Code duplicated, block: B:178:0x045b  */
    /* JADX WARN: Code duplicated, block: B:180:0x045e  */
    /* JADX WARN: Code duplicated, block: B:182:0x0472  */
    /* JADX WARN: Code duplicated, block: B:183:0x0475  */
    /* JADX WARN: Code duplicated, block: B:186:0x047d  */
    /* JADX WARN: Code duplicated, block: B:187:0x047f  */
    /* JADX WARN: Code duplicated, block: B:190:0x0489  */
    /* JADX WARN: Code duplicated, block: B:191:0x048c  */
    /* JADX WARN: Code duplicated, block: B:195:0x0493  */
    /* JADX WARN: Code duplicated, block: B:197:0x04c5  */
    /* JADX WARN: Code duplicated, block: B:199:0x04d7 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:205:0x04e2  */
    /* JADX WARN: Code duplicated, block: B:208:0x04ec  */
    /* JADX WARN: Code duplicated, block: B:209:0x0508  */
    /* JADX WARN: Code duplicated, block: B:212:0x0520  */
    /* JADX WARN: Code duplicated, block: B:217:0x053f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:221:0x0547  */
    /* JADX WARN: Code duplicated, block: B:320:0x07d8 A[PHI: r16
      0x07d8: PHI (r16v3 boolean) = (r16v2 boolean), (r16v2 boolean), (r16v5 boolean), (r16v5 boolean) binds: [B:329:0x0807, B:331:0x080d, B:317:0x07d0, B:319:0x07d6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:98:0x02d3  */
    /* JADX WARN: Instruction removed from duplicated block: B:122:0x035f, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:180:0x045e, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:209:0x0508, please report this as an issue */
    public final void onMotionEventImpl(MotionEvent motionEvent, Map map) {
        long j;
        int i;
        boolean z;
        int i2;
        EventInterceptInjectHelper.INSTANCE instance;
        boolean zContains;
        boolean z2;
        boolean z3;
        int toolType;
        boolean z4;
        boolean z5;
        int x;
        int y;
        long j2;
        boolean z6;
        Region region;
        boolean z7;
        RectF rectF;
        boolean z8;
        boolean z9;
        String str;
        String str2;
        String str3;
        int i3;
        HashMap map2 = new HashMap(map);
        this.mDisplayBackGestureHandlers = map2;
        DisplayBackGestureHandler displayBackGestureHandler = (DisplayBackGestureHandler) map2.get(Integer.valueOf(motionEvent.getDisplayId()));
        if (displayBackGestureHandler == null) {
            Log.e("SideGestureDetector", "Received MotionEvent on unknown display");
            return;
        }
        if (!OplusSystemUIApplicationUtils.isMainSystemUiThread()) {
            LogUtil.e(LogUtil.TAG_NAVBAR, "SideGestureDetector", "onMotionEventImpl: Skipping due to non-primary user");
            return;
        }
        EventInterceptInjectHelper.INSTANCE instance2 = EventInterceptInjectHelper.INSTANCE;
        boolean zIsMotionEventInvalid = ((EventInterceptInjectHelper) instance2.get()).isMotionEventInvalid(motionEvent);
        int i4 = 0;
        int i5 = 1;
        boolean z10 = this.mIsSmallFold && this.mDeviceState == 0;
        boolean z11 = zIsMotionEventInvalid && shouldInjectImmediate();
        if (motionEvent.getAction() != 2) {
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("isInvalidEvent:", " ,isMisTouchInvalidEvent = ", "  ,disableCui:", zIsMotionEventInvalid, z11);
            sbM.append(z10);
            sbM.append("  ,ev:");
            sbM.append(motionEvent);
            LogUtil.i(LogUtil.TAG_NAVBAR, "SideGestureDetector", sbM.toString());
        }
        if (!z10) {
            int action = motionEvent.getAction();
            Handler handler = this.mMainHandler;
            if (action != 0) {
                SideGestureDetector$$ExternalSyntheticLambda3 sideGestureDetector$$ExternalSyntheticLambda3 = new SideGestureDetector$$ExternalSyntheticLambda3(i5);
                sideGestureDetector$$ExternalSyntheticLambda3.f$0 = this;
                sideGestureDetector$$ExternalSyntheticLambda3.f$1 = motionEvent;
                VarHandle.storeStoreFence();
                handler.post(sideGestureDetector$$ExternalSyntheticLambda3);
            } else if (!zIsMotionEventInvalid) {
                SideGestureDetector$$ExternalSyntheticLambda3 sideGestureDetector$$ExternalSyntheticLambda4 = new SideGestureDetector$$ExternalSyntheticLambda3(i4);
                sideGestureDetector$$ExternalSyntheticLambda4.f$0 = this;
                sideGestureDetector$$ExternalSyntheticLambda4.f$1 = motionEvent;
                VarHandle.storeStoreFence();
                handler.post(sideGestureDetector$$ExternalSyntheticLambda4);
            }
        }
        String str4 = this.mDisplayIdTag;
        if (zIsMotionEventInvalid) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + "Invalid gesture trace ev : " + motionEvent);
            if (motionEvent.getActionMasked() != 0 || (i3 = this.mLastMotionEventAction) == -1 || i3 == 1) {
                if (!shouldInjectImmediate()) {
                    return;
                }
            } else {
                if (shouldInjectImmediate()) {
                    this.mIsMisTouchNeedInjectUpEvent = false;
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str4);
                sb.append("systemui cancel gesture trace ev : ");
                sb.append(motionEvent);
                sb.append(" ,mAllowGesture = ");
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_NAVBAR, "NoBackGesture", sb, this.mAllowGesture);
                motionEvent.setAction(3);
                if (this.mAllowGesture) {
                    SideGestureViewManagerEx sideGestureViewManagerEx = this.mSideGestureViewManager;
                    if (sideGestureViewManagerEx != null) {
                        sideGestureViewManagerEx.onGestureFinished(new GesturePointContainer(new PointF(motionEvent.getX(), motionEvent.getY()), 1, getSide(), this.mRotation));
                        this.mSideGestureViewManager.onUpEvent(getSide());
                    }
                    cancelGesture(motionEvent, Boolean.valueOf(z11), Boolean.FALSE);
                }
            }
        }
        int actionMasked = motionEvent.getActionMasked();
        this.mLastMotionEventAction = actionMasked;
        SideGestureConfigurationEx sideGestureConfigurationEx = this.mSideGestureConfiguration;
        if (actionMasked != 0) {
            if (!this.mAllowGesture) {
                boolean z12 = z11;
                if ((actionMasked == 1 || actionMasked == 6) && !z12) {
                    ((EventInterceptInjectHelper) instance2.get()).addUpEvent(this.mIsWithinInsets, motionEvent);
                }
                if (!this.mIsWithinInsets || z12) {
                    return;
                }
                ((EventInterceptInjectHelper) instance2.get()).addOtherEvent(motionEvent);
                return;
            }
            Trace.traceBegin(8L, "SideGestureDetctor");
            if (!z11) {
                ((EventInterceptInjectHelper) instance2.get()).addAllowGestureEvent(motionEvent);
            }
            if (this.mThresholdCrossed) {
                j = 8;
                i = 4;
            } else {
                if (actionMasked == 5) {
                    if (Utilities.isTrackpadThreeFingerSwipe(motionEvent)) {
                        if (motionEvent.getPointerCount() == 3) {
                            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Cancel back: multitouch TRACK_PAD_THREE_FINGER");
                            cancelGesture(motionEvent, Boolean.valueOf(z11), Boolean.TRUE);
                            return;
                        }
                        return;
                    }
                    if (!OplusNavBarUtils.isTrackpadFourFingerSwipe(motionEvent)) {
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Cancel back: multitouch");
                        cancelGesture(motionEvent, Boolean.valueOf(z11), Boolean.TRUE);
                        return;
                    }
                    if (motionEvent.getPointerCount() == 4) {
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Cancel back: multitouch TRACK_PAD_FOUR_FINGER");
                        cancelGesture(motionEvent, Boolean.valueOf(z11), Boolean.TRUE);
                        return;
                    }
                    return;
                }
                i = 4;
                if (actionMasked == 2) {
                    long eventTime = motionEvent.getEventTime() - motionEvent.getDownTime();
                    int i6 = this.mLongPressTimeout;
                    j = 8;
                    if (eventTime > i6) {
                        cancelGesture(motionEvent, Boolean.valueOf(z11), Boolean.TRUE);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str4);
                        sb2.append(" Cancel back [longpress]: ");
                        sb2.append(motionEvent.getEventTime());
                        sb2.append("  ");
                        sb2.append(motionEvent.getDownTime());
                        sb2.append("  ");
                        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(i6, LogUtil.TAG_NAVBAR, "NoBackGesture", sb2);
                        return;
                    }
                    float fAbs = Math.abs(motionEvent.getX() - this.mDownPoint.x);
                    float fAbs2 = Math.abs(motionEvent.getY() - this.mDownPoint.y);
                    float f = this.mDownPoint.y > motionEvent.getY() ? 1.0f : 2.5f;
                    if (fAbs * f < fAbs2 && fAbs > 0.0f && fAbs2 > this.mTouchSlop) {
                        cancelGesture(motionEvent, Boolean.valueOf(z11), Boolean.TRUE);
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Cancel back [vertical move]: dy=" + fAbs2 + ", dx" + fAbs + ", angleScale=" + f);
                        return;
                    }
                    int sideGestureAreaWidthLeft = this.mIsOnLeftEdge ? sideGestureConfigurationEx.getSideGestureAreaWidthLeft() : sideGestureConfigurationEx.getSideGestureAreaWidthRight();
                    if (fAbs > 9.0f && ((motionEvent.getX() - this.mDownPoint.x < 0.0f && this.mIsOnLeftEdge) || (!this.mIsOnLeftEdge && motionEvent.getX() - this.mDownPoint.x > 0.0f))) {
                        cancelGesture(motionEvent, Boolean.valueOf(z11), Boolean.TRUE);
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Cancel back [Negative direction]: ");
                        return;
                    }
                    if (fAbs > sideGestureAreaWidthLeft / 8.0f) {
                        this.mThresholdCrossed = true;
                        ((EventInterceptInjectHelper) instance2.get()).setThresholdCrossed(true);
                        if (this.mSideGestureViewManager != null && shouldRespondToGesture()) {
                            OplusSysUiSchedMgr.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.EDGE_SWIPE_BACK_UI);
                            this.mSideGestureViewManager.onDownEvent(getSide(), motionEvent, this.mRotation);
                        }
                        if (shouldRespondToGesture()) {
                            BackAnimation backAnimation = this.mBackAnimation;
                            if (backAnimation != null) {
                                backAnimation.onThresholdCrossed();
                                LogUtil.i(LogUtil.TAG_NAVBAR, "SideGestureDetector", "BackAnimation onThresholdCrossed");
                            } else {
                                Map map3 = this.mDisplayBackGestureHandlers;
                                DisplayBackGestureHandler displayBackGestureHandler2 = map3 != null ? (DisplayBackGestureHandler) map3.get(Integer.valueOf(this.mDisplayId)) : null;
                                if (displayBackGestureHandler2 != null) {
                                    displayBackGestureHandler2.setBatchingEnabled(true);
                                }
                            }
                        }
                    }
                } else {
                    j = 8;
                }
            }
            boolean z13 = ((float) ((int) motionEvent.getY())) > ((float) this.mScreenHeight) * 0.0f;
            int side = getSide();
            int x2 = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            int screenHeight = sideGestureConfigurationEx.getScreenHeight(this.mRotation);
            int screenWidth = sideGestureConfigurationEx.getScreenWidth(this.mRotation);
            if (y2 < screenHeight * 0.0f) {
                i2 = i;
                z = z11;
            } else if (side == 0) {
                z = z11;
                int cancelDistance = sideGestureConfigurationEx.getCancelDistance(x2, screenWidth, true);
                if (x2 >= 0 && x2 <= cancelDistance) {
                    StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("Left Gesture cancel: x_px ", x2, cancelDistance, ", CancelDistance: ", " px, BackDistance: ");
                    sbM2.append(sideGestureConfigurationEx.getBackDistance());
                    sbM2.append(" px");
                    LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureDetector", sbM2.toString());
                } else if (x2 > sideGestureConfigurationEx.getMinCancelDistance() && (x2 <= screenWidth || OplusNavBarUtils.isTwoFingerSwipeTrackpadEvent(motionEvent))) {
                    i2 = 2;
                }
                i2 = 1;
            } else {
                z = z11;
                if (side == 1) {
                    int cancelDistance2 = sideGestureConfigurationEx.getCancelDistance(x2, screenWidth, false);
                    if (x2 < screenWidth && x2 >= cancelDistance2) {
                        StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("Right Gesture cancel: x_px = ", x2, cancelDistance2, ", CancelDistance: ", " px, BackDistance: ");
                        sbM3.append(sideGestureConfigurationEx.getBackDistance());
                        sbM3.append(" px");
                        LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureDetector", sbM3.toString());
                    } else if (x2 < screenWidth - sideGestureConfigurationEx.getMinCancelDistance() && (x2 >= 0 || OplusNavBarUtils.isTwoFingerSwipeTrackpadEvent(motionEvent))) {
                        i2 = 2;
                    }
                }
                i2 = 1;
            }
            if (this.mSideGestureViewManager != null && shouldRespondToGesture() && actionMasked != 3) {
                this.mSideGestureViewManager.onUpdateGestureView(new GesturePointContainer(new PointF(motionEvent.getX(), motionEvent.getY()), i2, getSide(), this.mRotation));
            }
            boolean z14 = actionMasked == 1;
            boolean z15 = z14 && i2 == 2 && z13;
            if (actionMasked == 2 && shouldRespondToGesture()) {
                dispatchToBackAnimation(motionEvent);
            }
            if (z14) {
                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Gesture ACTION_UP: inYTouchRegion = " + z13 + ", state = " + i2 + " ,mThresholdCrossed =" + this.mThresholdCrossed + " ,upEvent = " + motionEvent);
                if (shouldRespondToGesture()) {
                    LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureDetector", "Response to side gesture");
                    SideGestureDetector$$ExternalSyntheticLambda2 sideGestureDetector$$ExternalSyntheticLambda2 = new SideGestureDetector$$ExternalSyntheticLambda2(1);
                    sideGestureDetector$$ExternalSyntheticLambda2.f$0 = this;
                    VarHandle.storeStoreFence();
                    this.mGestureBGHandler.post(sideGestureDetector$$ExternalSyntheticLambda2);
                    SideGestureViewManagerEx sideGestureViewManagerEx2 = this.mSideGestureViewManager;
                    if (sideGestureViewManagerEx2 != null) {
                        sideGestureViewManagerEx2.onGestureFinished(new GesturePointContainer(new PointF(motionEvent.getX(), motionEvent.getY()), i2, getSide(), this.mRotation));
                    }
                    if (z15) {
                        JankUtils.asyncLatencySceneBegin("SideGesture", 1, "performBackKey");
                        this.mBackCallback.triggerBack();
                        getSwitchCameraPreviewStartable().disableCameraPreview();
                    }
                    dispatchToBackAnimation(motionEvent);
                    SideGestureViewManagerEx sideGestureViewManagerEx3 = this.mSideGestureViewManager;
                    if (sideGestureViewManagerEx3 != null) {
                        sideGestureViewManagerEx3.onUpEvent(getSide());
                    }
                    OplusSysUiSchedMgr.getInstance().releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.EDGE_SWIPE_BACK_UI);
                    OplusSysUiSchedMgr.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.EDGE_SWIPE_BACK_EXIT);
                } else if (getNavBarHiddenStatus() && !this.mAllowGestureIgnoringBarVisibility && !this.mWmsAllowGestureIgnoringBarVisibility && !this.mIsExitMisTouchPreventionFlag && z15) {
                    LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureDetector", "show misTouch toast");
                    setToastRateLimitingEnabled(false);
                    Context context = this.mContext;
                    Toast toastMakeText = Toast.makeText(context, context.getString(2131956380), 0);
                    this.mMisTouchToast = toastMakeText;
                    toastMakeText.show();
                    this.mIsExitMisTouchPrevention = true;
                    updateGestureAndNavBarStates();
                    this.mGestureUIHandler.postDelayed(this.mMisTouchToastDismiss, this.mMisTouchToastTime.longValue());
                }
                this.mAllowGesture = false;
            } else if (actionMasked == 3) {
                if (this.mSideGestureViewManager != null && shouldRespondToGesture()) {
                    this.mSideGestureViewManager.onGestureFinished(new GesturePointContainer(new PointF(motionEvent.getX(), motionEvent.getY()), i2, getSide(), this.mRotation));
                    this.mSideGestureViewManager.onUpEvent(getSide());
                }
                cancelGesture(motionEvent, Boolean.valueOf(z), Boolean.TRUE);
            }
            if (actionMasked == 1 || actionMasked == 3) {
                if (this.mIsMisTouchNeedInjectUpEvent && this.mThresholdCrossed) {
                    ((EventInterceptInjectHelper) instance2.get()).injectValidEvent(motionEvent);
                    resetEventInterceptInjectState();
                } else {
                    ((EventInterceptInjectHelper) instance2.get()).onUpOrCancel();
                }
                this.mIsMisTouchNeedInjectUpEvent = false;
            }
            Trace.traceEnd(j);
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str4);
        sb3.append(" Start gesture: ");
        sb3.append(motionEvent);
        sb3.append(" ,mRotation = ");
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mRotation, LogUtil.TAG_NAVBAR, "NoBackGesture", sb3);
        this.mAllowGesture = false;
        displayBackGestureHandler.setBatchingEnabled(false);
        this.mIsOnLeftEdge = motionEvent.getX() <= ((float) sideGestureConfigurationEx.getSideGestureAreaWidthLeft());
        this.mGestureDonwPointInfloat_Y = motionEvent.getY();
        float x3 = motionEvent.getX();
        float y3 = motionEvent.getY();
        int bottomGestureAreaHeight = sideGestureConfigurationEx.getBottomGestureAreaHeight();
        boolean zIsSupportHotZoneAvoid = NavBarUtils.isSupportHotZoneAvoid(this.mContext);
        boolean z16 = this.mIsOnSubDisplayId;
        if (zIsSupportHotZoneAvoid) {
            instance = instance2;
            int iConvertDpToPixel = DisplaySizeUtils.convertDpToPixel(this.mContext, 50.0f);
            if (y3 >= this.mDisplaySize.y - iConvertDpToPixel) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str4);
                sb4.append("isSupportHotZoneAvoid ,avoid_px:");
                sb4.append(iConvertDpToPixel);
                sb4.append("  mDisplaySize.y = ");
                IconProvider$.ExternalSyntheticOutline0.m(x3, this.mDisplaySize.y, " x = ", " y = ", sb4);
                sb4.append(y3);
                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sb4.toString());
            }
            z2 = false;
            this.mIsWithinInsets = z2;
            if (z2) {
                if (!motionEvent.isFromSource(16386) && ((toolType = motionEvent.getToolType(0)) == 2 || toolType == 4)) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                this.mIsStylusEvent = z4;
                if (z4) {
                    IpeSlideGestureRejectionObserver.INSTANCE.getClass();
                    if (IpeSlideGestureRejectionObserver.ipeSlideGestureState == 1) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                } else {
                    z5 = false;
                }
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                if (!z16) {
                    j2 = this.mSysUiFlags;
                    if ((32768 & j2) != 0 && (33554432 & j2) == 0) {
                        if ((4398046511104L & j2) != 0) {
                            LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + "SYSUI_STATE_KEYGUARD_IMMERSIVE_SHOWING");
                        } else {
                            if ((131072 & j2) != 0) {
                                j2 &= -3;
                                LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + "SYSUI_STATE_ALLOW_GESTURE_IGNORING_BAR_VISIBILITY");
                            }
                            z6 = (j2 & 64) != 0;
                        }
                    }
                }
                if ((this.mIsInPipMode || !this.mPipExcludedBounds.contains(x, y)) && !this.mNavBarOverlayExcludedBounds.contains(x, y)) {
                    region = this.mExcludeRegion;
                    int i7 = this.mDisplaySize.x;
                    if (region == null && (region.contains(x, y) || (x == i7 && region.contains(x - 1, y)))) {
                        StringBuilder sbM4 = Bundler$.ExternalSyntheticOutline0.m(str4, " mExcludeRegion ");
                        sbM4.append(this.mExcludeRegion);
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM4.toString());
                    } else {
                        if (this.mIsStylusEvent) {
                            rectF = this.mQuickNotesRegionF;
                            float f2 = x;
                            float f3 = y;
                            if (rectF == null && !rectF.isEmpty() && f2 >= rectF.left && f2 <= rectF.right && f3 >= rectF.top && f3 <= rectF.bottom) {
                                StringBuilder sbM5 = Bundler$.ExternalSyntheticOutline0.m(str4, " mQuickNotesRegionF ");
                                sbM5.append(this.mQuickNotesRegionF);
                                LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM5.toString());
                            }
                            if (this.mDisabledForQuickstep && this.mIsBackGestureAllowed && !z6 && z7) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            if (z8) {
                                boolean zCheckSideGesture = OplusSideGesturePreProcess.checkSideGesture(this.mContext, y, this.mSideGestureViewManager.isExitAnimFinished(getSide()));
                                if (!z8 && zCheckSideGesture && !z5) {
                                    z9 = true;
                                }
                                this.mAllowGesture = z9;
                                if (z9) {
                                    resetGestureState();
                                    updateGestureAndNavBarStates();
                                }
                                if (LogUtil.panicEnabled) {
                                    StringBuilder sbM6 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                                    sbM6.append(this.mAllowGesture);
                                    sbM6.append(" ,isStylusEvent = ");
                                    LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM6, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                                }
                            } else {
                                StringBuilder sbM7 = Bundler$.ExternalSyntheticOutline0.m(str4, " AllowGesture false cuz: ");
                                StringJoiner stringJoiner = new StringJoiner("|");
                                if (this.mDisabledForQuickstep) {
                                    str = "disabled for quickstep";
                                } else {
                                    str = "";
                                }
                                stringJoiner.add(str);
                                if (this.mIsBackGestureAllowed) {
                                    str2 = "";
                                } else {
                                    str2 = "back Gesture not allowed";
                                }
                                stringJoiner.add(str2);
                                stringJoiner.add("");
                                if (z6) {
                                    str3 = "back gesture disabled by sysui flags";
                                } else {
                                    str3 = "";
                                }
                                stringJoiner.add(str3);
                                stringJoiner.add(z7 ? "" : "touch region not valid");
                                sbM7.append(stringJoiner.toString());
                                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM7.toString());
                                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " current sysUiFlags: " + QuickStepContract.getSystemUiStateString(this.mSysUiFlags));
                            }
                            z9 = false;
                            this.mAllowGesture = z9;
                            if (z9) {
                                resetGestureState();
                                updateGestureAndNavBarStates();
                            }
                            if (LogUtil.panicEnabled) {
                                StringBuilder sbM8 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                                sbM8.append(this.mAllowGesture);
                                sbM8.append(" ,isStylusEvent = ");
                                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM8, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                            }
                        }
                        z7 = true;
                        if (this.mDisabledForQuickstep) {
                            z8 = false;
                        } else {
                            z8 = false;
                        }
                        if (z8) {
                            StringBuilder sbM9 = Bundler$.ExternalSyntheticOutline0.m(str4, " AllowGesture false cuz: ");
                            StringJoiner stringJoiner2 = new StringJoiner("|");
                            if (this.mDisabledForQuickstep) {
                                str = "disabled for quickstep";
                            } else {
                                str = "";
                            }
                            stringJoiner2.add(str);
                            if (this.mIsBackGestureAllowed) {
                                str2 = "";
                            } else {
                                str2 = "back Gesture not allowed";
                            }
                            stringJoiner2.add(str2);
                            stringJoiner2.add("");
                            if (z6) {
                                str3 = "back gesture disabled by sysui flags";
                            } else {
                                str3 = "";
                            }
                            stringJoiner2.add(str3);
                            stringJoiner2.add(z7 ? "" : "touch region not valid");
                            sbM9.append(stringJoiner2.toString());
                            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM9.toString());
                            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " current sysUiFlags: " + QuickStepContract.getSystemUiStateString(this.mSysUiFlags));
                        } else {
                            boolean zCheckSideGesture2 = OplusSideGesturePreProcess.checkSideGesture(this.mContext, y, this.mSideGestureViewManager.isExitAnimFinished(getSide()));
                            if (!z8) {
                            }
                            this.mAllowGesture = z9;
                            if (z9) {
                                resetGestureState();
                                updateGestureAndNavBarStates();
                            }
                            if (LogUtil.panicEnabled) {
                                StringBuilder sbM10 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                                sbM10.append(this.mAllowGesture);
                                sbM10.append(" ,isStylusEvent = ");
                                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM10, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                            }
                        }
                        z9 = false;
                        this.mAllowGesture = z9;
                        if (z9) {
                            resetGestureState();
                            updateGestureAndNavBarStates();
                        }
                        if (LogUtil.panicEnabled) {
                            StringBuilder sbM11 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                            sbM11.append(this.mAllowGesture);
                            sbM11.append(" ,isStylusEvent = ");
                            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM11, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                        }
                    }
                } else {
                    StringBuilder sbM12 = Bundler$.ExternalSyntheticOutline0.m(str4, " mPipExcludedBounds ");
                    sbM12.append(this.mPipExcludedBounds);
                    LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM12.toString());
                }
                z7 = false;
                if (this.mDisabledForQuickstep) {
                    z8 = false;
                } else {
                    z8 = false;
                }
                if (z8) {
                    StringBuilder sbM13 = Bundler$.ExternalSyntheticOutline0.m(str4, " AllowGesture false cuz: ");
                    StringJoiner stringJoiner3 = new StringJoiner("|");
                    if (this.mDisabledForQuickstep) {
                        str = "disabled for quickstep";
                    } else {
                        str = "";
                    }
                    stringJoiner3.add(str);
                    if (this.mIsBackGestureAllowed) {
                        str2 = "";
                    } else {
                        str2 = "back Gesture not allowed";
                    }
                    stringJoiner3.add(str2);
                    stringJoiner3.add("");
                    if (z6) {
                        str3 = "back gesture disabled by sysui flags";
                    } else {
                        str3 = "";
                    }
                    stringJoiner3.add(str3);
                    stringJoiner3.add(z7 ? "" : "touch region not valid");
                    sbM13.append(stringJoiner3.toString());
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM13.toString());
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " current sysUiFlags: " + QuickStepContract.getSystemUiStateString(this.mSysUiFlags));
                } else {
                    boolean zCheckSideGesture3 = OplusSideGesturePreProcess.checkSideGesture(this.mContext, y, this.mSideGestureViewManager.isExitAnimFinished(getSide()));
                    if (!z8) {
                    }
                    this.mAllowGesture = z9;
                    if (z9) {
                        resetGestureState();
                        updateGestureAndNavBarStates();
                    }
                    if (LogUtil.panicEnabled) {
                        StringBuilder sbM14 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                        sbM14.append(this.mAllowGesture);
                        sbM14.append(" ,isStylusEvent = ");
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM14, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                    }
                }
                z9 = false;
                this.mAllowGesture = z9;
                if (z9) {
                    resetGestureState();
                    updateGestureAndNavBarStates();
                }
                if (LogUtil.panicEnabled) {
                    StringBuilder sbM15 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                    sbM15.append(this.mAllowGesture);
                    sbM15.append(" ,isStylusEvent = ");
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM15, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                }
            } else {
                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Not in side gesture area");
            }
            if (this.mAllowGesture && shouldRespondToGesture()) {
                dispatchToBackAnimation(motionEvent);
            }
            this.mDownPoint.set(motionEvent.getX(), motionEvent.getY());
            this.mThresholdCrossed = false;
            if (shouldInjectImmediate() || zIsMotionEventInvalid || !this.mAllowGesture) {
                z3 = false;
            } else {
                z3 = true;
            }
            this.mIsMisTouchNeedInjectUpEvent = z3;
            ((EventInterceptInjectHelper) instance.get()).setThresholdCrossed(false);
            ((EventInterceptInjectHelper) instance.get()).addDownEvent(this.mIsWithinInsets, this.mAllowGesture, shouldInjectImmediate(), motionEvent);
        }
        instance = instance2;
        Point point = this.mDisplaySize;
        if (y3 >= point.y - bottomGestureAreaHeight) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str4);
            sb5.append(" bottomGestureAreaHeight = ");
            sb5.append(bottomGestureAreaHeight);
            sb5.append("  mDisplaySize.y = ");
            IconProvider$.ExternalSyntheticOutline0.m(x3, this.mDisplaySize.y, " x = ", " y = ", sb5);
            sb5.append(y3);
            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sb5.toString());
        } else {
            if (y3 > this.mStatusBarHeight) {
                if (x3 >= 0.0f) {
                    int i8 = point.x;
                    if (x3 > i8 || (x3 < i8 - sideGestureConfigurationEx.getSideGestureAreaWidthRight() && x3 > sideGestureConfigurationEx.getSideGestureAreaWidthLeft())) {
                        StringBuilder sbM16 = Bundler$.ExternalSyntheticOutline0.m(str4, " isWithinInsets mDisplaySize.x = ");
                        sbM16.append(this.mDisplaySize.x);
                        sbM16.append("  mDisplaySize.y = ");
                        sbM16.append(this.mDisplaySize.y);
                        sbM16.append(" mSideGestureAreaWidthRight = ");
                        sbM16.append(sideGestureConfigurationEx.getSideGestureAreaWidthRight());
                        sbM16.append(" mSideGestureAreaWidthLeft ");
                        sbM16.append(sideGestureConfigurationEx.getSideGestureAreaWidthLeft());
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM16.toString());
                    } else {
                        int i9 = (int) x3;
                        int i10 = (int) y3;
                        if (z16 || this.mSideBarSceneRegion.isEmpty()) {
                            zContains = false;
                        } else {
                            this.mSmartBarRegion.set(this.mSideBarSceneRegion);
                            int i11 = this.mSmartBarRegion.getBounds().left;
                            int i12 = this.mDisplaySize.x;
                            int i13 = i12 / 2;
                            Region region2 = this.mSmartBarRegion;
                            if (i11 < i13) {
                                region2.set(0, region2.getBounds().top, sideGestureConfigurationEx.getSideGestureAreaWidthLeft(), this.mSmartBarRegion.getBounds().bottom);
                            } else {
                                region2.set(i12 - sideGestureConfigurationEx.getSideGestureAreaWidthRight(), this.mSmartBarRegion.getBounds().top, this.mDisplaySize.x, this.mSmartBarRegion.getBounds().bottom);
                            }
                            StringBuilder sbM17 = Bundler$.ExternalSyntheticOutline0.m(str4, " isInSmartBarRegion mSmartBarRegion =  ");
                            sbM17.append(this.mSmartBarRegion);
                            sbM17.append(" ,mSideBarSceneRegion = ");
                            sbM17.append(this.mSideBarSceneRegion);
                            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM17.toString());
                            int i14 = this.mRotation;
                            if (i14 != 1 && i14 != 2) {
                                zContains = this.mSmartBarRegion.contains(i9, i10);
                            } else if (this.mSmartBarRegion.contains(i9, i10) || this.mSmartBarRegion.getBounds().bottom == this.mGestureDonwPointInfloat_Y) {
                                zContains = true;
                            } else {
                                zContains = false;
                            }
                        }
                        if (zContains) {
                            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " exclude sidebar region when the sidebar is auto hide ");
                        } else {
                            z2 = true;
                        }
                    }
                } else {
                    StringBuilder sbM18 = Bundler$.ExternalSyntheticOutline0.m(str4, " isWithinInsets mDisplaySize.x = ");
                    sbM18.append(this.mDisplaySize.x);
                    sbM18.append("  mDisplaySize.y = ");
                    sbM18.append(this.mDisplaySize.y);
                    sbM18.append(" mSideGestureAreaWidthRight = ");
                    sbM18.append(sideGestureConfigurationEx.getSideGestureAreaWidthRight());
                    sbM18.append(" mSideGestureAreaWidthLeft ");
                    sbM18.append(sideGestureConfigurationEx.getSideGestureAreaWidthLeft());
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM18.toString());
                }
                this.mIsWithinInsets = z2;
                if (z2) {
                    if (motionEvent.isFromSource(16386)) {
                        z4 = false;
                    } else {
                        z4 = true;
                    }
                    this.mIsStylusEvent = z4;
                    if (z4) {
                        IpeSlideGestureRejectionObserver.INSTANCE.getClass();
                        if (IpeSlideGestureRejectionObserver.ipeSlideGestureState == 1) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                    } else {
                        z5 = false;
                    }
                    x = (int) motionEvent.getX();
                    y = (int) motionEvent.getY();
                    if (!z16) {
                        j2 = this.mSysUiFlags;
                        if ((32768 & j2) != 0) {
                        }
                    }
                    if (this.mIsInPipMode) {
                        region = this.mExcludeRegion;
                        int i15 = this.mDisplaySize.x;
                        if (region == null) {
                            if (this.mIsStylusEvent) {
                                rectF = this.mQuickNotesRegionF;
                                float f4 = x;
                                float f5 = y;
                                if (rectF == null) {
                                }
                            }
                            z7 = true;
                        } else {
                            if (this.mIsStylusEvent) {
                                rectF = this.mQuickNotesRegionF;
                                float f6 = x;
                                float f7 = y;
                                if (rectF == null) {
                                }
                            }
                            z7 = true;
                        }
                    } else {
                        region = this.mExcludeRegion;
                        int i16 = this.mDisplaySize.x;
                        if (region == null) {
                            if (this.mIsStylusEvent) {
                                rectF = this.mQuickNotesRegionF;
                                float f8 = x;
                                float f9 = y;
                                if (rectF == null) {
                                }
                            }
                            z7 = true;
                        } else {
                            if (this.mIsStylusEvent) {
                                rectF = this.mQuickNotesRegionF;
                                float f10 = x;
                                float f11 = y;
                                if (rectF == null) {
                                }
                            }
                            z7 = true;
                        }
                    }
                    if (this.mDisabledForQuickstep) {
                        z8 = false;
                    } else {
                        z8 = false;
                    }
                    if (z8) {
                        StringBuilder sbM19 = Bundler$.ExternalSyntheticOutline0.m(str4, " AllowGesture false cuz: ");
                        StringJoiner stringJoiner4 = new StringJoiner("|");
                        if (this.mDisabledForQuickstep) {
                            str = "disabled for quickstep";
                        } else {
                            str = "";
                        }
                        stringJoiner4.add(str);
                        if (this.mIsBackGestureAllowed) {
                            str2 = "";
                        } else {
                            str2 = "back Gesture not allowed";
                        }
                        stringJoiner4.add(str2);
                        stringJoiner4.add("");
                        if (z6) {
                            str3 = "back gesture disabled by sysui flags";
                        } else {
                            str3 = "";
                        }
                        stringJoiner4.add(str3);
                        stringJoiner4.add(z7 ? "" : "touch region not valid");
                        sbM19.append(stringJoiner4.toString());
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM19.toString());
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " current sysUiFlags: " + QuickStepContract.getSystemUiStateString(this.mSysUiFlags));
                    } else {
                        boolean zCheckSideGesture4 = OplusSideGesturePreProcess.checkSideGesture(this.mContext, y, this.mSideGestureViewManager.isExitAnimFinished(getSide()));
                        if (!z8) {
                        }
                        this.mAllowGesture = z9;
                        if (z9) {
                            resetGestureState();
                            updateGestureAndNavBarStates();
                        }
                        if (LogUtil.panicEnabled) {
                            StringBuilder sbM110 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                            sbM110.append(this.mAllowGesture);
                            sbM110.append(" ,isStylusEvent = ");
                            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM110, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                        }
                    }
                    z9 = false;
                    this.mAllowGesture = z9;
                    if (z9) {
                        resetGestureState();
                        updateGestureAndNavBarStates();
                    }
                    if (LogUtil.panicEnabled) {
                        StringBuilder sbM111 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                        sbM111.append(this.mAllowGesture);
                        sbM111.append(" ,isStylusEvent = ");
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM111, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                    }
                } else {
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Not in side gesture area");
                }
                if (this.mAllowGesture) {
                    dispatchToBackAnimation(motionEvent);
                }
                this.mDownPoint.set(motionEvent.getX(), motionEvent.getY());
                this.mThresholdCrossed = false;
                if (shouldInjectImmediate()) {
                    z3 = false;
                } else {
                    z3 = false;
                }
                this.mIsMisTouchNeedInjectUpEvent = z3;
                ((EventInterceptInjectHelper) instance.get()).setThresholdCrossed(false);
                ((EventInterceptInjectHelper) instance.get()).addDownEvent(this.mIsWithinInsets, this.mAllowGesture, shouldInjectImmediate(), motionEvent);
            }
            EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mStatusBarHeight, LogUtil.TAG_NAVBAR, "NoBackGesture", Bundler$.ExternalSyntheticOutline0.m(str4, " mStatusBarHeight = "));
        }
        z2 = false;
        this.mIsWithinInsets = z2;
        if (z2) {
            if (motionEvent.isFromSource(16386)) {
                z4 = false;
            } else {
                z4 = true;
            }
            this.mIsStylusEvent = z4;
            if (z4) {
                IpeSlideGestureRejectionObserver.INSTANCE.getClass();
                if (IpeSlideGestureRejectionObserver.ipeSlideGestureState == 1) {
                    z5 = true;
                } else {
                    z5 = false;
                }
            } else {
                z5 = false;
            }
            x = (int) motionEvent.getX();
            y = (int) motionEvent.getY();
            if (!z16) {
                j2 = this.mSysUiFlags;
                if ((32768 & j2) != 0) {
                }
            }
            if (this.mIsInPipMode) {
                region = this.mExcludeRegion;
                int i17 = this.mDisplaySize.x;
                if (region == null) {
                    if (this.mIsStylusEvent) {
                        rectF = this.mQuickNotesRegionF;
                        float f12 = x;
                        float f13 = y;
                        if (rectF == null) {
                        }
                    }
                    z7 = true;
                } else {
                    if (this.mIsStylusEvent) {
                        rectF = this.mQuickNotesRegionF;
                        float f14 = x;
                        float f15 = y;
                        if (rectF == null) {
                        }
                    }
                    z7 = true;
                }
            } else {
                region = this.mExcludeRegion;
                int i18 = this.mDisplaySize.x;
                if (region == null) {
                    if (this.mIsStylusEvent) {
                        rectF = this.mQuickNotesRegionF;
                        float f16 = x;
                        float f17 = y;
                        if (rectF == null) {
                        }
                    }
                    z7 = true;
                } else {
                    if (this.mIsStylusEvent) {
                        rectF = this.mQuickNotesRegionF;
                        float f18 = x;
                        float f19 = y;
                        if (rectF == null) {
                        }
                    }
                    z7 = true;
                }
            }
            if (this.mDisabledForQuickstep) {
                z8 = false;
            } else {
                z8 = false;
            }
            if (z8) {
                StringBuilder sbM112 = Bundler$.ExternalSyntheticOutline0.m(str4, " AllowGesture false cuz: ");
                StringJoiner stringJoiner5 = new StringJoiner("|");
                if (this.mDisabledForQuickstep) {
                    str = "disabled for quickstep";
                } else {
                    str = "";
                }
                stringJoiner5.add(str);
                if (this.mIsBackGestureAllowed) {
                    str2 = "";
                } else {
                    str2 = "back Gesture not allowed";
                }
                stringJoiner5.add(str2);
                stringJoiner5.add("");
                if (z6) {
                    str3 = "back gesture disabled by sysui flags";
                } else {
                    str3 = "";
                }
                stringJoiner5.add(str3);
                stringJoiner5.add(z7 ? "" : "touch region not valid");
                sbM112.append(stringJoiner5.toString());
                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", sbM112.toString());
                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " current sysUiFlags: " + QuickStepContract.getSystemUiStateString(this.mSysUiFlags));
            } else {
                boolean zCheckSideGesture5 = OplusSideGesturePreProcess.checkSideGesture(this.mContext, y, this.mSideGestureViewManager.isExitAnimFinished(getSide()));
                if (!z8) {
                }
                this.mAllowGesture = z9;
                if (z9) {
                    resetGestureState();
                    updateGestureAndNavBarStates();
                }
                if (LogUtil.panicEnabled) {
                    StringBuilder sbM113 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                    sbM113.append(this.mAllowGesture);
                    sbM113.append(" ,isStylusEvent = ");
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM113, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
                }
            }
            z9 = false;
            this.mAllowGesture = z9;
            if (z9) {
                resetGestureState();
                updateGestureAndNavBarStates();
            }
            if (LogUtil.panicEnabled) {
                StringBuilder sbM114 = Bundler$.ExternalSyntheticOutline0.m(str4, " mAllowGesture: ");
                sbM114.append(this.mAllowGesture);
                sbM114.append(" ,isStylusEvent = ");
                LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM114, this.mIsStylusEvent, " ,forbidSlideGesture = ", z5));
            }
        } else {
            LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", str4 + " Not in side gesture area");
        }
        if (this.mAllowGesture) {
            dispatchToBackAnimation(motionEvent);
        }
        this.mDownPoint.set(motionEvent.getX(), motionEvent.getY());
        this.mThresholdCrossed = false;
        if (shouldInjectImmediate()) {
            z3 = false;
        } else {
            z3 = false;
        }
        this.mIsMisTouchNeedInjectUpEvent = z3;
        ((EventInterceptInjectHelper) instance.get()).setThresholdCrossed(false);
        ((EventInterceptInjectHelper) instance.get()).addDownEvent(this.mIsWithinInsets, this.mAllowGesture, shouldInjectImmediate(), motionEvent);
    }

    public final void registerDeviceStateListener() {
        DeviceStateController.getInstance().addCallback(this.mStateChangedListener);
    }

    public final void registerGestureProgressListener() {
        LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", " == registerGestureProgressListener");
        NavGestureProcessObserver.Companion.getInstance().addListener(this.mNavGestureProcessListener);
    }

    public final void registerQuickNotesRegion() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("writting_pen_gesture_area"), false, this.mQuickNotesObserver, -1);
        updateQuickNotesRegion();
    }

    public final void registerSmartSideBarRegion() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("sidebar_scene_region"), false, this.mContentObserver, -1);
        updateSideBarSceneRegion();
    }

    public final void resetEventInterceptInjectState() {
        ((EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get()).resetState();
    }

    public final void resetGestureState() {
        this.mSideGestureConfiguration.resetGestureRemoteData();
        this.mBackCallback.setTriggerBack(false);
        LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureDetector", "SideGestureDetector : resetGestureState ");
    }

    public void setBackAnimation(BackAnimation backAnimation) {
        this.mBackAnimation = backAnimation;
    }

    public void setDisabledForQuickstep(boolean z) {
        this.mDisabledForQuickstep = z;
    }

    public void setHomeButton(View view) {
        this.mSideGestureHomeHandleAnimation.setHomeButton(view);
    }

    public void setIsBackGestureAllowed(boolean z) {
        this.mIsBackGestureAllowed = z;
    }

    public void setPipStashExclusionBounds(Rect rect) {
        this.mPipExcludedBounds.set(rect);
    }

    public void setSideGestureViewManager(SideGestureViewManagerEx sideGestureViewManagerEx) {
        this.mSideGestureViewManager = sideGestureViewManagerEx;
    }

    public final boolean shouldInjectImmediate() {
        return !shouldRespondToGesture() && this.mIsFirstGestureInGameMode && NavBarUtils.isGestureSideMode() && !this.mIsOnSubDisplayId;
    }

    public final boolean shouldRespondToGesture() {
        return !getNavBarHiddenStatus() || this.mAllowGestureIgnoringBarVisibility || this.mIsExitMisTouchPreventionFlag || this.mWmsAllowGestureIgnoringBarVisibility;
    }

    public final void unRegisterDeviceStateListener() {
        DeviceStateController.getInstance().removeCallback(this.mStateChangedListener);
    }

    public final void unRegisterGestureProgressListener() {
        LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", " == unRegisterGestureProgressListener");
        NavGestureProcessObserver.Companion.getInstance().removeListener(this.mNavGestureProcessListener);
    }

    public final void unregisterQuickNotesRegion() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mQuickNotesObserver);
    }

    public final void unregisterSmartSideBarRegion() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
    }

    public final void updateDisplaySize(int i) {
        this.mDisplay = this.mContext.getDisplay();
        SideGestureConfigurationEx sideGestureConfigurationEx = this.mSideGestureConfiguration;
        this.mScreenWidth = sideGestureConfigurationEx.getScreenWidth(i);
        int screenHeight = sideGestureConfigurationEx.getScreenHeight(i);
        this.mScreenHeight = screenHeight;
        this.mRotation = i;
        this.mDisplaySize.set(this.mScreenWidth, screenHeight);
        int i2 = 0;
        this.mStatusBarHeight = this.mIsOnSubDisplayId ? 0 : SystemBarUtils.getStatusBarHeight(this.mContext);
        SideGestureDetector$$ExternalSyntheticLambda1 sideGestureDetector$$ExternalSyntheticLambda1 = new SideGestureDetector$$ExternalSyntheticLambda1();
        sideGestureDetector$$ExternalSyntheticLambda1.f$0 = this;
        sideGestureDetector$$ExternalSyntheticLambda1.f$1 = i;
        VarHandle.storeStoreFence();
        Handler handler = this.mGestureUIHandler;
        if (handler != null) {
            handler.post(sideGestureDetector$$ExternalSyntheticLambda1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDisplayIdTag);
        sb.append(" SideGesture Update display size: mDisplaySize = ");
        sb.append(this.mDisplaySize);
        sb.append(", rotation=");
        sb.append(i);
        sb.append(" mStatusBarHeight=");
        sb.append(this.mStatusBarHeight);
        sb.append(",mScreenWidth = ");
        sb.append(this.mScreenWidth);
        sb.append(",mScreenHeight = ");
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mScreenHeight, LogUtil.TAG_NAVBAR, "NoBackGesture", sb);
        if (this.mDisplayId == 0) {
            OplusInputInjector oplusInputInjector = OplusInputInjector.getInstance();
            int i3 = this.mScreenWidth;
            int i4 = this.mScreenHeight;
            oplusInputInjector.getClass();
            oplusInputInjector.mScreenRegion.set(0.0f, 0.0f, i3, i4);
        }
        SideGestureDetector$$ExternalSyntheticLambda2 sideGestureDetector$$ExternalSyntheticLambda2 = new SideGestureDetector$$ExternalSyntheticLambda2(i2);
        sideGestureDetector$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        if (handler != null) {
            handler.post(sideGestureDetector$$ExternalSyntheticLambda2);
        }
        Point point = this.mDisplaySize;
        this.mSideGestureHomeHandleAnimation.mDisplaySize.set(point.x, point.y);
        if (this.mBackAnimation == null) {
            return;
        }
        float f = this.mDisplaySize.x;
        this.mBackAnimation.setSwipeThresholds(Math.min(f, this.mBackSwipeProgressThreshold), f, this.mNonLinearFactor);
    }

    public final void updateGestureAndNavBarStates() {
        boolean zIsAllowBackGestureIgnoringBarVisibility;
        long j = this.mSysUiFlags;
        this.mNavBarHidden = ((2 & j) == 0 || this.mIsOnSubDisplayId) ? false : true;
        this.mAllowGestureIgnoringBarVisibility = ((131072 & j) == 0 && (j & 4398046511104L) == 0) ? false : true;
        try {
            zIsAllowBackGestureIgnoringBarVisibility = OplusWindowManager.getInstance().isAllowBackGestureIgnoringBarVisibility();
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("isAllowBackGestureIgnoringBarVisibility e = "), LogUtil.TAG_NAVBAR, "SideGestureDetector");
            zIsAllowBackGestureIgnoringBarVisibility = false;
        }
        this.mWmsAllowGestureIgnoringBarVisibility = zIsAllowBackGestureIgnoringBarVisibility;
        this.mIsExitMisTouchPreventionFlag = this.mIsExitMisTouchPrevention;
        OplusGameModeObserver.INSTANCE.getClass();
        this.mIsFirstGestureInGameMode = OplusGameModeObserver.oplusGameModeObserverState == 1 && !this.mIsExitMisTouchPreventionFlag;
        StringBuilder sb = new StringBuilder("updateGestureAndNavBarStates， mNavBarHidden : ");
        sb.append(this.mNavBarHidden);
        sb.append(", mAllowGestureIgnoringBarVisibility: ");
        sb.append(this.mAllowGestureIgnoringBarVisibility);
        sb.append(", mWmsAllowGestureIgnoringBarVisibility: ");
        sb.append(this.mWmsAllowGestureIgnoringBarVisibility);
        sb.append(", mIsExitMisTouchPreventionFlag : ");
        sb.append(this.mIsExitMisTouchPreventionFlag);
        sb.append(", mIsFirstGestureInGameMode : ");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_NAVBAR, "SideGestureDetector", sb, this.mIsFirstGestureInGameMode);
    }

    public final void updateNavigationBarOverlayExcludeRegion(Rect rect) {
        this.mNavBarOverlayExcludedBounds.set(rect);
    }

    public final void updateQuickNotesRegion() {
        String stringForUser = Settings.Secure.getStringForUser(this.mContext.getContentResolver(), "writting_pen_gesture_area", -2);
        StringBuilder sb = new StringBuilder();
        String str = this.mDisplayIdTag;
        LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", Fragment$5$.ExternalSyntheticOutline0.m(str, " quickNotes regionStr = ", stringForUser, sb));
        if (stringForUser == null) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", "regionString = null");
            return;
        }
        String[] strArrSplit = stringForUser.split(" ");
        if (strArrSplit.length != 4) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", str + " parts length != 4");
            return;
        }
        try {
            this.mQuickNotesRegionF.set(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), Integer.parseInt(strArrSplit[3]));
            LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", str + " mQuickNotesRegionF = " + this.mQuickNotesRegionF);
        } catch (NumberFormatException unused) {
            LogUtil.e(LogUtil.TAG_NAVBAR, "NoBackGesture", "Failed to parse integers");
        }
    }

    public final void updateSideBarSceneRegion() {
        String stringForUser = Settings.Secure.getStringForUser(this.mContext.getContentResolver(), "sidebar_scene_region", -2);
        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("regionString = ", stringForUser, LogUtil.TAG_NAVBAR, "NoBackGesture");
        int i = 3;
        if (stringForUser != null) {
            String[] strArrSplit = stringForUser.split(" ");
            if (strArrSplit.length != 4) {
                LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", "parts length != 4");
                return;
            }
            try {
                this.mSideBarSceneRegion.set(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), Integer.parseInt(strArrSplit[3]));
                LogUtil.d(LogUtil.TAG_NAVBAR, "NoBackGesture", "mSideBarSceneRegion = " + this.mSideBarSceneRegion);
            } catch (NumberFormatException unused) {
                LogUtil.e(LogUtil.TAG_NAVBAR, "NoBackGesture", "Failed to parse integers");
            }
        } else {
            LogUtil.e(LogUtil.TAG_NAVBAR, "NoBackGesture", "regionString = null");
        }
        if (this.mSideGestureConfiguration != null) {
            SideGestureDetector$$ExternalSyntheticLambda2 sideGestureDetector$$ExternalSyntheticLambda2 = new SideGestureDetector$$ExternalSyntheticLambda2(i);
            sideGestureDetector$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            Handler handler = this.mGestureUIHandler;
            if (handler != null) {
                handler.post(sideGestureDetector$$ExternalSyntheticLambda2);
            }
        }
    }

    public final void updateSysUiFlags(long j) {
        this.mSysUiFlags = j;
    }

    public final void updateTouchSlop(float f) {
        this.mTouchSlop = f;
    }
}
