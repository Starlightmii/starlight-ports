package com.oplus.systemui.qs.base.seek;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsSeekBar;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.graphics.OplusPathAdapter;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.posteffect.util.OplusPathAdapterCompatUtils;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsSeekBarBlurManager;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVerticalSeekBar extends COUIVerticalSeekBar implements IQSSpotLightConfig {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsVerticalSeekBar";
    private Drawable activeMixColorDrawable;
    private Integer backgroundColorInTheme;
    private Drawable baseMixColorDrawable;
    private final Path clipPath;
    private final RectF clipProgressRect;
    private final Path inactivePath;
    private Drawable inactiveThemeDrawable;
    private Integer inactiveThemeRes;
    private final Rect inactiveTrackRect;
    public boolean isDetailToggle;
    public boolean isDrawingWithMixColor;
    public boolean isSupportMixColor;
    public boolean isSupportStroke;
    public boolean isThemeDisabled;
    public float lastPaddingVertical;
    public float mirrorScaleValue;
    private final OplusPathAdapter oplusPathAdapter;
    public int progressColorInTheme;
    private final OnSeekBarChangeListenerWrapper seekBarChangeListenerWrapper;
    public boolean showBackgroundStroke;
    private final float[] smoothCornerRadii;
    private final Lazy strokePaint$delegate;
    private final Path tempDiffOpPath;
    private final RectF tempRectFForOp;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Paint $r8$lambda$7CUKLV54gFdScYwpdXdPyCPFfcI(OplusQsVerticalSeekBar oplusQsVerticalSeekBar) {
        Paint paint = new Paint(1);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(oplusQsVerticalSeekBar.getContext().getResources().getDimension(2131172228));
        paint.setColor(((AbsSeekBar) oplusQsVerticalSeekBar).mContext.getColor(2131099770));
        return paint;
    }

    public OplusQsVerticalSeekBar(Context context) {
        super(context);
        this.isDrawingWithMixColor = this.isSupportMixColor;
        this.inactiveTrackRect = new Rect();
        this.mirrorScaleValue = 1.0f;
        OplusQsVerticalSeekBar$$ExternalSyntheticLambda0 oplusQsVerticalSeekBar$$ExternalSyntheticLambda0 = new OplusQsVerticalSeekBar$$ExternalSyntheticLambda0();
        oplusQsVerticalSeekBar$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.strokePaint$delegate = LazyKt__LazyJVMKt.lazy(oplusQsVerticalSeekBar$$ExternalSyntheticLambda0);
        this.clipPath = new Path();
        this.inactivePath = new Path();
        this.seekBarChangeListenerWrapper = new OnSeekBarChangeListenerWrapper();
        this.oplusPathAdapter = new OplusPathAdapter(((COUIVerticalSeekBar) this).mClipProgressPath, 1);
        this.clipProgressRect = new RectF(((COUIVerticalSeekBar) this).mClipProgressRect);
        this.smoothCornerRadii = new float[8];
        this.tempRectFForOp = new RectF();
        this.tempDiffOpPath = new Path();
        this.inactiveThemeRes = 2131236421;
        this.progressColorInTheme = 2131103673;
    }

    private final Paint getStrokePaint() {
        return (Paint) this.strokePaint$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setInactiveDrawableBounds(Drawable drawable) {
        float dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131172154);
        double d = dimensionPixelSize;
        drawable.setBounds(0, -((int) (((float) Math.ceil(d)) + dimensionPixelSize)), getWidth(), getHeight() + ((int) (dimensionPixelSize + ((float) Math.ceil(d)))));
    }

    public final void addOnSeekBarChangeListener(COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.seekBarChangeListenerWrapper.addListener(onSeekBarChangeListener);
        if (this.seekBarChangeListenerWrapper.isEmpty()) {
            return;
        }
        setOnSeekBarChangeListener(this.seekBarChangeListenerWrapper);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        super/*android.widget.AbsSeekBar*/.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.oplus.systemui.qs.base.util.QsSeekBarBlurManager] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.coui.appcompat.seekbar.COUIVerticalSeekBar] */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.view.View, com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void drawActiveMixColorTrack(Canvas canvas) {
        ?? r2;
        Drawable seekBarActiveDrawable = this.activeMixColorDrawable;
        if (seekBarActiveDrawable == null) {
            ?? r3 = this;
            seekBarActiveDrawable = QsSeekBarBlurManager.INSTANCE.getSeekBarActiveDrawable(r3, ((COUIVerticalSeekBar) this).mClipProgressRect, ((COUIVerticalSeekBar) this).mCurProgressRadius, ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight, this.isSupportStroke, this.isDetailToggle, this.mirrorScaleValue);
            r3.activeMixColorDrawable = seekBarActiveDrawable;
            r2 = r3;
        } else {
            r2 = this;
        }
        seekBarActiveDrawable.setBounds(((COUIVerticalSeekBar) r2).mClipProgressRect);
        int iSave = canvas.save();
        try {
            Rect rect = ((COUIVerticalSeekBar) r2).mBackgroundRect;
            int i = rect.left;
            Rect rect2 = ((COUIVerticalSeekBar) r2).mProgressRect;
            canvas.clipRect(i, rect2.top, rect2.right, rect.bottom);
            seekBarActiveDrawable.draw(canvas);
        } finally {
            canvas.restoreToCount(iSave);
        }
    }

    public final Integer getBackgroundColorInTheme() {
        return this.backgroundColorInTheme;
    }

    public final float getBackgroundCornerWeight() {
        return ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight;
    }

    public final float getBackgroundRadius() {
        return ((COUIVerticalSeekBar) this).mBackgroundRadius;
    }

    public final Rect getBackgroundRect() {
        return ((COUIVerticalSeekBar) this).mBackgroundRect;
    }

    public final Drawable getInactiveThemeDrawable() {
        return this.inactiveThemeDrawable;
    }

    public final Integer getInactiveThemeRes() {
        return this.inactiveThemeRes;
    }

    public final int getProgressColorInTheme() {
        return this.progressColorInTheme;
    }

    public float getSeekBarHeightFloat() {
        return super.getSeekBarHeightFloat();
    }

    public int getSpotLightType() {
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isThemeApplied() {
        return QsColorUtil.isGlobalThemeApplied(((AbsSeekBar) this).mContext) && !this.isThemeDisabled;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateColor(this.isSupportMixColor, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas canvas) {
        boolean z = ((COUIVerticalSeekBar) this).mIsSupportSmoothRoundCorner && ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight != 0.0f;
        if (this.isDrawingWithMixColor && isAttachedToWindow()) {
            if (getWidth() <= 0 || getHeight() <= 0) {
                return;
            }
            if (this.baseMixColorDrawable == null) {
                this.baseMixColorDrawable = QsSeekBarBlurManager.INSTANCE.getSeekBarBackground(this, ((COUIVerticalSeekBar) this).mClipProgressRect, ((COUIVerticalSeekBar) this).mCurProgressRadius, ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight, this.isSupportStroke, this.isDetailToggle, this.mirrorScaleValue);
            }
            Drawable drawable = this.baseMixColorDrawable;
            if (drawable != null) {
                drawable.setBounds(((COUIVerticalSeekBar) this).mClipProgressRect);
            }
            Drawable drawable2 = this.baseMixColorDrawable;
            if (drawable2 != null) {
                drawable2.draw(canvas);
            }
            drawActiveMixColorTrack(canvas);
            return;
        }
        super.onDraw(canvas);
        if (this.showBackgroundStroke) {
            ((COUIVerticalSeekBar) this).mClipProgressPath.reset();
            this.clipProgressRect.set(((COUIVerticalSeekBar) this).mClipProgressRect);
            if (z) {
                ArraysKt___ArraysJvmKt.fill$default(((COUIVerticalSeekBar) this).mCurProgressRadius, this.smoothCornerRadii);
                OplusPathAdapterCompatUtils.INSTANCE.addSmoothRoundRect(this.oplusPathAdapter, this.clipProgressRect, this.smoothCornerRadii, Path.Direction.CW, ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight);
            } else {
                Path path = ((COUIVerticalSeekBar) this).mClipProgressPath;
                Rect rect = ((COUIVerticalSeekBar) this).mClipProgressRect;
                float f = rect.left;
                float f2 = rect.top;
                float f3 = rect.right;
                float f4 = rect.bottom;
                float f5 = ((COUIVerticalSeekBar) this).mCurProgressRadius;
                path.addRoundRect(f, f2, f3, f4, f5, f5, Path.Direction.CCW);
            }
            RectF rectF = this.tempRectFForOp;
            Rect rect2 = ((COUIVerticalSeekBar) this).mProgressRect;
            rectF.set(rect2.left, rect2.top, rect2.right, rect2.bottom);
            this.tempDiffOpPath.reset();
            this.tempDiffOpPath.addRect(this.tempRectFForOp, Path.Direction.CCW);
            if (!((COUIVerticalSeekBar) this).mClipProgressPath.op(this.tempDiffOpPath, Path.Op.DIFFERENCE)) {
                LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "drawMixColorInactiveTrack: fail to op");
            }
            int iSave = canvas.save();
            try {
                canvas.drawPath(((COUIVerticalSeekBar) this).mClipProgressPath, getStrokePaint());
            } finally {
                canvas.restoreToCount(iSave);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setBackgroundWidth(getMeasuredWidth());
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        Drawable drawable;
        super.onSizeChanged(i, i2, i3, i4);
        setMoveDamping();
        if (!isThemeApplied() || (drawable = this.inactiveThemeDrawable) == null) {
            return;
        }
        setInactiveDrawableBounds(drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onVisibilityAggregated(boolean z) {
        ViewBlurProxy viewBlurProxy;
        ViewBlurProxy viewBlurProxy2;
        super/*android.widget.AbsSeekBar*/.onVisibilityAggregated(z);
        Drawable drawable = this.baseMixColorDrawable;
        AutoBlurDrawable autoBlurDrawable = drawable instanceof AutoBlurDrawable ? (AutoBlurDrawable) drawable : null;
        if (autoBlurDrawable != null && (viewBlurProxy2 = autoBlurDrawable.getViewBlurProxy()) != null) {
            viewBlurProxy2.onVisibilityAggregated(z);
        }
        Drawable drawable2 = this.activeMixColorDrawable;
        AutoBlurDrawable autoBlurDrawable2 = drawable2 instanceof AutoBlurDrawable ? (AutoBlurDrawable) drawable2 : null;
        if (autoBlurDrawable2 == null || (viewBlurProxy = autoBlurDrawable2.getViewBlurProxy()) == null) {
            return;
        }
        viewBlurProxy.onVisibilityAggregated(z);
    }

    public final void removeOnSeekBarChangeListener(COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.seekBarChangeListenerWrapper.removeListener(onSeekBarChangeListener);
        if (this.seekBarChangeListenerWrapper.isEmpty()) {
            setOnSeekBarChangeListener((COUIVerticalSeekBar.OnSeekBarChangeListener) null);
        }
    }

    public final void setBackgroundColorInTheme(Integer num) {
        this.backgroundColorInTheme = num;
    }

    public final void setDetailToggle(boolean z) {
        this.isDetailToggle = z;
    }

    public final void setInactiveThemeDrawable(Drawable drawable) {
        this.inactiveThemeDrawable = drawable;
    }

    public final void setInactiveThemeRes(Integer num) {
        this.inactiveThemeRes = num;
    }

    public final void setMirrorBlurScaleValue(float f) {
        this.mirrorScaleValue = f;
    }

    public final void setMoveDamping() {
        float seekBarHeightFloat = getSeekBarHeightFloat();
        float f = this.lastPaddingVertical;
        float f2 = ((COUIVerticalSeekBar) this).mCurPaddingVertical;
        if (f != f2 && seekBarHeightFloat > 0.0f && f2 > 0.0f) {
            this.lastPaddingVertical = f2;
            float f3 = (seekBarHeightFloat / ((2.0f * f2) + seekBarHeightFloat)) / 0.7759499f;
            setMoveDamping(RangesKt___RangesKt.coerceIn(f3, 0.5f, 1.3f));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("setMoveDamping:", f3, " seekBarHeight:", seekBarHeightFloat, " mCurPaddingVertical:"), ((COUIVerticalSeekBar) this).mCurPaddingVertical, "OplusQsVerticalSeekBar-->", "SystemUi--QuickSettings");
            }
        }
    }

    public final void setProgress(int i, boolean z) {
        ValueAnimator valueAnimator;
        if (!z && (valueAnimator = ((COUIVerticalSeekBar) this).mClickAnimator) != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        setProgress(i, z, false);
    }

    public final void setProgressColorInTheme(int i) {
        this.progressColorInTheme = i;
    }

    public final void setSupportStroke(boolean z) {
        this.isSupportStroke = z;
    }

    public final void startTransitionAnim(int i, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", MobileInputLogger$.ExternalSyntheticOutline0.m("startTransitionAnim: progress=", i, " isFromUser=", "OplusQsVerticalSeekBar-->", z));
        }
        super.startTransitionAnim(i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateBaseMixColorDrawableRadius(float f) {
        Drawable drawable = this.baseMixColorDrawable;
        AutoBlurDrawable autoBlurDrawable = drawable instanceof AutoBlurDrawable ? (AutoBlurDrawable) drawable : null;
        if (autoBlurDrawable != null) {
            QsSeekBarBlurManager.INSTANCE.applySeekBarBgBlurConfig(((AbsSeekBar) this).mContext, ((COUIVerticalSeekBar) this).mClipProgressRect, f, ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight, autoBlurDrawable, this.isSupportStroke, this.isDetailToggle, this.mirrorScaleValue);
        }
        Drawable drawable2 = this.activeMixColorDrawable;
        AutoBlurDrawable autoBlurDrawable2 = drawable2 instanceof AutoBlurDrawable ? (AutoBlurDrawable) drawable2 : null;
        if (autoBlurDrawable2 != null) {
            QsSeekBarBlurManager.INSTANCE.applySeekBarActiveBlurConfig(this, ((COUIVerticalSeekBar) this).mClipProgressRect, f, ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight, autoBlurDrawable2, this.isDetailToggle, this.mirrorScaleValue, this.isSupportStroke);
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x009f  */
    /* JADX WARN: Code duplicated, block: B:38:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:53:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:85:0x01a8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:86:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:87:0x01b4  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.widget.AbsSeekBar, com.coui.appcompat.seekbar.COUIVerticalSeekBar, com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.oplus.systemui.qs.base.util.QsSeekBarBlurManager] */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.widget.AbsSeekBar, com.coui.appcompat.seekbar.COUIVerticalSeekBar, com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar] */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.widget.AbsSeekBar, com.coui.appcompat.seekbar.COUIVerticalSeekBar, com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateColor(boolean z, boolean z2) {
        Integer num;
        ?? r3;
        boolean zIsThemeApplied = isThemeApplied();
        this.isSupportMixColor = z;
        this.isDrawingWithMixColor = z && !zIsThemeApplied;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" ", "OplusQsVerticalSeekBar-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("updateColor: isSupportMixColor=", " ", " ", z, this.isDrawingWithMixColor), isAttachedToWindow(), zIsThemeApplied));
        }
        if (this.isDrawingWithMixColor && isAttachedToWindow()) {
            Drawable drawable = this.baseMixColorDrawable;
            AutoBlurDrawable autoBlurDrawable = drawable instanceof AutoBlurDrawable ? (AutoBlurDrawable) drawable : null;
            if (autoBlurDrawable != null) {
                QsSeekBarBlurManager.INSTANCE.applySeekBarBgBlurConfig(((AbsSeekBar) this).mContext, ((COUIVerticalSeekBar) this).mClipProgressRect, ((COUIVerticalSeekBar) this).mCurProgressRadius, ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight, autoBlurDrawable, this.isSupportStroke, this.isDetailToggle, this.mirrorScaleValue);
            }
            Drawable drawable2 = this.activeMixColorDrawable;
            AutoBlurDrawable autoBlurDrawable2 = drawable2 instanceof AutoBlurDrawable ? (AutoBlurDrawable) drawable2 : null;
            if (autoBlurDrawable2 != null) {
                ?? r4 = this;
                QsSeekBarBlurManager.INSTANCE.applySeekBarActiveBlurConfig(r4, ((COUIVerticalSeekBar) this).mClipProgressRect, ((COUIVerticalSeekBar) this).mCurProgressRadius, ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight, autoBlurDrawable2, this.isDetailToggle, this.mirrorScaleValue, this.isSupportStroke);
                r3 = r4;
            } else {
                r3 = this;
            }
        } else {
            ?? r5 = this;
            if (zIsThemeApplied) {
                Integer num2 = r5.inactiveThemeRes;
                if (num2 != null) {
                    Drawable drawable3 = ((AbsSeekBar) r5).mContext.getDrawable(num2.intValue());
                    if (drawable3 != null) {
                        r5.inactiveThemeDrawable = drawable3;
                        r5.setInactiveTrackDrawable(drawable3);
                        r5.setInactiveDrawableBounds(drawable3);
                        r3 = r5;
                    } else {
                        num = r5.backgroundColorInTheme;
                        r3 = r5;
                        if (num != null) {
                            r5.setSeekBarBackgroundColor(ColorStateList.valueOf(((AbsSeekBar) r5).mContext.getColor(num.intValue())));
                            r3 = r5;
                        }
                    }
                } else {
                    num = r5.backgroundColorInTheme;
                    r3 = r5;
                    if (num != null) {
                        r5.setSeekBarBackgroundColor(ColorStateList.valueOf(((AbsSeekBar) r5).mContext.getColor(num.intValue())));
                        r3 = r5;
                    }
                }
            } else {
                r5.setInactiveTrackDrawable((Drawable) null);
                r5.inactiveThemeDrawable = null;
                r3 = r5;
            }
        }
        if (r3.isDrawingWithMixColor) {
            return;
        }
        if (QsColorUtil.isNeedDarkThemeColor(r3.getContext(), r3.isThemeDisabled)) {
            if (ScrimUtil.isLowGaussianLevel(r3.getContext())) {
                QsColorUtil.INSTANCE.getClass();
                if (QsColorUtil.isSuperPowerSaveState) {
                    r3.setSeekBarBackgroundColor(ColorStateList.valueOf(((AbsSeekBar) r3).mContext.getColor(2131103288)));
                } else if (!z2 || QsColorUtil.isGlobalThemeApplied(((AbsSeekBar) r3).mContext)) {
                    r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.LIGHT_OS_BRIGHTNESS_SLIDER_DARK_BG_COLOR));
                } else {
                    r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.DRAG_MODE_BG_COLOR));
                }
            } else {
                r3.setSeekBarBackgroundColor(ColorStateList.valueOf(((AbsSeekBar) r3).mContext.getColor(2131103288)));
            }
            r3.setProgressColor(ColorStateList.valueOf(QsColorUtil.QS_TILE_BG_INACTIVE_COLOR));
            return;
        }
        Context context = ((AbsSeekBar) r3).mContext;
        if (zIsThemeApplied) {
            r3.setProgressColor(ColorStateList.valueOf(context.getColor(r3.progressColorInTheme)));
            return;
        }
        if (QsColorUtil.isIconNeedUseLightColor(context, r3.isThemeDisabled) || QsColorUtil.isLowGaussianLevelColor(((AbsSeekBar) r3).mContext, r3.isThemeDisabled)) {
            if (!z2) {
                r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.BRIGHTNESS_SLIDER_BG_COLOR));
            } else if (QsColorUtil.isLowGaussianLevelColor(((AbsSeekBar) r3).mContext, r3.isThemeDisabled)) {
                r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.DRAG_MODE_BG_LIGHT_COLOR));
            } else {
                r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.DRAG_MODE_BG_COLOR));
            }
            r3.setProgressColor(ColorStateList.valueOf(QsColorUtil.QS_TILE_BG_INACTIVE_COLOR));
            return;
        }
        if (!QsColorUtil.isIconNeedUseLightColorWhenDarkMode(((AbsSeekBar) r3).mContext, r3.isThemeDisabled) && !QsColorUtil.isLowGaussianLevelColorWhenDarkMode(((AbsSeekBar) r3).mContext, r3.isThemeDisabled)) {
            int color = ((AbsSeekBar) r3).mContext.getColor(!r3.isThemeDisabled ? 2131103669 : 2131103670);
            int color2 = ((AbsSeekBar) r3).mContext.getColor(2131103673);
            r3.setSeekBarBackgroundColor(ColorStateList.valueOf(color));
            r3.setProgressColor(ColorStateList.valueOf(color2));
            return;
        }
        if (ScrimUtil.isLowGaussianLevel(r3.getContext())) {
            QsColorUtil.INSTANCE.getClass();
            if (QsColorUtil.isSuperPowerSaveState || ScrimUtil.isWallpaperBlurDisable()) {
                if (z2) {
                    r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.DRAG_MODE_BG_DARK_COLOR));
                } else {
                    r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.QS_TILE_BG_INACTIVE_DARK_COLOR));
                }
            } else if (z2) {
                r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.DRAG_MODE_BG_COLOR));
            } else {
                r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.LIGHT_OS_BRIGHTNESS_SLIDER_DARK_BG_COLOR));
            }
        } else if (z2) {
            r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.DRAG_MODE_BG_DARK_COLOR));
        } else {
            r3.setSeekBarBackgroundColor(ColorStateList.valueOf(QsColorUtil.QS_TILE_BG_INACTIVE_DARK_COLOR));
        }
        r3.setProgressColor(ColorStateList.valueOf(QsColorUtil.QS_TILE_BG_INACTIVE_COLOR));
    }

    public void updateSpotLightPath(Path path, Object obj) {
        if (obj instanceof OplusPathAdapter) {
            ((OplusPathAdapter) obj).addSmoothRoundRect(new RectF(getBackgroundRect()), getBackgroundRadius(), getBackgroundRadius(), Path.Direction.CW);
        } else {
            path.addRoundRect(new RectF(getBackgroundRect()), getBackgroundRadius(), getBackgroundRadius(), Path.Direction.CW);
        }
    }

    public OplusQsVerticalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isDrawingWithMixColor = this.isSupportMixColor;
        this.inactiveTrackRect = new Rect();
        this.mirrorScaleValue = 1.0f;
        OplusQsVerticalSeekBar$$ExternalSyntheticLambda0 oplusQsVerticalSeekBar$$ExternalSyntheticLambda0 = new OplusQsVerticalSeekBar$$ExternalSyntheticLambda0();
        oplusQsVerticalSeekBar$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.strokePaint$delegate = LazyKt__LazyJVMKt.lazy(oplusQsVerticalSeekBar$$ExternalSyntheticLambda0);
        this.clipPath = new Path();
        this.inactivePath = new Path();
        this.seekBarChangeListenerWrapper = new OnSeekBarChangeListenerWrapper();
        this.oplusPathAdapter = new OplusPathAdapter(((COUIVerticalSeekBar) this).mClipProgressPath, 1);
        this.clipProgressRect = new RectF(((COUIVerticalSeekBar) this).mClipProgressRect);
        this.smoothCornerRadii = new float[8];
        this.tempRectFForOp = new RectF();
        this.tempDiffOpPath = new Path();
        this.inactiveThemeRes = 2131236421;
        this.progressColorInTheme = 2131103673;
    }

    public OplusQsVerticalSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isDrawingWithMixColor = this.isSupportMixColor;
        this.inactiveTrackRect = new Rect();
        this.mirrorScaleValue = 1.0f;
        OplusQsVerticalSeekBar$$ExternalSyntheticLambda0 oplusQsVerticalSeekBar$$ExternalSyntheticLambda0 = new OplusQsVerticalSeekBar$$ExternalSyntheticLambda0();
        oplusQsVerticalSeekBar$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.strokePaint$delegate = LazyKt__LazyJVMKt.lazy(oplusQsVerticalSeekBar$$ExternalSyntheticLambda0);
        this.clipPath = new Path();
        this.inactivePath = new Path();
        this.seekBarChangeListenerWrapper = new OnSeekBarChangeListenerWrapper();
        this.oplusPathAdapter = new OplusPathAdapter(((COUIVerticalSeekBar) this).mClipProgressPath, 1);
        this.clipProgressRect = new RectF(((COUIVerticalSeekBar) this).mClipProgressRect);
        this.smoothCornerRadii = new float[8];
        this.tempRectFForOp = new RectF();
        this.tempDiffOpPath = new Path();
        this.inactiveThemeRes = 2131236421;
        this.progressColorInTheme = 2131103673;
    }

    public OplusQsVerticalSeekBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isDrawingWithMixColor = this.isSupportMixColor;
        this.inactiveTrackRect = new Rect();
        this.mirrorScaleValue = 1.0f;
        OplusQsVerticalSeekBar$$ExternalSyntheticLambda0 oplusQsVerticalSeekBar$$ExternalSyntheticLambda0 = new OplusQsVerticalSeekBar$$ExternalSyntheticLambda0();
        oplusQsVerticalSeekBar$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.strokePaint$delegate = LazyKt__LazyJVMKt.lazy(oplusQsVerticalSeekBar$$ExternalSyntheticLambda0);
        this.clipPath = new Path();
        this.inactivePath = new Path();
        this.seekBarChangeListenerWrapper = new OnSeekBarChangeListenerWrapper();
        this.oplusPathAdapter = new OplusPathAdapter(((COUIVerticalSeekBar) this).mClipProgressPath, 1);
        this.clipProgressRect = new RectF(((COUIVerticalSeekBar) this).mClipProgressRect);
        this.smoothCornerRadii = new float[8];
        this.tempRectFForOp = new RectF();
        this.tempDiffOpPath = new Path();
        this.inactiveThemeRes = 2131236421;
        this.progressColorInTheme = 2131103673;
    }
}
