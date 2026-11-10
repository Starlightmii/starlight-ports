package com.oplus.sysuiinlineview.utils;

import com.oplus.systemui.provider.ProviderUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiInlineConstants {
    public static final String ACTION_SYS_UI_INLINE_SERVICE = "oplus.inlineui.action.SYS_INLINE_UI";
    public static final SysUiInlineConstants INSTANCE = new SysUiInlineConstants();
    private static final String[] REQUIRED_PERMISSIONS = {ProviderUtil.OPLUS_COMPONENT_SAFE};

    public final String[] getREQUIRED_PERMISSIONS$SystemUiInlineView_release() {
        return REQUIRED_PERMISSIONS;
    }
}
