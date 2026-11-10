package com.oplus.systemui.volume;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.keyguard.AlphaOptimizedImageButton;
import com.android.systemui.volume.Events;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.oplus.systemui.volume.eximpl.OplusVolumeControllerExImpl;
import com.oplus.systemui.volume.view.OplusVolumeDialogView;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCaptionsToggleImageButton extends AlphaOptimizedImageButton {
    public boolean captionsEnabled;
    private ConfirmedTapListener confirmedTapListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ConfirmedTapListener {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusCaptionsToggleImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setContentDescription(getContext().getString(2131957194));
    }

    public static final boolean access$tryToSendTapConfirmedEvent(OplusCaptionsToggleImageButton oplusCaptionsToggleImageButton) {
        OplusVolumeDialogView.blurHostHelper.1 r4 = oplusCaptionsToggleImageButton.confirmedTapListener;
        if (r4 == null) {
            return false;
        }
        OplusVolumeDialogImpl volumeInteractor = r4.this$0.getVolumeInteractor();
        OplusVolumeControllerEx oplusVolumeControllerEx = volumeInteractor.mOplusVolumeController;
        oplusVolumeControllerEx.setCaptionsEnabled(!((OplusVolumeControllerExImpl) oplusVolumeControllerEx).areCaptionsEnabled());
        volumeInteractor.mOplusVolumeDialogView.updateCaptionsIcon(0);
        Events.writeEvent(21, new Object[0]);
        return true;
    }

    public final boolean getCaptionsEnabled() {
        return this.captionsEnabled;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int[] onCreateDrawableState(int i) {
        return super/*android.widget.ImageButton*/.onCreateDrawableState(i + 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Runnable setCaptionsEnabled(boolean z) {
        this.captionsEnabled = z;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK;
        String string = z ? getContext().getString(2131957191) : getContext().getString(2131957192);
        setCaptionsEnabled.1 r3 = new setCaptionsEnabled.1();
        r3.this$0 = this;
        VarHandle.storeStoreFence();
        ViewCompat.replaceAccessibilityAction(this, accessibilityActionCompat, string, r3);
        announceForAccessibility(this.captionsEnabled ? getContext().getString(2131957192) : getContext().getString(2131957191));
        Runnable imageResourceAsync = setImageResourceAsync(this.captionsEnabled ? 2131237430 : 2131237431);
        setCaptionsEnabled.2 r0 = new setCaptionsEnabled.2();
        r0.$loadIcon = imageResourceAsync;
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        return r0;
    }

    public final void setOnConfirmedTapListener(ConfirmedTapListener confirmedTapListener) {
        this.confirmedTapListener = confirmedTapListener;
    }
}
