package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.statusbar.phone.StatusBarPaddingProvider;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FixedKeyguardStatusBarView extends FixedStatusBarView implements DarkIconDispatcher.DarkReceiver {
    public static final Companion Companion = new Companion();
    private static final String TAG = "FixedKeyguardStatusBarView";
    private final StatusBarPaddingProvider statusBarPaddingProvider;

    public /* synthetic */ FixedKeyguardStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider, AttributeSet attributeSet, int i, String str, int i2) {
        this(context, statusBarPaddingProvider, (i2 & 4) != 0 ? null : attributeSet, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? null : str);
    }

    @Override // com.oplus.systemui.statusbar.widget.FixedStatusBarView
    public final void updateSafeInsets() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("updateSafeInsetsleftMargin = ", getLeftMargin(), getRightMargin(), ", rightMargin = ", "FixedKeyguardStatusBarView-->"));
        }
        this.statusBarPaddingProvider.updateKeyguardStatusBarPadding(this, new Pair<>(Integer.valueOf(getLeftMargin()), Integer.valueOf(getRightMargin())));
    }

    public FixedKeyguardStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider, AttributeSet attributeSet) {
        this(context, statusBarPaddingProvider, attributeSet, 0, null, 24);
    }

    public FixedKeyguardStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider, AttributeSet attributeSet, int i) {
        this(context, statusBarPaddingProvider, attributeSet, i, null, 16);
    }

    public FixedKeyguardStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider) {
        this(context, statusBarPaddingProvider, null, 0, null, 28);
    }

    public FixedKeyguardStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider, AttributeSet attributeSet, int i, String str) {
        super(context, statusBarPaddingProvider, attributeSet, i, str);
        this.statusBarPaddingProvider = statusBarPaddingProvider;
    }
}
