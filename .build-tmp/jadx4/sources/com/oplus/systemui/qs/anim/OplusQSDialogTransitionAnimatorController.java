package com.oplus.systemui.qs.anim;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.qs.OplusQSTransitionAnimatorController;
import com.android.systemui.shade.ShadeControllerImpl;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSDialogTransitionAnimatorController extends OplusQSTransitionAnimatorController {
    private final CoroutineScope scope;
    private final ShadeInteractor shadeInteractor;
    private final View view;

    public OplusQSDialogTransitionAnimatorController(View view, ShadeControllerImpl shadeControllerImpl, boolean z, ShadeInteractor shadeInteractor, CoroutineScope coroutineScope) {
        super(view, z, shadeControllerImpl, shadeInteractor, coroutineScope, 32);
        this.view = view;
        this.shadeInteractor = shadeInteractor;
        this.scope = coroutineScope;
    }

    public Drawable createAnimatorBackground() {
        Drawable drawableNewDrawable;
        Drawable.ConstantState constantState = this.view.getBackground().getConstantState();
        if (constantState == null || (drawableNewDrawable = constantState.newDrawable()) == null) {
            return null;
        }
        return drawableNewDrawable.mutate();
    }

    public Drawable getOriginBackground() {
        Drawable.ConstantState constantState;
        Drawable drawableNewDrawable;
        Drawable background = this.view.getBackground();
        if (background == null || (constantState = background.getConstantState()) == null || (drawableNewDrawable = constantState.newDrawable()) == null) {
            return null;
        }
        return drawableNewDrawable.mutate();
    }
}
