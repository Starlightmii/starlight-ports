package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DependencyEx;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialPreloadView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.AodFeatureOption;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RmPictorialLoader extends PictorialLoader {
    public static final List CAN_SHOW_DIALOG_AC = List.of("com.oplus.wallpapers", "com.coloros.gallery3d", "com.oplus.themestore", "com.android.launcher");
    public final List mPreloadChangeListeners = new ArrayList();

    public static void saveImageToFileAsJPEG(Bitmap bitmap, File file) {
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            KgdLogUtil.i("RmPictorialLoader", "Fail to create directory for target file");
            return;
        }
        try {
            saveImageToStream(bitmap, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            KgdLogUtil.i("RmPictorialLoader", "Fail to save image, " + e.getMessage());
        }
    }

    public static void saveImageToStream(Bitmap bitmap, FileOutputStream fileOutputStream) {
        StringBuilder sb;
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        try {
            byte[] bArr = new byte[OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING];
            while (true) {
                int i = byteArrayInputStream.read(bArr, 0, OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING);
                if (i == -1) {
                    try {
                        fileOutputStream.close();
                        return;
                    } catch (IOException e) {
                        e = e;
                        sb = new StringBuilder("saveByteArrayInputStream fos e ");
                        sb.append(e);
                        String string = sb.toString();
                    }
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (IOException e2) {
            KgdLogUtil.i("RmPictorialLoader", "saveByteArrayInputStream e " + e2);
            try {
                fileOutputStream.close();
            } catch (IOException e3) {
                e = e3;
                sb = new StringBuilder("saveByteArrayInputStream fos e ");
                sb.append(e);
                String string2 = sb.toString();
            }
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e4) {
                KgdLogUtil.i("RmPictorialLoader", "saveByteArrayInputStream fos e " + e4);
            }
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void releasePreloadCache() {
        if (this.mPreloadChangeListeners.isEmpty()) {
            return;
        }
        ((ArrayList) this.mPreloadChangeListeners).forEach(new RmPictorialLoader$$ExternalSyntheticLambda0());
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void removePreloadWallpaperChangeListener(PictorialPreloadView pictorialPreloadView) {
        this.mPreloadChangeListeners.remove(pictorialPreloadView);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void setPreloadWallpaperChangeListener(PictorialWallpaperHelper.IPictorialPreloadListener iPictorialPreloadListener) {
        if (this.mPreloadChangeListeners.contains(iPictorialPreloadListener)) {
            return;
        }
        this.mPreloadChangeListeners.add(iPictorialPreloadListener);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void showWallpaperAddRotaDialog(Context context, Drawable drawable) {
        if (context == null || drawable == null || KeyguardUpdateMonitor.getCurrentUser() != 0 || !KeyguardUtils.isDefaultKeyguardInSettings(context) || CommonSettingsValueProxy.getSuperPowerSaveState(context) == 1) {
            return;
        }
        AodFeatureOption.INSTANCE.getClass();
        if (AodFeatureOption.isSupportAod() && AodData.getInstance(context).mAodOptionsMgr.isCurrentAodSwitchEnable()) {
            KgdLogUtil.i("RmPictorialLoader", "showWallpaperAddRotaDialog return because isAod Enable");
            return;
        }
        KgdLogUtil.i("RmPictorialLoader", "showWallpaperAddRotaDialog wallpaper is change");
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        if (!pictorialMediator.mPictorialExist || !pictorialMediator.isDeviceProvisioned()) {
            KgdLogUtil.d("RmPictorialLoader", "showWallpaperAddRotaDialog return because  is not PictorialExist or is not DeviceProvisioned");
            return;
        }
        String pictorialAppPackage = pictorialMediator.getPictorialAppData().getPictorialAppPackage();
        ComponentName componentName = ActivityManagerWrapper.getInstance().getRunningTask() != null ? ActivityManagerWrapper.getInstance().getRunningTask().topActivity : null;
        if (componentName == null) {
            KgdLogUtil.d("RmPictorialLoader", "showWallpaperAddRotaDialog return because topActivity is null");
            return;
        }
        if (!CAN_SHOW_DIALOG_AC.contains(componentName.getPackageName())) {
            KgdLogUtil.d("RmPictorialLoader", "showWallpaperAddRotaDialog return because not contains show dialog ac");
            return;
        }
        if (componentName.getPackageName().equals(pictorialAppPackage)) {
            KgdLogUtil.d("RmPictorialLoader", "showWallpaperAddRotaDialog return because isPictorialAc");
            return;
        }
        if (componentName.getClassName().equals("com.android.launcher.settings.LauncherSettingsActivity")) {
            KgdLogUtil.d("RmPictorialLoader", "showWallpaperAddRotaDialog return because isLauncherSettings");
            return;
        }
        boolean zEquals = componentName.getPackageName().equals("com.android.launcher");
        try {
            RmPictorialLoader$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new RmPictorialLoader$.ExternalSyntheticLambda1();
            externalSyntheticLambda1.f$0 = this;
            externalSyntheticLambda1.f$1 = zEquals;
            externalSyntheticLambda1.f$2 = context;
            externalSyntheticLambda1.f$3 = drawable;
            externalSyntheticLambda1.f$4 = pictorialAppPackage;
            externalSyntheticLambda1.f$5 = pictorialMediator;
            VarHandle.storeStoreFence();
            PictorialUtils.runOnWorkThread(externalSyntheticLambda1);
        } catch (Exception e) {
            KgdLogUtil.i("RmPictorialLoader", "showWallpaperAddRotaDialog Exception:" + e.getMessage());
        }
    }
}
