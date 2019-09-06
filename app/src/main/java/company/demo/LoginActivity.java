package company.demo;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class LoginActivity extends AppCompatActivity {

    /****** LoginActivity ActionBar ****/
    private ActionBar actionbar;

    /****** LoginActivity input fields ****/
    private TextInputLayout emailTextInputLayout, passwordTextInputLayout;
    private TextInputEditText emailTextInputEditText, passwordTextInputEditText;

    /****** LoginActivity click events ****/
    private Button loginButton;
    private TextView registerButtonLink;

    /****** LoginActivity string variable ****/
    private String emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //******* set action bar **********
        actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViews();
        onTextChangedValidation();

        //******* login click event **********
        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                emailString = Objects.requireNonNull(emailTextInputEditText.getText()).toString();
                passwordString = Objects.requireNonNull(passwordTextInputEditText.getText()).toString();

                if (loginValidation()) {
                    Login_In(emailString, passwordString);
                }
            }
        });

        //******* register click event **********
        registerButtonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterActivity = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(goToRegisterActivity);
            }
        });
    }

    private void initViews() {

        emailTextInputLayout = findViewById(R.id.email_text_input_layout);
        emailTextInputEditText = findViewById(R.id.email_edit_text);

        passwordTextInputLayout = findViewById(R.id.password_text_input_layout);
        passwordTextInputEditText = findViewById(R.id.password_edit_text);
        passwordTextInputEditText.setTransformationMethod(new PasswordTransformationMethod());

        loginButton = findViewById(R.id.login_button);
        registerButtonLink= findViewById(R.id.register_link);
    }

    private void onTextChangedValidation() {
        emailTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {

                if (text.length() < 1) {
                    emailTextInputLayout.setErrorEnabled(true);
                    emailTextInputLayout.setError("Please enter your email !");
                }
                if (text.length() > 0) {
                    emailTextInputLayout.setError(null);
                    emailTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                String emailString = Objects.requireNonNull(emailTextInputEditText.getText()).toString();
                if(!emailValid(emailString))
                {
                    emailTextInputLayout.setError("Please enter valid email !");
                }
            }
        });

        passwordTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {

                if (text.length() < 1) {
                    passwordTextInputLayout.setErrorEnabled(true);
                    passwordTextInputLayout.setError("Please enter password !");
                }
                if (text.length() > 0) {
                    passwordTextInputLayout.setError(null);
                    passwordTextInputLayout.setErrorEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean loginValidation()
    {
        if (Objects.requireNonNull(emailTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter your email !";
            emailTextInputLayout.setError(message);
            return false;
        }else if(!emailValid(emailString))
        {
            emailTextInputLayout.setError("Please enter valid email !");
            return false;
        }

        if (Objects.requireNonNull(passwordTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter password !";
            passwordTextInputLayout.setError(message);
            return false;
        }
        return true;
    }

    public boolean emailValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void Login_In(String emailString,String passwordString){

        if(emailString.equals(AppSharedPreferences.getInstance(LoginActivity.this).getEmail()) && passwordString.equals(AppSharedPreferences.getInstance(LoginActivity.this).getPassword()))
        {
            Intent goToHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(goToHomeActivity);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
        }
    }
}
