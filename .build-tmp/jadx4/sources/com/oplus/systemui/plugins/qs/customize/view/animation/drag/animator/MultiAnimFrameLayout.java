package com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import java.lang.invoke.VarHandle;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class MultiAnimFrameLayout extends FrameLayout implements IMultiAnimView {
    private MultiAnimViewDelegate delegate;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static Unit $r8$lambda$V8kSRRj60s356LDX0T2k1fULV1E(MultiAnimFrameLayout multiAnimFrameLayout, String str, float f) {
        switch (str.hashCode()) {
            case -1225497657:
                if (str.equals(MultiAnimViewDelegate.PROPERTY_TRANSLATION_X)) {
                    super.setTranslationX(f);
                }
                break;
            case -1225497656:
                if (str.equals(MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y)) {
                    super.setTranslationY(f);
                }
                break;
            case -908189618:
                if (str.equals(MultiAnimViewDelegate.PROPERTY_SCALE_X)) {
                    super.setScaleX(f);
                }
                break;
            case -908189617:
                if (str.equals(MultiAnimViewDelegate.PROPERTY_SCALE_Y)) {
                    super.setScaleY(f);
                }
                break;
        }
        return Unit.INSTANCE;
    }

    public MultiAnimFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        MultiAnimFrameLayout$$ExternalSyntheticLambda0 multiAnimFrameLayout$$ExternalSyntheticLambda0 = new MultiAnimFrameLayout$$ExternalSyntheticLambda0();
        multiAnimFrameLayout$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.delegate = new MultiAnimViewDelegate(this, multiAnimFrameLayout$$ExternalSyntheticLambda0);
    }

    public final MultiAnimViewDelegate getDelegate() {
        return this.delegate;
    }

    public final void setDelegate(MultiAnimViewDelegate multiAnimViewDelegate) {
        this.delegate = multiAnimViewDelegate;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.IMultiAnimView
    public void setProperty(String str, float f, String str2) {
        this.delegate.setProperty(str, f, str2);
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.delegate.setProperty(MultiAnimViewDelegate.PROPERTY_SCALE_X, f, null);
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        this.delegate.setProperty(MultiAnimViewDelegate.PROPERTY_SCALE_Y, f, null);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.IMultiAnimView
    public void setShouldBlockTranslationAndScaleChange(boolean z, String str) {
        this.delegate.setShouldBlockTranslationAndScaleChange(z, str);
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.delegate.setProperty(MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, f, null);
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.delegate.setProperty(MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, f, null);
    }

    public MultiAnimFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 12, 0);
    }

    public MultiAnimFrameLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 8, 0);
    }

    public /* synthetic */ MultiAnimFrameLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i, 0);
    }

    public MultiAnimFrameLayout(Context context) {
        this(context, null, 0, 14, 0);
    }
}
