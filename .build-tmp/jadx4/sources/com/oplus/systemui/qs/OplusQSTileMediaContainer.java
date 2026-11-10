package com.oplus.systemui.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.qs.deprecated.QSPanelControllerBase;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileMediaContainer extends ConstraintLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSTileMediaContainer";
    public boolean isLargeFoldWithOpened;
    private ViewController.OnConfigurationChangedListener mConfigurationListener;
    public boolean mExpanded;
    private LinearLayout mFirstTileContainer;
    private final ArrayList<QSPanelControllerBase.TileRecord> mHlTiles;
    public boolean mIsMediaMode;
    public boolean mListening;
    private LinearLayout mQsMediaPanelContainer;
    private ConstraintLayout mQsSeekBarContainer;
    private LinearLayout mSecondTileContainer;
    private final ConstraintSet mTmpConstraintSet;
    private SpringOverScroller springOverScroller;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSTileMediaContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTmpConstraintSet = new ConstraintSet();
        this.mHlTiles = new ArrayList<>();
    }

    public final void addTileView(QSPanelControllerBase.TileRecord tileRecord) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "addHighlightTileView tileRecord=" + tileRecord + " mListening=" + this.mListening + " mExpanded=" + this.mExpanded, null, 8);
        this.mHlTiles.add(tileRecord);
        QSTile qSTile = tileRecord.tile;
        QSTileView qSTileView = tileRecord.tileView;
        qSTile.setListening(this, this.mListening);
        if (this.mHlTiles.size() > 1) {
            LinearLayout linearLayout = this.mSecondTileContainer;
            if (linearLayout != null) {
                linearLayout.addView(qSTileView);
            }
        } else {
            LinearLayout linearLayout2 = this.mFirstTileContainer;
            if (linearLayout2 != null) {
                linearLayout2.addView(qSTileView);
            }
        }
        ((OplusQSHighlightTileView) qSTileView).setCollapsedView(this.mExpanded);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void computeScroll() {
        QSFragmentHelper.getInstance().computeScroll(this, this.springOverScroller);
    }

    public final View getAnimatorView() {
        return this.mFirstTileContainer;
    }

    public final boolean getMediaMode() {
        return this.mIsMediaMode;
    }

    public final SpringOverScroller getSpringOverScroller() {
        return this.springOverScroller;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        Trace.traceBegin(8L, "OplusQSTileMediaContainer-onConfigurationChanged");
        super/*android.view.ViewGroup*/.onConfigurationChanged(configuration);
        updateResources$3();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onConfigurationChanged: expand: " + this.mExpanded + ", newConfig: " + configuration, null, 8);
        ViewController.OnConfigurationChangedListener onConfigurationChangedListener = this.mConfigurationListener;
        if (onConfigurationChangedListener != null) {
            onConfigurationChangedListener.onConfigurationChange(configuration);
        }
        Trace.traceEnd(8L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onFinishInflate() {
        super/*android.view.ViewGroup*/.onFinishInflate();
        this.mFirstTileContainer = (LinearLayout) findViewById(2131363363);
        this.mSecondTileContainer = (LinearLayout) findViewById(2131363364);
        this.mQsMediaPanelContainer = (LinearLayout) findViewById(2131363897);
        this.mQsSeekBarContainer = findViewById(2131365122);
        this.springOverScroller = new SpringOverScroller(((ViewGroup) this).mContext);
    }

    public final void removeTiles() {
        ArrayList<QSPanelControllerBase.TileRecord> arrayList;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "removeTiles", null, 8);
        int size = this.mHlTiles.size();
        int i = 0;
        while (true) {
            arrayList = this.mHlTiles;
            if (i >= size) {
                break;
            }
            arrayList.get(i).tile.setListening(this, false);
            i++;
        }
        arrayList.clear();
        LinearLayout linearLayout = this.mSecondTileContainer;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        LinearLayout linearLayout2 = this.mFirstTileContainer;
        if (linearLayout2 != null) {
            linearLayout2.removeAllViews();
        }
    }

    public final void setConfigurationChangeListener(ViewController.OnConfigurationChangedListener onConfigurationChangedListener) {
        this.mConfigurationListener = onConfigurationChangedListener;
    }

    public final void setExpanded(boolean z) {
        if (this.mExpanded != z) {
            this.mExpanded = z;
            int size = this.mHlTiles.size();
            for (int i = 0; i < size; i++) {
                ((OplusQSHighlightTileView) this.mHlTiles.get(i).tileView).setCollapsedView(this.mExpanded);
            }
        }
    }

    public final void setExpansion(float f) {
        if (Float.isNaN(f)) {
            return;
        }
        int size = this.mHlTiles.size();
        for (int i = 0; i < size; i++) {
            ((OplusQSHighlightTileView) this.mHlTiles.get(i).tileView).setExpandFraction(f);
        }
    }

    public final void setListening(boolean z) {
        if (z == this.mListening) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setListening: listening=", "OplusQSTileMediaContainer-->", "SystemUi--QuickSettings", z);
        }
        this.mListening = z;
        int size = this.mHlTiles.size();
        for (int i = 0; i < size; i++) {
            this.mHlTiles.get(i).tile.setListening(this, z);
            this.mHlTiles.get(i).tileView.setListening(z);
        }
    }

    public final void setMediaMode(boolean z) {
        OplusNotificationLogger.logMediaPanel(TAG, "setMediaMode: isMediaMode=" + z + ";old=" + this.mIsMediaMode);
        this.mIsMediaMode = z;
        updateResources$3();
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0470  */
    /* JADX WARN: Code duplicated, block: B:104:0x0476 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:108:0x0482  */
    /* JADX WARN: Code duplicated, block: B:121:0x04df  */
    /* JADX WARN: Code duplicated, block: B:123:0x04e7  */
    /* JADX WARN: Code duplicated, block: B:124:0x0502  */
    /* JADX WARN: Code duplicated, block: B:15:0x003e  */
    /* JADX WARN: Code duplicated, block: B:95:0x044f  */
    /* JADX WARN: Code duplicated, block: B:97:0x045b  */
    /* JADX WARN: Code duplicated, block: B:98:0x045f  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void updateResources$3() {
        boolean z;
        int i;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        LinearLayout linearLayout3;
        int i2;
        boolean zIsSupportVolumeSeekBar;
        ConstraintSet constraintSet;
        LinearLayout linearLayout4;
        ConstraintLayout constraintLayout;
        LinearLayout linearLayout5;
        LinearLayout linearLayout6;
        LinearLayout linearLayout7;
        LinearLayout linearLayout8;
        Resources resources = getResources();
        boolean z2 = OplusQSSmallSpaceController.Companion.isSmallSpace(((ViewGroup) this).mContext, false) && OplusQSDisplayModeUtils.isBigDisplayMode(((ViewGroup) this).mContext);
        boolean zIsBigDisplayMode = OplusQSDisplayModeUtils.isBigDisplayMode(((ViewGroup) this).mContext);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
            resources.getClass();
            z = DeviceUtil.shouldUseLargeFoldConfig(resources);
        }
        this.isLargeFoldWithOpened = z;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("isLargeFoldWithOpene= ", "shouldScaleDown= ", z, z2), null, 8);
        int dimensionPixelSize = resources.getDimensionPixelSize(z2 ? 2131171888 : 2131171887);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(2131171904);
        setPaddingRelative(dimensionPixelSize2, getPaddingTop(), dimensionPixelSize2, getPaddingBottom());
        this.mTmpConstraintSet.clone(this);
        boolean z3 = this.isLargeFoldWithOpened;
        LinearLayout linearLayout9 = this.mQsMediaPanelContainer;
        if (linearLayout9 == null || (linearLayout7 = this.mSecondTileContainer) == null || (linearLayout8 = this.mFirstTileContainer) == null) {
            i = 2131172198;
        } else {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isSupportVolumeSeekBar() && (z3 || FeatureOption.isTablet())) {
                int dimensionPixelSize3 = resources.getDimensionPixelSize(2131172198);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 3, 0, 3, 0);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 4, linearLayout7.getId(), 3, 0);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 6, 0, 6, 0);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 7, linearLayout9.getId(), 6, dimensionPixelSize3);
            } else if (QSFeatureOption.isSupportVolumeSeekBar() && this.mIsMediaMode) {
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 3, linearLayout7.getId(), 3, 0);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 4, linearLayout7.getId(), 4, 0);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 6, linearLayout9.getId(), 6, 0);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 7, linearLayout9.getId(), 7, 0);
            } else if (!QSFeatureOption.isSupportVolumeSeekBar() || this.mIsMediaMode) {
                i = 2131172198;
                boolean z4 = this.mIsMediaMode;
                ConstraintSet constraintSet2 = this.mTmpConstraintSet;
                if (z4) {
                    constraintSet2.connect(linearLayout8.getId(), 3, linearLayout9.getId(), 3, 0);
                    this.mTmpConstraintSet.connect(linearLayout8.getId(), 4, linearLayout7.getId(), 3, 0);
                    this.mTmpConstraintSet.connect(linearLayout8.getId(), 6, 0, 6, 0);
                    this.mTmpConstraintSet.connect(linearLayout8.getId(), 7, 2131363281, 6, dimensionPixelSize);
                } else {
                    constraintSet2.connect(linearLayout8.getId(), 3, 0, 3, 0);
                    this.mTmpConstraintSet.connect(linearLayout8.getId(), 4, linearLayout7.getId(), 4, 0);
                    this.mTmpConstraintSet.connect(linearLayout8.getId(), 6, 0, 6, 0);
                    this.mTmpConstraintSet.connect(linearLayout8.getId(), 7, 2131363281, 6, dimensionPixelSize);
                }
            } else {
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 3, 0, 3, 0);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 4, linearLayout7.getId(), 3, 0);
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 6, 0, 6, 0);
                i = 2131172198;
                this.mTmpConstraintSet.connect(linearLayout8.getId(), 7, 2131363281, 6, dimensionPixelSize);
            }
            i = 2131172198;
        }
        boolean z5 = this.isLargeFoldWithOpened;
        LinearLayout linearLayout10 = this.mQsMediaPanelContainer;
        if (linearLayout10 != null && (linearLayout5 = this.mSecondTileContainer) != null && (linearLayout6 = this.mFirstTileContainer) != null) {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isSupportVolumeSeekBar() && (z5 || FeatureOption.isTablet())) {
                int dimensionPixelSize4 = resources.getDimensionPixelSize(2131171891);
                int dimensionPixelSize5 = resources.getDimensionPixelSize(i);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 3, linearLayout6.getId(), 4, dimensionPixelSize4);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 4, 0, 4, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 6, 0, 6, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 7, linearLayout10.getId(), 6, dimensionPixelSize5);
            } else if (QSFeatureOption.isSupportVolumeSeekBar() && this.mIsMediaMode) {
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 3, linearLayout10.getId(), 4, resources.getDimensionPixelSize(z2 ? 2131171885 : 2131171886));
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 4, 0, 4, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 6, 2131363281, 7, dimensionPixelSize);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 7, 0, 7, 0);
            } else if (QSFeatureOption.isSupportVolumeSeekBar() && !this.mIsMediaMode) {
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 3, linearLayout6.getId(), 4, resources.getDimensionPixelSize(2131171891));
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 4, 0, 4, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 6, linearLayout6.getId(), 6, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 7, linearLayout6.getId(), 7, 0);
            } else if (this.mIsMediaMode) {
                int dimensionPixelSize6 = resources.getDimensionPixelSize(z2 ? 2131171890 : 2131171889);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 6, linearLayout6.getId(), 6, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 7, linearLayout6.getId(), 7, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 4, linearLayout10.getId(), 4, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 3, linearLayout6.getId(), 4, dimensionPixelSize6);
            } else {
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 6, 2131363281, 7, dimensionPixelSize);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 7, 0, 7, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 3, linearLayout6.getId(), 3, 0);
                this.mTmpConstraintSet.connect(linearLayout5.getId(), 4, linearLayout6.getId(), 4, 0);
            }
        }
        boolean z6 = this.isLargeFoldWithOpened;
        LinearLayout linearLayout11 = this.mQsMediaPanelContainer;
        if (linearLayout11 != null) {
            if (this.mIsMediaMode) {
                this.mTmpConstraintSet.setVisibility(linearLayout11.getId(), 0);
                if (z2) {
                    i2 = 2131171119;
                } else {
                    QSFeatureOption.INSTANCE.getClass();
                    if (!QSFeatureOption.isSupportVolumeSeekBar()) {
                        if (QSFeatureOption.isSupportVolumeSeekBar()) {
                            i2 = 2131171118;
                        } else {
                            i2 = 2131171118;
                        }
                    } else if (QSFeatureOption.isSupportVolumeSeekBar()) {
                        i2 = 2131171118;
                    } else {
                        i2 = 2131171118;
                    }
                }
                this.mTmpConstraintSet.constrainHeight(linearLayout11.getId(), resources.getDimensionPixelSize(i2));
                QSFeatureOption.INSTANCE.getClass();
                if (QSFeatureOption.isSupportVolumeSeekBar()) {
                    zIsSupportVolumeSeekBar = QSFeatureOption.isSupportVolumeSeekBar();
                    constraintSet = this.mTmpConstraintSet;
                    if (zIsSupportVolumeSeekBar) {
                        constraintSet.connect(linearLayout11.getId(), 6, 0, 6, 0);
                        linearLayout4 = linearLayout11;
                        this.mTmpConstraintSet.connect(linearLayout4.getId(), 7, 2131363281, 6, dimensionPixelSize);
                    } else {
                        linearLayout4 = linearLayout11;
                        constraintSet.connect(linearLayout4.getId(), 6, 2131363281, 7, dimensionPixelSize);
                        this.mTmpConstraintSet.connect(linearLayout4.getId(), 7, 0, 7, 0);
                    }
                    this.mTmpConstraintSet.connect(linearLayout4.getId(), 3, 0, 3, 0);
                } else {
                    zIsSupportVolumeSeekBar = QSFeatureOption.isSupportVolumeSeekBar();
                    constraintSet = this.mTmpConstraintSet;
                    if (zIsSupportVolumeSeekBar) {
                        constraintSet.connect(linearLayout11.getId(), 6, 0, 6, 0);
                        linearLayout4 = linearLayout11;
                        this.mTmpConstraintSet.connect(linearLayout4.getId(), 7, 2131363281, 6, dimensionPixelSize);
                    } else {
                        linearLayout4 = linearLayout11;
                        constraintSet.connect(linearLayout4.getId(), 6, 2131363281, 7, dimensionPixelSize);
                        this.mTmpConstraintSet.connect(linearLayout4.getId(), 7, 0, 7, 0);
                    }
                    this.mTmpConstraintSet.connect(linearLayout4.getId(), 3, 0, 3, 0);
                }
            } else {
                QSFeatureOption.INSTANCE.getClass();
                if (QSFeatureOption.isSupportVolumeSeekBar() && (z6 || FeatureOption.isTablet())) {
                    this.mTmpConstraintSet.setVisibility(linearLayout11.getId(), 0);
                    if (z2) {
                        i2 = 2131171119;
                    } else {
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportVolumeSeekBar() && zIsBigDisplayMode) {
                            i2 = 2131171122;
                        } else if (QSFeatureOption.isSupportVolumeSeekBar() || !(z6 || FeatureOption.isTablet())) {
                            i2 = 2131171118;
                        } else {
                            i2 = 2131171123;
                        }
                    }
                    this.mTmpConstraintSet.constrainHeight(linearLayout11.getId(), resources.getDimensionPixelSize(i2));
                    QSFeatureOption.INSTANCE.getClass();
                    if (QSFeatureOption.isSupportVolumeSeekBar() || !(z6 || FeatureOption.isTablet())) {
                        zIsSupportVolumeSeekBar = QSFeatureOption.isSupportVolumeSeekBar();
                        constraintSet = this.mTmpConstraintSet;
                        if (zIsSupportVolumeSeekBar) {
                            constraintSet.connect(linearLayout11.getId(), 6, 0, 6, 0);
                            linearLayout4 = linearLayout11;
                            this.mTmpConstraintSet.connect(linearLayout4.getId(), 7, 2131363281, 6, dimensionPixelSize);
                        } else {
                            linearLayout4 = linearLayout11;
                            constraintSet.connect(linearLayout4.getId(), 6, 2131363281, 7, dimensionPixelSize);
                            this.mTmpConstraintSet.connect(linearLayout4.getId(), 7, 0, 7, 0);
                        }
                    } else {
                        LinearLayout linearLayout12 = this.mFirstTileContainer;
                        if (linearLayout12 != null && (constraintLayout = this.mQsSeekBarContainer) != null) {
                            int dimensionPixelSize7 = resources.getDimensionPixelSize(i);
                            this.mTmpConstraintSet.connect(linearLayout11.getId(), 6, linearLayout12.getId(), 7, 0);
                            this.mTmpConstraintSet.connect(linearLayout11.getId(), 7, constraintLayout.getId(), 6, dimensionPixelSize7);
                            linearLayout4 = linearLayout11;
                        }
                    }
                    this.mTmpConstraintSet.connect(linearLayout4.getId(), 3, 0, 3, 0);
                } else {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateQsMediaPanelView setVisibility GONE", null, 8);
                    this.mTmpConstraintSet.setVisibility(linearLayout11.getId(), 8);
                }
            }
        }
        boolean z7 = this.isLargeFoldWithOpened;
        ConstraintLayout constraintLayout2 = this.mQsSeekBarContainer;
        if (constraintLayout2 != null && (linearLayout = this.mQsMediaPanelContainer) != null && (linearLayout2 = this.mSecondTileContainer) != null && (linearLayout3 = this.mFirstTileContainer) != null) {
            QSFeatureOption.INSTANCE.getClass();
            boolean zIsSupportVolumeSeekBar2 = QSFeatureOption.isSupportVolumeSeekBar();
            ConstraintSet constraintSet3 = this.mTmpConstraintSet;
            if (zIsSupportVolumeSeekBar2) {
                constraintSet3.setVisibility(constraintLayout2.getId(), 0);
                if (z7 || FeatureOption.isTablet()) {
                    this.mTmpConstraintSet.connect(constraintLayout2.getId(), 3, linearLayout.getId(), 3, 0);
                    this.mTmpConstraintSet.connect(constraintLayout2.getId(), 4, linearLayout.getId(), 4, 0);
                    this.mTmpConstraintSet.connect(constraintLayout2.getId(), 6, linearLayout.getId(), 7, 0);
                    this.mTmpConstraintSet.connect(constraintLayout2.getId(), 7, 0, 7, 0);
                } else {
                    boolean z8 = this.mIsMediaMode;
                    ConstraintSet constraintSet4 = this.mTmpConstraintSet;
                    if (z8) {
                        constraintSet4.connect(constraintLayout2.getId(), 3, linearLayout.getId(), 3, 0);
                        this.mTmpConstraintSet.connect(constraintLayout2.getId(), 4, linearLayout.getId(), 4, 0);
                        this.mTmpConstraintSet.connect(constraintLayout2.getId(), 6, linearLayout2.getId(), 6, 0);
                        this.mTmpConstraintSet.connect(constraintLayout2.getId(), 7, linearLayout2.getId(), 7, 0);
                    } else {
                        constraintSet4.connect(constraintLayout2.getId(), 3, linearLayout3.getId(), 3, 0);
                        this.mTmpConstraintSet.connect(constraintLayout2.getId(), 4, linearLayout2.getId(), 4, 0);
                        this.mTmpConstraintSet.connect(constraintLayout2.getId(), 6, 2131363281, 7, dimensionPixelSize);
                        this.mTmpConstraintSet.connect(constraintLayout2.getId(), 7, 0, 7, 0);
                    }
                }
            } else {
                constraintSet3.setVisibility(constraintLayout2.getId(), 8);
            }
        }
        this.mTmpConstraintSet.applyTo(this);
    }

    public final float updateSubTranslationY(float f, int i, Function1 function1, boolean z) {
        if (z) {
            LinearLayout linearLayout = this.mFirstTileContainer;
            if (linearLayout != null) {
                linearLayout.setTranslationY(0.0f);
            }
            LinearLayout linearLayout2 = this.mSecondTileContainer;
            if (linearLayout2 != null) {
                linearLayout2.setTranslationY(0.0f);
            }
        } else {
            LinearLayout linearLayout3 = this.mQsMediaPanelContainer;
            if (linearLayout3 != null && linearLayout3.getVisibility() == 0) {
                Float fValueOf = function1 != null ? Float.valueOf(((Number) function1.invoke(Integer.valueOf(i + 1))).floatValue()) : null;
                LinearLayout linearLayout4 = this.mFirstTileContainer;
                if (linearLayout4 != null) {
                    linearLayout4.setTranslationY(fValueOf != null ? fValueOf.floatValue() : 0.0f);
                }
                LinearLayout linearLayout5 = this.mSecondTileContainer;
                if (linearLayout5 != null) {
                    linearLayout5.setTranslationY(fValueOf != null ? fValueOf.floatValue() : 0.0f);
                }
                if (fValueOf != null) {
                    return fValueOf.floatValue();
                }
            }
        }
        return 0.0f;
    }

    public final void updateTileColor() {
        int size = this.mHlTiles.size();
        for (int i = 0; i < size; i++) {
            QSPanelControllerBase.TileRecord tileRecord = this.mHlTiles.get(i);
            OplusQSHighlightTileView oplusQSHighlightTileView = (OplusQSHighlightTileView) tileRecord.tileView;
            QSTile qSTile = tileRecord.tile;
            oplusQSHighlightTileView.onQsColorStateChanged(qSTile != null ? qSTile.getState() : null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0057  */
    /* JADX WARN: Code duplicated, block: B:33:0x0069  */
    /* JADX WARN: Code duplicated, block: B:35:0x006d  */
    public final void updateViewState(float f, float f2) {
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        LinearLayout linearLayout3 = this.mFirstTileContainer;
        if (linearLayout3 != null) {
            linearLayout3.setPivotX(linearLayout3.getWidth());
        }
        LinearLayout linearLayout4 = this.mFirstTileContainer;
        if (linearLayout4 != null) {
            linearLayout4.setPivotY(linearLayout4.getHeight() / 2);
        }
        LinearLayout linearLayout5 = this.mFirstTileContainer;
        if (linearLayout5 != null) {
            linearLayout5.setScaleX(f2);
        }
        LinearLayout linearLayout6 = this.mFirstTileContainer;
        if (linearLayout6 != null) {
            linearLayout6.setScaleY(f2);
        }
        LinearLayout linearLayout7 = this.mFirstTileContainer;
        if (linearLayout7 != null) {
            linearLayout7.setTransitionAlpha(f);
        }
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar()) {
            if (!this.isLargeFoldWithOpened) {
                FeatureOption.INSTANCE.getClass();
                if (!FeatureOption.isTablet()) {
                    linearLayout = this.mQsMediaPanelContainer;
                    if (linearLayout == null) {
                        linearLayout2 = this.mSecondTileContainer;
                        if (linearLayout2 != null) {
                            linearLayout2.setPivotX(linearLayout2.getWidth());
                        }
                    } else {
                        linearLayout2 = this.mSecondTileContainer;
                        if (linearLayout2 != null) {
                            linearLayout2.setPivotX(linearLayout2.getWidth());
                        }
                    }
                }
            }
            LinearLayout linearLayout8 = this.mSecondTileContainer;
            if (linearLayout8 != null) {
                linearLayout8.setPivotX(linearLayout8.getWidth());
            }
        } else {
            linearLayout = this.mQsMediaPanelContainer;
            if (linearLayout == null && linearLayout.getVisibility() == 0) {
                LinearLayout linearLayout9 = this.mSecondTileContainer;
                if (linearLayout9 != null) {
                    linearLayout9.setPivotX(0.0f);
                }
            } else {
                linearLayout2 = this.mSecondTileContainer;
                if (linearLayout2 != null) {
                    linearLayout2.setPivotX(linearLayout2.getWidth());
                }
            }
        }
        LinearLayout linearLayout10 = this.mSecondTileContainer;
        if (linearLayout10 != null) {
            linearLayout10.setPivotY(linearLayout10.getHeight() / 2);
        }
        LinearLayout linearLayout11 = this.mSecondTileContainer;
        if (linearLayout11 != null) {
            linearLayout11.setScaleX(f2);
        }
        LinearLayout linearLayout12 = this.mSecondTileContainer;
        if (linearLayout12 != null) {
            linearLayout12.setScaleY(f2);
        }
        LinearLayout linearLayout13 = this.mSecondTileContainer;
        if (linearLayout13 != null) {
            linearLayout13.setTransitionAlpha(f);
        }
        LinearLayout linearLayout14 = this.mQsMediaPanelContainer;
        if (linearLayout14 != null) {
            linearLayout14.setPivotX(linearLayout14.getWidth());
        }
        LinearLayout linearLayout15 = this.mQsMediaPanelContainer;
        if (linearLayout15 != null) {
            linearLayout15.setPivotY(linearLayout15.getHeight() / 2);
        }
        LinearLayout linearLayout16 = this.mQsMediaPanelContainer;
        if (linearLayout16 != null) {
            linearLayout16.setScaleX(f2);
        }
        LinearLayout linearLayout17 = this.mQsMediaPanelContainer;
        if (linearLayout17 != null) {
            linearLayout17.setScaleY(f2);
        }
        LinearLayout linearLayout18 = this.mQsMediaPanelContainer;
        if (linearLayout18 != null) {
            linearLayout18.setTransitionAlpha(f);
        }
        ConstraintLayout constraintLayout = this.mQsSeekBarContainer;
        if (constraintLayout != null) {
            constraintLayout.setPivotX(0.0f);
        }
        ConstraintLayout constraintLayout2 = this.mQsSeekBarContainer;
        if (constraintLayout2 != null) {
            constraintLayout2.setPivotY(constraintLayout2.getHeight() / 2);
        }
        ConstraintLayout constraintLayout3 = this.mQsSeekBarContainer;
        if (constraintLayout3 != null) {
            constraintLayout3.setScaleX(f2);
        }
        ConstraintLayout constraintLayout4 = this.mQsSeekBarContainer;
        if (constraintLayout4 != null) {
            constraintLayout4.setScaleY(f2);
        }
        ConstraintLayout constraintLayout5 = this.mQsSeekBarContainer;
        if (constraintLayout5 != null) {
            constraintLayout5.setTransitionAlpha(f);
        }
    }
}
