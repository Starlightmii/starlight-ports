package com.oplus.systemui.panelanimation;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPanelAnimationExImpl$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPanelAnimationExImpl f$0;

    public /* synthetic */ OplusPanelAnimationExImpl$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.f$0;
        switch (i) {
            case 0:
                return OplusPanelAnimationExImpl.$r8$lambda$Cg5vUqszVL1AxP797y47CSh3SDw(oplusPanelAnimationExImpl);
            case 1:
                return OplusPanelAnimationExImpl.m112$r8$lambda$7cyol_fO3bzc9vR7xbZw7O1SI(oplusPanelAnimationExImpl);
            default:
                return Float.valueOf(oplusPanelAnimationExImpl.getThresholdForTranslationY());
        }
    }
}
