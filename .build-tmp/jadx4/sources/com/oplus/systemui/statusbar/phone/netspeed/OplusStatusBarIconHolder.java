package com.oplus.systemui.statusbar.phone.netspeed;

import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.oplus.systemui.statusbar.phone.dynamic.DynamicIcon;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.IconSource;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarIconHolder extends StatusBarIconHolder {
    public static final Companion Companion = new Companion();
    private DynamicIcon dynamicIcon;
    private NetworkSpeedIconState networkSpeedState;
    public boolean firstAdd = true;
    public int type = 15;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusStatusBarIconHolder fromNetworkIconState(NetworkSpeedIconState networkSpeedIconState) {
            OplusStatusBarIconHolder oplusStatusBarIconHolder = new OplusStatusBarIconHolder();
            oplusStatusBarIconHolder.setNetworkSpeedState(networkSpeedIconState);
            oplusStatusBarIconHolder.setType$vendor__oplus__packages__apps__systemui__android_common__SystemUI_core(15);
            return oplusStatusBarIconHolder;
        }

        public final OplusStatusBarIconHolder fromOplusIconState(DynamicIcon dynamicIcon) {
            OplusStatusBarIconHolder oplusStatusBarIconHolder = new OplusStatusBarIconHolder();
            oplusStatusBarIconHolder.setDynamicIcon(dynamicIcon);
            if ((dynamicIcon != null ? dynamicIcon.getIconSource() : null) instanceof IconSource.StateLinkIconSource) {
                oplusStatusBarIconHolder.setType$vendor__oplus__packages__apps__systemui__android_common__SystemUI_core(16);
                return oplusStatusBarIconHolder;
            }
            oplusStatusBarIconHolder.setType$vendor__oplus__packages__apps__systemui__android_common__SystemUI_core(14);
            return oplusStatusBarIconHolder;
        }
    }

    public final DynamicIcon getDynamicIcon() {
        return this.dynamicIcon;
    }

    public final boolean getFirstAdd() {
        return this.firstAdd;
    }

    public final NetworkSpeedIconState getNetworkSpeedState() {
        return this.networkSpeedState;
    }

    public int getType() {
        return this.type;
    }

    public final boolean isVisible() {
        DynamicIcon dynamicIcon = this.dynamicIcon;
        if (dynamicIcon != null) {
            return dynamicIcon.getVisible();
        }
        return false;
    }

    public final void setDynamicIcon(DynamicIcon dynamicIcon) {
        this.dynamicIcon = dynamicIcon;
    }

    public final void setFirstAdd(boolean z) {
        this.firstAdd = z;
    }

    public final void setNetworkSpeedState(NetworkSpeedIconState networkSpeedIconState) {
        this.networkSpeedState = networkSpeedIconState;
    }

    /* JADX INFO: renamed from: setType$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic, reason: merged with bridge method [inline-methods] */
    public void setType$vendor__oplus__packages__apps__systemui__android_common__SystemUI_core(int i) {
        this.type = i;
    }

    public void setVisible(boolean z) {
        DynamicIcon dynamicIcon = this.dynamicIcon;
        if (dynamicIcon != null) {
            dynamicIcon.setVisible(z);
        }
    }
}
