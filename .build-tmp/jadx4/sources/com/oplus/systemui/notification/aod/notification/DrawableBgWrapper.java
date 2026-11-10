package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DrawableBgWrapper implements AodViewWrapper {
    private final Lazy colorCustom$delegate;
    private final Context context;
    public ViewGroup view;

    public DrawableBgWrapper(Context context) {
        this.context = context;
        DrawableBgWrapper$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new DrawableBgWrapper$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorCustom$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
    }

    private final int getColorCustom() {
        return ((Number) this.colorCustom$delegate.getValue()).intValue();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public OneColorAodColor createAodColor(int i) {
        int colorCustom = getColorCustom();
        OneColorAodColor oneColorAodColor = new OneColorAodColor(2);
        oneColorAodColor.state = 2;
        oneColorAodColor.end = colorCustom;
        oneColorAodColor.curr = 0;
        VarHandle.storeStoreFence();
        return oneColorAodColor;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ AodColor getAodColor() {
        return super.getAodColor();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public ViewGroup getView() {
        ViewGroup viewGroup = this.view;
        if (viewGroup != null) {
            return viewGroup;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(OneColorAodColor oneColorAodColor) {
        Drawable background = getView().getBackground();
        if (background != null) {
            background.setTintList(null);
        }
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ void setAodColor(AodColor aodColor) {
        super.setAodColor(aodColor);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(OneColorAodColor oneColorAodColor, float f) {
        oneColorAodColor.getClass();
        ArgbEvaluatorCompat argbEvaluatorCompat = ArgbEvaluatorCompat.getInstance();
        Integer numValueOf = Integer.valueOf(oneColorAodColor.end);
        argbEvaluatorCompat.getClass();
        oneColorAodColor.setCurr(ArgbEvaluatorCompat.evaluate(f, 0, numValueOf).intValue());
        Drawable background = getView().getBackground();
        if (background != null) {
            background.setTintMode(PorterDuff.Mode.SRC_OVER);
        }
        Drawable background2 = getView().getBackground();
        if (background2 != null) {
            background2.setTint(oneColorAodColor.getCurr());
        }
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(ViewGroup viewGroup) {
        this.view = viewGroup;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public boolean isSame(OneColorAodColor oneColorAodColor) {
        return true;
    }
}
