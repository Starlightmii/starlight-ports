package com.oplus.systemui.qs.base.tile;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextSwitcher;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.sqlite.SQLite__SQLiteKt$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.LaunchableViewDelegate;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.model.InterestingViewFlag;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.util.OplusQSFontHelper;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OnePlusQsColorUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.view.TextSwitcherExtKt;
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusQSHighlightTileView extends QSTileView implements LaunchableView, IQSSpotLightConfig {
    private static final String BED_TIME = "custom(com.google.android.apps.wellbeing/.screen.ui.GrayscaleTileService)";
    private static final String BLUETOOTH = "bt";
    private static final String CAMERA_ACCESS = "cameratoggle";
    private static final String CELL = "cell";
    public static final Companion Companion = new Companion();
    private static final String DND = "dnd";
    private static final String HOTSPOT = "hotspot";
    private static final String MIC_ACCESS = "mictoggle";
    private static final String TAG = "OplusQSHighlightTileViewImpl";
    private static final String WIFI = "wifi";
    private LaunchableViewDelegate delegate;
    private final QSIconView iconView;
    private final InterestingViewFlag interestingViewFlag;
    private String mAccessibilityClass;
    private final View mBg;
    private final TileDrawableWrapper mBgDrawable;
    public int mBgHeight;
    public int mBgSize;
    public boolean mClicked;
    private ColorStateList mColorLabelActive;
    private ColorStateList mColorLabelInActive;
    private ColorStateList mColorLabelUnavailable;
    private ColorStateList mColorSecondLabelActive;
    private ImageView mExpandIndicator;
    public boolean mHasDetachedFromWindow;
    private final FrameLayout mIconFrame;
    public int mIconSize;
    private LinearLayout mIndicatorContainer;
    public boolean mIsCollapsedView;
    public boolean mIsDualTarget;
    private TextSwitcher mLabel;
    private ViewGroup mLabelContainer;
    private LinearLayout mLabelGroup;
    public int mLastFlipFont;
    private final int[] mLocInScreen;
    private ImageView mPadLock;
    public int mState;
    private QSTile.State mTempState;
    public int mTileIconState;
    public boolean mTileState;
    private final Handler mainHandler;
    private final PressFeedbackHelper.QsTilePressFeedback tilePressFeedback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StateChangeRunnable implements Runnable {
        private final QSTile.State state;

        public StateChangeRunnable(QSTile.State state) {
            this.state = state;
        }

        public boolean equals(Object obj) {
            return obj instanceof StateChangeRunnable;
        }

        public final int hashCode() {
            return Reflection.getOrCreateKotlinClass(StateChangeRunnable.class).hashCode();
        }

        @Override // java.lang.Runnable
        public final void run() {
            OplusQSHighlightTileView oplusQSHighlightTileView = OplusQSHighlightTileView.this;
            QSTile.State state = this.state;
            QSTile.State stateCopy = oplusQSHighlightTileView.mTempState;
            if (stateCopy == null) {
                stateCopy = state.copy();
            }
            if (OplusQSHighlightTileView.this.mTempState == null) {
                OplusQSHighlightTileView.this.mTempState = stateCopy;
            } else {
                state.copyTo(stateCopy);
            }
            oplusQSHighlightTileView.handleStateChanged(stateCopy);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView$init$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements View.OnClickListener {
        public /* synthetic */ Object $expandable;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object $tile;

        public /* synthetic */ AnonymousClass3(int i) {
            this.$r8$classId = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            View.OnClickListener onClickListener;
            switch (this.$r8$classId) {
                case 0:
                    ((QSTile) this.$tile).click((Expandable) this.$expandable);
                    break;
                default:
                    if (((OplusQSHighlightTileView) this.$tile).mIsDualTarget && (onClickListener = (View.OnClickListener) this.$expandable) != null) {
                        onClickListener.onClick(view);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView$init$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements View.OnClickListener {
        public /* synthetic */ Expandable $expandable;
        public /* synthetic */ View $secondaryClickHostView;
        public /* synthetic */ QSTile $tile;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
                return;
            }
            this.$tile.secondaryClick(this.$expandable);
            View view2 = this.$secondaryClickHostView;
            if (view2 != null) {
                ViewExtKt.setPerformClickedFlag(view2, true);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView$init$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements View.OnLongClickListener {
        public /* synthetic */ Expandable $expandable;
        public /* synthetic */ QSTile $tile;

        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
                return true;
            }
            this.$tile.longClick(this.$expandable);
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit $r8$lambda$S6o5XjawFiDTDRDJhAYE36Blkdw(OplusQSHighlightTileView oplusQSHighlightTileView, int i) {
        super/*android.widget.LinearLayout*/.setVisibility(i);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSHighlightTileView(Context context, QSIconView qSIconView, boolean z) {
        super(context);
        this.iconView = qSIconView;
        FrameLayout frameLayout = new FrameLayout(context);
        this.mIconFrame = frameLayout;
        this.mLocInScreen = new int[2];
        this.mTileIconState = 1;
        TileDrawableWrapper tileDrawableWrapper = new TileDrawableWrapper(null);
        this.mBgDrawable = tileDrawableWrapper;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.interestingViewFlag = new InterestingViewFlag();
        setId(LinearLayout.generateViewId());
        setClipChildren(false);
        setClipToPadding(false);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        setClickable(true);
        setOrientation(0);
        setGravity(8388611);
        this.mIsCollapsedView = z;
        frameLayout.setDuplicateParentStateEnabled(true);
        setBgSize();
        addView(frameLayout, new LinearLayout.LayoutParams(-1, this.mBgHeight));
        int tileSize = getTileSize();
        float f = tileSize != 0 ? (this.mBgSize * 1.0f) / tileSize : 1.0f;
        qSIconView.setScaleX(f);
        qSIconView.setScaleY(f);
        int i = this.mIconSize;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i, 8388627);
        layoutParams.setMarginStart(getIconMarginStart());
        frameLayout.addView((View) qSIconView, (ViewGroup.LayoutParams) layoutParams);
        frameLayout.setClipChildren(false);
        frameLayout.setClipToPadding(false);
        setPadding(0, 0, 0, 0);
        setClipChildren(false);
        setClipToPadding(false);
        setFocusable(true);
        ImageView imageView = new ImageView(getContext());
        imageView.setDuplicateParentStateEnabled(true);
        imageView.setId(2131364808);
        imageView.setBackground(tileDrawableWrapper);
        frameLayout.addView(imageView, 0, new FrameLayout.LayoutParams(-1, -1));
        this.mBg = imageView;
        initLabelColor();
        Configuration configuration = getResources().getConfiguration();
        if (configuration != null) {
            this.mLastFlipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
        }
        TextSwitcher textSwitcher = this.mLabel;
        if (textSwitcher != null) {
            TextSwitcherExtKt.setTextViewTypeface(textSwitcher, Typeface.DEFAULT);
        }
        Context context2 = getContext();
        ImageView imageView2 = new ImageView(context2);
        this.mPadLock = imageView2;
        imageView2.setVisibility(8);
        imageView2.setImageResource(2131234068);
        imageView2.setImageTintList(context2.getColorStateList(2131103759));
        int dimensionPixelSize = context2.getResources().getDimensionPixelSize(2131172022);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        layoutParams2.gravity = 8388661;
        int i2 = dimensionPixelSize / 2;
        layoutParams2.setMarginsRelative(0, i2, i2, 0);
        frameLayout.addView(imageView2, layoutParams2);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(2131559286, (ViewGroup) this, false);
        this.mLabelContainer = viewGroup;
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        this.mLabel = (TextSwitcher) viewGroup.findViewById(2131365664);
        this.mPadLock = (ImageView) viewGroup.findViewById(2131364898);
        this.mIndicatorContainer = (LinearLayout) viewGroup.findViewById(2131363083);
        ImageView imageView3 = (ImageView) viewGroup.findViewById(2131363082);
        this.mExpandIndicator = imageView3;
        if (imageView3 != null) {
            imageView3.setContentDescription(getContext().getString(R.string.fingerprint_error_hw_not_present));
        }
        this.mLabelGroup = (LinearLayout) viewGroup.findViewById(2131363624);
        int titleMarginStart = getTitleMarginStart();
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -1);
        layoutParams3.setMarginStart(titleMarginStart);
        frameLayout.addView(this.mLabelContainer, layoutParams3);
        updateResource$1();
        PressFeedbackHelper.QsTilePressFeedback qsTilePressFeedback = new PressFeedbackHelper.QsTilePressFeedback(this);
        this.tilePressFeedback = qsTilePressFeedback;
        PressFeedbackHelper.attachPressFeedback((View) this, qsTilePressFeedback);
    }

    public static final void access$handleQsColorStateChanged(OplusQSHighlightTileView oplusQSHighlightTileView, QSTile.State state) {
        if (state != null) {
            oplusQSHighlightTileView.iconView.updateIconColorImmediately();
            oplusQSHighlightTileView.updateLabelColor();
            oplusQSHighlightTileView.updateIndicatorColor();
        }
    }

    public static void setLabelContent(TextSwitcher textSwitcher, CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return;
        }
        TextSwitcherExtKt.setContent(textSwitcher, charSequence);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        super/*android.widget.LinearLayout*/.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        SharedSpotLightEffect.onTouchEvent(this, motionEvent);
        return super/*android.widget.LinearLayout*/.dispatchTouchEvent(motionEvent);
    }

    public final View getBackgroundView() {
        return this.mBg;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getDetailY() {
        return (getHeight() / 2) + getTop();
    }

    public QSIconView getIcon() {
        return this.iconView;
    }

    public int getIconMarginStart() {
        return 0;
    }

    public final InterestingViewFlag getInterestingViewFlag() {
        return this.interestingViewFlag;
    }

    public View getLabel() {
        return this.mLabel;
    }

    public final int getMBgHeight() {
        return this.mBgHeight;
    }

    public final int getMBgSize() {
        return this.mBgSize;
    }

    public final int getMIconSize() {
        return this.mIconSize;
    }

    public final ImageView getMPadLock() {
        return this.mPadLock;
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super.getPaddingForLaunchAnimation();
    }

    public View getSecondaryClickHostView() {
        return this.mIndicatorContainer;
    }

    public int getSpotLightType() {
        return 2;
    }

    public int getTileSize() {
        return 0;
    }

    public int getTitleMarginStart() {
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:117:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:119:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:143:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:52:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:54:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:55:0x00d9  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    public final void handleStateChanged(QSTile.State state) {
        boolean z;
        ColorStateList colorStateList;
        CharSequence charSequence;
        CharSequence charSequence2;
        boolean z2;
        String str = state.spec;
        if (str != null && str.length() != 0 && this.mClicked) {
            String str2 = state.spec;
            if (Intrinsics.areEqual(str2, "wifi")) {
                JankUtils.wlanSwitchSceneEnd(getContext());
            } else if (Intrinsics.areEqual(str2, "bt")) {
                JankUtils.bluetoothSwitchSceneEnd(getContext());
            }
        }
        setClickable(state.state != 0);
        setLongClickable(state.handlesLongClick);
        setContentDescription(state.contentDescription);
        setStateDescription(getResources().getString(state.state == 2 ? 2131956739 : 2131956738));
        int i = state.state;
        this.mAccessibilityClass = i == 0 ? null : state.expandedAccessibilityClassName;
        if ((state instanceof QSTile.BooleanState) && this.mTileState != (z2 = ((QSTile.AdapterState) ((QSTile.BooleanState) state)).value)) {
            this.mClicked = false;
            this.mTileState = z2;
        }
        this.mTileIconState = i;
        TextSwitcher textSwitcher = this.mLabel;
        if (textSwitcher != null) {
            if (!Intrinsics.areEqual(TextSwitcherExtKt.getCurrentText(textSwitcher), state.label) || this.mState != state.state) {
                int i2 = state.state;
                if (i2 == 0) {
                    if (QsColorUtil.isNeedDarkThemeColor(getContext(), false)) {
                        colorStateList = getContext().getColorStateList(2131103291);
                    } else {
                        colorStateList = this.mColorLabelUnavailable;
                    }
                    TextSwitcherExtKt.setTextColor(textSwitcher, colorStateList);
                } else if (i2 == 1) {
                    TextSwitcherExtKt.setTextColor(textSwitcher, QsColorUtil.isNeedDarkThemeColor(getContext(), false) ? getContext().getColorStateList(2131103292) : this.mColorLabelInActive);
                } else if (i2 == 2) {
                    TextSwitcherExtKt.setTextColor(textSwitcher, this.mColorLabelActive);
                } else if (i2 == 101) {
                    if (QsColorUtil.isNeedDarkThemeColor(getContext(), false)) {
                        colorStateList = getContext().getColorStateList(2131103291);
                    } else {
                        colorStateList = this.mColorLabelUnavailable;
                    }
                    TextSwitcherExtKt.setTextColor(textSwitcher, colorStateList);
                }
                this.mState = state.state;
                setLabelContent(textSwitcher, state.label);
                textSwitcher.setSelected((TextUtils.isEmpty(state.label) || this.mIsCollapsedView) ? false : true);
            }
            String str3 = state.spec;
            if (str3 != null) {
                switch (str3.hashCode()) {
                    case -37334949:
                        if (str3.equals("mictoggle")) {
                            if (state.state == 1) {
                                setLabelContent(textSwitcher, state.secondaryLabel);
                            }
                        }
                        break;
                    case 3154:
                        if (str3.equals("bt") && state.state == 2) {
                            setLabelContent(textSwitcher, state.labelDesc);
                        }
                        break;
                    case 99610:
                        if (str3.equals("dnd") && state.state == 2) {
                            setLabelContent(textSwitcher, state.secondaryLabel);
                        }
                        break;
                    case 3049826:
                        if (str3.equals("cell") && state.state == 0 && ((charSequence = state.label) == null || charSequence.length() == 0)) {
                            setLabelContent(textSwitcher, state.secondaryLabel);
                        }
                        break;
                    case 3649301:
                        if (str3.equals("wifi") && (charSequence2 = state.secondaryLabel) != null && charSequence2.equals(getResources().getString(2131955876))) {
                            setLabelContent(textSwitcher, charSequence2);
                        }
                        break;
                    case 6344377:
                        if (str3.equals("cameratoggle")) {
                            if (state.state == 1) {
                                setLabelContent(textSwitcher, state.secondaryLabel);
                            }
                        }
                        break;
                    case 472658989:
                        if (str3.equals("custom(com.google.android.apps.wellbeing/.screen.ui.GrayscaleTileService)") && state.state == 2) {
                            setLabelContent(textSwitcher, state.secondaryLabel);
                        }
                        break;
                    case 1099603663:
                        if (str3.equals("hotspot") && state.state == 2) {
                            setLabelContent(textSwitcher, state.secondaryLabel);
                        }
                        break;
                }
            }
        }
        boolean z3 = state.dualTarget;
        ImageView imageView = this.mExpandIndicator;
        if (imageView != null) {
            imageView.setVisibility(z3 ? 0 : 8);
            updateIndicatorColor();
        }
        boolean z4 = state.dualTarget;
        ViewGroup viewGroup = this.mLabelContainer;
        if (viewGroup != null) {
            viewGroup.setContentDescription(z4 ? state.dualLabelContentDescription : null);
        }
        TextSwitcher textSwitcher2 = this.mLabel;
        if (textSwitcher2 != null) {
            textSwitcher2.setEnabled(!state.disabledByPolicy);
        }
        ImageView imageView2 = this.mPadLock;
        if (imageView2 != null) {
            imageView2.setVisibility(state.disabledByPolicy ? 0 : 8);
        }
        refreshDrawableState();
        QSIconView icon = getIcon();
        if (isShown() && getAlpha() == 1.0f) {
            getLocationOnScreen(this.mLocInScreen);
            z = this.mLocInScreen[1] >= (-getHeight());
        }
        icon.setIcon(state, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void init(QSTile qSTile) {
        Expandable expandableFromView = Expandable.Companion.fromView(this);
        View secondaryClickHostView = getSecondaryClickHostView();
        int i = 0;
        if (secondaryClickHostView != null) {
            OplusQSHighlightTileView$$ExternalSyntheticLambda0 oplusQSHighlightTileView$$ExternalSyntheticLambda0 = new OplusQSHighlightTileView$$ExternalSyntheticLambda0(0);
            oplusQSHighlightTileView$$ExternalSyntheticLambda0.f$0 = qSTile;
            oplusQSHighlightTileView$$ExternalSyntheticLambda0.f$1 = expandableFromView;
            VarHandle.storeStoreFence();
            OplusQSHighlightTileView$$ExternalSyntheticLambda0 oplusQSHighlightTileView$$ExternalSyntheticLambda1 = new OplusQSHighlightTileView$$ExternalSyntheticLambda0(1);
            oplusQSHighlightTileView$$ExternalSyntheticLambda1.f$0 = qSTile;
            oplusQSHighlightTileView$$ExternalSyntheticLambda1.f$1 = expandableFromView;
            VarHandle.storeStoreFence();
            ViewExtKt.preloadAndRelease(secondaryClickHostView, oplusQSHighlightTileView$$ExternalSyntheticLambda0, oplusQSHighlightTileView$$ExternalSyntheticLambda1);
        }
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(i);
        anonymousClass3.$tile = qSTile;
        anonymousClass3.$expandable = expandableFromView;
        VarHandle.storeStoreFence();
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        anonymousClass4.$tile = qSTile;
        anonymousClass4.$expandable = expandableFromView;
        anonymousClass4.$secondaryClickHostView = secondaryClickHostView;
        VarHandle.storeStoreFence();
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.$tile = qSTile;
        anonymousClass5.$expandable = expandableFromView;
        VarHandle.storeStoreFence();
        init(anonymousClass3, anonymousClass4, anonymousClass5);
        if (!qSTile.isTileReady() || qSTile.getState() == null) {
            return;
        }
        onStateChanged(qSTile.getState());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void initLabelColor() {
        this.mColorLabelUnavailable = getContext().getColorStateList(2131103710);
        this.mColorLabelActive = (!OnePlusQsColorUtil.INSTANCE.getEnableTwoToneTheme() || ScrimUtil.isLowGaussianLevel(getContext())) ? getContext().getColorStateList(2131103707) : getContext().getColorStateList(2131102960);
        this.mColorLabelInActive = getContext().getColorStateList(2131103706);
        this.mColorSecondLabelActive = getContext().getColorStateList(2131103712);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.LinearLayout*/.onAttachedToWindow();
        this.mHasDetachedFromWindow = false;
        List<SpanSize> list = QSConstant.highlightTileType;
        onOutlineUpdate(list);
        onDrawableUpdate(list, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        if (configuration != null) {
            super/*android.widget.LinearLayout*/.onConfigurationChanged(configuration);
            int flipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
            if (flipFont != this.mLastFlipFont) {
                this.mLastFlipFont = flipFont;
                TextSwitcher textSwitcher = this.mLabel;
                if (textSwitcher != null) {
                    TextSwitcherExtKt.setTextViewTypeface(textSwitcher, Typeface.DEFAULT);
                }
            }
        }
        setBgSize();
        this.mIconFrame.getLayoutParams().height = this.mBgHeight;
        int tileSize = getTileSize();
        float f = tileSize != 0 ? (this.mBgSize * 1.0f) / tileSize : 1.0f;
        this.iconView.setScaleX(f);
        this.iconView.setScaleY(f);
        updateResource$1();
        FlavorTwoFeatureOption.INSTANCE.getClass();
        if (FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
            updateLabelColor();
            updateIndicatorColor();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int[] onCreateDrawableState(int i) {
        InterestingViewFlag interestingViewFlag = getInterestingViewFlag();
        int[] drawableState = interestingViewFlag.getDrawableState();
        InterestingViewFlag interestingViewFlag2 = getInterestingViewFlag();
        QSTile.State state = this.mTempState;
        if (!interestingViewFlag2.setTileState(state != null ? state.state : 1) && drawableState != null) {
            return drawableState;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onCreateDrawableState: viewFlag=" + interestingViewFlag, null, 8);
        int[] iArrOnCreateDrawableState = super/*android.widget.LinearLayout*/.onCreateDrawableState(i + 1);
        int tileState = interestingViewFlag.getTileState();
        if (tileState == 0) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Unavailable.getFlag());
        } else if (tileState == 2) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Active.getFlag());
        } else if (tileState != 101) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Inactive.getFlag());
        } else {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Inoperable.getFlag());
        }
        interestingViewFlag.setDrawableState(iArrOnCreateDrawableState);
        iArrOnCreateDrawableState.getClass();
        return iArrOnCreateDrawableState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        super/*android.widget.LinearLayout*/.onDetachedFromWindow();
        this.mHasDetachedFromWindow = true;
        this.mainHandler.removeCallbacksAndMessages(null);
    }

    public void onDrawableUpdate(List<SpanSize> list, boolean z) {
        if (list.contains(SpanCache.TWO_X_ONE)) {
            this.mBgDrawable.setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) StdQSResPool.highlightTileViewDrawable.getValue(), this.mBg, false, 6));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super/*android.widget.LinearLayout*/.onInitializeAccessibilityEvent(accessibilityEvent);
        if (TextUtils.isEmpty(this.mAccessibilityClass)) {
            return;
        }
        accessibilityEvent.setClassName(this.mAccessibilityClass);
        if (Switch.class.getName().equals(this.mAccessibilityClass)) {
            boolean z = this.mClicked;
            boolean z2 = this.mTileState;
            if (z) {
                z2 = !z2;
            }
            accessibilityEvent.setContentDescription(getResources().getString(z2 ? 2131956739 : 2131956738));
            accessibilityEvent.setChecked(z2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super/*android.widget.LinearLayout*/.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        boolean z = false;
        accessibilityNodeInfo.setSelected(false);
        if (TextUtils.isEmpty(this.mAccessibilityClass)) {
            return;
        }
        accessibilityNodeInfo.setClassName(this.mAccessibilityClass);
        if (Switch.class.getName().equals(this.mAccessibilityClass)) {
            boolean z2 = this.mClicked;
            boolean z3 = this.mTileState;
            if (!z2) {
                z = z3;
            } else if (!z3) {
                z = true;
            }
            accessibilityNodeInfo.setText(getResources().getString(z ? 2131956739 : 2131956738));
            accessibilityNodeInfo.setChecked(z);
            accessibilityNodeInfo.setCheckable(true);
            if (isLongClickable()) {
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK.getId(), getResources().getString(2131951846)));
            }
        }
    }

    public void onOutlineUpdate(List<SpanSize> list) {
        if (list.contains(SpanCache.TWO_X_ONE)) {
            this.mBgDrawable.setPathProvider((CornerOutlineProvider) StdQSResPool.highLightTileOutline.getValue());
        }
    }

    public void onQsColorStateChanged(QSTile.State state) {
        onQsColorStateChanged.1 r0 = new onQsColorStateChanged.1();
        r0.this$0 = this;
        r0.$state = state;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onStateChanged(QSTile.State state) {
        if (this.mHasDetachedFromWindow) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "onStateChanged when has detached from window", null, 8);
            return;
        }
        QSTile.State stateCopy = state.copy();
        boolean z = this.mIsDualTarget;
        boolean z2 = stateCopy.dualTarget;
        if (z != z2) {
            this.mIsDualTarget = z2;
        }
        StateChangeRunnable stateChangeRunnable = new StateChangeRunnable(stateCopy);
        removeCallbacks(stateChangeRunnable);
        post(stateChangeRunnable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean performClick() {
        this.mClicked = true;
        return super/*android.widget.LinearLayout*/.performClick();
    }

    public void setBgSize() {
    }

    public final void setCollapsedView(boolean z) {
        this.mIsCollapsedView = !z;
    }

    public final void setExpandFraction(float f) {
        TextSwitcher textSwitcher = this.mLabel;
        if (textSwitcher != null) {
            TextSwitcherExtKt.setTextViewSelected(textSwitcher, f == 1.0f);
        }
    }

    public void setListening(boolean z) {
        this.tilePressFeedback.reset();
    }

    public final void setMBgHeight(int i) {
        this.mBgHeight = i;
    }

    public final void setMBgSize(int i) {
        this.mBgSize = i;
    }

    public final void setMIconSize(int i) {
        this.mIconSize = i;
    }

    public final void setMPadLock(ImageView imageView) {
        this.mPadLock = imageView;
    }

    public void setPosition(int i) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setShouldBlockVisibilityChanges(boolean z) {
        if (z && this.delegate == null) {
            OplusQSHighlightTileView$$ExternalSyntheticLambda2 oplusQSHighlightTileView$$ExternalSyntheticLambda2 = new OplusQSHighlightTileView$$ExternalSyntheticLambda2();
            oplusQSHighlightTileView$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            this.delegate = new LaunchableViewDelegate(this, oplusQSHighlightTileView$$ExternalSyntheticLambda2);
        }
        LaunchableViewDelegate launchableViewDelegate = this.delegate;
        if (launchableViewDelegate != null) {
            launchableViewDelegate.setShouldBlockVisibilityChanges(z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setVisibility(int i) {
        LaunchableViewDelegate launchableViewDelegate = this.delegate;
        if (launchableViewDelegate != null) {
            launchableViewDelegate.setVisibility(i);
        } else {
            super/*android.widget.LinearLayout*/.setVisibility(i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append('[');
        int[] iArr = this.mLocInScreen;
        sb.append(MutableVectorKt$.ExternalSyntheticOutline0.m("locInScreen=(", iArr[0], iArr[1], ", ", ")"));
        sb.append(", iconView=" + this.iconView);
        sb.append(", tileState=" + this.mTileState);
        SQLite__SQLiteKt$.ExternalSyntheticOutline0.m(this.mTileIconState, ", mTileIconState=", sb);
        SQLite__SQLiteKt$.ExternalSyntheticOutline0.m(this.mBgSize, ", mBgSize=", sb);
        SQLite__SQLiteKt$.ExternalSyntheticOutline0.m(this.mBgHeight, ", mBgHeight=", sb);
        Drawable background = this.mIconFrame.getBackground();
        if (background instanceof GradientDrawable) {
            ColorStateList color = ((GradientDrawable) background).getColor();
            sb.append(", bgColor=" + (color != null ? Integer.toHexString(color.getDefaultColor()) : null));
        } else if (background instanceof ShapeDrawable) {
            Paint paint = ((ShapeDrawable) background).getPaint();
            sb.append(", bgColor=" + (paint != null ? Integer.toHexString(paint.getColor()) : null));
        }
        ColorStateList colorStateList = this.mColorLabelUnavailable;
        sb.append(", mColorLabelUnavailable=" + (colorStateList != null ? Integer.toHexString(colorStateList.getDefaultColor()) : null));
        ColorStateList colorStateList2 = this.mColorLabelActive;
        sb.append(", mColorLabelActive=" + (colorStateList2 != null ? Integer.toHexString(colorStateList2.getDefaultColor()) : null));
        ColorStateList colorStateList3 = this.mColorLabelInActive;
        sb.append(", mColorLabelInActive=" + (colorStateList3 != null ? Integer.toHexString(colorStateList3.getDefaultColor()) : null));
        ColorStateList colorStateList4 = this.mColorSecondLabelActive;
        sb.append(", mColorSecondLabelActive=" + (colorStateList4 != null ? Integer.toHexString(colorStateList4.getDefaultColor()) : null));
        sb.append("]");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View updateAccessibilityOrder(View view) {
        setAccessibilityTraversalAfter(view != null ? view.getId() : 0);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateIndicatorColor() {
        ImageView imageView = this.mExpandIndicator;
        if (imageView != null) {
            imageView.setImageResource(2131236321);
        }
        ImageView imageView2 = this.mExpandIndicator;
        if (imageView2 == null || imageView2.getVisibility() != 0) {
            return;
        }
        int i = this.mTileIconState;
        if (i == 1) {
            boolean zIsNeedDarkThemeColor = QsColorUtil.isNeedDarkThemeColor(getContext(), false);
            ColorStateList colorStateList = getContext().getColorStateList(zIsNeedDarkThemeColor ? 2131103292 : 2131103706);
            ColorStateList colorStateList2 = getContext().getColorStateList(zIsNeedDarkThemeColor ? 2131103291 : 2131103244);
            ImageView imageView3 = this.mExpandIndicator;
            if (imageView3 != null) {
                QsColorUtil.compareAndUpdateImageTintList(imageView3, colorStateList);
            }
            ImageView imageView4 = this.mExpandIndicator;
            if (imageView4 != null) {
                QsColorUtil.compareAndUpdateBgTintList(imageView4, colorStateList2);
                return;
            }
            return;
        }
        if (i == 2) {
            ImageView imageView5 = this.mExpandIndicator;
            if (imageView5 != null) {
                QsColorUtil.compareAndUpdateImageTintList(imageView5, (!OnePlusQsColorUtil.INSTANCE.getEnableTwoToneTheme() || ScrimUtil.isLowGaussianLevel(getContext())) ? getContext().getColorStateList(2131103707) : getContext().getColorStateList(2131102960));
            }
            ImageView imageView6 = this.mExpandIndicator;
            if (imageView6 != null) {
                QsColorUtil.compareAndUpdateBgTintList(imageView6, getContext().getColorStateList(2131103243));
                return;
            }
            return;
        }
        boolean zIsNeedDarkThemeColor2 = QsColorUtil.isNeedDarkThemeColor(getContext(), false);
        ColorStateList colorStateList3 = getContext().getColorStateList(zIsNeedDarkThemeColor2 ? 2131103291 : 2131103710);
        ColorStateList colorStateList4 = getContext().getColorStateList(zIsNeedDarkThemeColor2 ? 2131103291 : 2131103245);
        ImageView imageView7 = this.mExpandIndicator;
        if (imageView7 != null) {
            QsColorUtil.compareAndUpdateImageTintList(imageView7, colorStateList3);
        }
        ImageView imageView8 = this.mExpandIndicator;
        if (imageView8 != null) {
            QsColorUtil.compareAndUpdateBgTintList(imageView8, colorStateList4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateLabelColor() {
        initLabelColor();
        int i = this.mState;
        if (i != 0) {
            if (i == 1) {
                ColorStateList colorStateList = QsColorUtil.isNeedDarkThemeColor(getContext(), false) ? getContext().getColorStateList(2131103292) : this.mColorLabelInActive;
                TextSwitcher textSwitcher = this.mLabel;
                if (textSwitcher != null) {
                    TextSwitcherExtKt.setTextColor(textSwitcher, colorStateList);
                    return;
                }
                return;
            }
            if (i == 2) {
                TextSwitcher textSwitcher2 = this.mLabel;
                if (textSwitcher2 != null) {
                    TextSwitcherExtKt.setTextColor(textSwitcher2, this.mColorLabelActive);
                    return;
                }
                return;
            }
            if (i != 101) {
                return;
            }
        }
        ColorStateList colorStateList2 = QsColorUtil.isNeedDarkThemeColor(getContext(), false) ? getContext().getColorStateList(2131103291) : this.mColorLabelUnavailable;
        TextSwitcher textSwitcher3 = this.mLabel;
        if (textSwitcher3 != null) {
            TextSwitcherExtKt.setTextColor(textSwitcher3, colorStateList2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateResource$1() {
        TextSwitcher textSwitcher = this.mLabel;
        if (textSwitcher != null) {
            OplusQSFontHelper oplusQSFontHelper = OplusQSFontHelper.getInstance(((LinearLayout) this).mContext);
            float f = 12.0f;
            if (oplusQSFontHelper.mContext != null) {
                float[] fArr = oplusQSFontHelper.mFontScaleSizes;
                float f2 = Resources.getSystem().getConfiguration().fontScale;
                int i = 0;
                float fAbs = Math.abs(fArr[0] - f2);
                for (int i2 = 1; i2 < fArr.length; i2++) {
                    float fAbs2 = Math.abs(fArr[i2] - f2);
                    if (fAbs2 <= fAbs) {
                        i = i2;
                        fAbs = fAbs2;
                    }
                }
                LogUtil.d(LogUtil.TAG_QS, "OplusQSFontHelper", "fontScale: " + f2);
                int[] intArray = oplusQSFontHelper.mContext.getResources().getIntArray(2130903268);
                if (intArray != null && intArray.length > i) {
                    f = intArray[i];
                    ClipboardOverlayView$.ExternalSyntheticOutline0.m(intArray[i], LogUtil.TAG_QS, "OplusQSFontHelper", new StringBuilder("desiredFontSize: "));
                }
                LogUtil.d(LogUtil.TAG_QS, "OplusQSFontHelper", "type: 6 fontSize: " + f);
            }
            TextSwitcherExtKt.setTextSize(textSwitcher, f);
        }
    }

    public void updateSpotLightPath(Path path, Object obj) {
        ((CornerOutlineProvider) StdQSResPool.highLightTileOutline.getValue()).updatePath(this.mBgDrawable, path, null, obj);
    }

    public FrameLayout getIconWithBackground() {
        return this.mIconFrame;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void init(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener) {
        LinearLayout linearLayout = this.mIndicatorContainer;
        if (linearLayout != null) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(1);
            anonymousClass3.$tile = this;
            anonymousClass3.$expandable = onClickListener2;
            VarHandle.storeStoreFence();
            linearLayout.setOnClickListener(anonymousClass3);
        }
        setOnClickListener(onClickListener);
        setOnLongClickListener(onLongClickListener);
        ViewGroup viewGroup = this.mLabelContainer;
        if (viewGroup != null) {
            viewGroup.setClickable(false);
        }
        ViewGroup viewGroup2 = this.mLabelContainer;
        if (viewGroup2 != null) {
            viewGroup2.setLongClickable(false);
        }
    }

    public OplusQSHighlightTileView(Context context, QSIconView qSIconView) {
        this(context, qSIconView, false);
    }
}
