package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.OneShotPreDrawListener;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsLabelView extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QsLabelView";
    public boolean enableExpandIndicator;
    private final Lazy expandIndicatorDrawable$delegate;
    public final int indicatorPadding;
    private final ImageView indicatorView;
    private final Lazy indicatorViewWidth$delegate;
    public int labelPadding;
    public int lastLineHeight;
    private final View textAndIndicatorView;
    private TextChangeListener textChangeListener;
    private final TextView textView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public QsLabelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.labelPadding = getResources().getDimensionPixelSize(2131171960);
        this.indicatorPadding = getResources().getDimensionPixelSize(2131171958);
        QsLabelView$$ExternalSyntheticLambda0 qsLabelView$$ExternalSyntheticLambda0 = new QsLabelView$$ExternalSyntheticLambda0(0);
        qsLabelView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.expandIndicatorDrawable$delegate = LazyKt__LazyJVMKt.lazy(qsLabelView$$ExternalSyntheticLambda0);
        QsLabelView$$ExternalSyntheticLambda0 qsLabelView$$ExternalSyntheticLambda1 = new QsLabelView$$ExternalSyntheticLambda0(1);
        qsLabelView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.indicatorViewWidth$delegate = LazyKt__LazyJVMKt.lazy(qsLabelView$$ExternalSyntheticLambda1);
        FrameLayout.inflate(context, 2131559388, this);
        setClipChildren(false);
        setClipToPadding(false);
        this.textAndIndicatorView = findViewById(2131363621);
        this.textView = (TextView) findViewById(2131365666);
        this.indicatorView = (ImageView) findViewById(2131363082);
        int i3 = this.labelPadding;
        setPaddingRelative(i3, 0, i3, 0);
    }

    public static final void access$updateLabelPadding(QsLabelView qsLabelView) {
        if (qsLabelView.enableExpandIndicator && Intrinsics.areEqual(Locale.getDefault().getLanguage(), "en") && qsLabelView.textView.getLineCount() > 1) {
            qsLabelView.labelPadding = qsLabelView.getResources().getDimensionPixelSize(2131171959);
        } else {
            qsLabelView.labelPadding = qsLabelView.getResources().getDimensionPixelSize(2131171960);
        }
        qsLabelView.setPaddingRelative(qsLabelView.labelPadding, qsLabelView.getPaddingTop(), qsLabelView.labelPadding, qsLabelView.getPaddingBottom());
    }

    private final Drawable getExpandIndicatorDrawable() {
        return (Drawable) this.expandIndicatorDrawable$delegate.getValue();
    }

    private final int getIndicatorViewWidth() {
        return ((Number) this.indicatorViewWidth$delegate.getValue()).intValue();
    }

    public final boolean getEnableExpandIndicator() {
        return this.enableExpandIndicator;
    }

    public final ImageView getIndicatorView() {
        return this.indicatorView;
    }

    public final View getTextAndIndicatorView() {
        return this.textAndIndicatorView;
    }

    public final TextChangeListener getTextChangeListener() {
        return null;
    }

    public final TextView getTextView() {
        return this.textView;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = ((View.MeasureSpec.getSize(i) - getIndicatorViewWidth()) - this.indicatorPadding) - (this.labelPadding * 2);
        if (this.indicatorView.getVisibility() == 0 && this.textView.getMaxWidth() != size) {
            this.textView.setMaxWidth(size);
        }
        super.onMeasure(i, i2);
    }

    public final void setEnableExpandIndicator(boolean z) {
        if (this.enableExpandIndicator != z) {
            this.enableExpandIndicator = z;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[enableExpandIndicator] ", "QsLabelView-->", "SystemUi--QuickSettings", z);
            }
            this.indicatorView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.view.View
    public void setLayerType(int i, Paint paint) {
        this.textView.setLayerType(i, paint);
        this.indicatorView.setLayerType(i, paint);
    }

    public final void setTextChangeListener(TextChangeListener textChangeListener) {
    }

    public final void setTextLineHeightAndSpacing(int i) {
        if (this.lastLineHeight != i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("[setTextLineHeight] lastLineHeight=", this.lastLineHeight, i, ", lineHeight=", "QsLabelView-->"));
            }
            this.lastLineHeight = i;
            this.textView.setLineHeight(i);
            this.textView.setLineSpacing(0.0f, 1.0f);
        }
    }

    public final void updateColor(int i, boolean z, boolean z2) {
        ColorStateList colorStateList;
        if (ScrimUtil.isWallpaperBlurDisable()) {
            colorStateList = getContext().getColorStateList(z ? 2131103755 : 2131103753);
        } else if (QsColorUtil.isTextNeedUseLightColorWhenLight(getContext(), i)) {
            colorStateList = z ? ColorStateList.valueOf(QsColorUtil.QS_TILE_LABEL_UNAVAILABLE_COLOR) : getContext().getColorStateList(R.color.white);
        } else if (z) {
            if (QsColorUtil.isNeedUseDarkThemeColor(getContext(), z2)) {
                colorStateList = (ScrimUtil.isBAnimLevel() && z2) ? getContext().getColorStateList(2131103755) : getContext().getColorStateList(2131103291);
            } else {
                colorStateList = getContext().getColorStateList(z2 ? 2131103755 : 2131103754);
            }
        } else if (QsColorUtil.isNeedUseDarkThemeColor(getContext(), z2)) {
            colorStateList = (ScrimUtil.isBAnimLevel() && z2) ? getContext().getColorStateList(2131103753) : getContext().getColorStateList(2131103289);
        } else {
            colorStateList = getContext().getColorStateList(z2 ? 2131103753 : 2131103752);
        }
        colorStateList.getClass();
        this.textView.setTextColor(colorStateList);
        getExpandIndicatorDrawable().setTintList(colorStateList);
        this.indicatorView.setImageDrawable(getExpandIndicatorDrawable());
    }

    public final void updateText(CharSequence charSequence) {
        if (Intrinsics.areEqual(charSequence, this.textView.getText())) {
            return;
        }
        this.textView.setText(charSequence);
        QsLabelView$updateText$$inlined$doOnPreDraw$1 qsLabelView$updateText$$inlined$doOnPreDraw$1 = new QsLabelView$updateText$$inlined$doOnPreDraw$1();
        qsLabelView$updateText$$inlined$doOnPreDraw$1.$this_doOnPreDraw = this;
        qsLabelView$updateText$$inlined$doOnPreDraw$1.this$0 = this;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(this, qsLabelView$updateText$$inlined$doOnPreDraw$1);
    }

    public QsLabelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public QsLabelView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ QsLabelView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public QsLabelView(Context context) {
        this(context, null, 0, 0, 14, null);
    }
}
