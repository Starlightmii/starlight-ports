package com.oplus.systemui.volume.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.oplus.systemui.volume.repository.SingleAppVolumeButtonInfoRepository;
import java.lang.invoke.VarHandle;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleAppVolumeButtonContainer extends FrameLayout {
    private final ImageButton buttonView;
    private SingleAppVolumeButtonInfoRepository.Edge edge;
    private Rect exclusionRect;
    public final int gestureExclusionPadding;
    private final ViewTreeObserver.OnComputeInternalInsetsListener insetsListener;
    public boolean isButtonHandlingTouch;

    public SingleAppVolumeButtonContainer(Context context, ImageButton imageButton, int i) {
        super(context);
        this.buttonView = imageButton;
        this.gestureExclusionPadding = i;
        this.edge = SingleAppVolumeButtonInfoRepository.Edge.LEFT;
        addView(imageButton);
        setClipChildren(false);
        setClipToPadding(false);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        SingleAppVolumeButtonContainer$insetsListener$1 singleAppVolumeButtonContainer$insetsListener$1 = new SingleAppVolumeButtonContainer$insetsListener$1();
        singleAppVolumeButtonContainer$insetsListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.insetsListener = singleAppVolumeButtonContainer$insetsListener$1;
    }

    public static final void access$updateTouchDelegateAndGestureExclusion(SingleAppVolumeButtonContainer singleAppVolumeButtonContainer, float f, float f2) {
        SingleAppVolumeButtonInfoRepository.Edge edge = singleAppVolumeButtonContainer.edge;
        int i = edge == SingleAppVolumeButtonInfoRepository.Edge.LEFT ? singleAppVolumeButtonContainer.gestureExclusionPadding : 0;
        int i2 = edge == SingleAppVolumeButtonInfoRepository.Edge.RIGHT ? singleAppVolumeButtonContainer.gestureExclusionPadding : 0;
        Rect rect = new Rect((int) (f - i), (int) f2, (int) (f + singleAppVolumeButtonContainer.buttonView.getWidth() + i2), (int) (f2 + singleAppVolumeButtonContainer.buttonView.getHeight()));
        singleAppVolumeButtonContainer.exclusionRect = rect;
        singleAppVolumeButtonContainer.buttonView.setSystemGestureExclusionRects(CollectionsKt__CollectionsJVMKt.listOf(new Rect(-i, 0, singleAppVolumeButtonContainer.buttonView.getWidth() + i2, singleAppVolumeButtonContainer.buttonView.getHeight())));
        singleAppVolumeButtonContainer.setTouchDelegate(new TouchDelegate(rect, singleAppVolumeButtonContainer.buttonView));
    }

    public final boolean dispatchToButton(MotionEvent motionEvent) {
        motionEvent.offsetLocation(-this.buttonView.getX(), -this.buttonView.getY());
        boolean zDispatchTouchEvent = this.buttonView.dispatchTouchEvent(motionEvent);
        motionEvent.offsetLocation(this.buttonView.getX(), this.buttonView.getY());
        return zDispatchTouchEvent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            Rect rect = this.exclusionRect;
            if (!(rect != null ? rect.contains((int) motionEvent.getX(), (int) motionEvent.getY()) : false)) {
                this.isButtonHandlingTouch = false;
                return true;
            }
            this.isButtonHandlingTouch = true;
        } else if ((actionMasked == 1 || actionMasked == 3) && this.isButtonHandlingTouch) {
            dispatchToButton(motionEvent);
            this.isButtonHandlingTouch = false;
            return true;
        }
        if (this.isButtonHandlingTouch) {
            return dispatchToButton(motionEvent);
        }
        return true;
    }

    public final ViewTreeObserver.OnComputeInternalInsetsListener getInsetsListener() {
        return this.insetsListener;
    }

    public final void notifyPositionChanged(SingleAppVolumeButtonInfoRepository.Edge edge, float f, float f2) {
        this.edge = edge;
        notifyPositionChanged.1 r1 = new notifyPositionChanged.1();
        r1.this$0 = this;
        r1.$x = f;
        r1.$y = f2;
        VarHandle.storeStoreFence();
        post(r1);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
