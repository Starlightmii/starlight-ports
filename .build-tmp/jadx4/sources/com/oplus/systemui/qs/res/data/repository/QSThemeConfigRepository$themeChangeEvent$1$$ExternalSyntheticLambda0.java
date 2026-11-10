package com.oplus.systemui.qs.res.data.repository;

import com.oplusos.systemui.common.receiver.ThemeReceiver;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSThemeConfigRepository$themeChangeEvent$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ QSThemeConfigRepository$themeChangeEvent$1$listener$1 f$0;

    public final Object invoke() {
        ThemeReceiver.Companion.getInstance().removeListener(this.f$0);
        return Unit.INSTANCE;
    }
}
