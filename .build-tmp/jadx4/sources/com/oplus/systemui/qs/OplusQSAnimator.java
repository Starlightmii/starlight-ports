package com.oplus.systemui.qs;

import android.content.res.Resources;
import android.os.Trace;
import android.view.View;
import androidx.slice.widget.RowView$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.TouchAnimator;
import com.android.systemui.qs.animator.IQSAnimator;
import com.android.systemui.qs.deprecated.PagedTileLayout;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.qs.deprecated.QSPanelController;
import com.android.systemui.qs.deprecated.QSPanelControllerBase;
import com.android.systemui.qs.deprecated.QuickQSPanel;
import com.android.systemui.qs.deprecated.QuickQSPanelController;
import com.android.systemui.qs.deprecated.SideLabelTileLayout;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplus.systemui.qs.base.tile.OplusQSTileBaseView;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSAnimator implements IQSAnimator, QSHost.Callback, PagedTileLayout.PageListener, TouchAnimator.Listener, View.OnLayoutChangeListener, View.OnAttachStateChangeListener {
    public TouchAnimator mAllPageFourthRowAlphaAnimator;
    public TouchAnimator mAllPageFourthRowTYAnimator;
    public TouchAnimator mAllPageSecondRowAlphaAnimator;
    public TouchAnimator mAllPageSecondRowTYAnimator;
    public TouchAnimator mAllPageThirdRowAlphaAnimator;
    public TouchAnimator mAllPageThirdRowTYAnimator;
    public final Executor mExecutor;
    public TouchAnimator mFirstPageFirstRowAlphaAnimator;
    public TouchAnimator mFirstPageFirstRowTXAnimator;
    public TouchAnimator mFirstPageFirstRowTYAnimator;
    public TouchAnimator mFirstPageQSTileLayoutAnimator;
    public final QSHost mHost;
    public TouchAnimator mLandPageFirstRowTYAnimator;
    public TouchAnimator mLandPageSecondRowAnimator;
    public float mLastPosition;
    public final AnonymousClass1 mNonFirstPageListener;
    public int mNumQuickTiles;
    public boolean mOnKeyguard;
    public TouchAnimator mOtherPageFirstRowAnimator;
    public TouchAnimator mOtherPageQQSPanelAlphaAnimator;
    public TouchAnimator mOtherPageQQSPanelTXAnimator;
    public final PagedTileLayout mPagedLayout;
    public TouchAnimator mQQSFifthTileAlphaAnimator;
    public TouchAnimator mQQSSixthTileAlphaAnimator;
    public TouchAnimator mQQSTileScaleAnimator;
    public final QSPanel mQsPanel;
    public final QSPanelController mQsPanelController;
    public final View mQsRootView;
    public final QuickQSPanelController mQuickQSPanelController;
    public final QuickQSPanel mQuickQsPanel;
    public final OplusQSAnimator$$ExternalSyntheticLambda0 mUpdateAnimators;
    public float mScaleXY = 0.884f;
    public final ArrayList mAllViews = new ArrayList();
    public final ArrayList mQuickQsViews = new ArrayList();
    public boolean mOnFirstPage = true;
    public int mFirstRowYDiff = 0;
    public boolean mNeedsAnimatorUpdate = false;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSAnimator$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends TouchAnimator.ListenerAdapter {
        public /* synthetic */ OplusQSAnimator this$0;

        public final void onAnimationAtEnd() {
            this.this$0.mQuickQsPanel.setVisibility(4);
        }

        public final void onAnimationAtStart() {
            this.this$0.mQuickQsPanel.setVisibility(0);
        }

        public final void onAnimationStarted() {
            this.this$0.mQuickQsPanel.setVisibility(0);
        }
    }

    public OplusQSAnimator(View view, QuickQSPanel quickQSPanel, QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, QSHost qSHost, Executor executor, QSPanel qSPanel) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mNonFirstPageListener = anonymousClass1;
        OplusQSAnimator$$ExternalSyntheticLambda0 oplusQSAnimator$$ExternalSyntheticLambda0 = new OplusQSAnimator$$ExternalSyntheticLambda0();
        oplusQSAnimator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mUpdateAnimators = oplusQSAnimator$$ExternalSyntheticLambda0;
        this.mQsRootView = view;
        this.mQuickQsPanel = quickQSPanel;
        this.mQsPanel = qSPanel;
        this.mQsPanelController = qSPanelController;
        this.mQuickQSPanelController = quickQSPanelController;
        this.mHost = qSHost;
        this.mExecutor = executor;
        qSHost.addCallback(this);
        View view2 = ((ViewController) qSPanelController).mView;
        if (view2 != null) {
            view2.addOnAttachStateChangeListener(this);
        }
        view.addOnLayoutChangeListener(this);
        if (qSPanelController.isAttachedToWindow()) {
            onViewAttachedToWindow(null);
        }
        PagedTileLayout tileLayout = qSPanelController.getTileLayout();
        if (tileLayout instanceof PagedTileLayout) {
            this.mPagedLayout = tileLayout;
        } else {
            LogUtil.w(LogUtil.TAG_QS, "OplusQSAnimator", "QS Not using page layout");
        }
        qSPanelController.setPageListener(this);
    }

    public static void getRelativePositionInt(int[] iArr, View view, View view2) {
        if (view == view2 || view == null) {
            return;
        }
        if (!view.getClass().equals(SideLabelTileLayout.class)) {
            iArr[0] = view.getLeft() + iArr[0];
            iArr[1] = view.getTop() + iArr[1];
        }
        if (view.getClass().equals(PagedTileLayout.class)) {
            iArr[1] = view.getPaddingTop() + iArr[1];
        }
        Object parent = view.getParent();
        if (parent instanceof View) {
            getRelativePositionInt(iArr, (View) parent, view2);
        }
    }

    public final void clearAnimationState() {
        int size = this.mAllViews.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.mAllViews.get(i);
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
        setQuickQsViewsVisible(0);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m("OplusQSAnimator: ", "    mOnFirstPage:", printWriter), this.mOnFirstPage, printWriter, "    mOnKeyguard:"), this.mOnKeyguard, printWriter, "    mNumQuickTiles:"), this.mNumQuickTiles, printWriter);
    }

    public final void onAnimationAtEnd() {
        this.mQuickQsPanel.setVisibility(4);
        setQuickQsViewsVisible(0);
    }

    public final void onAnimationAtStart() {
        this.mQuickQsPanel.setVisibility(0);
    }

    public final void onAnimationStarted() {
        updateQQSVisibility();
        if (this.mOnFirstPage) {
            setQuickQsViewsVisible(4);
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.mExecutor.execute(this.mUpdateAnimators);
    }

    public final void onPageChanged(boolean z) {
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("onPageChanged: isFirst=", ", mOnFirstPage=", z);
        sbM.append(this.mOnFirstPage);
        sbM.append(", mLastPosition=");
        sbM.append(this.mLastPosition);
        LogUtil.i(LogUtil.TAG_QS, "OplusQSAnimator", sbM.toString());
        if (this.mLastPosition == 0.0f && !this.mOnKeyguard) {
            z = true;
        }
        if (this.mOnFirstPage == z) {
            return;
        }
        if (z) {
            this.mQuickQsPanel.setTranslationY(0.0f);
        } else {
            clearAnimationState();
        }
        this.mOnFirstPage = z;
    }

    public final void onTilesChanged() {
        this.mExecutor.execute(this.mUpdateAnimators);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        updateAnimators();
        setPosition(this.mLastPosition);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.mHost.removeCallback(this);
        QSPanel qSPanel = this.mQsPanel;
        if (qSPanel != null) {
            qSPanel.setPageListener((PagedTileLayout.PageListener) null);
            this.mQsPanel.removeOnAttachStateChangeListener(this);
        }
        View view2 = this.mQsRootView;
        if (view2 != null) {
            view2.removeOnLayoutChangeListener(this);
        }
    }

    public void setOnKeyguard(boolean z) {
        this.mOnKeyguard = z;
        updateQQSVisibility();
        if (this.mOnKeyguard) {
            clearAnimationState();
        }
    }

    public void setPosition(float f) {
        if (this.mNeedsAnimatorUpdate) {
            updateAnimators();
        }
        if (this.mFirstPageFirstRowAlphaAnimator == null || this.mOnKeyguard || QSFragmentHelper.getInstance().forbidQsExpansion()) {
            return;
        }
        Trace.traceBegin(4096L, "OplusQSAnimator#setPosition#" + this.mLastPosition + "-" + f);
        this.mLastPosition = f;
        if (f == 0.0f && !this.mOnFirstPage) {
            LogUtil.i(LogUtil.TAG_QS, "OplusQSAnimator", "setPosition: force update to first page");
            onPageChanged(true);
        }
        boolean z = this.mOnFirstPage;
        TouchAnimator touchAnimator = this.mOtherPageFirstRowAnimator;
        if (z) {
            touchAnimator.setPosition(f);
            this.mAllPageSecondRowAlphaAnimator.setPosition(f);
            this.mAllPageThirdRowAlphaAnimator.setPosition(f);
            this.mAllPageFourthRowAlphaAnimator.setPosition(f);
            this.mQQSFifthTileAlphaAnimator.setPosition(f);
            this.mQQSSixthTileAlphaAnimator.setPosition(f);
            if (!QSFragmentHelper.getInstance().isCoveringOrCoveredQs()) {
                this.mQuickQsPanel.setAlpha(1.0f);
            }
            this.mFirstPageFirstRowAlphaAnimator.setPosition(f);
            this.mFirstPageQSTileLayoutAnimator.setPosition(f);
            this.mFirstPageFirstRowTXAnimator.setPosition(f);
            this.mFirstPageFirstRowTYAnimator.setPosition(f);
            this.mLandPageFirstRowTYAnimator.setPosition(f);
            this.mAllPageSecondRowTYAnimator.setPosition(f);
            this.mAllPageThirdRowTYAnimator.setPosition(f);
            this.mAllPageFourthRowTYAnimator.setPosition(f);
            this.mLandPageSecondRowAnimator.setPosition(f);
            this.mQQSTileScaleAnimator.setPosition(f);
        } else {
            touchAnimator.setPosition(f);
            this.mAllPageSecondRowAlphaAnimator.setPosition(f);
            this.mAllPageThirdRowAlphaAnimator.setPosition(f);
            this.mAllPageFourthRowAlphaAnimator.setPosition(f);
            this.mLandPageFirstRowTYAnimator.setPosition(f);
            this.mAllPageSecondRowTYAnimator.setPosition(f);
            this.mAllPageThirdRowTYAnimator.setPosition(f);
            this.mAllPageFourthRowTYAnimator.setPosition(f);
            this.mLandPageSecondRowAnimator.setPosition(f);
            if (!QSFragmentHelper.getInstance().isCoveringOrCoveredQs()) {
                this.mOtherPageQQSPanelAlphaAnimator.setPosition(f);
            }
            this.mOtherPageQQSPanelTXAnimator.setPosition(f);
        }
        Trace.traceEnd(4096L);
    }

    public void setQuickQsViewsVisible(int i) {
        float f = i == 0 ? 1.0f : 0.0f;
        int size = this.mQuickQsViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = this.mQuickQsViews.get(i2);
            if (obj instanceof OplusQSTileBaseView) {
                OplusQSTileBaseView oplusQSTileBaseView = (OplusQSTileBaseView) obj;
                oplusQSTileBaseView.getBackgroundView().setTransitionAlpha(f);
                oplusQSTileBaseView.getIcon().setTransitionAlpha(f);
            }
        }
    }

    public void setShowCollapsedOnKeyguard(boolean z) {
        updateQQSVisibility();
        setPosition(this.mLastPosition);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:66:0x0328  */
    /* JADX WARN: Code duplicated, block: B:69:0x032c  */
    /* JADX WARN: Code duplicated, block: B:71:0x032f  */
    public final void updateAnimators() {
        boolean z;
        TouchAnimator.Builder builder;
        TouchAnimator.Builder builder2;
        TouchAnimator.Builder builder3;
        TouchAnimator.Builder builder4;
        QSTileView qSTileView;
        TouchAnimator.Builder builder5;
        TouchAnimator.Builder builder6;
        TouchAnimator.Builder builder7;
        TouchAnimator.Builder builder8;
        TouchAnimator.Builder builder9;
        TouchAnimator.Builder builder10;
        TouchAnimator.Builder builder11;
        TouchAnimator.Builder builder12;
        TouchAnimator.Builder builder13;
        int i;
        char c;
        int columnCount;
        QSTileView qSTileView2;
        TouchAnimator.Builder builder14;
        TouchAnimator.Builder builder15;
        String str;
        int i2;
        Trace.traceBegin(4096L, "OplusQSAnimator#updateAnimators");
        this.mNeedsAnimatorUpdate = false;
        TouchAnimator.Builder builder16 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder17 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder18 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder19 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder20 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder21 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder22 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder23 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder24 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder25 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder26 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder27 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder28 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder29 = new TouchAnimator.Builder();
        int i3 = 0;
        TouchAnimator.Builder builder30 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder31 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder32 = builder26;
        if (this.mPagedLayout == null) {
            return;
        }
        Collection tiles = this.mHost.getTiles();
        TouchAnimator.Builder builder33 = builder16;
        int[] iArr = new int[2];
        int i4 = 2;
        TouchAnimator.Builder builder34 = builder30;
        TouchAnimator.Builder builder35 = builder28;
        double maxNumInFirstPage = this.mPagedLayout.getMaxNumInFirstPage();
        double columnCount2 = this.mPagedLayout.getColumnCount();
        clearAnimationState();
        this.mAllViews.clear();
        this.mQuickQsViews.clear();
        this.mNumQuickTiles = this.mQuickQsPanel.getNumQuickTiles();
        View tileLayout = this.mQsPanelController.getTileLayout();
        this.mAllViews.add(tileLayout);
        int measuredWidth = this.mQsRootView.getMeasuredWidth();
        Resources resources = this.mQsPanel.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131172135);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(2131172137);
        if (dimensionPixelSize > 0 && dimensionPixelSize2 > 0) {
            this.mScaleXY = dimensionPixelSize / dimensionPixelSize2;
        }
        if (DeviceUtil.isLandscape(this.mQsRootView.getContext())) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet() || DeviceUtil.shouldUseLargeFoldConfig(resources)) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = true;
        }
        Iterator it = tiles.iterator();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            z = z;
            if (!it.hasNext()) {
                break;
            }
            it = it;
            QSTile qSTile = (QSTile) it.next();
            TouchAnimator.Builder builder36 = builder18;
            builder29 = builder29;
            if (this.mHost.indexOf(qSTile.getTileSpec()) == 0 || this.mHost.indexOf(qSTile.getTileSpec()) == 1) {
                builder = builder19;
                builder2 = builder20;
                builder3 = builder22;
                builder4 = builder27;
                builder18 = builder36;
                builder32 = builder32;
                builder19 = builder;
                builder21 = builder21;
                builder33 = builder33;
                builder27 = builder4;
                builder23 = builder23;
                builder24 = builder24;
                i3 = 0;
                i4 = 2;
                builder34 = builder34;
                builder22 = builder3;
                builder20 = builder2;
                builder17 = builder17;
                i6 = i6;
                builder35 = builder35;
                builder31 = builder31;
            } else {
                ArrayList arrayList = ((QSPanelControllerBase) this.mQsPanelController).mRecords;
                int size = arrayList.size();
                TouchAnimator.Builder builder37 = builder19;
                int i8 = i3;
                while (true) {
                    if (i8 >= size) {
                        qSTileView = null;
                        break;
                    }
                    Object obj = arrayList.get(i8);
                    i8++;
                    ArrayList arrayList2 = arrayList;
                    QSPanelControllerBase.TileRecord tileRecord = (QSPanelControllerBase.TileRecord) obj;
                    int i9 = size;
                    if (tileRecord.tile == qSTile) {
                        qSTileView = tileRecord.tileView;
                        break;
                    } else {
                        size = i9;
                        arrayList = arrayList2;
                    }
                }
                if (qSTileView == null) {
                    LogUtil.i(LogUtil.TAG_QS, "OplusQSAnimator", "tileView is null " + qSTile.getTileSpec());
                    builder = builder37;
                    builder2 = builder20;
                    builder3 = builder22;
                    builder4 = builder27;
                    builder18 = builder36;
                    builder32 = builder32;
                    builder19 = builder;
                    builder21 = builder21;
                    builder33 = builder33;
                    builder27 = builder4;
                    builder23 = builder23;
                    builder24 = builder24;
                    i3 = 0;
                    i4 = 2;
                    builder34 = builder34;
                    builder22 = builder3;
                    builder20 = builder2;
                    builder17 = builder17;
                    i6 = i6;
                    builder35 = builder35;
                    builder31 = builder31;
                } else {
                    View iconView = qSTileView.getIcon().getIconView();
                    View view = this.mQsRootView;
                    TouchAnimator.Builder builder38 = builder22;
                    TouchAnimator.Builder builder39 = builder27;
                    TouchAnimator.Builder builder40 = builder17;
                    TouchAnimator.Builder builder41 = builder21;
                    int i10 = i6;
                    if (i10 < this.mQuickQSPanelController.getTileLayout().getNumVisibleTiles()) {
                        ArrayList arrayList3 = ((QSPanelControllerBase) this.mQuickQSPanelController).mRecords;
                        builder2 = builder20;
                        int size2 = arrayList3.size();
                        int i11 = i3;
                        while (true) {
                            if (i11 >= size2) {
                                qSTileView2 = null;
                                break;
                            }
                            Object obj2 = arrayList3.get(i11);
                            i11++;
                            int i12 = size2;
                            QSPanelControllerBase.TileRecord tileRecord2 = (QSPanelControllerBase.TileRecord) obj2;
                            ArrayList arrayList4 = arrayList3;
                            if (tileRecord2.tile == qSTile) {
                                qSTileView2 = tileRecord2.tileView;
                                break;
                            } else {
                                arrayList3 = arrayList4;
                                size2 = i12;
                            }
                        }
                        if (qSTileView2 == null) {
                            builder23 = builder23;
                            builder34 = builder34;
                            builder3 = builder38;
                            builder4 = builder39;
                            builder17 = builder40;
                            builder21 = builder41;
                            builder24 = builder24;
                            builder31 = builder31;
                            builder35 = builder35;
                            builder = builder37;
                            i6 = i10;
                            builder36 = builder36;
                            builder18 = builder36;
                            builder32 = builder32;
                            builder19 = builder;
                            builder21 = builder21;
                            builder33 = builder33;
                            builder27 = builder4;
                            builder23 = builder23;
                            builder24 = builder24;
                            i3 = 0;
                            i4 = 2;
                            builder34 = builder34;
                            builder22 = builder3;
                            builder20 = builder2;
                            builder17 = builder17;
                            i6 = i6;
                            builder35 = builder35;
                            builder31 = builder31;
                        } else {
                            int i13 = iArr[i3];
                            View iconView2 = qSTileView2.getIcon().getIconView();
                            int i14 = i3;
                            int[] iArr2 = {iconView2.getWidth() / 2, i14};
                            getRelativePositionInt(iArr2, iconView2, view);
                            iArr[i14] = iconView.getWidth() / 2;
                            iArr[1] = i14;
                            getRelativePositionInt(iArr, iconView, view);
                            int i15 = iArr[i14] - iArr2[i14];
                            int i16 = iArr[1] - iArr2[1];
                            if (i10 < this.mPagedLayout.getColumnCount()) {
                                int i17 = i4;
                                float[] fArr = new float[i17];
                                fArr[0] = 0.0f;
                                fArr[1] = i15;
                                builder23.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, fArr);
                                float[] fArr2 = new float[i17];
                                fArr2[0] = 0.0f;
                                fArr2[1] = i16;
                                builder24.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, fArr2);
                                if (z) {
                                    float f = 1.0f / this.mScaleXY;
                                    float[] fArr3 = new float[i17];
                                    fArr3[0] = 1.0f;
                                    fArr3[1] = f;
                                    builder31.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_SCALE_X, fArr3);
                                    float f2 = 1.0f / this.mScaleXY;
                                    float[] fArr4 = new float[i17];
                                    fArr4[0] = 1.0f;
                                    fArr4[1] = f2;
                                    builder31.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_SCALE_Y, fArr4);
                                }
                                float f3 = -i15;
                                float[] fArr5 = new float[i17];
                                fArr5[0] = f3;
                                fArr5[1] = 0.0f;
                                builder23.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, fArr5);
                                float[] fArr6 = new float[i17];
                                fArr6[0] = -i16;
                                fArr6[1] = 0.0f;
                                builder24.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, fArr6);
                                this.mFirstRowYDiff = i16;
                                i5 = iArr[0];
                                i7 = i5 - i13;
                                this.mQuickQsViews.add(qSTileView);
                                builder6 = builder38;
                                builder14 = builder40;
                                builder9 = builder41;
                            } else if (i10 < this.mQuickQsPanel.getTileLayout().getNumVisibleTiles()) {
                                int columnCount3 = (i10 - this.mPagedLayout.getColumnCount()) + 1;
                                builder23.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, new float[]{0.0f, ((i7 * columnCount3) + i5) - iArr2[0]});
                                builder24.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{0.0f, this.mFirstRowYDiff});
                                if (columnCount3 == 1) {
                                    builder15 = builder2;
                                    str = OplusVolumeTipsControler.PROPERTY_NAME_ALPHA;
                                    builder15.addFloat(qSTileView2, str, new float[]{1.0f, 0.0f});
                                } else {
                                    builder15 = builder2;
                                    str = OplusVolumeTipsControler.PROPERTY_NAME_ALPHA;
                                    if (columnCount3 == 2) {
                                        builder9 = builder41;
                                        builder9.addFloat(qSTileView2, str, new float[]{1.0f, 0.0f});
                                    }
                                    if (columnCount3 != 1) {
                                        i2 = 2;
                                        if (columnCount3 == 2) {
                                        }
                                        float[] fArr7 = new float[i2];
                                        // fill-array-data instruction
                                        fArr7[0] = 0.0f;
                                        fArr7[1] = 1.0f;
                                        builder14 = builder40;
                                        builder14.addFloat(qSTileView, str, fArr7);
                                        float[] fArr8 = new float[i2];
                                        fArr8[0] = -this.mFirstRowYDiff;
                                        fArr8[1] = 0.0f;
                                        builder39.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, fArr8);
                                        builder2 = builder15;
                                        builder6 = builder38;
                                    } else {
                                        i2 = 2;
                                    }
                                    if (z) {
                                        float f4 = 1.0f / this.mScaleXY;
                                        float[] fArr9 = new float[i2];
                                        fArr9[0] = 1.0f;
                                        fArr9[1] = f4;
                                        builder31.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_SCALE_X, fArr9);
                                        float f5 = 1.0f / this.mScaleXY;
                                        float[] fArr10 = new float[i2];
                                        fArr10[0] = 1.0f;
                                        fArr10[1] = f5;
                                        builder31.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_SCALE_Y, fArr10);
                                    }
                                    float[] fArr11 = new float[i2];
                                    // fill-array-data instruction
                                    fArr11[0] = 0.0f;
                                    fArr11[1] = 1.0f;
                                    builder14 = builder40;
                                    builder14.addFloat(qSTileView, str, fArr11);
                                    float[] fArr12 = new float[i2];
                                    fArr12[0] = -this.mFirstRowYDiff;
                                    fArr12[1] = 0.0f;
                                    builder39.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, fArr12);
                                    builder2 = builder15;
                                    builder6 = builder38;
                                }
                                builder9 = builder41;
                                if (columnCount3 != 1) {
                                    i2 = 2;
                                    if (columnCount3 == 2) {
                                    }
                                    float[] fArr13 = new float[i2];
                                    // fill-array-data instruction
                                    fArr13[0] = 0.0f;
                                    fArr13[1] = 1.0f;
                                    builder14 = builder40;
                                    builder14.addFloat(qSTileView, str, fArr13);
                                    float[] fArr14 = new float[i2];
                                    fArr14[0] = -this.mFirstRowYDiff;
                                    fArr14[1] = 0.0f;
                                    builder39.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, fArr14);
                                    builder2 = builder15;
                                    builder6 = builder38;
                                } else {
                                    i2 = 2;
                                }
                                if (z) {
                                    float f6 = 1.0f / this.mScaleXY;
                                    float[] fArr15 = new float[i2];
                                    fArr15[0] = 1.0f;
                                    fArr15[1] = f6;
                                    builder31.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_SCALE_X, fArr15);
                                    float f7 = 1.0f / this.mScaleXY;
                                    float[] fArr16 = new float[i2];
                                    fArr16[0] = 1.0f;
                                    fArr16[1] = f7;
                                    builder31.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_SCALE_Y, fArr16);
                                }
                                float[] fArr17 = new float[i2];
                                // fill-array-data instruction
                                fArr17[0] = 0.0f;
                                fArr17[1] = 1.0f;
                                builder14 = builder40;
                                builder14.addFloat(qSTileView, str, fArr17);
                                float[] fArr18 = new float[i2];
                                fArr18[0] = -this.mFirstRowYDiff;
                                fArr18[1] = 0.0f;
                                builder39.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, fArr18);
                                builder2 = builder15;
                                builder6 = builder38;
                            } else {
                                builder14 = builder40;
                                builder9 = builder41;
                                builder6 = builder38;
                                builder6.addFloat(qSTileView2, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{1.0f, 0.0f});
                                builder24.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{0.0f, i16});
                                builder23.addFloat(qSTileView2, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, new float[]{0.0f, this.mQsPanel.isLayoutRtl() ? i15 - measuredWidth : i15 + measuredWidth});
                            }
                            this.mAllViews.add(qSTileView2);
                            builder5 = builder23;
                            builder8 = builder24;
                            builder7 = builder14;
                            builder33 = builder33;
                            builder34 = builder34;
                            builder11 = builder37;
                            builder12 = builder39;
                            builder10 = builder31;
                            builder35 = builder35;
                        }
                    } else {
                        builder2 = builder20;
                        builder5 = builder23;
                        builder6 = builder38;
                        builder7 = builder40;
                        builder8 = builder24;
                        builder9 = builder41;
                        PagedTileLayout pagedTileLayout = this.mPagedLayout;
                        if (pagedTileLayout == null || (columnCount = pagedTileLayout.getColumnCount()) == 0 || i10 >= (((this.mNumQuickTiles + columnCount) - 1) / columnCount) * columnCount) {
                            builder10 = builder31;
                            if (maxNumInFirstPage > 0.0d) {
                                double d = i10;
                                if (d >= maxNumInFirstPage) {
                                    d %= maxNumInFirstPage;
                                }
                                int i18 = (int) (d / columnCount2);
                                if (i18 != 0) {
                                    if (i18 == 1) {
                                        builder35 = builder35;
                                        builder11 = builder37;
                                        i10 = i10;
                                        builder13 = builder36;
                                        builder7.addFloat(qSTileView, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
                                        int i19 = this.mFirstRowYDiff;
                                        if (z) {
                                            builder39.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{-i19, 0.0f});
                                        } else {
                                            builder39.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{i19 * (-2.2f), 0.0f});
                                            TouchAnimator.Builder builder42 = builder34;
                                            builder42.addFloat(qSTileView, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
                                            builder42.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_SCALE_X, new float[]{0.9f, 1.0f});
                                            builder42.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_SCALE_Y, new float[]{0.9f, 1.0f});
                                            builder12 = builder39;
                                            builder34 = builder42;
                                            builder32 = builder32;
                                            builder33 = builder33;
                                        }
                                    } else if (i18 != 2) {
                                        builder11 = builder37;
                                        builder11.addFloat(qSTileView, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
                                        builder29.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{-this.mFirstRowYDiff, 0.0f});
                                    } else {
                                        builder11 = builder37;
                                        builder36.addFloat(qSTileView, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
                                        builder35 = builder35;
                                        builder35.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{-this.mFirstRowYDiff, 0.0f});
                                        i10 = i10;
                                        builder13 = builder36;
                                    }
                                    builder33 = builder33;
                                    builder34 = builder34;
                                    builder12 = builder39;
                                    builder32 = builder32;
                                } else {
                                    builder35 = builder35;
                                    builder34 = builder34;
                                    builder11 = builder37;
                                    i10 = i10;
                                    builder12 = builder39;
                                    builder13 = builder36;
                                    builder33 = builder33;
                                    builder33.addFloat(qSTileView, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
                                    if (z) {
                                        builder32 = builder32;
                                        i = 0;
                                        c = 1;
                                    } else {
                                        i = 0;
                                        c = 1;
                                        builder32 = builder32;
                                        builder32.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{-this.mFirstRowYDiff, 0.0f});
                                    }
                                    iArr[i] = iconView.getWidth() / 2;
                                    iArr[c] = i;
                                    getRelativePositionInt(iArr, iconView, view);
                                }
                                this.mAllViews.add(qSTileView);
                                int i20 = i10 + 1;
                                builder18 = builder13;
                                builder32 = builder32;
                                builder19 = builder11;
                                builder35 = builder35;
                                builder21 = builder9;
                                builder33 = builder33;
                                builder27 = builder12;
                                builder23 = builder5;
                                builder24 = builder8;
                                builder31 = builder10;
                                i3 = 0;
                                i4 = 2;
                                builder34 = builder34;
                                builder22 = builder6;
                                builder20 = builder2;
                                i6 = i20;
                                builder17 = builder7;
                            }
                            builder12 = builder39;
                        } else {
                            builder7.addFloat(qSTileView, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
                            builder39.addFloat(qSTileView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{-this.mFirstRowYDiff, 0.0f});
                            this.mAllViews.add(qSTileView);
                            builder10 = builder31;
                        }
                        builder11 = builder37;
                        builder12 = builder39;
                    }
                    builder13 = builder36;
                    this.mAllViews.add(qSTileView);
                    int i21 = i10 + 1;
                    builder18 = builder13;
                    builder32 = builder32;
                    builder19 = builder11;
                    builder35 = builder35;
                    builder21 = builder9;
                    builder33 = builder33;
                    builder27 = builder12;
                    builder23 = builder5;
                    builder24 = builder8;
                    builder31 = builder10;
                    i3 = 0;
                    i4 = 2;
                    builder34 = builder34;
                    builder22 = builder6;
                    builder20 = builder2;
                    i6 = i21;
                    builder17 = builder7;
                }
            }
        }
        TouchAnimator.Builder builder43 = builder29;
        TouchAnimator.Builder builder44 = builder17;
        TouchAnimator.Builder builder45 = builder20;
        TouchAnimator.Builder builder46 = builder22;
        TouchAnimator.Builder builder47 = builder23;
        TouchAnimator.Builder builder48 = builder24;
        TouchAnimator.Builder builder49 = builder31;
        TouchAnimator.Builder builder50 = builder32;
        TouchAnimator.Builder builder51 = builder35;
        TouchAnimator.Builder builder52 = builder34;
        TouchAnimator.Builder builder53 = builder19;
        TouchAnimator.Builder builder54 = builder27;
        TouchAnimator.Builder builder55 = builder21;
        TouchAnimator.Builder builder56 = builder18;
        this.mQQSTileScaleAnimator = builder49.build();
        this.mOtherPageFirstRowAnimator = builder33.setStartDelay(0.2f).setEndDelay(0.6f).build();
        double d2 = maxNumInFirstPage / columnCount2;
        this.mAllPageSecondRowAlphaAnimator = builder44.setStartDelay(d2 > 2.0d ? 0.1468f : 0.5468f).setEndDelay(d2 > 2.0d ? 0.4064f : 0.0f).build();
        this.mAllPageThirdRowAlphaAnimator = builder56.setStartDelay(0.75f).build();
        this.mAllPageFourthRowAlphaAnimator = builder53.setStartDelay(0.14f).build();
        this.mLandPageFirstRowTYAnimator = builder50.build();
        this.mAllPageSecondRowTYAnimator = builder54.build();
        this.mAllPageThirdRowTYAnimator = builder51.build();
        this.mAllPageFourthRowTYAnimator = builder43.build();
        this.mLandPageSecondRowAnimator = builder52.setStartDelay(0.334f).build();
        this.mQQSFifthTileAlphaAnimator = builder45.build();
        this.mQQSSixthTileAlphaAnimator = builder55.build();
        this.mFirstPageFirstRowAlphaAnimator = builder46.setListener(this).build();
        TouchAnimator.Builder endDelay = builder25.setStartDelay(0.2f).setEndDelay(0.35f);
        endDelay.addFloat(tileLayout, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
        this.mFirstPageQSTileLayoutAnimator = endDelay.build();
        this.mFirstPageFirstRowTXAnimator = builder47.build();
        this.mFirstPageFirstRowTYAnimator = builder48.build();
        TouchAnimator.Builder builder57 = new TouchAnimator.Builder();
        builder57.addFloat(this.mQuickQsPanel, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{1.0f, 0.0f});
        this.mOtherPageQQSPanelAlphaAnimator = builder57.setListener(this.mNonFirstPageListener).setEndDelay(0.8f).build();
        TouchAnimator.Builder builder58 = new TouchAnimator.Builder();
        builder58.addFloat(this.mQuickQsPanel, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{0.0f, this.mFirstRowYDiff});
        this.mOtherPageQQSPanelTXAnimator = builder58.setEndDelay(0.3f).build();
        Trace.traceEnd(4096L);
    }

    public final void updateQQSVisibility() {
        this.mQuickQsPanel.setVisibility((this.mOnKeyguard || this.mLastPosition == 1.0f) ? 4 : 0);
    }
}
