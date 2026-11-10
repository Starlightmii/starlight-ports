package com.oplus.systemui.statusbar.phone;

import android.graphics.drawable.Icon;
import android.os.UserHandle;
import com.android.internal.statusbar.StatusBarIcon;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarIcon extends StatusBarIcon {
    public int foreDrawableResId;

    public OplusStatusBarIcon(UserHandle userHandle, String str, Icon icon, int i, int i2, CharSequence charSequence, StatusBarIcon.Type type) {
        super(userHandle, str, icon, i, i2, charSequence, type);
        this.foreDrawableResId = -1;
    }

    public OplusStatusBarIcon clone() {
        OplusStatusBarIcon oplusStatusBarIcon = new OplusStatusBarIcon(((StatusBarIcon) this).user, ((StatusBarIcon) this).pkg, ((StatusBarIcon) this).icon, ((StatusBarIcon) this).iconLevel, ((StatusBarIcon) this).number, ((StatusBarIcon) this).contentDescription, StatusBarIcon.Type.SystemIcon);
        ((StatusBarIcon) oplusStatusBarIcon).visible = ((StatusBarIcon) this).visible;
        oplusStatusBarIcon.foreDrawableResId = this.foreDrawableResId;
        return oplusStatusBarIcon;
    }

    public final int getForeDrawableResId() {
        return this.foreDrawableResId;
    }

    public final void setForeDrawableResId(int i) {
        this.foreDrawableResId = i;
    }
}
