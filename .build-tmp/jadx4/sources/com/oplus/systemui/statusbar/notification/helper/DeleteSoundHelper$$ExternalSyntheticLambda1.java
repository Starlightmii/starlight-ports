package com.oplus.systemui.statusbar.notification.helper;

import android.media.SoundPool;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class DeleteSoundHelper$$ExternalSyntheticLambda1 implements SoundPool.OnLoadCompleteListener {
    public /* synthetic */ DeleteSoundHelper f$0;

    @Override // android.media.SoundPool.OnLoadCompleteListener
    public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
        DeleteSoundHelper deleteSoundHelper = this.f$0;
        ExecutorService executorService = deleteSoundHelper.mSoundEexecutor;
        DeleteSoundHelper$$ExternalSyntheticLambda2 deleteSoundHelper$$ExternalSyntheticLambda2 = new DeleteSoundHelper$$ExternalSyntheticLambda2(2);
        deleteSoundHelper$$ExternalSyntheticLambda2.f$0 = deleteSoundHelper;
        VarHandle.storeStoreFence();
        executorService.execute(deleteSoundHelper$$ExternalSyntheticLambda2);
    }
}
