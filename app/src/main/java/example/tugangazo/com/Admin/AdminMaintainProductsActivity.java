package example.tugangazo.com.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import example.tugangazo.com.R;

public class AdminMaintainProductsActivity extends AppCompatActivity {
    private Button applyChangesBtn, deleteBtn;
    private EditText name, price, description;
    private ImageView imageView;

    private String productID = "";

    private DatabaseReference productsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_products);

        productID = getIntent().getStringExtra("pid");
        productsRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);


        applyChangesBtn = findViewById(R.id.apply_changes_btn);
        name = findViewById(R.id.product_name_maintain);
        price = findViewById(R.id.product_price_maintain);
        description = findViewById(R.id.product_description_maintain);
        imageView = findViewById(R.id.product_image_maintain);
        deleteBtn = findViewById(R.id.delete_product_btn);

        DisplaySpecificProductInfo();

        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyChanges();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteThisProduct();
            }
        });
    }

    private void DeleteThisProduct() {
        productsRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(AdminMaintainProductsActivity.this, AdminCategoryActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(AdminMaintainProductsActivity.this, "El producto ha sido borrado correctamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ApplyChanges() {
        String pName = name.getText().toString();
        String pPrice = price.getText().toString();
        String pDescription = description.getText().toString();
        
        if (pName.equals("")){
            Toast.makeText(this, "Escriba el nombre del producto.", Toast.LENGTH_SHORT).show();
        }
        else if (pPrice.equals("")){
            Toast.makeText(this, "Escriba el precio del producto.", Toast.LENGTH_SHORT).show();
        }
        else if (pDescription.equals("")){
            Toast.makeText(this, "Escriba la descripción del producto.", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productID);
            productMap.put("description", pDescription);
            productMap.put("price", pPrice);
            productMap.put("pr_name", pName);

            productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(AdminMaintainProductsActivity.this, "Los cambios han sido aplicados satisfactoriamente.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AdminMaintainProductsActivity.this, AdminCategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }


    private void DisplaySpecificProductInfo() {
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String pName = dataSnapshot.child("pr_name").getValue().toString();
                    String pPrice = dataSnapshot.child("price").getValue().toString();
                    String pDescription = dataSnapshot.child("description").getValue().toString();
                    String pImage = dataSnapshot.child("image").getValue().toString();

                    name.setText(pName);
                    price.setText(pPrice);
                    description.setText(pDescription);
                    Picasso.get().load(pImage).into(imageView);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
