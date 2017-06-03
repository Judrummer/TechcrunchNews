

package com.github.judrummer.techcrunchnews.extension

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

fun FragmentManager.transaction(transactionBlock: (FragmentTransaction).() -> (Unit)) {
    val transaction = beginTransaction()
    transaction.transactionBlock()
    transaction.commit()
}

