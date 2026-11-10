package com.oplus.systemui.subdisplay.aod.fullscreen;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.subdisplay.IAodSubFullscreenController;
import com.oplus.dispatch.OCDHandler;
import com.oplus.systemui.ISubFullscreenCallback;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.systemui.subdisplay.SecondaryKeyguardProviderClient;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.wallpaperserver.WallpaperSignalController;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodSubFullscreenController extends IAodSubFullscreenController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AodSubFullscreenController";
    public volatile int deviceState;
    public final AODDisplayUtil mAODDisplayUtil;
    private AodSubFullscreenPolicy mAodSubFullscreenPolicy;
    private Context mContext;
    public volatile boolean mDreamingStarted;
    public volatile boolean mIsFullscreenShowing;
    private final DeviceStateController.StateChangedListener mStateChangedListener;
    private final Consumer<Integer> mUpdateCallback;
    private final SecondaryKeyguardProviderClient secondaryProviderClient;
    private final ISubFullscreenCallback subFullscreenCallBack;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    public volatile int mLastState = 2;

    public AodSubFullscreenController(Context context, SecondaryKeyguardProviderClient secondaryKeyguardProviderClient) {
        this.mContext = context;
        this.secondaryProviderClient = secondaryKeyguardProviderClient;
        mUpdateCallback.1 r1 = new mUpdateCallback.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mUpdateCallback = r1;
        mStateChangedListener.1 r2 = new mStateChangedListener.1();
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStateChangedListener = r2;
        this.subFullscreenCallBack = new subFullscreenCallBack.1(this);
        Context context2 = this.mContext;
        if (context2 != null) {
            context2.getClass();
            this.mAodSubFullscreenPolicy = new AodSubFullscreenPolicy(context2);
            this.mAODDisplayUtil = AODDisplayUtil.getInstance(this.mContext);
        }
    }

    public static final void access$hideAndResetState(AodSubFullscreenController aodSubFullscreenController) {
        aodSubFullscreenController.mIsFullscreenShowing = false;
        if (aodSubFullscreenController.deviceState == 3) {
            aodSubFullscreenController.requestSubDisplayVisibility(1);
        } else {
            aodSubFullscreenController.requestSubDisplayVisibility(2);
        }
        AodSubFullscreenPolicy aodSubFullscreenPolicy = aodSubFullscreenController.mAodSubFullscreenPolicy;
        if (aodSubFullscreenPolicy != null) {
            aodSubFullscreenPolicy.onHideAndResetState();
        }
        LogUtil.i$default(LogUtil.TAG_AOD, TAG, "Hide and reset state", null, 8);
        aodSubFullscreenController.mLastState = 2;
    }

    public static final void access$hideFullscreen(AodSubFullscreenController aodSubFullscreenController) {
        if (!aodSubFullscreenController.mIsFullscreenShowing) {
            LogUtil.w$default(8, LogUtil.TAG_AOD, TAG, "hideFullscreen, but mIsFullscreenShowing is false!");
            return;
        }
        aodSubFullscreenController.mIsFullscreenShowing = false;
        LogUtil.i$default(LogUtil.TAG_AOD, TAG, "Hide sub display Fullscreen", null, 8);
        AODDisplayUtil aODDisplayUtil = aodSubFullscreenController.mAODDisplayUtil;
        (aODDisplayUtil != null ? aODDisplayUtil : null).requestScreenState(1, 1000, TAG);
        aodSubFullscreenController.requestSubDisplayVisibility(1);
        AodSubFullscreenPolicy aodSubFullscreenPolicy = aodSubFullscreenController.mAodSubFullscreenPolicy;
        if (aodSubFullscreenPolicy != null) {
            aodSubFullscreenPolicy.onHideFullscreen();
        }
        aodSubFullscreenController.mLastState = 1;
    }

    public static final void access$onRequestAodFinished(AodSubFullscreenController aodSubFullscreenController) {
        if (aodSubFullscreenController.mContext == null) {
            return;
        }
        int dozeScreenState = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusDozeService().getDozeScreenState();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onRequestAodFinished, started: ", ", showing: ", " state: ", aodSubFullscreenController.mDreamingStarted, aodSubFullscreenController.mIsFullscreenShowing);
        sbM.append(dozeScreenState);
        LogUtil.i$default(LogUtil.TAG_AOD, TAG, sbM.toString(), null, 8);
        if (aodSubFullscreenController.mDreamingStarted && aodSubFullscreenController.mIsFullscreenShowing) {
            if (dozeScreenState == 0 || dozeScreenState == 2) {
                AODDisplayUtil aODDisplayUtil = aodSubFullscreenController.mAODDisplayUtil;
                if (aODDisplayUtil == null) {
                    aODDisplayUtil = null;
                }
                aODDisplayUtil.requestScreenState(3, 3000, TAG);
                LogUtil.i$default(LogUtil.TAG_AOD, TAG, "delay set doze when aod finished", null, 8);
            }
        }
    }

    public static final void access$onRequestAodReady(AodSubFullscreenController aodSubFullscreenController, int i) {
        if (aodSubFullscreenController.mContext == null) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_AOD, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onRequestAodReady showing: ", aodSubFullscreenController.mIsFullscreenShowing, " scene: ", i), null, 8);
        if (aodSubFullscreenController.mDreamingStarted && aodSubFullscreenController.mIsFullscreenShowing) {
            int i2 = i == 1 ? 7 : 8;
            WallpaperSignalController.Companion companion = WallpaperSignalController.Companion;
            Context context = aodSubFullscreenController.mContext;
            context.getClass();
            companion.getInstance(context).onPowerDisplayEvent(i2);
            aodSubFullscreenController.requestDoze(3000);
        }
    }

    public static final void access$showFullscreen(AodSubFullscreenController aodSubFullscreenController) {
        Display secondaryDisplay;
        if (aodSubFullscreenController.mIsFullscreenShowing) {
            LogUtil.w$default(8, LogUtil.TAG_AOD, TAG, "Show sub display Fullscreen, already showing!");
            return;
        }
        aodSubFullscreenController.mIsFullscreenShowing = true;
        LogUtil.i$default(LogUtil.TAG_AOD, TAG, "Show sub display Fullscreen", null, 8);
        Context context = aodSubFullscreenController.mContext;
        if (context != null && (secondaryDisplay = OplusSubDisplayUtils.Companion.getInstance(context).getSecondaryDisplay()) != null && secondaryDisplay.getState() == 1) {
            aodSubFullscreenController.mLastState = 1;
        }
        aodSubFullscreenController.requestSubDisplayVisibility(3);
        AodSubFullscreenPolicy aodSubFullscreenPolicy = aodSubFullscreenController.mAodSubFullscreenPolicy;
        if (aodSubFullscreenPolicy != null) {
            aodSubFullscreenPolicy.onShowFullscreen();
        }
        aodSubFullscreenController.mLastState = 3;
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBlackScreenGestureControll().enterSubDisplayFullscreenAodScene();
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final boolean isFullscreenEnabled() {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        OplusSubDisplayUtils.Companion companion = OplusSubDisplayUtils.Companion;
        context.getClass();
        if (companion.getInstance(context).getSubDisplayShow()) {
            return AodData.getInstance(this.mContext).mAodOptionsMgr.isCurrentFullScreen();
        }
        return false;
    }

    public final void onCreate() {
        if (this.mContext != null) {
            DeviceStateController.getInstance().addCallback(this.mStateChangedListener);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_AOD)) {
                Log.d("SystemUi--Aod", "AodSubFullscreenController-->aod Sub fullscreen create");
            }
        }
    }

    public final void onDestroy() {
        if (this.mContext != null) {
            DeviceStateController.getInstance().removeCallback(this.mStateChangedListener);
            LogUtil.i$default(LogUtil.TAG_AOD, TAG, "aod Sub fullscreen destroy", null, 8);
            this.mDreamingStarted = false;
            this.mIsFullscreenShowing = false;
            AodData.getInstance(this.mContext).setAodIsInShow(false);
        }
    }

    public final void onDreamingStarted() {
        if (this.mContext == null || this.mDreamingStarted) {
            return;
        }
        this.mDreamingStarted = true;
        this.deviceState = 0;
        LogUtil.i$default(LogUtil.TAG_AOD, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("Sub fullscreen started, mIsFullscreenShowing: ", this.mIsFullscreenShowing), null, 8);
        AODDisplayUtil aODDisplayUtil = this.mAODDisplayUtil;
        if (aODDisplayUtil == null) {
            aODDisplayUtil = null;
        }
        aODDisplayUtil.clearAodDisplayClient();
        AODDisplayUtil aODDisplayUtil2 = this.mAODDisplayUtil;
        (aODDisplayUtil2 != null ? aODDisplayUtil2 : null).setBlockRequestState(false);
        AodSubFullscreenPolicy aodSubFullscreenPolicy = this.mAodSubFullscreenPolicy;
        if (aodSubFullscreenPolicy != null) {
            aodSubFullscreenPolicy.setUpdateCallback(this.mUpdateCallback);
        }
        AodSubFullscreenPolicy aodSubFullscreenPolicy2 = this.mAodSubFullscreenPolicy;
        if (aodSubFullscreenPolicy2 != null) {
            OCDHandler oCDHandler = aodSubFullscreenPolicy2.aodHandler;
            AodSubFullscreenPolicy.hideAod.1 r2 = new AodSubFullscreenPolicy.hideAod.1(1);
            r2.this$0 = aodSubFullscreenPolicy2;
            VarHandle.storeStoreFence();
            oCDHandler.post(r2);
        }
    }

    public final void onDreamingStopped() {
        if (this.mContext != null && this.mDreamingStarted) {
            this.mDreamingStarted = false;
            LogUtil.i$default(LogUtil.TAG_AOD, TAG, "Sub fullscreen stopped", null, 8);
            AodSubFullscreenPolicy aodSubFullscreenPolicy = this.mAodSubFullscreenPolicy;
            if (aodSubFullscreenPolicy != null) {
                OCDHandler oCDHandler = aodSubFullscreenPolicy.aodHandler;
                AodSubFullscreenPolicy.hideAod.1 r1 = new AodSubFullscreenPolicy.hideAod.1(2);
                r1.this$0 = aodSubFullscreenPolicy;
                VarHandle.storeStoreFence();
                oCDHandler.post(r1);
            }
        }
    }

    public final void requestDoze(int i) {
        if (!this.mDreamingStarted || !this.mIsFullscreenShowing) {
            LogUtil.w$default(8, LogUtil.TAG_AOD, TAG, "requestDoze, full screen not showing, duration: " + i);
            return;
        }
        AODDisplayUtil aODDisplayUtil = this.mAODDisplayUtil;
        if (aODDisplayUtil == null) {
            aODDisplayUtil = null;
        }
        int dozeScreenState = aODDisplayUtil.getDozeScreenState();
        if (dozeScreenState == 3 || dozeScreenState == 4) {
            AODDisplayUtil aODDisplayUtil2 = this.mAODDisplayUtil;
            (aODDisplayUtil2 != null ? aODDisplayUtil2 : null).requestScreenState(3, i, TAG);
            this.mLastState = 3;
        }
    }

    public final void requestSubDisplayVisibility(int i) {
        LogUtil.i$default(LogUtil.TAG_AOD, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "requestSubDisplayVisibility: "), null, 8);
        if (i == 1) {
            int i2 = this.mLastState;
            if (i2 == 3 || i2 == 4) {
                this.secondaryProviderClient.requestAodFullscreenVisibility(this.mContext, 4, this.subFullscreenCallBack);
                WallpaperSignalController.Companion companion = WallpaperSignalController.Companion;
                Context context = this.mContext;
                context.getClass();
                companion.getInstance(context).onPowerDisplayEvent(11);
            }
        } else if (i == 2) {
            int i3 = this.mLastState;
            if (i3 == 1) {
                this.secondaryProviderClient.requestAodFullscreenVisibility(this.mContext, 5, this.subFullscreenCallBack);
                WallpaperSignalController.Companion companion2 = WallpaperSignalController.Companion;
                Context context2 = this.mContext;
                context2.getClass();
                companion2.getInstance(context2).onPowerDisplayEvent(12);
            } else if (i3 == 3 || i3 == 4) {
                this.secondaryProviderClient.requestAodFullscreenVisibility(this.mContext, 3, this.subFullscreenCallBack);
                WallpaperSignalController.Companion companion3 = WallpaperSignalController.Companion;
                Context context3 = this.mContext;
                context3.getClass();
                companion3.getInstance(context3).onPowerDisplayEvent(10);
            }
        } else if (i == 3) {
            int i4 = this.mLastState;
            if (i4 == 1) {
                AODDisplayUtil aODDisplayUtil = this.mAODDisplayUtil;
                if (aODDisplayUtil == null) {
                    aODDisplayUtil = null;
                }
                aODDisplayUtil.requestScreenState(3, 3000, TAG);
                System.currentTimeMillis();
                this.secondaryProviderClient.requestAodFullscreenVisibility(this.mContext, 2, this.subFullscreenCallBack);
                WallpaperSignalController.Companion companion4 = WallpaperSignalController.Companion;
                Context context4 = this.mContext;
                context4.getClass();
                companion4.getInstance(context4).onPowerDisplayEvent(9);
            } else if (i4 == 2) {
                this.secondaryProviderClient.requestAodFullscreenVisibility(this.mContext, 1, this.subFullscreenCallBack);
            }
        }
        LogUtil.i$default(LogUtil.TAG_AOD, TAG, CursorUtil$.ExternalSyntheticOutline0.m("request visibility: from ", Display.stateToString(this.mLastState), " to ", Display.stateToString(i)), null, 8);
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }
}
