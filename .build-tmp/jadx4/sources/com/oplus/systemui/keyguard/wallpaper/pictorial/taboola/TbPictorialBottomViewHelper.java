package com.oplus.systemui.keyguard.wallpaper.pictorial.taboola;

import android.content.Context;
import android.content.res.Resources;
import android.widget.FrameLayout;
import com.android.systemui.DependencyEx;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.helper.ScreenSizeHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialFunctionContainer;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialGuideEntity;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.RmPictorialBottomViewHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.view.PictorialGuideView;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TbPictorialBottomViewHelper extends RmPictorialBottomViewHelper {
    public WeakReference mContainerWeak;
    public PictorialGuideEntity mGuideEntity;
    public WeakReference mPictorialGuideViewWeak;

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper
    public final PictorialMessageView createPictorialMessageView(Context context) {
        updatePictorialBottomView(context);
        return createBottomView(2131558951, 2131558950, context);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper
    public final void onPictorialGuideInfoChanged(Context context, PictorialGuideEntity pictorialGuideEntity, PictorialFunctionContainer pictorialFunctionContainer) {
        PictorialGuideView pictorialGuideView;
        KgdLogUtil.i("TbPictorialBottomViewHelper", "onPictorialGuideInfoChanged info = " + pictorialGuideEntity);
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        if (pictorialGuideEntity == null || !pictorialGuideEntity.shouldShow() || pictorialMediator.isMainSwitchOn()) {
            WeakReference weakReference = this.mPictorialGuideViewWeak;
            pictorialGuideView = weakReference != null ? (PictorialGuideView) weakReference.get() : null;
            if (pictorialGuideView != null) {
                pictorialFunctionContainer.removeView(pictorialGuideView);
            }
            KgdLogUtil.i("TbPictorialBottomViewHelper", "delete Guide info ");
            return;
        }
        this.mGuideEntity = pictorialGuideEntity;
        WeakReference weakReference2 = this.mPictorialGuideViewWeak;
        pictorialGuideView = weakReference2 != null ? (PictorialGuideView) weakReference2.get() : null;
        if (pictorialGuideView == null) {
            pictorialGuideView = new PictorialGuideView(context);
            this.mPictorialGuideViewWeak = new WeakReference(pictorialGuideView);
            updatePictorialGuideView(context, pictorialFunctionContainer);
            pictorialFunctionContainer.addView(pictorialGuideView);
        }
        pictorialGuideView.applyChange(pictorialGuideEntity);
        KgdLogUtil.i("TbPictorialBottomViewHelper", "add Guide info " + pictorialGuideEntity);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper
    public final void updatePictorialGuideView(Context context, PictorialFunctionContainer pictorialFunctionContainer) {
        int i;
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        WeakReference weakReference = this.mPictorialGuideViewWeak;
        PictorialGuideView pictorialGuideView = weakReference != null ? (PictorialGuideView) weakReference.get() : null;
        WeakReference weakReference2 = this.mContainerWeak;
        if (weakReference2 == null || weakReference2.get() != pictorialFunctionContainer) {
            this.mContainerWeak = new WeakReference(pictorialFunctionContainer);
        }
        PictorialGuideEntity pictorialGuideEntity = this.mGuideEntity;
        if (pictorialGuideEntity == null || !pictorialGuideEntity.shouldShow() || pictorialGuideView == null || pictorialMediator.isMainSwitchOn()) {
            return;
        }
        Context contextUpdateDisplayConfiguration = KeyguardUtils.Companion.updateDisplayConfiguration(context);
        int baseDisplayWidthSize = ScreenSizeHelper.getInstance(context).getBaseDisplayWidthSize();
        Resources resources = contextUpdateDisplayConfiguration.getResources();
        int dimension = (baseDisplayWidthSize - ((int) resources.getDimension(2131168871))) - ((int) resources.getDimension(2131168870));
        int dimension2 = (int) resources.getDimension(2131168868);
        int dimension3 = (int) resources.getDimension(2131168871);
        int dimension4 = (int) resources.getDimension(2131168871);
        int dimensionPixelSize = resources.getDimensionPixelSize(2131168850);
        if (isFingeprintShow() && dimension2 >= (i = this.mIconLocation - (this.mIconSize >> 1))) {
            dimension2 = i;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimension, dimension2);
        layoutParams.setMargins(dimension3, 0, dimension4, dimensionPixelSize);
        layoutParams.gravity = 81;
        pictorialGuideView.setLayoutParams(layoutParams);
        PictorialFunctionContainer.setMessageViewWidth(pictorialGuideView, dimension, dimension2);
    }
}
