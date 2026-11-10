package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.RmPictorialFactory;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialFactory;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.invoke.VarHandle;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialTipsUtil {
    public static final PictorialTipsUtil INSTANCE = new PictorialTipsUtil();
    private static final String TAG = "PictorialTipsUtil";

    public static final void clickDetail(Context context, PictorialMediator pictorialMediator, KeyguardWallpaperInfo keyguardWallpaperInfo) {
        Intent clickDetailIntent;
        PictorialFactory pictorialFactory = pictorialMediator.getPictorialFactory();
        if (!(pictorialFactory instanceof OplusPictorialFactory) && !(pictorialFactory instanceof RmPictorialFactory)) {
            KgdLogUtil.i(TAG, "clickDetail isOplusPictorial not return");
            return;
        }
        if (!pictorialMediator.isPictorialTipsFeatherSupport() || !pictorialMediator.isSupportPictorialTips()) {
            KgdLogUtil.i(TAG, "clickDetail pictorial tips not allowed, return");
            return;
        }
        PictorialWallpaperInfo pictorialWallpaperInfo = keyguardWallpaperInfo instanceof PictorialWallpaperInfo ? (PictorialWallpaperInfo) keyguardWallpaperInfo : null;
        if (pictorialWallpaperInfo == null || (clickDetailIntent = pictorialWallpaperInfo.getClickDetailIntent()) == null) {
            return;
        }
        INSTANCE.getClass();
        Bundle bundle = new Bundle();
        bundle.putString("wallpaperWebType", pictorialWallpaperInfo.getWallpaperWebType());
        bundle.putString("transparent", pictorialWallpaperInfo.getTransparent());
        bundle.putString("click_action", "more");
        bundle.putString("imageId", pictorialWallpaperInfo.getImageId());
        Object fileUri = pictorialWallpaperInfo.getFileUri();
        if (fileUri == null) {
            fileUri = "";
        }
        bundle.putString("fileUri", fileUri.toString());
        bundle.putString("txtGroupId", pictorialWallpaperInfo.getTxtGroupId());
        int intExtra = clickDetailIntent.getIntExtra("type", -1);
        bundle.putInt("type", intExtra);
        if (intExtra == 3) {
            bundle.putString("deepLink", pictorialWallpaperInfo.getDeepLink());
            bundle.putString("instantApp", pictorialWallpaperInfo.getInstantAppLink());
            bundle.putString("packageNameLink", pictorialWallpaperInfo.getPackageNameLink());
        }
        if (TextUtils.isEmpty(pictorialWallpaperInfo.getWallpaperId())) {
            bundle.putInt("category", 10000);
            bundle.putString("webUrl", pictorialWallpaperInfo.getWebUrl());
        } else {
            bundle.putString("wallpaperId", pictorialWallpaperInfo.getWallpaperId());
            bundle.putInt("category", 10002);
            bundle.putString("webUrl", pictorialWallpaperInfo.getWallpaperWebUrl());
        }
        ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).notifyPictorialFinish();
        clickDetail.1 r2 = new clickDetail.1();
        r2.$pictorialMediator = pictorialMediator;
        r2.$bundle = bundle;
        r2.$currentWallpaperInfo = pictorialWallpaperInfo;
        r2.$clickDetailIntent = clickDetailIntent;
        r2.$context = context;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(r2);
    }

    public static final String getPictorialTips(PictorialMediator pictorialMediator, KeyguardWallpaperInfo keyguardWallpaperInfo, boolean z) {
        PictorialFactory pictorialFactory = pictorialMediator.getPictorialFactory();
        if (!(pictorialFactory instanceof OplusPictorialFactory) && !(pictorialFactory instanceof RmPictorialFactory)) {
            KgdLogUtil.i(TAG, "onWallpaperChange isOplusPictorial not return");
            return null;
        }
        if (keyguardWallpaperInfo instanceof PictorialWallpaperInfo) {
            PictorialWallpaperInfo pictorialWallpaperInfo = (PictorialWallpaperInfo) keyguardWallpaperInfo;
            if (pictorialWallpaperInfo.isPictorialType()) {
                StringBuilder sb = new StringBuilder();
                if (z) {
                    if (!TextUtils.isEmpty(pictorialWallpaperInfo.getTextTitle())) {
                        sb.append(StringsKt__StringsJVMKt.replace(pictorialWallpaperInfo.getTextTitle(), "  |", "", false));
                    } else if (!TextUtils.isEmpty(pictorialWallpaperInfo.getTextContent())) {
                        sb.append(pictorialWallpaperInfo.getTextContent());
                    }
                } else if (!TextUtils.isEmpty(pictorialWallpaperInfo.getWallpaperTitle())) {
                    sb.append(StringsKt__StringsJVMKt.replace(pictorialWallpaperInfo.getWallpaperTitle(), "  |", "", false));
                }
                return sb.toString();
            }
        }
        return null;
    }

    public static final boolean hasMore(KeyguardWallpaperInfo keyguardWallpaperInfo) {
        PictorialWallpaperInfo pictorialWallpaperInfo = keyguardWallpaperInfo instanceof PictorialWallpaperInfo ? (PictorialWallpaperInfo) keyguardWallpaperInfo : null;
        return (pictorialWallpaperInfo != null ? pictorialWallpaperInfo.getClickDetailIntent() : null) != null;
    }
}
