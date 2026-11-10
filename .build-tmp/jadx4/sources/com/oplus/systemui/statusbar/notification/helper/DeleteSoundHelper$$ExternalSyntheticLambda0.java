package com.oplus.systemui.statusbar.notification.helper;

import android.app.AppGlobals;
import android.content.Context;
import android.provider.Settings;
import com.oplus.systemui.statusbar.notification.util.SoundUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class DeleteSoundHelper$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ DeleteSoundHelper f$0;
    public /* synthetic */ Context f$1;

    @Override // java.lang.Runnable
    public final void run() {
        DeleteSoundHelper deleteSoundHelper = this.f$0;
        Context context = this.f$1;
        if (deleteSoundHelper.mPrepared) {
            return;
        }
        deleteSoundHelper.mResolver = AppGlobals.getInitialApplication().getContentResolver();
        deleteSoundHelper.mGlobalDeleteSoundObserver.registerForUserSwitch(AppGlobals.getInitialApplication(), false);
        deleteSoundHelper.mDeleteSoundSwitch = Settings.Secure.getIntForUser(deleteSoundHelper.mResolver, "global_delete_sound", 1, -2) == 1;
        SoundUtil soundUtil = new SoundUtil();
        deleteSoundHelper.mColorSoundUtil = soundUtil;
        DeleteSoundHelper$$ExternalSyntheticLambda1 deleteSoundHelper$$ExternalSyntheticLambda1 = new DeleteSoundHelper$$ExternalSyntheticLambda1();
        deleteSoundHelper$$ExternalSyntheticLambda1.f$0 = deleteSoundHelper;
        VarHandle.storeStoreFence();
        soundUtil.setCompleteListener(deleteSoundHelper$$ExternalSyntheticLambda1);
        deleteSoundHelper.mContext = context;
    }
}
