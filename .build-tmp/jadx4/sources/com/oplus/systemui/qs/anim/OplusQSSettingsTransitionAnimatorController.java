package com.oplus.systemui.qs.anim;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.qs.OplusQSTileBgScaleDrawable;
import com.android.systemui.qs.OplusQSTransitionAnimatorController;
import com.android.systemui.shade.ShadeControllerImpl;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import dagger.Lazy;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSettingsTransitionAnimatorController extends OplusQSTransitionAnimatorController {
    public static final Companion Companion = new Companion();
    private final Lazy platformBlurManager;
    private final CoroutineScope scope;
    private final ShadeControllerImpl shadeControllerImpl;
    private final ShadeInteractor shadeInteractor;
    private final View view;

    public OplusQSSettingsTransitionAnimatorController(View view, ShadeControllerImpl shadeControllerImpl, boolean z, ShadeInteractor shadeInteractor, CoroutineScope coroutineScope, Lazy lazy) {
        super(view, z, shadeControllerImpl, shadeInteractor, coroutineScope, lazy);
        this.view = view;
        this.shadeControllerImpl = shadeControllerImpl;
        this.shadeInteractor = shadeInteractor;
        this.scope = coroutineScope;
        this.platformBlurManager = lazy;
    }

    public Drawable createAnimatorBackground() {
        Drawable originBackground = getOriginBackground();
        if (originBackground != null) {
            setOplusQSTileBgScaleDrawable(new OplusQSTileBgScaleDrawable(originBackground, 1.13f, 1.27f, 17));
        }
        return getOplusQSTileBgScaleDrawable();
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
