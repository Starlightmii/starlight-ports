package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import android.content.Context;
import android.util.Log;
import com.android.keyguard.OplusLockIconView$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardLogger$$ExternalSyntheticOutline0;
import com.android.systemui.R;
import com.android.systemui.application.impl.SystemUIApplicationImpl$$ExternalSyntheticOutline0;
import com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class OplusHandleAnimatorController {
    public static final Companion Companion = new Companion();
    public static final String TAG = "HandleAnimatorController";
    private Context context;
    public boolean followHandInterruptLongPress;
    private OplusHandleFadedAnimator oplusHandleFadedAnimator;
    private OplusHandleFollowHandAnimator oplusHandleFollowHandAnimator;
    private OplusHandleLongPressAnimator oplusHandleLongPressAnimator;
    private OplusHandleOldAnimator oplusHandleOldAnimator;
    private OplusNavigationHandle oplusNavigationHandle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusHandleAnimatorController(Context context, OplusNavigationHandle oplusNavigationHandle) {
        this.context = context;
        this.oplusNavigationHandle = oplusNavigationHandle;
        context.getResources().getDimension(R.dimen.navigation_handle_translation_limit);
        this.oplusHandleFollowHandAnimator = new OplusHandleFollowHandAnimator(this.context, this.oplusNavigationHandle);
        this.oplusHandleLongPressAnimator = new OplusHandleLongPressAnimator(this.context, this.oplusNavigationHandle);
        this.oplusHandleOldAnimator = new OplusHandleOldAnimator(this.context, this.oplusNavigationHandle);
        this.oplusHandleFadedAnimator = new OplusHandleFadedAnimator(this.context, this.oplusNavigationHandle);
    }

    public final void doHandleAnimator(OplusHandleAnimType oplusHandleAnimType, float f) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            boolean zIsFollowHandAnimRunning = this.oplusHandleFollowHandAnimator.isFollowHandAnimRunning();
            boolean zIsLongPressAnimRunning = this.oplusHandleLongPressAnimator.isLongPressAnimRunning();
            boolean z = this.followHandInterruptLongPress;
            boolean zIsFollowHandXAnimRunning = this.oplusHandleFollowHandAnimator.isFollowHandXAnimRunning();
            boolean zIsFollowHandYAnimRunning = this.oplusHandleFollowHandAnimator.isFollowHandYAnimRunning();
            boolean zIsRunning = this.oplusHandleFadedAnimator.isRunning();
            StringBuilder sb = new StringBuilder("doHandleAnimator: ");
            sb.append(oplusHandleAnimType);
            sb.append(",isFollowHandAnimRunning: ");
            sb.append(zIsFollowHandAnimRunning);
            sb.append(",isLongPressAnimRunning:");
            KeyguardLogger$$ExternalSyntheticOutline0.m(",followHandInterruptLongPress:", ",isFollowHandXAnimRunning: ", sb, zIsLongPressAnimRunning, z);
            KeyguardLogger$$ExternalSyntheticOutline0.m(",isFollowHandYAnimRunning: ", ",isHandleFadedAnimRunning: ", sb, zIsFollowHandXAnimRunning, zIsFollowHandYAnimRunning);
            SystemUIApplicationImpl$$ExternalSyntheticOutline0.m("HandleAnimatorController-->", "SystemUi--NavBar", sb, zIsRunning);
        }
        switch (oplusHandleAnimType.ordinal()) {
            case 0:
                if (!this.oplusHandleFollowHandAnimator.isFollowHandAnimRunning()) {
                    this.oplusHandleFadedAnimator.end();
                    OplusHandleLongPressAnimator oplusHandleLongPressAnimator = this.oplusHandleLongPressAnimator;
                    oplusHandleLongPressAnimator.getClass();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                        OplusLockIconView$$ExternalSyntheticOutline0.m("HandleLongPressAnimatorStart, isEnlarge: ", "HandleLongPressAnimator-->", "SystemUi--NavBar", oplusHandleLongPressAnimator.isEnlarge);
                    }
                    if (!oplusHandleLongPressAnimator.isEnlarge) {
                        oplusHandleLongPressAnimator.animateLongPress(true);
                    }
                }
                break;
            case 1:
                if (this.oplusHandleLongPressAnimator.isLongPressAnimRunning()) {
                    this.oplusHandleLongPressAnimator.end();
                }
                break;
            case 2:
                if (this.oplusHandleLongPressAnimator.isLongPressAnimRunning() || this.followHandInterruptLongPress) {
                    if (!this.followHandInterruptLongPress) {
                        this.followHandInterruptLongPress = true;
                        this.oplusHandleLongPressAnimator.end();
                    }
                } else if (!this.oplusHandleFollowHandAnimator.isFollowHandYAnimRunning()) {
                    this.oplusHandleFollowHandAnimator.startFollowHandXAnimator(f);
                }
                break;
            case 3:
                if (this.oplusHandleFollowHandAnimator.isFollowHandAnimRunning()) {
                    OplusHandleFollowHandAnimator oplusHandleFollowHandAnimator = this.oplusHandleFollowHandAnimator;
                    oplusHandleFollowHandAnimator.isTouchUp = true;
                    oplusHandleFollowHandAnimator.startFollowHandAnimator(f, true);
                }
                break;
            case 4:
                if (this.oplusHandleLongPressAnimator.isLongPressAnimRunning() || this.followHandInterruptLongPress) {
                    if (!this.followHandInterruptLongPress) {
                        this.followHandInterruptLongPress = true;
                        this.oplusHandleLongPressAnimator.end();
                    }
                } else if (!this.oplusHandleFollowHandAnimator.isFollowHandXAnimRunning()) {
                    this.oplusHandleFollowHandAnimator.startFollowHandYAnimator(f);
                }
                break;
            case 5:
                if (!this.oplusHandleLongPressAnimator.isLongPressAnimRunning() && !this.oplusHandleOldAnimator.isLongPressAnimRunning()) {
                    this.oplusHandleFadedAnimator.start();
                    break;
                }
                break;
            case 6:
                this.oplusHandleFadedAnimator.end();
                break;
            default:
                LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
                break;
        }
    }

    public final void doHandleOldAnimator(OplusHandleOldAnimType oplusHandleOldAnimType) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "HandleAnimatorController-->".concat("doHandleOldAnimator: " + oplusHandleOldAnimType));
        }
        switch (oplusHandleOldAnimType.ordinal()) {
            case 0:
            case 1:
            case 2:
                this.oplusHandleOldAnimator.doTransitionAnimator(oplusHandleOldAnimType);
                break;
            case 3:
                this.oplusHandleOldAnimator.doTransitionResetAnimator();
                break;
            case 4:
                this.oplusHandleFadedAnimator.end();
                this.oplusHandleOldAnimator.startPressAnimation();
                break;
            case 5:
                this.oplusHandleOldAnimator.endLongPressAnimation();
                break;
            case 6:
                break;
            default:
                LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
                break;
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isFadedAnimatorRunning() {
        return this.oplusHandleFadedAnimator.isRunning();
    }

    public final boolean isHandleAnimatorRunning() {
        return this.oplusHandleLongPressAnimator.isLongPressAnimRunning() || this.oplusHandleFollowHandAnimator.isFollowHandAnimRunning() || this.oplusHandleOldAnimator.isLongPressAnimRunning() || this.oplusHandleOldAnimator.isTransitionAnimRunning();
    }

    public final boolean isLongPressAnimRunning() {
        return this.oplusHandleLongPressAnimator.isLongPressAnimRunning();
    }

    public final boolean isOldLongPressAnimRunning() {
        return this.oplusHandleOldAnimator.isLongPressAnimRunning();
    }

    public final boolean isOldTransitionAnimRunning() {
        return this.oplusHandleOldAnimator.isTransitionAnimRunning();
    }

    public final boolean isPressAnimatorRunning() {
        return this.oplusHandleLongPressAnimator.isLongPressAnimRunning() || this.oplusHandleOldAnimator.isLongPressAnimRunning();
    }

    public final void setContext(Context context) {
        this.context = context;
    }
}
