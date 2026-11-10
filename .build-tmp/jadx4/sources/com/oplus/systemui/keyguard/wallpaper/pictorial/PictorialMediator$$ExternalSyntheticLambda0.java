package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.slice.widget.RowView$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ PictorialMediator f$0;
    public /* synthetic */ boolean f$1;

    @Override // java.lang.Runnable
    public final void run() {
        PictorialMediator pictorialMediator = this.f$0;
        boolean z = this.f$1;
        AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
        if (pictorialMediator.isOplusGLStyle()) {
            SharedPreferences sharedPreferences = pictorialMediator.mContext.getSharedPreferences("pictorial_info", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialMediator", RowView$.ExternalSyntheticOutline0.m("setChildModeOplusGLStatus isChildMode = ", ",isEnablePlaySwitchOn():", z), pictorialMediator.mEnablePlaySwitchOn);
            if (z && pictorialMediator.mEnablePlaySwitchOn) {
                editorEdit.putInt("oplus_gl_status", 1);
                editorEdit.apply();
                Settings.System.putIntForUser(pictorialMediator.mContentResolver, "oplus_customize_pictorial_enable_play", 0, KeyguardUpdateMonitor.getCurrentUser());
                pictorialMediator.mEnablePlaySwitchOn = false;
                return;
            }
            if (z) {
                return;
            }
            int i = sharedPreferences.getInt("oplus_gl_status", -1);
            OplusLockIconView$.ExternalSyntheticOutline0.m(i, "setChildModeOplusGLStatus oplusGLStatus = ", "PictorialMediator");
            if (i == 1) {
                editorEdit.putInt("oplus_gl_status", -1);
                editorEdit.apply();
                Settings.System.putIntForUser(pictorialMediator.mContentResolver, "oplus_customize_pictorial_enable_play", 1, KeyguardUpdateMonitor.getCurrentUser());
                pictorialMediator.mEnablePlaySwitchOn = true;
            }
        }
    }
}
