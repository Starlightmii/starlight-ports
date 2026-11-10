package com.oplus.systemui.volume.utils;

import android.content.Context;
import com.android.systemui.util.JankUtils;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeAnimationJankTrace {
    public static final OplusVolumeAnimationJankTrace INSTANCE = new OplusVolumeAnimationJankTrace();
    private static final Map<Integer, String> sceneDesMap = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(301, "Volume Bar Showing"), TuplesKt.to(302, "Volume Panel Showing")});

    public static final void beginScene(Context context, int i) {
        String str = sceneDesMap.get(Integer.valueOf(i));
        if (str == null) {
            return;
        }
        JankUtils.gfxSceneBegin$default(context, i, str, 4L);
    }

    public static final void endScene(Context context, int i) {
        JankUtils.gfxSceneEnd(context, new int[]{i});
    }
}
