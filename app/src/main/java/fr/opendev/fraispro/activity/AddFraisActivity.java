package fr.opendev.fraispro.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import fr.opendev.fraispro.R;
import fr.opendev.fraispro.model.Frais;
import fr.opendev.fraispro.utils.Constants;

public class AddFraisActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    Button add;
    EditText valueFrais;
    EditText nameFrais;
    ConstraintLayout constraintLayout;
    private FirebaseAuth auth;
    private String userUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_frais);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
        {
            userUID = auth.getCurrentUser().getUid();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.new_frais);

        add = (Button) findViewById(R.id.addButton);
        valueFrais = (EditText) findViewById(R.id.value_frais);
        nameFrais = (EditText) findViewById(R.id.name_frais);
        constraintLayout = (ConstraintLayout)findViewById(R.id.constraint_layout);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UUID fraisUID = UUID.randomUUID();
                DatabaseReference mRef = mDatabase.getRef().child(Constants.FRAIS_NODE).child(userUID).child(fraisUID.toString());
                float value = Float.valueOf(valueFrais.getText().toString());
                String name = nameFrais.getText().toString();
                mRef.setValue(new Frais(value, name));

                finish();
            }
        });


    }
}
