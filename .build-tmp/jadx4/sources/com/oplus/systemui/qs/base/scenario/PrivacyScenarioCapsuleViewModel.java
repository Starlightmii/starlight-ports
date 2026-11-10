package com.oplus.systemui.qs.base.scenario;

import android.view.View;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface PrivacyScenarioCapsuleViewModel {
    StateFlow getCapsuleState();

    StateFlow getPrivacyAppName();

    StateFlow getPrivacyPermGroup();

    StateFlow getScenarioCapsuleDrawable();

    Flow getScenarioIconRes();

    Flow getScenarioLightAction();

    StateFlow getScenarioName();

    StateFlow getScenarioPermGroup();

    StateFlow getShowCapsule();

    StateFlow getShowSpacer();

    Flow getTextColorRes();

    StateFlow isTextMarqueeEnable();

    boolean onCapsuleClick(View view);

    void setListening(boolean z);
}
