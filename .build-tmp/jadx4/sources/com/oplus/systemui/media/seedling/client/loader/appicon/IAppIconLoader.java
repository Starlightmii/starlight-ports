package com.oplus.systemui.media.seedling.client.loader.appicon;

import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IAppIconLoader {
    Object load(SeedlingMediaData seedlingMediaData);

    boolean update(Object obj, SeedlingMediaData seedlingMediaData);
}
