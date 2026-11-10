package com.oplus.systemui.qs.fake;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.OneShotPreDrawListener;
import com.android.systemui.panelanimation.PanelAnimationEx;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFakeStatusController$qsPanelExpandFractionListener$1 implements PanelAnimationEx.ExpandedFractionChangeListener {
    public /* synthetic */ OplusQSFakeStatusController this$0;

    public final void onFractionChanged(float f) {
        OplusQSFakeStatusController oplusQSFakeStatusController = this.this$0;
        if (oplusQSFakeStatusController.needUpdatePosition) {
            oplusQSFakeStatusController.needUpdatePosition = false;
            View view = oplusQSFakeStatusController.mView;
            OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1 oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1 = new OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1(3);
            oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1.this$0 = oplusQSFakeStatusController;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(view, oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1);
        }
        oplusQSFakeStatusController.rawFraction = f;
        if (oplusQSFakeStatusController.isTabletLandscape()) {
            return;
        }
        if (oplusQSFakeStatusController.isQsexpanded()) {
            FrameLayout frameLayout = oplusQSFakeStatusController.fakeStatusIconContainer;
            if (frameLayout != null) {
                frameLayout.setTranslationX(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.statusIconTranslationX);
            }
            FrameLayout frameLayout2 = oplusQSFakeStatusController.fakeStatusIconContainer;
            if (frameLayout2 != null) {
                frameLayout2.setTranslationY(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.statusIconTranslationY);
            }
            FrameLayout frameLayout3 = oplusQSFakeStatusController.fakePrivacyIconContainer;
            if (frameLayout3 != null) {
                frameLayout3.setTranslationX(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.statusIconTranslationX);
            }
            FrameLayout frameLayout4 = oplusQSFakeStatusController.fakePrivacyIconContainer;
            if (frameLayout4 != null) {
                frameLayout4.setTranslationY(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.statusIconTranslationY);
            }
            ConstraintLayout constraintLayout = oplusQSFakeStatusController.fakeRight;
            if (constraintLayout != null) {
                constraintLayout.setTranslationY(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.qsExpandedY);
            }
            oplusQSFakeStatusController.statusBarHeader.getView().setTranslationY(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.qsExpandedY);
            ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
            if (mClockContainer != null) {
                mClockContainer.setTranslationY((oplusQSFakeStatusController.differenceTranslationY + oplusQSFakeStatusController.notificationIconTranslationY) * (oplusQSFakeStatusController.rawFraction - 1.0f));
            }
            ViewGroup mClockContainer2 = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
            if (mClockContainer2 != null) {
                mClockContainer2.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.clockTranslationX);
            }
            ViewGroup mStatusIconsView = oplusQSFakeStatusController.statusBarHeader.getMStatusIconsView();
            if (mStatusIconsView != null) {
                mStatusIconsView.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationX);
            }
            ViewGroup mStatusIconsView2 = oplusQSFakeStatusController.statusBarHeader.getMStatusIconsView();
            if (mStatusIconsView2 != null) {
                mStatusIconsView2.setTranslationY((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationY);
            }
            ViewGroup viewGroup = oplusQSFakeStatusController.quickStatus;
            if (viewGroup != null) {
                viewGroup.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationX);
            }
            ViewGroup viewGroup2 = oplusQSFakeStatusController.quickStatus;
            if (viewGroup2 != null) {
                viewGroup2.setTranslationY((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationY);
                return;
            }
            return;
        }
        OplusQSFakeStatusController.access$updateClockScale(oplusQSFakeStatusController, f);
        FrameLayout frameLayout5 = oplusQSFakeStatusController.fakeClockContainer;
        if (frameLayout5 != null) {
            frameLayout5.setTranslationX(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.clockTranslationX);
        }
        FrameLayout frameLayout6 = oplusQSFakeStatusController.fakeNotificationIconContainer;
        if (frameLayout6 != null) {
            frameLayout6.setTranslationX(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.notificationIconTranslationX);
        }
        FrameLayout frameLayout7 = oplusQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout7 != null) {
            frameLayout7.setTranslationX(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.statusIconTranslationX);
        }
        FrameLayout frameLayout8 = oplusQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout8 != null) {
            frameLayout8.setTranslationY(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.statusIconTranslationY);
        }
        FrameLayout frameLayout9 = oplusQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout9 != null) {
            frameLayout9.setTranslationX(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.statusIconTranslationX);
        }
        FrameLayout frameLayout10 = oplusQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout10 != null) {
            frameLayout10.setTranslationY(oplusQSFakeStatusController.rawFraction * oplusQSFakeStatusController.statusIconTranslationY);
        }
        ConstraintLayout constraintLayout2 = oplusQSFakeStatusController.fakeLeft;
        if (constraintLayout2 != null) {
            constraintLayout2.setTranslationY((oplusQSFakeStatusController.differenceTranslationY + oplusQSFakeStatusController.notificationIconTranslationY) * oplusQSFakeStatusController.rawFraction);
        }
        AppCompatTextView appCompatTextView = oplusQSFakeStatusController.splitCarrier;
        if (appCompatTextView != null) {
            appCompatTextView.setTranslationY((oplusQSFakeStatusController.differenceTranslationY + oplusQSFakeStatusController.notificationIconTranslationY) * (oplusQSFakeStatusController.rawFraction - 1.0f));
        }
        if (!oplusQSFakeStatusController.isFakeClockGone) {
            View dateView = oplusQSFakeStatusController.oplusQSFooterViewController.getDateView();
            if (dateView != null) {
                dateView.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.notificationIconTranslationX);
            }
            View clockView = oplusQSFakeStatusController.oplusQSFooterViewController.getClockView();
            if (clockView != null) {
                clockView.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.clockTranslationX);
            }
        }
        View dateView2 = oplusQSFakeStatusController.oplusQSFooterViewController.getDateView();
        if (dateView2 != null) {
            dateView2.setTranslationY((oplusQSFakeStatusController.differenceTranslationY + oplusQSFakeStatusController.notificationIconTranslationY) * (oplusQSFakeStatusController.rawFraction - 1.0f));
        }
        View clockView2 = oplusQSFakeStatusController.oplusQSFooterViewController.getClockView();
        if (clockView2 != null) {
            clockView2.setTranslationY((oplusQSFakeStatusController.differenceTranslationY + oplusQSFakeStatusController.notificationIconTranslationY) * (oplusQSFakeStatusController.rawFraction - 1.0f));
        }
        ViewGroup mClockContainer3 = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer3 != null) {
            mClockContainer3.setTranslationY((oplusQSFakeStatusController.differenceTranslationY + oplusQSFakeStatusController.notificationIconTranslationY) * (oplusQSFakeStatusController.rawFraction - 1.0f));
        }
        ViewGroup mClockContainer4 = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer4 != null) {
            mClockContainer4.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.clockTranslationX);
        }
        AppCompatTextView appCompatTextView2 = oplusQSFakeStatusController.splitCarrier;
        if (appCompatTextView2 != null) {
            appCompatTextView2.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.clockTranslationX);
        }
        ViewGroup mStatusIconsView3 = oplusQSFakeStatusController.statusBarHeader.getMStatusIconsView();
        if (mStatusIconsView3 != null) {
            mStatusIconsView3.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationX);
        }
        ViewGroup mStatusIconsView4 = oplusQSFakeStatusController.statusBarHeader.getMStatusIconsView();
        if (mStatusIconsView4 != null) {
            mStatusIconsView4.setTranslationY((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationY);
        }
        ViewGroup viewGroup3 = oplusQSFakeStatusController.quickStatus;
        if (viewGroup3 != null) {
            viewGroup3.setTranslationX((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationX);
        }
        ViewGroup viewGroup4 = oplusQSFakeStatusController.quickStatus;
        if (viewGroup4 != null) {
            viewGroup4.setTranslationY((oplusQSFakeStatusController.rawFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationY);
        }
    }

    public final void onFractionEnd(float f) {
        if (f == 0.0f || f == 1.0f) {
            this.this$0.needUpdatePosition = true;
        }
    }
}
