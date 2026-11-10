package com.oplus.systemui.media.seedling.client.loader.album.artwrok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import com.oplus.systemui.media.seedling.utils.FeatureOption;
import com.oplus.systemui.media.seedling.utils.FileUtil;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.Triple;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UriArtworkLoader implements IArtworkLoader {
    public static final Companion Companion = new Companion();
    private static final String TAG = "UriArtworkLoader";
    private Bitmap albumBitmap;
    public int artworkCount;
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public UriArtworkLoader(Context context, int i) {
        this.context = context;
        this.artworkCount = i;
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.album.artwrok.IArtworkLoader
    public int getArtworkCount() {
        return this.artworkCount;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.album.artwrok.IArtworkLoader
    public Triple<Uri, Bitmap, Boolean> load(String str, String str2, SeedlingMediaData seedlingMediaData) {
        Icon artworkIcon = seedlingMediaData.getArtworkIcon();
        Bitmap bitmapFromIcon = artworkIcon != null ? FileUtil.getBitmapFromIcon(artworkIcon) : null;
        if (bitmapFromIcon == null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "get artworkBitmap failed; packageName: " + str + " artworkBitmap is null");
            this.albumBitmap = null;
            return new Triple<>((Object) null, (Object) null, Boolean.TRUE);
        }
        if (bitmapFromIcon.sameAs(this.albumBitmap)) {
            OplusNotificationLogger.logMediaSeedling(TAG, "load artworkBitmap == albumBitmapMap[pkgName], don't update data");
            return new Triple<>((Object) null, (Object) null, Boolean.FALSE);
        }
        this.albumBitmap = bitmapFromIcon;
        int artworkCount = getArtworkCount();
        setArtworkCount(artworkCount + 1);
        String str3 = str2 + "_" + (artworkCount % 8) + "_artwork.png";
        FeatureOption.INSTANCE.getClass();
        Uri uriConvertBitmapToUri = FeatureOption.isMediaUMSEnabled ? FileUtil.convertBitmapToUri(this.context, bitmapFromIcon, str3) : null;
        OplusNotificationLogger.logMediaSeedling(TAG, "get artworkBitmap success: " + str3 + "; packageName: " + str);
        return new Triple<>(uriConvertBitmapToUri, bitmapFromIcon, Boolean.TRUE);
    }

    public final void onDestroy() {
        this.albumBitmap = null;
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.album.artwrok.IArtworkLoader
    public void setArtworkCount(int i) {
        this.artworkCount = i;
    }
}
