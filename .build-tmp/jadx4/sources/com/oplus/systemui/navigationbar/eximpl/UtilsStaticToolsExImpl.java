package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Display;
import com.android.systemui.navigationbar.UtilsStaticToolsEx;
import com.oplus.systemui.navigationbar.gesture.AppSettingsHandler;
import com.oplus.systemui.navigationbar.gesture.AppSettingsHandler$$ExternalSyntheticLambda0;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.gesture.subdisplay.ConfigurationOnSubDisplay;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureBarTypeObserver;
import com.oplus.systemui.navigationbar.observer.SystemFoldModeObserver;
import com.oplus.systemui.navigationbar.taskbar.TaskbarUtils;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplus.systemui.navigationbar.utils.SupportOutUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UtilsStaticToolsExImpl extends UtilsStaticToolsEx {
    private final Context mContext;

    public UtilsStaticToolsExImpl(Context context) {
        this.mContext = context;
    }

    public final boolean canSamplingRegionMode(int i) {
        return (NavBarUtils.isGestureUpMode() || NavBarUtils.isSideGestureBarHide() || i == 4 || i == 7 || i == 3 || i == 9) ? false : true;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public int getNavBarHeight(Context context) {
        return NavBarUtils.getNavBarHeight(context);
    }

    public int getNavButtonsPosition() {
        return NavBarSettingsValueProxy.Companion.getNavButtonsPosition(this.mContext);
    }

    public int getNavButtonsSwitchDirect() {
        return NavBarSettingsValueProxy.Companion.getNavButtonsSwitchDirect(this.mContext);
    }

    public int getNavStateProxy() {
        return NavBarSettingsValueProxy.Companion.getNavState(this.mContext);
    }

    public void getPackageIcon(String str, Function1 function1) {
        OplusNavBarUtils.getPackageIcon(str, function1);
    }

    public Drawable getPackageIconBlock(String str) {
        return OplusNavBarUtils.getPackageIconBlock(str);
    }

    public boolean isAssistantAvailable(Context context, int i, int i2) {
        return NavBarUtils.isAssistantAvailable(context, i, i2);
    }

    public boolean isEnableExternalDisplay(Context context, int i) {
        return OplusNavBarUtils.isEnableExternalDisplay(context, i);
    }

    public boolean isEnableTaskbar(Context context) {
        return TaskbarUtils.isEnableTaskbar(context);
    }

    public final boolean isFoldOpenNavigationLayout() {
        return OplusNavBarUtils.isFoldOpenNavigationLayout(this.mContext);
    }

    public final boolean isGestureSideMode() {
        return NavBarUtils.isGestureSideMode();
    }

    public final boolean isGestureUpMode() {
        return NavBarUtils.isGestureUpMode();
    }

    public final boolean isHideNavBarGestureMode() {
        return NavBarUtils.isGestureUpMode() || NavBarUtils.isSideGestureBarHide();
    }

    public final boolean isLargeDevice() {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isFoldDevice() && SystemFoldModeObserver.Companion.getInstance().isFoldOpen() && !FeatureOption.isFoldRemapDisabled();
    }

    public boolean isLongPressHomeEnabled(Context context, int i) {
        return NavBarUtils.isLongPressHomeEnabled(context, i);
    }

    public final boolean isNavColorReversalEnable() {
        return NavBarUtils.isNavColorReversalEnable;
    }

    public boolean isSubDisplay(Context context, int i) {
        ConfigurationOnSubDisplay.Companion.getInstance(context).getClass();
        return ConfigurationOnSubDisplay.isSubDisplayId(i);
    }

    public boolean isSystemUiSupportClearBottomGestureArea(Context context) {
        return SupportOutUtils.isSystemUiSupportClearBottomGestureArea(context);
    }

    public final boolean isUpGestureBarHide() {
        return NavBarUtils.isGestureUpMode() && SwipeUpGestureBarTypeObserver.INSTANCE.getSwipeUpGestureBarType() == 1;
    }

    public final boolean isVirtualKeyMode() {
        return NavBarUtils.isVirtualKeyMode();
    }

    public final void onTaskStackChanged() {
        AppSettingsHandler appSettingsHandler = AppSettingsHandler.getInstance(this.mContext);
        Executor executor = appSettingsHandler.mBgExecutor;
        AppSettingsHandler$$ExternalSyntheticLambda0 appSettingsHandler$$ExternalSyntheticLambda0 = new AppSettingsHandler$$ExternalSyntheticLambda0(1);
        appSettingsHandler$$ExternalSyntheticLambda0.f$0 = appSettingsHandler;
        VarHandle.storeStoreFence();
        executor.execute(appSettingsHandler$$ExternalSyntheticLambda0);
    }

    public void setNavBarWindowVisible(Context context, boolean z) {
        NavBarUtils.setNavBarWindowVisible(context, z);
    }

    public void setTaskbarStatus(Context context, boolean z) {
        NavBarUtils.setTaskbarStatus(context, z);
    }

    public boolean isEnableExternalDisplay(Display display) {
        return OplusNavBarUtils.isEnableExternalDisplay(display);
    }
}
