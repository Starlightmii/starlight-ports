package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialWallpaperStatistic$mExposureStartRunnable$1 implements PictorialWallpaperStatistic.StartRunnable {
    public boolean isPanoramicAod;
    public KeyguardWallpaperInfo keyguardWallpaperInfo;

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperStatistic.StartRunnable
    public KeyguardWallpaperInfo getKeyguardWallpaperInfo() {
        return this.keyguardWallpaperInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        KeyguardWallpaperInfo keyguardWallpaperInfo = getKeyguardWallpaperInfo();
        if (keyguardWallpaperInfo == null || !keyguardWallpaperInfo.isPictorialType()) {
            return;
        }
        PictorialWallpaperStatistic.mHasStatisticExposeStart = true;
        PictorialWallpaperStatistic pictorialWallpaperStatistic = PictorialWallpaperStatistic.INSTANCE;
        PictorialWallpaperInfo pictorialWallpaperInfo = (PictorialWallpaperInfo) getKeyguardWallpaperInfo();
        boolean z = this.isPanoramicAod;
        pictorialWallpaperStatistic.getClass();
        if (pictorialWallpaperInfo == null || PictorialWallpaperStatistic.mExposureStart) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("PictorialWallpaperStatistic-->", "SystemUi--Keyguard", ExifInterface$.ExternalSyntheticOutline1.m("exposureStart : imageInfo = ", pictorialWallpaperInfo.getTextTitle(), ", ", pictorialWallpaperInfo.getImageId(), ", isPanoramicAod = "), z);
        }
        PictorialWallpaperStatistic.mExposureStart = true;
        Bundle bundle = new Bundle();
        bundle.putInt("category", 10000);
        bundle.putString("eventname", "show");
        bundle.putString("imageId", pictorialWallpaperInfo.getImageId());
        if (pictorialWallpaperInfo.getGroupId() != null) {
            bundle.putString("groupId", pictorialWallpaperInfo.getGroupId());
        }
        bundle.putInt("groupType", pictorialWallpaperInfo.getGroupType());
        bundle.putInt("case", 1);
        if (z) {
            bundle.putString("mode", "p_aod");
        }
        bundle.putInt("if_cover", ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mOccluded ? 1 : 0);
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        bundle.putInt("button_text", (pictorialMediator.isPictorialTipsFeatherSupport() && pictorialMediator.isSupportPictorialTips()) ? 1 : 0);
        int clockTop = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController().getClockTop();
        int keyguardStyleClockHeight = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController().getKeyguardStyleClockHeight();
        int intrinsicContentHeight = ((NotificationStackScrollLayoutController) Dependency.sDependency.getDependencyInner(NotificationStackScrollLayoutController.class)).getIntrinsicContentHeight();
        bundle.putIntArray("obstructions_height_px", new int[]{clockTop, keyguardStyleClockHeight, intrinsicContentHeight > 1 ? intrinsicContentHeight : 0});
        ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).reportStatisticData(bundle);
        PictorialMediator pictorialMediator2 = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        if (!pictorialMediator2.isLHStyle() || pictorialWallpaperInfo.getSourceName() == null || TextUtils.isEmpty(pictorialWallpaperInfo.getSourceName().trim())) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("category", 10000);
        bundle2.putString("eventname", "description_show");
        bundle2.putString("imageId", pictorialWallpaperInfo.getImageId());
        bundle2.putString("groupId", pictorialWallpaperInfo.getGroupId());
        bundle2.putInt("groupType", pictorialWallpaperInfo.getGroupType());
        bundle2.putInt("case", 1);
        pictorialMediator2.reportStatisticData(bundle2);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperStatistic.StartRunnable
    public void setKeyguardWallpaperInfo(KeyguardWallpaperInfo keyguardWallpaperInfo) {
        this.keyguardWallpaperInfo = keyguardWallpaperInfo;
    }

    public final void setPanoramicAod(boolean z) {
        this.isPanoramicAod = z;
    }
}
