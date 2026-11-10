package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import kotlin.jvm.functions.Function0;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class EditableVolumeViewHolder$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        QSVolume qSVolumeBindQSVolume;
        EditableVolumeViewHolder.Companion companion = EditableVolumeViewHolder.Companion;
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        if (proxy != null && (qSVolumeBindQSVolume = proxy.bindQSVolume()) != null) {
            return qSVolumeBindQSVolume;
        }
        Path$.ExternalSyntheticBUOutline0.m$1("cannot get QSVolume from host");
        return null;
    }
}
