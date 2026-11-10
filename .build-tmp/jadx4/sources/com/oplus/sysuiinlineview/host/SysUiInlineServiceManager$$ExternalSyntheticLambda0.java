package com.oplus.sysuiinlineview.host;

import android.os.Bundle;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SysUiInlineServiceManager$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ String f$0;
    public /* synthetic */ Bundle f$1;

    public /* synthetic */ SysUiInlineServiceManager$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ((SysUiInlineServiceWrapper) obj).onHostViewDestroy(this.f$0, this.f$1);
                break;
            case 1:
                ((SysUiInlineServiceWrapper) obj).onHostViewLayout(this.f$0, this.f$1);
                break;
            case 2:
                ((SysUiInlineServiceWrapper) obj).onHostViewCommand(this.f$0, this.f$1);
                break;
            case 3:
                ((SysUiInlineServiceWrapper) obj).onHostViewPause(this.f$0, this.f$1);
                break;
            case 4:
                ((SysUiInlineServiceWrapper) obj).onHostViewResume(this.f$0, this.f$1);
                break;
            default:
                ((SysUiInlineServiceWrapper) obj).onHostViewConfigurationChanged(this.f$0, this.f$1);
                break;
        }
    }
}
