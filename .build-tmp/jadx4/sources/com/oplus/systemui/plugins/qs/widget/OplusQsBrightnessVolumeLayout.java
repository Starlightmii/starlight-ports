package com.oplus.systemui.plugins.qs.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessVolumeLayout extends FrameLayout implements ITransitionDesc {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsBrightnessVolumeLayout";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ OplusQsBrightnessVolumeLayout(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        View viewFindViewById;
        if ((Intrinsics.areEqual(str, "drag_transition") || Intrinsics.areEqual(str, "parent_translation_transition")) && (viewFindViewById = findViewById(2131365964)) != null) {
            list.add(new Pair<>(viewFindViewById, FadeTransition.class));
        }
        return list;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSetPressed(boolean z) {
    }

    @Override // android.view.View
    public void setLayerType(int i, Paint paint) {
        View viewFindViewById = findViewById(2131365964);
        if (viewFindViewById != null) {
            viewFindViewById.setLayerType(i, null);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OplusQsBrightnessVolumeLayout(Context context) {
        AttributeSet attributeSet = null;
        this(context, attributeSet, 2, attributeSet);
    }

    public OplusQsBrightnessVolumeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
