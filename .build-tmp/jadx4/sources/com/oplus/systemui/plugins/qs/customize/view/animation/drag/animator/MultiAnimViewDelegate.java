package com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator;

import android.view.View;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiAnimViewDelegate implements IMultiAnimView {
    public static final Companion Companion = new Companion();
    public static final String PROPERTY_SCALE_X = "scaleX";
    public static final String PROPERTY_SCALE_Y = "scaleY";
    public static final String PROPERTY_TRANSLATION_X = "translationX";
    public static final String PROPERTY_TRANSLATION_Y = "translationY";
    private static final String TAG = "MultiAnimViewDelegate";
    public boolean blockTranslationAndScaleChange;
    private String enableTag;
    private final Function2 superSetProperty;
    private final View view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MultiAnimViewDelegate(View view, Function2 function2) {
        this.view = view;
        this.superSetProperty = function2;
    }

    public final String enableTag() {
        return this.enableTag;
    }

    public final void reset() {
        this.blockTranslationAndScaleChange = false;
        this.enableTag = null;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.IMultiAnimView
    public void setProperty(String str, float f, String str2) {
        if (!this.blockTranslationAndScaleChange || (Intrinsics.areEqual(str2, this.enableTag) && this.enableTag != null)) {
            this.superSetProperty.invoke(str, Float.valueOf(f));
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.IMultiAnimView
    public void setShouldBlockTranslationAndScaleChange(boolean z, String str) {
        View view = this.view;
        StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("setShouldBlockTranslationAndScaleChange: ", ", tag: ", str, z, ", view: ");
        sbM.append(view);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        this.blockTranslationAndScaleChange = z;
        if (!z) {
            str = null;
        }
        this.enableTag = str;
    }
}
