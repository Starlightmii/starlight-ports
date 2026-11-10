package com.oplus.systemui.scenario.host.impl.ui.composable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LocalViewsMapKt {
    private static final ProvidableCompositionLocal<ConcurrentHashMap<String, View>> LocalViewsMap = CompositionLocalKt.staticCompositionLocalOf(new LocalViewsMapKt$$ExternalSyntheticLambda0());
    private static final String TAG = "LocalViewsMap";

    /* JADX INFO: renamed from: com.oplus.systemui.scenario.host.impl.ui.composable.LocalViewsMapKt$getOrCreateView$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Function {
        public /* synthetic */ Context $context;
        public /* synthetic */ Function1 $createView;
        public /* synthetic */ String $viewKey;

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            String str = this.$viewKey;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("getOrCreateView: createView for ", str, "LocalViewsMap-->", "SystemUi--QuickSettings");
            }
            return (View) this.$createView.invoke(this.$context);
        }
    }

    public static final ProvidableCompositionLocal<ConcurrentHashMap<String, View>> getLocalViewsMap() {
        return LocalViewsMap;
    }

    public static final <V extends View> V getOrCreateView(ConcurrentHashMap<String, View> concurrentHashMap, Context context, String str, Function1 function1) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$createView = function1;
        anonymousClass1.$context = context;
        anonymousClass1.$viewKey = str;
        VarHandle.storeStoreFence();
        V v = (V) concurrentHashMap.computeIfAbsent(str, anonymousClass1);
        ViewParent parent = v.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(v);
        }
        return v;
    }
}
