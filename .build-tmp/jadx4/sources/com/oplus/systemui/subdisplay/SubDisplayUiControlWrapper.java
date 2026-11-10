package com.oplus.systemui.subdisplay;

import android.os.RemoteException;
import android.util.Log;
import com.oplus.systemui.ISubDisplayCallBack;
import com.oplus.systemui.ISubDisplayUiControl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SubDisplayUiControlWrapper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SubDisplayKeyguardControl";
    private final ISubDisplayUiControl mSubDisplayUiControl;

    public SubDisplayUiControlWrapper(ISubDisplayUiControl iSubDisplayUiControl) {
        this.mSubDisplayUiControl = iSubDisplayUiControl;
    }

    public final void collapsePanels() {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.collapsePanels();
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void onAodAnimationEnd() {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.onAodAnimationEnd();
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void onDreamingStart(int i) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.onDreamingStart(i);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void onDreamingStop(int i) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.onDreamingStop(i);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void registerCallback(ISubDisplayCallBack iSubDisplayCallBack) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.registerCallback(iSubDisplayCallBack);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void requestKeyguardOnHomeCard() {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.requestKeyguardOnHomeCard();
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void setCountDownInfo(int i, int i2) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.setCountDownInfo(i, i2);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void setLockIconState(int i) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.setLockIconState(i);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void setSecureState(boolean z) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.setSecureState(z);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void setSimSecureState(boolean z, int i) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.setSimSecureState(z, i);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void setStrongAuthState(int i) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.setStrongAuthState(i);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }

    public final void unRegisterCallback(ISubDisplayCallBack iSubDisplayCallBack) {
        try {
            ISubDisplayUiControl iSubDisplayUiControl = this.mSubDisplayUiControl;
            if (iSubDisplayUiControl != null) {
                iSubDisplayUiControl.unRegisterCallback(iSubDisplayCallBack);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Remote Exception", e);
        } catch (Exception e2) {
            Log.w(TAG, "Exception", e2);
        }
    }
}
