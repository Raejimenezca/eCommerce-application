package example.tugangazo.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import example.tugangazo.com.Admin.AdminCategoryActivity;
import example.tugangazo.com.Model.Users;
import example.tugangazo.com.Prevalent.Prevalent;
import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText inputPhoneNumber, inputPassword;
    private Button loginButton;
    private ProgressDialog loadingBar;
    private TextView adminLink, notAdminLink, forgetPasswordLink;

    private String parentDBName = "Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_btn);
        inputPassword = (EditText) findViewById(R.id.login_password_input);
        inputPhoneNumber = (EditText) findViewById(R.id.login_phone_number_input);
        adminLink = (TextView) findViewById(R.id.admin_panel_link);
        notAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        forgetPasswordLink = findViewById(R.id.forget_password_link);
        loadingBar = new ProgressDialog(this);


        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chbx);
        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        forgetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                intent.putExtra("check", "login");
                startActivity(intent);
            }
        });

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Administrar");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                parentDBName = "Admins";
            }
        });

        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Ingresa");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                parentDBName = "Users";
            }
        });
    }

    private void LoginUser() {
        String phone = inputPhoneNumber.getText().toString();
        String password = inputPassword.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Por favor escribe tu número de telefono...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Por favor escribe tu contraseña...", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Ingresar");
            loadingBar.setMessage("Por favor espera mientras revisamos las credenciales.");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {
        if (chkBoxRememberMe.isChecked()){
            Paper.book().write(Prevalent.userPhoneKey, phone);
            Paper.book().write(Prevalent.userPasswordKey, password);
        }

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDBName).child(phone).exists()){
                    Users usersData = dataSnapshot.child(parentDBName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone)){

                        if (usersData.getPassword().equals(password)){
                            if (parentDBName.equals("Admins")){
                                Toast.makeText(LoginActivity.this, "Has ingresado como administrador satisfactoriamente!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(intent);
                            }
                            else if (parentDBName.equals("Users")){
                                Toast.makeText(LoginActivity.this, "Has ingresado satisfactoriamente!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Contraseña no valida", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"No existe una cuenta con número de telefono " + phone ,Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
