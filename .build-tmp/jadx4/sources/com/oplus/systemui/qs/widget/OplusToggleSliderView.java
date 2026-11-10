package com.oplus.systemui.qs.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.collection.FloatList$;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.keyguard.OplusLockIconView$;
import com.android.settingslib.RestrictedLockUtils;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.qs.OplusBrightnessControllerEx;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.qs.personality.OnPersonalityChangeListener;
import com.android.systemui.settings.brightness.BrightnessDialog;
import com.android.systemui.util.ColorUtilKt;
import com.coui.appcompat.seekbar.COUISeekBar;
import com.coui.appcompat.seekbar.core.COUISeekBarAdvanced;
import com.oplus.systemui.qs.OplusQSSmallSpaceController;
import com.oplus.systemui.qs.base.seek.ClipBrightnessView;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.utils.LocalLongClickHandler;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplus.systemui.util.ScreenUtils;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import com.oplusos.systemui.util.OpUtils;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusToggleSliderView extends RelativeLayout implements OnPersonalityChangeListener, Dumpable {
    public static final Interpolator ICON_COLOR_TRANSITION_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    public final ClipBrightnessView mBrightnessIcon;
    public OplusBrightnessControllerEx mExt;
    public final TextView mHighBrightness;
    public OplusToggleSliderView$$ExternalSyntheticLambda0 mIconChangeRunnable;
    public final boolean mIsIconNeedUseLightColor;
    public final boolean mIsInBrightnessDialog;
    public boolean mIsMirror;
    public final LocalLongClickHandler mLongClickHandler;
    public MirrorController mMirrorController;
    public Gefingerpoken mOnInterceptListener;
    public final AnonymousClass2 mOnSeekBarChangeListener;
    public final AnonymousClass3 mOnTouchListener;
    public final AnonymousClass1 mOutlineProvider;
    public final float mRadius;
    public COUISeekBar.OnSeekBarChangeListener mSeekListener;
    public OplusToggleSliderView$$ExternalSyntheticLambda1 mSetupProgressDrawableRunnable;
    public int mShapeType;
    public final OplusToggleSeekBar mSlider;
    public final CheckBox mToggle;
    public final ShapeDrawable mToggleBackground;
    public boolean mTracking;
    public ValueAnimator mValueAnimator;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.widget.OplusToggleSliderView$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements COUISeekBar.OnSeekBarChangeListener {
        public int mCurrentProgress;
        public /* synthetic */ OplusToggleSliderView this$0;

        public final void onProgressChanged(COUISeekBar cOUISeekBar, int i, boolean z) {
            OplusToggleSliderView oplusToggleSliderView = this.this$0;
            if (!oplusToggleSliderView.mTracking || z) {
                MirrorController mirrorController = oplusToggleSliderView.mMirrorController;
                if (mirrorController != null && mirrorController.getToggleSlider() != null) {
                    oplusToggleSliderView.mMirrorController.getToggleSlider().setValue(oplusToggleSliderView.mSlider.getProgress());
                }
                oplusToggleSliderView.setBrightnessIconAnimator(oplusToggleSliderView.mSlider.getProgress());
                COUISeekBar.OnSeekBarChangeListener onSeekBarChangeListener = oplusToggleSliderView.mSeekListener;
                if (onSeekBarChangeListener != null) {
                    OplusToggleSeekBar oplusToggleSeekBar = oplusToggleSliderView.mSlider;
                    onSeekBarChangeListener.onProgressChanged(oplusToggleSeekBar, oplusToggleSeekBar.getProgress(), z);
                }
                FlavorOneFeatureOption flavorOneFeatureOption = FlavorOneFeatureOption.INSTANCE;
                if (flavorOneFeatureOption.isBrightnessSecMaxSupported()) {
                    OplusBrightnessControllerEx oplusBrightnessControllerEx = oplusToggleSliderView.getOplusBrightnessControllerEx();
                    if (!oplusBrightnessControllerEx.isHighBrightness(oplusToggleSliderView.mSlider.getProgress())) {
                        if (flavorOneFeatureOption.isBrightnessSecMaxSupported()) {
                            oplusToggleSliderView.mBrightnessIcon.updateHighBrightnessState(false);
                            return;
                        }
                        return;
                    }
                    boolean z2 = oplusToggleSliderView.mSlider.getProgress() > this.mCurrentProgress;
                    this.mCurrentProgress = oplusToggleSliderView.mSlider.getProgress();
                    oplusToggleSliderView.mBrightnessIcon.updateHighBrightnessState(true);
                    if (oplusBrightnessControllerEx.isLongLanguage()) {
                        return;
                    }
                    oplusToggleSliderView.mHighBrightness.setVisibility(z2 ? 0 : 8);
                    oplusToggleSliderView.mHighBrightness.setText(2131952478);
                }
            }
        }

        public final void onStartTrackingTouch(COUISeekBar cOUISeekBar) {
            LogUtil.d(LogUtil.TAG_QS, "OplusToggleSliderView", "onStartTrackingTouch");
            OplusToggleSliderView oplusToggleSliderView = this.this$0;
            oplusToggleSliderView.mTracking = true;
            this.mCurrentProgress = oplusToggleSliderView.mSlider.getProgress();
            COUISeekBar.OnSeekBarChangeListener onSeekBarChangeListener = oplusToggleSliderView.mSeekListener;
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onStartTrackingTouch(oplusToggleSliderView.mSlider);
            }
        }

        public final void onStopTrackingTouch(COUISeekBar cOUISeekBar) {
            LogUtil.d(LogUtil.TAG_QS, "OplusToggleSliderView", "onStopTrackingTouch");
            OplusToggleSliderView oplusToggleSliderView = this.this$0;
            if (oplusToggleSliderView.mTracking) {
                oplusToggleSliderView.mTracking = false;
                TextView textView = oplusToggleSliderView.mHighBrightness;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                COUISeekBar.OnSeekBarChangeListener onSeekBarChangeListener = oplusToggleSliderView.mSeekListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStopTrackingTouch(oplusToggleSliderView.mSlider);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.widget.OplusToggleSliderView$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements View.OnTouchListener {
        public /* synthetic */ OplusToggleSliderView this$0;

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            this.this$0.mLongClickHandler.onTouch(view, motionEvent);
            if (this.this$0.mSlider.extracted()) {
                return true;
            }
            if (!this.this$0.isEnabled()) {
                this.this$0.setEnabled(true);
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                this.this$0.mSlider.performAccessibilityAction(64, null);
                this.this$0.mSlider.sendAccessibilityEvent(4);
                return false;
            }
            if (action != 1 && action != 3) {
                return false;
            }
            OplusToggleSliderView oplusToggleSliderView = this.this$0;
            if (oplusToggleSliderView.mTracking && oplusToggleSliderView.mMirrorController != null) {
                LogUtil.d(LogUtil.TAG_QS, "OplusToggleSliderView", "Stop manually sliding the brightness bar");
                this.this$0.mMirrorController.hideMirror();
            }
            this.this$0.mSlider.performAccessibilityAction(64, null);
            this.this$0.mSlider.sendAccessibilityEvent(4);
            return false;
        }
    }

    public static void $r8$lambda$igX4Djz0iLI9Pk8Tkm3WJ9GYG9M(OplusToggleSliderView oplusToggleSliderView) {
        if (oplusToggleSliderView.isAttachedToWindow()) {
            oplusToggleSliderView.setupSliderProgressDrawable(((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getPersonalityManagerEx().getLastShapeType());
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$oCpi7e1DHBNL6ogDxZaTBvUh-5A, reason: not valid java name */
    public static void m353$r8$lambda$oCpi7e1DHBNL6ogDxZaTBvUh5A(OplusToggleSliderView oplusToggleSliderView, Drawable drawable, boolean z) {
        boolean zIsPacManVersion = OpUtils.isPacManVersion();
        CheckBox checkBox = oplusToggleSliderView.mToggle;
        Context context = ((RelativeLayout) oplusToggleSliderView).mContext;
        if (zIsPacManVersion) {
            drawable.mutate().setTint(context.getColor(OpUtils.isGoogleDarkTheme(context) ? 2131102992 : 2131103678));
            checkBox.setForeground(drawable);
        } else {
            drawable.mutate().setTint(context.getColor(z ? 2131103677 : 2131103678));
            checkBox.setForeground(drawable);
        }
    }

    /* JADX WARN: Type inference failed for: r10v1, types: [com.oplus.systemui.qs.widget.OplusToggleSliderView$1] */
    public OplusToggleSliderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOutlineProvider = new ViewOutlineProvider() { // from class: com.oplus.systemui.qs.widget.OplusToggleSliderView.1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                int width = OplusToggleSliderView.this.mSlider.getWidth();
                int height = OplusToggleSliderView.this.mSlider.getHeight();
                OplusToggleSliderView oplusToggleSliderView = OplusToggleSliderView.this;
                outline.setRoundRect(0, 0, width, height, oplusToggleSliderView.getShapeRadius(oplusToggleSliderView.mShapeType));
            }
        };
        this.mLongClickHandler = new LocalLongClickHandler();
        this.mShapeType = 2;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        View.inflate(context, 2131559721, this);
        this.mRadius = ScreenUtils.dip2pxFloat(((RelativeLayout) this).mContext, 21.0f);
        View viewFindViewById = findViewById(2131365266);
        viewFindViewById.setOnTouchListener(anonymousClass3);
        COUISeekBarAdvanced cOUISeekBarAdvanced = (OplusToggleSeekBar) findViewById(2131365265);
        this.mSlider = cOUISeekBarAdvanced;
        int dimensionPixelSize = ((RelativeLayout) this).mContext.getResources().getDimensionPixelSize(2131172154);
        cOUISeekBarAdvanced.setSupportDeformation(true);
        cOUISeekBarAdvanced.setMaxHeightDeformed(dimensionPixelSize);
        cOUISeekBarAdvanced.setMaxMovingDistance(dimensionPixelSize);
        cOUISeekBarAdvanced.setMoveType(2);
        cOUISeekBarAdvanced.setMoveDamping(1.0f);
        cOUISeekBarAdvanced.setOnSeekBarChangeListener(anonymousClass2);
        cOUISeekBarAdvanced.setOnTouchListener(anonymousClass3);
        int color = ((RelativeLayout) this).mContext.getColor(2131103669);
        boolean z = getContext() instanceof BrightnessDialog;
        this.mIsInBrightnessDialog = z;
        int i2 = 0;
        boolean z2 = QsColorUtil.isIconNeedUseLightColor(((RelativeLayout) this).mContext, false) && !z;
        this.mIsIconNeedUseLightColor = z2;
        color = z2 ? QsColorUtil.BRIGHTNESS_SLIDER_BG_COLOR : color;
        LogUtil.i(LogUtil.TAG_QS, "OplusToggleSliderView", "bgColor=" + Integer.toHexString(color));
        OplusToggleSliderView$$ExternalSyntheticLambda1 oplusToggleSliderView$$ExternalSyntheticLambda1 = new OplusToggleSliderView$$ExternalSyntheticLambda1(i2);
        oplusToggleSliderView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        cOUISeekBarAdvanced.post(oplusToggleSliderView$$ExternalSyntheticLambda1);
        this.mShapeType = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getPersonalityManagerEx().getLastShapeType();
        cOUISeekBarAdvanced.setSeekBarBackgroundColor(ColorStateList.valueOf(color));
        cOUISeekBarAdvanced.setBackgroundRadius(getShapeRadius(this.mShapeType));
        cOUISeekBarAdvanced.setPaddingHorizontal(getShapeRadius(this.mShapeType));
        cOUISeekBarAdvanced.setAccessibilityLabel(getContentDescription().toString());
        cOUISeekBarAdvanced.setClickable(true);
        ViewCompat.setAccessibilityDelegate(cOUISeekBarAdvanced, new AccessibilityDelegateCompat() { // from class: com.oplus.systemui.qs.widget.OplusToggleSliderView.4
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                accessibilityNodeInfoCompat.setTraversalBefore(OplusToggleSliderView.this);
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            }
        });
        ClipBrightnessView clipBrightnessView = (ClipBrightnessView) findViewById(2131362347);
        this.mBrightnessIcon = clipBrightnessView;
        boolean z3 = QsSettingsValueProxy.getBrightnessMode(((RelativeLayout) this).mContext) != 0;
        if (z3 && QSFeatureOption.INSTANCE.getShouldRemoveAutoBrightness()) {
            clipBrightnessView.setAutoMode(true);
        } else {
            clipBrightnessView.setAutoMode(false);
        }
        if (FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported()) {
            this.mHighBrightness = (TextView) findViewById(2131363360);
        }
        CheckBox checkBox = (CheckBox) findViewById(2131362345);
        this.mToggle = checkBox;
        int width = checkBox.getWidth();
        ShapeDrawable shapeDrawable = new ShapeDrawable(((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getPersonalityManagerEx().getLastShape(((RelativeLayout) this).mContext));
        shapeDrawable.setIntrinsicWidth(width);
        shapeDrawable.setIntrinsicHeight(width);
        this.mToggleBackground = shapeDrawable;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
        if (QSFeatureOption.INSTANCE.getShouldRemoveAutoBrightness()) {
            layoutParams.setMarginEnd(0);
            checkBox.setVisibility(8);
            checkBox.setClickable(false);
        } else {
            layoutParams.setMarginEnd(getResources().getDimensionPixelSize(2131171553) - getResources().getDimensionPixelSize(2131171549));
            checkBox.setVisibility(0);
            checkBox.setClickable(true);
            checkBox.setChecked(z3);
            updateToggleBackground(z3, false);
            checkBox.setOnLongClickListener(new OplusToggleSliderView$$ExternalSyntheticLambda2());
        }
        viewFindViewById.setLayoutParams(layoutParams);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131171545);
            int dimensionPixelSize3 = getResources().getDimensionPixelSize(2131171546);
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("mIconPadding = ", dimensionPixelSize2, dimensionPixelSize3, "  sliderSidePadding = ", " config of OplusToggleSliderView =  ");
            sbM.append(((RelativeLayout) this).mContext.getResources().getConfiguration());
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusToggleSliderView", sbM.toString());
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams2.setMarginStart(dimensionPixelSize3 - getResources().getDimensionPixelSize(2131171549));
            viewFindViewById.setLayoutParams(layoutParams2);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) clipBrightnessView.getLayoutParams();
            layoutParams3.setMarginStart(dimensionPixelSize2);
            clipBrightnessView.setLayoutParams(layoutParams3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OplusBrightnessControllerEx getOplusBrightnessControllerEx() {
        if (this.mExt == null) {
            this.mExt = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getBrightnessControllerEx();
        }
        return this.mExt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBrightnessIconAnimator(int i) {
        int layoutDirection = getLayoutDirection();
        ClipBrightnessView clipBrightnessView = this.mBrightnessIcon;
        if (layoutDirection == 1) {
            clipBrightnessView.updateRotation(((this.mSlider.getMax() - i) / this.mSlider.getMax()) * 360.0f);
        } else {
            clipBrightnessView.updateRotation((i / this.mSlider.getMax()) * 360.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupSliderProgressDrawable(int i) {
        if (OpUtils.isPacManVersion() && (i == 2 || i == 4)) {
            this.mSlider.setProgressColor(ColorStateList.valueOf(((RelativeLayout) this).mContext.getColor(2131102994)));
            this.mSlider.setThumbColor(ColorStateList.valueOf(((RelativeLayout) this).mContext.getColor(2131102994)));
            return;
        }
        int i2 = this.mIsIconNeedUseLightColor ? 204 : 255;
        int i3 = (!QsColorUtil.isIconNeedUseLightColorWhenDarkMode(((RelativeLayout) this).mContext, false) || this.mIsInBrightnessDialog) ? 78 : 204;
        boolean z = this.mIsMirror;
        Context context = ((RelativeLayout) this).mContext;
        if (z) {
            boolean zIsNightMode = OplusDarkModeUtil.isNightMode(context);
            OplusToggleSeekBar oplusToggleSeekBar = this.mSlider;
            if (zIsNightMode) {
                oplusToggleSeekBar.setProgressColor(ColorStateList.valueOf(((RelativeLayout) this).mContext.getColor(2131103672)));
                this.mSlider.setThumbColor(ColorStateList.valueOf(0));
                return;
            } else {
                oplusToggleSeekBar.setProgressColor(ColorStateList.valueOf(ColorUtilKt.getColorWithAlpha(i2 / 255.0f, ((RelativeLayout) this).mContext.getColor(2131103673))));
                this.mSlider.setThumbColor(ColorStateList.valueOf(0));
                return;
            }
        }
        boolean zIsNightMode2 = OplusDarkModeUtil.isNightMode(context);
        OplusToggleSeekBar oplusToggleSeekBar2 = this.mSlider;
        if (zIsNightMode2) {
            oplusToggleSeekBar2.setProgressColor(ColorStateList.valueOf(ColorUtilKt.getColorWithAlpha(i3 / 255.0f, ((RelativeLayout) this).mContext.getColor(2131103673))));
            this.mSlider.setThumbColor(ColorStateList.valueOf(0));
        } else {
            oplusToggleSeekBar2.setProgressColor(ColorStateList.valueOf(ColorUtilKt.getColorWithAlpha(i2 / 255.0f, ((RelativeLayout) this).mContext.getColor(2131103673))));
            this.mSlider.setThumbColor(ColorStateList.valueOf(0));
        }
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("OplusToggleSliderView: ", "    height of mSlider: ", printWriter);
        COUISeekBar cOUISeekBar = this.mSlider;
        StringBuilder sbM2 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM, cOUISeekBar == null ? 0 : cOUISeekBar.getHeight(), printWriter, "    width of mSlider: ");
        COUISeekBar cOUISeekBar2 = this.mSlider;
        StringBuilder sbM3 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM2, cOUISeekBar2 == null ? 0 : cOUISeekBar2.getWidth(), printWriter, "    lp of mSlider: ");
        COUISeekBar cOUISeekBar3 = this.mSlider;
        StringBuilder sbM4 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM3, cOUISeekBar3 == null ? 0 : cOUISeekBar3.getLayoutParams().width, printWriter, "    mIsMirror: ");
        sbM4.append(this.mIsMirror);
        sbM4.append(" this:");
        sbM4.append(this);
        printWriter.println(sbM4.toString());
        printWriter.println("    height of OplusToggleSliderView: " + getHeight());
        printWriter.println("    width of OplusToggleSliderView: " + getWidth());
        StringBuilder sb = new StringBuilder("    lp of OplusToggleSliderView: ");
        sb.append(getLayoutParams() != null ? getLayoutParams().width : 0);
        printWriter.println(sb.toString());
        if (getResources() != null) {
            printWriter.println("    getResourcesIconPadding: " + getResources().getDimensionPixelSize(2131171545));
            printWriter.println("    getResourcesSliderSidePadding: " + getResources().getDimensionPixelSize(2131171546));
            printWriter.println("    config of OplusToggleSliderView: " + getResources().getConfiguration());
        }
        View viewFindViewById = findViewById(2131365266);
        if (viewFindViewById != null) {
            printWriter.println("    height of sliderContainer: " + viewFindViewById.getHeight());
            printWriter.println("    width of sliderContainer: " + viewFindViewById.getWidth());
            OplusLockIconView$.ExternalSyntheticOutline0.m(new StringBuilder("    lp of sliderContainer: "), viewFindViewById.getLayoutParams().width, printWriter);
        }
    }

    public int getMax() {
        return this.mSlider.getMax();
    }

    public View.OnLongClickListener getOnLongClickListener() {
        return this.mLongClickHandler.getLongClickListener();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0013  */
    /* JADX WARN: Code duplicated, block: B:15:0x0016  */
    /* JADX WARN: Code duplicated, block: B:16:0x0019  */
    public final float getShapeRadius(int i) {
        float f;
        if (i == 0) {
            f = 0.25f;
        } else if (i == 1) {
            f = 0.5f;
        } else if (i == 2) {
            f = 1.0f;
        } else if (i == 3) {
            f = 0.5f;
        } else if (i == 4) {
            f = 1.0f;
        } else if (i != 5) {
            f = 0.0f;
        } else {
            f = 0.25f;
        }
        return this.mRadius * f;
    }

    public int getValue() {
        return this.mSlider.getProgress();
    }

    @Override // android.view.View
    public final boolean hasOnLongClickListeners() {
        return getOnLongClickListener() != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getPersonalityManagerEx().addListener(this);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Resources resources = ((RelativeLayout) this).mContext.getResources();
        int i = 0;
        boolean z = OplusQSSmallSpaceController.isSmallSpace(((RelativeLayout) this).mContext) && OplusQSDisplayModeUtils.isBigDisplayMode(((RelativeLayout) this).mContext);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int dimensionPixelSize = resources.getDimensionPixelSize(z ? 2131171552 : 2131171551);
        if (layoutParams.height != dimensionPixelSize) {
            layoutParams.height = dimensionPixelSize;
            setLayoutParams(layoutParams);
        }
        View viewFindViewById = findViewById(2131365266);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
        if (!QSFeatureOption.INSTANCE.getShouldRemoveAutoBrightness()) {
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131171553);
            updateToggleBackground(QsSettingsValueProxy.getBrightnessMode(((RelativeLayout) this).mContext) != 0, false);
            i = dimensionPixelSize2;
        }
        if (i != layoutParams2.getMarginEnd()) {
            viewFindViewById.setLayoutParams(layoutParams2);
        }
        ClipBrightnessView clipBrightnessView = this.mBrightnessIcon;
        clipBrightnessView.init();
        clipBrightnessView.requestLayout();
        ViewGroup.LayoutParams layoutParams3 = this.mSlider.getLayoutParams();
        int dimensionPixelSize3 = resources.getDimensionPixelSize(z ? 2131165750 : 2131165749);
        if (layoutParams3.height != dimensionPixelSize3) {
            layoutParams3.height = dimensionPixelSize3;
            this.mSlider.setLayoutParams(layoutParams3);
        }
        this.mSlider.setBackgroundHeight(dimensionPixelSize3);
        this.mSlider.setBackgroundRadius(getShapeRadius(this.mShapeType));
        this.mSlider.setPaddingHorizontal(getShapeRadius(this.mShapeType));
        if (LogUtil.getNormalDebug()) {
            LogUtil.d(LogUtil.TAG_QS, "OplusToggleSliderView", "updateResource mSlider's height: " + this.mSlider.getHeight() + " width: " + this.mSlider.getWidth() + " mIsMirror: " + this.mIsMirror + " this: " + this);
        }
        ViewGroup.LayoutParams layoutParams4 = this.mToggle.getLayoutParams();
        if (layoutParams4.height != dimensionPixelSize) {
            layoutParams4.height = dimensionPixelSize;
            layoutParams4.width = dimensionPixelSize;
            this.mToggle.setLayoutParams(layoutParams4);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getPersonalityManagerEx().removeListener(this);
        this.mSlider.setOnSeekBarChangeListener((COUISeekBar.OnSeekBarChangeListener) null);
        OplusToggleSliderView$$ExternalSyntheticLambda1 oplusToggleSliderView$$ExternalSyntheticLambda1 = this.mSetupProgressDrawableRunnable;
        if (oplusToggleSliderView$$ExternalSyntheticLambda1 != null) {
            removeCallbacks(oplusToggleSliderView$$ExternalSyntheticLambda1);
            this.mSetupProgressDrawableRunnable = null;
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Gefingerpoken gefingerpoken = this.mOnInterceptListener;
        return gefingerpoken != null ? gefingerpoken.onInterceptTouchEvent(motionEvent) : super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Runnable runnable = this.mSetupProgressDrawableRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        OplusToggleSliderView$$ExternalSyntheticLambda1 oplusToggleSliderView$$ExternalSyntheticLambda1 = new OplusToggleSliderView$$ExternalSyntheticLambda1(1);
        oplusToggleSliderView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mSetupProgressDrawableRunnable = oplusToggleSliderView$$ExternalSyntheticLambda1;
        postDelayed(oplusToggleSliderView$$ExternalSyntheticLambda1, 1000L);
    }

    public final void onShapeChanged(int i) {
        this.mToggleBackground.setShape(((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getPersonalityManagerEx().getLastShape(((RelativeLayout) this).mContext));
        COUISeekBar cOUISeekBar = this.mSlider;
        Runnable externalSyntheticLambda4 = new OplusToggleSliderView$.ExternalSyntheticLambda4();
        ((OplusToggleSliderView$.ExternalSyntheticLambda4) externalSyntheticLambda4).f$0 = this;
        ((OplusToggleSliderView$.ExternalSyntheticLambda4) externalSyntheticLambda4).f$1 = i;
        VarHandle.storeStoreFence();
        cOUISeekBar.post(externalSyntheticLambda4);
        this.mShapeType = i;
        if (this.mIsMirror) {
            this.mSlider.invalidateOutline();
        }
        this.mSlider.setBackgroundRadius(getShapeRadius(this.mShapeType));
        this.mSlider.setPaddingHorizontal(getShapeRadius(this.mShapeType));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent viewParent = ((RelativeLayout) this).mParent;
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public void setAutoBrightness(boolean z) {
        if (QSFeatureOption.INSTANCE.getShouldRemoveAutoBrightness()) {
            this.mBrightnessIcon.setAutoMode(z);
        } else if (this.mToggle.isChecked() != z) {
            this.mToggle.setChecked(z);
            updateToggleBackground(z, true);
        }
    }

    public void setEnforcedAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mToggle.setEnabled(enforcedAdmin == null);
        this.mSlider.setEnabled(enforcedAdmin == null);
        this.mSlider.setEnforcedAdmin(enforcedAdmin);
    }

    @Override // android.view.View
    public void setLongClickable(boolean z) {
        this.mLongClickHandler.setEnable(z);
    }

    public void setMax(int i) {
        this.mSlider.setMax(i);
    }

    public void setMirrorControllerAndMirror(MirrorController mirrorController) {
        this.mMirrorController = mirrorController;
    }

    public void setOnDispatchTouchEventListener(DispatchTouchEventListener dispatchTouchEventListener) {
    }

    public void setOnInterceptListener(Gefingerpoken gefingerpoken) {
        this.mOnInterceptListener = gefingerpoken;
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mLongClickHandler.setLongClickListener(onLongClickListener);
    }

    public void setOnSeekBarChangeListener(COUISeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mSeekListener = onSeekBarChangeListener;
    }

    public void setOnToggleCheckListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.mToggle.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public void setValue(int i) {
        setBrightnessIconAnimator(i);
        this.mSlider.setProgress(i);
    }

    public final void setValue$1(int i) {
        setBrightnessIconAnimator(i);
        this.mSlider.setProgress(i, true, false);
    }

    public final void updateToggleBackground(boolean z, boolean z2) {
        Drawable drawable = ((RelativeLayout) this).mContext.getDrawable(2131237284);
        int activeColorWithDarkMode = QSFragmentHelper.getInstance().getActiveColorWithDarkMode(((RelativeLayout) this).mContext);
        boolean z3 = ((RelativeLayout) this).mContext.getResources().getBoolean(2131034330);
        if (activeColorWithDarkMode == 0 || z3) {
            activeColorWithDarkMode = ((RelativeLayout) this).mContext.getColor(2131103674);
        }
        int color = ((RelativeLayout) this).mContext.getColor(2131103675);
        if (this.mIsIconNeedUseLightColor) {
            color = QsColorUtil.QS_TILE_BG_INACTIVE_COLOR;
        }
        ShapeDrawable shapeDrawable = this.mToggleBackground;
        if (z) {
            shapeDrawable.getPaint().setColor(activeColorWithDarkMode);
            int i = color;
            color = activeColorWithDarkMode;
            activeColorWithDarkMode = i;
        } else {
            shapeDrawable.getPaint().setColor(color);
        }
        if (drawable != null) {
            Runnable runnable = this.mIconChangeRunnable;
            if (runnable != null) {
                removeCallbacks(runnable);
            }
            OplusToggleSliderView$$ExternalSyntheticLambda0 oplusToggleSliderView$$ExternalSyntheticLambda0 = new OplusToggleSliderView$$ExternalSyntheticLambda0();
            oplusToggleSliderView$$ExternalSyntheticLambda0.f$0 = this;
            oplusToggleSliderView$$ExternalSyntheticLambda0.f$1 = drawable;
            oplusToggleSliderView$$ExternalSyntheticLambda0.f$2 = z;
            VarHandle.storeStoreFence();
            this.mIconChangeRunnable = oplusToggleSliderView$$ExternalSyntheticLambda0;
            postDelayed(oplusToggleSliderView$$ExternalSyntheticLambda0, 50L);
        }
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z2) {
            this.mToggle.setBackground(this.mToggleBackground);
            return;
        }
        ObjectAnimator objectAnimatorOfArgb = ObjectAnimator.ofArgb(this.mToggleBackground.getPaint(), "color", activeColorWithDarkMode, color);
        this.mValueAnimator = objectAnimatorOfArgb;
        objectAnimatorOfArgb.setDuration(z ? 250L : 200L);
        this.mValueAnimator.setInterpolator(ICON_COLOR_TRANSITION_INTERPOLATOR);
        ValueAnimator valueAnimator2 = this.mValueAnimator;
        5 r9 = new 5();
        r9.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator2.addUpdateListener(r9);
        this.mValueAnimator.start();
    }

    public final void updateViewState(boolean z) {
        if (!QSFeatureOption.INSTANCE.getShouldRemoveAutoBrightness() && z) {
            this.mToggle.setOnLongClickListener(null);
            this.mToggle.setClickable(false);
            this.mToggle.setLongClickable(false);
        }
        if (z) {
            int lastShapeType = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getPersonalityManagerEx().getLastShapeType();
            this.mShapeType = lastShapeType;
            setupSliderProgressDrawable(lastShapeType);
            boolean zIsNightMode = OplusDarkModeUtil.isNightMode(((RelativeLayout) this).mContext);
            OplusToggleSeekBar oplusToggleSeekBar = this.mSlider;
            Context context = ((RelativeLayout) this).mContext;
            if (zIsNightMode) {
                oplusToggleSeekBar.setSeekBarBackgroundColor(ColorStateList.valueOf(context.getColor(2131103671)));
            } else {
                oplusToggleSeekBar.setSeekBarBackgroundColor(ColorStateList.valueOf(ColorUtilKt.getColorWithAlpha(0.9019608f, context.getColor(2131103671))));
            }
            this.mSlider.setBackgroundRadius(getShapeRadius(this.mShapeType));
            this.mSlider.setPaddingHorizontal(getShapeRadius(this.mShapeType));
            this.mSlider.setOutlineProvider(this.mOutlineProvider);
        }
        this.mIsMirror = z;
    }

    public OplusToggleSliderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OplusToggleSliderView(Context context) {
        this(context, null);
    }
}
