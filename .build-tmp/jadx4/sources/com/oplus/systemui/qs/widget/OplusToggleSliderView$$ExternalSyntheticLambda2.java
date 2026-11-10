package com.oplus.systemui.qs.widget;

import android.content.Intent;
import android.view.View;
import android.view.animation.Interpolator;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.ActivityStarter;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.pipeline.data.repository.WellBeingAssistantRepositoryImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusToggleSliderView$$ExternalSyntheticLambda2 implements View.OnLongClickListener {
    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        Interpolator interpolator = OplusToggleSliderView.ICON_COLOR_TRANSITION_INTERPOLATOR;
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
            return true;
        }
        WellBeingAssistantRepositoryImpl.Companion.getClass();
        if (WellBeingAssistantRepositoryImpl.isWellBeingAssistantState) {
            return true;
        }
        ((ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class)).postStartActivityDismissingKeyguard(new Intent("android.settings.DISPLAY_SETTINGS"), 0);
        return true;
    }
}
