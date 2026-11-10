package com.oplus.systemui.notification.interruption.fullscreenbanner.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatEditText;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class RemoteEditText extends AppCompatEditText {
    public FullScreenBannerHelper mFullScreenBannerHelper;
    public final InputMethodManager mInputMethodManager;

    /* JADX WARN: Multi-variable type inference failed */
    public RemoteEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInputMethodManager = (InputMethodManager) context.getSystemService(InputMethodManager.class);
        requestFocus();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (inputConnectionOnCreateInputConnection != null && this.mInputMethodManager != null) {
            1 r0 = new 1(0);
            r0.this$0 = this;
            VarHandle.storeStoreFence();
            post(r0);
        }
        return inputConnectionOnCreateInputConnection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super/*android.widget.EditText*/.onKeyDown(i, keyEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super/*android.widget.EditText*/.onKeyUp(i, keyEvent);
        }
        if (!isFocusable() || !isEnabled()) {
            return true;
        }
        setInnerFocusable(false);
        FullScreenBannerHelper fullScreenBannerHelper = this.mFullScreenBannerHelper;
        if (fullScreenBannerHelper == null) {
            return true;
        }
        fullScreenBannerHelper.closeRemoteInputs();
        fullScreenBannerHelper.mHandler.sendEmptyMessageDelayed(0, 5000L);
        return true;
    }

    public void setFullScreenBannerHelper(FullScreenBannerHelper fullScreenBannerHelper) {
        this.mFullScreenBannerHelper = fullScreenBannerHelper;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setInnerFocusable(boolean z) {
        setFocusableInTouchMode(z);
        setFocusable(z);
        setCursorVisible(z);
        if (z) {
            requestFocus();
        }
    }
}
