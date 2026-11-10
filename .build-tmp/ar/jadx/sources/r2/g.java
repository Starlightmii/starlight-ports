package r2;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.util.Objects;
import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class g extends j {
    public static int U(FontStyle fontStyle, FontStyle fontStyle2) {
        return (Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100) + (fontStyle.getSlant() == fontStyle2.getSlant() ? 0 : 2);
    }

    public final FontFamily S(u2.d[] dVarArr, ContentResolver contentResolver) {
        Font fontBuild;
        FontFamily.Builder builder = null;
        for (u2.d dVar : dVarArr) {
            if (Objects.equals(dVar.f5832a.getScheme(), "systemfont")) {
                fontBuild = T(dVar);
            } else {
                try {
                    Uri uri = dVar.f5832a;
                    String str = dVar.f5836e;
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(uri, "r", null);
                    if (parcelFileDescriptorOpenFileDescriptor == null) {
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        }
                        fontBuild = null;
                    } else {
                        try {
                            Font.Builder ttcIndex = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(dVar.f5834c).setSlant(dVar.f5835d ? 1 : 0).setTtcIndex(dVar.f5833b);
                            if (!TextUtils.isEmpty(str)) {
                                ttcIndex.setFontVariationSettings(str);
                            }
                            fontBuild = ttcIndex.build();
                            parcelFileDescriptorOpenFileDescriptor.close();
                        } catch (Throwable th) {
                            try {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                } catch (IOException e5) {
                    Log.w("TypefaceCompatApi29Impl", "Font load failed", e5);
                    fontBuild = null;
                }
            }
            if (fontBuild != null) {
                if (builder == null) {
                    builder = new FontFamily.Builder(fontBuild);
                } else {
                    builder.addFont(fontBuild);
                }
            }
        }
        if (builder == null) {
            return null;
        }
        return builder.build();
    }

    public Font T(u2.d dVar) {
        throw new UnsupportedOperationException("Getting font from Typeface is not supported before API31");
    }

    @Override // p3.j
    public final Typeface s(Context context, u2.d[] dVarArr) {
        try {
            FontFamily fontFamilyS = S(dVarArr, context.getContentResolver());
            if (fontFamilyS == null) {
                return null;
            }
            Typeface.CustomFallbackBuilder customFallbackBuilder = new Typeface.CustomFallbackBuilder(fontFamilyS);
            FontStyle fontStyle = new FontStyle(400, 0);
            Font font = fontFamilyS.getFont(0);
            int iU = U(fontStyle, font.getStyle());
            for (int i5 = 1; i5 < fontFamilyS.getSize(); i5++) {
                Font font2 = fontFamilyS.getFont(i5);
                int iU2 = U(fontStyle, font2.getStyle());
                if (iU2 < iU) {
                    font = font2;
                    iU = iU2;
                }
            }
            return customFallbackBuilder.setStyle(font.getStyle()).build();
        } catch (Exception e5) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e5);
            return null;
        }
    }
}
