package com.oplus.systemui.plugins.qs.customize.component.proxy;

import android.animation.Animator;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import com.android.systemui.plugins.ToastPlugin;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffect;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileOperationHintAction {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileOperationHintAction";
    private final Context context;
    private final TileEditViewModel editViewModel;
    private final Runnable hideToastRunnable;
    private final OplusQSRootView qsRootView;
    private final OplusSeparateQSPlugin.IStub separateQsPluginStub;
    private ToastPlugin.Toast systemUIToast;
    private CharSequence text;
    private View toastView;
    private WindowManager windowManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public TileOperationHintAction(OplusQSRootView oplusQSRootView, TileEditViewModel tileEditViewModel, OplusSeparateQSPlugin.IStub iStub) {
        this.qsRootView = oplusQSRootView;
        this.editViewModel = tileEditViewModel;
        this.separateQsPluginStub = iStub;
        this.context = oplusQSRootView.getContext();
        TileOperationHintAction$hideToastRunnable$1 tileOperationHintAction$hideToastRunnable$1 = new TileOperationHintAction$hideToastRunnable$1();
        tileOperationHintAction$hideToastRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.hideToastRunnable = tileOperationHintAction$hideToastRunnable$1;
        RepeatWhenAttachedKt.repeatWhenAttached(oplusQSRootView, EmptyCoroutineContext.INSTANCE, new TileOperationHintAction$collectFlow$1(this, null));
    }

    public static final void access$processEffect(TileOperationHintAction tileOperationHintAction, TileEditEffect tileEditEffect) {
        if (tileEditEffect instanceof TileEditEffect.MaxTileCountEffect) {
            tileOperationHintAction.showToast(tileOperationHintAction.context.getResources().getString(2131955196));
            return;
        }
        if (tileEditEffect instanceof TileEditEffect.TileAlreadyAddedEffect) {
            tileOperationHintAction.showToast(tileOperationHintAction.context.getResources().getString(2131955209, ((TileEditEffect.TileAlreadyAddedEffect) tileEditEffect).getTileLabel()));
            return;
        }
        if (tileEditEffect instanceof TileEditEffect.AToBMaxTileCountEffect) {
            tileOperationHintAction.showToast(tileOperationHintAction.context.getResources().getString(2131955240));
        } else if (tileEditEffect instanceof TileEditEffect.TileAddFailedDueToNoEnoughSpace) {
            tileOperationHintAction.showToast(tileOperationHintAction.context.getResources().getString(2131955184, ((TileEditEffect.TileAddFailedDueToNoEnoughSpace) tileEditEffect).getTileLabel()));
        } else {
            tileOperationHintAction.getClass();
        }
    }

    public final void hideHintImmediate() {
        Object obj;
        Unit unit;
        View view = this.toastView;
        if (view != null) {
            try {
                WindowManager windowManager = this.windowManager;
                if (windowManager != null) {
                    windowManager.removeViewImmediate(view);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                obj = Result.constructor-impl(unit);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "removeView error: " + th2);
            }
        }
        this.toastView = null;
        this.systemUIToast = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "hideHintImmediate: " + ((Object) this.text), null, 8);
    }

    public final void onExitEditMode() {
        View view = this.toastView;
        if (view != null) {
            view.removeCallbacks(this.hideToastRunnable);
        }
        hideHintImmediate();
    }

    public final void showToast(CharSequence charSequence) {
        Animator outAnimation;
        if (this.toastView != null) {
            if (Intrinsics.areEqual(this.text, charSequence)) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "same text is shown now", null, 8);
                return;
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "remove current toast: " + ((Object) this.text), null, 8);
            View view = this.toastView;
            if (view != null) {
                view.removeCallbacks(this.hideToastRunnable);
            }
            ToastPlugin.Toast toast = this.systemUIToast;
            if (toast != null && (outAnimation = toast.getOutAnimation()) != null) {
                outAnimation.cancel();
            }
            hideHintImmediate();
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "showToast: " + ((Object) charSequence), null, 8);
        ToastPlugin.Toast toastBindSystemUIToast = this.separateQsPluginStub.bindSystemUIToast(this.context, charSequence);
        this.systemUIToast = toastBindSystemUIToast;
        if (toastBindSystemUIToast != null) {
            this.toastView = toastBindSystemUIToast.getView();
            this.text = charSequence;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.setTitle("Tile operation warning toast");
            layoutParams.type = 2017;
            layoutParams.flags = 152;
            layoutParams.y = toastBindSystemUIToast.getYOffset().intValue();
            int absoluteGravity = Gravity.getAbsoluteGravity(toastBindSystemUIToast.getGravity().intValue(), this.context.getResources().getConfiguration().getLayoutDirection());
            layoutParams.gravity = absoluteGravity;
            if ((absoluteGravity & 7) == 7) {
                layoutParams.horizontalWeight = 1.0f;
            }
            if ((absoluteGravity & 112) == 112) {
                layoutParams.verticalWeight = 1.0f;
            }
            if (this.windowManager == null) {
                Object systemService = this.context.getSystemService("window");
                this.windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
            }
            WindowManager windowManager = this.windowManager;
            if (windowManager != null) {
                windowManager.addView(this.toastView, layoutParams);
            }
            Animator inAnimation = toastBindSystemUIToast.getInAnimation();
            if (inAnimation != null) {
                inAnimation.start();
            }
            View view2 = this.toastView;
            if (view2 != null) {
                view2.postDelayed(this.hideToastRunnable, 3000L);
            }
        }
    }
}
