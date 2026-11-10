package com.oplus.sysuiinlineview;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ISysUiInlineService extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.sysuiinlineview.ISysUiInlineService";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Stub extends Binder implements ISysUiInlineService {
        static final int TRANSACTION_onHostViewCommand = 7;
        static final int TRANSACTION_onHostViewConfigurationChanged = 6;
        static final int TRANSACTION_onHostViewCreate = 1;
        static final int TRANSACTION_onHostViewDestroy = 5;
        static final int TRANSACTION_onHostViewLayout = 3;
        static final int TRANSACTION_onHostViewPause = 4;
        static final int TRANSACTION_onHostViewResume = 2;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        class Proxy implements ISysUiInlineService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISysUiInlineService.DESCRIPTOR;
            }

            @Override // com.oplus.sysuiinlineview.ISysUiInlineService
            public void onHostViewCommand(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ISysUiInlineService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.m490$$Nest$smwriteTypedObject(parcelObtain, bundle);
                    this.mRemote.transact(7, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplus.sysuiinlineview.ISysUiInlineService
            public void onHostViewConfigurationChanged(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ISysUiInlineService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.m490$$Nest$smwriteTypedObject(parcelObtain, bundle);
                    this.mRemote.transact(6, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplus.sysuiinlineview.ISysUiInlineService
            public void onHostViewCreate(String str, ISysUiInlineCallback iSysUiInlineCallback, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ISysUiInlineService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongInterface(iSysUiInlineCallback);
                    _Parcel.m490$$Nest$smwriteTypedObject(parcelObtain, bundle);
                    this.mRemote.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplus.sysuiinlineview.ISysUiInlineService
            public void onHostViewDestroy(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ISysUiInlineService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.m490$$Nest$smwriteTypedObject(parcelObtain, bundle);
                    this.mRemote.transact(5, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplus.sysuiinlineview.ISysUiInlineService
            public void onHostViewLayout(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ISysUiInlineService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.m490$$Nest$smwriteTypedObject(parcelObtain, bundle);
                    this.mRemote.transact(3, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplus.sysuiinlineview.ISysUiInlineService
            public void onHostViewPause(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ISysUiInlineService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.m490$$Nest$smwriteTypedObject(parcelObtain, bundle);
                    this.mRemote.transact(4, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplus.sysuiinlineview.ISysUiInlineService
            public void onHostViewResume(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ISysUiInlineService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.m490$$Nest$smwriteTypedObject(parcelObtain, bundle);
                    this.mRemote.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ISysUiInlineService.DESCRIPTOR);
        }

        public static ISysUiInlineService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(ISysUiInlineService.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ISysUiInlineService)) ? new Proxy(iBinder) : (ISysUiInlineService) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISysUiInlineService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISysUiInlineService.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    String string = parcel.readString();
                    ISysUiInlineCallback iSysUiInlineCallbackAsInterface = ISysUiInlineCallback.Stub.asInterface(parcel.readStrongBinder());
                    Parcelable.Creator creator = Bundle.CREATOR;
                    onHostViewCreate(string, iSysUiInlineCallbackAsInterface, (Bundle) _Parcel.m489$$Nest$smreadTypedObject(parcel));
                    return true;
                case 2:
                    String string2 = parcel.readString();
                    Parcelable.Creator creator2 = Bundle.CREATOR;
                    onHostViewResume(string2, (Bundle) _Parcel.m489$$Nest$smreadTypedObject(parcel));
                    return true;
                case 3:
                    String string3 = parcel.readString();
                    Parcelable.Creator creator3 = Bundle.CREATOR;
                    onHostViewLayout(string3, (Bundle) _Parcel.m489$$Nest$smreadTypedObject(parcel));
                    return true;
                case 4:
                    String string4 = parcel.readString();
                    Parcelable.Creator creator4 = Bundle.CREATOR;
                    onHostViewPause(string4, (Bundle) _Parcel.m489$$Nest$smreadTypedObject(parcel));
                    return true;
                case 5:
                    String string5 = parcel.readString();
                    Parcelable.Creator creator5 = Bundle.CREATOR;
                    onHostViewDestroy(string5, (Bundle) _Parcel.m489$$Nest$smreadTypedObject(parcel));
                    return true;
                case 6:
                    String string6 = parcel.readString();
                    Parcelable.Creator creator6 = Bundle.CREATOR;
                    onHostViewConfigurationChanged(string6, (Bundle) _Parcel.m489$$Nest$smreadTypedObject(parcel));
                    return true;
                case 7:
                    String string7 = parcel.readString();
                    Parcelable.Creator creator7 = Bundle.CREATOR;
                    onHostViewCommand(string7, (Bundle) _Parcel.m489$$Nest$smreadTypedObject(parcel));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class _Parcel {
        /* JADX INFO: renamed from: -$$Nest$smreadTypedObject, reason: not valid java name */
        public static Object m489$$Nest$smreadTypedObject(Parcel parcel) {
            Parcelable.Creator creator = Bundle.CREATOR;
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: renamed from: -$$Nest$smwriteTypedObject, reason: not valid java name */
        public static void m490$$Nest$smwriteTypedObject(Parcel parcel, Parcelable parcelable) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                ((Bundle) parcelable).writeToParcel(parcel, 0);
            }
        }
    }

    void onHostViewCommand(String str, Bundle bundle);

    void onHostViewConfigurationChanged(String str, Bundle bundle);

    void onHostViewCreate(String str, ISysUiInlineCallback iSysUiInlineCallback, Bundle bundle);

    void onHostViewDestroy(String str, Bundle bundle);

    void onHostViewLayout(String str, Bundle bundle);

    void onHostViewPause(String str, Bundle bundle);

    void onHostViewResume(String str, Bundle bundle);
}
