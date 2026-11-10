package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.pipeline.wifi.ui.view.ModernStatusBarWifiView;
import com.android.systemui.statusbar.seedling.OplusSeedingCardControllerEx;
import com.oplus.systemui.capsule.FlavorOneCapsuleController;
import com.oplus.systemui.capsule.StateMonitor;
import com.oplus.systemui.statusbar.phone.signal.widget.OplusModernStatusBarWifiView;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeedlingCardController extends OplusSeedingCardControllerEx {
    private final Context context;
    private final HiddenIconDispatcher hiddenIconDispatcher;
    private final SeedlingPluginManager seedlingPluginManager;

    public OplusSeedlingCardController(Context context, SeedlingPluginManager seedlingPluginManager, HiddenIconDispatcher hiddenIconDispatcher) {
        super(context);
        this.context = context;
        this.seedlingPluginManager = seedlingPluginManager;
        this.hiddenIconDispatcher = hiddenIconDispatcher;
    }

    public final void addSeedlingPluginListener() {
        this.seedlingPluginManager.init();
    }

    public void calculateIconTranslations(StatusBarLocation statusBarLocation, StatusIconContainer statusIconContainer, boolean z) {
        Pair<Integer, Integer> locationByDefault;
        float scaleFraction;
        IScaledAncestorView scaledAncestorViewOrNull = ILocationCalculatorKt.getScaledAncestorViewOrNull(statusIconContainer);
        if (scaledAncestorViewOrNull != null) {
            scaleFraction = scaledAncestorViewOrNull.getScaleFraction(this.hiddenIconDispatcher.getLastShouldScale(statusBarLocation));
            locationByDefault = ILocationCalculatorKt.getLocationByScaledAncestorView(statusIconContainer, scaledAncestorViewOrNull.getScaledAncestorView(), scaleFraction, scaledAncestorViewOrNull.getPivotXDirection());
        } else {
            locationByDefault = ILocationCalculatorKt.getLocationByDefault(statusIconContainer);
            scaleFraction = 1.0f;
        }
        int childCount = statusIconContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ModernStatusBarWifiView childAt = statusIconContainer.getChildAt(i);
            int i2 = StatusIconContainer.mSetUXCount;
            StatusIconContainer.StatusIconState statusIconState = (StatusIconContainer.StatusIconState) childAt.getTag(2131365374);
            if (statusIconState != null) {
                statusIconState.allowIndependentAlphaAnimation = false;
                if (statusIconState.visibleState == 0) {
                    int xTranslation = (int) ((statusIconState.getXTranslation() * scaleFraction) + ((Number) locationByDefault.getFirst()).floatValue());
                    if (childAt instanceof OplusModernStatusBarWifiView) {
                        int dimension = (int) (((OplusModernStatusBarWifiView) childAt).getResources().getDimension(2131173107) * scaleFraction);
                        if (statusIconContainer.isLayoutRtl()) {
                            dimension = -dimension;
                        }
                        xTranslation += dimension;
                    }
                    if (HiddenIconDispatcher.Companion.isInAreas(this.hiddenIconDispatcher.getHiddenAreas(statusBarLocation), new Pair<>(Integer.valueOf(xTranslation), Integer.valueOf((int) ((childAt.getWidth() * scaleFraction) + xTranslation))))) {
                        statusIconState.visibleState = 2;
                        if (z) {
                            statusIconState.allowIndependentAlphaAnimation = true;
                            statusIconState.setAlpha(0.0f, "seedling");
                        }
                    }
                }
            }
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isShowActivityOverLockscreen() {
        return this.seedlingPluginManager.isShowActivityOverLockscreen;
    }

    public final boolean isSystemCapsuleShow() {
        return this.seedlingPluginManager.hasSystemCapsule;
    }

    public final void setShowActivityOverLockscreen(boolean z) {
        this.seedlingPluginManager.setLaunchActivityOverLockscreen(z);
    }

    public final boolean shouldShowSystemPromptView() {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isSupportMiniCapsule() && ((FlavorOneCapsuleController) DependencyEx.sDependency.getDependency(FlavorOneCapsuleController.class)) != null && StateMonitor.Companion.getInstance().mMiniCapsuleSwitchEnable) {
            return true;
        }
        return !this.seedlingPluginManager.isPluginAvailable;
    }
}
