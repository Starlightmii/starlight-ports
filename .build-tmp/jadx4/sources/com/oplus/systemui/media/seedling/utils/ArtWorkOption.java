package com.oplus.systemui.media.seedling.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.core.graphics.ColorUtils;
import androidx.exifinterface.media.ExifInterface$;
import androidx.palette.graphics.Palette;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ArtWorkOption {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ArtWorkOption";
    private static ArtWorkOption instance;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final ArtWorkOption getInstance() {
            ArtWorkOption artWorkOption;
            ArtWorkOption artWorkOption2 = ArtWorkOption.instance;
            if (artWorkOption2 != null) {
                return artWorkOption2;
            }
            synchronized (this) {
                artWorkOption = ArtWorkOption.instance;
                if (artWorkOption == null) {
                    artWorkOption = new ArtWorkOption();
                    ArtWorkOption.instance = artWorkOption;
                }
            }
            return artWorkOption;
        }
    }

    public final String getBgColorFromBitmap(Bitmap bitmap) {
        String str = String.format("#%08X", Arrays.copyOf(new Object[]{Integer.valueOf(ColorUtils.setAlphaComponent(-1, 26))}, 1));
        if (bitmap.isRecycled()) {
            LogUtil.e$default(12, TAG, "getBgColorFromBitmap failed: bitmap recycled", null);
            return str;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            LogUtil.e$default(12, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(width, height, "getBgColorFromBitmap failed: originalWidth=", ", originalHeight="), null);
            return str;
        }
        float fMin = (width > 36 || height > 36) ? Math.min(36.0f / width, 36.0f / height) : 1.0f;
        int i = (int) (width * fMin);
        if (i < 1) {
            i = 1;
        }
        int i2 = (int) (height * fMin);
        if (i2 < 1) {
            i2 = 1;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("getBgColorFromBitmap: scaleFactor=", fMin, "ArtWorkOption-->", "SystemUi--SeedlingPlugin");
        }
        Palette.Swatch swatch = Palette.from(Bitmap.createScaledBitmap(bitmap, i, i2, true)).generate().mDominantSwatch;
        int rgb = swatch != null ? swatch.getRgb() : -1;
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(rgb), Color.green(rgb), Color.blue(rgb), fArr);
        fArr[1] = Math.max(fArr[1], 0.6f);
        fArr[2] = 0.8f;
        int alphaComponent = ColorUtils.setAlphaComponent(Color.HSVToColor(fArr), 26);
        String hexString = Integer.toHexString(rgb);
        String hexString2 = Integer.toHexString(alphaComponent);
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("dominantColor=#", hexString, " bgColor=#", hexString2, " H=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, " S=", f2, " V=");
        sbM.append(f3);
        OplusNotificationLogger.logMediaSeedling(TAG, sbM.toString());
        return String.format("#%08X", Arrays.copyOf(new Object[]{Integer.valueOf(alphaComponent)}, 1));
    }
}
