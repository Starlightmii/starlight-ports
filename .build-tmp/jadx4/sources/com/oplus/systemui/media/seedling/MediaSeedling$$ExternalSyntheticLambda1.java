package com.oplus.systemui.media.seedling;

import android.os.Bundle;
import android.os.Message;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class MediaSeedling$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ MediaSeedling f$0;

    public /* synthetic */ MediaSeedling$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        MediaSeedling mediaSeedling = this.f$0;
        switch (i) {
            case 0:
                mediaSeedling.handleEvent(((Message) obj).getData());
                return Unit.INSTANCE;
            case 1:
                Bundle bundle = (Bundle) obj;
                if (bundle != null) {
                    long j = bundle.getLong("option_data", -1L);
                    if (j < 0) {
                        j = bundle.getInt("option_data", -1);
                    }
                    if (j >= 0) {
                        bundle.putLong("MediaActionSeek", j * 1000);
                    }
                }
                mediaSeedling.handleEvent(bundle);
                return Unit.INSTANCE;
            default:
                return MediaSeedling.m20$r8$lambda$W8jYs13AcgP0mKiC55mWUU4788(mediaSeedling, (Bundle) obj);
        }
    }
}
