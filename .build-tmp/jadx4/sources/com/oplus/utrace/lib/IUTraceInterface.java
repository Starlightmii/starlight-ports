package com.oplus.utrace.lib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IUTraceInterface extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.utrace.lib.IUTraceInterface";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Stub extends Binder implements IUTraceInterface {
        static final int TRANSACTION_addTrace = 1;
        static final int TRANSACTION_addTrace2 = 2;
        static final int TRANSACTION_addTraces = 3;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        class Proxy implements IUTraceInterface {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oplus.utrace.lib.IUTraceInterface
            public void addTrace(UTraceRecord uTraceRecord) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IUTraceInterface.DESCRIPTOR);
                    if (uTraceRecord != null) {
                        parcelObtain.writeInt(1);
                        uTraceRecord.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplus.utrace.lib.IUTraceInterface
            public int addTrace2(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IUTraceInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.oplus.utrace.lib.IUTraceInterface
            public int addTraces(List<String> list) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IUTraceInterface.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IUTraceInterface.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, IUTraceInterface.DESCRIPTOR);
        }

        public static IUTraceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IUTraceInterface.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IUTraceInterface)) ? new Proxy(iBinder) : (IUTraceInterface) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IUTraceInterface.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IUTraceInterface.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                addTrace((UTraceRecord) (parcel.readInt() != 0 ? UTraceRecord.CREATOR.createFromParcel(parcel) : null));
                parcel2.writeNoException();
            } else if (i == 2) {
                int iAddTrace2 = addTrace2(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(iAddTrace2);
            } else {
                if (i != 3) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                int iAddTraces = addTraces(parcel.createStringArrayList());
                parcel2.writeNoException();
                parcel2.writeInt(iAddTraces);
            }
            return true;
        }
    }

    void addTrace(UTraceRecord uTraceRecord);

    int addTrace2(String str);

    int addTraces(List<String> list);
}
