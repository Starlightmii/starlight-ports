package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.widget.ImageView;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImageViewAodWrapper implements AodViewWrapper {
    public static final Companion Companion = new Companion();
    private final Lazy colorCustom$delegate;
    private final Lazy colorOther$delegate;
    private final Context context;
    private final Lazy expandIconColor$delegate;
    public ImageView view;

    public ImageViewAodWrapper(Context context) {
        this.context = context;
        ImageViewAodWrapper$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ImageViewAodWrapper$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorOther$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
        ImageViewAodWrapper$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new ImageViewAodWrapper$.ExternalSyntheticLambda0(1);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.expandIconColor$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda1);
        ImageViewAodWrapper$.ExternalSyntheticLambda0 externalSyntheticLambda2 = new ImageViewAodWrapper$.ExternalSyntheticLambda0(2);
        externalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorCustom$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda2);
    }

    private final int getColorCustom() {
        return ((Number) this.colorCustom$delegate.getValue()).intValue();
    }

    private final int getColorOther() {
        return ((Number) this.colorOther$delegate.getValue()).intValue();
    }

    private final int getExpandIconColor() {
        return ((Number) this.expandIconColor$delegate.getValue()).intValue();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public ImageAodColor createAodColor(int i) {
        int expandIconColor;
        Companion companion = Companion;
        int iAccess$getCurrentColor = Companion.access$getCurrentColor(companion, getView());
        int iAccess$getCurrentColor2 = Companion.access$getCurrentColor(companion, getView());
        if (i != 3) {
            expandIconColor = (i == 7 || i == 9) ? getColorCustom() : getColorOther();
        } else {
            expandIconColor = getExpandIconColor();
        }
        return new ImageAodColor(2, iAccess$getCurrentColor, iAccess$getCurrentColor2, expandIconColor, i == 4 ? Float.valueOf(0.2f) : null);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ AodColor getAodColor() {
        return super.getAodColor();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public ImageView getView() {
        ImageView imageView = this.view;
        if (imageView != null) {
            return imageView;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public boolean isSame(ImageAodColor imageAodColor) {
        return Companion.access$getCurrentColor(Companion, getView()) == imageAodColor.getCurr();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(ImageAodColor imageAodColor) {
        if (imageAodColor.getStart() == 0) {
            getView().setColorFilter((ColorFilter) null);
        } else if (getView().getId() == 16909091) {
            getView().setColorFilter(imageAodColor.getStart(), PorterDuff.Mode.SRC_IN);
        } else {
            getView().setColorFilter(imageAodColor.getStart());
        }
        if (imageAodColor.getEndAlpha() != null) {
            getView().setTransitionAlpha(1.0f);
        }
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ void setAodColor(AodColor aodColor) {
        super.setAodColor(aodColor);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(ImageAodColor imageAodColor, float f) {
        imageAodColor.getClass();
        ArgbEvaluatorCompat argbEvaluatorCompat = ArgbEvaluatorCompat.getInstance();
        Integer numValueOf = Integer.valueOf(imageAodColor.start);
        Integer numValueOf2 = Integer.valueOf(imageAodColor.end);
        argbEvaluatorCompat.getClass();
        imageAodColor.setCurr(ArgbEvaluatorCompat.evaluate(f, numValueOf, numValueOf2).intValue());
        if (getView().getId() == 16909091) {
            getView().setColorFilter(imageAodColor.getCurr(), PorterDuff.Mode.SRC_IN);
        } else {
            getView().setColorFilter(imageAodColor.getCurr());
        }
        if (imageAodColor.getEndAlpha() != null) {
            getView().setTransitionAlpha(imageAodColor.getAlpha(f));
        }
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(ImageView imageView) {
        this.view = imageView;
    }
}
