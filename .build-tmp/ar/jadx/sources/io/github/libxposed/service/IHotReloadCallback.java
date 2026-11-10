package io.github.libxposed.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface IHotReloadCallback extends IInterface {
    public static final String DESCRIPTOR = "io.github.libxposed.service.IHotReloadCallback";

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static class Default implements IHotReloadCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // io.github.libxposed.service.IHotReloadCallback
        public void onHotReloadResult(int i5, String str) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static abstract class Stub extends Binder implements IHotReloadCallback {
        static final int TRANSACTION_onHotReloadResult = 2;

        /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
        public static final class Proxy implements IHotReloadCallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public final String getInterfaceDescriptor() {
                return IHotReloadCallback.DESCRIPTOR;
            }

            @Override // io.github.libxposed.service.IHotReloadCallback
            public void onHotReloadResult(int i5, String str) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IHotReloadCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IHotReloadCallback.DESCRIPTOR);
        }

        public static IHotReloadCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IHotReloadCallback.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IHotReloadCallback)) ? new Proxy(iBinder) : (IHotReloadCallback) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i5, Parcel parcel, Parcel parcel2, int i6) {
            if (i5 >= 1 && i5 <= 16777215) {
                parcel.enforceInterface(IHotReloadCallback.DESCRIPTOR);
            }
            if (i5 != 2) {
                return super.onTransact(i5, parcel, parcel2, i6);
            }
            onHotReloadResult(parcel.readInt(), parcel.readString());
            return true;
        }
    }

    void onHotReloadResult(int i5, String str);
}
