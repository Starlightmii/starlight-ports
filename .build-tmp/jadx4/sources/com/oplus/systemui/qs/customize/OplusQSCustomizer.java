package com.oplus.systemui.qs.customize;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.Dependency;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.android.systemui.qs.deprecated.PagedTileLayout$2$;
import com.android.systemui.qs.deprecated.QSImpl;
import com.android.systemui.statusbar.phone.LightBarController;
import com.coui.appcompat.indicator.COUIPageIndicator;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.qs.base.customizer.IQSCustomizer$EditType;
import com.oplus.systemui.qs.base.customizer.OplusItemAnimator;
import com.oplus.systemui.qs.base.customizer.OplusPagerDragHelper;
import com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager;
import com.oplus.systemui.qs.base.customizer.OplusPagerGridSnapHelper;
import com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.util.StrokeCornerUtils;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplus.systemui.qs.base.widget.OplusQSPageIndicator;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.mydevice.MyDevicePanel;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.notification.util.ScrimVisibleUtil;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSCustomizer extends LinearLayout implements QSTileQueryHelper.ColorTileStateListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final OplusQSPageIndicator mBottomIndicator;
    public final RelativeLayout mBottomPartLayout;
    public OplusQSDetailClipper mClipper;
    public final AnonymousClass10 mCollapseAnimationListener;
    public final FrameLayout mCustomizeContainer;
    public final OplusQSCustomizerToolbar mCustomizerToolbar;
    public boolean mCustomizing;
    public final AnonymousClass10 mExpandAnimationListener;
    public int mHorizontalOffset;
    public int mHorizontalOffsetHighLight;
    public int mHorizontalSidePadding;
    public int mHorizontalSidePaddingHighLight;
    public boolean mIsShown;
    public final OplusPagerGridLayoutManager mLayoutManagerBottom;
    public final OplusPagerGridLayoutManager mLayoutManagerHighLight;
    public final OplusPagerGridLayoutManager mLayoutManagerTop;
    public final LightBarController mLightBarController;
    public final TextView mLimitNumberHint;
    public final TextView mMoreFunctionLabel;
    public QSContainerController mNotifQsContainer;
    public boolean mOpening;
    public QS mQs;
    public final RecyclerView mRecyclerViewBottom;
    public final RecyclerView mRecyclerViewHighLight;
    public final RecyclerView mRecyclerViewTop;
    public Runnable mSaveRunnable;
    public final OplusTileAdapter mTileAdapterBottom;
    public final OplusTileAdapter mTileAdapterHighLight;
    public final OplusTileAdapter mTileAdapterTop;
    public final OplusQSPageIndicator mTopIndicator;
    public final AnonymousClass1 mTransparentShadowBuilder;
    public final View mTransparentView;
    public int mVerticalOffset;
    public int mVerticalOffsetBottom;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSCustomizer$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends View.DragShadowBuilder {
        @Override // android.view.View.DragShadowBuilder
        public final void onDrawShadow(Canvas canvas) {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSCustomizer$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements OplusQSPagerDragAdapter.Callback {
        public /* synthetic */ OplusQSCustomizer this$0;

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final void addRemoveTile(QSTileQueryHelper.OplusTileInfo oplusTileInfo) {
            OplusQSCustomizer oplusQSCustomizer = this.this$0;
            OplusQSCustomizer.m263$$Nest$mhandleAddRemoveTile(oplusQSCustomizer, oplusTileInfo, IQSCustomizer$EditType.HIGHLIGHT);
            oplusQSCustomizer.showMinCountHint();
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final boolean checkLimitCount() {
            return OplusQSCustomizer.m265$$Nest$mhandleCheckMinCount(this.this$0, IQSCustomizer$EditType.HIGHLIGHT);
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public QSTileQueryHelper.OplusTileInfo getEmptyTileInfo() {
            return null;
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final void shouldAddEmptyTileInfo() {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSCustomizer$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements OplusQSPagerDragAdapter.Callback {
        public /* synthetic */ OplusQSCustomizer this$0;

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final void addRemoveTile(QSTileQueryHelper.OplusTileInfo oplusTileInfo) {
            OplusQSCustomizer oplusQSCustomizer = this.this$0;
            OplusQSCustomizer.m263$$Nest$mhandleAddRemoveTile(oplusQSCustomizer, oplusTileInfo, IQSCustomizer$EditType.TOP);
            oplusQSCustomizer.showMinCountHint();
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final boolean checkLimitCount() {
            return OplusQSCustomizer.m265$$Nest$mhandleCheckMinCount(this.this$0, IQSCustomizer$EditType.TOP);
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public QSTileQueryHelper.OplusTileInfo getEmptyTileInfo() {
            return this.this$0.getEmptyTileInfo();
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final void shouldAddEmptyTileInfo() {
            int i = OplusQSCustomizer.$r8$clinit;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSCustomizer$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements OplusPagerGridLayoutManager.Callback {
        public /* synthetic */ OplusQSCustomizer this$0;

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public final void onPageScrollStateChanged() {
            this.this$0.mTopIndicator.getClass();
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public final void onPageScrolled(float f, int i) {
            this.this$0.mTopIndicator.setCurrentPosition(f, i);
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public final void onPageSelect(int i, int i2) {
            LogUtil.i(LogUtil.TAG_QS, "OplusQSCustomizer", TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i2, "top indicator onPageSelect:", " scrollState:"));
            OplusQSPageIndicator oplusQSPageIndicator = this.this$0.mTopIndicator;
            if (i2 == 0) {
                oplusQSPageIndicator.setCurrentPosition(i);
            } else {
                ((COUIPageIndicator) oplusQSPageIndicator).mModel.mAccessCurrentIndex = i;
            }
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public final void onPageSizeChanged(int i) {
            PagedTileLayout$2$.ExternalSyntheticOutline0.m(i, "top indicator onPageSizeChanged:", LogUtil.TAG_QS, "OplusQSCustomizer");
            COUIPageIndicator cOUIPageIndicator = this.this$0.mTopIndicator;
            Runnable externalSyntheticLambda0 = new OplusQSCustomizer$4$.ExternalSyntheticLambda0(0);
            ((OplusQSCustomizer$4$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$0 = this;
            ((OplusQSCustomizer$4$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$1 = i;
            VarHandle.storeStoreFence();
            cOUIPageIndicator.post(externalSyntheticLambda0);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSCustomizer$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements OplusQSPagerDragAdapter.Callback {
        public /* synthetic */ OplusQSCustomizer this$0;

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final void addRemoveTile(QSTileQueryHelper.OplusTileInfo oplusTileInfo) {
            OplusQSCustomizer oplusQSCustomizer = this.this$0;
            OplusQSCustomizer.m263$$Nest$mhandleAddRemoveTile(oplusQSCustomizer, oplusTileInfo, IQSCustomizer$EditType.BOTTOM);
            oplusQSCustomizer.showMinCountHint();
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final boolean checkLimitCount() {
            return OplusQSCustomizer.m265$$Nest$mhandleCheckMinCount(this.this$0, IQSCustomizer$EditType.BOTTOM);
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public QSTileQueryHelper.OplusTileInfo getEmptyTileInfo() {
            return null;
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter.Callback
        public final void shouldAddEmptyTileInfo() {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSCustomizer$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass6 implements OplusPagerGridLayoutManager.Callback {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQSCustomizer this$0;

        public /* synthetic */ AnonymousClass6(int i) {
            this.$r8$classId = i;
        }

        private final void onDragStart$com$oplus$systemui$qs$customize$OplusQSCustomizer$9(boolean z) {
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public void onLayoutComplete(RecyclerView recyclerView, int i, int i2) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
            int i3 = ((i - 1) * this.this$0.mVerticalOffsetBottom) + (i2 * i);
            if (layoutParams.height != i3) {
                layoutParams.height = recyclerView.getPaddingBottom() + recyclerView.getPaddingTop() + i3;
                recyclerView.setLayoutParams(layoutParams);
            }
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public void onPageScrollStateChanged() {
            this.this$0.mBottomIndicator.getClass();
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public void onPageScrolled(float f, int i) {
            this.this$0.mBottomIndicator.setCurrentPosition(f, i);
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public void onPageSelect(int i, int i2) {
            LogUtil.i(LogUtil.TAG_QS, "OplusQSCustomizer", TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i2, "bottom indicator onPageSelect:", " scrollState:"));
            OplusQSPageIndicator oplusQSPageIndicator = this.this$0.mBottomIndicator;
            if (i2 == 0) {
                oplusQSPageIndicator.setCurrentPosition(i);
            } else {
                ((COUIPageIndicator) oplusQSPageIndicator).mModel.mAccessCurrentIndex = i;
            }
        }

        @Override // com.oplus.systemui.qs.base.customizer.OplusPagerGridLayoutManager.Callback
        public void onPageSizeChanged(int i) {
            PagedTileLayout$2$.ExternalSyntheticOutline0.m(i, "bottom indicator onPageSizeChanged:", LogUtil.TAG_QS, "OplusQSCustomizer");
            COUIPageIndicator cOUIPageIndicator = this.this$0.mBottomIndicator;
            Runnable externalSyntheticLambda0 = new OplusQSCustomizer$4$.ExternalSyntheticLambda0(1);
            ((OplusQSCustomizer$4$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$0 = this;
            ((OplusQSCustomizer$4$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$1 = i;
            VarHandle.storeStoreFence();
            cOUIPageIndicator.post(externalSyntheticLambda0);
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleAddRemoveTile, reason: not valid java name */
    public static void m263$$Nest$mhandleAddRemoveTile(OplusQSCustomizer oplusQSCustomizer, QSTileQueryHelper.OplusTileInfo oplusTileInfo, IQSCustomizer$EditType iQSCustomizer$EditType) {
        if (iQSCustomizer$EditType == IQSCustomizer$EditType.HIGHLIGHT) {
            if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_TILE_HIGHLIGHT)) {
                LogUtil.i(LogUtil.TAG_QS, "OplusQSCustomizer", "quickly click");
                return;
            }
            int tilePosition = oplusQSCustomizer.mTileAdapterHighLight.getTilePosition(oplusTileInfo);
            if (tilePosition != -1) {
                oplusQSCustomizer.mTileAdapterHighLight.removeTile(tilePosition);
            }
            if (oplusQSCustomizer.mTileAdapterBottom.getTilePosition(oplusTileInfo) == -1) {
                oplusQSCustomizer.mTileAdapterBottom.addTile(oplusTileInfo, oplusQSCustomizer.mLayoutManagerBottom.getCurrentPageFirstPos());
            }
            int currentPageFirstPos = oplusQSCustomizer.mLayoutManagerTop.getCurrentPageFirstPos();
            if (!oplusQSCustomizer.mTileAdapterTop.getTile(currentPageFirstPos).getVisible()) {
                currentPageFirstPos = oplusQSCustomizer.mLayoutManagerTop.getPreviousPageFirstPos();
            }
            if (currentPageFirstPos != -1) {
                oplusQSCustomizer.mTileAdapterHighLight.addTile(oplusQSCustomizer.mTileAdapterTop.getTile(currentPageFirstPos), 1);
                oplusQSCustomizer.mTileAdapterTop.removeTile(currentPageFirstPos);
                return;
            }
            return;
        }
        if (iQSCustomizer$EditType == IQSCustomizer$EditType.TOP) {
            int tilePosition2 = oplusQSCustomizer.mTileAdapterTop.getTilePosition(oplusTileInfo);
            if (tilePosition2 != -1) {
                oplusQSCustomizer.mTileAdapterTop.removeTile(tilePosition2);
            }
            if (oplusQSCustomizer.mTileAdapterBottom.getTilePosition(oplusTileInfo) == -1) {
                oplusQSCustomizer.mTileAdapterBottom.addTile(oplusTileInfo, oplusQSCustomizer.mLayoutManagerBottom.getCurrentPageFirstPos());
                return;
            }
            return;
        }
        int tilePosition3 = oplusQSCustomizer.mTileAdapterBottom.getTilePosition(oplusTileInfo);
        if (tilePosition3 != -1) {
            oplusQSCustomizer.mTileAdapterBottom.removeTile(tilePosition3);
        }
        int emptyTileIndex = oplusQSCustomizer.getEmptyTileIndex();
        if (emptyTileIndex != -1) {
            oplusQSCustomizer.mTileAdapterTop.removeTile(emptyTileIndex);
        }
        if (oplusQSCustomizer.mTileAdapterTop.getTilePosition(oplusTileInfo) == -1) {
            int currentPageLastPos = oplusQSCustomizer.mLayoutManagerTop.getCurrentPageLastPos();
            OplusPagerGridLayoutManager oplusPagerGridLayoutManager = oplusQSCustomizer.mLayoutManagerTop;
            if ((oplusPagerGridLayoutManager.getCurrentPageLastPos() + 1) % oplusPagerGridLayoutManager.mOnePageSize != 0) {
                currentPageLastPos++;
            }
            oplusQSCustomizer.mTileAdapterTop.addTile(oplusTileInfo, currentPageLastPos);
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleCheckHighLightTileSize, reason: not valid java name */
    public static boolean m264$$Nest$mhandleCheckHighLightTileSize(OplusQSCustomizer oplusQSCustomizer) {
        int itemCount = oplusQSCustomizer.mTileAdapterHighLight.getItemCount() - 1;
        QSTileQueryHelper.OplusTileInfo tile = oplusQSCustomizer.mTileAdapterHighLight.getTile(itemCount);
        int tilePosition = oplusQSCustomizer.mTileAdapterTop.getTilePosition(tile);
        if (oplusQSCustomizer.mTileAdapterHighLight.getItemCount() > 2 && tile.getVisible() && tilePosition == -1) {
            oplusQSCustomizer.mTileAdapterTop.addTile(tile, oplusQSCustomizer.mLayoutManagerTop.getCurrentPageFirstPos());
            oplusQSCustomizer.mTileAdapterHighLight.removeTile(itemCount);
        }
        QSTileQueryHelper.OplusTileInfo tile2 = oplusQSCustomizer.mTileAdapterHighLight.getTile(oplusQSCustomizer.mTileAdapterHighLight.getItemCount() - 1);
        if (oplusQSCustomizer.mTileAdapterHighLight.getItemCount() < 2) {
            int i = 0;
            while (true) {
                if (i >= oplusQSCustomizer.mTileAdapterTop.getItemCount()) {
                    i = -1;
                    break;
                }
                tile2 = oplusQSCustomizer.mTileAdapterTop.getTile(i);
                if (tile2.getVisible()) {
                    break;
                }
                i++;
            }
            if (i != -1) {
                oplusQSCustomizer.mTileAdapterHighLight.addTile(tile2, 1);
                oplusQSCustomizer.mTileAdapterTop.removeTile(i);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleCheckMinCount, reason: not valid java name */
    public static boolean m265$$Nest$mhandleCheckMinCount(OplusQSCustomizer oplusQSCustomizer, IQSCustomizer$EditType iQSCustomizer$EditType) {
        int itemCount = oplusQSCustomizer.mTileAdapterTop.getItemCount();
        if (itemCount != 0) {
            OplusTileAdapter oplusTileAdapter = oplusQSCustomizer.mTileAdapterTop;
            if (oplusTileAdapter.getTile(oplusTileAdapter.getItemCount() - 1).getSpec() == null) {
                itemCount--;
            }
        }
        if (itemCount <= 8 && iQSCustomizer$EditType != IQSCustomizer$EditType.BOTTOM) {
            oplusQSCustomizer.showMinCountHint();
            return true;
        }
        TextView textView = oplusQSCustomizer.mMoreFunctionLabel;
        if (textView != null) {
            textView.setText(2131955622);
        }
        TextView textView2 = oplusQSCustomizer.mLimitNumberHint;
        if (textView2 == null || textView2.getVisibility() != 0) {
            return false;
        }
        oplusQSCustomizer.mLimitNumberHint.setVisibility(4);
        return false;
    }

    /* JADX INFO: renamed from: -$$Nest$msetCustomizerShowing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m266$$Nest$msetCustomizerShowing(OplusQSCustomizer oplusQSCustomizer) {
        oplusQSCustomizer.setCustomizerShowing(Boolean.FALSE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.qs.customize.OplusQSCustomizer$10] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.oplus.systemui.qs.customize.OplusQSCustomizer$10] */
    public OplusQSCustomizer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTransparentShadowBuilder = new AnonymousClass1();
        final int i = 0;
        this.mExpandAnimationListener = new AnimatorListenerAdapter(this) { // from class: com.oplus.systemui.qs.customize.OplusQSCustomizer.10
            public final /* synthetic */ OplusQSCustomizer this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                switch (i) {
                    case 0:
                        this.this$0.mOpening = false;
                        break;
                    default:
                        OplusQSCustomizer oplusQSCustomizer = this.this$0;
                        if (!oplusQSCustomizer.mIsShown) {
                            oplusQSCustomizer.setVisibility(8);
                        }
                        OplusQSCustomizer.m266$$Nest$msetCustomizerShowing(this.this$0);
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i) {
                    case 0:
                        OplusQSCustomizer oplusQSCustomizer = this.this$0;
                        if (oplusQSCustomizer.mIsShown) {
                            oplusQSCustomizer.setCustomizing(true);
                        }
                        this.this$0.mOpening = false;
                        break;
                    default:
                        this.this$0.setCustomizing(false);
                        OplusQSCustomizer oplusQSCustomizer2 = this.this$0;
                        if (!oplusQSCustomizer2.mIsShown) {
                            oplusQSCustomizer2.setVisibility(8);
                        }
                        OplusQSCustomizer.m266$$Nest$msetCustomizerShowing(this.this$0);
                        this.this$0.mTileAdapterTop.notifyDataSetChanged();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mCollapseAnimationListener = new AnimatorListenerAdapter(this) { // from class: com.oplus.systemui.qs.customize.OplusQSCustomizer.10
            public final /* synthetic */ OplusQSCustomizer this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                switch (i2) {
                    case 0:
                        this.this$0.mOpening = false;
                        break;
                    default:
                        OplusQSCustomizer oplusQSCustomizer = this.this$0;
                        if (!oplusQSCustomizer.mIsShown) {
                            oplusQSCustomizer.setVisibility(8);
                        }
                        OplusQSCustomizer.m266$$Nest$msetCustomizerShowing(this.this$0);
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i2) {
                    case 0:
                        OplusQSCustomizer oplusQSCustomizer = this.this$0;
                        if (oplusQSCustomizer.mIsShown) {
                            oplusQSCustomizer.setCustomizing(true);
                        }
                        this.this$0.mOpening = false;
                        break;
                    default:
                        this.this$0.setCustomizing(false);
                        OplusQSCustomizer oplusQSCustomizer2 = this.this$0;
                        if (!oplusQSCustomizer2.mIsShown) {
                            oplusQSCustomizer2.setVisibility(8);
                        }
                        OplusQSCustomizer.m266$$Nest$msetCustomizerShowing(this.this$0);
                        this.this$0.mTileAdapterTop.notifyDataSetChanged();
                        break;
                }
            }
        };
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(((LinearLayout) this).mContext, 2132020256);
        COUIThemeOverlay.getInstance().applyThemeOverlays(contextThemeWrapper);
        LogUtil.i(LogUtil.TAG_QS, "OplusQSCustomizer", "OplusQSCustomizer init contetx: " + context + " contextWrapper: " + contextThemeWrapper);
        LayoutInflater.from(context).inflate(2131559507, this);
        this.mCustomizeContainer = (FrameLayout) findViewById(2131362769);
        this.mTransparentView = findViewById(2131362775);
        OplusQSPageIndicator oplusQSPageIndicator = (OplusQSPageIndicator) findViewById(2131363445);
        this.mTopIndicator = oplusQSPageIndicator;
        OplusQSPageIndicator oplusQSPageIndicator2 = (OplusQSPageIndicator) findViewById(2131363442);
        this.mBottomIndicator = oplusQSPageIndicator2;
        RecyclerView recyclerViewFindViewById = findViewById(2131363744);
        this.mRecyclerViewHighLight = recyclerViewFindViewById;
        RecyclerView recyclerViewFindViewById2 = findViewById(2131363747);
        this.mRecyclerViewTop = recyclerViewFindViewById2;
        RecyclerView recyclerViewFindViewById3 = findViewById(2131363742);
        this.mRecyclerViewBottom = recyclerViewFindViewById3;
        this.mCustomizerToolbar = findViewById(2131362774);
        TextView textView = (TextView) findViewById(2131364007);
        this.mMoreFunctionLabel = textView;
        TextView textView2 = (TextView) findViewById(2131363730);
        this.mLimitNumberHint = textView2;
        textView2.setText(getResources().getString(2131955206, 10));
        this.mBottomPartLayout = (RelativeLayout) findViewById(2131362315);
        int integer = ((LinearLayout) this).mContext.getResources().getInteger(2131427752);
        int dimensionPixelOffset = ((LinearLayout) this).mContext.getResources().getDimensionPixelOffset(2131171608);
        this.mHorizontalOffsetHighLight = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171610);
        this.mHorizontalOffset = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171609);
        this.mVerticalOffset = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171611);
        this.mVerticalOffsetBottom = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171612);
        this.mHorizontalSidePaddingHighLight = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171775);
        this.mHorizontalSidePadding = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171774);
        Context context2 = ((LinearLayout) this).mContext;
        IQSCustomizer$EditType iQSCustomizer$EditType = IQSCustomizer$EditType.HIGHLIGHT;
        OplusTileAdapter oplusTileAdapter = new OplusTileAdapter(context2, iQSCustomizer$EditType);
        this.mTileAdapterHighLight = oplusTileAdapter;
        oplusTileAdapter.setNeedShowAppLabel(false);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        oplusTileAdapter.setEditModeCallback(anonymousClass2);
        recyclerViewFindViewById.getRecycledViewPool().setMaxRecycledViews(0, 15);
        recyclerViewFindViewById.setItemViewCacheSize(0);
        recyclerViewFindViewById.setAdapter(oplusTileAdapter);
        int dimensionPixelOffset2 = ((LinearLayout) this).mContext.getResources().getDimensionPixelOffset(2131171929);
        OplusPagerGridLayoutManager.Builder builder = new OplusPagerGridLayoutManager.Builder();
        builder.mColumns = 2;
        builder.mItemHeight = dimensionPixelOffset2;
        int i3 = this.mHorizontalOffsetHighLight;
        int i4 = this.mVerticalOffset;
        builder.mHorizontalOffset = i3;
        builder.mVerticalOffset = i4;
        OplusPagerGridLayoutManager.Builder editType = builder.setEditType(iQSCustomizer$EditType);
        editType.mHorizontalSidePadding = this.mHorizontalSidePadding;
        OplusPagerGridLayoutManager oplusPagerGridLayoutManagerBuild = editType.build();
        this.mLayoutManagerHighLight = oplusPagerGridLayoutManagerBuild;
        recyclerViewFindViewById.setLayoutManager(oplusPagerGridLayoutManagerBuild);
        new OplusPagerGridSnapHelper().attachToRecyclerView(recyclerViewFindViewById);
        WindowManager windowManager = (WindowManager) ((LinearLayout) this).mContext.getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getRealSize(point);
        }
        OplusItemAnimator oplusItemAnimator = new OplusItemAnimator();
        oplusItemAnimator.setMoveDuration(150L);
        oplusItemAnimator.setChangeDuration(300L);
        oplusItemAnimator.setMaxInPageTranslation(Math.min(point.x, point.y));
        recyclerViewFindViewById.setItemAnimator(oplusItemAnimator);
        Context context3 = ((LinearLayout) this).mContext;
        IQSCustomizer$EditType iQSCustomizer$EditType2 = IQSCustomizer$EditType.TOP;
        OplusTileAdapter oplusTileAdapter2 = new OplusTileAdapter(context3, iQSCustomizer$EditType2);
        this.mTileAdapterTop = oplusTileAdapter2;
        oplusTileAdapter2.setNeedShowAppLabel(false);
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        oplusTileAdapter2.setEditModeCallback(anonymousClass3);
        recyclerViewFindViewById2.getRecycledViewPool().setMaxRecycledViews(0, 15);
        recyclerViewFindViewById2.setItemViewCacheSize(0);
        recyclerViewFindViewById2.setAdapter(oplusTileAdapter2);
        OplusPagerGridLayoutManager.Builder builder2 = new OplusPagerGridLayoutManager.Builder();
        builder2.mColumns = integer;
        builder2.mItemHeight = dimensionPixelOffset;
        int i5 = this.mHorizontalOffset;
        int i6 = this.mVerticalOffset;
        builder2.mHorizontalOffset = i5;
        builder2.mVerticalOffset = i6;
        OplusPagerGridLayoutManager.Builder editType2 = builder2.setEditType(iQSCustomizer$EditType2);
        editType2.mHorizontalSidePadding = this.mHorizontalSidePadding;
        OplusPagerGridLayoutManager oplusPagerGridLayoutManagerBuild2 = editType2.build();
        this.mLayoutManagerTop = oplusPagerGridLayoutManagerBuild2;
        recyclerViewFindViewById2.setLayoutManager(oplusPagerGridLayoutManagerBuild2);
        new OplusPagerGridSnapHelper().attachToRecyclerView(recyclerViewFindViewById2);
        OplusItemAnimator oplusItemAnimator2 = new OplusItemAnimator();
        oplusItemAnimator2.setMoveDuration(150L);
        oplusItemAnimator2.setChangeDuration(300L);
        oplusItemAnimator2.setMaxInPageTranslation(Math.min(point.x, point.y));
        recyclerViewFindViewById2.setItemAnimator(oplusItemAnimator2);
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        oplusPagerGridLayoutManagerBuild2.setCallback(anonymousClass4);
        Context context4 = ((LinearLayout) this).mContext;
        IQSCustomizer$EditType iQSCustomizer$EditType3 = IQSCustomizer$EditType.BOTTOM;
        OplusTileAdapter oplusTileAdapter3 = new OplusTileAdapter(context4, iQSCustomizer$EditType3);
        this.mTileAdapterBottom = oplusTileAdapter3;
        oplusTileAdapter3.setNeedShowAppLabel(true);
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        oplusTileAdapter3.setEditModeCallback(anonymousClass5);
        int dimensionPixelOffset3 = ((LinearLayout) this).mContext.getResources().getDimensionPixelOffset(2131171607);
        OplusPagerGridLayoutManager.Builder builder3 = new OplusPagerGridLayoutManager.Builder();
        builder3.mColumns = integer;
        builder3.mItemHeight = dimensionPixelOffset3;
        int i7 = this.mHorizontalOffset;
        int i8 = this.mVerticalOffsetBottom;
        builder3.mHorizontalOffset = i7;
        builder3.mVerticalOffset = i8;
        OplusPagerGridLayoutManager.Builder editType3 = builder3.setEditType(iQSCustomizer$EditType3);
        editType3.mHorizontalSidePadding = this.mHorizontalSidePadding;
        OplusPagerGridLayoutManager oplusPagerGridLayoutManagerBuild3 = editType3.build();
        this.mLayoutManagerBottom = oplusPagerGridLayoutManagerBuild3;
        recyclerViewFindViewById3.getRecycledViewPool().setMaxRecycledViews(0, 15);
        recyclerViewFindViewById3.setItemViewCacheSize(0);
        recyclerViewFindViewById3.setAdapter(oplusTileAdapter3);
        recyclerViewFindViewById3.setLayoutManager(oplusPagerGridLayoutManagerBuild3);
        new OplusPagerGridSnapHelper().attachToRecyclerView(recyclerViewFindViewById3);
        OplusItemAnimator oplusItemAnimator3 = new OplusItemAnimator();
        oplusItemAnimator3.setMoveDuration(150L);
        oplusItemAnimator3.setChangeDuration(300L);
        oplusItemAnimator3.setMaxInPageTranslation(Math.min(point.x, point.y));
        recyclerViewFindViewById3.setItemAnimator(oplusItemAnimator3);
        AnonymousClass6 anonymousClass6 = new AnonymousClass6(i);
        anonymousClass6.this$0 = this;
        VarHandle.storeStoreFence();
        oplusPagerGridLayoutManagerBuild3.setCallback(anonymousClass6);
        this.mLightBarController = (LightBarController) Dependency.sDependency.getDependencyInner(LightBarController.class);
        AnonymousClass6 anonymousClass7 = new AnonymousClass6(1);
        anonymousClass7.this$0 = this;
        VarHandle.storeStoreFence();
        OplusPagerDragHelper oplusPagerDragHelper = new OplusPagerDragHelper(anonymousClass7);
        recyclerViewFindViewById.setOnDragListener(oplusPagerDragHelper);
        AnonymousClass6 anonymousClass8 = new AnonymousClass6(2);
        anonymousClass8.this$0 = this;
        VarHandle.storeStoreFence();
        OplusPagerDragHelper oplusPagerDragHelper2 = new OplusPagerDragHelper(anonymousClass8);
        recyclerViewFindViewById2.setOnDragListener(oplusPagerDragHelper2);
        AnonymousClass6 anonymousClass9 = new AnonymousClass6(3);
        anonymousClass9.this$0 = this;
        VarHandle.storeStoreFence();
        OplusPagerDragHelper oplusPagerDragHelper3 = new OplusPagerDragHelper(anonymousClass9);
        recyclerViewFindViewById3.setOnDragListener(oplusPagerDragHelper3);
        oplusPagerDragHelper.setTag("HIGHLIGHT");
        oplusPagerDragHelper2.setTag("TOP");
        oplusPagerDragHelper3.setTag("BOTTOM");
        oplusPagerDragHelper.addObserver(oplusPagerDragHelper2);
        oplusPagerDragHelper.addObserver(oplusPagerDragHelper3);
        oplusPagerDragHelper2.addObserver(oplusPagerDragHelper);
        oplusPagerDragHelper2.addObserver(oplusPagerDragHelper3);
        oplusPagerDragHelper3.addObserver(oplusPagerDragHelper2);
        oplusPagerDragHelper3.addObserver(oplusPagerDragHelper);
        oplusQSPageIndicator.setUseOriginalRTL(true);
        oplusQSPageIndicator2.setUseOriginalRTL(true);
        updateResources();
        if (textView != null) {
            textView.setTextAppearance(2130969765);
        }
    }

    private int getEmptyTileIndex() {
        return this.mTileAdapterTop.getEmptyTileIndex();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QSTileQueryHelper.OplusTileInfo getEmptyTileInfo() {
        QSTile.State state = new QSTile.State();
        state.state = 1;
        state.label = "null";
        state.contentDescription = "null";
        state.icon = null;
        QSTileQueryHelper.OplusTileInfo oplusTileInfo = new QSTileQueryHelper.OplusTileInfo((String) null, state, false);
        oplusTileInfo.setVisible(false);
        return oplusTileInfo;
    }

    private void setCustomizerShowing(Boolean bool) {
        QSContainerController qSContainerController = this.mNotifQsContainer;
        if (qSContainerController != null) {
            qSContainerController.setCustomizerShowing(bool.booleanValue(), 0L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            updateDragShadow(this.mTransparentShadowBuilder);
            cancelDragAndDrop();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public OplusTileAdapter getTileAdapterHighLight() {
        return this.mTileAdapterHighLight;
    }

    public OplusTileAdapter getTileAdapterTop() {
        return this.mTileAdapterTop;
    }

    public OplusQSCustomizerToolbar getToolbar() {
        return this.mCustomizerToolbar;
    }

    public final void hide(boolean z) {
        initClipper();
        if (this.mIsShown) {
            MetricsLogger.hidden(((LinearLayout) this).mContext, 358);
            this.mIsShown = false;
            this.mOpening = false;
            if (!z) {
                setCustomizing(false);
                this.mClipper.cancelAnimator();
                this.mClipper.resetStatus();
                setVisibility(8);
                setCustomizerShowing(Boolean.FALSE);
                return;
            }
            OplusQSDetailClipper oplusQSDetailClipper = this.mClipper;
            AnonymousClass10 anonymousClass10 = this.mCollapseAnimationListener;
            oplusQSDetailClipper.cancelAnimator();
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            oplusQSDetailClipper.mAlphaOutAnimator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(300L);
            ValueAnimator valueAnimator = oplusQSDetailClipper.mAlphaOutAnimator;
            Interpolator interpolator = OplusQSDetailClipper.CUSTOM_INTERPOLATOR;
            valueAnimator.setInterpolator(interpolator);
            oplusQSDetailClipper.mAlphaOutAnimator.addListener(oplusQSDetailClipper.mAlphaOutOnEnd);
            ValueAnimator valueAnimator2 = oplusQSDetailClipper.mAlphaOutAnimator;
            OplusQSDetailClipper$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSDetailClipper$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = oplusQSDetailClipper;
            VarHandle.storeStoreFence();
            valueAnimator2.addUpdateListener(externalSyntheticLambda0);
            oplusQSDetailClipper.mAlphaOutAnimator.start();
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
            oplusQSDetailClipper.mFadeOutAnimator = valueAnimatorOfFloat2;
            valueAnimatorOfFloat2.setDuration(300L);
            if (anonymousClass10 != null) {
                oplusQSDetailClipper.mFadeOutAnimator.addListener(anonymousClass10);
            }
            oplusQSDetailClipper.mFadeOutAnimator.addListener(oplusQSDetailClipper.mFadeOutOnEnd);
            oplusQSDetailClipper.mFadeOutAnimator.addListener(oplusQSDetailClipper.mAlphaAnimate);
            oplusQSDetailClipper.mFadeOutAnimator.setInterpolator(interpolator);
            ValueAnimator valueAnimator3 = oplusQSDetailClipper.mFadeOutAnimator;
            OplusQSDetailClipper$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new OplusQSDetailClipper$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda1.f$0 = oplusQSDetailClipper;
            VarHandle.storeStoreFence();
            valueAnimator3.addUpdateListener(externalSyntheticLambda1);
            oplusQSDetailClipper.mFadeOutAnimator.setStartDelay(100L);
            ScrimVisibleUtil.Companion.getInstance().setRunning(true);
            oplusQSDetailClipper.mFadeOutAnimator.start();
        }
    }

    public final void initAdapter() {
        LogUtil.d(LogUtil.TAG_QS, "OplusQSCustomizer", "initAdapter");
        this.mTileAdapterHighLight.setTilesChanged(false);
        this.mTileAdapterTop.setTilesChanged(false);
        this.mTileAdapterBottom.setTilesChanged(false);
        this.mRecyclerViewTop.scrollToPosition(0);
        this.mRecyclerViewBottom.scrollToPosition(0);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.oplus.systemui.qs.customize.OplusQSDetailClipper$2] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.oplus.systemui.qs.customize.OplusQSDetailClipper$2] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.oplus.systemui.qs.customize.OplusQSDetailClipper$2] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.oplus.systemui.qs.customize.OplusQSDetailClipper$2] */
    public final void initClipper() {
        if (this.mClipper == null) {
            FrameLayout frameLayout = this.mCustomizeContainer;
            final OplusQSDetailClipper oplusQSDetailClipper = new OplusQSDetailClipper();
            oplusQSDetailClipper.mAlphaAnimate = new OplusQSDetailClipper.AnonymousClass1();
            final int i = 0;
            oplusQSDetailClipper.mVisibleOnStart = new AnimatorListenerAdapter() { // from class: com.oplus.systemui.qs.customize.OplusQSDetailClipper.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i) {
                        case 0:
                            oplusQSDetailClipper.mFadeInAnimator = null;
                            break;
                        case 1:
                            oplusQSDetailClipper.mAlphaInAnimator = null;
                            break;
                        case 2:
                            oplusQSDetailClipper.mDetail.setVisibility(4);
                            oplusQSDetailClipper.mDetail.setAlpha(0.0f);
                            OplusQSDetailClipper oplusQSDetailClipper2 = oplusQSDetailClipper;
                            oplusQSDetailClipper2.mFadeOutAnimator = null;
                            oplusQSDetailClipper2.resetStatus();
                            oplusQSDetailClipper.mIsRunning = false;
                            break;
                        default:
                            oplusQSDetailClipper.mAlphaOutAnimator = null;
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    switch (i) {
                        case 0:
                            oplusQSDetailClipper.mDetail.setVisibility(0);
                            oplusQSDetailClipper.mIsRunning = true;
                            break;
                        default:
                            super.onAnimationStart(animator);
                            break;
                    }
                }
            };
            final int i2 = 1;
            oplusQSDetailClipper.mAlphaInOnEnd = new AnimatorListenerAdapter() { // from class: com.oplus.systemui.qs.customize.OplusQSDetailClipper.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i2) {
                        case 0:
                            oplusQSDetailClipper.mFadeInAnimator = null;
                            break;
                        case 1:
                            oplusQSDetailClipper.mAlphaInAnimator = null;
                            break;
                        case 2:
                            oplusQSDetailClipper.mDetail.setVisibility(4);
                            oplusQSDetailClipper.mDetail.setAlpha(0.0f);
                            OplusQSDetailClipper oplusQSDetailClipper2 = oplusQSDetailClipper;
                            oplusQSDetailClipper2.mFadeOutAnimator = null;
                            oplusQSDetailClipper2.resetStatus();
                            oplusQSDetailClipper.mIsRunning = false;
                            break;
                        default:
                            oplusQSDetailClipper.mAlphaOutAnimator = null;
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    switch (i2) {
                        case 0:
                            oplusQSDetailClipper.mDetail.setVisibility(0);
                            oplusQSDetailClipper.mIsRunning = true;
                            break;
                        default:
                            super.onAnimationStart(animator);
                            break;
                    }
                }
            };
            final int i3 = 2;
            oplusQSDetailClipper.mFadeOutOnEnd = new AnimatorListenerAdapter() { // from class: com.oplus.systemui.qs.customize.OplusQSDetailClipper.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i3) {
                        case 0:
                            oplusQSDetailClipper.mFadeInAnimator = null;
                            break;
                        case 1:
                            oplusQSDetailClipper.mAlphaInAnimator = null;
                            break;
                        case 2:
                            oplusQSDetailClipper.mDetail.setVisibility(4);
                            oplusQSDetailClipper.mDetail.setAlpha(0.0f);
                            OplusQSDetailClipper oplusQSDetailClipper2 = oplusQSDetailClipper;
                            oplusQSDetailClipper2.mFadeOutAnimator = null;
                            oplusQSDetailClipper2.resetStatus();
                            oplusQSDetailClipper.mIsRunning = false;
                            break;
                        default:
                            oplusQSDetailClipper.mAlphaOutAnimator = null;
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    switch (i3) {
                        case 0:
                            oplusQSDetailClipper.mDetail.setVisibility(0);
                            oplusQSDetailClipper.mIsRunning = true;
                            break;
                        default:
                            super.onAnimationStart(animator);
                            break;
                    }
                }
            };
            final int i4 = 3;
            oplusQSDetailClipper.mAlphaOutOnEnd = new AnimatorListenerAdapter() { // from class: com.oplus.systemui.qs.customize.OplusQSDetailClipper.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i4) {
                        case 0:
                            oplusQSDetailClipper.mFadeInAnimator = null;
                            break;
                        case 1:
                            oplusQSDetailClipper.mAlphaInAnimator = null;
                            break;
                        case 2:
                            oplusQSDetailClipper.mDetail.setVisibility(4);
                            oplusQSDetailClipper.mDetail.setAlpha(0.0f);
                            OplusQSDetailClipper oplusQSDetailClipper2 = oplusQSDetailClipper;
                            oplusQSDetailClipper2.mFadeOutAnimator = null;
                            oplusQSDetailClipper2.resetStatus();
                            oplusQSDetailClipper.mIsRunning = false;
                            break;
                        default:
                            oplusQSDetailClipper.mAlphaOutAnimator = null;
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    switch (i4) {
                        case 0:
                            oplusQSDetailClipper.mDetail.setVisibility(0);
                            oplusQSDetailClipper.mIsRunning = true;
                            break;
                        default:
                            super.onAnimationStart(animator);
                            break;
                    }
                }
            };
            oplusQSDetailClipper.mDetail = frameLayout;
            oplusQSDetailClipper.mFooter = QSFragmentHelper.getInstance().getQsFooter();
            oplusQSDetailClipper.mQSPanel = QSFragmentHelper.getInstance().getQSPanel();
            oplusQSDetailClipper.mQSBottom = QSFragmentHelper.getInstance().getQSBottom();
            oplusQSDetailClipper.mTileMedia = QSFragmentHelper.getInstance().getQsTileMediaContainer();
            oplusQSDetailClipper.mMyDevice = QSFragmentHelper.getInstance().getMyDevicePanel();
            oplusQSDetailClipper.mBackground = QSFragmentHelper.getInstance().getQsPanelBackground();
            oplusQSDetailClipper.mHeader = QSFragmentHelper.getInstance().getHeader();
            oplusQSDetailClipper.mNotificationScrollLayout = QSFragmentHelper.getInstance().getNotificationScrollLayout();
            oplusQSDetailClipper.mIsScaleMode = true;
            VarHandle.storeStoreFence();
            this.mClipper = oplusQSDetailClipper;
        }
    }

    @Override // android.view.View
    public final boolean isShown() {
        return this.mIsShown;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateResources();
    }

    public final void onCurrentTilesChanged(List list) {
        if (list.size() <= 2) {
            LogUtil.i(LogUtil.TAG_QS, "OplusQSCustomizer", "tiles size less than HIGHLIGHT_TILE_SIZE return.");
            return;
        }
        this.mTileAdapterHighLight.onTilesChanged(list.subList(0, 2));
        this.mTileAdapterTop.onTilesChanged(list.subList(2, list.size()));
        showMinCountHint();
    }

    @Override // android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        return true;
    }

    public final void onStockTilesChanged(List list) {
        this.mTileAdapterBottom.onTilesChanged(list);
    }

    public void setContainer(QSContainerController qSContainerController) {
        this.mNotifQsContainer = qSContainerController;
    }

    public void setCustomizing(boolean z) {
        this.mCustomizing = z;
        QSImpl qSImpl = this.mQs;
        if (qSImpl == null || !(qSImpl instanceof QSImpl)) {
            return;
        }
        qSImpl.notifyCustomizeChanged();
    }

    public void setKeyguardShowing(boolean z) {
        initClipper();
        if (z && this.mIsShown) {
            this.mClipper.cancelAnimator();
            this.mClipper.resetStatus();
        }
    }

    public void setMyDevicePanel(MyDevicePanel myDevicePanel) {
        OplusQSDetailClipper oplusQSDetailClipper = this.mClipper;
        if (oplusQSDetailClipper != null) {
            oplusQSDetailClipper.setMyDevicePanel(myDevicePanel);
        }
    }

    public void setQs(QS qs) {
        this.mQs = qs;
    }

    public void setSaveRunnable(Runnable runnable) {
        this.mSaveRunnable = runnable;
    }

    public final void show() {
        initClipper();
        if (this.mIsShown) {
            return;
        }
        MetricsLogger.visible(((LinearLayout) this).mContext, 358);
        this.mIsShown = true;
        this.mOpening = true;
        initAdapter();
        setVisibility(0);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            this.mRecyclerViewHighLight.setAdapter(this.mTileAdapterHighLight);
        }
        OplusQSDetailClipper oplusQSDetailClipper = this.mClipper;
        AnonymousClass10 anonymousClass10 = this.mExpandAnimationListener;
        oplusQSDetailClipper.cancelAnimator();
        if (oplusQSDetailClipper.mIsScaleMode) {
            oplusQSDetailClipper.mDetail.setScaleX(0.9f);
            oplusQSDetailClipper.mDetail.setScaleY(0.9f);
        }
        oplusQSDetailClipper.mDetail.setAlpha(0.0f);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        oplusQSDetailClipper.mFadeInAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(300L);
        if (anonymousClass10 != null) {
            oplusQSDetailClipper.mFadeInAnimator.addListener(anonymousClass10);
        }
        oplusQSDetailClipper.mFadeInAnimator.addListener(oplusQSDetailClipper.mVisibleOnStart);
        ValueAnimator valueAnimator = oplusQSDetailClipper.mFadeInAnimator;
        Interpolator interpolator = OplusQSDetailClipper.CUSTOM_INTERPOLATOR;
        valueAnimator.setInterpolator(interpolator);
        ValueAnimator valueAnimator2 = oplusQSDetailClipper.mFadeInAnimator;
        OplusQSDetailClipper$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSDetailClipper$.ExternalSyntheticLambda0(2);
        externalSyntheticLambda0.f$0 = oplusQSDetailClipper;
        VarHandle.storeStoreFence();
        valueAnimator2.addUpdateListener(externalSyntheticLambda0);
        oplusQSDetailClipper.mFadeInAnimator.start();
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        oplusQSDetailClipper.mAlphaInAnimator = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(133L);
        oplusQSDetailClipper.mAlphaInAnimator.addListener(oplusQSDetailClipper.mAlphaInOnEnd);
        oplusQSDetailClipper.mAlphaInAnimator.addListener(oplusQSDetailClipper.mAlphaAnimate);
        oplusQSDetailClipper.mAlphaInAnimator.setInterpolator(interpolator);
        ValueAnimator valueAnimator3 = oplusQSDetailClipper.mAlphaInAnimator;
        OplusQSDetailClipper$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new OplusQSDetailClipper$.ExternalSyntheticLambda0(3);
        externalSyntheticLambda1.f$0 = oplusQSDetailClipper;
        VarHandle.storeStoreFence();
        valueAnimator3.addUpdateListener(externalSyntheticLambda1);
        ScrimVisibleUtil.Companion.getInstance().setRunning(true);
        oplusQSDetailClipper.mAlphaInAnimator.start();
        setCustomizerShowing(Boolean.TRUE);
    }

    public final void showImmediately() {
        if (this.mIsShown) {
            return;
        }
        setVisibility(0);
        this.mIsShown = true;
        initAdapter();
        setCustomizing(true);
        setCustomizerShowing(Boolean.TRUE);
    }

    public final void showMinCountHint() {
        if (this.mLimitNumberHint != null) {
            int itemCount = this.mTileAdapterTop.getItemCount();
            if (itemCount != 0) {
                OplusTileAdapter oplusTileAdapter = this.mTileAdapterTop;
                if (oplusTileAdapter.getTile(oplusTileAdapter.getItemCount() - 1).getSpec() == null) {
                    itemCount--;
                }
            }
            this.mLimitNumberHint.setVisibility(itemCount == 8 ? 0 : 4);
        }
    }

    public final void updateLabelColor(int i) {
        boolean zIsTextNeedUseLightColorWhenLight = QsColorUtil.isTextNeedUseLightColorWhenLight(((LinearLayout) this).mContext, i);
        OplusQSPageIndicator oplusQSPageIndicator = this.mTopIndicator;
        if (!zIsTextNeedUseLightColorWhenLight) {
            oplusQSPageIndicator.setTraceDotColor(((LinearLayout) this).mContext.getColor(2131100936));
            this.mTopIndicator.setPageIndicatorDotsColor(((LinearLayout) this).mContext.getColor(2131100864));
            this.mBottomIndicator.setTraceDotColor(((LinearLayout) this).mContext.getColor(2131100936));
            this.mBottomIndicator.setPageIndicatorDotsColor(((LinearLayout) this).mContext.getColor(2131100864));
            this.mLimitNumberHint.setTextColor(getContext().getColor(2131101006));
            TextView textView = this.mMoreFunctionLabel;
            if (textView != null) {
                textView.setTextColor(((LinearLayout) this).mContext.getColor(2131100936));
                return;
            }
            return;
        }
        oplusQSPageIndicator.setTraceDotColor(-1);
        OplusQSPageIndicator oplusQSPageIndicator2 = this.mTopIndicator;
        int i2 = QsColorUtil.QS_PAGE_INDICATOR_UNSELECTED_COLOR;
        oplusQSPageIndicator2.setPageIndicatorDotsColor(i2);
        this.mBottomIndicator.setTraceDotColor(-1);
        this.mBottomIndicator.setPageIndicatorDotsColor(i2);
        this.mLimitNumberHint.setTextColor(QsColorUtil.TOOLBAR_SECONDARY_TEXT_COLOR);
        TextView textView2 = this.mMoreFunctionLabel;
        if (textView2 != null) {
            textView2.setTextColor(QsColorUtil.CARRIER_TEXT_LIGHT_COLOR);
        }
    }

    public final void updateResources() {
        float weight;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mLimitNumberHint.getLayoutParams();
        layoutParams.topMargin = 0;
        layoutParams.addRule(6, 2131363445);
        this.mLimitNumberHint.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mMoreFunctionLabel.getLayoutParams();
        layoutParams2.setMarginsRelative(((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171597), ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171598), ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171601), layoutParams2.bottomMargin);
        this.mMoreFunctionLabel.setLayoutParams(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mRecyclerViewBottom.getLayoutParams();
        layoutParams3.topMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171593);
        layoutParams3.addRule(3, 2131364007);
        layoutParams3.addRule(2, 2131363442);
        this.mRecyclerViewBottom.setLayoutParams(layoutParams3);
        int dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171527);
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.mCustomizeContainer.getLayoutParams();
        layoutParams4.topMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171796);
        this.mCustomizeContainer.setLayoutParams(layoutParams4);
        FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) this.mTransparentView.getLayoutParams();
        layoutParams5.setMarginStart(dimensionPixelSize);
        layoutParams5.setMarginEnd(dimensionPixelSize);
        this.mTransparentView.setLayoutParams(layoutParams5);
        int dimensionPixelOffset = ((LinearLayout) this).mContext.getResources().getDimensionPixelOffset(2131167779);
        int dimensionPixelOffset2 = ((LinearLayout) this).mContext.getResources().getDimensionPixelOffset(2131167778);
        LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) this.mCustomizerToolbar.getLayoutParams();
        layoutParams6.topMargin = 0;
        layoutParams6.height = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171793);
        layoutParams6.setMarginStart(dimensionPixelOffset);
        layoutParams6.setMarginEnd(dimensionPixelOffset2);
        this.mCustomizerToolbar.setLayoutParams(layoutParams6);
        int integer = ((LinearLayout) this).mContext.getResources().getInteger(2131427752);
        int dimensionPixelOffset3 = ((LinearLayout) this).mContext.getResources().getDimensionPixelOffset(2131171608);
        this.mLayoutManagerTop.updateItemHeightAndColumn(dimensionPixelOffset3, integer);
        int dimensionPixelOffset4 = ((LinearLayout) this).mContext.getResources().getDimensionPixelOffset(2131171608);
        this.mLayoutManagerBottom.updateItemHeightAndColumn(dimensionPixelOffset4, integer);
        int dimensionPixelSize2 = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171929);
        this.mLayoutManagerHighLight.updateItemHeightAndColumn(dimensionPixelSize2, 2);
        ConstraintLayout.LayoutParams layoutParams7 = this.mRecyclerViewHighLight.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams7).height = dimensionPixelSize2;
        this.mRecyclerViewHighLight.setLayoutParams(layoutParams7);
        this.mTileAdapterHighLight.setContext(((LinearLayout) this).mContext);
        LinearLayout.LayoutParams layoutParams8 = (LinearLayout.LayoutParams) this.mRecyclerViewTop.getLayoutParams();
        layoutParams8.height = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171795);
        layoutParams8.topMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171600);
        this.mRecyclerViewTop.setLayoutParams(layoutParams8);
        StringBuilder sb = new StringBuilder("itemHighLightHeight=");
        sb.append(dimensionPixelSize2);
        sb.append(" itemHeight=");
        RectList$.ExternalSyntheticOutline0.m(dimensionPixelOffset3, dimensionPixelOffset4, " itemBottomHeight=", " ", sb);
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(layoutParams8.height, LogUtil.TAG_NOTIFICATION, "OplusQSCustomizer", sb);
        View viewFindViewById = findViewById(2131363446);
        LinearLayout.LayoutParams layoutParams9 = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
        layoutParams9.topMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171595);
        layoutParams9.bottomMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171594);
        viewFindViewById.setLayoutParams(layoutParams9);
        this.mTopIndicator.setCurrentPosition(0);
        RelativeLayout.LayoutParams layoutParams10 = (RelativeLayout.LayoutParams) this.mBottomIndicator.getLayoutParams();
        layoutParams10.topMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171762);
        layoutParams10.bottomMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171761);
        this.mBottomIndicator.setLayoutParams(layoutParams10);
        this.mBottomIndicator.setCurrentPosition(0);
        this.mHorizontalSidePaddingHighLight = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171775);
        this.mHorizontalSidePadding = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171774);
        this.mHorizontalOffsetHighLight = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171610);
        this.mHorizontalOffset = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171609);
        this.mVerticalOffset = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171611);
        this.mVerticalOffsetBottom = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171612);
        this.mBottomIndicator.measure(0, 0);
        this.mBottomIndicator.getMeasuredHeight();
        ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171762);
        OplusPagerGridLayoutManager oplusPagerGridLayoutManager = this.mLayoutManagerHighLight;
        int i = this.mHorizontalOffsetHighLight;
        int i2 = this.mVerticalOffset;
        oplusPagerGridLayoutManager.mHorizontalOffset = i;
        oplusPagerGridLayoutManager.mVerticalOffset = i2;
        OplusPagerGridLayoutManager oplusPagerGridLayoutManager2 = this.mLayoutManagerTop;
        int i3 = this.mHorizontalOffset;
        oplusPagerGridLayoutManager2.mHorizontalOffset = i3;
        oplusPagerGridLayoutManager2.mVerticalOffset = i2;
        OplusPagerGridLayoutManager oplusPagerGridLayoutManager3 = this.mLayoutManagerBottom;
        int i4 = this.mVerticalOffsetBottom;
        oplusPagerGridLayoutManager3.mHorizontalOffset = i3;
        oplusPagerGridLayoutManager3.mVerticalOffset = i4;
        oplusPagerGridLayoutManager.mHorizontalSidePadding = this.mHorizontalSidePaddingHighLight;
        int i5 = this.mHorizontalSidePadding;
        oplusPagerGridLayoutManager2.mHorizontalSidePadding = i5;
        oplusPagerGridLayoutManager3.mHorizontalSidePadding = i5;
        RelativeLayout relativeLayout = this.mBottomPartLayout;
        Context context = getContext();
        RelativeLayout relativeLayout2 = this.mBottomPartLayout;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), 2131235820);
        if (!ScrimUtil.isLowGaussianLevel(context) && !QsColorUtil.isGlobalThemeApplied(context)) {
            BlurConfig blurConfig = new BlurConfig(0, 0, null, null, null, null, false, null, null, 0, null, 33554431);
            float dimensionPixelSize3 = context.getResources().getDimensionPixelSize(2131171620);
            OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, dimensionPixelSize3);
            if (roundParams != null) {
                dimensionPixelSize3 = roundParams.getRadius();
                weight = roundParams.getWeight();
            } else {
                weight = 3.0f;
            }
            blurConfig.setBlurRadius(context.getResources().getInteger(2131427741));
            blurConfig.setLeftTopCornerRadius(dimensionPixelSize3);
            blurConfig.setRightTopCornerRadius(dimensionPixelSize3);
            blurConfig.setLeftBottomCornerRadius(context.getResources().getDimension(2131172188));
            blurConfig.setRightBottomCornerRadius(context.getResources().getDimensionPixelSize(2131172188));
            blurConfig.setEnableStaticBlurCorner(true);
            blurConfig.setRadiusWeight(Float.valueOf(weight));
            blurConfig.setPlatformMixConfig(QSBlurConfigProvider.getStdInactiveBlurConfig(context.getResources().getConfiguration().isNightModeActive(), StrokeCornerUtils.isSupportStroke(), BaseQSResPool.getMirrorBlurScaleValue()));
            ViewBlurProxy viewBlurProxy = new ViewBlurProxy(relativeLayout2, blurConfig, null, null, null);
            viewBlurProxy.setBlurType(ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE);
            drawable = new AutoBlurDrawable(viewBlurProxy, drawable);
        }
        relativeLayout.setBackground(drawable);
        this.mBottomPartLayout.setPadding(0, 0, 0, ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171756));
        LinearLayout.LayoutParams layoutParams11 = (LinearLayout.LayoutParams) this.mBottomPartLayout.getLayoutParams();
        layoutParams11.topMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131171592);
        this.mBottomPartLayout.setLayoutParams(layoutParams11);
    }
}
