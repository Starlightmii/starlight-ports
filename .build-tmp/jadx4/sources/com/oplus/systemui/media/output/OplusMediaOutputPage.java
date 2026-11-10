package com.oplus.systemui.media.output;

import android.content.Context;
import android.view.View;
import com.oplus.systemui.media.output.data.OutputAudioSharingInteractor;
import com.oplus.systemui.media.output.page.IOplusPage;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusMediaOutputPage implements IOplusPage {
    private final OutputAudioSharingInteractor audioSharingInteractor;
    private final Context context;
    private final OplusMediaOutputPageController pageManager;

    public OplusMediaOutputPage(Context context, OplusMediaOutputPageController oplusMediaOutputPageController, OutputAudioSharingInteractor outputAudioSharingInteractor) {
        this.context = context;
        this.pageManager = oplusMediaOutputPageController;
        this.audioSharingInteractor = outputAudioSharingInteractor;
    }

    public final OutputAudioSharingInteractor getAudioSharingInteractor() {
        return this.audioSharingInteractor;
    }

    public final Context getContext() {
        return this.context;
    }

    public final OplusMediaOutputPageController getPageManager() {
        return this.pageManager;
    }

    @Override // com.oplus.systemui.media.output.page.IOplusPage
    public abstract /* synthetic */ View getView();

    @Override // com.oplus.systemui.media.output.page.IOplusPage
    public void onBack() {
    }
}
