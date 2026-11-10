package com.oplus.systemui.statusbar;

import android.R;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.oplus.systemui.statusbar.phone.StatusBarPaddingProvider;
import com.oplus.systemui.statusbar.widget.FixedKeyguardStatusBarView;
import com.oplus.systemui.statusbar.widget.FixedStatusBarView;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FixedStatusBarControllerExImpl implements FixedStatusBarControllerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "FixedStatusBarController";
    private final Context context;
    public boolean fixedModeEnabled;
    private final ArrayList<FixedStatusBarView> fixedStatusBarViewList = new ArrayList<>();
    public boolean panelExpanded;
    private final StatusBarPaddingProvider statusBarPaddingProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public FixedStatusBarControllerExImpl(Context context, StatusBarPaddingProvider statusBarPaddingProvider) {
        this.context = context;
        this.statusBarPaddingProvider = statusBarPaddingProvider;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void onPanelExpandedChanged(boolean z) {
        if (!this.fixedModeEnabled) {
            this.panelExpanded = z;
            return;
        }
        if (this.panelExpanded == z) {
            return;
        }
        this.panelExpanded = z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" onPanelExpandedChanged: isExpanded=", ", fixedModeEnabled=", "FixedStatusBarController-->", z, this.fixedModeEnabled));
        }
        int i = (this.fixedModeEnabled && this.panelExpanded) ? 8 : 0;
        applyFixedStatusBarVisibility.1 r0 = new applyFixedStatusBarVisibility.1();
        r0.this$0 = this;
        r0.$visibility = i;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }

    public final void removeFixedStatusBar() {
        ArrayList<FixedStatusBarView> arrayList = this.fixedStatusBarViewList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            FixedStatusBarView fixedStatusBarView = arrayList.get(i);
            i++;
            FixedStatusBarView fixedStatusBarView2 = fixedStatusBarView;
            ((ViewGroup) fixedStatusBarView2.getParent()).removeView(fixedStatusBarView2);
        }
        this.fixedStatusBarViewList.clear();
    }

    @Override // com.oplus.systemui.statusbar.FixedStatusBarControllerEx
    public void updateFixedKeyguardStatusBarView(boolean z, KeyguardStatusBarView keyguardStatusBarView, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "FixedStatusBarController-->".concat(" updateFixedKeyguardStatusBarView:" + z + ", fixedTime:" + str));
        }
        int i = 0;
        if (!z) {
            this.fixedModeEnabled = false;
            removeFixedStatusBar();
            return;
        }
        removeFixedStatusBar();
        this.fixedModeEnabled = true;
        Context context = keyguardStatusBarView.getContext();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, context.getResources().getDimensionPixelSize(R.dimen.text_size_subhead_material));
        FixedKeyguardStatusBarView fixedKeyguardStatusBarView = new FixedKeyguardStatusBarView(context, this.statusBarPaddingProvider, null, 0, str, 12);
        if (this.fixedModeEnabled && this.panelExpanded) {
            i = 8;
        }
        fixedKeyguardStatusBarView.setVisibility(i);
        if (keyguardStatusBarView.getParent() != null) {
            ((ViewGroup) keyguardStatusBarView.getParent()).addView(fixedKeyguardStatusBarView, layoutParams);
        }
        this.fixedStatusBarViewList.add(fixedKeyguardStatusBarView);
    }

    @Override // com.oplus.systemui.statusbar.FixedStatusBarControllerEx
    public void updateFixedStatusBarView(boolean z, PhoneStatusBarView phoneStatusBarView, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "FixedStatusBarController-->".concat(" updateFixedStatusBarView:" + z + ", fixedTime:" + str));
        }
        int i = 0;
        if (!z) {
            this.fixedModeEnabled = false;
            removeFixedStatusBar();
            return;
        }
        removeFixedStatusBar();
        this.fixedModeEnabled = true;
        Context context = phoneStatusBarView.getContext();
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, SystemBarUtils.getStatusBarHeight(context));
        FixedStatusBarView fixedStatusBarView = new FixedStatusBarView(context, this.statusBarPaddingProvider, null, 0, str, 12);
        if (this.fixedModeEnabled && this.panelExpanded) {
            i = 8;
        }
        fixedStatusBarView.setVisibility(i);
        phoneStatusBarView.addView(fixedStatusBarView, layoutParams);
        this.fixedStatusBarViewList.add(fixedStatusBarView);
    }
}
