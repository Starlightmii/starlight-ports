package com.oplus.sysuiinlineview;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.view.KeyEvent;
import android.view.SurfaceControlViewHost;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ISysUiInlineCallback extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.sysuiinlineview.ISysUiInlineCallback";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Stub extends Binder implements ISysUiInlineCallback {
        static final int TRANSACTION_onInlineViewCreate = 1;
        static final int TRANSACTION_onInlineViewKeyEvent = 2;
        static final int TRANSACTION_onInlineViewRequest = 3;

        public Stub() {
            attachInterface(this, ISysUiInlineCallback.DESCRIPTOR);
        }

        public static ISysUiInlineCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(ISysUiInlineCallback.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ISysUiInlineCallback)) ? new Proxy(iBinder) : (ISysUiInlineCallback) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISysUiInlineCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISysUiInlineCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onInlineViewCreate((SurfaceControlViewHost.SurfacePackage) (parcel.readInt() != 0 ? SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(parcel) : null));
                return true;
            }
            if (i == 2) {
                onInlineViewKeyEvent(parcel.readInt(), (KeyEvent) (parcel.readInt() != 0 ? KeyEvent.CREATOR.createFromParcel(parcel) : null));
                return true;
            }
            if (i != 3) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            onInlineViewRequest((Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
            return true;
        }
    }

    void onInlineViewCreate(SurfaceControlViewHost.SurfacePackage surfacePackage);

    void onInlineViewKeyEvent(int i, KeyEvent keyEvent);

    void onInlineViewRequest(Bundle bundle);
}
