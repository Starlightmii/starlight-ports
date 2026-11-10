package com.oplus.systemui.statusbar.notification.helper;

import android.provider.Settings;
import com.android.systemui.ExpandHelper$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class DeleteSoundHelper$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ DeleteSoundHelper f$0;

    public /* synthetic */ DeleteSoundHelper$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        DeleteSoundHelper deleteSoundHelper = this.f$0;
        switch (i) {
            case 0:
                deleteSoundHelper.play();
                break;
            case 1:
                deleteSoundHelper.mDeleteSoundSwitch = Settings.Secure.getIntForUser(deleteSoundHelper.mResolver, "global_delete_sound", 1, -2) == 1;
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "DeleteSoundManager", new StringBuilder("onChange: mDeleteSoundSwitchObserver mDeleteSoundSwitch = "), deleteSoundHelper.mDeleteSoundSwitch);
                break;
            default:
                deleteSoundHelper.mPrepared = true;
                deleteSoundHelper.play();
                break;
        }
    }
}
