package com.oplus.sysuiinlineview.host;

import android.view.View;
import androidx.core.view.ViewKt$;
import com.oplus.sysuiinlineview.host.controller.SysUiInlineHostViewController;
import com.oplus.sysuiinlineview.host.view.SysUiInlineHostView;
import java.util.WeakHashMap;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiInlineViewHost$buildHostView$1$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ SysUiInlineHostViewController $controller;
    public /* synthetic */ SysUiInlineViewHost this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.this$0.mHostViews.put(view instanceof SysUiInlineHostView ? (SysUiInlineHostView) view : null, this.$controller);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        WeakHashMap weakHashMap = this.this$0.mHostViews;
        if (weakHashMap != null) {
            TypeIntrinsics.asMutableMap(weakHashMap).remove(view);
        } else {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
        }
    }
}
