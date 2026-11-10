package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.webkit.URLUtil;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.SecurityController;
import com.oplus.dispatch.DispatchQueue;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplus.dispatch.QueueManager;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.customize.KeyguardCustomizeManager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PictorialUtils {
    public static volatile boolean sIsPictorialSlidePagerStarting;
    public static final OCDHandler sPictorialHandler;
    public static StringBuilder sStringBuilder;

    static {
        OCDMessageQueue oCDMessageQueueMessageQueueCreate = QueueManager.messageQueueCreate("pictorial_queue", QueueAttr.QUEUE_SERIAL_TIME, (DispatchQueue) null);
        sIsPictorialSlidePagerStarting = false;
        sPictorialHandler = new OCDHandler(oCDMessageQueueMessageQueueCreate);
    }

    public static boolean checkPictorialDetailsIntent(Context context, Intent intent) {
        if (context == null) {
            LogUtil.w(LogUtil.TAG_KEYGUARD, "PictorialUtils", "checkPictorialDetailsIntent context is null, return!");
            return false;
        }
        String dataString = intent.getDataString();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            if (URLUtil.isNetworkUrl(dataString)) {
                intent.setAction(((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getActionSeePictorialDetails());
                if (context.getPackageManager().resolveActivity(intent, OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS) != null) {
                    return true;
                }
                intent.setAction("android.intent.action.VIEW");
                intent.putExtra("privateData", "OplusOs" + dataString);
                intent.setPackage(getBrowserPackageName());
            } else {
                if (TextUtils.isEmpty(dataString)) {
                    KgdLogUtil.d("PictorialUtils", "checkPictorialDetailsIntent. The data is null!");
                    return false;
                }
                intent.addCategory("android.intent.category.BROWSABLE");
            }
        } else if (!TextUtils.isEmpty(dataString)) {
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
        }
        return !context.getPackageManager().queryIntentActivities(intent, OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS).isEmpty();
    }

    public static Bitmap createCenterCropBitmapIfNeed(Bitmap bitmap, int i, int i2, Bitmap.Config config) {
        if (bitmap == null || bitmap.isRecycled()) {
            KgdLogUtil.w("PictorialUtils", "source is null or recycled");
            return bitmap;
        }
        if (i <= 0 || i2 <= 0) {
            KgdLogUtil.w("PictorialUtils", "target width or height can not expected");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i && height == i2) {
            KgdLogUtil.d("PictorialUtils", "same width and height");
            return bitmap;
        }
        float f = i;
        float f2 = width;
        float f3 = i2;
        float f4 = height;
        float fMax = Math.max(f / f2, f3 / f4);
        int i3 = ((int) (f - (f2 * fMax))) / 2;
        int i4 = ((int) (f3 - (f4 * fMax))) / 2;
        Matrix matrix = new Matrix();
        matrix.setScale(fMax, fMax);
        matrix.postTranslate(i3, i4);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, config);
        new Canvas(bitmapCreateBitmap).drawBitmap(bitmap, matrix, null);
        StringBuilder sb = new StringBuilder("center crop bitmap: matrix=");
        sb.append(matrix);
        sb.append(" sw=");
        sb.append(width);
        sb.append(" sh=");
        RectList$.ExternalSyntheticOutline0.m(height, i, " tw=", " th=", sb);
        sb.append(i2);
        KgdLogUtil.d("PictorialUtils", sb.toString());
        return bitmapCreateBitmap;
    }

    public static Bitmap createCenterCropBitmapIfNeedForPhone(Context context, Bitmap bitmap, Bitmap.Config config) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isLargeScreenFolding() || FeatureOption.isTablet()) {
            return bitmap;
        }
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        int realScreenWidth = companion.getRealScreenWidth();
        int realScreenHeight = companion.getRealScreenHeight();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getRealSize(point);
            realScreenWidth = Math.min(point.x, point.y);
            realScreenHeight = Math.max(point.x, point.y);
            KgdLogUtil.d("PictorialUtils", "createCenterCropBitmap sW=" + realScreenWidth + " sH=" + realScreenHeight);
        }
        return createCenterCropBitmapIfNeed(bitmap, realScreenWidth, realScreenHeight, config);
    }

    public static int dp2px(Context context, int i) {
        return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void fitPictorialDetailsIntent(Context context, PictorialWallpaperInfo pictorialWallpaperInfo) {
        String deepLink = pictorialWallpaperInfo.getDeepLink();
        if (!TextUtils.isEmpty(deepLink) && !"null".equals(deepLink)) {
            Intent intent = new Intent();
            intent.setData(Uri.parse(deepLink));
            intent.putExtra("type", 3);
            if (checkPictorialDetailsIntent(context, intent)) {
                pictorialWallpaperInfo.setClickDetailIntent(intent);
                return;
            }
            KgdLogUtil.d("PictorialUtils", "canot get detail intent from deeplink !");
        }
        String webUrl = pictorialWallpaperInfo.getWebUrl();
        if (TextUtils.isEmpty(webUrl) || "null".equals(webUrl)) {
            return;
        }
        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse(webUrl));
        intent2.putExtra("webUrl", webUrl);
        intent2.putExtra("imageId", pictorialWallpaperInfo.getImageId());
        intent2.putExtra("type", 1);
        if (pictorialWallpaperInfo.getFileUri() != null) {
            intent2.putExtra("fileUri", pictorialWallpaperInfo.getFileUri().toSafeString());
        }
        if (checkPictorialDetailsIntent(context, intent2)) {
            pictorialWallpaperInfo.setClickDetailIntent(intent2);
        } else {
            KgdLogUtil.d("PictorialUtils", "canot get detail intent from webUrl !");
        }
    }

    public static String getBrowserPackageName() {
        return KeyguardCustomizeManager.Companion.getInstance().mNeedUseChromePackage ? "com.android.chrome" : "com.heytap.browser";
    }

    public static Handler getHandler() {
        return sPictorialHandler;
    }

    public static boolean hasWorkProfile() {
        return ((SecurityController) Dependency.sDependency.getDependencyInner(SecurityController.class)).mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile() || ((SecurityController) Dependency.sDependency.getDependencyInner(SecurityController.class)).mDevicePolicyManager.isDeviceManaged();
    }

    public static boolean isFestivalWallpaperEnabled(Context context) {
        Boolean boolValueOf = Boolean.FALSE;
        if (context == null || context.getContentResolver() == null) {
            return false;
        }
        try {
            Bundle bundleCall = context.getContentResolver().call(PictorialAppData.URI_WALLPAPER_ENTRANCE_SHOW, "method_festival_wallpaper_enabled", (String) null, (Bundle) null);
            if (bundleCall != null) {
                boolValueOf = Boolean.valueOf(bundleCall.getBoolean("is_festival_wallpaper_enabled"));
            }
        } catch (Exception unused) {
            KgdLogUtil.e("PictorialUtils", "isFestivalWallpaperEnabled error");
        }
        KgdLogUtil.i("PictorialUtils", "[isFestivalWallpaperEnabled] isEnabled=" + boolValueOf);
        return boolValueOf.booleanValue();
    }

    public static boolean isSameImage(PictorialWallpaperInfo pictorialWallpaperInfo, PictorialWallpaperInfo pictorialWallpaperInfo2) {
        if (pictorialWallpaperInfo != null && pictorialWallpaperInfo2 != null) {
            String groupId = pictorialWallpaperInfo.getGroupId();
            String imageId = pictorialWallpaperInfo.getImageId();
            String textTitle = pictorialWallpaperInfo.getTextTitle();
            String textContent = pictorialWallpaperInfo.getTextContent();
            String groupId2 = pictorialWallpaperInfo2.getGroupId();
            String imageId2 = pictorialWallpaperInfo2.getImageId();
            String textTitle2 = pictorialWallpaperInfo2.getTextTitle();
            String textContent2 = pictorialWallpaperInfo.getTextContent();
            if (groupId == null || groupId2 == null || !groupId.equals(groupId2) || imageId == null || imageId2 == null || !imageId.equals(imageId2) || pictorialWallpaperInfo.getContentSize() != pictorialWallpaperInfo2.getContentSize()) {
                return false;
            }
            boolean z = (textTitle != null && textTitle.equals(textTitle2)) || (textTitle == null && textTitle2 == null);
            boolean z2 = (textContent != null && textContent.equals(textContent2)) || (textContent == null && textContent2 == null);
            KgdLogUtil.d("PictorialUtils", "isSameImage isTitleEqual=" + z + ",isContentEqual=" + z2);
            if (z && z2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSameWallpaperInfo(KeyguardWallpaperInfo keyguardWallpaperInfo, KeyguardWallpaperInfo keyguardWallpaperInfo2) {
        if (keyguardWallpaperInfo == null || keyguardWallpaperInfo2 == null || keyguardWallpaperInfo.getMType() != keyguardWallpaperInfo2.getMType()) {
            return false;
        }
        if (keyguardWallpaperInfo.isPictorialType() && keyguardWallpaperInfo2.isPictorialType()) {
            return isSameImage((PictorialWallpaperInfo) keyguardWallpaperInfo, (PictorialWallpaperInfo) keyguardWallpaperInfo2);
        }
        return true;
    }

    public static boolean isWidgetTaglineSupport(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty("com.oplus.pictorial.is_support_space_tagline") && !TextUtils.isEmpty(str) && !TextUtils.isEmpty("com.oplus.pictorial.is_support_space_tagline")) {
            try {
                Bundle bundle = context.getPackageManager().getApplicationInfo(str, PackageManager.ApplicationInfoFlags.of(128L)).metaData;
                if (bundle == null) {
                    KgdLogUtil.w("PictorialUtils", "isAppHasTargetMetaData: metaData is null for apk:" + str);
                    return false;
                }
                boolean z = bundle.getBoolean("com.oplus.pictorial.is_support_space_tagline", false);
                KgdLogUtil.w("PictorialUtils", "isAppHasTargetMetaData :" + z + ",metaData: com.oplus.pictorial.is_support_space_tagline");
                return z;
            } catch (Exception e) {
                KgdLogUtil.w("PictorialUtils", "isAppHasTargetMetaData ERROR:" + e + "  apk:" + str);
            }
        }
        return false;
    }

    public static void runOnWorkThread(Runnable runnable) {
        OCDHandler oCDHandler = sPictorialHandler;
        if (KeyguardThreadUtil.canRunOnCurrentThread(oCDHandler)) {
            runnable.run();
        } else {
            oCDHandler.post(runnable);
        }
    }

    public static void seePictorialDetails(Context context, Intent intent, ActivityStarter activityStarter) {
        if (context == null || activityStarter == null) {
            return;
        }
        intent.addFlags(268435456);
        if (!((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getActionSeePictorialDetails().equals(intent.getAction())) {
            activityStarter.startActivity(intent, true);
            return;
        }
        try {
            context.startActivityAsUser(intent, ActivityOptions.makeCustomAnimation(context, 0, 0).toBundle(), new UserHandle(-2));
        } catch (ActivityNotFoundException unused) {
            LogUtil.w(LogUtil.TAG_KEYGUARD, "PictorialUtils", "seePictorialDetails. Fail to start pictorial details activity! intent = " + intent);
        }
    }

    public static void setIsPictorialSlidePagerStarting(boolean z) {
        sIsPictorialSlidePagerStarting = z;
    }
}
