package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.view.OplusScreenDragUtil;
import android.view.OplusWindowManager;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.app.OplusSplitScreenObserver;
import com.oplus.splitscreen.OplusSplitScreenManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SmallScreenTile extends QSTileImpl {
    public boolean mClickSuccessed;
    public boolean mIsDocked;
    public boolean mIsInFreeFromMode;
    public final KeyguardStateController mKeyguard;
    public final AnonymousClass3 mKeyguardCallback;
    public boolean mListening;
    public final OplusSplitScreenObserver mOplusSplitScreenObserver;
    public int mOrientation;
    public final SmallScreenTile$$ExternalSyntheticLambda0 mRunnable;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.SmallScreenTile$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements KeyguardStateController.Callback {
        public /* synthetic */ SmallScreenTile this$0;

        public final void onKeyguardShowingChanged() {
            SmallScreenTile smallScreenTile = this.this$0;
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) smallScreenTile).TAG, "onKeyguardShowingChanged");
            smallScreenTile.refreshState((Object) null);
        }
    }

    public SmallScreenTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardStateController keyguardStateController) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mClickSuccessed = true;
        this.mIsDocked = false;
        this.mIsInFreeFromMode = false;
        this.mOrientation = 0;
        SmallScreenTile$$ExternalSyntheticLambda0 smallScreenTile$$ExternalSyntheticLambda0 = new SmallScreenTile$$ExternalSyntheticLambda0();
        smallScreenTile$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mRunnable = smallScreenTile$$ExternalSyntheticLambda0;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mKeyguardCallback = anonymousClass3;
        this.mOplusSplitScreenObserver = new OplusSplitScreenObserver() { // from class: com.oplus.systemui.qs.tiles.SmallScreenTile.4
            public void onStateChanged(String str, Bundle bundle) {
                if ("splitScreenModeChange".equals(str)) {
                    LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) SmallScreenTile.this).TAG, "split screen mode changed");
                    SmallScreenTile.this.refreshState((Object) null);
                }
            }
        };
        this.mKeyguard = keyguardStateController;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237222);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 119;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956571);
    }

    public final void handleClick(Expandable expandable) {
        boolean z = this.mIsDocked;
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
        if (!z && !this.mIsInFreeFromMode && this.mClickSuccessed && !keyguardStateControllerImpl.mShowing && this.mOrientation == 1) {
            this.mClickSuccessed = false;
            ((QSTileImpl) this).mHandler.postDelayed(this.mRunnable, 3000L);
            return;
        }
        String str = ((QSTileImpl) this).TAG;
        StringBuilder sb = new StringBuilder("handleTouchUp failure !! isDocked = ");
        sb.append(this.mIsDocked);
        sb.append(" mClickSuccessed ");
        sb.append(this.mClickSuccessed);
        sb.append(" KeyGuardisShowing= ");
        sb.append(keyguardStateControllerImpl.mShowing);
        sb.append(" mOrientation ");
        sb.append(this.mOrientation);
        sb.append("  mIsInFreeFromMode ");
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, str, sb, this.mIsInFreeFromMode);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        ((QSTileImpl) this).mHandler.removeCallbacks(this.mRunnable);
    }

    public final void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.mListening == z) {
            return;
        }
        this.mClickSuccessed = true;
        this.mListening = z;
        AnonymousClass3 anonymousClass3 = this.mKeyguardCallback;
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
        if (!z) {
            OplusSplitScreenManager.getInstance().unregisterSplitScreenObserver(this.mOplusSplitScreenObserver);
            keyguardStateControllerImpl.removeCallback(anonymousClass3);
        } else {
            OplusSplitScreenManager.getInstance().registerSplitScreenObserver(this.mOplusSplitScreenObserver);
            KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
            keyguardStateControllerImpl2.getClass();
            keyguardStateControllerImpl2.addCallback(anonymousClass3);
        }
    }

    public final void handleUpdateState(QSTile.State state, Object obj) {
        boolean zIsInSplitScreenMode;
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        this.mOrientation = ((QSTileImpl) this).mContext.getResources().getConfiguration().orientation;
        try {
            Uri uri = Util.ROM_UPDATE_CONTENT_URI;
            try {
                zIsInSplitScreenMode = OplusSplitScreenManager.getInstance().isInSplitScreenMode();
            } catch (Exception e) {
                LogUtil.e(LogUtil.TAG_COMMON, "Util", "isInMultiWindowMode e=", e);
                zIsInSplitScreenMode = false;
            }
            this.mIsDocked = zIsInSplitScreenMode;
            this.mIsInFreeFromMode = false;
            if (!zIsInSplitScreenMode) {
                this.mIsInFreeFromMode = new OplusWindowManager().isInFreeformMode();
            }
        } catch (RemoteException e2) {
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "getDockedStackSide  isInFreeformMode  error " + e2.getMessage());
        }
        String str = ((QSTileImpl) this).TAG;
        StringBuilder sb = new StringBuilder("SmallScreenTile isDocked = ");
        sb.append(this.mIsDocked);
        sb.append(" KeyGuardIsShowing= ");
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
        sb.append(keyguardStateControllerImpl.mShowing);
        sb.append(" mOrientation ");
        sb.append(this.mOrientation);
        sb.append("  mIsInFreeFromMode ");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_QS, str, sb, this.mIsInFreeFromMode);
        if (keyguardStateControllerImpl.mShowing || this.mIsDocked || this.mIsInFreeFromMode || this.mOrientation == 2) {
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237223);
        } else if (OplusScreenDragUtil.isDragState()) {
            ((QSTile.State) booleanState).state = 2;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237221);
        } else {
            ((QSTile.State) booleanState).state = 1;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237222);
        }
        if (((QSTile.AdapterState) booleanState).value != OplusScreenDragUtil.isDragState()) {
            OplusScreenDragUtil.isDragState();
        }
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131956571);
        ((QSTile.AdapterState) booleanState).value = OplusScreenDragUtil.isDragState();
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956571);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    public final boolean isAvailable() {
        return false;
    }

    public final QSTile.State newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = false;
        return booleanState;
    }
}
