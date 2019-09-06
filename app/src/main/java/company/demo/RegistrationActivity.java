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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class RegistrationActivity extends AppCompatActivity {

    /****** RegistrationActivity ActionBar ****/
    private ActionBar actionbar;

    /****** RegistrationActivity input fields ****/
    private TextInputLayout firstNameTextInputLayout,lastNameTextInputLayout,emailTextInputLayout,passwordTextInputLayout,confirmPasswordTextInputLayout;
    private TextInputEditText firstNameTextInputEditText,lastNameTextInputEditText,emailTextInputEditText,passwordTextInputEditText,confirmPasswordTextInputEditText;

    /****** RegistrationActivity click events ****/
    private Button registerButton;
    private TextView loginButtonLink;

    /****** RegistrationActivity string variable ****/
    private String firstNameString,lastNameString,emailString,passwordString,confirmPasswordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //******* set action bar **********
        actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViews();
        onTextChangedValidation();

        //******* login click event **********
        registerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                firstNameString = Objects.requireNonNull(firstNameTextInputEditText.getText()).toString();
                lastNameString = Objects.requireNonNull(lastNameTextInputEditText.getText()).toString();
                emailString = Objects.requireNonNull(emailTextInputEditText.getText()).toString();
                passwordString = Objects.requireNonNull(passwordTextInputEditText.getText()).toString();
                confirmPasswordString = Objects.requireNonNull(confirmPasswordTextInputEditText.getText()).toString();

                if (loginValidation()) {
                    Register(firstNameString,lastNameString,emailString,passwordString,confirmPasswordString);
                }
            }
        });

        //******* register click event **********
        loginButtonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterActivity = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(goToRegisterActivity);
            }
        });
    }

    private void initViews() {

        firstNameTextInputLayout = findViewById(R.id.first_name_text_input_layout);
        firstNameTextInputEditText = findViewById(R.id.first_name_edit_text);

        lastNameTextInputLayout = findViewById(R.id.last_name_text_input_layout);
        lastNameTextInputEditText = findViewById(R.id.last_name_edit_text);

        emailTextInputLayout = findViewById(R.id.email_text_input_layout);
        emailTextInputEditText = findViewById(R.id.email_edit_text);

        passwordTextInputLayout = findViewById(R.id.password_text_input_layout);
        passwordTextInputEditText = findViewById(R.id.password_edit_text);
        passwordTextInputEditText.setTransformationMethod(new PasswordTransformationMethod());

        confirmPasswordTextInputLayout = findViewById(R.id.confirm_password_text_input_layout);
        confirmPasswordTextInputEditText = findViewById(R.id.confirm_password_edit_text);
        confirmPasswordTextInputEditText.setTransformationMethod(new PasswordTransformationMethod());

        registerButton = findViewById(R.id.register_button);
        loginButtonLink= findViewById(R.id.login_link);
    }

    private void onTextChangedValidation() {

        firstNameTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {

                if (text.length() < 1) {
                    firstNameTextInputLayout.setErrorEnabled(true);
                    firstNameTextInputLayout.setError("Please enter first name!");
                }
                if (text.length() > 0) {
                    firstNameTextInputLayout.setError(null);
                    firstNameTextInputLayout.setErrorEnabled(false);
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

        lastNameTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {

                if (text.length() < 1) {
                    lastNameTextInputLayout.setErrorEnabled(true);
                    lastNameTextInputLayout.setError("Please enter last name!");
                }
                if (text.length() > 0) {
                    lastNameTextInputLayout.setError(null);
                    lastNameTextInputLayout.setErrorEnabled(false);
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

        confirmPasswordTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {

                if (text.length() < 1) {
                    confirmPasswordTextInputLayout.setErrorEnabled(true);
                    confirmPasswordTextInputLayout.setError("Please enter confirm password!");
                }
                if (text.length() > 0)
                {
                    confirmPasswordTextInputLayout.setError(null);
                    confirmPasswordTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                String passwordString = Objects.requireNonNull(passwordTextInputEditText.getText()).toString();
                String confirmPasswordString = Objects.requireNonNull(confirmPasswordTextInputEditText.getText()).toString();
                if(!confirmPasswordString.equals(passwordString)){
                    confirmPasswordTextInputLayout.setError("Password and Confirm password not match !");
                }
            }
        });
    }

    private boolean loginValidation()
    {
        if (Objects.requireNonNull(firstNameTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter first name !";
            firstNameTextInputLayout.setError(message);
            return false;
        }
        if (Objects.requireNonNull(lastNameTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter last name !";
            lastNameTextInputLayout.setError(message);
            return false;
        }
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

        if (Objects.requireNonNull(confirmPasswordTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter confirm password !";
            confirmPasswordTextInputLayout.setError(message);
            return false;
        }
        if (!Objects.requireNonNull(passwordTextInputEditText.getText()).toString().equals(Objects.requireNonNull(confirmPasswordTextInputEditText.getText()).toString()))
        {
            String message = "Password and Confirm password not match !";
            confirmPasswordTextInputLayout.setError(message);
            return false;
        }
        return true;
    }

    public boolean emailValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void Register(String firstNameString, String lastNameString, String emailString, String passwordString, String confirmPasswordString)
    {
        try
        {
            AppSharedPreferences.getInstance(RegistrationActivity.this).clearData();
            AppSharedPreferences.getInstance(RegistrationActivity.this).register(firstNameString,lastNameString,emailString,passwordString);
            Intent goToAddLocationAndUserActivity = new Intent(RegistrationActivity.this,LoginActivity.class);
            goToAddLocationAndUserActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            goToAddLocationAndUserActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(goToAddLocationAndUserActivity);
            Toast.makeText(getApplicationContext(), "Register Success, Login here", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
