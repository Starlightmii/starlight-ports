package com.oplus.systemui.separate;

import android.content.pm.UserInfo;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import com.android.internal.util.UserIcons;
import com.android.keyguard.OplusLockIconView$;
import com.android.settingslib.drawable.UserIconDrawable;
import com.android.systemui.animation.Expandable;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.oplus.systemui.biometrics.OplusBiometricAuthController;
import com.oplus.systemui.qs.widget.CircleDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSQuickEntranceManager$handleUserClick$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $view;
    public /* synthetic */ OplusSeparateQSQuickEntranceManager this$0;

    public /* synthetic */ OplusSeparateQSQuickEntranceManager$handleUserClick$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Drawable drawableNewDrawable;
        int i = 1;
        switch (this.$r8$classId) {
            case 0:
                OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("handleUserClick: dismiss keyguard, keyguardIsShowing = ", "OplusSeparateQSQuickEntranceManager-->", "SystemUi--QuickSettings", oplusSeparateQSQuickEntranceManager.keyguardStateController.mShowing);
                }
                OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager2 = this.this$0;
                if (oplusSeparateQSQuickEntranceManager2._userStateChangedListener == null) {
                    2 r1 = new 2();
                    r1.this$0 = oplusSeparateQSQuickEntranceManager2;
                    VarHandle.storeStoreFence();
                    oplusSeparateQSQuickEntranceManager2._userStateChangedListener = r1;
                    UserSwitcherInteractor userSwitcherInteractor = (UserSwitcherInteractor) oplusSeparateQSQuickEntranceManager2.userInteractor.get();
                    UserSwitcherInteractor.UserCallback userCallback = this.this$0._userStateChangedListener;
                    userSwitcherInteractor.addCallback(userCallback != null ? userCallback : null);
                }
                this.this$0.showUserSwitchDialogRequestTime = SystemClock.elapsedRealtime();
                OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager3 = this.this$0;
                View view = (View) this.$view;
                OplusSeparateQSQuickEntranceManager$handleUserClick$1 oplusSeparateQSQuickEntranceManager$handleUserClick$1 = new OplusSeparateQSQuickEntranceManager$handleUserClick$1(2);
                oplusSeparateQSQuickEntranceManager$handleUserClick$1.this$0 = oplusSeparateQSQuickEntranceManager3;
                oplusSeparateQSQuickEntranceManager$handleUserClick$1.$view = view;
                VarHandle.storeStoreFence();
                oplusSeparateQSQuickEntranceManager3.showUserSwitchDialogRunnable = oplusSeparateQSQuickEntranceManager$handleUserClick$1;
                ((OplusBiometricAuthController) this.this$0.oplusBiometricAuthController.get()).showOrHideFingerprintIconTemporarily(false, "User Switch");
                break;
            case 1:
                ((UserSwitcherInteractor) this.this$0.userInteractor.get()).showUserSwitcher((Expandable) null, ((View) this.$view).getContext(), (View) this.$view);
                break;
            case 2:
                OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager4 = this.this$0;
                View view2 = (View) this.$view;
                OplusSeparateQSQuickEntranceManager$handleUserClick$1 oplusSeparateQSQuickEntranceManager$handleUserClick$2 = new OplusSeparateQSQuickEntranceManager$handleUserClick$1(i);
                oplusSeparateQSQuickEntranceManager$handleUserClick$2.this$0 = oplusSeparateQSQuickEntranceManager4;
                oplusSeparateQSQuickEntranceManager$handleUserClick$2.$view = view2;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThreadDelay(oplusSeparateQSQuickEntranceManager$handleUserClick$2, 0L);
                break;
            default:
                Drawable drawable = (Drawable) this.$view;
                if (drawable != null) {
                    ColorFilter colorFilter = drawable.getColorFilter();
                    Drawable.ConstantState constantState = ((Drawable) this.$view).getConstantState();
                    Drawable drawableMutate = (constantState == null || (drawableNewDrawable = constantState.newDrawable(this.this$0.context.getResources())) == null) ? null : drawableNewDrawable.mutate();
                    UserInfo userInfo = this.this$0.userTracker.getUserInfo();
                    if (!userInfo.isGuest() || (drawableMutate instanceof UserIconDrawable)) {
                        if (drawableMutate instanceof UserIconDrawable) {
                            ((UserIconDrawable) drawableMutate).setColorFilter(-1, PorterDuff.Mode.DST_IN);
                        } else if (colorFilter instanceof PorterDuffColorFilter) {
                            if (drawableMutate != null) {
                                drawableMutate.setColorFilter(((PorterDuffColorFilter) colorFilter).getColor(), PorterDuff.Mode.DST_IN);
                            }
                        } else if (drawableMutate != null) {
                            drawableMutate.setColorFilter(-1, PorterDuff.Mode.DST_IN);
                        }
                    } else if (drawableMutate != null) {
                        drawableMutate.setColorFilter(this.this$0.context.getResources().getColor(2131103759, null), PorterDuff.Mode.XOR);
                    }
                    if (drawableMutate != null) {
                        drawableMutate.setBounds(0, 0, drawableMutate.getIntrinsicWidth(), drawableMutate.getIntrinsicHeight());
                    }
                    this.this$0.icon = new CircleDrawable(this.this$0.context.getResources(), UserIcons.convertToBitmap(drawableMutate));
                    OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager5 = this.this$0;
                    oplusSeparateQSQuickEntranceManager5.description = oplusSeparateQSQuickEntranceManager5.context.getString(2131951942, this.this$0.userTracker.getUserInfo().name);
                    LogUtil.i$default(LogUtil.TAG_QS, "OplusSeparateQSQuickEntranceManager", "onUserInfoChanged icon: " + this.this$0.icon + ", userInfo: " + userInfo, null, 8);
                    Executor executor = this.this$0.uiExecutor;
                    OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager6 = this.this$0;
                    OplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1 oplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1 = new OplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1(0);
                    oplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1.this$0 = oplusSeparateQSQuickEntranceManager6;
                    VarHandle.storeStoreFence();
                    executor.execute(oplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1);
                } else {
                    OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager7 = this.this$0;
                    if (!oplusSeparateQSQuickEntranceManager7.reloadedUserInfo) {
                        oplusSeparateQSQuickEntranceManager7.userInfoController.reloadUserInfo();
                        this.this$0.reloadedUserInfo = true;
                        LogUtil.i$default(LogUtil.TAG_QS, "OplusSeparateQSQuickEntranceManager", "failed to get user info, try again", null, 8);
                    }
                }
                break;
        }
    }
}
