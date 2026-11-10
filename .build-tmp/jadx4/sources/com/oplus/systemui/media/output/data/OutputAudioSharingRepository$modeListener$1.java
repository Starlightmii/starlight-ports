package com.oplus.systemui.media.output.data;

import android.media.AudioManager;
import com.oplus.systemui.media.output.OplusMediaOutputSwitchingPage;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OutputAudioSharingRepository$modeListener$1 implements AudioManager.OnModeChangedListener {
    public /* synthetic */ OutputAudioSharingRepository this$0;

    @Override // android.media.AudioManager.OnModeChangedListener
    public final void onModeChanged(int i) {
        this.this$0.setPhoneMode(i);
        OplusMediaOutputSwitchingPage.onShow.2 listener = this.this$0.getListener();
        if (listener != null) {
            OplusMediaOutputSwitchingPage oplusMediaOutputSwitchingPage = listener.this$0;
            OplusMediaOutputSwitchingPage.onShow.2.1 r2 = new OplusMediaOutputSwitchingPage.onShow.2.1(0);
            r2.this$0 = oplusMediaOutputSwitchingPage;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(r2);
        }
    }
}
