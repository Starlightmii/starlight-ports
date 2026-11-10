package com.oplus.systemui.utils;

import android.os.SystemProperties;
import java.util.Locale;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class WallpaperUtilKt$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        return Boolean.valueOf(StringsKt__StringsJVMKt.startsWith(SystemProperties.get("ro.boot.hardware", "oplus").toLowerCase(Locale.getDefault()), "mt", false));
    }
}
