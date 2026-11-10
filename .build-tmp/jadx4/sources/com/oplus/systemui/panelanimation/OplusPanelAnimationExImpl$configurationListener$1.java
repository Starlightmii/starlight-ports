package com.oplus.systemui.panelanimation;

import android.content.res.Configuration;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ OplusPanelAnimationExImpl this$0;

    public final void onConfigChanged(Configuration configuration) {
        OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.this$0;
        oplusPanelAnimationExImpl.context.getResources().getDimension(2131173668);
        oplusPanelAnimationExImpl.context.getResources().getDimension(2131173669);
        oplusPanelAnimationExImpl.context.getResources().getDimension(2131173670);
        oplusPanelAnimationExImpl.distanceRange = oplusPanelAnimationExImpl.context.getResources().getDimension(2131172462);
        oplusPanelAnimationExImpl.context.getResources().getDimension(2131165733);
    }
}
