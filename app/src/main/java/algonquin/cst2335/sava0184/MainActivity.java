package algonquin.cst2335.sava0184;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Parth Savaliya
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /**
     * This hold textview
     */
    private TextView tv = null;
    /**
     * THis holds password
     */
    private EditText et = null;
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textview);
        et = findViewById(R.id.edittext);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = et.getText().toString();
                checkPassWordComplexity(password);
            }

            /** This Function is to check password checking
             *
             *
             * @param password The String Object that are we checking
             * @retutn Return true if password is strong
             */

            boolean checkPassWordComplexity(String password) {

                boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;

                foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
                if (!foundUpperCase) {

                    Toast.makeText(getApplicationContext(), "Password should have atleast one upper case", Toast.LENGTH_SHORT).show();
                    ;// Say that they are missing an upper case letter;

                    return false;

                } else if (!foundLowerCase) {
                    Toast.makeText(getApplicationContext(), "Password should have atleast one Lowe case", Toast.LENGTH_SHORT).show();
                    ;// Say that they are missing an lower case letter;

                    return false;

                } else if (!foundNumber) {
                    Toast.makeText(getApplicationContext(), "Password should have atleast one Number", Toast.LENGTH_SHORT).show();
                    ;// Say that they are missing a number;
                    return false;
                } else if (!foundSpecial) {
                    Toast.makeText(getApplicationContext(), "Password should have atleast one Special character", Toast.LENGTH_SHORT).show();
                    ;// Say that they are missing a special character ;
                    return false;
                } else

                    return true; //only get here if they're all true

            }

            /**
             *
             * @param c Charachter
             * @return returns true if it found the special character
             */
            boolean isSpecialCharacter(char c) {
                switch (c) {
                    case '#':
                    case '?':
                    case '*':
                    case '%':
                    case '^':
                    case '$':
                    case '&':
                    case '!':
                        return true;
                    default:
                        return false;
                }
            }
        });


    }
}