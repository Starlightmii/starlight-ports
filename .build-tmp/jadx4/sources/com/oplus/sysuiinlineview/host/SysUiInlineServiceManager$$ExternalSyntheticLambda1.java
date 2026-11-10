package com.oplus.sysuiinlineview.host;

import android.os.Bundle;
import com.oplus.sysuiinlineview.ISysUiInlineCallback;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SysUiInlineServiceManager$$ExternalSyntheticLambda1 implements Consumer {
    public /* synthetic */ String f$0;
    public /* synthetic */ ISysUiInlineCallback f$1;
    public /* synthetic */ Bundle f$2;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((SysUiInlineServiceWrapper) obj).onHostViewCreate(this.f$0, this.f$1, this.f$2);
    }
}
