package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.widget.TextView;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.text.HexExtensionsKt;
import kotlin.text.HexFormat;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TextViewAodWrapper implements AodViewWrapper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TextViewAodWrapper";
    private final Lazy colorOther$delegate;
    private final Lazy colorTime$delegate;
    private final Context context;
    public TextView view;

    public TextViewAodWrapper(Context context) {
        this.context = context;
        TextViewAodWrapper$$ExternalSyntheticLambda0 textViewAodWrapper$$ExternalSyntheticLambda0 = new TextViewAodWrapper$$ExternalSyntheticLambda0(0);
        textViewAodWrapper$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorTime$delegate = LazyKt__LazyJVMKt.lazy(textViewAodWrapper$$ExternalSyntheticLambda0);
        TextViewAodWrapper$$ExternalSyntheticLambda0 textViewAodWrapper$$ExternalSyntheticLambda1 = new TextViewAodWrapper$$ExternalSyntheticLambda0(1);
        textViewAodWrapper$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorOther$delegate = LazyKt__LazyJVMKt.lazy(textViewAodWrapper$$ExternalSyntheticLambda1);
    }

    private final int getColorOther() {
        return ((Number) this.colorOther$delegate.getValue()).intValue();
    }

    private final int getColorTime() {
        return ((Number) this.colorTime$delegate.getValue()).intValue();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public TextAodColor createAodColor(int i) {
        return new TextAodColor(2, getView().getCurrentTextColor(), getView().getTextColors(), getView().getCurrentTextColor(), (i == 1 || i == 5) ? getColorTime() : getColorOther());
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ AodColor getAodColor() {
        return super.getAodColor();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public TextView getView() {
        TextView textView = this.view;
        if (textView != null) {
            return textView;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public boolean isSame(TextAodColor textAodColor) {
        return getView().getCurrentTextColor() == textAodColor.getCurr();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(TextAodColor textAodColor) {
        getView().setTextColor(textAodColor.getOriginal());
        if (getView().getCurrentTextColor() == textAodColor.getEnd()) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "error happened when restore color ", HexExtensionsKt.toHexString(textAodColor.getEnd(), HexFormat.Companion.getDefault()), LogUtil.TAG_NOTIFICATION, TAG);
        }
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ void setAodColor(AodColor aodColor) {
        super.setAodColor(aodColor);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(TextAodColor textAodColor, float f) {
        textAodColor.getClass();
        ArgbEvaluatorCompat argbEvaluatorCompat = ArgbEvaluatorCompat.getInstance();
        Integer numValueOf = Integer.valueOf(textAodColor.start);
        Integer numValueOf2 = Integer.valueOf(textAodColor.end);
        argbEvaluatorCompat.getClass();
        textAodColor.setCurr(ArgbEvaluatorCompat.evaluate(f, numValueOf, numValueOf2).intValue());
        getView().setTextColor(textAodColor.getCurr());
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(TextView textView) {
        this.view = textView;
    }
}
