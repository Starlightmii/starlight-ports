package com.oplus.systemui.qs.tileex;

import android.content.ComponentName;
import android.media.AudioManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSTileExImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QSTileExImpl f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        QSTileExImpl qSTileExImpl = this.f$0;
        switch (i) {
            case 0:
                QSTileExImpl.Companion companion = QSTileExImpl.Companion;
                return qSTileExImpl.newUiFirstInteractor();
            case 1:
                QSTileExImpl.Companion companion2 = QSTileExImpl.Companion;
                return new ComponentName(qSTileExImpl.getContext(), qSTileExImpl.getContext().getPackageName());
            default:
                QSTileExImpl.Companion companion3 = QSTileExImpl.Companion;
                return (AudioManager) qSTileExImpl.getContext().getSystemService(AudioManager.class);
        }
    }
}
