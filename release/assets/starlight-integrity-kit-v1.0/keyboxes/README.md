# Keyboxes

Validated on the device with:

```sh
duckd tee-attestation keybox <file>
```

| File | Size | sha256 | Verdict |
|---|---:|---|---|
| `Yurikey70.xml` | 5590 B | `f005433f37832860a9a595627e19d04511c6378b2725b7f88fda912f648c59eb` | **Valid structure** (`ok:true`), ECDSA, 4 certificates, `trust_verified:false`, `revocation_checked:false`. Leaf `notAfter = Nov 25 05:44:23 2026 GMT`. |
| `evokerrkey49.xml` | 4710 B | `2a4cd97a0359e020a5d305e159b8ca02da61f2a4ae9e47488360b1762fd7aa52` | **Invalid** (`invalid_keybox`: expected one complete keybox XML with matching counts, PEM keys and certificate chains). Do not install. |

`trust_verified:false` means the local verifier did not confirm a Google trust
anchor; it does **not** mean the keybox is syntactically broken. The keybox is
leaked attestation material, not this device's TEE key. Google can revoke it
at any time; the leaf expires on 2026-11-25.

`Yurikey70.xml` is the only keybox in this folder that the kit's `keybox`
action will accept.
