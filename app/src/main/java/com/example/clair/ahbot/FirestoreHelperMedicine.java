package com.example.clair.ahbot;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FirestoreHelperMedicine {

    FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    CollectionReference medicineCollection = firebaseFirestore.collection("MedicineList");


    List<Medicine> medicineList=new ArrayList<>();

    public FirestoreHelperMedicine(){}
    public FirestoreHelperMedicine(ViewMedicine r) {
        final ViewMedicine reference = r;
        medicineCollection
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {

                        String medName = document.getString("MedicineName");
                        String amt = document.getString("Amount");
                        String freq = document.getString("Frequency");
                        String remarks = document.getString("Remarks");


                        Medicine medicine = new Medicine(medName, amt, freq, remarks);
                        medicineList.add(medicine);
                    }

                }

                else{
                    Log.d(TAG,"Error getting documents: ",task.getException());
                }
                reference.getMedicine(medicineList);
            }
        });                               }
       }


