package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl$;
import com.android.systemui.statusbar.phone.ui.StatusBarIconList;
import com.oplus.systemui.statusbar.phone.dynamic.DynamicIcon;
import com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView;
import com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView;
import com.oplus.systemui.statusbar.phone.netspeed.NetworkSpeedIconState;
import com.oplus.systemui.statusbar.phone.netspeed.OplusStatusBarIconHolder;
import com.oplus.systemui.statusbar.phone.netspeed.widget.NetworkSpeedView;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusIconController {
    private StatusBarIconController statusBarIconController;

    public OplusIconController(StatusBarIconController statusBarIconController) {
        this.statusBarIconController = statusBarIconController;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [android.view.View, com.android.systemui.statusbar.StatusIconDisplayable, com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView] */
    /* JADX WARN: Type inference failed for: r3v9, types: [android.view.View, com.android.systemui.statusbar.StatusIconDisplayable, com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView] */
    public final StatusIconDisplayable addHolder(int i, String str, ViewGroup viewGroup, StatusBarIconHolder statusBarIconHolder, boolean z) {
        switch (statusBarIconHolder.getType()) {
            case 14:
                if (statusBarIconHolder instanceof OplusStatusBarIconHolder) {
                    ?? oplusStatusBarIconView = new OplusStatusBarIconView(viewGroup.getContext(), str, z);
                    OplusStatusBarIconHolder oplusStatusBarIconHolder = (OplusStatusBarIconHolder) statusBarIconHolder;
                    DynamicIcon dynamicIcon = oplusStatusBarIconHolder.getDynamicIcon();
                    if (dynamicIcon != null) {
                        oplusStatusBarIconView.applyDynamicIcon(dynamicIcon, oplusStatusBarIconHolder.getFirstAdd());
                    }
                    oplusStatusBarIconHolder.setFirstAdd(false);
                    viewGroup.addView((View) oplusStatusBarIconView, i, new LinearLayout.LayoutParams(-2, -2));
                    return oplusStatusBarIconView;
                }
                return null;
            case 15:
                if (statusBarIconHolder instanceof OplusStatusBarIconHolder) {
                    NetworkSpeedIconState networkSpeedState = ((OplusStatusBarIconHolder) statusBarIconHolder).getNetworkSpeedState();
                    Context context = viewGroup.getContext();
                    int i2 = NetworkSpeedView.$r8$clinit;
                    NetworkSpeedView networkSpeedView = (NetworkSpeedView) LayoutInflater.from(context).inflate(2131559188, (ViewGroup) null);
                    networkSpeedView.setSlot(str);
                    networkSpeedView.setBlocked(z);
                    networkSpeedView.setVisibleState(0);
                    networkSpeedView.applyNetworkState(networkSpeedState);
                    viewGroup.addView(networkSpeedView, i, new LinearLayout.LayoutParams(viewGroup.getResources().getDimensionPixelSize(2131173072), -1));
                    return networkSpeedView;
                }
                return null;
            case 16:
                if (statusBarIconHolder instanceof OplusStatusBarIconHolder) {
                    ?? securePaymentIconView = new SecurePaymentIconView(viewGroup.getContext(), str, z);
                    OplusStatusBarIconHolder oplusStatusBarIconHolder2 = (OplusStatusBarIconHolder) statusBarIconHolder;
                    DynamicIcon dynamicIcon2 = oplusStatusBarIconHolder2.getDynamicIcon();
                    if (dynamicIcon2 != null) {
                        securePaymentIconView.applyDynamicIcon(dynamicIcon2, oplusStatusBarIconHolder2.getFirstAdd());
                    }
                    oplusStatusBarIconHolder2.setFirstAdd(false);
                    viewGroup.addView((View) securePaymentIconView, i, new LinearLayout.LayoutParams(-2, -2));
                    return securePaymentIconView;
                }
                return null;
            default:
                return null;
        }
    }

    public final void onSetIconHolder(int i, StatusBarIconHolder statusBarIconHolder, ViewGroup viewGroup) {
        switch (statusBarIconHolder.getType()) {
            case 14:
            case 16:
                if (statusBarIconHolder instanceof OplusStatusBarIconHolder) {
                    OplusStatusBarIconView oplusStatusBarIconView = (OplusStatusBarIconView) viewGroup.getChildAt(i);
                    DynamicIcon dynamicIcon = ((OplusStatusBarIconHolder) statusBarIconHolder).getDynamicIcon();
                    if (dynamicIcon != null) {
                        oplusStatusBarIconView.applyDynamicIcon(dynamicIcon, true);
                    }
                }
                break;
            case 15:
                if (statusBarIconHolder instanceof OplusStatusBarIconHolder) {
                    ((NetworkSpeedView) viewGroup.getChildAt(i)).applyNetworkState(((OplusStatusBarIconHolder) statusBarIconHolder).getNetworkSpeedState());
                }
                break;
        }
    }

    public final void setNetWorkSpeedIcon(String str, NetworkSpeedIconState networkSpeedIconState) {
        if (networkSpeedIconState != null && networkSpeedIconState.getVisible()) {
            this.statusBarIconController.setIcon(str, OplusStatusBarIconHolder.Companion.fromNetworkIconState(networkSpeedIconState));
            return;
        }
        StatusBarIconControllerImpl statusBarIconControllerImpl = this.statusBarIconController;
        StatusBarIconList statusBarIconList = statusBarIconControllerImpl.mStatusBarIconList;
        if (statusBarIconControllerImpl.mStatusBarPipelineFlags.isIconControlledByFlags(str)) {
            Log.i("StatusBarIconController", "Ignoring removal of (" + str + "). It should be controlled elsewhere");
            return;
        }
        if (statusBarIconList.getIconHolder(0, str) == null) {
            return;
        }
        int viewIndex = statusBarIconList.getViewIndex(0, str);
        ((StatusBarIconList.Slot) statusBarIconList.mSlots.get(statusBarIconList.findOrInsertSlot(str))).mHolder = null;
        ArrayList arrayList = statusBarIconControllerImpl.mIconGroups;
        StatusBarIconControllerImpl$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new StatusBarIconControllerImpl$.ExternalSyntheticLambda2(0);
        externalSyntheticLambda2.f$0 = viewIndex;
        VarHandle.storeStoreFence();
        arrayList.forEach(externalSyntheticLambda2);
    }

    public final void setOplusIcon(String str, DynamicIcon dynamicIcon) {
        this.statusBarIconController.setIcon(str, OplusStatusBarIconHolder.Companion.fromOplusIconState(dynamicIcon));
    }

    public final void setOplusIconVisible(String str, boolean z) {
        this.statusBarIconController.setIconVisibility(str, z);
    }
}
