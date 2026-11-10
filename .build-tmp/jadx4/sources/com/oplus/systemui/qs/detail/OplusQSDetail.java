package com.oplus.systemui.qs.detail;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.DependencyEx;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.common.dialog.OplusQsDialogBlurBackgroundEx;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.coui.appcompat.couiswitch.COUISwitch;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.common.dialog.OplusQSDetailDialogProvider;
import com.oplus.systemui.notification.base.OplusNotificationQsExt;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsFontUtil;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.util.OplusContextUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.common.view.SmoothCornersGradientDrawable;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSDetail extends LinearLayout implements View.OnClickListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int mAdapterLandscapeHeight;
    public int mAdapterVerticalHeight;
    public ViewBlurProxy mBlurProxy;
    public View mButtonDivider;
    public boolean mChangeTypeAndWrapHeight;
    public DetailAdapter mDetailAdapter;
    public LinearLayout mDetailFrame;
    public LinearLayout mDetailHintContainer;
    public TextView mDetailHintTextView;
    public TextView mDetailSettingsButton;
    public final SparseArray mDetailViews;
    public OplusQSDetailDialogProvider mDialogProvider;
    public boolean mIsShowQsDetail;
    public boolean mIsWindowTranslucent;
    public OplusNotificationQsExt mNotificationQsInteractor;
    public LinearLayout mQsDetailBottom;
    public View mQsDetailHeader;
    public COUISwitch mQsDetailHeaderSwitch;
    public ViewStub mQsDetailHeaderSwitchStub;
    public TextView mQsDetailHeaderTitle;
    public OplusQSDetailDialogProvider.DialogHeight mTileType;
    public final 1 mUpdateAdapterHeightRunnable;

    public static void $r8$lambda$z7rjplqbcx68LseTc9u7gUL0Rcc(OplusQSDetail oplusQSDetail, Intent intent) {
        if (!DeviceUtil.isLandscape(((LinearLayout) oplusQSDetail).mContext) || DeviceUtil.shouldUseLargeFoldConfig(oplusQSDetail.getResources())) {
            ((ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class)).startActivity(intent, true, true);
        } else {
            ((ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class)).postStartActivityDismissingKeyguard(intent, 80);
        }
        OplusQSDetailDialogProvider oplusQSDetailDialogProvider = oplusQSDetail.mDialogProvider;
        if (oplusQSDetailDialogProvider == null) {
            return;
        }
        oplusQSDetailDialogProvider.destroy(true);
        oplusQSDetail.mDialogProvider = null;
    }

    public OplusQSDetail(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDetailViews = new SparseArray();
        this.mIsShowQsDetail = false;
        this.mTileType = OplusQSDetailDialogProvider.DialogHeight.IMMUTABLE_ONE;
        this.mChangeTypeAndWrapHeight = false;
        this.mAdapterVerticalHeight = 0;
        this.mAdapterLandscapeHeight = 0;
        this.mIsWindowTranslucent = false;
        1 r1 = new 1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mUpdateAdapterHeightRunnable = r1;
        COUIThemeOverlay.getInstance().applyThemeOverlays(((LinearLayout) this).mContext);
    }

    public final void handleShowingDetail(DetailAdapter detailAdapter, OplusQSDetailDialogProvider.DialogHeight dialogHeight) {
        setClickable(detailAdapter != null);
        if (detailAdapter != null) {
            setupDetailHeader(detailAdapter);
            this.mDetailHintContainer.setVisibility(detailAdapter.hasHint() ? 0 : 8);
            this.mDetailHintTextView.setVisibility(detailAdapter.hasHint() ? 0 : 8);
            setupDetailFooter(detailAdapter);
            announceForAccessibility(((LinearLayout) this).mContext.getString(2131951925, detailAdapter.getTitle()));
            this.mDetailAdapter = detailAdapter;
            new OplusQsDetailHeightProxy(this, detailAdapter, dialogHeight).collectQsDetailHeight();
            updateLayoutFrame(dialogHeight);
            setVisibility(0);
            this.mQsDetailHeader.setVisibility(this.mDetailAdapter.hasHeader() ? 0 : 8);
        } else if (this.mDetailAdapter != null) {
            this.mDetailAdapter = null;
        }
        sendAccessibilityEvent(32);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAdapterVerticalHeight = 0;
        this.mAdapterLandscapeHeight = 0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        OplusQSDetailDialogProvider oplusQSDetailDialogProvider = this.mDialogProvider;
        if (oplusQSDetailDialogProvider == null) {
            return;
        }
        oplusQSDetailDialogProvider.destroy(false);
        this.mDialogProvider = null;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mTileType == OplusQSDetailDialogProvider.DialogHeight.CHANGE) {
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(configuration.orientation, LogUtil.TAG, "OplusQSDetail", new StringBuilder("onConfigurationChanged, orientation = "));
        }
        DetailAdapter detailAdapter = this.mDetailAdapter;
        if (detailAdapter != null && detailAdapter.hasHeader()) {
            Context context = ((LinearLayout) this).mContext;
            Context contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(context, false));
            this.mQsDetailHeaderTitle.setTextColor(OplusContextUtil.getAttrColor(contextCreateDarkColorContext, 2130969296));
            this.mButtonDivider.setBackgroundColor(OplusContextUtil.getAttrColor(contextCreateDarkColorContext, 2130969283));
        }
        updateBackground();
        this.mDetailHintTextView.setText(2131955692);
        QsFontUtil.setTextFontVariation(this.mDetailHintTextView);
        FontSizeUtils.updateFontSize(this.mDetailHintTextView, 2131171659);
        for (int i = 0; i < this.mDetailViews.size(); i++) {
            ((View) this.mDetailViews.valueAt(i)).dispatchConfigurationChanged(configuration);
        }
        OplusQSDetailDialogProvider.DialogHeight dialogHeight = this.mTileType;
        if (dialogHeight == OplusQSDetailDialogProvider.DialogHeight.ADAPTIVE_IMMUTABLE) {
            OplusQSDetail$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSDetail$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            post(externalSyntheticLambda0);
        } else {
            updateLayoutFrame(dialogHeight);
        }
        DetailAdapter detailAdapter2 = this.mDetailAdapter;
        if (detailAdapter2 != null) {
            boolean zHasHint = detailAdapter2.hasHint();
            LinearLayout linearLayout = this.mDetailHintContainer;
            if (zHasHint) {
                linearLayout.setVisibility(0);
                this.mDetailHintTextView.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
                this.mDetailHintTextView.setVisibility(8);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAdapterVerticalHeight = 0;
        this.mAdapterLandscapeHeight = 0;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mDetailFrame = (LinearLayout) findViewById(2131362831);
        Context context = ((LinearLayout) this).mContext;
        Context contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(context, false));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(contextCreateDarkColorContext).inflate(2131559510, (ViewGroup) this.mDetailFrame, false);
        this.mQsDetailBottom = linearLayout;
        this.mDetailFrame.addView(linearLayout, layoutParams);
        this.mButtonDivider = this.mQsDetailBottom.findViewById(2131364726);
        this.mDetailSettingsButton = (TextView) this.mQsDetailBottom.findViewById(R.id.button2);
        this.mDetailHintTextView = (TextView) findViewById(2131364772);
        this.mDetailHintContainer = (LinearLayout) findViewById(2131364771);
        View viewInflate = LayoutInflater.from(contextCreateDarkColorContext).inflate(2131559511, (ViewGroup) this.mDetailFrame, false);
        this.mQsDetailHeader = viewInflate;
        this.mDetailFrame.addView(viewInflate, 0, layoutParams);
        this.mQsDetailHeaderTitle = (TextView) this.mQsDetailHeader.findViewById(R.id.title);
        this.mQsDetailHeaderSwitchStub = (ViewStub) this.mQsDetailHeader.findViewById(2131362847);
        this.mDetailHintTextView.setText(2131955692);
        QsFontUtil.setTextFontVariation(this.mDetailHintTextView);
        setOnClickListener(this);
        updateBackground();
        if (QsColorUtil.isNeedDarkThemeColor(((LinearLayout) this).mContext, false)) {
            this.mButtonDivider.setBackgroundColor(getResources().getColor(2131103687));
            this.mQsDetailHeaderTitle.setTextColor(getResources().getColor(2131103698));
        }
        this.mDetailFrame.setOnTouchListener(new OplusQSDetail$.ExternalSyntheticLambda3());
        this.mNotificationQsInteractor = (OplusNotificationQsExt) DependencyEx.sDependency.getDependency(OplusNotificationQsExt.class);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        DetailAdapter detailAdapter;
        if (!this.mChangeTypeAndWrapHeight) {
            super.onMeasure(i, i2);
            return;
        }
        int dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171697);
        int size = View.MeasureSpec.getSize(i2);
        LogUtil.d(LogUtil.TAG, "OplusQSDetail", TextFieldBuffer$.ExternalSyntheticOutline0.m(size, dimensionPixelSize, "onMeasure, getMaxMeasureSpec, size = ", ", value = "));
        if (dimensionPixelSize > 0 && size > dimensionPixelSize && ((detailAdapter = this.mDetailAdapter) == null || detailAdapter.customHeight() == 0)) {
            i2 = View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE ? View.MeasureSpec.makeMeasureSpec(dimensionPixelSize, Integer.MIN_VALUE) : View.MeasureSpec.makeMeasureSpec(dimensionPixelSize, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        ViewBlurProxy viewBlurProxy = this.mBlurProxy;
        if (viewBlurProxy != null) {
            viewBlurProxy.onVisibilityAggregated(z);
        }
    }

    public void setParentDialog(OplusQSDetailDialogProvider oplusQSDetailDialogProvider) {
        this.mDialogProvider = oplusQSDetailDialogProvider;
    }

    public void setWindowTranslucent(boolean z) {
        this.mIsWindowTranslucent = z;
    }

    public void setupDetailFooter(DetailAdapter detailAdapter) {
        Integer numPaddingBottom = detailAdapter.paddingBottom();
        if (numPaddingBottom != null) {
            this.mQsDetailBottom.setMinimumHeight(numPaddingBottom.intValue());
        }
        Intent settingsIntent = detailAdapter.getSettingsIntent();
        this.mDetailSettingsButton.setVisibility(settingsIntent != null ? 0 : 8);
        TextView textView = this.mDetailSettingsButton;
        OplusQSDetail$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusQSDetail$.ExternalSyntheticLambda1(1);
        externalSyntheticLambda1.f$0 = this;
        externalSyntheticLambda1.f$1 = settingsIntent;
        VarHandle.storeStoreFence();
        textView.setOnClickListener(externalSyntheticLambda1);
        detailAdapter.setDetailSettingsButton(this.mDetailSettingsButton);
    }

    public void setupDetailHeader(DetailAdapter detailAdapter) {
        boolean zHasHeader = detailAdapter.hasHeader();
        View view = this.mQsDetailHeader;
        if (!zHasHeader) {
            view.setVisibility(8);
            return;
        }
        view.setVisibility(0);
        this.mQsDetailHeaderTitle.setText(detailAdapter.getTitle());
        Boolean toggleState = detailAdapter.getToggleState();
        COUISwitch cOUISwitch = this.mQsDetailHeaderSwitch;
        if (toggleState == null) {
            if (cOUISwitch != null) {
                cOUISwitch.setVisibility(8);
            }
            this.mQsDetailHeader.setClickable(false);
            return;
        }
        if (cOUISwitch == null) {
            this.mQsDetailHeaderSwitch = this.mQsDetailHeaderSwitchStub.inflate();
        }
        this.mQsDetailHeaderSwitch.setVisibility(0);
        boolean zBooleanValue = toggleState.booleanValue();
        COUISwitch cOUISwitch2 = this.mQsDetailHeaderSwitch;
        if (cOUISwitch2 != null) {
            cOUISwitch2.stopLoading$1();
            if (this.mQsDetailHeaderSwitch.isChecked() != zBooleanValue) {
                this.mQsDetailHeaderSwitch.setChecked(zBooleanValue);
            }
        }
        this.mQsDetailHeaderSwitch.setLoadingStyle(true);
        COUISwitch cOUISwitch3 = this.mQsDetailHeaderSwitch;
        2 r4 = new 2();
        r4.this$0 = this;
        r4.val$adapter = detailAdapter;
        VarHandle.storeStoreFence();
        cOUISwitch3.setOnLoadingStateChangedListener(r4);
        COUISwitch cOUISwitch4 = this.mQsDetailHeaderSwitch;
        OplusQSDetail$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusQSDetail$.ExternalSyntheticLambda1(0);
        externalSyntheticLambda1.f$0 = this;
        externalSyntheticLambda1.f$1 = detailAdapter;
        VarHandle.storeStoreFence();
        cOUISwitch4.setOnClickListener(externalSyntheticLambda1);
        this.mQsDetailHeaderSwitch.setClickable(true);
        if (!detailAdapter.turnOnSwitchWhenAttach() || toggleState.booleanValue()) {
            return;
        }
        this.mQsDetailHeaderSwitch.startLoading();
    }

    public final void showDetail(DetailAdapter detailAdapter, boolean z, OplusQSDetailDialogProvider.DialogHeight dialogHeight) {
        if (this.mIsShowQsDetail == z) {
            return;
        }
        if (z) {
            this.mIsShowQsDetail = true;
            handleShowingDetail(detailAdapter, dialogHeight);
            this.mNotificationQsInteractor.setDetailShowingDialog(true);
        } else {
            this.mIsShowQsDetail = false;
            handleShowingDetail(null, dialogHeight);
            this.mNotificationQsInteractor.setDetailShowingDialog(false);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x006f  */
    /* JADX WARN: Code duplicated, block: B:23:0x0075  */
    /* JADX WARN: Code duplicated, block: B:25:0x0086  */
    public final void updateBackground() {
        int color = ((LinearLayout) this).mContext.getColor(2131103683);
        Boolean bool = (Boolean) ((OplusSeparateNotificationAndQSState) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSState.class)).getEnableSeparateNotificationAndQS().getValue();
        if (!QsColorUtil.isGlobalThemeApplied(((LinearLayout) this).mContext)) {
            if (ScrimUtil.isLowGaussianLevel(((LinearLayout) this).mContext)) {
                QsColorUtil.INSTANCE.getClass();
                if (QsColorUtil.isSuperPowerSaveState) {
                    if (bool.booleanValue()) {
                        color = getResources().getColor(2131103200);
                        if (QsUtil.isPowerSavingMode()) {
                            color = getResources().getColor(2131103197);
                        }
                    }
                } else if (this.mIsWindowTranslucent) {
                    color = bool.booleanValue() ? getResources().getColor(2131103198) : getResources().getColor(2131103197);
                } else {
                    color = (bool.booleanValue() || OplusDarkModeUtil.isNightMode(getContext())) ? QsColorUtil.LIGHT_OS_QS_TILE_BG_INACTIVE_DARK_COLOR : QsColorUtil.LIGHT_OS_QS_TILE_BG_INACTIVE_COLOR;
                }
            } else if (bool.booleanValue()) {
                color = getResources().getColor(2131103200);
                if (QsUtil.isPowerSavingMode()) {
                    color = getResources().getColor(2131103197);
                }
            }
        }
        if (!ScrimUtil.isLowGaussianLevel(((LinearLayout) this).mContext) && QsColorUtil.isNeedDarkThemeColor(((LinearLayout) this).mContext, false)) {
            color = getResources().getColor(2131103201);
        }
        float dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171633);
        SmoothCornersGradientDrawable smoothCornersGradientDrawable = new SmoothCornersGradientDrawable();
        smoothCornersGradientDrawable.setColor(color);
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(((LinearLayout) this).mContext, dimensionPixelSize);
        if (roundParams != null) {
            smoothCornersGradientDrawable.setSmoothRound(roundParams.getRadius(), roundParams.getWeight());
        } else {
            smoothCornersGradientDrawable.setSmoothRound(dimensionPixelSize, 0.0f);
        }
        Drawable drawableProvideBlurBackground = ((OplusQsDialogBlurBackgroundEx) DependencyEx.sDependency.getDependency(OplusQsDialogBlurBackgroundEx.class)).provideBlurBackground(this.mDetailFrame, smoothCornersGradientDrawable, (Integer) null, (Float) null);
        if (drawableProvideBlurBackground instanceof AutoBlurDrawable) {
            this.mBlurProxy = ((AutoBlurDrawable) drawableProvideBlurBackground).getViewBlurProxy();
        } else {
            this.mBlurProxy = null;
        }
        this.mDetailFrame.setBackground(drawableProvideBlurBackground);
    }

    public final void updateLayoutFrame(OplusQSDetailDialogProvider.DialogHeight dialogHeight) {
        this.mTileType = dialogHeight;
        this.mChangeTypeAndWrapHeight = false;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mDetailFrame.getLayoutParams();
        FeatureOption.INSTANCE.getClass();
        boolean zIsTablet = FeatureOption.isTablet();
        Context context = ((LinearLayout) this).mContext;
        if (zIsTablet) {
            layoutParams.width = context.getResources().getDimensionPixelSize(2131171691);
            layoutParams.height = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171634);
            layoutParams.topMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171632);
            layoutParams.bottomMargin = 0;
            int dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171631);
            layoutParams.leftMargin = dimensionPixelSize;
            layoutParams.setMarginStart(dimensionPixelSize);
            layoutParams.rightMargin = 0;
            layoutParams.setMarginEnd(0);
            updateLayoutWidthAndHeight(layoutParams, dialogHeight);
        } else if (DeviceUtil.isLandscape(context)) {
            DetailAdapter detailAdapter = this.mDetailAdapter;
            if (detailAdapter == null || detailAdapter.customLandWidth() <= 0) {
                layoutParams.width = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171740);
            } else {
                layoutParams.width = this.mDetailAdapter.customLandWidth();
            }
            Context context2 = ((LinearLayout) this).mContext;
            Display defaultDisplay = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            LogUtil.d(LogUtil.TAG_QS, "OplusQSDetail", "getRealDisplayMetrics widthPixels: " + displayMetrics.widthPixels + ", heightPixels: " + displayMetrics.heightPixels + ", contextDisplayId: " + context2.getDisplayId() + ", displayDisplayId: " + defaultDisplay.getDisplayId());
            int iMin = Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels);
            if (QSFragmentHelper.getInstance().getQsContainer() != null && QSFragmentHelper.getInstance().getQsContainer().getHeight() > 0 && !this.mIsShowQsDetail) {
                iMin = Math.min(QSFragmentHelper.getInstance().getQsContainer().getHeight(), iMin);
            } else if (DeviceUtil.shouldUseLargeFoldConfig(getResources())) {
                iMin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171658);
            }
            layoutParams.height = iMin - (getResources().getDimensionPixelSize(2131171692) * 2);
            layoutParams.topMargin = getResources().getDimensionPixelSize(2131171692);
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(2131171692);
            layoutParams.leftMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171631);
            if (dialogHeight == OplusQSDetailDialogProvider.DialogHeight.CHANGE) {
                layoutParams.height = -2;
                this.mChangeTypeAndWrapHeight = true;
            } else if (dialogHeight == OplusQSDetailDialogProvider.DialogHeight.ADAPTIVE_IMMUTABLE) {
                layoutParams.height = -2;
                this.mChangeTypeAndWrapHeight = true;
                this.mDetailFrame.post(this.mUpdateAdapterHeightRunnable);
            }
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        } else {
            layoutParams.bottomMargin = 0;
            layoutParams.topMargin = 0;
            layoutParams.width = -1;
            layoutParams.height = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171634);
            if (DeviceUtil.shouldUseLargeFoldConfig(getResources())) {
                layoutParams.width = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171740);
                layoutParams.height = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171658);
            }
            updateLayoutWidthAndHeight(layoutParams, dialogHeight);
            layoutParams.leftMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171631);
            layoutParams.rightMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171631);
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        }
        LogUtil.w(LogUtil.TAG, "OplusQSDetail", "jetta->dialog updateLayoutFrame,mDialogProvider=" + this.mDialogProvider + ",mTileType = " + dialogHeight + ",setLayoutParams, height = " + layoutParams.height + ",width = " + layoutParams.width + ",mChangeTypeAndWrapHeight = " + this.mChangeTypeAndWrapHeight + ",topMargin=" + layoutParams.topMargin + ",bottomMargin=" + layoutParams.bottomMargin + ",leftMargin=" + layoutParams.leftMargin + ",rightMargin=" + layoutParams.rightMargin);
        this.mDetailFrame.setLayoutParams(layoutParams);
    }

    public final void updateLayoutWidthAndHeight(LinearLayout.LayoutParams layoutParams, OplusQSDetailDialogProvider.DialogHeight dialogHeight) {
        DetailAdapter detailAdapter;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet() || DeviceUtil.shouldUseLargeFoldConfig(getResources())) {
            layoutParams.width = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171740);
        }
        if (dialogHeight == OplusQSDetailDialogProvider.DialogHeight.IMMUTABLE_ONE) {
            layoutParams.height = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171697);
            return;
        }
        if (dialogHeight == OplusQSDetailDialogProvider.DialogHeight.CUSTOM && (detailAdapter = this.mDetailAdapter) != null && detailAdapter.customHeight() > 0) {
            layoutParams.height = this.mDetailAdapter.customHeight();
            return;
        }
        if (dialogHeight == OplusQSDetailDialogProvider.DialogHeight.CHANGE) {
            layoutParams.height = -2;
            this.mChangeTypeAndWrapHeight = true;
        } else if (dialogHeight == OplusQSDetailDialogProvider.DialogHeight.IMMUTABLE_TWO) {
            layoutParams.height = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171699);
        } else if (dialogHeight == OplusQSDetailDialogProvider.DialogHeight.ADAPTIVE_IMMUTABLE) {
            layoutParams.height = -2;
            this.mChangeTypeAndWrapHeight = true;
            this.mDetailFrame.post(this.mUpdateAdapterHeightRunnable);
        }
    }
}
