package com.oplus.systemui.plugins.qs.guide;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.material.ripple.Ripple_androidKt$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface$;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.media.DynamicRouteAttributes$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.qs.GuideEvent;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.util.QSThreadUtil;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideSpiritualViewController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsGuideSpiritualViewController";
    private MutableStateFlow _isWindowShowing;
    private final OplusQsGuideSpiritualView.AnimationCallBack animationCallBack;
    private CompleteCallback completeCallback;
    private final Context context;
    private final OplusQsGuideStateProvider guideStateProvider;
    private final Handler handler;
    private InterruptReason interruptReason;
    public boolean isFullScreen;
    private String mScenarioType;
    private AnimationState notificationState;
    private AnimationState qsScenarioAssistantState;
    private AnimationState qsState;
    private OplusQsGuideSpiritualView view;
    private final WindowManager windowManager;
    private StateFlow windowShowingFlow;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnimationState {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AnimationState[] $VALUES;
        public static final AnimationState COMPLETE;
        public static final AnimationState END;
        public static final AnimationState PLAYING;
        public static final AnimationState WAIT;

        static {
            AnimationState animationState = new AnimationState("WAIT", 0);
            WAIT = animationState;
            AnimationState animationState2 = new AnimationState("PLAYING", 1);
            PLAYING = animationState2;
            AnimationState animationState3 = new AnimationState("END", 2);
            END = animationState3;
            AnimationState animationState4 = new AnimationState("COMPLETE", 3);
            COMPLETE = animationState4;
            AnimationState[] animationStateArr = {animationState, animationState2, animationState3, animationState4};
            $VALUES = animationStateArr;
            $ENTRIES = EnumEntriesKt.enumEntries(animationStateArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static AnimationState valueOf(String str) {
            return (AnimationState) Enum.valueOf(AnimationState.class, str);
        }

        public static AnimationState[] values() {
            return (AnimationState[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CompleteCallback {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class GuideFlag {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ GuideFlag[] $VALUES;
        public static final GuideFlag FLAG_NONE;
        public static final GuideFlag FLAG_SCENARIO_ASSISTANT;
        public static final GuideFlag FLAG_SCENARIO_ASSISTANT_CANCEL;
        public static final GuideFlag FLAG_SCENARIO_ASSISTANT_KEEP;
        public static final GuideFlag FLAG_SCENARIO_ASSISTANT_ONLY;

        static {
            GuideFlag guideFlag = new GuideFlag("FLAG_NONE", 0);
            FLAG_NONE = guideFlag;
            GuideFlag guideFlag2 = new GuideFlag("FLAG_SCENARIO_ASSISTANT_CANCEL", 1);
            FLAG_SCENARIO_ASSISTANT_CANCEL = guideFlag2;
            GuideFlag guideFlag3 = new GuideFlag("FLAG_SCENARIO_ASSISTANT_KEEP", 2);
            FLAG_SCENARIO_ASSISTANT_KEEP = guideFlag3;
            GuideFlag guideFlag4 = new GuideFlag("FLAG_SCENARIO_ASSISTANT", 3);
            FLAG_SCENARIO_ASSISTANT = guideFlag4;
            GuideFlag guideFlag5 = new GuideFlag("FLAG_SCENARIO_ASSISTANT_ONLY", 4);
            FLAG_SCENARIO_ASSISTANT_ONLY = guideFlag5;
            GuideFlag[] guideFlagArr = {guideFlag, guideFlag2, guideFlag3, guideFlag4, guideFlag5};
            $VALUES = guideFlagArr;
            $ENTRIES = EnumEntriesKt.enumEntries(guideFlagArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static GuideFlag valueOf(String str) {
            return (GuideFlag) Enum.valueOf(GuideFlag.class, str);
        }

        public static GuideFlag[] values() {
            return (GuideFlag[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class InterruptReason {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ InterruptReason[] $VALUES;
        public static final InterruptReason FULL_SCREEN;
        public static final InterruptReason HEADS_UP_NOTIFICATION;
        public static final InterruptReason LAUNCH_APP_NOT_AT_TOP;
        public static final InterruptReason NONE;
        public static final InterruptReason NOTIFICATION_PANEL_ENTER;
        public static final InterruptReason SELECT_DIALOG_SHOW;

        static {
            InterruptReason interruptReason = new InterruptReason("NONE", 0);
            NONE = interruptReason;
            InterruptReason interruptReason2 = new InterruptReason("HEADS_UP_NOTIFICATION", 1);
            HEADS_UP_NOTIFICATION = interruptReason2;
            InterruptReason interruptReason3 = new InterruptReason("NOTIFICATION_PANEL_ENTER", 2);
            NOTIFICATION_PANEL_ENTER = interruptReason3;
            InterruptReason interruptReason4 = new InterruptReason("LAUNCH_APP_NOT_AT_TOP", 3);
            LAUNCH_APP_NOT_AT_TOP = interruptReason4;
            InterruptReason interruptReason5 = new InterruptReason("FULL_SCREEN", 4);
            FULL_SCREEN = interruptReason5;
            InterruptReason interruptReason6 = new InterruptReason("SELECT_DIALOG_SHOW", 5);
            SELECT_DIALOG_SHOW = interruptReason6;
            InterruptReason[] interruptReasonArr = {interruptReason, interruptReason2, interruptReason3, interruptReason4, interruptReason5, interruptReason6};
            $VALUES = interruptReasonArr;
            $ENTRIES = EnumEntriesKt.enumEntries(interruptReasonArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static InterruptReason valueOf(String str) {
            return (InterruptReason) Enum.valueOf(InterruptReason.class, str);
        }

        public static InterruptReason[] values() {
            return (InterruptReason[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GuideEvent.values().length];
            try {
                iArr[GuideEvent.KEYGUARD_DISMISS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[GuideEvent.NOTIFICATION_PANEL_ENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[GuideEvent.NOTIFICATION_PANEL_EXIT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[GuideEvent.QS_PANEL_ENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[GuideEvent.QS_PANEL_EXIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[GuideEvent.SCREEN_OFF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[GuideEvent.SUPER_SAVE_MODE_CHANGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[GuideEvent.HEADS_UP_NOTIFICATION_SHOW.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[GuideEvent.HEADS_UP_NOTIFICATION_DISMISS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[GuideEvent.SCREEN_DIRECTION_CHANGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[GuideEvent.SELECT_DIALOG_DISMISS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[GuideEvent.PROVISION_COMPLETE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[GuideEvent.LAUNCH_APP_ENTER.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[GuideEvent.LAUNCH_APP_EXIT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[GuideEvent.SCENARIO_ASSISTANT.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[GuideEvent.FULLSCREEN_ENTER.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[GuideEvent.FULLSCREEN_EXIT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[OplusQsGuideSpiritualView.AnimationType.values().length];
            try {
                iArr2[0] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                OplusQsGuideSpiritualView.AnimationType animationType = OplusQsGuideSpiritualView.AnimationType.NOTIFICATION;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                OplusQsGuideSpiritualView.AnimationType animationType2 = OplusQsGuideSpiritualView.AnimationType.NOTIFICATION;
                iArr2[2] = 3;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualViewController$playAnimation$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Enum $animationType;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQsGuideSpiritualViewController this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            OplusQsGuideSpiritualView oplusQsGuideSpiritualView;
            switch (this.$r8$classId) {
                case 0:
                    OplusQsGuideSpiritualViewController.access$addGuideView(this.this$0, (OplusQsGuideSpiritualView.AnimationType) this.$animationType);
                    OplusQsGuideSpiritualView oplusQsGuideSpiritualView2 = this.this$0.view;
                    if (oplusQsGuideSpiritualView2 != null) {
                        oplusQsGuideSpiritualView2.playAnimation((OplusQsGuideSpiritualView.AnimationType) this.$animationType);
                    }
                    break;
                case 1:
                    LogUtil.i$default(LogUtil.TAG_QS, OplusQsGuideSpiritualViewController.TAG, FileProvider$.ExternalSyntheticOutline0.m(" onAnimationCancel :", ((OplusQsGuideSpiritualView.AnimationType) this.$animationType).name()), null, 8);
                    if (this.this$0.getAnimationState((OplusQsGuideSpiritualView.AnimationType) this.$animationType) == AnimationState.PLAYING) {
                        this.this$0.setAnimationState((OplusQsGuideSpiritualView.AnimationType) this.$animationType, AnimationState.WAIT);
                    }
                    break;
                case 2:
                    LogUtil.i$default(LogUtil.TAG_QS, OplusQsGuideSpiritualViewController.TAG, " onAnimationEnd :" + ((OplusQsGuideSpiritualView.AnimationType) this.$animationType).name() + " qsState=" + this.this$0.qsState + " qsScenarioAssistantState=" + this.this$0.qsScenarioAssistantState, null, 8);
                    this.this$0.setAnimationState((OplusQsGuideSpiritualView.AnimationType) this.$animationType, AnimationState.END);
                    if (((OplusQsGuideSpiritualView.AnimationType) this.$animationType) != OplusQsGuideSpiritualView.AnimationType.NOTIFICATION || this.this$0.qsState == AnimationState.COMPLETE) {
                        this.this$0.cancelAnimation(GuideFlag.FLAG_SCENARIO_ASSISTANT);
                    } else {
                        OplusQsGuideSpiritualViewController.startPlayAnimation$default(this.this$0);
                    }
                    OplusQsGuideSpiritualView.AnimationType animationType = (OplusQsGuideSpiritualView.AnimationType) this.$animationType;
                    OplusQsGuideSpiritualView.AnimationType animationType2 = OplusQsGuideSpiritualView.AnimationType.SCENARIO_ASSISTANT;
                    OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController = this.this$0;
                    if (animationType != animationType2) {
                        OplusQsGuideSpiritualViewController.access$checkAnimationEndTimes(oplusQsGuideSpiritualViewController, animationType);
                    } else {
                        oplusQsGuideSpiritualViewController.checkScenarioAssistantAnimationEndTimes();
                    }
                    break;
                case 3:
                    LogUtil.i$default(LogUtil.TAG_QS, OplusQsGuideSpiritualViewController.TAG, FileProvider$.ExternalSyntheticOutline0.m(" onAnimationStart :", ((OplusQsGuideSpiritualView.AnimationType) this.$animationType).name()), null, 8);
                    this.this$0.setAnimationState((OplusQsGuideSpiritualView.AnimationType) this.$animationType, AnimationState.PLAYING);
                    break;
                default:
                    OplusQsGuideSpiritualView oplusQsGuideSpiritualView3 = this.this$0.view;
                    if (oplusQsGuideSpiritualView3 != null) {
                        oplusQsGuideSpiritualView3.cancelAnimation((GuideFlag) this.$animationType);
                    }
                    if (((GuideFlag) this.$animationType) != GuideFlag.FLAG_SCENARIO_ASSISTANT_KEEP || (oplusQsGuideSpiritualView = this.this$0.view) == null || !oplusQsGuideSpiritualView.isAnimationPlaying(OplusQsGuideSpiritualView.AnimationType.SCENARIO_ASSISTANT)) {
                        if (((GuideFlag) this.$animationType) == GuideFlag.FLAG_SCENARIO_ASSISTANT_CANCEL) {
                            OplusQsGuideSpiritualView oplusQsGuideSpiritualView4 = this.this$0.view;
                            if (oplusQsGuideSpiritualView4 == null || !oplusQsGuideSpiritualView4.isAnimationPlaying(OplusQsGuideSpiritualView.AnimationType.NOTIFICATION)) {
                                OplusQsGuideSpiritualView oplusQsGuideSpiritualView5 = this.this$0.view;
                                if (oplusQsGuideSpiritualView5 != null && oplusQsGuideSpiritualView5.isAnimationPlaying(OplusQsGuideSpiritualView.AnimationType.QS)) {
                                }
                            }
                        }
                        OplusQsGuideSpiritualViewController.access$removeGuideView(this.this$0);
                    }
                    break;
            }
        }
    }

    public OplusQsGuideSpiritualViewController(Context context, OplusQsGuideStateProvider oplusQsGuideStateProvider, Handler handler) {
        this.context = context;
        this.guideStateProvider = oplusQsGuideStateProvider;
        this.handler = handler;
        this.windowManager = (WindowManager) context.getSystemService("window");
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isWindowShowing = MutableStateFlow;
        this.windowShowingFlow = FlowKt.asStateFlow(MutableStateFlow);
        AnimationState animationState = AnimationState.WAIT;
        this.notificationState = animationState;
        this.qsState = animationState;
        this.qsScenarioAssistantState = animationState;
        this.interruptReason = InterruptReason.NONE;
        this.mScenarioType = "";
        OplusQsGuideSpiritualViewController$animationCallBack$1 oplusQsGuideSpiritualViewController$animationCallBack$1 = new OplusQsGuideSpiritualViewController$animationCallBack$1();
        oplusQsGuideSpiritualViewController$animationCallBack$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationCallBack = oplusQsGuideSpiritualViewController$animationCallBack$1;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController--> init");
        }
    }

    public static final void access$addGuideView(OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController, OplusQsGuideSpiritualView.AnimationType animationType) {
        boolean z = false;
        if (oplusQsGuideSpiritualViewController.view == null) {
            OplusQsGuideSpiritualView.Companion.setDebug(oplusQsGuideSpiritualViewController.guideStateProvider.getTestGuideMode() >= 1);
            OplusQsGuideSpiritualView oplusQsGuideSpiritualView = new OplusQsGuideSpiritualView(oplusQsGuideSpiritualViewController.context, null, 0, 14, 0);
            oplusQsGuideSpiritualViewController.view = oplusQsGuideSpiritualView;
            oplusQsGuideSpiritualView.setCallBack(oplusQsGuideSpiritualViewController.animationCallBack);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Ripple_androidKt$.ExternalSyntheticOutline0.m(oplusQsGuideSpiritualViewController._isWindowShowing.getValue(), "addGuideView: ", " "), null, 8);
        if (((Boolean) oplusQsGuideSpiritualViewController._isWindowShowing.getValue()).booleanValue()) {
            return;
        }
        MutableStateFlow mutableStateFlow = oplusQsGuideSpiritualViewController._isWindowShowing;
        try {
            ConstraintLayout constraintLayout = oplusQsGuideSpiritualViewController.view;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "addGuideView, isAttachedToWindow" + (constraintLayout != null ? Boolean.valueOf(constraintLayout.isAttachedToWindow()) : null), null, 8);
            ConstraintLayout constraintLayout2 = oplusQsGuideSpiritualViewController.view;
            if (constraintLayout2 != null && !constraintLayout2.isAttachedToWindow()) {
                oplusQsGuideSpiritualViewController.windowManager.addView(constraintLayout2, oplusQsGuideSpiritualViewController.getLayoutParams());
            }
            z = true;
        } catch (WindowManager.BadTokenException unused) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "addGuideView BadTokenException", null, 8);
        } catch (WindowManager.InvalidDisplayException unused2) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "addGuideView InvalidDisplayException", null, 8);
        }
        mutableStateFlow.setValue(Boolean.valueOf(z));
        if (animationType != OplusQsGuideSpiritualView.AnimationType.SCENARIO_ASSISTANT) {
            updateStatusBarVisible$default(oplusQsGuideSpiritualViewController, !((Boolean) oplusQsGuideSpiritualViewController.windowShowingFlow.getValue()).booleanValue());
        }
    }

    public static final void access$checkAnimationEndTimes(OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController, OplusQsGuideSpiritualView.AnimationType animationType) {
        OplusQsGuideSpiritualView.AnimationType animationType2 = OplusQsGuideSpiritualView.AnimationType.QS;
        boolean z = false;
        boolean z2 = animationType == animationType2;
        if (oplusQsGuideSpiritualViewController.qsState == AnimationState.COMPLETE && animationType == OplusQsGuideSpiritualView.AnimationType.NOTIFICATION) {
            z = true;
        }
        if (z2 || z) {
            if (oplusQsGuideSpiritualViewController.guideStateProvider.getTestGuideMode() >= 2) {
                resetAnimationState$default(oplusQsGuideSpiritualViewController);
                startPlayAnimation$default(oplusQsGuideSpiritualViewController);
            } else {
                oplusQsGuideSpiritualViewController.setAnimationEndTimes(oplusQsGuideSpiritualViewController.getAnimationEndTimes() + 1);
                LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(oplusQsGuideSpiritualViewController.getAnimationEndTimes(), " checkAnimationEndTimes add 1 : "), null, 8);
            }
        }
        if (oplusQsGuideSpiritualViewController.getAnimationEndTimes() >= 3) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " checkAnimationEndTimes Complete", null, 8);
            oplusQsGuideSpiritualViewController.setGuideAnimationComplete(animationType2);
            oplusQsGuideSpiritualViewController.setGuideAnimationComplete(OplusQsGuideSpiritualView.AnimationType.NOTIFICATION);
        }
    }

    public static final void access$removeGuideView(OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController) {
        OplusQsGuideSpiritualView oplusQsGuideSpiritualView;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Ripple_androidKt$.ExternalSyntheticOutline0.m(oplusQsGuideSpiritualViewController._isWindowShowing.getValue(), "removeGuideView: ", " "), null, 8);
        if (((Boolean) oplusQsGuideSpiritualViewController._isWindowShowing.getValue()).booleanValue()) {
            try {
                try {
                    ConstraintLayout constraintLayout = oplusQsGuideSpiritualViewController.view;
                    if (constraintLayout != null) {
                        LogUtil.i$default(LogUtil.TAG_QS, TAG, "removeGuideView, isAttachedToWindow" + constraintLayout.isAttachedToWindow(), null, 8);
                        oplusQsGuideSpiritualViewController.windowManager.removeViewImmediate(oplusQsGuideSpiritualViewController.view);
                    }
                    oplusQsGuideSpiritualViewController._isWindowShowing.setValue(Boolean.FALSE);
                    if (oplusQsGuideSpiritualViewController.guideStateProvider.getTestGuideMode() < 3) {
                        OplusQsGuideSpiritualView oplusQsGuideSpiritualView2 = oplusQsGuideSpiritualViewController.view;
                        if (oplusQsGuideSpiritualView2 != null) {
                            oplusQsGuideSpiritualView2.setCallBack(null);
                        }
                        oplusQsGuideSpiritualView = oplusQsGuideSpiritualViewController.view;
                        if (oplusQsGuideSpiritualView != null) {
                            oplusQsGuideSpiritualView.release();
                        }
                        oplusQsGuideSpiritualViewController.view = null;
                    }
                } catch (WindowManager.BadTokenException unused) {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "removeGuideView BadTokenException", null, 8);
                    oplusQsGuideSpiritualViewController._isWindowShowing.setValue(Boolean.FALSE);
                    if (oplusQsGuideSpiritualViewController.guideStateProvider.getTestGuideMode() < 3) {
                        OplusQsGuideSpiritualView oplusQsGuideSpiritualView3 = oplusQsGuideSpiritualViewController.view;
                        if (oplusQsGuideSpiritualView3 != null) {
                            oplusQsGuideSpiritualView3.setCallBack(null);
                        }
                        oplusQsGuideSpiritualView = oplusQsGuideSpiritualViewController.view;
                        if (oplusQsGuideSpiritualView != null) {
                        }
                        oplusQsGuideSpiritualViewController.view = null;
                    }
                } catch (WindowManager.InvalidDisplayException unused2) {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "removeGuideView InvalidDisplayException", null, 8);
                    oplusQsGuideSpiritualViewController._isWindowShowing.setValue(Boolean.FALSE);
                    if (oplusQsGuideSpiritualViewController.guideStateProvider.getTestGuideMode() < 3) {
                        OplusQsGuideSpiritualView oplusQsGuideSpiritualView4 = oplusQsGuideSpiritualViewController.view;
                        if (oplusQsGuideSpiritualView4 != null) {
                            oplusQsGuideSpiritualView4.setCallBack(null);
                        }
                        oplusQsGuideSpiritualView = oplusQsGuideSpiritualViewController.view;
                        if (oplusQsGuideSpiritualView != null) {
                        }
                        oplusQsGuideSpiritualViewController.view = null;
                    }
                }
                updateStatusBarVisible$default(oplusQsGuideSpiritualViewController, !((Boolean) oplusQsGuideSpiritualViewController.windowShowingFlow.getValue()).booleanValue());
            } catch (Throwable th) {
                oplusQsGuideSpiritualViewController._isWindowShowing.setValue(Boolean.FALSE);
                if (oplusQsGuideSpiritualViewController.guideStateProvider.getTestGuideMode() < 3) {
                    OplusQsGuideSpiritualView oplusQsGuideSpiritualView5 = oplusQsGuideSpiritualViewController.view;
                    if (oplusQsGuideSpiritualView5 != null) {
                        oplusQsGuideSpiritualView5.setCallBack(null);
                    }
                    OplusQsGuideSpiritualView oplusQsGuideSpiritualView6 = oplusQsGuideSpiritualViewController.view;
                    if (oplusQsGuideSpiritualView6 != null) {
                        oplusQsGuideSpiritualView6.release();
                    }
                    oplusQsGuideSpiritualViewController.view = null;
                }
                throw th;
            }
        }
    }

    private final int getAnimationEndTimes() {
        return this.guideStateProvider.getAnimationEndTimes();
    }

    private final WindowManager.LayoutParams getLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2017;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 49;
        layoutParams.flags = 134284080;
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.context.getSystemService(AccessibilityManager.class);
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            layoutParams.flags |= 8;
        }
        layoutParams.privateFlags = 16;
        layoutParams.format = -3;
        layoutParams.setTitle("NotificationShadeGuideTips");
        return layoutParams;
    }

    public static void interruptAnimation$default(OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController, InterruptReason interruptReason, int i) {
        boolean z = (i & 2) != 0;
        GuideFlag guideFlag = GuideFlag.FLAG_SCENARIO_ASSISTANT;
        if (z) {
            oplusQsGuideSpiritualViewController.cancelAnimation(guideFlag);
        }
        oplusQsGuideSpiritualViewController.resetAnimationState(guideFlag);
        oplusQsGuideSpiritualViewController.interruptReason = interruptReason;
    }

    public static /* synthetic */ void resetAnimationState$default(OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController) {
        oplusQsGuideSpiritualViewController.resetAnimationState(GuideFlag.FLAG_SCENARIO_ASSISTANT);
    }

    private final void setAnimationEndTimes(int i) {
        this.guideStateProvider.setAnimationEndTimes(i);
    }

    private final void setGuideAnimationComplete(OplusQsGuideSpiritualView.AnimationType animationType) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m(" setGuideAnimationComplete: ", animationType.name()), null, 8);
        if (this.guideStateProvider.getTestGuideMode() >= 1) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " setGuideAnimationComplete fail ,isNeedTestGuide：", null, 8);
            return;
        }
        AnimationState animationState = getAnimationState(animationType);
        AnimationState animationState2 = AnimationState.COMPLETE;
        if (animationState == animationState2) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, Bundler$.ExternalSyntheticOutline0.m(" setGuideAnimationComplete: ", animationType.name(), " already complete"), null, 8);
            return;
        }
        setAnimationState(animationType, animationState2);
        int iOrdinal = animationType.ordinal();
        if (iOrdinal == 0) {
            this.guideStateProvider.setNotificationGuideCompleted(true);
        } else if (iOrdinal == 1) {
            this.guideStateProvider.setQsGuideCompleted(true);
        } else if (iOrdinal != 2) {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return;
        }
        if (this.guideStateProvider.isAllGuideComplete()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "setGuideAnimationComplete isGuideComplete", null, 8);
            CompleteCallback completeCallback = this.completeCallback;
            if (completeCallback != null) {
                OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager = ((OplusQsGuideSpiritualManager$guideAnimationCallback$1) completeCallback).this$0;
                Handler handler = oplusQsGuideSpiritualManager.handler;
                OplusQsGuideSpiritualManager$guideAnimationCallback$1$onComplete$1 oplusQsGuideSpiritualManager$guideAnimationCallback$1$onComplete$1 = new OplusQsGuideSpiritualManager$guideAnimationCallback$1$onComplete$1();
                oplusQsGuideSpiritualManager$guideAnimationCallback$1$onComplete$1.this$0 = oplusQsGuideSpiritualManager;
                VarHandle.storeStoreFence();
                handler.post(oplusQsGuideSpiritualManager$guideAnimationCallback$1$onComplete$1);
            }
        }
    }

    public static /* synthetic */ void startPlayAnimation$default(OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController) {
        oplusQsGuideSpiritualViewController.startPlayAnimation(GuideFlag.FLAG_SCENARIO_ASSISTANT);
    }

    public static void updateStatusBarVisible$default(OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController, boolean z) {
        int displayId = oplusQsGuideSpiritualViewController.context.getDisplayId();
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m(" updateStatusBarVisible:", displayId, ", ", ", ", z);
        sbM.append(true);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        oplusQsGuideSpiritualViewController.guideStateProvider.updateStatusBarVisible(displayId, z);
    }

    public final void cancelAnimation(GuideFlag guideFlag) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, " cancelAnimation guideFlag" + guideFlag, null, 8);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(4);
        anonymousClass1.this$0 = this;
        anonymousClass1.$animationType = guideFlag;
        VarHandle.storeStoreFence();
        QSThreadUtil.runOnUiThread(anonymousClass1);
    }

    public final void checkScenarioAssistantAnimationEndTimes() {
        ScenarioAssistantGuideProvider scenarioAssistantGuideProvider = ScenarioAssistantGuideProvider.INSTANCE;
        int testGuideMode = scenarioAssistantGuideProvider.getTestGuideMode(this.context);
        if (testGuideMode >= 1) {
            resetAnimationState$default(this);
            return;
        }
        if (!TextUtils.isEmpty(this.mScenarioType)) {
            OplusQsGuideSpUtils.setGuideSpIntValue(this.context, this.mScenarioType, 1);
        }
        int isQsScenarioAssistantGuideTotalCount = scenarioAssistantGuideProvider.getIsQsScenarioAssistantGuideTotalCount(this.context);
        if (isQsScenarioAssistantGuideTotalCount < 6) {
            isQsScenarioAssistantGuideTotalCount++;
            scenarioAssistantGuideProvider.setIsQsScenarioAssistantGuideTotalCount(this.context, isQsScenarioAssistantGuideTotalCount);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        scenarioAssistantGuideProvider.setQsScenarioAssistantGuideDate(this.context, jCurrentTimeMillis);
        if (isQsScenarioAssistantGuideTotalCount >= 6) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " checkScenarioAssistantAnimationEndTimes Complete", null, 8);
            setGuideAnimationComplete(OplusQsGuideSpiritualView.AnimationType.SCENARIO_ASSISTANT);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController-->".concat(" checkScenarioAssistantAnimationEndTimes " + isQsScenarioAssistantGuideTotalCount + " qsScenarioAssistantGuideDate=" + jCurrentTimeMillis + " isQsScenarioAssistantGuideTodayCompleted=" + scenarioAssistantGuideProvider.isQsScenarioAssistantGuideTodayCompleted(this.context) + " isQsScenarioAssistantGuideCompleted=" + (isQsScenarioAssistantGuideTotalCount >= 6 && testGuideMode < 1)));
        }
    }

    public final AnimationState getAnimationState(OplusQsGuideSpiritualView.AnimationType animationType) {
        int iOrdinal = animationType.ordinal();
        if (iOrdinal == 0) {
            return this.notificationState;
        }
        if (iOrdinal == 1) {
            return this.qsState;
        }
        if (iOrdinal == 2) {
            return this.qsScenarioAssistantState;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }

    public final IBinder getWindowTokens() {
        ConstraintLayout constraintLayout = this.view;
        if (constraintLayout != null) {
            return constraintLayout.getWindowToken();
        }
        return null;
    }

    public final void handleEvent(GuideEvent guideEvent) {
        switch (WhenMappings.$EnumSwitchMapping$0[guideEvent.ordinal()]) {
            case 1:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController--> handleDismissKeyguard");
                }
                resetAnimationState$default(this);
                this.interruptReason = InterruptReason.NONE;
                startPlayAnimation$default(this);
                break;
            case 2:
                if (this.guideStateProvider.isKeyguardShow()) {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, " handleNotificationPanelEnter isKeyguardShow", null, 8);
                    interruptAnimation$default(this, InterruptReason.NONE, 6);
                } else if (!this.guideStateProvider.isSelectDialogNeedShow()) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("handleNotificationPanelEnter: noti-- ", this.notificationState.name(), ", qs-- ", this.qsState.name(), "OplusQsGuideSpiritualViewController-->"));
                    }
                    AnimationState animationState = this.qsState;
                    AnimationState animationState2 = this.notificationState;
                    AnimationState animationState3 = AnimationState.PLAYING;
                    if (animationState2 == animationState3) {
                        interruptAnimation$default(this, InterruptReason.NOTIFICATION_PANEL_ENTER, 6);
                    } else {
                        interruptAnimation$default(this, InterruptReason.NONE, 6);
                    }
                    setGuideAnimationComplete(OplusQsGuideSpiritualView.AnimationType.NOTIFICATION);
                    if (animationState == animationState3 || animationState == AnimationState.END) {
                        setGuideAnimationComplete(OplusQsGuideSpiritualView.AnimationType.QS);
                    }
                } else {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, " handleNotificationPanelEnter isSelectDialogNeedShow", null, 8);
                    interruptAnimation$default(this, InterruptReason.NONE, 6);
                }
                break;
            case 3:
                LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("handleNotificationPanelExit: ", this.interruptReason.name()), null, 8);
                InterruptReason interruptReason = InterruptReason.NOTIFICATION_PANEL_ENTER;
                GuideFlag guideFlag = GuideFlag.FLAG_SCENARIO_ASSISTANT;
                if (this.interruptReason == interruptReason) {
                    InterruptReason interruptReason2 = InterruptReason.NONE;
                    this.interruptReason = InterruptReason.NONE;
                    startPlayAnimation(guideFlag);
                }
                break;
            case 4:
                if (this.guideStateProvider.isKeyguardShow()) {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, " handleQsPanelEnter isKeyguardShow", null, 8);
                    interruptAnimation$default(this, InterruptReason.NONE, 6);
                } else if (!this.guideStateProvider.isSelectDialogNeedShow()) {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("handleQsPanelEnter: ", this.notificationState.name()), null, 8);
                    AnimationState animationState4 = this.notificationState;
                    interruptAnimation$default(this, InterruptReason.NONE, 6);
                    setGuideAnimationComplete(OplusQsGuideSpiritualView.AnimationType.QS);
                    if (animationState4 == AnimationState.END || animationState4 == AnimationState.PLAYING) {
                        setGuideAnimationComplete(OplusQsGuideSpiritualView.AnimationType.NOTIFICATION);
                    }
                } else {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, " handleQsPanelEnter isSelectDialogNeedShow", null, 8);
                    interruptAnimation$default(this, InterruptReason.NONE, 6);
                }
                break;
            case 5:
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "handleQsPanelExit:", null, 8);
                break;
            case 6:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController--> handleScreenOff");
                }
                interruptAnimation$default(this, InterruptReason.NONE, 6);
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController--> handleSuperSaveModeChange");
                }
                interruptAnimation$default(this, InterruptReason.NONE, 6);
                break;
            case 8:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("handleHeadsUpNotificationShow: ", this.qsState.name(), ", ", this.notificationState.name(), "OplusQsGuideSpiritualViewController-->"));
                }
                AnimationState animationState5 = this.qsState;
                AnimationState animationState6 = AnimationState.PLAYING;
                if (animationState5 == animationState6 || this.notificationState == animationState6) {
                    interruptAnimation$default(this, InterruptReason.HEADS_UP_NOTIFICATION, 6);
                }
                break;
            case 9:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("handleHeadsUpNotificationDismiss: ", this.interruptReason.name(), "OplusQsGuideSpiritualViewController-->", "SystemUi--QuickSettings");
                }
                InterruptReason interruptReason3 = InterruptReason.HEADS_UP_NOTIFICATION;
                GuideFlag guideFlag2 = GuideFlag.FLAG_SCENARIO_ASSISTANT;
                if (this.interruptReason == interruptReason3) {
                    InterruptReason interruptReason4 = InterruptReason.NONE;
                    this.interruptReason = InterruptReason.NONE;
                    startPlayAnimation(guideFlag2);
                }
                break;
            case 10:
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "handleScreenDirectionChange:", null, 8);
                interruptAnimation$default(this, InterruptReason.NONE, 6);
                break;
            case 11:
                LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("handleSelectDialogDismiss: ", this.interruptReason.name()), null, 8);
                AnimationState animationState7 = this.qsState;
                AnimationState animationState8 = AnimationState.WAIT;
                if (animationState7 == animationState8 && this.notificationState == animationState8) {
                    resetAllState();
                    startPlayAnimation$default(this);
                    break;
                }
                break;
            case 12:
                LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("handleSelectDialogDismiss: ", this.interruptReason.name()), null, 8);
                AnimationState animationState9 = this.qsState;
                AnimationState animationState10 = AnimationState.WAIT;
                if (animationState9 == animationState10 && this.notificationState == animationState10) {
                    resetAllState();
                    startPlayAnimation$default(this);
                    break;
                }
                break;
            case 13:
                LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("handleLaunchAppEnter: ", this.interruptReason.name()), null, 8);
                InterruptReason interruptReason5 = InterruptReason.LAUNCH_APP_NOT_AT_TOP;
                GuideFlag guideFlag3 = GuideFlag.FLAG_NONE;
                if (this.interruptReason == interruptReason5) {
                    InterruptReason interruptReason6 = InterruptReason.NONE;
                    this.interruptReason = InterruptReason.NONE;
                    startPlayAnimation(guideFlag3);
                }
                break;
            case 14:
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "handleLaunchAppExit:", null, 8);
                InterruptReason interruptReason7 = InterruptReason.NONE;
                GuideFlag guideFlag4 = GuideFlag.FLAG_SCENARIO_ASSISTANT_KEEP;
                cancelAnimation(guideFlag4);
                resetAnimationState(guideFlag4);
                this.interruptReason = interruptReason7;
                break;
            case 15:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController--> handleScenarioAssistant");
                }
                setAnimationState(OplusQsGuideSpiritualView.AnimationType.SCENARIO_ASSISTANT, AnimationState.WAIT);
                this.interruptReason = InterruptReason.NONE;
                startPlayAnimation(GuideFlag.FLAG_SCENARIO_ASSISTANT_ONLY);
                break;
            case 16:
                handleFullScreenChange(true);
                break;
            case 17:
                handleFullScreenChange(false);
                break;
            default:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController--> handleEvent do nothing");
                    break;
                }
                break;
        }
    }

    public final void handleFullScreenChange(boolean z) {
        this.isFullScreen = z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(" handleFullScreenChange ", "OplusQsGuideSpiritualViewController-->", "SystemUi--QuickSettings", z);
        }
        if (!z) {
            InterruptReason interruptReason = InterruptReason.FULL_SCREEN;
            GuideFlag guideFlag = GuideFlag.FLAG_NONE;
            if (this.interruptReason == interruptReason) {
                this.interruptReason = InterruptReason.NONE;
                startPlayAnimation(GuideFlag.FLAG_SCENARIO_ASSISTANT_ONLY);
                return;
            }
            return;
        }
        if (this.qsScenarioAssistantState == AnimationState.PLAYING) {
            InterruptReason interruptReason2 = InterruptReason.FULL_SCREEN;
            GuideFlag guideFlag2 = GuideFlag.FLAG_SCENARIO_ASSISTANT_CANCEL;
            cancelAnimation(guideFlag2);
            resetAnimationState(guideFlag2);
            this.interruptReason = interruptReason2;
        }
    }

    public final void playAnimation(OplusQsGuideSpiritualView.AnimationType animationType) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "playAnimation: " + animationType, null, 8);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        anonymousClass1.$animationType = animationType;
        VarHandle.storeStoreFence();
        QSThreadUtil.runOnUiThread(anonymousClass1);
    }

    public final void resetAllState() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, " resetAllState", null, 8);
        resetAnimationState$default(this);
        this.interruptReason = InterruptReason.NONE;
        setAnimationEndTimes(0);
    }

    public final void resetAnimationState(GuideFlag guideFlag) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController--> resetAnimationState");
        }
        OplusQsGuideSpiritualView.AnimationType animationType = OplusQsGuideSpiritualView.AnimationType.QS;
        AnimationState animationState = AnimationState.WAIT;
        setAnimationState(animationType, animationState);
        setAnimationState(OplusQsGuideSpiritualView.AnimationType.NOTIFICATION, animationState);
        if (guideFlag == GuideFlag.FLAG_SCENARIO_ASSISTANT) {
            setAnimationState(OplusQsGuideSpiritualView.AnimationType.SCENARIO_ASSISTANT, animationState);
        }
    }

    public final void setAnimationCallback(CompleteCallback completeCallback) {
        this.completeCallback = completeCallback;
    }

    public final void setAnimationState(OplusQsGuideSpiritualView.AnimationType animationType, AnimationState animationState) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, CursorUtil$.ExternalSyntheticOutline0.m(" setAnimationState: ", animationType.name(), " , ", animationState.name()), null, 8);
        int iOrdinal = animationType.ordinal();
        if (iOrdinal == 0) {
            if (this.guideStateProvider.isNotificationGuideCompleted()) {
                animationState = AnimationState.COMPLETE;
            }
            this.notificationState = animationState;
        } else if (iOrdinal == 1) {
            if (this.guideStateProvider.isQsGuideCompleted()) {
                animationState = AnimationState.COMPLETE;
            }
            this.qsState = animationState;
        } else {
            if (iOrdinal != 2) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            }
            if (ScenarioAssistantGuideProvider.INSTANCE.isQsScenarioAssistantGuideCompleted(this.context)) {
                animationState = AnimationState.COMPLETE;
            }
            this.qsScenarioAssistantState = animationState;
        }
    }

    public final void setScenarioType(String str) {
        this.mScenarioType = str;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0107  */
    public final void startPlayAnimation(GuideFlag guideFlag) {
        boolean z;
        boolean zIsKeyguardShow = this.guideStateProvider.isKeyguardShow();
        this.guideStateProvider.getClass();
        boolean zIsPhoneLandscape = OplusQsGuideStateProvider.isPhoneLandscape();
        this.guideStateProvider.getClass();
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        boolean zBooleanValue = ((Boolean) companion.getInstance().getSuperPowerSaveMode().getValue()).booleanValue();
        boolean zIsMainUser = this.guideStateProvider.isMainUser();
        int iIntValue = ((Number) companion.getInstance().getUserId().getValue()).intValue();
        boolean zIsSupportGuide = OplusQsGuideSpUtils.isSupportGuide(this.context);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualViewController-->".concat("startPlayAnimation guideFlag=" + guideFlag + " isMainUser=" + zIsMainUser + " userId=" + iIntValue + " isNeedQsGuide=" + zIsSupportGuide));
        }
        if (zIsKeyguardShow || zIsPhoneLandscape || zBooleanValue) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(TextFieldImplKt$.ExternalSyntheticOutline0.m("startPlayAnimation: no play ,for-- isKeyguardShow:", ", isPhoneLandscape:", ", isSuperPowerSavingMode:", zIsKeyguardShow, zIsPhoneLandscape), zBooleanValue, ", isMainUser:", zIsMainUser), null, 8);
            interruptAnimation$default(this, InterruptReason.NONE, 4);
            return;
        }
        if (!this.guideStateProvider.isProvisionComplete()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "startPlayAnimation no play, interruptReason: isProvisionComplete false", null, 8);
            interruptAnimation$default(this, InterruptReason.NONE, 4);
            return;
        }
        if (this.guideStateProvider.isSelectDialogNeedShow()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "startPlayAnimation no play, interruptReason: isSelectDialogNeedShow ", null, 8);
            interruptAnimation$default(this, InterruptReason.SELECT_DIALOG_SHOW, 4);
            return;
        }
        if (this.guideStateProvider.isHeadsUpNotificationShow()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "startPlayAnimation no play, interruptReason: isHeadsUpNotificationShow ", null, 8);
            interruptAnimation$default(this, InterruptReason.HEADS_UP_NOTIFICATION, 4);
            return;
        }
        GuideFlag guideFlag2 = GuideFlag.FLAG_SCENARIO_ASSISTANT_ONLY;
        boolean z2 = false;
        if (guideFlag != guideFlag2 || this.qsScenarioAssistantState != AnimationState.WAIT || this.isFullScreen || TextUtils.isEmpty(this.mScenarioType)) {
            z = false;
        } else {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isSupportScenarioAssistant()) {
                ScenarioAssistantGuideProvider scenarioAssistantGuideProvider = ScenarioAssistantGuideProvider.INSTANCE;
                if (scenarioAssistantGuideProvider.isQsScenarioAssistantGuideCompleted(this.context) || scenarioAssistantGuideProvider.isQsScenarioAssistantGuideTodayCompleted(this.context)) {
                    z = false;
                } else {
                    z = true;
                }
            } else {
                z = false;
            }
        }
        if (!this.guideStateProvider.isLaunchAppTop()) {
            if (z) {
                playAnimation(OplusQsGuideSpiritualView.AnimationType.SCENARIO_ASSISTANT);
                return;
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("startPlayAnimation no play, interruptReason: isLaunchAppTop false isFullScreen=", this.isFullScreen), null, 8);
            if (this.isFullScreen && guideFlag == guideFlag2) {
                interruptAnimation$default(this, InterruptReason.FULL_SCREEN, 4);
                return;
            } else {
                interruptAnimation$default(this, InterruptReason.LAUNCH_APP_NOT_AT_TOP, 4);
                return;
            }
        }
        if (zIsSupportGuide && zIsMainUser && guideFlag != guideFlag2) {
            z2 = true;
        }
        if (z2 && this.notificationState == AnimationState.WAIT && !this.guideStateProvider.isNotificationGuideCompleted()) {
            playAnimation(OplusQsGuideSpiritualView.AnimationType.NOTIFICATION);
            return;
        }
        if (z2 && this.qsState == AnimationState.WAIT && !this.guideStateProvider.isQsGuideCompleted()) {
            playAnimation(OplusQsGuideSpiritualView.AnimationType.QS);
            return;
        }
        if (z) {
            playAnimation(OplusQsGuideSpiritualView.AnimationType.SCENARIO_ASSISTANT);
            return;
        }
        interruptAnimation$default(this, InterruptReason.NONE, 6);
        String strName = this.notificationState.name();
        String strName2 = this.qsState.name();
        boolean z3 = this.isFullScreen;
        ScenarioAssistantGuideProvider scenarioAssistantGuideProvider2 = ScenarioAssistantGuideProvider.INSTANCE;
        boolean zIsQsScenarioAssistantGuideCompleted = scenarioAssistantGuideProvider2.isQsScenarioAssistantGuideCompleted(this.context);
        boolean zIsQsScenarioAssistantGuideTodayCompleted = scenarioAssistantGuideProvider2.isQsScenarioAssistantGuideTodayCompleted(this.context);
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("startPlayAnimation: not play for: notification--  ", strName, " ,qs-- ", strName2, " guideFlag=");
        sbM.append(guideFlag);
        sbM.append(" isFullScreen=");
        sbM.append(z3);
        sbM.append("  isQsScenarioAssistantGuideCompleted=");
        LogUtil.i$default(LogUtil.TAG_QS, TAG, DynamicRouteAttributes$.ExternalSyntheticOutline0.m("  isQsScenarioAssistantGuideTodayCompleted=", " ", sbM, zIsQsScenarioAssistantGuideCompleted, zIsQsScenarioAssistantGuideTodayCompleted), null, 8);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OplusQsGuideSpiritualViewController@" + hashCode() + "(");
        stringBuffer.append("view=" + this.view + ", ");
        stringBuffer.append("windowShowing=" + this._isWindowShowing.getValue() + ", ");
        stringBuffer.append("notificationState=" + this.notificationState + ", ");
        stringBuffer.append("qsState=" + this.qsState + ", ");
        stringBuffer.append("interruptReason=" + this.interruptReason + ", ");
        stringBuffer.append("animationEndTimes=" + getAnimationEndTimes());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
