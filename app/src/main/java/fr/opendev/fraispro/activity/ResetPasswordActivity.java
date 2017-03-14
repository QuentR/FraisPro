package fr.opendev.fraispro.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.opendev.fraispro.R;

public class ResetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText inputEmail;
    @BindView(R.id.btn_reset_password)
    Button btnReset;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);


        auth = FirebaseAuth.getInstance();
        setOnClickListeners();


    }


    private void setOnClickListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Veuillez saisir votre adresse mail pour la récupération du mot de passe", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Snackbar snackbar = Snackbar
                                            .make(coordinatorLayout, "Un mail contenant les instructions de réinitialisation du mot de passe vous a été envoyé!", Snackbar.LENGTH_SHORT);
                                    snackbar.show();
                                } else {
                                    Snackbar snackbar = Snackbar
                                            .make(coordinatorLayout, "Erreur lors de l'envoi de l'email...", Snackbar.LENGTH_SHORT);
                                    View sbView = snackbar.getView();
                                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                                    textView.setTextColor(Color.RED);
                                    snackbar.show();
                                    snackbar.show();
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

}
