package com.oplus.systemui.media.seedling.client.loader.album.artwrok;

import android.graphics.Bitmap;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import kotlin.Triple;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IArtworkLoader {
    int getArtworkCount();

    Triple<Object, Bitmap, Boolean> load(String str, String str2, SeedlingMediaData seedlingMediaData);

    void setArtworkCount(int i);
}
