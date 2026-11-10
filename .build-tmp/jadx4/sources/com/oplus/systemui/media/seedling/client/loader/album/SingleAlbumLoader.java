package com.oplus.systemui.media.seedling.client.loader.album;

import android.graphics.Bitmap;
import com.oplus.systemui.media.seedling.client.loader.album.artwrok.IArtworkLoader;
import com.oplus.systemui.media.seedling.client.loader.album.artwrok.UriArtworkLoader;
import com.oplus.systemui.media.seedling.client.loader.album.background.IArtworkBgColorLoader;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.Triple;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleAlbumLoader implements IAlbumLoader {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SingleAlbumLoader";
    private final IArtworkBgColorLoader artworkBackgroundLoader;
    private final IArtworkLoader artworkLoader;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SingleAlbumLoader(IArtworkLoader iArtworkLoader, IArtworkBgColorLoader iArtworkBgColorLoader) {
        this.artworkLoader = iArtworkLoader;
        this.artworkBackgroundLoader = iArtworkBgColorLoader;
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.album.IAlbumLoader
    public Triple<Object, Object, Boolean> load(String str, String str2, SeedlingMediaData seedlingMediaData) {
        Triple<Object, Bitmap, Boolean> tripleLoad = this.artworkLoader.load(str, str2, seedlingMediaData);
        Object objComponent1 = tripleLoad.component1();
        Bitmap bitmap = (Bitmap) tripleLoad.component2();
        if (((Boolean) tripleLoad.component3()).booleanValue()) {
            return bitmap == null ? new Triple<>((Object) null, (Object) null, Boolean.TRUE) : new Triple<>(objComponent1, this.artworkBackgroundLoader.load(str, seedlingMediaData, objComponent1, bitmap), Boolean.TRUE);
        }
        return new Triple<>(objComponent1, (Object) null, Boolean.FALSE);
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.album.IAlbumLoader
    public final void onDestroy() {
        ((UriArtworkLoader) this.artworkLoader).onDestroy();
        this.artworkBackgroundLoader.getClass();
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.album.IAlbumLoader
    public boolean update(Object obj, Object obj2, boolean z, SeedlingMediaData seedlingMediaData) {
        OplusNotificationLogger.logMediaSeedling(TAG, "update artwork " + obj + " artworkBackground " + obj2 + " artworkChanged " + z);
        return this.artworkBackgroundLoader.update(obj, obj2, z, seedlingMediaData);
    }
}
