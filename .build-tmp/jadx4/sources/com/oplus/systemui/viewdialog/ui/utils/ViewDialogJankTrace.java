package com.oplus.systemui.viewdialog.ui.utils;

import android.content.Context;
import android.os.Trace;
import androidx.compose.runtime.OpaqueKey$;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.qs.media.OplusQsMediaDialogController;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogJankTrace {
    public static final ViewDialogJankTrace INSTANCE = new ViewDialogJankTrace();
    private static final Map<String, Pair<String, Integer>> dialogTraceParams = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(OplusQSHighlightTileViewLabelTextHandler.BLUETOOTH, new Pair("Click To Bluetooth Second Page", 91)), TuplesKt.to(OplusQSHighlightTileViewLabelTextHandler.WIFI, new Pair("Click To Wlan Second Page", 90)), TuplesKt.to(OplusQsMediaDialogController.TAG, new Pair("MUSIC_PANEL_OPEN", 94))});
    private static final Map<String, Pair<Integer, Integer>> dialogLatencyParams = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(OplusQSHighlightTileViewLabelTextHandler.BLUETOOTH, new Pair(433, 434)), TuplesKt.to(OplusQSHighlightTileViewLabelTextHandler.WIFI, new Pair(331, 432)), TuplesKt.to(OplusQsMediaDialogController.TAG, new Pair(335, 436))});

    public final void onDialogAnimationEnd(Context context, String str) {
        Pair<String, Integer> pair = dialogTraceParams.get(str);
        if (pair == null) {
            return;
        }
        Trace.asyncTraceEnd(8L, OpaqueKey$.ExternalSyntheticOutline0.m(pair.getFirst(), "showDetail_"), ((Number) pair.getSecond()).intValue());
        JankUtils.gfxSceneEnd(context, new int[]{((Number) pair.getSecond()).intValue()});
        Pair<Integer, Integer> pair2 = dialogLatencyParams.get(str);
        if (pair2 == null) {
            return;
        }
        JankUtils.latencySceneEnd(context, ((Number) pair2.getSecond()).intValue());
    }

    public final void onDialogAnimationStart(Context context, String str) {
        Pair<String, Integer> pair = dialogTraceParams.get(str);
        if (pair == null) {
            return;
        }
        JankUtils.gfxSceneBegin$default(context, ((Number) pair.getSecond()).intValue(), (String) pair.getFirst(), 4L);
        Pair<Integer, Integer> pair2 = dialogLatencyParams.get(str);
        if (pair2 == null) {
            return;
        }
        JankUtils.latencySceneEnd(context, ((Number) pair2.getFirst()).intValue());
    }

    public final void onDialogStartShow(String str) {
        Pair<String, Integer> pair = dialogTraceParams.get(str);
        if (pair == null) {
            return;
        }
        Trace.asyncTraceBegin(8L, OpaqueKey$.ExternalSyntheticOutline0.m(pair.getFirst(), "showDetail_"), ((Number) pair.getSecond()).intValue());
    }
}
