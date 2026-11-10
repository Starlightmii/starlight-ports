package com.oplus.systemui.qs;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.TouchAnimator;
import com.android.systemui.qs.deprecated.PagedTileLayout;
import com.android.systemui.tuner.TunerService;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.OplusQSPageIndicator;
import com.oplus.systemui.qs.helper.OplusQSFontHelper;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.pipeline.data.repository.WellBeingAssistantRepositoryImpl;
import com.oplus.systemui.qs.policy.OplusQSSecurityText;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSBottomImpl extends LinearLayout implements TunerService.Tunable, Dumpable {
    public TouchAnimator mBrightnessAnimator;
    public View mBrightnessView;
    public int mBrightnesspadding;
    public int mCollapseGapTop;
    public float mCollapseTranslationY;
    public int mExpandGapTop;
    public float mExpansionAmount;
    public float mGapBottom;
    public float mLastExpansionAmount;
    public int mLastLayoutDirection;
    public OplusQSSecurityText mOplusQSSecurityView;
    public TouchAnimator mOplusQSSecurityViewAnimator;
    public OplusQSPageIndicator mPageIndicator;
    public final AnonymousClass1 mPageIndicatorCallback;
    public PagedTileLayout mPagedTileLayout;
    public TouchAnimator mQSIndicatorAnimator;
    public boolean mShowPageIndicator;
    public SpringOverScroller mSpringOverScroller;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSBottomImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 {
        public /* synthetic */ OplusQSBottomImpl this$0;
    }

    public OplusQSBottomImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastLayoutDirection = -1;
        this.mBrightnesspadding = -1;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPageIndicatorCallback = anonymousClass1;
    }

    @Override // android.view.View
    public final void computeScroll() {
        QSFragmentHelper.getInstance().computeScroll(this, this.mSpringOverScroller);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m("OplusQSBottomImpl: ", "    mCollapseGapTop: ", printWriter), this.mCollapseGapTop, printWriter, "    mExpandGapTop: "), this.mExpandGapTop, printWriter, "    mGapBottom: "), this.mGapBottom, printWriter, "    mExpansionAmount: "), this.mExpansionAmount, printWriter, "    mLastExpansionAmount: "), this.mLastExpansionAmount, printWriter, "    topGap: ");
        sbM.append(getTopGap(this.mLastExpansionAmount));
        printWriter.println(sbM.toString());
        OplusLockIconView$.ExternalSyntheticOutline0.m(new StringBuilder("    mBrightnesspadding: "), this.mBrightnesspadding, printWriter);
    }

    public int getCollapseHeight() {
        return this.mBrightnessView.getHeight();
    }

    public SpringOverScroller getSpringOverScroller() {
        return this.mSpringOverScroller;
    }

    public final float getTopGap(float f) {
        boolean z = ((LinearLayout) this).mContext.getResources().getConfiguration().orientation == 2;
        QSFeatureOption.INSTANCE.getClass();
        if (((QSFeatureOption.isSupportVolumeSeekBar() && z && QSFragmentHelper.getInstance().getQsTileMediaContainer().getMediaMode()) ? Boolean.TRUE : Boolean.FALSE).booleanValue()) {
            Resources resources = getResources();
            return ((OplusQSSmallSpaceController.isSmallSpace(((LinearLayout) this).mContext) && OplusQSDisplayModeUtils.isBigDisplayMode(((LinearLayout) this).mContext)) ? Float.valueOf(resources.getDimensionPixelSize(2131171532)) : Float.valueOf(resources.getDimensionPixelSize(2131171533))).floatValue();
        }
        this.mLastExpansionAmount = f;
        return MathUtils.lerp(this.mCollapseGapTop, this.mExpandGapTop, f);
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class)).addTunable(this, new String[]{"qs_show_brightness"});
        OplusQSPageIndicator oplusQSPageIndicator = this.mPageIndicator;
        if (oplusQSPageIndicator != null) {
            oplusQSPageIndicator.mCallbacks.add(this.mPageIndicatorCallback);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mLastLayoutDirection = -1;
        updateResources();
        this.mOplusQSSecurityView.setTextSize(OplusQSFontHelper.getInstance(((LinearLayout) this).mContext).getAdapterTextSizeSp(4));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class)).removeTunable(this);
        OplusQSPageIndicator oplusQSPageIndicator = this.mPageIndicator;
        if (oplusQSPageIndicator != null) {
            oplusQSPageIndicator.mCallbacks.remove(this.mPageIndicatorCallback);
        }
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [android.view.View, com.oplus.systemui.qs.base.widget.OplusQSPageIndicator] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ?? r0 = (OplusQSPageIndicator) findViewById(2131362314);
        this.mPageIndicator = r0;
        r0.setLayoutDirection(0);
        this.mBrightnessView = findViewById(2131364713);
        QSFeatureOption.INSTANCE.getClass();
        boolean zIsSupportVolumeSeekBar = QSFeatureOption.isSupportVolumeSeekBar();
        View view = this.mBrightnessView;
        if (zIsSupportVolumeSeekBar) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
        }
        this.mOplusQSSecurityView = (OplusQSSecurityText) findViewById(2131364767);
        updateTextColor(QsColorUtil.getQsColorState());
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this.mOplusQSSecurityView, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
        this.mOplusQSSecurityViewAnimator = builder.setStartDelay(0.84f).build();
        this.mSpringOverScroller = new SpringOverScroller(((LinearLayout) this).mContext);
        this.mOplusQSSecurityView.setTextSize(OplusQSFontHelper.getInstance(((LinearLayout) this).mContext).getAdapterTextSizeSp(4));
        updateResources();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.mLastLayoutDirection != i) {
            this.mLastLayoutDirection = i;
            updateResources();
        }
    }

    public final void onTuningChanged(String str, String str2) {
        if ("qs_show_brightness".equals(str)) {
            View view = this.mBrightnessView;
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isSupportVolumeSeekBar()) {
                view.setVisibility(8);
            } else {
                view.setVisibility((str2 == null || Integer.parseInt(str2) != 0) ? 0 : 8);
            }
        }
    }

    public void setExpansion(float f) {
        if (Float.isNaN(f)) {
            return;
        }
        this.mExpansionAmount = f;
        TouchAnimator touchAnimator = this.mQSIndicatorAnimator;
        if (touchAnimator != null) {
            touchAnimator.setPosition(f);
        }
        TouchAnimator touchAnimator2 = this.mBrightnessAnimator;
        if (touchAnimator2 != null) {
            touchAnimator2.setPosition(f);
        }
        TouchAnimator touchAnimator3 = this.mOplusQSSecurityViewAnimator;
        if (touchAnimator3 != null) {
            touchAnimator3.setPosition(f);
            if (this.mOplusQSSecurityView.getVisibility() == 0) {
                if (f == 0.0f) {
                    this.mOplusQSSecurityView.setClickable(false);
                } else if (f == 1.0f) {
                    this.mOplusQSSecurityView.setClickable(true);
                }
            }
        }
    }

    public void setHost(QSHost qSHost) {
    }

    public void setListening(boolean z) {
        this.mOplusQSSecurityView.setListening(z);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateResources() {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        boolean z = OplusQSSmallSpaceController.isSmallSpace(((LinearLayout) this).mContext) && OplusQSDisplayModeUtils.isBigDisplayMode(((LinearLayout) this).mContext);
        Resources resources = getResources();
        if (z) {
            dimensionPixelSize = resources.getDimensionPixelSize(2131171529);
        } else {
            QSFeatureOption.INSTANCE.getClass();
            dimensionPixelSize = QSFeatureOption.isSupportVolumeSeekBar() ? 0 : resources.getDimensionPixelSize(2131171528);
        }
        this.mCollapseGapTop = dimensionPixelSize;
        this.mExpandGapTop = z ? resources.getDimensionPixelSize(2131171531) : resources.getDimensionPixelSize(2131171530);
        this.mGapBottom = resources.getDimensionPixelSize(2131171534);
        boolean z2 = this.mShowPageIndicator;
        boolean zIsSuperPowerSaveModel = !z2;
        if (z2) {
            SuperPowerSaveRepositoryImpl.Companion.getClass();
            zIsSuperPowerSaveModel = SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
        }
        if (!zIsSuperPowerSaveModel) {
            WellBeingAssistantRepositoryImpl.Companion.getClass();
            zIsSuperPowerSaveModel = WellBeingAssistantRepositoryImpl.isWellBeingAssistantState;
        }
        if (zIsSuperPowerSaveModel != (this.mPageIndicator.getVisibility() != 0)) {
            this.mPageIndicator.setVisibility(zIsSuperPowerSaveModel ? 4 : 0);
        }
        Resources resources2 = getResources();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mPageIndicator.getLayoutParams();
        if (z) {
            dimensionPixelSize2 = resources2.getDimensionPixelSize(2131171538);
        } else {
            QSFeatureOption.INSTANCE.getClass();
            dimensionPixelSize2 = !QSFeatureOption.isSupportVolumeSeekBar() ? resources2.getDimensionPixelSize(2131171537) : 0;
        }
        if (layoutParams.bottomMargin != dimensionPixelSize2) {
            layoutParams.bottomMargin = dimensionPixelSize2;
            this.mPageIndicator.setLayoutParams(layoutParams);
        }
        if (zIsSuperPowerSaveModel) {
            this.mQSIndicatorAnimator = null;
        } else {
            float f = DeviceUtil.isLandscape(((LinearLayout) this).mContext) ? 0.84f : 0.9f;
            TouchAnimator.Builder builder = new TouchAnimator.Builder();
            builder.addFloat(this.mPageIndicator, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
            TouchAnimator touchAnimatorBuild = builder.setStartDelay(f).build();
            this.mQSIndicatorAnimator = touchAnimatorBuild;
            touchAnimatorBuild.setPosition(this.mExpansionAmount);
        }
        Resources resources3 = getResources();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mBrightnessView.getLayoutParams();
        if (layoutParams2.height != resources3.getDimensionPixelSize(z ? 2131171552 : 2131171551)) {
            this.mBrightnessView.setLayoutParams(layoutParams2);
        }
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(2131171539);
        if (dimensionPixelSize3 != getPaddingStart() || dimensionPixelSize3 != getPaddingEnd()) {
            this.mBrightnessView.setPaddingRelative(dimensionPixelSize3, 0, dimensionPixelSize3, 0);
        }
        this.mBrightnesspadding = dimensionPixelSize3;
        float f2 = -((this.mShowPageIndicator ? resources3.getDimensionPixelSize(2131171536) : 0.0f) + resources3.getDimensionPixelSize(2131171537));
        if (f2 != this.mCollapseTranslationY) {
            this.mCollapseTranslationY = f2;
            TouchAnimator.Builder builder2 = new TouchAnimator.Builder();
            builder2.addFloat(this.mBrightnessView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{this.mCollapseTranslationY, 0.0f});
            this.mBrightnessAnimator = builder2.build();
        }
        TouchAnimator touchAnimator = this.mBrightnessAnimator;
        if (touchAnimator != null) {
            touchAnimator.setPosition(this.mExpansionAmount);
        }
        TouchAnimator touchAnimator2 = this.mOplusQSSecurityViewAnimator;
        if (touchAnimator2 != null) {
            touchAnimator2.setPosition(this.mExpansionAmount);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateTextColor(int i) {
        PagedTileLayout pagedTileLayout = this.mPagedTileLayout;
        if (pagedTileLayout != null) {
            this.mPageIndicator.setCurrentPosition(pagedTileLayout.mPosition);
        }
        boolean zIsTextNeedUseLightColorWhenLight = QsColorUtil.isTextNeedUseLightColorWhenLight(((LinearLayout) this).mContext, i);
        OplusQSPageIndicator oplusQSPageIndicator = this.mPageIndicator;
        if (zIsTextNeedUseLightColorWhenLight) {
            oplusQSPageIndicator.setTraceDotColor(-1);
            this.mPageIndicator.setPageIndicatorDotsColor(QsColorUtil.QS_PAGE_INDICATOR_UNSELECTED_COLOR);
            int i2 = QsColorUtil.CARRIER_TEXT_LIGHT_COLOR;
            this.mOplusQSSecurityView.setTextColor(i2);
            this.mOplusQSSecurityView.setCompoundDrawableTintList(ColorStateList.valueOf(i2));
            return;
        }
        oplusQSPageIndicator.setTraceDotColor(((LinearLayout) this).mContext.getColor(2131103717));
        this.mPageIndicator.setPageIndicatorDotsColor(((LinearLayout) this).mContext.getColor(2131103720));
        int color = ((LinearLayout) this).mContext.getColor(2131103705);
        this.mOplusQSSecurityView.setTextColor(color);
        this.mOplusQSSecurityView.setCompoundDrawableTintList(ColorStateList.valueOf(color));
    }
}
