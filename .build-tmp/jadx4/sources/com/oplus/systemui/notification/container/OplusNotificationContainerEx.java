package com.oplus.systemui.notification.container;

import android.content.Context;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationContainerEx {
    public Flow alphaForShadeAndQsExpansion(Flow flow) {
        return flow;
    }

    public Flow bouncerToGoneTransition(Flow flow) {
        return flow;
    }

    public Flow dozingToDreamingTransition(Flow flow) {
        return flow;
    }

    public Flow dozingToLockscreenTransition(Flow flow) {
        return flow;
    }

    public Flow dozingToOccludedTransition(Flow flow) {
        return flow;
    }

    public Flow dozingToPrimaryBouncerTransition(Flow flow) {
        return flow;
    }

    public Flow lockscreenToGoneTransition(Flow flow) {
        return flow;
    }

    public Flow lockscreenToOccludedTransition(Flow flow) {
        return flow;
    }

    public Flow lockscreenToPrimaryBouncerTransition(Flow flow) {
        return flow;
    }

    public Flow mapNotificationKeyguardAlphaFlow(Flow flow) {
        return flow;
    }

    public Flow occludedToLockscreenTransition(Flow flow) {
        return flow;
    }

    public Flow offToLockscreenTransition(Flow flow) {
        return flow;
    }

    public SharedNotificationContainerInteractor.ConfigurationBasedDimensions onConfigurationBasedDimensions(Context context, SharedNotificationContainerInteractor.ConfigurationBasedDimensions configurationBasedDimensions) {
        return configurationBasedDimensions;
    }

    public void onMaxAlphaForKeyguard(float f) {
    }

    public NotificationContainerBounds onNotificationContainerBounds(boolean z, NotificationContainerBounds notificationContainerBounds, int i, float f, boolean z2, float f2) {
        return notificationContainerBounds;
    }

    public Flow primaryBouncerToLockscreenTransition(Flow flow) {
        return flow;
    }

    public void setMaxAllowedKeyguardNotifications(int i) {
    }
}
