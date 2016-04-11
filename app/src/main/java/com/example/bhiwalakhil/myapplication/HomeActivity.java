package com.example.bhiwalakhil.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

   char[] letters = "acdegilmnoprstuw".toCharArray();
   EditText textString;
   TextView textViewAnswer, labelCurrentStatus;
   RelativeLayout wrapButton, wrapProcessing;
   ProgressBar progressBar;
   private TextView textCurrentStatu;
   private CustomASy customASy;
   private StringBuilder builder;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_home);
	  textString = (EditText) findViewById(R.id.inputString);
	  textViewAnswer = (TextView) findViewById(R.id.answer);
	  labelCurrentStatus = (TextView) findViewById(R.id.labelCurrentStatus);
	  textCurrentStatu = (TextView) findViewById(R.id.currentStatus);
	  wrapButton = (RelativeLayout) findViewById(R.id.relWrapButton);
	  wrapProcessing = (RelativeLayout) findViewById(R.id.relWrapProcessing);
	  progressBar = (ProgressBar) findViewById(R.id.progressBar);
	  wrapProcessing.setVisibility(View.GONE);
   }

   public void findString(View view) {
	  String inputString = textString.getText().toString();
	  wrapButton.setVisibility(View.GONE);
	  wrapProcessing.setVisibility(View.VISIBLE);

	  customASy = new CustomASy();
	  customASy.execute(Long.valueOf(inputString));
   }

   class CustomASy extends AsyncTask<Long, String, String> {

	  RevString revString;

	  @Override
	  protected void onPreExecute() {
		 super.onPreExecute();
//		 progressBar.setVisibility(View.VISIBLE);
		 revString = new RevString();

	  }

	  @Override
	  protected String doInBackground(Long... params) {


		 String curString = "aaaaa";


		 while (!(revString.hash(curString) == params[0])) {
//			curString = stringUP(curString, letters, 4);
			curString = revString.nextUpString(curString);
			publishProgress(curString);
		 }
		 return "Given hash \'" + params[0] + "\' is from String \'" + curString + "\'.";

//		 return String.valueOf(hash("enums"));  //For verification of solution
	  }

	  @Override
	  protected void onProgressUpdate(String... values) {
		 super.onProgressUpdate(values);
		 textCurrentStatu.setText(values[0]);

	  }

	  @Override
	  protected void onPostExecute(String s) {
		 super.onPostExecute(s);
		 progressBar.setVisibility(View.GONE);
//		 Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
		 if (textViewAnswer != null)
			textViewAnswer.setText(s);
		 if (textViewAnswer != null) {
			textViewAnswer.setVisibility(View.VISIBLE);
		 }
		 if (labelCurrentStatus != null) {
			labelCurrentStatus.setVisibility(View.INVISIBLE);
		 }

	  }
   }

//   private long hash(String s) {
//	  long h = 7;
//
//
//	  for (int i = 0; i < s.length(); i++) {
////            letters[s.toCharArray()[i]]
//		 int count = 0;
//		 while (letters[count] != s.toCharArray()[i]) {
//			count++;
//		 }
//		 h = (h * 37 + (count));
//	  }
//	  return h;
//   }


}
