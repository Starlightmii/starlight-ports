package com.oplus.systemui.volume;

import android.content.Intent;
import android.view.View;
import com.android.systemui.volume.Events;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import java.time.LocalDate;
import java.time.LocalTime;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusVolumeDialogImpl$$ExternalSyntheticLambda7 implements View.OnClickListener {
    public /* synthetic */ OplusVolumeDialogImpl f$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        OplusVolumeDialogImpl oplusVolumeDialogImpl = this.f$0;
        OplusVolumeDialogImpl.H h = oplusVolumeDialogImpl.mHandler;
        OplusVolumeDialogImpl.AnonymousClass1 anonymousClass1 = oplusVolumeDialogImpl.mLaunchSoundSettingsAfterDismissDelay;
        boolean z = oplusVolumeDialogImpl.mExpanded && oplusVolumeDialogImpl.mContext.getResources().getConfiguration().orientation == 2;
        oplusVolumeDialogImpl.dismissH$1(5);
        Events.writeEvent(8, new Object[0]);
        if (Util.isActivityAtTop(oplusVolumeDialogImpl.mSoundSettingsActivity)) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "top activity name: " + Util.getCurrentTopActivityNameInActivityStack());
            oplusVolumeDialogImpl.mPendingLandscapeExpandedSettingsLaunch = false;
            h.removeCallbacks(anonymousClass1);
            return;
        }
        if (z) {
            oplusVolumeDialogImpl.mPendingLandscapeExpandedSettingsLaunch = true;
            h.removeCallbacks(anonymousClass1);
            h.postDelayed(anonymousClass1, 200L);
        } else {
            oplusVolumeDialogImpl.mPendingLandscapeExpandedSettingsLaunch = false;
            h.removeCallbacks(anonymousClass1);
            oplusVolumeDialogImpl.mactivityStarter.startActivity(new Intent("android.settings.SOUND_SETTINGS"), true);
        }
        if (oplusVolumeDialogImpl.mSettingsButtonClickTime.isEmpty()) {
            oplusVolumeDialogImpl.mSettingsButtonClickTime = LocalDate.now().toString();
        }
        oplusVolumeDialogImpl.mSettingsButtonClickTime += ";" + LocalTime.now().getHour();
        oplusVolumeDialogImpl.mSettingsButtonClickCount++;
    }
}
