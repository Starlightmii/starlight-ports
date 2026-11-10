package io.github.libxposed.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface IXposedScopeCallback extends IInterface {
    public static final String DESCRIPTOR = "io.github.libxposed.service.IXposedScopeCallback";

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static class Default implements IXposedScopeCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // io.github.libxposed.service.IXposedScopeCallback
        public void onScopeRequestApproved(List<String> list) {
        }

        @Override // io.github.libxposed.service.IXposedScopeCallback
        public void onScopeRequestFailed(String str) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static abstract class Stub extends Binder implements IXposedScopeCallback {
        static final int TRANSACTION_onScopeRequestApproved = 2;
        static final int TRANSACTION_onScopeRequestFailed = 3;

        /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
        public static final class Proxy implements IXposedScopeCallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public final String getInterfaceDescriptor() {
                return IXposedScopeCallback.DESCRIPTOR;
            }

            @Override // io.github.libxposed.service.IXposedScopeCallback
            public void onScopeRequestApproved(List<String> list) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IXposedScopeCallback.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    this.mRemote.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // io.github.libxposed.service.IXposedScopeCallback
            public void onScopeRequestFailed(String str) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IXposedScopeCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(3, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IXposedScopeCallback.DESCRIPTOR);
        }

        public static IXposedScopeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IXposedScopeCallback.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IXposedScopeCallback)) ? new Proxy(iBinder) : (IXposedScopeCallback) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i5, Parcel parcel, Parcel parcel2, int i6) {
            if (i5 >= 1 && i5 <= 16777215) {
                parcel.enforceInterface(IXposedScopeCallback.DESCRIPTOR);
            }
            if (i5 == 2) {
                onScopeRequestApproved(parcel.createStringArrayList());
            } else {
                if (i5 != 3) {
                    return super.onTransact(i5, parcel, parcel2, i6);
                }
                onScopeRequestFailed(parcel.readString());
            }
            return true;
        }
    }

    void onScopeRequestApproved(List<String> list);

    void onScopeRequestFailed(String str);
}
