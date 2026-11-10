package com.oplus.systemui.qs.base.tile;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.qs.base.customizer.IQSCustomizer$EditType;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.model.InterestingViewFlag;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.tileimpl.OplusQSTileBaseViewImpl;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusQSTileBaseView extends QSTileView {
    public String mAccessibilityClass;
    public ImageView mBg;
    public TileDrawableWrapper mBgDrawable;
    public int mBgSize;
    public MotionEvent mCachedIconFrameEvent;
    public boolean mClicked;
    public boolean mCollapsedView;
    public IQSCustomizer$EditType mEidtType;
    public int mHighLightBgHeight;
    public int mHighLightIconPaddingStart;
    public QSIconView mIcon;
    public SpotLightIconFrame mIconFrame;
    public float mIconFrameEventOffsetX;
    public float mIconFrameEventOffsetY;
    public boolean mIsCustomizer;
    public boolean mIsHighLightForCustomizer;
    public boolean mIsLandscape;
    public int[] mLocInScreen;
    public PressFeedbackHelper.QsTilePressFeedback mQsTilePressFeedback;
    public QSTile.State mTempState;
    public int mTileIconState;
    public boolean mTileState;
    public boolean mTouchInIconFrame;
    public InterestingViewFlag mViewFlag;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class H extends Handler {
        public final /* synthetic */ OplusQSTileBaseViewImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public H(OplusQSTileBaseViewImpl oplusQSTileBaseViewImpl) {
            super(Looper.getMainLooper());
            this.this$0 = oplusQSTileBaseViewImpl;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what == 1) {
                this.this$0.handleStateChanged((QSTile.State) message.obj);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SpotLightIconFrame extends LaunchableFrameLayout implements IQSSpotLightConfig {
        public final /* synthetic */ OplusQSTileBaseViewImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SpotLightIconFrame(OplusQSTileBaseViewImpl oplusQSTileBaseViewImpl, Context context) {
            super(context);
            this.this$0 = oplusQSTileBaseViewImpl;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void dispatchDraw(Canvas canvas) {
            super/*android.widget.FrameLayout*/.dispatchDraw(canvas);
            SharedSpotLightEffect.draw(this, canvas);
        }

        public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
            return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
        }

        public int getSpotLightType() {
            return 1;
        }

        public final boolean hasOverlappingRendering() {
            return false;
        }

        public final boolean isViewSupportSpotLight() {
            return this.this$0.mEidtType != IQSCustomizer$EditType.BOTTOM;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void onAttachedToWindow() {
            super/*android.widget.FrameLayout*/.onAttachedToWindow();
            ViewParent parent = getParent();
            OplusQSTileBaseViewImpl oplusQSTileBaseViewImpl = this.this$0;
            if (parent == oplusQSTileBaseViewImpl) {
                oplusQSTileBaseViewImpl.updateIconResource();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void onDetachedFromWindow() {
            super/*android.widget.FrameLayout*/.onDetachedFromWindow();
            this.this$0.mQsTilePressFeedback.reset();
            OplusQSTileBaseViewImpl oplusQSTileBaseViewImpl = this.this$0;
            oplusQSTileBaseViewImpl.mTouchInIconFrame = false;
            MotionEvent motionEvent = oplusQSTileBaseViewImpl.mCachedIconFrameEvent;
            if (motionEvent != null) {
                motionEvent.recycle();
                oplusQSTileBaseViewImpl.mCachedIconFrameEvent = null;
            }
        }

        public void updateSpotLightPath(Path path, Object obj) {
            if (this.this$0.mEidtType == IQSCustomizer$EditType.HIGHLIGHT) {
                ((CornerOutlineProvider) StdQSResPool.highLightTileOutline.getValue()).updatePath(this.this$0.mBgDrawable, path, null, obj);
            } else {
                ((CornerOutlineProvider) StdQSResPool.tileOutline.getValue()).updatePath(this.this$0.mBgDrawable, path, null, obj);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StateChangeRunnable implements Runnable {
        public QSTile.State mState;
        public /* synthetic */ OplusQSTileBaseView this$0;

        public final boolean equals(Object obj) {
            return obj instanceof StateChangeRunnable;
        }

        public final int hashCode() {
            return StateChangeRunnable.class.hashCode();
        }

        @Override // java.lang.Runnable
        public final void run() {
            OplusQSTileBaseView oplusQSTileBaseView = this.this$0;
            QSTile.State state = this.mState;
            QSTile.State state2 = oplusQSTileBaseView.mTempState;
            if (state2 == null) {
                oplusQSTileBaseView.mTempState = state.copy();
            } else {
                state.copyTo(state2);
            }
            oplusQSTileBaseView.handleStateChanged(this.this$0.mTempState);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean animationsEnabled() {
        if (!isShown() || getAlpha() != 1.0f) {
            return false;
        }
        getLocationOnScreen(this.mLocInScreen);
        return this.mLocInScreen[1] >= (-getHeight());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.mTouchInIconFrame && actionMasked != 0) {
            return super/*android.widget.LinearLayout*/.dispatchTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            MotionEvent motionEvent3 = this.mCachedIconFrameEvent;
            if (motionEvent3 != null) {
                motionEvent3.recycle();
                this.mCachedIconFrameEvent = null;
            }
            if (this.mEidtType != IQSCustomizer$EditType.BOTTOM) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                boolean z = x >= ((float) this.mIconFrame.getLeft()) && x < ((float) this.mIconFrame.getRight()) && y >= ((float) this.mIconFrame.getTop()) && y < ((float) this.mIconFrame.getBottom());
                this.mTouchInIconFrame = z;
                if (z) {
                    this.mCachedIconFrameEvent = MotionEvent.obtain(motionEvent);
                    this.mIconFrameEventOffsetX = getScrollX() - this.mIconFrame.getLeft();
                    this.mIconFrameEventOffsetY = getScrollY() - this.mIconFrame.getTop();
                }
            } else {
                this.mTouchInIconFrame = false;
            }
        }
        if (this.mTouchInIconFrame && (motionEvent2 = this.mCachedIconFrameEvent) != null) {
            if (actionMasked != motionEvent2.getAction()) {
                this.mCachedIconFrameEvent.setAction(actionMasked);
            }
            this.mCachedIconFrameEvent.setLocation(motionEvent.getX() + this.mIconFrameEventOffsetX, motionEvent.getY() + this.mIconFrameEventOffsetY);
            SharedSpotLightEffect.onTouchEvent(this.mIconFrame, this.mCachedIconFrameEvent);
            if (actionMasked == 1 || actionMasked == 3) {
                this.mTouchInIconFrame = false;
                MotionEvent motionEvent4 = this.mCachedIconFrameEvent;
                if (motionEvent4 != null) {
                    motionEvent4.recycle();
                    this.mCachedIconFrameEvent = null;
                }
            }
        }
        return super/*android.widget.LinearLayout*/.dispatchTouchEvent(motionEvent);
    }

    public View getBackgroundView() {
        return this.mBg;
    }

    public int getBgSize() {
        return this.mBgSize;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getDetailY() {
        return (getHeight() / 2) + getTop();
    }

    public QSIconView getIcon() {
        return this.mIcon;
    }

    public int getQSTileSize() {
        return 0;
    }

    public View getSecondaryClickHostView() {
        return null;
    }

    public void handleQsColorStateChanged$1(QSTile.State state) {
        this.mIcon.updateIconColorImmediately();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void handleStateChanged(QSTile.State state) {
        boolean z;
        String str = state.spec;
        if (str != null && this.mClicked) {
            str.hashCode();
            switch (str) {
                case "rotation":
                    JankUtils.rotationSwitchSceneEnd(((LinearLayout) this).mContext);
                    break;
                case "cell":
                    JankUtils.cellularSwitchSceneEnd(((LinearLayout) this).mContext);
                    break;
                case "location":
                    JankUtils.locationSwitchSceneEnd(((LinearLayout) this).mContext);
                    break;
            }
        }
        state.functionName.getMainText();
        setClickable(state.state != 0);
        setContentDescription(state.contentDescription);
        setStateDescription(state.state == 2 ? getResources().getString(2131956739) : getResources().getString(2131956738));
        int i = state.state;
        this.mAccessibilityClass = i == 0 ? null : state.expandedAccessibilityClassName;
        if (i != this.mTileIconState && this.mClicked) {
            this.mClicked = false;
        }
        if ((state instanceof QSTile.BooleanState) && this.mTileState != (z = ((QSTile.AdapterState) ((QSTile.BooleanState) state)).value)) {
            this.mClicked = false;
            this.mTileState = z;
        }
        this.mTileIconState = i;
        refreshDrawableState();
        getIcon().setIcon(state, animationsEnabled());
    }

    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final void init(QSTile qSTile) {
        Expandable expandableFromView = Expandable.fromView(this.mIconFrame);
        View secondaryClickHostView = getSecondaryClickHostView();
        if (secondaryClickHostView != null) {
            OplusQSTileBaseView$$ExternalSyntheticLambda1 oplusQSTileBaseView$$ExternalSyntheticLambda1 = new OplusQSTileBaseView$$ExternalSyntheticLambda1(0);
            oplusQSTileBaseView$$ExternalSyntheticLambda1.f$0 = secondaryClickHostView;
            oplusQSTileBaseView$$ExternalSyntheticLambda1.f$1 = qSTile;
            oplusQSTileBaseView$$ExternalSyntheticLambda1.f$2 = expandableFromView;
            VarHandle.storeStoreFence();
            OplusQSTileBaseView$$ExternalSyntheticLambda1 oplusQSTileBaseView$$ExternalSyntheticLambda2 = new OplusQSTileBaseView$$ExternalSyntheticLambda1(1);
            oplusQSTileBaseView$$ExternalSyntheticLambda2.f$0 = secondaryClickHostView;
            oplusQSTileBaseView$$ExternalSyntheticLambda2.f$1 = qSTile;
            oplusQSTileBaseView$$ExternalSyntheticLambda2.f$2 = expandableFromView;
            VarHandle.storeStoreFence();
            ViewExtKt.preloadAndRelease(secondaryClickHostView, oplusQSTileBaseView$$ExternalSyntheticLambda1, oplusQSTileBaseView$$ExternalSyntheticLambda2);
        }
        OplusQSTileBaseView$$ExternalSyntheticLambda3 oplusQSTileBaseView$$ExternalSyntheticLambda3 = new OplusQSTileBaseView$$ExternalSyntheticLambda3();
        oplusQSTileBaseView$$ExternalSyntheticLambda3.f$0 = qSTile;
        oplusQSTileBaseView$$ExternalSyntheticLambda3.f$1 = expandableFromView;
        VarHandle.storeStoreFence();
        OplusQSTileBaseView$$ExternalSyntheticLambda4 oplusQSTileBaseView$$ExternalSyntheticLambda4 = new OplusQSTileBaseView$$ExternalSyntheticLambda4();
        oplusQSTileBaseView$$ExternalSyntheticLambda4.f$0 = qSTile;
        oplusQSTileBaseView$$ExternalSyntheticLambda4.f$1 = expandableFromView;
        oplusQSTileBaseView$$ExternalSyntheticLambda4.f$2 = secondaryClickHostView;
        VarHandle.storeStoreFence();
        OplusQSTileBaseView$$ExternalSyntheticLambda5 oplusQSTileBaseView$$ExternalSyntheticLambda5 = new OplusQSTileBaseView$$ExternalSyntheticLambda5();
        oplusQSTileBaseView$$ExternalSyntheticLambda5.f$0 = qSTile;
        oplusQSTileBaseView$$ExternalSyntheticLambda5.f$1 = expandableFromView;
        VarHandle.storeStoreFence();
        init(oplusQSTileBaseView$$ExternalSyntheticLambda3, oplusQSTileBaseView$$ExternalSyntheticLambda4, oplusQSTileBaseView$$ExternalSyntheticLambda5);
        if (!qSTile.isTileReady() || qSTile.getState() == null) {
            return;
        }
        onStateChanged(qSTile.getState());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.LinearLayout*/.onAttachedToWindow();
        boolean zIsLandscape = DeviceUtil.isLandscape(((LinearLayout) this).mContext);
        if (this.mIsLandscape != zIsLandscape) {
            this.mIsLandscape = zIsLandscape;
            updateIconResource();
        }
        List<SpanSize> list = QSConstant.tileType;
        onOutlineUpdate(list);
        onDrawableUpdate(list, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.widget.LinearLayout*/.onConfigurationChanged(configuration);
        boolean z = configuration.orientation == 2;
        if (this.mIsLandscape != z) {
            this.mIsLandscape = z;
            updateIconResource();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int[] onCreateDrawableState(int i) {
        InterestingViewFlag interestingViewFlag = this.mViewFlag;
        int[] drawableState = interestingViewFlag.getDrawableState();
        InterestingViewFlag interestingViewFlag2 = this.mViewFlag;
        QSTile.State state = this.mTempState;
        if (!interestingViewFlag2.setTileState(state != null ? state.state : 1) && drawableState != null) {
            return drawableState;
        }
        LogUtil.i(LogUtil.TAG_QS, "OplusQSTileBaseView", "onCreateDrawableState: viewFlag=" + interestingViewFlag);
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
        return iArrOnCreateDrawableState;
    }

    public void onDrawableUpdate(List list, boolean z) {
        if (list.contains(SpanCache.ONE_X_ONE)) {
            this.mBgDrawable.setDrawable(((QSDrawableBuilder) StdQSResPool.tileViewDrawable.getValue()).build(this.mBg, true, null));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
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
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
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

    /* JADX WARN: Multi-variable type inference failed */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super/*android.widget.LinearLayout*/.onLayout(z, i, i2, i3, i4);
    }

    public void onOutlineUpdate(List list) {
        if (list.contains(SpanCache.ONE_X_ONE)) {
            this.mBgDrawable.setPathProvider((CornerOutlineProvider) StdQSResPool.tileOutline.getValue());
        }
    }

    public void onQsColorStateChanged(QSTile.State state) {
        if (state != null) {
            OplusQSTileBaseView$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSTileBaseView$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            externalSyntheticLambda0.f$1 = state;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(externalSyntheticLambda0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onStateChanged(QSTile.State state) {
        QSTile.State stateCopy = state.copy();
        StateChangeRunnable stateChangeRunnable = new StateChangeRunnable();
        stateChangeRunnable.this$0 = this;
        stateChangeRunnable.mState = stateCopy;
        VarHandle.storeStoreFence();
        removeCallbacks(stateChangeRunnable);
        if (post(stateChangeRunnable)) {
            return;
        }
        LogUtil.i(LogUtil.TAG_QS, "OplusQSTileBaseView", state.spec + " tile is post fail");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean performClick() {
        this.mClicked = true;
        return super/*android.widget.LinearLayout*/.performClick();
    }

    public abstract void setBgSize();

    /* JADX WARN: Multi-variable type inference failed */
    public void setContext(Context context) {
        ((LinearLayout) this).mContext = context;
    }

    public void setEditType(IQSCustomizer$EditType iQSCustomizer$EditType) {
        this.mIsHighLightForCustomizer = iQSCustomizer$EditType == IQSCustomizer$EditType.HIGHLIGHT;
        this.mEidtType = iQSCustomizer$EditType;
        updateIconResource();
    }

    public void setPosition(int i) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void spiltActionForLayerOrNot() {
        Pair pair = ((QSTileView) this).mSplitViews;
        List list = (List) pair.first;
        List list2 = (List) pair.second;
        list.clear();
        list2.clear();
        if (BaseQSResPool.supportMixColor()) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                list.add(childAt);
                if (this.mIconFrame != childAt) {
                    list2.add(childAt);
                }
            }
        }
        if (list2.isEmpty()) {
            list.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append('[');
        sb.append("locInScreen=(" + this.mLocInScreen[0] + ", " + this.mLocInScreen[1] + ")");
        StringBuilder sb2 = new StringBuilder(", iconView=");
        sb2.append(this.mIcon);
        sb.append(sb2.toString());
        sb.append(", tileState=" + this.mTileState);
        sb.append(", mTileIconState=" + this.mTileIconState);
        sb.append(", mBgSize=" + this.mBgSize);
        sb.append(", tileView-size:(" + getWidth() + " , " + getHeight() + ")");
        sb.append(", mBgView-size:(" + this.mBg.getWidth() + " , " + this.mBg.getHeight() + ")");
        sb.append("]");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View updateAccessibilityOrder(View view) {
        setAccessibilityTraversalAfter(view.getId());
        return this;
    }

    public final void updateIconResource() {
        setBgSize();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mBg.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mIcon.getLayoutParams();
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.mIconFrame.getLayoutParams();
        if (this.mIsHighLightForCustomizer) {
            if (layoutParams == null || layoutParams.width != -1) {
                this.mBg.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
            }
            if (layoutParams2 == null || layoutParams2.width != this.mHighLightBgHeight || layoutParams2.getMarginStart() != this.mHighLightIconPaddingStart) {
                int i = this.mHighLightBgHeight;
                FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(i, i, 8388627);
                layoutParams4.setMarginStart(this.mHighLightIconPaddingStart);
                this.mIcon.setLayoutParams(layoutParams4);
            }
            if (layoutParams3 == null || layoutParams3.width != -1) {
                this.mIconFrame.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                return;
            }
            return;
        }
        if (layoutParams == null || layoutParams.width != this.mBgSize) {
            int i2 = this.mBgSize;
            this.mBg.setLayoutParams(new FrameLayout.LayoutParams(i2, i2, 17));
        }
        if (layoutParams2 == null || layoutParams2.width != -2) {
            this.mIcon.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        }
        if (layoutParams3 == null || layoutParams3.width != this.mBgSize) {
            int i3 = this.mBgSize;
            this.mIconFrame.setLayoutParams(new LinearLayout.LayoutParams(i3, i3));
        }
        int qSTileSize = getQSTileSize();
        float f = qSTileSize != 0 ? (this.mBgSize * 1.0f) / qSTileSize : 1.0f;
        this.mIcon.setScaleX(f);
        this.mIcon.setScaleY(f);
    }

    public FrameLayout getIconWithBackground() {
        return this.mIconFrame;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void init(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener) {
        setOnClickListener(onClickListener);
        setOnLongClickListener(onLongClickListener);
    }
}
