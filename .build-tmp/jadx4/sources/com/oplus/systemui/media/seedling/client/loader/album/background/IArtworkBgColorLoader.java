package com.oplus.systemui.media.seedling.client.loader.album.background;

import android.graphics.Bitmap;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IArtworkBgColorLoader {
    Object load(String str, SeedlingMediaData seedlingMediaData, Object obj, Bitmap bitmap);

    boolean update(Object obj, Object obj2, boolean z, SeedlingMediaData seedlingMediaData);
}
