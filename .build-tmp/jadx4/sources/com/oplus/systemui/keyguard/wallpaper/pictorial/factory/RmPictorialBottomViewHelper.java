package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DependencyEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.oplus.systemui.biometrics.finger.KeyguardFingerprintUtils;
import com.oplus.systemui.biometrics.finger.udfps.OnScreenFingerprintUiMech;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppDataImpl;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RmPictorialBottomViewHelper implements PictorialBottomViewHelper {
    public int mIconLocation;
    public int mIconSize;
    public NotificationPanelViewController mPanelViewController;
    public boolean mIsSupportWidgetTagline = false;
    public int mBottomStyle = 0;

    public final PictorialMessageView createBottomView(int i, int i2, Context context) {
        if (this.mIsSupportWidgetTagline) {
            i2 = 2131559555;
        }
        if (this.mBottomStyle == 1) {
            i = i2;
        }
        return (PictorialMessageView) LayoutInflater.from(context).inflate(i, (ViewGroup) null);
    }

    public final boolean isFingeprintShow() {
        OnScreenFingerprintUiMech onScreenFingerprintUiMech;
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        boolean z = (pictorialMediator == null || (onScreenFingerprintUiMech = pictorialMediator.mOnScreenFingerprintUiMech) == null) ? false : onScreenFingerprintUiMech.isFingerprintIconShow;
        OplusLockIconView$.ExternalSyntheticOutline0.m("isFingeprintShow :", "RmPictorialBottomViewHelper", z);
        if (!z) {
            this.mIconLocation = 0;
            this.mIconSize = 0;
            return z;
        }
        KeyguardFingerprintUtils keyguardFingerprintUtils = KeyguardFingerprintUtils.INSTANCE;
        this.mIconLocation = keyguardFingerprintUtils.getIconMarginBottom();
        this.mIconSize = keyguardFingerprintUtils.getPressedIconSize();
        return z;
    }

    public final void updatePictorialBottomView(Context context) {
        KgdLogUtil.i("RmPictorialBottomViewHelper", " updatePictorialBottomView");
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        boolean z = pictorialMediator.mTaglineSwitchOn;
        this.mBottomStyle = z ? 1 : 0;
        float dimension = z ? context.getResources().getDimension(2131168830) : context.getResources().getDimension(2131168831);
        PictorialAppDataImpl pictorialAppDataImpl = pictorialMediator.mPictorialAppDataImpl;
        this.mIsSupportWidgetTagline = pictorialAppDataImpl != null ? pictorialAppDataImpl.mIsSupportWidgetTaglineMessageView : false;
        NotificationPanelViewController panelViewController = pictorialMediator.getPanelViewController();
        this.mPanelViewController = panelViewController;
        if (panelViewController != null) {
            KgdLogUtil.i("RmPictorialBottomViewHelper", "updatePictorialBottomView setAmbientIndicationBottomPadding");
            this.mPanelViewController.setAmbientIndicationBottomPadding((int) dimension);
        }
    }
}
