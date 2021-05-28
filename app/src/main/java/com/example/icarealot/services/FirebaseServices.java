package com.example.icarealot.services;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseServices {

  private static FirebaseAuth mAuth;
  private static FirebaseUser mUser;
  private static FirebaseDatabase mDatabase;

  public static FirebaseAuth getFirebaseAuthInstance() {
    if (mAuth == null) {
      mAuth = FirebaseAuth.getInstance();
    }
    return mAuth;
  }

  public static void getFirebaseAuthLogout() {
    FirebaseAuth.getInstance().signOut();
  }


  public static FirebaseDatabase getFirebaseDatabaseInstance() {
    if (mDatabase == null) {
      mDatabase = FirebaseDatabase.getInstance();
    }
    return mDatabase;
  }

  public static FirebaseUser getFirebaseUserCurrentUserInstance() {
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    if (currentUser != null) {
      return currentUser;
    }
    return null;
  }

  public static void setFirebaseUser(FirebaseUser user) {
    mUser = user;
  }

  public static FirebaseUser getFirebaseUser() {
    return mUser;
  }

}
