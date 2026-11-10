package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.widget.TextView;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.TuplesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TimeDrawableAodWrapper implements AodViewWrapper {
    private final Lazy colorTime$delegate;
    private final Context context;
    private final dagger.Lazy materialColorEx;
    public TextView view;

    public TimeDrawableAodWrapper(Context context, dagger.Lazy lazy) {
        this.context = context;
        this.materialColorEx = lazy;
        TimeDrawableAodWrapper$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new TimeDrawableAodWrapper$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorTime$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
    }

    private final int getColorTime() {
        return ((Number) this.colorTime$delegate.getValue()).intValue();
    }

    private final VectorDrawable getDrawable() {
        Drawable drawable;
        Drawable[] compoundDrawables = getView().getCompoundDrawables();
        int length = compoundDrawables.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                drawable = null;
                break;
            }
            drawable = compoundDrawables[i];
            if (drawable != null) {
                break;
            }
            i++;
        }
        if (drawable instanceof VectorDrawable) {
            return (VectorDrawable) drawable;
        }
        return null;
    }

    private final int getDrawableOriginalColor() {
        return ((Boolean) ((NotificationMaterialColorEx) this.materialColorEx.get()).isMaterialColor().getValue()).booleanValue() ? NotificationColorPalette.Companion.material().getSecondaryColor() : NotificationColorPalette.Companion.from((MaterialColorMode) ((NotificationMaterialColorEx) this.materialColorEx.get()).getCurrentMaterialColor().getValue()).getSecondaryColor();
    }

    private final int getStartColor() {
        return getDrawableOriginalColor();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public TimeAodColor createAodColor(int i) {
        return new TimeAodColor(2, TuplesKt.to(Integer.valueOf(getView().getCurrentTextColor()), Integer.valueOf(getStartColor())), getView().getTextColors(), TuplesKt.to(Integer.valueOf(getView().getCurrentTextColor()), Integer.valueOf(getStartColor())), TuplesKt.to(Integer.valueOf(getColorTime()), Integer.valueOf(getColorTime())));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public TimeAodColor getAodColor() {
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
    public boolean isSame(TimeAodColor timeAodColor) {
        return getView().getCurrentTextColor() == ((Number) timeAodColor.getCurr().getFirst()).intValue();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(TimeAodColor timeAodColor) {
        getView().setTextColor(timeAodColor.getOriginal());
        VectorDrawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setColorFilter(new PorterDuffColorFilter(((Number) timeAodColor.getStart().getSecond()).intValue(), PorterDuff.Mode.SRC_IN));
        }
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setAodColor(TimeAodColor timeAodColor) {
        VectorDrawable drawable;
        AodViewWrapper.Companion.setAodColor(getView(), timeAodColor);
        if (timeAodColor != null || (drawable = getDrawable()) == null) {
            return;
        }
        drawable.setColorFilter(new PorterDuffColorFilter(getStartColor(), PorterDuff.Mode.SRC_IN));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(TimeAodColor timeAodColor, float f) {
        timeAodColor.setCurr(timeAodColor.getColor(f));
        getView().setTextColor(((Number) timeAodColor.getCurr().getFirst()).intValue());
        VectorDrawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setColorFilter(new PorterDuffColorFilter(((Number) timeAodColor.getCurr().getSecond()).intValue(), PorterDuff.Mode.SRC_IN));
        }
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(TextView textView) {
        this.view = textView;
    }
}
