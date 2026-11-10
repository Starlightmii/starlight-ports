package com.oplus.systemui.preventmode;

import android.app.WindowConfiguration;
import android.content.res.Configuration;
import android.graphics.Rect;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPreventModeExImpl$configurationChangeListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ OplusPreventModeExImpl this$0;

    public final void onConfigChanged(Configuration configuration) {
        WindowConfiguration windowConfiguration;
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "onConfigChanged");
        OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
        Rect maxBounds = oplusPreventModeExImpl.context.getResources().getConfiguration().windowConfiguration.getMaxBounds();
        Rect maxBounds2 = (configuration == null || (windowConfiguration = configuration.windowConfiguration) == null) ? null : windowConfiguration.getMaxBounds();
        oplusPreventModeExImpl.setBoundsChangedByRotation(!Intrinsics.areEqual(maxBounds, maxBounds2) && maxBounds2 != null && maxBounds.width() == maxBounds2.height() && maxBounds.height() == maxBounds2.width());
        if (configuration != null) {
            oplusPreventModeExImpl.context = oplusPreventModeExImpl.originContext.createConfigurationContext(configuration);
        }
    }

    public final void onDensityOrFontScaleChanged() {
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "onDensityOrFontScaleChanged");
        OplusPreventModeExImpl.access$rebuildController(this.this$0);
    }

    public final void onFontChanged(Configuration configuration) {
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "onFontChanged");
        OplusPreventModeExImpl.access$rebuildController(this.this$0);
    }

    public final void onLocaleListChanged() {
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "onLocaleListChanged");
        OplusPreventModeExImpl.access$rebuildController(this.this$0);
    }

    public final void onMaxBoundsChanged() {
        OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, "onMaxBoundsChanged, isRotation: " + oplusPreventModeExImpl.getBoundsChangedByRotation());
        if (oplusPreventModeExImpl.getBoundsChangedByRotation()) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet()) {
                return;
            }
        }
        OplusPreventModeExImpl.access$rebuildController(oplusPreventModeExImpl);
    }
}
