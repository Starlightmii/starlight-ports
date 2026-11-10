package com.oplus.systemui.preventmode;

import android.app.OplusActivityTaskManager;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inspector.WindowInspector;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.qs.SubDisplayQSController;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.observer.TalkbackObserver;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPreventWindowController {
    public static final Companion Companion = new Companion();
    private static final String DISPLAY_CATEGORY_ALL_INCLUDING_DISABLED = "android.hardware.display.category.ALL_INCLUDING_DISABLED";
    public static final String INFO_ENTRANCE = "1";
    public static final String INFO_EXIT = "0";
    private Context context;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager.LayoutParams mSecondaryLayoutParams;
    private OplusPreventModeView mSecondaryView;
    private WindowManager mSecondaryWindowManager;
    private OplusPreventModeView mView;
    private WindowManager mWindowManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusPreventWindowController(Context context) {
        Display secondaryDisplay;
        this.context = context;
        Context contextUpdateDisplayConfiguration = KeyguardUtils.Companion.updateDisplayConfiguration(context, true, false);
        contextUpdateDisplayConfiguration = contextUpdateDisplayConfiguration == null ? this.context : contextUpdateDisplayConfiguration;
        this.context = contextUpdateDisplayConfiguration;
        contextUpdateDisplayConfiguration.updateDisplay(0);
        Object systemService = this.context.getSystemService("window");
        this.mWindowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        View viewInflate = LayoutInflater.from(this.context).inflate(2131559319, (ViewGroup) null);
        this.mView = viewInflate instanceof OplusPreventModeView ? (OplusPreventModeView) viewInflate : null;
        OplusSubDisplayUtils.Companion companion = OplusSubDisplayUtils.Companion;
        if (!companion.getInstance(this.context).getSupportSubDisplay() || (secondaryDisplay = companion.getInstance(this.context).getSecondaryDisplay()) == null) {
            return;
        }
        Context contextCreateWindowContext = this.context.createDisplayContext(secondaryDisplay).createWindowContext(2016, null);
        View viewInflate2 = LayoutInflater.from(contextCreateWindowContext).inflate(2131559410, (ViewGroup) null);
        this.mSecondaryView = viewInflate2 instanceof OplusPreventModeView ? (OplusPreventModeView) viewInflate2 : null;
        Object systemService2 = contextCreateWindowContext.getSystemService("window");
        this.mSecondaryWindowManager = systemService2 instanceof WindowManager ? (WindowManager) systemService2 : null;
    }

    public final void addWindow() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.type = 2016;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.screenOrientation = 1;
        layoutParams.setTitle("PreventModeView");
        layoutParams.accessibilityTitle = layoutParams.getTitle();
        layoutParams.windowAnimations = -1;
        layoutParams.userActivityTimeout = 10000L;
        layoutParams.privateFlags = 16;
        TalkbackObserver.Companion companion = TalkbackObserver.Companion;
        if (!companion.isTalkbackEnabled(this.context)) {
            layoutParams.flags = 8;
        }
        layoutParams.setBlurBehindRadius(150);
        layoutParams.format = -3;
        OplusPreventModeView oplusPreventModeView = this.mView;
        if (oplusPreventModeView != null) {
            oplusPreventModeView.setAlpha(Float.MIN_VALUE);
        }
        OplusPreventModeView oplusPreventModeView2 = this.mView;
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "addWindow; mView alpha: " + (oplusPreventModeView2 != null ? Float.valueOf(oplusPreventModeView2.getAlpha()) : null));
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.addView(this.mView, this.mLayoutParams);
        }
        writeNodeFileByDevice(0, 194, "1");
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "PreventModeView show success");
        if (OplusSubDisplayUtils.Companion.getInstance(this.context).getSupportSubDisplay()) {
            WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
            this.mSecondaryLayoutParams = layoutParams2;
            layoutParams2.type = 2016;
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            layoutParams2.screenOrientation = 7;
            layoutParams2.setTitle("SecondaryPreventModeView");
            layoutParams2.accessibilityTitle = layoutParams2.getTitle();
            layoutParams2.windowAnimations = -1;
            layoutParams2.userActivityTimeout = 10000L;
            layoutParams2.privateFlags = 16;
            if (!companion.isTalkbackEnabled(this.context)) {
                layoutParams2.flags = 8;
            }
            layoutParams2.setBlurBehindRadius(150);
            layoutParams2.format = -3;
            OplusPreventModeView oplusPreventModeView3 = this.mSecondaryView;
            if (oplusPreventModeView3 != null) {
                oplusPreventModeView3.setAlpha(Float.MIN_VALUE);
            }
            OplusPreventModeView oplusPreventModeView4 = this.mSecondaryView;
            KgdLogUtil.i(OplusPreventModeExImpl.TAG, "addSecondaryWindow, mSecondaryView alpha: " + (oplusPreventModeView4 != null ? Float.valueOf(oplusPreventModeView4.getAlpha()) : null));
            WindowManager windowManager2 = this.mSecondaryWindowManager;
            if (windowManager2 != null) {
                windowManager2.addView(this.mSecondaryView, this.mSecondaryLayoutParams);
            }
            SubDisplayQSController.INSTANCE.updatePreventMode(this.context, true, false);
            writeNodeFileByDevice(1, 194, "1");
            KgdLogUtil.i(OplusPreventModeExImpl.TAG, "SecondaryPreventModeView show success");
        }
    }

    public final void clear() {
        this.mWindowManager = null;
        this.mSecondaryWindowManager = null;
        this.mView = null;
        this.mSecondaryView = null;
    }

    public final void clearView() {
        OplusPreventModeView oplusPreventModeView = this.mView;
        if (oplusPreventModeView != null && WindowInspector.getGlobalWindowViews().contains(oplusPreventModeView)) {
            removeMainWindow();
        }
        OplusPreventModeView oplusPreventModeView2 = this.mSecondaryView;
        if (oplusPreventModeView2 != null && WindowInspector.getGlobalWindowViews().contains(oplusPreventModeView2)) {
            removeSecondaryWindow();
        }
        clear();
    }

    public final Context getContext() {
        return this.context;
    }

    public final WindowManager.LayoutParams getMLayoutParams() {
        return this.mLayoutParams;
    }

    public final WindowManager.LayoutParams getMSecondaryLayoutParams() {
        return this.mSecondaryLayoutParams;
    }

    public final OplusPreventModeView getMSecondaryView() {
        return this.mSecondaryView;
    }

    public final WindowManager getMSecondaryWindowManager() {
        return this.mSecondaryWindowManager;
    }

    public final OplusPreventModeView getMView() {
        return this.mView;
    }

    public final WindowManager getMWindowManager() {
        return this.mWindowManager;
    }

    public final void removeMainWindow() {
        if (this.mView == null) {
            Path$.ExternalSyntheticBUOutline0.m("removeMainWindow; mView is null");
            return;
        }
        if (!WindowInspector.getGlobalWindowViews().contains(this.mView)) {
            Path$.ExternalSyntheticBUOutline0.m("removeMainWindow; mView is not added");
            return;
        }
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.removeViewImmediate(this.mView);
        }
        writeNodeFileByDevice(0, 194, "0");
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "PreventModeView remove success");
    }

    public final void removeSecondaryWindow() {
        if (this.mSecondaryView == null) {
            Path$.ExternalSyntheticBUOutline0.m("removeSecondaryWindow; mSecondaryView is null");
            return;
        }
        if (!WindowInspector.getGlobalWindowViews().contains(this.mSecondaryView)) {
            Path$.ExternalSyntheticBUOutline0.m("removeSecondaryWindow; mSecondaryView is not added");
            return;
        }
        WindowManager windowManager = this.mSecondaryWindowManager;
        if (windowManager != null) {
            windowManager.removeViewImmediate(this.mSecondaryView);
        }
        SubDisplayQSController.INSTANCE.updatePreventMode(this.context, false, false);
        writeNodeFileByDevice(1, 194, "0");
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "SecondaryPreventModeView remove success");
    }

    public final void removeWindow() {
        removeMainWindow();
        if (OplusSubDisplayUtils.Companion.getInstance(this.context).getSupportSubDisplay()) {
            removeSecondaryWindow();
        }
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setMLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
    }

    public final void setMSecondaryLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.mSecondaryLayoutParams = layoutParams;
    }

    public final void setMSecondaryView(OplusPreventModeView oplusPreventModeView) {
        this.mSecondaryView = oplusPreventModeView;
    }

    public final void setMSecondaryWindowManager(WindowManager windowManager) {
        this.mSecondaryWindowManager = windowManager;
    }

    public final void setMView(OplusPreventModeView oplusPreventModeView) {
        this.mView = oplusPreventModeView;
    }

    public final void setMWindowManager(WindowManager windowManager) {
        this.mWindowManager = windowManager;
    }

    public final void updateLayoutParams() {
        if (!WindowInspector.getGlobalWindowViews().contains(this.mView)) {
            Path$.ExternalSyntheticBUOutline0.m("updateMainLayoutParams; mView is not added");
            return;
        }
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        if (layoutParams == null) {
            Path$.ExternalSyntheticBUOutline0.m("updateLayoutParams; mLayoutParams is null");
            return;
        }
        TalkbackObserver.Companion companion = TalkbackObserver.Companion;
        layoutParams.flags = companion.isTalkbackEnabled(this.context) ? 16777220 : 16777228;
        OplusPreventModeView oplusPreventModeView = this.mView;
        if (oplusPreventModeView != null) {
            oplusPreventModeView.setAlpha(1.0f);
        }
        OplusPreventModeView oplusPreventModeView2 = this.mView;
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "updateLayoutParams; mView alpha: " + (oplusPreventModeView2 != null ? Float.valueOf(oplusPreventModeView2.getAlpha()) : null));
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.updateViewLayout(this.mView, this.mLayoutParams);
        }
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "PreventModeView updateViewLayout");
        if (OplusSubDisplayUtils.Companion.getInstance(this.context).getSupportSubDisplay()) {
            if (!WindowInspector.getGlobalWindowViews().contains(this.mSecondaryView)) {
                Path$.ExternalSyntheticBUOutline0.m("updateSecondaryLayoutParams; mSecondaryView is not added");
                return;
            }
            if (this.mSecondaryLayoutParams == null) {
                Path$.ExternalSyntheticBUOutline0.m("updateSecondaryLayoutParams; mSecondaryLayoutParams is null");
                return;
            }
            OplusPreventModeView oplusPreventModeView3 = this.mSecondaryView;
            if (oplusPreventModeView3 != null) {
                oplusPreventModeView3.setAlpha(1.0f);
            }
            OplusPreventModeView oplusPreventModeView4 = this.mSecondaryView;
            KgdLogUtil.i(OplusPreventModeExImpl.TAG, "updateLayoutParams; mSecondaryView alpha: " + (oplusPreventModeView4 != null ? Float.valueOf(oplusPreventModeView4.getAlpha()) : null));
            WindowManager.LayoutParams layoutParams2 = this.mSecondaryLayoutParams;
            if (layoutParams2 != null) {
                layoutParams2.flags = companion.isTalkbackEnabled(this.context) ? 16777220 : 16777228;
            }
            WindowManager windowManager2 = this.mSecondaryWindowManager;
            if (windowManager2 != null) {
                windowManager2.updateViewLayout(this.mSecondaryView, this.mSecondaryLayoutParams);
            }
            SubDisplayQSController.INSTANCE.updatePreventMode(this.context, true, true);
            KgdLogUtil.i(OplusPreventModeExImpl.TAG, "SecondaryPreventModeView updateViewLayout");
        }
    }

    public final void writeNodeFileByDevice(int i, int i2, String str) {
        OplusActivityTaskManager.getInstance().writeNodeFileByDevice(i, i2, str);
    }
}
