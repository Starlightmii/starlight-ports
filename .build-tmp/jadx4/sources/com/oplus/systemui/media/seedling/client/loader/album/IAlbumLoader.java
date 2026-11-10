package com.oplus.systemui.media.seedling.client.loader.album;

import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import kotlin.Triple;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IAlbumLoader {
    public static final Companion Companion = Companion.$$INSTANCE;

    Triple<Object, Object, Boolean> load(String str, String str2, SeedlingMediaData seedlingMediaData);

    void onDestroy();

    boolean update(Object obj, Object obj2, boolean z, SeedlingMediaData seedlingMediaData);
}
