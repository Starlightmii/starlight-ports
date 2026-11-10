package com.oplus.systemui.qs.detail.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.sysuiinlineview.host.SysUiInlineViewHost;
import com.oplus.sysuiinlineview.host.controller.SysUiInlineHostViewController;
import com.oplus.sysuiinlineview.host.view.SysUiInlineHostView;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSTileInlineDetailAdapter implements DetailAdapter {
    private final String category;
    private final Intent intent;
    private final String tileSpec;
    private final UserTracker userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        QSTileInlineDetailAdapter create(String str, Intent intent);
    }

    public QSTileInlineDetailAdapter(UserTracker userTracker, String str, Intent intent) {
        this.userTracker = userTracker;
        this.tileSpec = str;
        this.intent = intent;
        this.category = SystemUIInlineViewHelper.getTileDetailCategory(str);
    }

    public View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
        SysUiInlineHostViewController controller;
        Bundle baseExtras;
        SysUiInlineViewHost inlineViewHost = SystemUIInlineViewHelper.getInlineViewHost(context, this.category, this.userTracker.getUserHandle());
        SysUiInlineHostView sysUiInlineHostViewBuildHostView = inlineViewHost != null ? inlineViewHost.buildHostView(context, viewGroup, z) : null;
        if (sysUiInlineHostViewBuildHostView != null && (controller = sysUiInlineHostViewBuildHostView.getController()) != null && (baseExtras = controller.getBaseExtras()) != null) {
            SystemUIInlineViewHelper.setQSDetailDarkTheme(baseExtras, QsColorUtil.isNeedDarkThemeColor(context, false));
            SystemUIInlineViewHelper.setQSDetailThirdTheme(baseExtras, QsColorUtil.isGlobalThemeApplied(context));
            baseExtras.putBoolean(SystemUIInlineViewHelper.BUNDLE_KEY_IS_SUPPORT_LIGHT, BaseQSResPool.isSupportSpotLight());
        }
        return sysUiInlineHostViewBuildHostView;
    }

    public Intent getSettingsIntent() {
        return this.intent;
    }

    public String getTag() {
        return this.tileSpec;
    }
}
