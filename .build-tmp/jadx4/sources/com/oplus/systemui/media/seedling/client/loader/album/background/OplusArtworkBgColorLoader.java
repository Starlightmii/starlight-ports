package com.oplus.systemui.media.seedling.client.loader.album.background;

import android.graphics.Bitmap;
import android.net.Uri;
import com.oplus.systemui.media.seedling.utils.ArtWorkOption;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusArtworkBgColorLoader implements IArtworkBgColorLoader {
    @Override // com.oplus.systemui.media.seedling.client.loader.album.background.IArtworkBgColorLoader
    public String load(String str, SeedlingMediaData seedlingMediaData, Uri uri, Bitmap bitmap) {
        return ArtWorkOption.Companion.getInstance().getBgColorFromBitmap(bitmap);
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.album.background.IArtworkBgColorLoader
    public boolean update(Uri uri, String str, boolean z, SeedlingMediaData seedlingMediaData) {
        seedlingMediaData.setArtworkUri(uri);
        if (str != null) {
            seedlingMediaData.setArtworkBgColor(str);
        }
        seedlingMediaData.setArtworkUriContentChanged(z);
        return true;
    }
}
