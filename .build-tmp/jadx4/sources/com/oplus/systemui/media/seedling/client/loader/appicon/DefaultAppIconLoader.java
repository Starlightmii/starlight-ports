package com.oplus.systemui.media.seedling.client.loader.appicon;

import android.content.Context;
import android.net.Uri;
import com.oplus.systemui.media.seedling.utils.FileUtil;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DefaultAppIconLoader implements IAppIconLoader {
    private final Context context;

    public DefaultAppIconLoader(Context context) {
        this.context = context;
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.appicon.IAppIconLoader
    public Uri load(SeedlingMediaData seedlingMediaData) {
        return FileUtil.loadAppIconToUri(this.context, seedlingMediaData);
    }

    @Override // com.oplus.systemui.media.seedling.client.loader.appicon.IAppIconLoader
    public boolean update(Uri uri, SeedlingMediaData seedlingMediaData) {
        if (Intrinsics.areEqual(seedlingMediaData.getAppIconUri(), uri)) {
            return false;
        }
        seedlingMediaData.setAppIconUri(uri);
        return true;
    }
}
