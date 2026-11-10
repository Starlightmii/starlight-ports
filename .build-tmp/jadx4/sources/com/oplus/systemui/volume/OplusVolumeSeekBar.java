package com.oplus.systemui.volume;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.graphics.OplusPath;
import com.oplus.systemui.volume.utils.VolumeAnimUtil;
import com.oplus.systemui.volume.utils.material.OplusVolumeBarMaterialHost;
import com.oplus.systemui.volume.utils.material.OplusVolumeBlurDrawableFinder;
import com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext;
import com.oplus.systemui.volume.utils.material.VolumeBlurLightParams;
import com.oplus.systemui.volume.utils.material.VolumeBlurManager;
import com.oplus.systemui.volume.utils.spotlight.OplusVolumeSeekBarSpotLightHelper;
import com.oplus.systemui.volume.view.OplusVolumeDialogView;
import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusVolumeSeekBar extends COUIVerticalSeekBar implements OplusVolumeSeekBarMaterialContext {
    public static final Interpolator VOLUME_SCALE_WIDTH_INTERPOLATOR = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
    public static boolean sIsDefaultVolumeTheme;
    public final Paint mArcSuperVolumePaint;
    public OnBottomDeformedListener mBottomDeformedListener;
    public final Context mContext;
    public final Paint mDrawPaint;
    public final Handler mHandler;
    public EffectiveAnimationView mIcon;
    public boolean mInStopTrackingTouchCallback;
    public float mInitRawX;
    public float mInitRawY;
    public boolean mIsAnimating;
    public boolean mIsDisabled;
    public Boolean mIsShowSuperVolume;
    public boolean mIsStartTrack;
    public boolean mIsSuperVolumeOn;
    public boolean mIsTouching;
    public float mLastBottomDeformedValue;
    public final OplusVolumeBarMaterialHost mMaterialHost;
    public OnSeekBarChangeListener mOnChangeListener;
    public boolean mPerformClick;
    public float mPlatformBlurStrokeWidthScale;
    public float mPlatformOuterShadowScale;
    public AutoBlurDrawable mProgressBlurDrawable;
    public final OplusVolumeSeekBar$$ExternalSyntheticLambda0 mRunnable;
    public final AnonymousClass1 mSeekBarListener;
    public final OplusVolumeSeekBarSpotLightHelper mSpotLightHelper;
    public ValueAnimator mSuperVolumeAlphaAnimator;
    public final Paint mSuperVolumePaint;
    public int mThickType;
    public Boolean mTouchable;
    public int mTrackTouch;
    public int mTrackingTouchSleepTime;
    public int mTransType;

    /* JADX INFO: renamed from: com.oplus.systemui.volume.OplusVolumeSeekBar$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements COUIVerticalSeekBar.OnSeekBarChangeListener {
        public /* synthetic */ OplusVolumeSeekBar this$0;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [android.widget.AbsSeekBar, com.oplus.systemui.volume.OplusVolumeSeekBar] */
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
        public final void onProgressChanged(COUIVerticalSeekBar cOUIVerticalSeekBar, int i, boolean z) {
            OnSeekBarChangeListener onSeekBarChangeListener;
            ?? r0 = this.this$0;
            if ((r0.mTrackTouch == 0 || r0.isAccessibilityFocused()) && (onSeekBarChangeListener = r0.mOnChangeListener) != 0) {
                onSeekBarChangeListener.onProgressChanged(r0, i, z);
            }
        }

        public final void onStartTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
            OplusVolumeSeekBar oplusVolumeSeekBar = this.this$0;
            Interpolator interpolator = OplusVolumeSeekBar.VOLUME_SCALE_WIDTH_INTERPOLATOR;
            oplusVolumeSeekBar.mIsStartTrack = true;
            oplusVolumeSeekBar.mHandler.removeCallbacks(oplusVolumeSeekBar.mRunnable);
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(3.0f, 6.0f);
            OplusVolumeSeekBar$$ExternalSyntheticLambda2 oplusVolumeSeekBar$$ExternalSyntheticLambda2 = new OplusVolumeSeekBar$$ExternalSyntheticLambda2(2);
            oplusVolumeSeekBar$$ExternalSyntheticLambda2.f$0 = oplusVolumeSeekBar;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(oplusVolumeSeekBar$$ExternalSyntheticLambda2);
            valueAnimatorOfFloat.setDuration(300L);
            valueAnimatorOfFloat.setInterpolator(OplusVolumeSeekBar.VOLUME_SCALE_WIDTH_INTERPOLATOR);
            valueAnimatorOfFloat.start();
            if (oplusVolumeSeekBar.mTrackTouch == -1) {
                oplusVolumeSeekBar.setTrackTouch(0);
                OnSeekBarChangeListener onSeekBarChangeListener = oplusVolumeSeekBar.mOnChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStartTrackingTouch(oplusVolumeSeekBar);
                }
            }
        }

        public final void onStopTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
            this.this$0.setStopTrackingTouch();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.OplusVolumeSeekBar$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements COUIVerticalSeekBar.OnDeformedListener {
        public /* synthetic */ OplusVolumeSeekBar this$0;

        public final void onHeightDeformedChanged(float f, float f2) {
            ViewGroup mVerticalRowIconParent;
            OplusVolumeSeekBar oplusVolumeSeekBar = this.this$0;
            if (oplusVolumeSeekBar.mLastBottomDeformedValue == f2) {
                return;
            }
            oplusVolumeSeekBar.mLastBottomDeformedValue = f2;
            EffectiveAnimationView effectiveAnimationView = oplusVolumeSeekBar.mIcon;
            if (effectiveAnimationView != null) {
                effectiveAnimationView.setTranslationY(f2);
            }
            OplusVolumeDialogView.blurHostHelper.1 r0 = oplusVolumeSeekBar.mBottomDeformedListener;
            if (r0 == null || (mVerticalRowIconParent = r0.this$0.getMVerticalRowIconParent()) == null) {
                return;
            }
            mVerticalRowIconParent.setTranslationY(f2);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OnBottomDeformedListener {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OnSeekBarChangeListener {
        void onProgressChanged(OplusVolumeSeekBar oplusVolumeSeekBar, int i, boolean z);

        void onStartTrackingTouch(OplusVolumeSeekBar oplusVolumeSeekBar);

        void onStopTrackingTouch(OplusVolumeSeekBar oplusVolumeSeekBar);

        void onTouchDown(OplusVolumeSeekBar oplusVolumeSeekBar);

        void onTouchUp(OplusVolumeSeekBar oplusVolumeSeekBar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: $r8$lambda$S96uiNGVV3LhcUDJypm4Tz-pJb4, reason: not valid java name */
    public static void m479$r8$lambda$S96uiNGVV3LhcUDJypm4TzpJb4(OplusVolumeSeekBar oplusVolumeSeekBar, boolean z, float f, float f2, float f3, float f4, float f5, int i, int i2, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        if (z) {
            oplusVolumeSeekBar.setPlatformBlurStrokeWidthScale(DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f2, f, animatedFraction, f));
            if (sIsDefaultVolumeTheme && VolumeBlurLightParams.isLightEnabled(oplusVolumeSeekBar.getContext(), sIsDefaultVolumeTheme)) {
                VolumeBlurManager.updateVolumeBarPlatformStrokeWidthScale(oplusVolumeSeekBar, oplusVolumeSeekBar.mPlatformBlurStrokeWidthScale);
            }
        }
        oplusVolumeSeekBar.applyPlatformOuterShadowScale(((f4 - f3) * animatedFraction) + f3);
        if (f5 == 0.0f) {
            return;
        }
        if (oplusVolumeSeekBar.getBackgroundBlurDrawable() == null && oplusVolumeSeekBar.getPlatformAutoBlurDrawable() == null) {
            return;
        }
        oplusVolumeSeekBar.applyBarBlurRadiusPx(i + ((int) ((1.0f - (oplusVolumeSeekBar.getTranslationX() / f5)) * (i2 - i))));
    }

    public static /* synthetic */ void $r8$lambda$vY1agyACasnvlgvzVFzjajssX7Y(OplusVolumeSeekBar oplusVolumeSeekBar) {
        oplusVolumeSeekBar.mIsStartTrack = false;
        oplusVolumeSeekBar.setTrackTouch(-1);
    }

    public static /* synthetic */ void $r8$lambda$yc7tbjk2FV6CO0uCtEl2QrPqUis(OplusVolumeSeekBar oplusVolumeSeekBar, OplusVolumeDialogImpl$$ExternalSyntheticLambda1 oplusVolumeDialogImpl$$ExternalSyntheticLambda1, int i, int i2, int i3) {
        oplusVolumeDialogImpl$$ExternalSyntheticLambda1.run();
        if (oplusVolumeSeekBar.getBackgroundBlurDrawable() == null && oplusVolumeSeekBar.getPlatformAutoBlurDrawable() == null) {
            return;
        }
        if (i == 1) {
            oplusVolumeSeekBar.applyBarBlurRadiusPx(i2);
        } else if (i == 0) {
            oplusVolumeSeekBar.applyBarBlurRadiusPx(i3);
        }
    }

    static {
        new PathInterpolator(0.3f, 0.0f, 0.67f, 1.0f);
        sIsDefaultVolumeTheme = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusVolumeSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsStartTrack = false;
        this.mIsSuperVolumeOn = false;
        this.mIsDisabled = false;
        this.mTrackTouch = -1;
        this.mTrackingTouchSleepTime = 0;
        new Path();
        this.mLastBottomDeformedValue = Float.MIN_VALUE;
        this.mIsTouching = false;
        this.mThickType = 0;
        this.mTransType = 0;
        this.mIsAnimating = false;
        this.mPlatformBlurStrokeWidthScale = 1.0f;
        this.mPlatformOuterShadowScale = 1.0f;
        this.mHandler = new Handler();
        this.mPerformClick = true;
        this.mIsShowSuperVolume = Boolean.FALSE;
        this.mTouchable = Boolean.TRUE;
        this.mBottomDeformedListener = null;
        this.mInStopTrackingTouchCallback = false;
        OplusVolumeSeekBar$$ExternalSyntheticLambda0 oplusVolumeSeekBar$$ExternalSyntheticLambda0 = new OplusVolumeSeekBar$$ExternalSyntheticLambda0();
        oplusVolumeSeekBar$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mRunnable = oplusVolumeSeekBar$$ExternalSyntheticLambda0;
        OplusVolumeBarMaterialHost oplusVolumeBarMaterialHost = new OplusVolumeBarMaterialHost(this);
        this.mMaterialHost = oplusVolumeBarMaterialHost;
        this.mSpotLightHelper = new OplusVolumeSeekBarSpotLightHelper(this, oplusVolumeBarMaterialHost);
        this.mContext = context;
        Paint paint = new Paint();
        this.mDrawPaint = paint;
        paint.setFilterBitmap(false);
        this.mDrawPaint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mSuperVolumePaint = paint2;
        paint2.setFilterBitmap(false);
        this.mSuperVolumePaint.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.mArcSuperVolumePaint = paint3;
        paint3.setFilterBitmap(false);
        this.mArcSuperVolumePaint.setAntiAlias(true);
        setFlingLinearDamping(25.0f);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSeekBarListener = anonymousClass1;
        setOnSeekBarChangeListener(anonymousClass1);
        setMoveType(2);
        setMoveDamping(0.8f);
        setEnableCustomEnlarge(true);
        ((COUIVerticalSeekBar) this).mMaxTopMovingDistance = 20;
        ((COUIVerticalSeekBar) this).mMaxBottomMovingDistance = 10;
        ((COUIVerticalSeekBar) this).mMaxTopHeightDeformedValue = 18.0f;
        ((COUIVerticalSeekBar) this).mMaxBottomHeightDeformedValue = 9.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private BackgroundBlurDrawable getBackgroundBlurDrawable() {
        return OplusVolumeBlurDrawableFinder.findBackgroundBlurDrawable(getBackground());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AutoBlurDrawable getPlatformAutoBlurDrawable() {
        return OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(getBackground());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View getRowView() {
        for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
            View view = (View) parent;
            if (view.getTag() instanceof OplusVolumeRow) {
                return view;
            }
        }
        return null;
    }

    public static void setIsDefaultVolumeTheme(boolean z) {
        sIsDefaultVolumeTheme = z;
    }

    private void setPlatformBlurStrokeWidthScale(float f) {
        this.mPlatformBlurStrokeWidthScale = Math.max(0.01f, Math.min(1.0f, f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setTrackTouch(int i) {
        this.mTrackTouch = i;
    }

    private void setTrackingTouchSleepTime(int i) {
        this.mTrackingTouchSleepTime = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void applyBarBlurRadiusPx(int i) {
        BackgroundBlurDrawable backgroundBlurDrawable = getBackgroundBlurDrawable();
        if (backgroundBlurDrawable != null) {
            backgroundBlurDrawable.setBlurRadius(i);
            return;
        }
        AutoBlurDrawable platformAutoBlurDrawable = getPlatformAutoBlurDrawable();
        if (platformAutoBlurDrawable != null) {
            platformAutoBlurDrawable.getViewBlurProxy().setBlurAmount(VolumeBlurManager.mapBarBlurRadiusToAmount(i, getContext()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void applyPlatformOuterShadowScale(float f) {
        this.mPlatformOuterShadowScale = Math.max(0.125f, Math.min(1.0f, f));
        if (!sIsDefaultVolumeTheme || isVolumePanelExpanded()) {
            return;
        }
        setElevation(getResources().getDimension(2131173914) * this.mPlatformOuterShadowScale);
    }

    @Override // com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext
    public final void copyBackgroundRect(Rect rect) {
        rect.set(((COUIVerticalSeekBar) this).mBackgroundRect);
    }

    @Override // com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext
    public final void copyClipProgressRect(Rect rect) {
        rect.set(((COUIVerticalSeekBar) this).mClipProgressRect);
    }

    @Override // com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext
    public final void copyProgressRect(Rect rect) {
        rect.set(((COUIVerticalSeekBar) this).mProgressRect);
    }

    public final void doKeyDownTrans(View view, float f, boolean z, int i) {
        COUISpringInterpolator cOUISpringInterpolator = z ? VolumeAnimUtil.VOLUME_TRANS_MOVE_INTERPOLATOR : VolumeAnimUtil.VOLUME_TRANS_BACK_INTERPOLATOR;
        float f2 = z ? 0.6f * f : f;
        OplusVolumeSeekBar$$ExternalSyntheticLambda1 oplusVolumeSeekBar$$ExternalSyntheticLambda1 = new OplusVolumeSeekBar$$ExternalSyntheticLambda1(0);
        oplusVolumeSeekBar$$ExternalSyntheticLambda1.f$0 = this;
        oplusVolumeSeekBar$$ExternalSyntheticLambda1.f$1 = i;
        VarHandle.storeStoreFence();
        VolumeAnimUtil.startViewPropertyAnimation(view, 0L, 550L, cOUISpringInterpolator, f2, 0.0f, 0.0f, 0.0f, oplusVolumeSeekBar$$ExternalSyntheticLambda1, null, null, 1);
    }

    public final void draw(Canvas canvas) {
        this.mMaterialHost.syncBeforeDraw();
        super.draw(canvas);
    }

    public final void drawActiveTrack(Canvas canvas) {
        Drawable defaultDrawable;
        if (!sIsDefaultVolumeTheme || this.mProgressBlurDrawable == null || !VolumeBlurManager.isVolumePlatformBlurEnabled(this.mContext) || ((COUIVerticalSeekBar) this).mClipProgressRect.isEmpty() || ((COUIVerticalSeekBar) this).mProgressRect.isEmpty()) {
            super.drawActiveTrack(canvas);
            VolumeFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.isOplusSuperVolume() && this.mThickType == 1 && this.mIsShowSuperVolume.booleanValue()) {
                drawProgressWithGradient(canvas);
                return;
            } else {
                ((COUIVerticalSeekBar) this).mProgressPaint.setShader(null);
                return;
            }
        }
        this.mProgressBlurDrawable.setBounds(((COUIVerticalSeekBar) this).mClipProgressRect);
        VolumeBlurManager.syncVolumeBarProgressCorner(this.mProgressBlurDrawable, ((COUIVerticalSeekBar) this).mCurProgressRadius, null);
        int iSave = canvas.save();
        Rect rect = ((COUIVerticalSeekBar) this).mBackgroundRect;
        int i = rect.left;
        Rect rect2 = ((COUIVerticalSeekBar) this).mProgressRect;
        canvas.clipRect(i, rect2.top, rect2.right, rect.bottom);
        if (this.mProgressBlurDrawable.getViewBlurProxy().getCachePlatformStaticBlurDrawable() == null && (defaultDrawable = this.mProgressBlurDrawable.getDefaultDrawable()) != null) {
            defaultDrawable.setBounds(((COUIVerticalSeekBar) this).mClipProgressRect);
            defaultDrawable.draw(canvas);
        }
        this.mProgressBlurDrawable.draw(canvas);
        canvas.restoreToCount(iSave);
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isOplusSuperVolume() && this.mThickType == 1 && this.mIsShowSuperVolume.booleanValue()) {
            drawProgressWithGradient(canvas);
        }
    }

    public final void drawProgressWithGradient(Canvas canvas) {
        boolean z = ((COUIVerticalSeekBar) this).mIsSupportSmoothRoundCorner && ((COUIVerticalSeekBar) this).mProgressRoundCornerWeight != 0.0f;
        Rect rect = ((COUIVerticalSeekBar) this).mProgressRect;
        ((COUIVerticalSeekBar) this).mProgressPaint.setShader(new LinearGradient(0.0f, rect.top, 0.0f, rect.bottom, new int[]{this.mContext.getResources().getColor(2131103117), this.mContext.getResources().getColor(2131103127)}, new float[]{0.0f, 0.2f}, Shader.TileMode.CLAMP));
        ((COUIVerticalSeekBar) this).mClipProgressPath.reset();
        if (z) {
            OplusPath oplusPath = new OplusPath(((COUIVerticalSeekBar) this).mClipProgressPath);
            Rect rect2 = ((COUIVerticalSeekBar) this).mClipProgressRect;
            float f = rect2.left;
            float f2 = rect2.top;
            float f3 = rect2.right;
            float f4 = rect2.bottom;
            float f5 = ((COUIVerticalSeekBar) this).mCurProgressRadius;
            oplusPath.addSmoothRoundRect(f, f2, f3, f4, f5, f5, ((COUIVerticalSeekBar) this).mProgressRoundCornerWeight, Path.Direction.CCW);
        } else {
            Path path = ((COUIVerticalSeekBar) this).mClipProgressPath;
            Rect rect3 = ((COUIVerticalSeekBar) this).mClipProgressRect;
            float f6 = rect3.left;
            float f7 = rect3.top;
            float f8 = rect3.right;
            float f9 = rect3.bottom;
            float f10 = ((COUIVerticalSeekBar) this).mCurProgressRadius;
            path.addRoundRect(f6, f7, f8, f9, f10, f10, Path.Direction.CCW);
        }
        canvas.save();
        canvas.clipPath(((COUIVerticalSeekBar) this).mClipProgressPath);
        canvas.drawRect(((COUIVerticalSeekBar) this).mProgressRect, ((COUIVerticalSeekBar) this).mProgressPaint);
        canvas.restore();
    }

    public final void endCustomDeformation() {
        super.endCustomDeformation();
        EffectiveAnimationView effectiveAnimationView = this.mIcon;
        if (effectiveAnimationView != null) {
            effectiveAnimationView.setTranslationY(0.0f);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void ensureProgressPlatformBlur() {
        if (!sIsDefaultVolumeTheme || !VolumeBlurManager.isVolumePlatformBlurEnabled(this.mContext)) {
            releaseProgressPlatformBlur();
            return;
        }
        if (VolumeBlurManager.volumeSliderBlurWanted) {
            if (this.mProgressBlurDrawable == null) {
                this.mProgressBlurDrawable = VolumeBlurManager.getVolumeBarProgressDrawable(this.mContext, this, ((COUIVerticalSeekBar) this).mCurProgressRadius);
            }
            if (this.mProgressBlurDrawable == null) {
                return;
            }
            setSeekBarBackgroundColor(ColorStateList.valueOf(0));
            setProgressColor(ColorStateList.valueOf(0));
            VolumeBlurManager.applyVolumeBarProgressMixColors(this.mProgressBlurDrawable, this.mContext);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext
    public Context getAppContext() {
        return getContext().getApplicationContext();
    }

    @Override // com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext
    public float getBackgroundRoundCornerWeight() {
        return ((COUIVerticalSeekBar) this).mBackgroundRoundCornerWeight;
    }

    @Override // com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext
    public float getCurBackgroundRadius() {
        return ((COUIVerticalSeekBar) this).mCurBackgroundRadius;
    }

    public boolean getDisabled() {
        return this.mIsDisabled;
    }

    public boolean getIsSuperVolumeOn() {
        return this.mIsSuperVolumeOn;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext
    public View getMaterialHostView() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getMaxHeight() {
        return super/*android.widget.AbsSeekBar*/.getMaxHeight();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getMinHeight() {
        return super/*android.widget.AbsSeekBar*/.getMinHeight();
    }

    public float getPlatformBlurStrokeWidthScale() {
        return this.mPlatformBlurStrokeWidthScale;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.volume.utils.material.OplusVolumeSeekBarMaterialContext
    public Drawable getSeekBarBackground() {
        return getBackground();
    }

    public int getThickType() {
        return this.mThickType;
    }

    public final boolean isAnimating() {
        return this.mIsAnimating;
    }

    public final boolean isVolumePanelExpanded() {
        View rowView = getRowView();
        if (rowView == null) {
            return false;
        }
        Object tag = rowView.getTag();
        if (tag instanceof OplusVolumeRow) {
            return ((OplusVolumeRow) tag).isVolumePanelExpanded();
        }
        return false;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mSpotLightHelper.onAttachedToWindow();
        refreshSpotLightEnableState();
        this.mMaterialHost.refreshPlatformLightForGateChange();
        ensureProgressPlatformBlur();
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mSpotLightHelper.onDetachedFromWindow();
        releaseProgressPlatformBlur();
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (sIsDefaultVolumeTheme) {
            this.mSpotLightHelper.drawSpotLightEffect(canvas);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        getMeasuredHeight();
        getRotation();
        this.mContext.getResources().getDimension(2131173977);
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mSpotLightHelper.onSizeChanged(i, i2);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x005e  */
    /* JADX WARN: Code duplicated, block: B:30:0x0069  */
    /* JADX WARN: Code duplicated, block: B:32:0x0070  */
    /* JADX WARN: Code duplicated, block: B:34:0x0075  */
    /* JADX WARN: Code duplicated, block: B:44:0x00b3  */
    /* JADX WARN: Instruction removed from duplicated block: B:44:0x00b3, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        EffectiveAnimationView effectiveAnimationView;
        int[] locationOnScreen;
        if (this.mTransType == 1 || !this.mTouchable.booleanValue()) {
            LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeSeekBar", "mTransType = " + this.mTransType + " mTouchable = " + this.mTouchable);
            return true;
        }
        if (this.mIsDisabled) {
            return true;
        }
        if (sIsDefaultVolumeTheme) {
            this.mSpotLightHelper.handleMotionEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mOnChangeListener.onTouchDown(this);
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            this.mIsTouching = true;
            this.mPerformClick = true;
            this.mInitRawX = motionEvent.getRawX();
            this.mInitRawY = motionEvent.getRawY();
            LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeSeekBar", "ACTION_DOWN mInitRawX=" + this.mInitRawX + " mInitRawY=" + this.mInitRawY);
        } else if (action == 1) {
            this.mOnChangeListener.onTouchUp(this);
            this.mIsTouching = false;
            effectiveAnimationView = this.mIcon;
            if (effectiveAnimationView != null) {
                locationOnScreen = effectiveAnimationView.getLocationOnScreen();
                if (locationOnScreen.length != 2) {
                    return super.onTouchEvent(motionEvent);
                }
                if (this.mPerformClick && motionEvent.getRawX() > locationOnScreen[0]) {
                    if (motionEvent.getRawX() < this.mIcon.getWidth() + locationOnScreen[0] && motionEvent.getRawY() > locationOnScreen[1]) {
                        if (motionEvent.getRawY() < this.mIcon.getHeight() + locationOnScreen[1]) {
                            LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeSeekBar", "ACTION_UP mPerformClick=" + this.mPerformClick + " " + locationOnScreen[0] + " " + locationOnScreen[1] + " getRawX=" + motionEvent.getRawX() + " getRawY=" + motionEvent.getRawY());
                            this.mIcon.performClick();
                            return true;
                        }
                    }
                }
            }
        } else if (action != 2) {
            if (action == 3) {
                this.mOnChangeListener.onTouchUp(this);
                this.mIsTouching = false;
                effectiveAnimationView = this.mIcon;
                if (effectiveAnimationView != null) {
                    locationOnScreen = effectiveAnimationView.getLocationOnScreen();
                    if (locationOnScreen.length != 2) {
                        return super.onTouchEvent(motionEvent);
                    }
                    if (this.mPerformClick) {
                        if (motionEvent.getRawX() < this.mIcon.getWidth() + locationOnScreen[0]) {
                            if (motionEvent.getRawY() < this.mIcon.getHeight() + locationOnScreen[1]) {
                                LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeSeekBar", "ACTION_UP mPerformClick=" + this.mPerformClick + " " + locationOnScreen[0] + " " + locationOnScreen[1] + " getRawX=" + motionEvent.getRawX() + " getRawY=" + motionEvent.getRawY());
                                this.mIcon.performClick();
                                return true;
                            }
                        }
                    }
                }
            }
        } else if (this.mPerformClick && (Math.abs(motionEvent.getRawX() - this.mInitRawX) > ((COUIVerticalSeekBar) this).mTouchSlop || Math.abs(motionEvent.getRawY() - this.mInitRawY) > ((COUIVerticalSeekBar) this).mTouchSlop)) {
            this.mPerformClick = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void preCalcMaterialClipPath() {
        int seekBarCenterX = getSeekBarCenterX();
        float paddingTop = (getPaddingTop() + ((COUIVerticalSeekBar) this).mCurPaddingVertical) - ((COUIVerticalSeekBar) this).mCurBackgroundRadius;
        float height = ((getHeight() - getPaddingBottom()) - ((COUIVerticalSeekBar) this).mCurPaddingVertical) + ((COUIVerticalSeekBar) this).mCurBackgroundRadius;
        Rect rect = ((COUIVerticalSeekBar) this).mBackgroundRect;
        float f = seekBarCenterX;
        float f2 = (((COUIVerticalSeekBar) this).mCurBackgroundWidth / 2.0f) - ((COUIVerticalSeekBar) this).mWidthDeformedValue;
        rect.set((int) (f - f2), (int) ((paddingTop - ((COUIVerticalSeekBar) this).mHeightTopDeformedUpValue) + ((COUIVerticalSeekBar) this).mHeightTopDeformedDownValue), (int) (f2 + f), (int) ((height - ((COUIVerticalSeekBar) this).mHeightBottomDeformedUpValue) + ((COUIVerticalSeekBar) this).mHeightBottomDeformedDownValue));
    }

    public final void refreshSpotLightEnableState() {
        this.mSpotLightHelper.refreshEnableState(sIsDefaultVolumeTheme, this.mTouchable.booleanValue(), this.mIsDisabled);
    }

    public final void releaseProgressPlatformBlur() {
        AutoBlurDrawable autoBlurDrawable = this.mProgressBlurDrawable;
        if (autoBlurDrawable == null) {
            if (sIsDefaultVolumeTheme && VolumeBlurManager.isVolumePlatformBlurEnabled(this.mContext)) {
                setSeekBarBackgroundColor(ColorStateList.valueOf(0));
                setProgressColor(ColorStateList.valueOf(0));
                return;
            }
            return;
        }
        VolumeBlurManager.releaseVolumeBarProgressBlur(autoBlurDrawable);
        this.mProgressBlurDrawable = null;
        if (sIsDefaultVolumeTheme) {
            if (VolumeBlurManager.isVolumePlatformBlurEnabled(this.mContext)) {
                setSeekBarBackgroundColor(ColorStateList.valueOf(0));
                setProgressColor(ColorStateList.valueOf(0));
            } else {
                setSeekBarBackgroundColor(ColorStateList.valueOf(this.mContext.getColor(2131103128)));
                setProgressColor(ColorStateList.valueOf(this.mContext.getColor(2131103127)));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void resetSeekBar() {
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "resetSeekBar");
        animate().cancel();
        startEnlargeAnim(0, 600L, VolumeAnimUtil.VOLUME_END_THICK_INTERPOLATOR);
        ValueAnimator valueAnimator = ((COUIVerticalSeekBar) this).mEnlargeAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            ((COUIVerticalSeekBar) this).mEnlargeAnimator.end();
        }
        ValueAnimator valueAnimator2 = ((COUIVerticalSeekBar) this).mReleaseAnimator;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            ((COUIVerticalSeekBar) this).mReleaseAnimator.end();
        }
        endCustomDeformation();
        setTranslationX(0.0f);
        applyBarBlurRadiusPx(VolumeBlurManager.getVolumeBarBlurStrengthMaxPx(getContext()));
        setPlatformBlurStrokeWidthScale(1.0f);
        if (sIsDefaultVolumeTheme && VolumeBlurLightParams.isLightEnabled(getContext(), sIsDefaultVolumeTheme)) {
            VolumeBlurManager.updateVolumeBarPlatformStrokeWidthScale(this, this.mPlatformBlurStrokeWidthScale);
        }
        applyPlatformOuterShadowScale(1.0f);
        EffectiveAnimationView effectiveAnimationView = this.mIcon;
        if (effectiveAnimationView != null) {
            effectiveAnimationView.animate().cancel();
            this.mIcon.setTranslationX(0.0f);
            this.mIcon.setScaleX(1.0f);
            this.mIcon.setScaleY(1.0f);
            this.mIcon.setAlpha(1.0f);
        }
        setTouchable(true);
        this.mThickType = 0;
        this.mTransType = 0;
    }

    public void setAnimating(boolean z) {
        this.mIsAnimating = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setBackground(Drawable drawable) {
        super/*android.widget.AbsSeekBar*/.setBackground(drawable);
        this.mMaterialHost.attachBackgroundGeometryResolver();
        this.mMaterialHost.refreshPlatformLightForGateChange();
    }

    public void setDisabled(boolean z) {
        this.mIsDisabled = z;
        refreshSpotLightEnableState();
    }

    public void setIcon(EffectiveAnimationView effectiveAnimationView) {
        this.mIcon = effectiveAnimationView;
        this.mLastBottomDeformedValue = Float.MIN_VALUE;
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        setDeformedListener(anonymousClass5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setIsShowSuperVolume(boolean z) {
        this.mIsShowSuperVolume = Boolean.valueOf(z);
        invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setIsSuperVolumeOn(boolean z) {
        if (!(z != this.mIsSuperVolumeOn)) {
            this.mIsSuperVolumeOn = z;
            invalidate();
            return;
        }
        this.mIsSuperVolumeOn = z;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(z ? 0 : 255, z ? 255 : 0);
        this.mSuperVolumeAlphaAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(z ? 783L : 600L);
        this.mSuperVolumeAlphaAnimator.setInterpolator(VolumeAnimUtil.SUPER_VOLUME_SLIDER_COLOR_INTERPOLATOR);
        ValueAnimator valueAnimator = this.mSuperVolumeAlphaAnimator;
        OplusVolumeSeekBar$$ExternalSyntheticLambda2 oplusVolumeSeekBar$$ExternalSyntheticLambda2 = new OplusVolumeSeekBar$$ExternalSyntheticLambda2(0);
        oplusVolumeSeekBar$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator.addUpdateListener(oplusVolumeSeekBar$$ExternalSyntheticLambda2);
        this.mSuperVolumeAlphaAnimator.addListener(new 4(this));
        this.mSuperVolumeAlphaAnimator.start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized void setMax(int i) {
        super.setMax(i);
        postInvalidate();
    }

    public void setOnBottomDeformedListener(OnBottomDeformedListener onBottomDeformedListener) {
        this.mBottomDeformedListener = onBottomDeformedListener;
        this.mLastBottomDeformedValue = Float.MIN_VALUE;
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnChangeListener = onSeekBarChangeListener;
    }

    public synchronized void setProgress(int i) {
        try {
            if (i != getProgress() && !this.mIsAnimating && !this.mInStopTrackingTouchCallback) {
                endCustomDeformation();
                stopPhysicsMove();
            }
            super.setProgress(i);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void setStopTrackingTouch() {
        if (this.mTrackTouch == 0) {
            OnSeekBarChangeListener onSeekBarChangeListener = this.mOnChangeListener;
            if (onSeekBarChangeListener != null) {
                this.mInStopTrackingTouchCallback = true;
                try {
                    onSeekBarChangeListener.onStopTrackingTouch(this);
                    this.mInStopTrackingTouchCallback = false;
                } catch (Throwable th) {
                    this.mInStopTrackingTouchCallback = false;
                    throw th;
                }
            }
            Interpolator interpolator = VOLUME_SCALE_WIDTH_INTERPOLATOR;
            setTrackingTouchSleepTime(300);
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(6.0f, 3.0f);
            OplusVolumeSeekBar$$ExternalSyntheticLambda2 oplusVolumeSeekBar$$ExternalSyntheticLambda2 = new OplusVolumeSeekBar$$ExternalSyntheticLambda2(1);
            oplusVolumeSeekBar$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(oplusVolumeSeekBar$$ExternalSyntheticLambda2);
            valueAnimatorOfFloat.setDuration(300L);
            valueAnimatorOfFloat.setStartDelay(0L);
            valueAnimatorOfFloat.setInterpolator(interpolator);
            valueAnimatorOfFloat.start();
            this.mHandler.postDelayed(this.mRunnable, this.mTrackingTouchSleepTime);
        }
    }

    public void setTouchable(boolean z) {
        this.mTouchable = Boolean.valueOf(z);
        refreshSpotLightEnableState();
    }

    public final boolean shouldThick(boolean z) {
        boolean z2 = false;
        if (!VolumeAnimUtil.enableComplexAnim(this.mContext)) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "shouldThick enableComplexAnim=false");
            return false;
        }
        View rowView = getRowView();
        float alpha = rowView != null ? rowView.getAlpha() : 1.0f;
        if (this.mThickType != z && (((z && !this.mIsTouching) || !z) && alpha > 0.0f)) {
            z2 = true;
        }
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("shouldThick toThick:", ",result:", ",rowViewAlpha:", z, z2);
        sbM.append(alpha);
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", sbM.toString());
        return z2;
    }

    public OplusVolumeSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public OplusVolumeSeekBar(Context context) {
        this(context, null);
    }
}
